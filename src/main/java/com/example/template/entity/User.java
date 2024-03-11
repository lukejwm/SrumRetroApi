package com.example.template.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private long id;

    @NotBlank(message = "First name is mandatory")
    @JsonProperty
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @JsonProperty
    private String lastName;

    @NotBlank(message = "Username is mandatory")
    @JsonProperty
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    @JsonProperty
    private String email;

    @Override
    public String toString() {
        return String.format("User(id=%d, firstName=%s, lastName=%s, username=%s, email=%s)", id, firstName, lastName, username, email);
    }
}
