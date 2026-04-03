package com.catmanscodes.springboot_security.controller;

import com.catmanscodes.springboot_security.model.User;
import com.catmanscodes.springboot_security.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final MyUserService myUserService;

    @Autowired
    public UserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        return ResponseEntity.ok(myUserService.registerUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(myUserService.getAllUsers());
    }

}
