package com.techtest.scrumretroapi.entity.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedbackType {
    POSITIVE("Positive"),
    NEGATIVE("Negative"),
    IDEA("Idea"),
    PRAISE("Praise");

    private final String feedbackType;

    public static FeedbackType fromString(String text) {
        for (FeedbackType type : FeedbackType.values()) {
            if (type.feedbackType.equalsIgnoreCase(text)) {
                return type;
            }
        }

        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
