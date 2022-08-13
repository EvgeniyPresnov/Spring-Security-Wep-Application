package ru.homeproject.springsecuritywebapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homeproject.springsecuritywebapp.entity.User;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login(String error) {
        if (error != null) {
            System.out.println("Invalid username and password");
        }
        return "loginPage";
    }

    @PostMapping("/login")
    public String in(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "loginPage";
        }
        return "/view/books";
    }

    @GetMapping("/403")
    public String accessDeniedPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
           model.addAttribute("user", authentication.getName());
        }
        return "403Page";
    }
}
