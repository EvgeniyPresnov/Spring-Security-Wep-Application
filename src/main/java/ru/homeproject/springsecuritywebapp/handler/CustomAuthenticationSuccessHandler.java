package ru.homeproject.springsecuritywebapp.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.homeproject.springsecuritywebapp.service.impl.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static ru.homeproject.springsecuritywebapp.util.AuthConstants.USER_LOG_IN;

/**
 * This class handles success user's login.
 *
 */
@Log4j2
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String username = userDetailsService.loadUserByUsername(authentication.getName()).getUsername();
        String password = userDetailsService.loadUserByUsername(authentication.getName()).getPassword();
        Collection<? extends GrantedAuthority> authorities =
                userDetailsService.loadUserByUsername(authentication.getName()).getAuthorities();

        System.out.println("The user with " +
                "username = " + username + ", "  +
                "password = " + password + ", " +
                "authorities = " + authorities +
                " is log in");
        log.warn(String.format(USER_LOG_IN, username, password, authorities));
        response.sendRedirect(request.getContextPath() + "/books/list");
    }

}
