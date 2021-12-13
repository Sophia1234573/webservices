package com.solutions.denisovich.controller;

import com.solutions.denisovich.captcha.VerifyRecaptcha;
import com.solutions.denisovich.model.Role;
import com.solutions.denisovich.model.User;
import com.solutions.denisovich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    private final UserService userService;
    private static final String CAPTCHA_ERROR_MESSAGE = "Please, confirm that you are not a robot";
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/user")
    public String getUserHomePage(Principal principal, Model model) {
        User user = getUserFromPrincipal(principal);
        model.addAttribute("userFirstName", user.getFirstName());
        return "user";
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage() {
        return new ModelAndView("registration", "newUser", new User());
    }

    @PostMapping("/registration/addNewUser")
    public String addNewUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult bindingResult, HttpServletRequest request, Model model) throws IOException {

        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        if (!bindingResult.hasErrors() && verify) {
            newUser.setRole(new Role("user"));
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            userService.create(newUser);
            return "successRegistration";
        } else {
            model.addAttribute("errorCaptchaMessage", CAPTCHA_ERROR_MESSAGE);
            return "registration";
        }
    }

    private User getUserFromPrincipal(Principal principal) {
        return userService.findByLogin(principal.getName());
    }
}
