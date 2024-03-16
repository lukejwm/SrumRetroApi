package com.techtest.scrumretroapi.entity.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.scrumretroapi.entity.utils.EnumConverter;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackItem {
    @Id
    private String name;

    @JsonProperty
    private String body;

    @JsonProperty
    @Convert(converter = EnumConverter.class)
    private FeedbackType feedbackType;
}
