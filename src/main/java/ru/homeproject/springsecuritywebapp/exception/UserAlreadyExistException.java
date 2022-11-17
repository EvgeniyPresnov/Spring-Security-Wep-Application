package ru.homeproject.springsecuritywebapp.exception;

import lombok.extern.log4j.Log4j2;
import ru.homeproject.springsecuritywebapp.entity.User;


// TODO: the comment for this exception
/**
 * This custom exception.
 *
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String s) {
        super(s);
    }
}