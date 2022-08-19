package ru.homeproject.springsecuritywebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.homeproject.springsecuritywebapp.entity.User;
import ru.homeproject.springsecuritywebapp.service.UserService;

/**
 * This class handles the requests which the URL ends with "/accounts".
 *
 */

@Controller
@RequestMapping("/accounts")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createAccount(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/books/list";
    }

    @GetMapping("/users")
    public String usersList() {
        return "users";
    }
}
