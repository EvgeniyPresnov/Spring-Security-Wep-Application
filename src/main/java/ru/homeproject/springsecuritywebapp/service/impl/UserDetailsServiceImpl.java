package ru.homeproject.springsecuritywebapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homeproject.springsecuritywebapp.entity.User;
import ru.homeproject.springsecuritywebapp.model.MyUserDetails;
import ru.homeproject.springsecuritywebapp.repository.UserRepository;

import java.util.Optional;

/**
 * This class is used to retrieve the user's authentication information.
 *
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * The method returns the user details in the UserDetails object
     *
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException if user doesn't find by username in data base
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' doesn't find", username));
        }

        return user.map(MyUserDetails::new).get();
    }
}
