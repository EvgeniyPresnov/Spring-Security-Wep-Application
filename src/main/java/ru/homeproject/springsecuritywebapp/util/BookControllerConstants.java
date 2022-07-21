package ru.homeproject.springsecuritywebapp.util;

import lombok.experimental.UtilityClass;

/**
 * This class defines the util for BookController class.
 *
 */

@UtilityClass
public final class BookControllerConstants {
    public static final String START_LOGGING = "--- Logging BookController is started ---";
    public static final String STOP_LOGGING = "--- Logging BookController is stopped ---";

    public static final String INVALID_INPUT_DATA = "Input data is invalid on a form";

    public static final String LIST_BOOKS = "The list of books: %s";
    public static final String GET_BOOK = "The book: %s by id is got";
    public static final String ADD_NEW_NOOK = "A new book: %s is added";
    public static final String EDIT_BOOK = "The book: %s by id %s is edited";
    public static final String DELETE_BOOK = "The book: %s will be deleted";

    public static final String BOOK_NOT_FIND_BY_ID = "The book doesn't exist by id: %s";
    public static final String BOOK_NOT_FIND = "The book: %s  doesn't exist by id = %s.";
}
