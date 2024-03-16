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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        // Mock the repository method to return a page of retrospectives
        List<Retrospective> retrospectives = new ArrayList<>();
        Page<Retrospective> page = new PageImpl<>(retrospectives);
        when(retrospectiveRepository.getAllRetrospectives(any(Pageable.class))).thenReturn(Optional.of(page));

        // Call the service method
        Optional<Page<Retrospective>> result = retrospectiveService.getAllRetrospectives(Pageable.unpaged());

        // Verify the repository method was called with the correct Pageable parameter
        verify(retrospectiveRepository, times(1)).getAllRetrospectives(any(Pageable.class));

        // Verify the result
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(retrospectives, result.get().getContent());
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
        try {
            retrospectiveService.createNewRetrospective(retrospective);
        } catch (Exception exp) {
            // if exception is thrown, then fail the test
            System.err.println("Exception thrown: " + exp.getMessage());
            fail();
        }

        // Verify the repository method was called
        verify(retrospectiveRepository, times(1)).createNewRetrospective(retrospective);
    }

    // TODO: add test to check that exception does get thrown!

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
