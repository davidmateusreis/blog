package com.david.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false)
    @NotBlank(message = "This field may not be blank")
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "This field may not be blank")
    private String name;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "This field may not be blank")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "This field may not be blank")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = {
            @JoinColumn(name = "USER_ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
    })
    private Set<Role> role;
}
