package com.david.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.backend.entity.User;
import com.david.backend.exception.ErrorResponse;
import com.david.backend.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUser();
    }

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorResponse> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        User registeredUser = userService.registerNewUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }
}
