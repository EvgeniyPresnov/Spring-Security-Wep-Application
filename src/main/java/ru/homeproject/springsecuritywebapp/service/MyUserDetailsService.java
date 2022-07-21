//package ru.homeproject.springsecuritywebapp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.homeproject.springsecuritywebapp.entity.User;
//import ru.homeproject.springsecuritywebapp.model.MyUserDetails;
//import ru.homeproject.springsecuritywebapp.repository.UserRepository;
//
//import java.util.Optional;
//
//@Service
//public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUserName(userName);
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
//
//        return user.map(MyUserDetails::new).get();
//    }
//}
