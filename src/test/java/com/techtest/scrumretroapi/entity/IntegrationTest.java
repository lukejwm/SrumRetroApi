package com.techtest.scrumretroapi.entity;

import com.techtest.scrumretroapi.entity.feedback.Feedback;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.entity.feedback.FeedbackType;
import com.techtest.scrumretroapi.repository.RetrospectiveRepository;
import com.techtest.scrumretroapi.service.RetrospectiveService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private RetrospectiveService retrospectiveService;

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @Test
    void testRetrospectiveFeedbackData() {
        Retrospective retrospective = new Retrospective();
        retrospective.setName("Retrospective 1");
        retrospective.setSummary("Summary");
        retrospective.setDate(LocalDate.now());
        retrospective.setParticipants(Arrays.asList("Person1", "Person2", "Person3"));

        // create new Feedback ArrayList to add to retrospective
        List<Feedback> feedbackList = Arrays.asList(
            new Feedback(1, new FeedbackItem("Item 1", "Body 1", FeedbackType.POSITIVE), retrospective),
            new Feedback(2, new FeedbackItem("Item 2", "Body 2", FeedbackType.NEGATIVE), retrospective),
            new Feedback(3, new FeedbackItem("Item 3", "Body 3", FeedbackType.IDEA), retrospective)
        );

        retrospective.setFeedback(feedbackList);
        List<Retrospective> listOfRetro = Arrays.asList(retrospective);

        // Stub repository method to return the mocked retrospective
        when(retrospectiveRepository.findRetrospectivesByDate(LocalDate.now())).thenReturn(listOfRetro);

        // Call service method to get retrospective feedback
        List<Retrospective> retrievedRetrospective = retrospectiveService.getRetrospectivesByDate(LocalDate.now());

        // Assertions
        assertNotNull(retrievedRetrospective);
        //assertEquals(retrospective.getName(), retrievedRetrospective.getName());
        //assertEquals(retrospective.getFeedback().size(), retrievedRetrospective.getFeedback().size());

    }
}
