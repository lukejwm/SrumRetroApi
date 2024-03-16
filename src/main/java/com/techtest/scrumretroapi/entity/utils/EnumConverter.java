package com.techtest.scrumretroapi.entity.utils;

import com.techtest.scrumretroapi.entity.feedback.FeedbackType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Purpose to convert custom enum object to a string and back to enum object
 */

@Converter
public class EnumConverter implements AttributeConverter<FeedbackType, String> {
    @Override
    public String convertToDatabaseColumn(FeedbackType feedbackType) {
        if (feedbackType == null) {
            return null;
        }

        return feedbackType.toString();
    }

    @Override
    public FeedbackType convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return FeedbackType.fromString(value);
    }
}
