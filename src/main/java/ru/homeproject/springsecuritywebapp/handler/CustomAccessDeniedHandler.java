package ru.homeproject.springsecuritywebapp.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.homeproject.springsecuritywebapp.service.impl.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

import static ru.homeproject.springsecuritywebapp.util.AuthConstants.USER_ACCESS_DENIED;

@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final String ACCESS_DENIES_PAGE = "/auth/403";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        String username = userDetailsService.loadUserByUsername(authentication.getName()).getUsername();
        String password = userDetailsService.loadUserByUsername(authentication.getName()).getPassword();
        Collection<? extends GrantedAuthority> authorities =
                userDetailsService.loadUserByUsername(authentication.getName()).getAuthorities();

        if (authentication != null) {
            System.out.println("The user with " +
                    "username = " + username + ", "  +
                    "password = " + password + ", " +
                    "authorities = " + authorities +
                    " is attempted to access the protected URL: "
                    + request.getRequestURI());
            log.warn(String.format(USER_ACCESS_DENIED, username, password, authorities, request.getRequestURI()));
        }
        response.sendRedirect(request.getContextPath() + ACCESS_DENIES_PAGE);
    }
}
