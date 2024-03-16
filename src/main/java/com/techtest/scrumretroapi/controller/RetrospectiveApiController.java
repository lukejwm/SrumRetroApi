package com.techtest.scrumretroapi.controller;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.service.RetrospectiveService;
import com.techtest.scrumretroapi.utils.LoggingUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: add pagination

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
    private final Log logger = LoggingUtil.getLogger(RetrospectiveApiController.class);

    public RetrospectiveApiController(RetrospectiveService retrospectiveService) {
        this.retrospectiveService = retrospectiveService;
    }

    @Operation(summary = "Get all retrospectives (with pagination)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all available retrospectives from the server",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Retrospective.class))}),
            @ApiResponse(responseCode = "404", description = "No retrospective data found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Failed to fetch retrospectives from server", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<Page<Retrospective>> getAllRetrospectives(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize) {
        logger.info("API invoked: httpMethod=GET, path=/retrospectives/all");
        logger.info("Attempting to get all retrospectives with pagination");

        // Set up pagination
        Pageable pageable = PageRequest.of(page, pageSize);
        Optional<Page<Retrospective>> retrospectives = retrospectiveService.getAllRetrospectives(pageable);

        // check if any data is returned and return the response
        if (retrospectives.isPresent()) {
            logger.info("Successfully found all retrospectives. Returning status OK");
            return ResponseEntity.ok(retrospectives.get());
        } else {
            logger.warn("No retrospectives found. Returning status NOT FOUND");
            return ResponseEntity.notFound().build();
        }
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
        logger.info("API invoked: httpMethod=GET, path=retrospectives/filter?{date}, with query date=" + date.toString());
        Optional<List<Retrospective>> retrospectives = retrospectiveService.getRetrospectivesByDate(date);

        if (retrospectives.isPresent()) {
            logger.info("Successfully found all retrospectives with date: " + date + ". Returning status OK");
            return ResponseEntity.ok(retrospectives.orElse(new ArrayList<>()));
        } else {
            logger.warn("No retrospectives found with date " + date + ". Returning status NOT FOUND");
            return ResponseEntity.notFound().build();
        }
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
        logger.info("API Invoked: httpMethod=POST, path='/retrospective'");
        logger.info("Attempting to create new retrospective with values: " + retrospective);

        try {
            retrospectiveService.createNewRetrospective(retrospective);
            logger.info("Successfully created new retrospective: " + retrospective.getName());
            return ResponseEntity.ok().body("Successfully created new retrospective");
        } catch (IllegalArgumentException exp) {
            logger.warn("Invalid request body received: " + exp.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        } catch (Exception exp) {
            logger.error("Failed to create new retrospective: " + exp.getMessage(), exp);
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
        logger.info(String.format("API Invoked: httpMethod=PUT path='/retrospective/%s/feedback'", retrospectiveName));
        logger.info("Attempting to add new feedback for retrospective with values: " + newFeedbackItem);

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
