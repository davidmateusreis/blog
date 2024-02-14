package com.david.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.david.backend.entity.Role;
import com.david.backend.entity.User;
import com.david.backend.exception.UsernameAlreadyExistsException;
import com.david.backend.exception.UsernameNotExistsException;
import com.david.backend.repository.RoleRepository;
import com.david.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setName("Admin");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("User");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setName("Admin");
        adminUser.setUsername("admin");
        adminUser.setEmail("admin@yourgamingnews.com");
        adminUser.setPassword(getEncodedPassword(System.getenv("ADMIN_PASSWORD")));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        adminUser.setActive(true);
        userRepository.save(adminUser);

        User user = new User();
        user.setName("David");
        user.setUsername("david");
        user.setEmail("david@yourgamingnews.com");
        user.setPassword(getEncodedPassword(System.getenv("ADMIN_PASSWORD")));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        user.setActive(true);
        userRepository.save(user);
    }

    public User registerNewUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("This username is already taken!");
        }

        Role role = roleRepository.findById("User").get();

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRole(roleSet);
        user.setActive(false);

        String password = getEncodedPassword(user.getPassword());
        user.setPassword(password);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUserStatus(@NonNull String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotExistsException("User not found!"));

        user.setActive(!user.isActive());
        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
