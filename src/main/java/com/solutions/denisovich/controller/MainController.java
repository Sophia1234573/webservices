package com.solutions.denisovich.controller;

import com.solutions.denisovich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
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

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/")
    public String getRedirectedPages(Authentication auth) {

        if (auth == null) {
            return "login";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("user"))) {
            return "redirect: /user";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            return "redirect: /admin";
        }
        return "login";
    }

    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        boolean isWrongLoginOrPassword = false;
        if (error != null) {
            errorMessage = "Username or Password is incorrect!";
            isWrongLoginOrPassword = true;
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out!";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("isWrongLoginOrPassword", isWrongLoginOrPassword);
        return "login";
    }
}
