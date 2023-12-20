package com.david.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.backend.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
