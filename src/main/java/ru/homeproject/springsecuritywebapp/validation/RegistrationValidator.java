package ru.homeproject.springsecuritywebapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.homeproject.springsecuritywebapp.entity.User;

@Component
public class RegistrationValidator implements Validator {

    private static final String USERNAME_INPUT_PARAMETER = "userName";
    private static final String PASSWORD_INPUT_PARAMETER = "password";
    private static final String ROLE_INPUT_PARAMETER = "roles";

    private static final String USERNAME_ERROR_MESSAGE = "user.userName.empty";
    private static final String PASSWORD_ERROR_MESSAGE = "user.password.empty";
    private static final String ROLE_ERROR_MESSAGE = "user.roles.empty";

    private static final int MINIMUM_LENGTH = 8;
    private static final int MAXIMUM_LENGTH = 20;

    private static final String MIN_LENGTH = "8";
    private static final String MAX_LENGTH = "20";

    private static final String LOWER_CASE_CONDITION = ".*?[a-z].*";
    private static final String UPPER_CASE_CONDITION = ".*?[A-Z].*";
    private static final String NUMBER_CONDITION = ".*?[0-9].*";
    private static final String SEQUENCE_CONDITION = ".*?(.{2,})\\1.*";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String password = user.getPassword();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME_INPUT_PARAMETER, USERNAME_ERROR_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD_INPUT_PARAMETER, PASSWORD_ERROR_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ROLE_INPUT_PARAMETER, ROLE_ERROR_MESSAGE);

        if (password.length() < MINIMUM_LENGTH || password.length() > MAXIMUM_LENGTH) {
            errors.rejectValue(PASSWORD_INPUT_PARAMETER, PasswordError.CONSTRAINT_SIZE.toString(),
                    new Object[] { MIN_LENGTH, MAX_LENGTH }, null);
        }
        if (!password.matches(UPPER_CASE_CONDITION)) {
            errors.rejectValue(PASSWORD_INPUT_PARAMETER, PasswordError.CONSTRAINT_UPPER_CASE.toString());
        }
        if (!password.matches(LOWER_CASE_CONDITION)) {
            errors.rejectValue(PASSWORD_INPUT_PARAMETER, PasswordError.CONSTRAINT_LOWER_CASE.toString());
        }
        if (!password.matches(NUMBER_CONDITION)) {
            errors.rejectValue(PASSWORD_INPUT_PARAMETER, PasswordError.CONSTRAINT_NUMBER.toString());
        }
        if (password.matches(SEQUENCE_CONDITION)) {
            errors.rejectValue(PASSWORD_INPUT_PARAMETER, PasswordError.CONSTRAINT_SEQUENCE.toString());
        }
    }

    public enum PasswordError {

        CONSTRAINT_SIZE("password.size.constraint"),
        CONSTRAINT_LOWER_CASE("password.lowercase.constraint"),
        CONSTRAINT_UPPER_CASE("password.uppercase.constraint"),
        CONSTRAINT_NUMBER("password.number.constraint"),
        CONSTRAINT_SEQUENCE("password.sequence.constraint");

        private final String errorType;

        PasswordError(final String errorType) {
            this.errorType = errorType;
        }

        @Override
        public String toString() {
            return errorType;
        }
    }
}
