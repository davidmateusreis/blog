package com.david.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.david.backend.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String key1,
            String key2, Pageable pageable);

    Optional<News> findBySlug(String slug);

    Optional<News> findByGuid(String guid);
}
