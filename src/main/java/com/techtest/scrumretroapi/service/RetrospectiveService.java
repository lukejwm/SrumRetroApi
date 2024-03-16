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
        return retrospectiveRepository.getAllRetrospectives(pageable);
    }

    public Optional<List<Retrospective>> getRetrospectivesByDate(LocalDate date) {
        // TODO: convert date to string?
        return retrospectiveRepository.getRetrospectivesByDate(date);
    }

    public void createNewRetrospective(Retrospective retrospective) throws Exception {
        String nameToCheck = retrospective.getName();

        // check if the name already exists in the repository
        boolean nameAlreadyExists = retrospectiveRepository.existsByName(nameToCheck);

        if (nameAlreadyExists) {
            // TODO: log this!
            throw new Exception(String.format("The retrospective name '%s' has already been added!", nameToCheck));
        } else {
            retrospectiveRepository.createNewRetrospective(retrospective);
        }
    }

    public void createNewFeedbackForRetrospective(String retrospectiveName, FeedbackItem newFeedbackItem) {
        retrospectiveRepository.createNewFeedbackForRetrospective(retrospectiveName, newFeedbackItem);
    }

    public void updateFeedbackForRetrospective (String retrospectiveName, int itemId, FeedbackItem feedbackItem) {
        retrospectiveRepository.updateFeedbackForRetrospective(retrospectiveName, itemId, feedbackItem);
    }
}
