package com.david.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.backend.entity.User;
import com.david.backend.exception.ErrorResponse;
import com.david.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = { "${app.cors.allowed-origins}" })
    @PostMapping("/users/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorResponse> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> new ErrorResponse(error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        User registeredUser = userService.registerNewUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    @CrossOrigin(origins = { "${app.cors.allowed-origins}" })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin(origins = { "${app.cors.allowed-origins}" })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/users/me")
    public ResponseEntity<User> getCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @CrossOrigin(origins = { "${app.cors.allowed-origins}" })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/status")
    public ResponseEntity<User> updateUserStatus(@PathVariable @NonNull Long id) {
        User updatedUser = userService.updateUserStatus(id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
