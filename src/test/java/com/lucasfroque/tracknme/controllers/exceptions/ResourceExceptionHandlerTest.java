package com.lucasfroque.tracknme.controllers.exceptions;

import com.lucasfroque.tracknme.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler ecxeptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void resourceNotFoundTest() {
        ResponseEntity<StandardError> response = ecxeptionHandler
                .resourceNotFound(
                        new ResourceNotFoundException("Resource not found"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Resource not found", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/funcionarios/1", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }
}