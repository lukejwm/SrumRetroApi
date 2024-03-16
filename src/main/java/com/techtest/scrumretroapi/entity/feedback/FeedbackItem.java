package com.techtest.scrumretroapi.entity.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackItem {
    @Id
    private String name;

    @JsonProperty
    private String body;

    @JsonProperty
    private FeedbackType feedbackType;
}
