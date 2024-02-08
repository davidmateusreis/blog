package com.david.backend.service;

import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.david.backend.entity.User;
import com.david.backend.exception.UsernameNotExistsException;
import com.david.backend.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) {

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotExistsException("This username not exists"));

                Set<GrantedAuthority> authorities = user.getRole().stream()
                                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toSet());

                return new org.springframework.security.core.userdetails.User(
                                username,
                                user.getPassword(),
                                authorities);
        }
}
