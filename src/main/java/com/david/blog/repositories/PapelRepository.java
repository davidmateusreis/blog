package com.david.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.blog.models.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
    
    Papel findByPapel(String papel);
}
