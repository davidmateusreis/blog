package com.david.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.backend.entity.Message;
import com.david.backend.service.MessageService;

@RestController
@RequestMapping("/api/contact")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @CrossOrigin(origins = { "http://localhost:4200" })
    @PostMapping
    public ResponseEntity<Message> send(@RequestBody Message message) {
        try {
            Message sentMessage = messageService.sendMessage(message);
            return new ResponseEntity<>(sentMessage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
