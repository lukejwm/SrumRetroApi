package com.techtest.scrumretroapi.repository;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.utils.LoggingUtil;
import org.apache.commons.logging.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RetrospectiveRepository {
    // before interfacing with the database, store all data in a list to simply ensure that all API functions are working and then
    // integrate with database

    private final List<Retrospective> retrospectiveList = new ArrayList<>();
    private final Log log = LoggingUtil.getLogger(RetrospectiveRepository.class);

    public RetrospectiveRepository() {
        List<String> participants1 = new ArrayList<>(List.of("Fred", "Tom", "Joan"));
        List<String> participants2 = new ArrayList<>(List.of("Hannah", "Gavin", "Derek"));

        retrospectiveList.add(new Retrospective(
                "Retrospective 1", "Post release retrospective", LocalDate.now(), participants1, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 2", "Another post release retrospective", LocalDate.now(), participants1, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 3", "Pre release retrospective", LocalDate.now(), participants2, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 4", "Post sprint retrospective", LocalDate.now(), participants1, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 5", "Some retrospective", LocalDate.now(), participants2, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 6", "Another post release retrospective", LocalDate.now(), participants1, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 7", "Another pre release retrospective", LocalDate.now(), participants2, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 8", "Tech support retrospective", LocalDate.now(), participants1, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 9", "Another tech support retrospective", LocalDate.now(), participants2, new ArrayList<>()));
        retrospectiveList.add(new Retrospective(
                "Retrospective 10", "Some other retrospective", LocalDate.now(), participants1, new ArrayList<>()));
    }

    public Optional<Page<Retrospective>> getAllRetrospectives(Pageable pageable) {
        if (retrospectiveList.isEmpty()) {
            return Optional.empty();
        } else {
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), retrospectiveList.size());
            List<Retrospective> content = retrospectiveList.subList(start, end);
            return Optional.of(new PageImpl<>(content, pageable, retrospectiveList.size()));
        }
    }

    public Optional<List<Retrospective>> getRetrospectivesByDate(LocalDate date) {
        List<Retrospective> filteredList = retrospectiveList.stream().filter(retrospective -> retrospective.getDate().equals(date)).toList();
        return filteredList.isEmpty() ? Optional.empty() : Optional.of(filteredList);
    }

    public void createNewRetrospective(Retrospective retrospective) {
        retrospectiveList.add(retrospective);
    }

    public boolean existsByName(String name) {
        return true;
    }

    public void createNewFeedbackForRetrospective(String retrospectiveName, FeedbackItem newFeedbackItem) {
    }

    public void updateFeedbackForRetrospective(String retrospectiveName, int itemId, FeedbackItem newFeedbackItem) {
    }
}
