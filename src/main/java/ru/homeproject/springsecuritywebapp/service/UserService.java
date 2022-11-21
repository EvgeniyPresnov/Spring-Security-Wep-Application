package ru.homeproject.springsecuritywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.homeproject.springsecuritywebapp.entity.User;
import ru.homeproject.springsecuritywebapp.exception.UserAlreadyExistException;
import ru.homeproject.springsecuritywebapp.repository.UserRepository;

import java.util.List;

/**
 * This class provides the business logic about users.
 *
 */
@Service
public class UserService {

    private static final String USER_ALREADY_EXIST_ERROR = "User with name ' %s  ' already exists in database";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * The method saves a new user in data base
     *
     * @param user
     */
    public void createUser(User user) {
        if (isUserExist(user)) {
            throw new UserAlreadyExistException(String.format(USER_ALREADY_EXIST_ERROR, user.getUserName()));
        }
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(user.getRoles());
        userRepository.save(newUser);
    }

    /**
     * The method deletes the user by id
     *
     * @param id
     */
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * The method checks existing the user's username in data base
     *
     * @param user
     * @return
     * @throws UserAlreadyExistException if user doesn't exist in data base
     */
    private boolean isUserExist(User user) throws UserAlreadyExistException {
        List<User> users = getUsers();
        for (User i: users) {
            if (i.getUserName().equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }
}
