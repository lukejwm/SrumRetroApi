package com.techtest.scrumretroapi.repository;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class RetrospectiveRepository {
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
