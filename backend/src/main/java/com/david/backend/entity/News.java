package com.david.backend.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class News {

    @Id
    private Long id;
    private String author;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String link;
    private String imageUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubDate;
}