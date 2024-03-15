package com.david.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        @NotBlank(message = "This field may not be blank")
        @Size(min = 4, message = "Username must be at least 4 characters")
        private String username;
        @Column(nullable = false)
        @NotBlank(message = "This field may not be blank")
        @Size(min = 4, message = "Name must be at least 4 characters")
        private String name;
        @Column(nullable = false, unique = true)
        @NotBlank(message = "This field may not be blank")
        @Email(message = "Invalid email format")
        private String email;
        @Column(nullable = false)
        @NotBlank(message = "This field may not be blank")
        @Size(min = 8, message = "Password must be at least 8 characters")
        private String password;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(name = "users_roles", joinColumns = {
                        @JoinColumn(name = "user_id")
        }, inverseJoinColumns = {
                        @JoinColumn(name = "role_id")
        })
        private Set<Role> role;
        @Column(nullable = false)
        private boolean active;
}
