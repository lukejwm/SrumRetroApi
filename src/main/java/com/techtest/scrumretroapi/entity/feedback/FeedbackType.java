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
}
