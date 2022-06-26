package com.david.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.blog.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
