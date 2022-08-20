package ru.homeproject.springsecuritywebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.homeproject.springsecuritywebapp.entity.User;
import ru.homeproject.springsecuritywebapp.service.UserService;
import ru.homeproject.springsecuritywebapp.validation.UserRegistrationValidator;

/**
 * This class handles the requests which the URL ends with "/accounts".
 *
 */

@Controller
@RequestMapping("/accounts")
public class RegistrationController {

    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userRegistrationValidator);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createAccount(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation error!");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/books/list";
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }
}
