package com.techtest.scrumretroapi.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Transactional
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_item_id")
    @JsonIgnore
    private Long id;

    @JsonProperty
    @Column(name = "participant_name", length = 50, unique = true)
    private String name;

    @JsonProperty
    @Column(name = "feedback_text", length = 300)
    private String body;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private FeedbackType feedbackType;

    @OneToOne
    @JoinColumn(name = "fk_feedback_item_id")
    @JsonIgnore // Prevent circular reference
    private Feedback feedback;
}
