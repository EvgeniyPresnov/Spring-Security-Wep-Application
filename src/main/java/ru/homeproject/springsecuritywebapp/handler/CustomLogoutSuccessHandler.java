package ru.homeproject.springsecuritywebapp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import ru.homeproject.springsecuritywebapp.service.impl.UserDetailsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String username = userDetailsService.loadUserByUsername(authentication.getName()).getUsername();
        String password = userDetailsService.loadUserByUsername(authentication.getName()).getPassword();
        Collection<? extends GrantedAuthority> authorities =
                userDetailsService.loadUserByUsername(authentication.getName()).getAuthorities();

        System.out.println("The user with " +
                "username = " + username + ", "  +
                "password = " + password + ", " +
                "authorities = " + authorities +
                " is log out");

        super.onLogoutSuccess(request, response, authentication);
        //response.sendRedirect(request.getContextPath() + "/loginPage");
    }
}
