package ru.homeproject.springsecuritywebapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.homeproject.springsecuritywebapp.entity.User;

@Component
public class UserValidator implements Validator {

    private static final String USERNAME_INPUT_PARAMETER = "userName";
    private static final String PASSWORD_INPUT_PARAMETER = "password";
    private static final String ROLE_INPUT_PARAMETER = "roles";

    private static final String USERNAME_ERROR_MESSAGE = "user.userName.empty";
    private static final String PASSWORD_ERROR_MESSAGE = "user.password.empty";
    private static final String ROLE_ERROR_MESSAGE = "user.roles.empty";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME_INPUT_PARAMETER, USERNAME_ERROR_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD_INPUT_PARAMETER, PASSWORD_ERROR_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ROLE_INPUT_PARAMETER, ROLE_ERROR_MESSAGE);
    }
}
