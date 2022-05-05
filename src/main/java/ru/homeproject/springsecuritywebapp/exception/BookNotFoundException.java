package ru.homeproject.springsecuritywebapp.exception;

import lombok.extern.log4j.Log4j2;
import ru.homeproject.springsecuritywebapp.entity.Book;

import static ru.homeproject.springsecuritywebapp.util.BookControllerConstants.BOOK_NOT_FIND;
import static ru.homeproject.springsecuritywebapp.util.BookControllerConstants.BOOK_NOT_FIND_BY_ID;

/**
 * This custom exception raises .
 *
 */
@Log4j2
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(int id) {
        log.error(String.format(BOOK_NOT_FIND_BY_ID, id));
    }

    public BookNotFoundException(int id, Book book) {
        log.error(String.format(BOOK_NOT_FIND, book, id));
    }
}
