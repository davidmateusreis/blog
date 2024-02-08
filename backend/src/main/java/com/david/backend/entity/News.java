package com.david.backend.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String link;
    private String imageUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubDate;
    private String slug;
    @Column(nullable = false, unique = true)
    private String guid;
}