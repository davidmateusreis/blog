package com.david.backend.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.david.backend.entity.Role;
import com.david.backend.entity.User;
import com.david.backend.entity.VerificationToken;
import com.david.backend.exception.RoleNotFoundException;
import com.david.backend.exception.UsernameAlreadyExistsException;
import com.david.backend.exception.UsernameNotExistsException;
import com.david.backend.repository.RoleRepository;
import com.david.backend.repository.UserRepository;
import com.david.backend.repository.VerificationTokenRepository;

@Service
public class UserService {

    @Value("${app.frontend.base-url}")
    private String baseUrl;

    @NonNull
    private static final Long DEFAULT_ROLE = 2L;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

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

    public void sendVerificationEmail(User user, String token) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("${spring.mail.username}");
        message.setTo(user.getEmail());
        message.setSubject("Action Required: Verify Your Email Address");
        message.setText(
                "Please, verify your email address by clicking this link: " + baseUrl + "/activate?token="
                        + token);
        javaMailSender.send(message);
    }

    public String generateVerificationToken(User user) {

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public boolean activateAccount(String token) {

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken != null) {
            User user = verificationToken.getUser();
            user.setActive(true);
            userRepository.save(user);
            verificationTokenRepository.delete(verificationToken);
            return true;
        }
        return false;
    }
}
