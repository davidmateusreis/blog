package com.david.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.backend.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
