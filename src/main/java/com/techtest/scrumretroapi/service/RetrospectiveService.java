package com.techtest.scrumretroapi.service;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.repository.RetrospectiveRepository;
import com.techtest.scrumretroapi.utils.LoggingUtil;
import org.apache.commons.logging.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetrospectiveService {
    private final RetrospectiveRepository retrospectiveRepository;
    private final Log logger = LoggingUtil.getLogger(RetrospectiveService.class);

    public RetrospectiveService(RetrospectiveRepository retrospectiveRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
    }

    public Optional<Page<Retrospective>> getAllRetrospectives(Pageable pageable) {
        logger.debug("Attempting to retrieve all retrospectives with pagination");
        Optional<Page<Retrospective>> result = retrospectiveRepository.getAllRetrospectives(pageable);
        result.ifPresent(page -> logger.info("Retrieved " + page.getNumberOfElements() + " retrospectives"));
        return result;
    }

    public Optional<List<Retrospective>> getRetrospectivesByDate(LocalDate date) {
        logger.info("Attempting to retrieve retrospectives for date: " + date);
        return retrospectiveRepository.getRetrospectivesByDate(date);
    }

    public void createNewRetrospective(Retrospective retrospective) throws Exception {
        String nameToCheck = retrospective.getName();
        logger.debug(String.format("Attempting to add new retrospective with name='%s'", nameToCheck));

        // Check if the name already exists in the repository
        boolean nameAlreadyExists = retrospectiveRepository.existsByName(nameToCheck);

        if (nameAlreadyExists) {
            String errorMessage = String.format("The retrospective name '%s' has already been added!", nameToCheck);
            logger.error(errorMessage);
            throw new Exception(errorMessage);
        } else {
            logger.info("Creating a new retrospective with name: " + nameToCheck);
            retrospectiveRepository.createNewRetrospective(retrospective);
            logger.info("New retrospective created successfully");
        }
    }

    public void createNewFeedbackForRetrospective(String retrospectiveName, FeedbackItem newFeedbackItem) {
        logger.info("Adding new feedback to retrospective: " + retrospectiveName);
        logger.debug("Adding feedback with values: " + newFeedbackItem);
        retrospectiveRepository.createNewFeedbackForRetrospective(retrospectiveName, newFeedbackItem);
    }

    public void updateFeedbackForRetrospective (String retrospectiveName, int itemId, FeedbackItem feedbackItem) {
        logger.info("Updating feedback for retrospective: " + retrospectiveName + ", item ID: " + itemId);
        logger.debug("Adding feedback with values: " + feedbackItem);
        retrospectiveRepository.updateFeedbackForRetrospective(retrospectiveName, itemId, feedbackItem);
    }
}
