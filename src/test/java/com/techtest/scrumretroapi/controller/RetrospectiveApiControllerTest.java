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
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

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
    void testGetAllRetrospectivesSuccess() {
        // Mocking the service method to return a non-empty Optional with some retrospectives
        List<Retrospective> retrospectives = List.of(
            new Retrospective("Retrospective 1", "Post release retrospective", LocalDate.now(), new ArrayList<>(), new ArrayList<>()),
            new Retrospective("Retrospective 1", "Post release retrospective", LocalDate.now(), new ArrayList<>(), new ArrayList<>())
        );

        when(retrospectiveService.getAllRetrospectives()).thenReturn(Optional.of(retrospectives));

        // Call the controller method
        ResponseEntity<List<Retrospective>> result = retrospectiveApiController.getAllRetrospectives();

        // Verifying the result and that the service is called
        assertEquals(OK, result.getStatusCode());
        assertEquals(retrospectives, result.getBody()); // Verifying the returned retrospectives
        verify(retrospectiveService, times(1)).getAllRetrospectives();
    }


    @Test
    void testGetAllRetrospectivesFail() {
        // Mocking the service method to return an empty Optional
        when(retrospectiveService.getAllRetrospectives()).thenReturn(Optional.empty());

        // Call the controller method
        ResponseEntity<List<Retrospective>> result = retrospectiveApiController.getAllRetrospectives();

        // Verifying the result and that the service is called
        assertEquals(NOT_FOUND, result.getStatusCode());
        verify(retrospectiveService, times(1)).getAllRetrospectives();
    }

    @Test
    void testGetAllRetrospectivesByDateSuccess() {
        // Mock the service method
        LocalDate date = LocalDate.now();
        List<Retrospective> retrospectives = Collections.singletonList(new Retrospective());
        when(retrospectiveService.getRetrospectivesByDate(date)).thenReturn(Optional.of(retrospectives));

        // Call the controller method
        ResponseEntity<List<Retrospective>> result = retrospectiveApiController.getAllRetrospectivesByDate(date);

        // Verifying the result and that the service is called
        assertEquals(OK, result.getStatusCode());
        verify(retrospectiveService, times(1)).getRetrospectivesByDate(date);
    }

    @Test
    void testGetAllRetrospectivesByDateFail() {
        // Mock the service method
        LocalDate date = LocalDate.now();
        when(retrospectiveService.getRetrospectivesByDate(date)).thenReturn(Optional.empty());

        // Call the controller method
        ResponseEntity<List<Retrospective>> result = retrospectiveApiController.getAllRetrospectivesByDate(date);

        // Verifying the result and that the service is called
        assertEquals(NOT_FOUND, result.getStatusCode());
        verify(retrospectiveService, times(1)).getRetrospectivesByDate(date);
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
