package com.david.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.backend.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    public List<News> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String key1,
            String key2);
}
