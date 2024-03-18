package com.techtest.scrumretroapi.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackItem {
    @JsonProperty
    private String name;

    @JsonProperty
    private String body;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private FeedbackType feedbackType;
}
