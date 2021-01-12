package com.ratemyfit.ratemyfit.service;
import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;


//https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }



}