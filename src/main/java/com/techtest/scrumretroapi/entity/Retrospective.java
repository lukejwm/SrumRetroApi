package com.techtest.scrumretroapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtest.scrumretroapi.entity.feedback.Feedback;
import com.techtest.scrumretroapi.entity.utils.StringListConverter;
import jakarta.persistence.*;
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
    private String name;

    @JsonProperty
    private String summary;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty
    @Convert(converter = StringListConverter.class)
    private List<String> participants;

    @JsonProperty
    @OneToMany(mappedBy = "retrospective", cascade = CascadeType.ALL)
    private List<Feedback> feedback;
}
