package com.david.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.blog.models.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    
}
