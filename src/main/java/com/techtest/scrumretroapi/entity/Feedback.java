package com.techtest.scrumretroapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    // It is possible two people in an organization have the same name, so username can act as a unique identifier
    @Id
    private String username;

    @JsonProperty
    private String nameOfReporter;

    @JsonProperty
    private String body;

    @JsonProperty
    private FeedbackType feedbackType;
}
