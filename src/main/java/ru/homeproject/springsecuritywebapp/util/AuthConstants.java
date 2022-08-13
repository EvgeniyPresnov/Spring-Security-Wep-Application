package ru.homeproject.springsecuritywebapp.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthConstants {
    public static final String START_LOGGING = "--- Logging AuthController is started ---";
    public static final String STOP_LOGGING = "--- Logging AuthController is stopped ---";

    public static final String INVALID_INPUT_DATA = "Invalid username and password";

    public static final String USER_ACCESS_DENIED =
            "The user with username = %s , password = %s, authorities = %s is attempted to access the protected URL = %s";
    public static final String USER_LOG_IN =
            "The user with username = %s , password = %s, authorities = %s is log in";
    public static final String USER_LOG_OUT =
            "The user with username = %s , password = %s, authorities = %s is log out";
}
