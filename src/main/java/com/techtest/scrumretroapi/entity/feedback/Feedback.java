package com.techtest.scrumretroapi.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.scrumretroapi.entity.Retrospective;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback implements Serializable {
    @JsonProperty
    @Column(name = "item_id")
    private int item;

    @JsonProperty
    @Embedded
    private FeedbackItem itemBody;
}
