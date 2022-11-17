package ru.homeproject.springsecuritywebapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.homeproject.springsecuritywebapp.entity.User;
import ru.homeproject.springsecuritywebapp.exception.UserAlreadyExistException;
import ru.homeproject.springsecuritywebapp.service.UserService;
import ru.homeproject.springsecuritywebapp.validation.RegistrationValidator;

/**
 * This class handles the requests which the URL ends with "/accounts".
 *
 */

@Controller
@RequestMapping("/accounts")
@Log4j2
public class RegistrationController {

    private static final String INPUT_DATA = "userName";
    private static final String ERROR_MESSAGE = "An account already exists for this user";

    @Autowired
    private RegistrationValidator registrationValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationValidator);
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
        try {
            userService.createUser(user);
        }  catch (UserAlreadyExistException e) {
            bindingResult.rejectValue(INPUT_DATA, "", ERROR_MESSAGE);
            log.warn("User with name '" + user.getUserName() + "' already exists in database");
            return "registration";
        }

        return "redirect:/books/list";
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/accounts/users";
    }
}
