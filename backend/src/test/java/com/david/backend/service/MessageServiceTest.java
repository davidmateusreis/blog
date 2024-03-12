package com.david.backend.service;

import com.david.backend.entity.Message;
import com.david.backend.exception.ContactMessageException;
import com.david.backend.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMessage_Success() {
        // Arrange
        Message message = new Message(1L, "David", "david@yourgamingnews.com", "This is a test message",
                Instant.now());

        when(messageRepository.save(message)).thenReturn(message);

        // Act
        Message result = messageService.sendMessage(message);

        // Assert
        assertNotNull(result);
        assertEquals("David", result.getAuthor());
        assertEquals("david@yourgamingnews.com", result.getEmail());
        assertEquals("This is a test message", result.getContent());
        assertNotNull(result.getCreatedAt());

        // Verify that the dependency was called
        verify(messageRepository, times(1)).save(message);
        // Ensure that other dependencies were not called
        verifyNoMoreInteractions(messageRepository);
    }

    @Test
    public void testSendMessage_Failure() {
        // Arrange
        Message message = new Message(1L, "David", "david@yourgamingnews.com", "This is a test message",
                Instant.now());

        when(messageRepository.save(message)).thenThrow(new ContactMessageException("Simulated database error"));

        // Act and Assert
        assertThrows(ContactMessageException.class, () -> messageService.sendMessage(message));

        // Verify that the dependency was called
        verify(messageRepository, times(1)).save(message);
        // Ensure that other dependencies were not called
        verifyNoMoreInteractions(messageRepository);
    }
}
