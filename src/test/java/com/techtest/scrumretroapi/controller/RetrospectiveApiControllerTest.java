package com.techtest.scrumretroapi.controller;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.service.RetrospectiveService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RetrospectiveApiControllerTest {
    private AutoCloseable closeable;

    @Mock
    private RetrospectiveService retrospectiveService;

    @InjectMocks
    private RetrospectiveApiController retrospectiveApiController;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetAllRetrospectives() {
        // Mocking the service method
        List<Retrospective> retrospectives = new ArrayList<>();
        when(retrospectiveService.getAllRetrospectives()).thenReturn(Optional.of(retrospectives));

        // Call the controller method
        Optional<List<Retrospective>> result = retrospectiveApiController.getAllRetrospectives();

        // Verifying the result and that the service is called
        assertEquals(retrospectives, result.orElse(null));
        verify(retrospectiveService, times(1)).getAllRetrospectives();
    }

    @Test
    public void testGetAllRetrospectivesByDate() {
        // Mock the service method
        LocalDate date = LocalDate.now();
        List<Retrospective> retrospectives = Collections.singletonList(new Retrospective());
        when(retrospectiveService.getRetrospectivesByDate(date)).thenReturn(Optional.of(retrospectives));

        // Call the controller method
        Optional<List<Retrospective>> result = retrospectiveApiController.getAllRetrospectivesByDate(date);

        // Verify the service method was called
        verify(retrospectiveService, times(1)).getRetrospectivesByDate(date);

        // Verify the result
        assertTrue(result.isPresent());
    }

    @Test
    public void testCreateNewRetrospective() {
        // Mock the service method
        Retrospective retrospective = new Retrospective();
        doNothing().when(retrospectiveService).createNewRetrospective(retrospective);

        // Call the controller method
        ResponseEntity<Void> result = retrospectiveApiController.createNewRetrospective(retrospective);

        // Verify the service method was called
        verify(retrospectiveService, times(1)).createNewRetrospective(retrospective);

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testAddFeedbackToRetrospective() {
        // Mock the service method
        FeedbackItem feedbackItem = new FeedbackItem();
        doNothing().when(retrospectiveService).createNewFeedbackForRetrospective(anyString(), eq(feedbackItem));

        // Call the controller method
        ResponseEntity<Void> result = retrospectiveApiController.addFeedbackToRetrospective("name", feedbackItem);

        // Verify the service method was called
        verify(retrospectiveService, times(1)).createNewFeedbackForRetrospective("name", feedbackItem);

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testUpdateFeedbackItem() {
        // Mock the service method
        FeedbackItem feedbackItem = new FeedbackItem();
        doNothing().when(retrospectiveService).updateFeedbackForRetrospective(anyString(), anyInt(), eq(feedbackItem));

        // Call the controller method
        ResponseEntity<Void> result = retrospectiveApiController.updateFeedbackItem("name", 1, feedbackItem);

        // Verify the service method was called
        verify(retrospectiveService, times(1)).updateFeedbackForRetrospective("name", 1, feedbackItem);

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
