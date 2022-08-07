package ru.homeproject.springsecuritywebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.homeproject.springsecuritywebapp.handler.CustomAccessDeniedHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/books/list").permitAll()
                .antMatchers("/books/info").hasAnyAuthority("USER", "TEST")
                .antMatchers("/books/book/add").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/books/book/delete/**").hasAuthority("ADMIN")
                .antMatchers("/books/book/edit/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().
                formLogin().permitAll()
                    .loginPage("/auth/login")
                    .defaultSuccessUrl("/books/list")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
                .and()
                .logout().permitAll()
                    .logoutUrl("/auth/logout")
                    //.logoutSuccessUrl("/books/list")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and()
                //.exceptionHandling().accessDeniedPage("/auth/403")
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                ;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

}
