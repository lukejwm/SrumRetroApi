package com.techtest.scrumretroapi.controller;

import com.techtest.scrumretroapi.entity.Retrospective;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "Scrum Retrospective API",
                description = "This template API project provides basic endpoints for reading, creating, editing and deleting user data."
        )
)
@RequestMapping("/retrospective")
@Tag(name = "Scrum Retrospective API")
public class RetrospectiveController {
    @Operation(summary = "Get all retrospectives")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all available retrospectives from the server",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Retrospective.class))}),
            @ApiResponse(responseCode = "404", description = "No retrospective data found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to fetch retrospectives from server", content = @Content)
    })
    @GetMapping("/all")
    public Optional<List<Retrospective>> getAllRetrospectives() {
        // TODO: implement logic in service and add pagination
        return Optional.empty();
    }

    @Operation(summary = "Get all retrospectives by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all available retrospectives for given date from the server",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Retrospective.class))}),
            @ApiResponse(responseCode = "404", description = "No retrospective data found for given date", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to fetch retrospectives from server", content = @Content)
    })
    @GetMapping("/by-date/{date}")
    public Optional<List<Retrospective>> getAllRetrospectivesByDate(@Parameter(description = "Date to filter retrospectives")
                                                           @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        // TODO: implement logic in service and add pagination
        return Optional.empty();
    }

    @Operation(summary = "Create a new retrospective",
            description = "Endpoint to create a new retrospective to be updated in the server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New retrospective successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to create new retrospective in server", content = @Content)
    })
    @PostMapping("/")
    public void createNewRetrospective(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New retrospective to be created.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Retrospective.class)
                    ))
            @RequestBody final Retrospective retrospective) {
        // TODO: implement logic in service
    }

    @Operation(summary = "Create feedback for existing retrospective")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New feedback successfully added to retrospective", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Retrospective not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to create new feedback for retrospective in server", content = @Content)
    })
    @PutMapping("/{name}")
    public void addFeedbackToRetrospective(@PathVariable String name) {
        // TODO: implement logic in service
    }

    @Operation(summary = "Update feedback for retrospective")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feedback item successfully updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Feedback item not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to update feedback in server", content = @Content)
    })
    @PutMapping("/feedback/{itemName}")
    public void updateFeedbackItem(@PathVariable String itemName) {
    }
}
