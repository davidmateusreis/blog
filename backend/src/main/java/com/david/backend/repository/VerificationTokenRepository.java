package com.david.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.backend.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
}
