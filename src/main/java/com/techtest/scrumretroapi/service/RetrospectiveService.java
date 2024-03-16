package com.techtest.scrumretroapi.service;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.repository.RetrospectiveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetrospectiveService {
    private final RetrospectiveRepository retrospectiveRepository;

    public RetrospectiveService(RetrospectiveRepository retrospectiveRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
    }

    public Optional<List<Retrospective>> getAllRetrospectives() {
        return retrospectiveRepository.getAllRetrospectives();
    }

    public Optional<List<Retrospective>> getRetrospectivesByDate(LocalDate date) {
        // TODO: convert date to string?
        return retrospectiveRepository.getRetrospectivesByDate(date);
    }

    public void createNewRetrospective(Retrospective retrospective) {
        retrospectiveRepository.createNewRetrospective(retrospective);
    }

    public void createNewFeedbackForRetrospective(String retrospectiveName, FeedbackItem newFeedbackItem) {
        retrospectiveRepository.createNewFeedbackForRetrospective(retrospectiveName, newFeedbackItem);
    }

    public void updateFeedbackForRetrospective (String retrospectiveName, int itemId, FeedbackItem feedbackItem) {
        retrospectiveRepository.updateFeedbackForRetrospective(retrospectiveName, itemId, feedbackItem);
    }
}
