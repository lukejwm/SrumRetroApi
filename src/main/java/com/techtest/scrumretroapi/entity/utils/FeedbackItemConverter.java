package com.techtest.scrumretroapi.entity.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Purpose of class is to convert a feedback item into a JSON string
 */

@Converter
public class FeedbackItemConverter implements AttributeConverter<FeedbackItem, String> {
    @Override
    public String convertToDatabaseColumn(FeedbackItem feedbackItem) {
        if (feedbackItem == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(feedbackItem);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting FeedbackItem to JSON", e);
        }
    }

    @Override
    public FeedbackItem convertToEntityAttribute(String json) {
        if (json == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, FeedbackItem.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to FeedbackItem", e);
        }
    }
}
