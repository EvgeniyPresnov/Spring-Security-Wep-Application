//package ru.homeproject.springsecuritywebapp.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import ru.homeproject.springsecuritywebapp.entity.User;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class MyUserDetails implements UserDetails {
//
//    private String userName;
//    private String password;
//    private boolean active;
//    private List<GrantedAuthority> authorities;
//
//    public MyUserDetails() {}
//
//    public MyUserDetails(User user) {
//        this.userName = user.getUserName();
//        this.password = user.getPassword();
//        this.active = user.isActive();
//        this.authorities = Arrays.stream(user.getRoles())
//                            .map(SimpleGrantedAuthority::new)
//                            .collect(Collectors.toList())
//                            ;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return "bla";
//    }
//
//    @Override
//    public String getUsername() {
//        return "bla";
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return active;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
