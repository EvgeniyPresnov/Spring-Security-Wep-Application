package ru.homeproject.springsecuritywebapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.homeproject.springsecuritywebapp.entity.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static ru.homeproject.springsecuritywebapp.util.AuthConstants.INVALID_INPUT_DATA;
import static ru.homeproject.springsecuritywebapp.util.AuthConstants.START_LOGGING;
import static ru.homeproject.springsecuritywebapp.util.AuthConstants.STOP_LOGGING;

/**
 * This class handles the requests which the URL ends with "/auth".
 *
 */

@Controller
@RequestMapping("/auth")
@Log4j2
public class AuthController {

    @PostConstruct
    private void startLogging() {
        log.info(START_LOGGING);
    }

    /**
     * The method handles the request for displaying a login page.
     *
     * @param error
     * @return login page
     */
    @GetMapping("/login")
    public String login(String error, User user, Model model) {
        if (error != null) {
            System.out.println("Invalid username and password");
            log.info(String.format(INVALID_INPUT_DATA));
        }
        model.addAttribute("user", user);
        return "loginPage";
    }

    /**
     * The method handles the request for user that attempts to access a page
     * that is restricted to roles they do not have.
     *
     * @param model
     * @return access denied page
     */
    @GetMapping("/403")
    public String accessDeniedPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
           model.addAttribute("user", authentication.getName());
        }
        return "403Page";
    }

    @PreDestroy
    private void stopLogging() {
        log.info(STOP_LOGGING);
    }
}
