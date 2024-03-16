package com.techtest.scrumretroapi.controller;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.service.RetrospectiveService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// TODO: include logging and pagination

@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "Scrum Retrospective API",
                description = "This template API project provides basic endpoints for reading, creating, editing and deleting user data."
        )
)
@RequestMapping("/retrospective")
@Tag(name = "Scrum Retrospective API")
public class RetrospectiveApiController {
    private final RetrospectiveService retrospectiveService;

    public RetrospectiveApiController(RetrospectiveService retrospectiveService) {
        this.retrospectiveService = retrospectiveService;
    }

    @Operation(summary = "Get all retrospectives")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all available retrospectives from the server",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Retrospective.class))}),
            @ApiResponse(responseCode = "404", description = "No retrospective data found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to fetch retrospectives from server", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<Retrospective>> getAllRetrospectives() {
        Optional<List<Retrospective>> retrospectives = retrospectiveService.getAllRetrospectives();
        return retrospectives.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all retrospectives by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all available retrospectives for given date from the server",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Retrospective.class))}),
            @ApiResponse(responseCode = "404", description = "No retrospective data found for given date", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to fetch retrospectives from server", content = @Content)
    })
    @GetMapping("/filter")
    public ResponseEntity<List<Retrospective>> getAllRetrospectivesByDate(@Parameter(description = "Date to filter retrospectives by")
                                                                    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Optional<List<Retrospective>> retrospectives = retrospectiveService.getRetrospectivesByDate(date);
        return retrospectives.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new retrospective",
            description = "Endpoint to create a new retrospective to be updated in the server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New retrospective successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to create new retrospective in server", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<String> createNewRetrospective(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New retrospective to be created.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Retrospective.class)
                    ))
            @RequestBody final Retrospective retrospective) {

        // TODO: add appropriate logging!
        try {
            retrospectiveService.createNewRetrospective(retrospective);
            return ResponseEntity.ok().body("Successfully created new retrospective");
        } catch (IllegalArgumentException exp) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        } catch (Exception exp) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to create new item. Process failed with exception: " + exp.getMessage());
        }
    }

    @Operation(summary = "Create feedback for existing retrospective")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New feedback successfully added to retrospective", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Retrospective not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to create new feedback for retrospective in server", content = @Content)
    })
    @PutMapping("/{retrospectiveName}/feedback")
    public ResponseEntity<Void> addFeedbackToRetrospective(
            @PathVariable String retrospectiveName,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New feedback to be created for retrospective.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = FeedbackItem.class)
                    ))
            @RequestBody FeedbackItem newFeedbackItem) {
        try {
            retrospectiveService.createNewFeedbackForRetrospective(retrospectiveName, newFeedbackItem);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException exp) {
            return ResponseEntity.notFound().build();
        } catch (Exception exp) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Update feedback for retrospective")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feedback item successfully updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "Feedback item not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to update feedback in server", content = @Content)
    })
    @PutMapping("{retrospectiveName}/feedback/{itemId}")
    public ResponseEntity<Void> updateFeedbackItem(
            @PathVariable String retrospectiveName,
            @PathVariable int itemId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Retrospective feedback to be edited",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = FeedbackItem.class)
                    ))
            @RequestBody FeedbackItem feedbackItem) {
        try {
            retrospectiveService.updateFeedbackForRetrospective(retrospectiveName, itemId, feedbackItem);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException exp) {
            return ResponseEntity.notFound().build();
        } catch (Exception exp) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
