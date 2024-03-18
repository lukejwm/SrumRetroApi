package com.techtest.scrumretroapi.entity.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.scrumretroapi.entity.Retrospective;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Transactional
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int item;

    @JsonProperty
    @OneToOne(mappedBy = "feedback", cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_feedback_item_id")
    private FeedbackItem itemBody;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private Retrospective retrospective;
}
