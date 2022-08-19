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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import ru.homeproject.springsecuritywebapp.handler.CustomAccessDeniedHandler;
import ru.homeproject.springsecuritywebapp.handler.CustomAuthenticationSuccessHandler;
import ru.homeproject.springsecuritywebapp.handler.CustomLogoutSuccessHandler;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final int EXPIRE_TIME = 60 * 60 * 24; // 1 day

    @Autowired
    private DataSource dataSource;

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
                .antMatchers("/accounts/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                    .loginPage("/auth/login")
                    .defaultSuccessUrl("/auth/login")
                    .successHandler(authenticationSuccessHandler())
                .and()
                .logout().permitAll()
                    .logoutUrl("/auth/logout")
                    .logoutSuccessHandler(logoutSuccessHandler())
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .rememberMe()
                    .rememberMeParameter("remember")
                    .key("secretKey")
                    .tokenValiditySeconds(EXPIRE_TIME)
                    .tokenRepository(persistentTokenRepository())
                    .userDetailsService(userDetailsService)
                ;
    }

    /**
     * This method stores the generated tokens in persistent_logins table.
     *
     * @return a database table.
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
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
