package ru.homeproject.springsecuritywebapp.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.homeproject.springsecuritywebapp.entity.Book;

/**
 * The custom validator to validate for multiple fields on a form.
 *
 */
@Component
public class BookValidator implements Validator {

    private static final String AUTHOR_INPUT_PARAMETER = "author";
    private static final String TITLE_INPUT_PARAMETER = "title";
    private static final String PRICE_INPUT_PARAMETER = "price";

    private static final String AUTHOR_ERROR_MESSAGE = "book.author.empty";
    private static final String TITLE_ERROR_MESSAGE = "book.title.empty";
    private static final String PRICE_ERROR_MESSAGE = "book.price.empty";

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, AUTHOR_INPUT_PARAMETER, AUTHOR_ERROR_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, TITLE_INPUT_PARAMETER, TITLE_ERROR_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PRICE_INPUT_PARAMETER, PRICE_ERROR_MESSAGE);
    }
}
