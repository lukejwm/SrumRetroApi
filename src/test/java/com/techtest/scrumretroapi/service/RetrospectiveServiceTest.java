package com.techtest.scrumretroapi.service;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.repository.RetrospectiveRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

public class RetrospectiveServiceTest {
    private AutoCloseable closeable;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @InjectMocks
    private RetrospectiveService retrospectiveService;

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
        // Mock the repository method
        List<Retrospective> retrospectives = new ArrayList<>();
        when(retrospectiveRepository.getAllRetrospectives()).thenReturn(Optional.of(retrospectives));

        // Call the service method
        Optional<List<Retrospective>> result = retrospectiveService.getAllRetrospectives();

        // Verify the repository method was called
        verify(retrospectiveRepository, times(1)).getAllRetrospectives();

        // Verify the result
        assert result.isPresent();
        assertSame(retrospectives, result.get());
    }

    @Test
    void testGetRetrospectivesByDate() {
        // Mock the repository method
        LocalDate date = LocalDate.now();
        List<Retrospective> retrospectives = new ArrayList<>();
        when(retrospectiveRepository.getRetrospectivesByDate(date)).thenReturn(Optional.of(retrospectives));

        // Call the service method
        Optional<List<Retrospective>> result = retrospectiveService.getRetrospectivesByDate(date);

        // Verify the repository method was called
        verify(retrospectiveRepository, times(1)).getRetrospectivesByDate(date);

        // Verify the result
        assert result.isPresent();
        assertSame(retrospectives, result.get());
    }

    @Test
    void testCreateNewRetrospective() {
        // Mock the repository method
        Retrospective retrospective = new Retrospective();
        doNothing().when(retrospectiveRepository).createNewRetrospective(retrospective);

        // Call the service method
        retrospectiveService.createNewRetrospective(retrospective);

        // Verify the repository method was called
        verify(retrospectiveRepository, times(1)).createNewRetrospective(retrospective);
    }

    @Test
    void testCreateNewFeedbackForRetrospective() {
        // Mock the repository method
        FeedbackItem feedbackItem = new FeedbackItem();
        doNothing().when(retrospectiveRepository).createNewFeedbackForRetrospective(anyString(), eq(feedbackItem));

        // Call the service method
        retrospectiveService.createNewFeedbackForRetrospective("retrospectiveName", feedbackItem);

        // Verify the repository method was called
        verify(retrospectiveRepository, times(1)).createNewFeedbackForRetrospective("retrospectiveName", feedbackItem);
    }

    @Test
    void testUpdateFeedbackForRetrospective() {
        // Mock the repository method
        FeedbackItem feedbackItem = new FeedbackItem();
        doNothing().when(retrospectiveRepository).updateFeedbackForRetrospective(anyString(), anyInt(), eq(feedbackItem));

        // Call the service method
        retrospectiveService.updateFeedbackForRetrospective("retrospectiveName", 1, feedbackItem);

        // Verify the repository method was called
        verify(retrospectiveRepository, times(1)).updateFeedbackForRetrospective("retrospectiveName", 1, feedbackItem);
    }
}
