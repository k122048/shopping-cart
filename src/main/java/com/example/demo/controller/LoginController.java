package com.example.demo.controller;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home1() {
        return "redirect:/inventory";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login-page";
    }

    @GetMapping("/register")
    public String registerForm(WebRequest request, Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register-form";

    }


    @PostMapping("/register")
    public String registerUserAccount(
            @ModelAttribute("user") User user,
            HttpServletRequest request,
            Errors errors, Model model) {
        try {
            User registered = userService.registerNewUserAccount(user);
        } catch (UserAlreadyExistException uaeEx) {
            model.addAttribute("message", "An account for that username/email already exists.");
            return "bad-user";
        }

        return "redirect:/login";
        // rest of the implementation
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }
}
