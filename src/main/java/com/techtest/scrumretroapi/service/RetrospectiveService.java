package com.techtest.scrumretroapi.service;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetrospectiveService {

    public RetrospectiveService() {}

    public Optional<List<Retrospective>> getAllRetrospectives() {
        return Optional.empty();
    }

    public Optional<List<Retrospective>> getRetrospectivesByDate(LocalDate date) {
        return Optional.empty();
    }

    public void createNewRetrospective(Retrospective retrospective) {}

    public void createNewFeedbackForRetrospective(String retrospectiveName, FeedbackItem newFeedbackItem) {}

    public void updateFeedbackForRetrospective (String retrospectiveName, int itemId, FeedbackItem newFeedbackItem) {}
}
