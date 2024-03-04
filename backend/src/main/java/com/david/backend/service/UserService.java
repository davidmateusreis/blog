package com.david.backend.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.david.backend.entity.Role;
import com.david.backend.entity.User;
import com.david.backend.exception.RoleNotFoundException;
import com.david.backend.exception.UsernameAlreadyExistsException;
import com.david.backend.exception.UsernameNotExistsException;
import com.david.backend.repository.RoleRepository;
import com.david.backend.repository.UserRepository;

@Service
public class UserService {

    @NonNull
    private static final Long DEFAULT_ROLE = 2L;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerNewUser(User user) {

        validateUsernameNotExists(user.getUsername());

        Role userRole = roleRepository.findById(DEFAULT_ROLE)
                .orElseThrow(() -> new RoleNotFoundException("Default role not found!"));

        user.setRole(Set.of(userRole));
        user.setActive(false);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }

    private void validateUsernameNotExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("This username is already taken!");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotExistsException("Current user not found!"));
    }

    public User updateUserStatus(@NonNull Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotExistsException("User not found!"));

        user.setActive(!user.isActive());
        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
