package com.charlesxvr.springsecurity6.controllers;

import com.charlesxvr.springsecurity6.dto.AuthenticationRequest;
import com.charlesxvr.springsecurity6.dto.AuthenticationResponse;
import com.charlesxvr.springsecurity6.models.User;
import com.charlesxvr.springsecurity6.services.imp.AuthenticationServiceImp;
import com.charlesxvr.springsecurity6.services.imp.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private AuthenticationServiceImp authenticationServiceImp;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) {
        System.out.println("Diezpeso");

        AuthenticationResponse jwtDto = authenticationServiceImp.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }



    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userServiceImp.getUsers();
        if (users != null && !users.isEmpty()) {
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.notFound().build();
    }/*
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userServiceImp.newUser(user)
        );
    }*/
}
