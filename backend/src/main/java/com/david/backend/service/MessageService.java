package com.david.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.david.backend.entity.Message;
import com.david.backend.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        try {
            return messageRepository.save(message);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving message to the database");
        }
    }
}
