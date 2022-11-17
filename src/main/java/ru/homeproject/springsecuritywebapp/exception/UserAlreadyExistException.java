package ru.homeproject.springsecuritywebapp.exception;


/**
 * This custom exception.
 *
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String s) {
        super(s);
    }
}