package com.techtest.scrumretroapi.entity.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.utils.FeedbackItemConverter;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item;

    @JsonProperty
    @Convert(converter = FeedbackItemConverter.class)
    private FeedbackItem itemBody;

    @ManyToOne
    @JoinColumn(name = "name")
    private Retrospective retrospective;
}
