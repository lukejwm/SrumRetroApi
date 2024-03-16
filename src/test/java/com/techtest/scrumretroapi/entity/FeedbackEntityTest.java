package com.techtest.scrumretroapi.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedbackEntityTest extends EntityBaseTest {
    private final String username = "username123";
    private final String nameOfReporter = "John Smith";
    private final String body = "Great, you fixed the bug!";
    private final FeedbackType feedbackType = FeedbackType.POSITIVE;
    private Feedback feedback;

    @BeforeEach
    void setUp() {
        this.feedback = new Feedback(username, nameOfReporter, body, feedbackType);
    }

    @Test
    void testObjectInitialisationWithConstructor() {
        // Read the data from the object and verify that it matches the input
        assertEquals(username, feedback.getUsername());
        assertEquals(nameOfReporter, feedback.getNameOfReporter());
        assertEquals(body, feedback.getBody());
        assertEquals(feedbackType, feedback.getFeedbackType());
    }

    @Test
    void testObjectIsConvertedToJsonWithJsonPropertyAnnotations() {
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJsonPattern = "\\{\"username\":\".*?\",\"nameOfReporter\":\".*?\",\"body\":\".*?\",\"feedbackType\":\".*?\"\\}";

        testObjectIsConvertedToJsonWithJsonPropertyAnnotations(feedback, objectMapper, expectedJsonPattern);
    }
}
