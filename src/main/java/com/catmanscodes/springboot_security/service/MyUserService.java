package com.catmanscodes.springboot_security.service;

import com.catmanscodes.springboot_security.model.User;
import com.catmanscodes.springboot_security.model.UserPrincipal;
import com.catmanscodes.springboot_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new UserPrincipal(user);
    }

    public User registerUser(User user) {

    //encrypt the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
