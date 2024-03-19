package com.techtest.scrumretroapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.scrumretroapi.entity.feedback.Feedback;
import com.techtest.scrumretroapi.entity.utils.StringListConverter;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Retrospective {
    @Id
    @JsonProperty
    @Column(name = "retrospective_name", length = 100, nullable = false, unique = true)
    private String name;

    @JsonProperty
    @Column(name = "summary", length = 300)
    private String summary;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @JsonProperty
    @Convert(converter = StringListConverter.class)
    private List<String> participants;

    @JsonProperty
    @ElementCollection(targetClass = Feedback.class)
    private List<Feedback> feedback;
}
