package com.david.backend.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    private String messageAuthor;
    @Email
    @NotBlank(message = "This field may not be blank")
    private String messageEmail;
    @NotBlank(message = "This field may not be blank")
    private String messageContent;
    @CreationTimestamp
    private Instant messageCreatedAt;
}
