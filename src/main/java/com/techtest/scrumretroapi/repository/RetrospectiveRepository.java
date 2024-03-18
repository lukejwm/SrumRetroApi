package com.techtest.scrumretroapi.repository;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RetrospectiveRepository extends JpaRepository<Retrospective, Long> {
    @Query(value = "SELECT r FROM Retrospective r ORDER BY r.name")
    Page<Retrospective> findAllRetrospectives(Pageable pageable);

    @Query("SELECT r FROM Retrospective r WHERE r.date = :date")
    List<Retrospective> findRetrospectivesByDate(@Param("date") LocalDate date);

    @Query("SELECT r FROM Retrospective r WHERE r.name = :name")
    Retrospective findByName(@Param("name") String name);

//    void createNewRetrospectiveWithFeedback(Retrospective retrospective) throws Exception;

//    void addFeedbackToRetrospective(String retroName, FeedbackItem feedbackItem);
//
//    void updateFeedbackItemForRetrospective(String retroName, int itemId, FeedbackItem newFeedbackItem);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Retrospective r SET r.feedback = CONCAT(r.feedback, :feedbackItem) WHERE r.name = :name")
//    void addFeedbackToRetrospective(@Param("name") String name, @Param("feedbackItem") FeedbackItem feedbackItem);
}
