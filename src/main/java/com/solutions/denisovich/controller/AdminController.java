package com.solutions.denisovich.controller;

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

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    private static final String ADD_USER = "Add user";
    private static final String EDIT_USER = "Edit user";

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/admin")
    public String getAdminHomePage(Principal principal, Model model) {
        List<User> allUsers = userService.findAll();
        Map<User, Integer> usersWithAge = userService.getUsersWithAge(allUsers);
        model.addAttribute("users", usersWithAge);
        User user = getUserFromPrincipal(principal);
        model.addAttribute("adminLastName", user.getLastName());
        model.addAttribute("principalId", user.getId());
        return "adminHomePage";
    }

    @GetMapping("/admin/showUserForm")
    public ModelAndView getAddUserForm(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserFromPrincipal(principal);
        modelAndView.setViewName("userForm");

        modelAndView.addObject("user", new User());
        modelAndView.addObject("adminLastName", user.getLastName());
        modelAndView.addObject("titleName", ADD_USER);
        modelAndView.addObject("isAddUser", true);
        modelAndView.addObject("isEditUser", false);
        return modelAndView;
    }

    @GetMapping("/admin/showUpdateUser")
    public ModelAndView getFormToEditUser(@RequestParam("userId") Long id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserFromPrincipal(principal);
        User userById = userService.findById(id);
        userById.setPreviousPassword(userById.getPassword());

        modelAndView.setViewName("userForm");
        modelAndView.addObject("user", userById);
        modelAndView.addObject("adminLastName", user.getLastName());
        modelAndView.addObject("titleName", EDIT_USER);
        modelAndView.addObject("isEditUser", true);
        modelAndView.addObject("isPrincipal", isPrincipal(id, principal));
        modelAndView.addObject("isAddUser", false);
        return modelAndView;
    }

    @PostMapping("/admin/editUser")
    public String editUserById(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Principal principal, Model model) {
        if (user.getPassword() == null) {
            user.setPassword(user.getPreviousPassword());
        } else if (bindingResult.hasFieldErrors("password")
                || bindingResult.hasFieldErrors("firstName")
                || bindingResult.hasFieldErrors("lastName")
                || bindingResult.hasFieldErrors("email")
                || bindingResult.hasFieldErrors("birthday")) {
            createModelToEditUser(principal, model);
            return "userForm";
        } else {
            User userByLogin = userService.findByLogin(user.getLogin());
            if (!userByLogin.getPassword().equals(user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
        }
        userService.update(user);
        return "redirect:/admin";
    }

    private void createModelToAddUser(Principal principal, Model model) {
        User userFromPrincipal = getUserFromPrincipal(principal);
        model.addAttribute("adminLastName", userFromPrincipal.getLastName());
        model.addAttribute("titleName", ADD_USER);
        model.addAttribute("isAddUser", true);
        model.addAttribute("isEditUser", false);
    }

    private void createModelToEditUser(Principal principal, Model model) {
        User userFromPrincipal = getUserFromPrincipal(principal);
        model.addAttribute("adminLastName", userFromPrincipal.getLastName());
        model.addAttribute("titleName", EDIT_USER);
        model.addAttribute("isAddUser", false);
        model.addAttribute("isEditUser", true);
    }

    @PostMapping("/admin/addUser")
    public String addNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Principal principal, Model model) {
        if (bindingResult.hasErrors()) {
            createModelToAddUser(principal, model);
            return "userForm";
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.create(user);
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/deleteUser")
    public String deleteUserById(@RequestParam("userId") Long id) {
        User userById = userService.findById(id);
        userService.remove(userById);
        return "redirect:/admin";
    }

    private boolean isPrincipal(Long id, Principal principal) {
        boolean isPrincipal = false;
        User user = getUserFromPrincipal(principal);
        if (user.getId().equals(id)) {
            isPrincipal = true;
        }
        return isPrincipal;
    }

    private User getUserFromPrincipal(Principal principal) {
        return userService.findByLogin(principal.getName());
    }
}
