package com.techtest.scrumretroapi.service;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.Feedback;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.repository.RetrospectiveRepository;
import com.techtest.scrumretroapi.utils.LoggingUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RetrospectiveService {

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    private final Log logger = LoggingUtil.getLogger(RetrospectiveService.class);

    public Page<Retrospective> getAllRetrospectives(Pageable pageable) {
        logger.debug("Attempting to retrieve all retrospectives with pagination");
        return retrospectiveRepository.findAllRetrospectives(pageable);
    }

    public List<Retrospective> getRetrospectivesByDate(LocalDate date) {
        logger.info("Attempting to retrieve retrospectives for date: " + date);
        return retrospectiveRepository.findRetrospectivesByDate(date);
    }

    @Transactional
    public void createNewRetrospective(Retrospective retrospective) throws Exception {
        String nameToCheck = retrospective.getName();
        logger.debug(String.format("Attempting to add new retrospective with name='%s'", nameToCheck));

        Retrospective existingRetrospective = retrospectiveRepository.findByName(nameToCheck);
        boolean nameExists = existingRetrospective != null;

        if (nameExists) {
            String errorMessage = String.format("The retrospective name '%s' has already been added!", nameToCheck);
            logger.error(errorMessage);
            throw new Exception(errorMessage);
        } else {
            // Check if feedback has been included in the create request
            //List<Feedback> feedbackList = retrospective.getFeedback();

            logger.info("Creating a new retrospective with name: " + nameToCheck);
            retrospectiveRepository.save(retrospective);
            logger.info("New retrospective created successfully");

//            if (feedbackList.isEmpty()) {
//                // save the retrospective as normal
//                retrospectiveRepository.save(retrospective);
//                logger.info("New retrospective created successfully");
//            }
        }
    }

    public void createNewFeedbackForRetrospective(String retrospectiveName, FeedbackItem newFeedbackItem) {
        // Do this immutably, simply make a copy of the existing feedback list, update the list with the new item and simply

//        logger.info("Adding new feedback to retrospective: " + retrospectiveName);
//        logger.debug("Adding feedback with values: " + newFeedbackItem);
//        retrospectiveRepository.createNewFeedbackForRetrospective(retrospectiveName, newFeedbackItem);
    }

    public void updateFeedbackForRetrospective(String retrospectiveName, int itemId, FeedbackItem feedbackItem) {
//        logger.info("Updating feedback for retrospective: " + retrospectiveName + ", item ID: " + itemId);
//        logger.debug("Adding feedback with values: " + feedbackItem);
//        retrospectiveRepository.updateFeedbackForRetrospective(retrospectiveName, itemId, feedbackItem);
    }
}
