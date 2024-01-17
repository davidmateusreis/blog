package com.david.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.backend.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String key1,
            String key2);

    Optional<News> findBySlug(String slug);

    Optional<News> findByGuid(String guid);
}
