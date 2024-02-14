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
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @NotBlank(message = "This field may not be blank")
    @Size(min = 4, message = "Name must be at least 4 characters")
    private String messageAuthor;
    @Email(message = "Invalid email format")
    @NotBlank(message = "This field may not be blank")
    private String messageEmail;
    @NotBlank(message = "This field may not be blank")
    @Size(min = 4, message = "Message must be at least 4 characters")
    private String messageContent;
    @CreationTimestamp
    private Instant messageCreatedAt;
}
