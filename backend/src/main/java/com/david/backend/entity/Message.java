package com.david.backend.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "This field may not be blank")
    @Size(min = 4, message = "Name must be at least 4 characters")
    private String author;
    @Email(message = "Invalid email format")
    @NotBlank(message = "This field may not be blank")
    private String email;
    @NotBlank(message = "This field may not be blank")
    @Size(min = 4, message = "Message must be at least 4 characters")
    private String content;
    @CreationTimestamp
    private Instant createdAt;
}
