package com.techtest.scrumretroapi.entity.utils;

import com.techtest.scrumretroapi.entity.feedback.Feedback;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class FeedbackListConverter implements AttributeConverter<List<Feedback>, Feedback> {
    @Override
    public Feedback convertToDatabaseColumn(List<Feedback> attribute) {
        return null;
    }

    @Override
    public List<Feedback> convertToEntityAttribute(Feedback dbData) {
        return null;
    }
}

/**
 * @Converter
 * public class StringListConverter implements AttributeConverter<List<String>, String> {
 *     private static final String SPLIT_CHAR = ";";
 *
 *     @Override
 *     public String convertToDatabaseColumn(List<String> stringList) {
 *         return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
 *     }
 *
 *     @Override
 *     public List<String> convertToEntityAttribute(String stringData) {
 *         return stringData != null ? Arrays.asList(stringData.split(SPLIT_CHAR)) : emptyList();
 *     }
 * }
 */
