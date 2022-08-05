package ru.homeproject.springsecuritywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.homeproject.springsecuritywebapp.repository")
public class SpringsecuritywebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritywebappApplication.class, args);
    }

}
