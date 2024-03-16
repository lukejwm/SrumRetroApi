package com.techtest.scrumretroapi.service;

import com.techtest.scrumretroapi.entity.Retrospective;
import com.techtest.scrumretroapi.entity.feedback.FeedbackItem;
import com.techtest.scrumretroapi.repository.RetrospectiveRepository;
import com.techtest.scrumretroapi.utils.LoggingUtil;
import jakarta.persistence.EntityManager;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetrospectiveService {

//    @Autowired
//    private EntityManager entityManager;

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    private final Log logger = LoggingUtil.getLogger(RetrospectiveService.class);

//    public RetrospectiveService() {}

    public Page<Retrospective> getAllRetrospectives(Pageable pageable) {
        logger.debug("Attempting to retrieve all retrospectives with pagination");
        return retrospectiveRepository.findAllRetrospectives(pageable);
    }

    public List<Retrospective> getRetrospectivesByDate(LocalDate date) {
        logger.info("Attempting to retrieve retrospectives for date: " + date);
        return retrospectiveRepository.findRetrospectivesByDate(date);
    }

    public void createNewRetrospective(Retrospective retrospective) throws Exception {
        String nameToCheck = retrospective.getName();
        logger.debug(String.format("Attempting to add new retrospective with name='%s'", nameToCheck));

        //Optional<Retrospective> existingRetrospective = retrospectiveRepository.findByName(nameToCheck);
        Retrospective existingRetrospective = retrospectiveRepository.findByName(nameToCheck);
        boolean nameExists = existingRetrospective != null;

        if (nameExists) {
            String errorMessage = String.format("The retrospective name '%s' has already been added!", nameToCheck);
            logger.error(errorMessage);
            throw new Exception(errorMessage);
        } else {
            logger.info("Creating a new retrospective with name: " + nameToCheck);
            retrospectiveRepository.save(retrospective);
            logger.info("New retrospective created successfully");
        }
    }

    public void createNewFeedbackForRetrospective(String retrospectiveName, FeedbackItem newFeedbackItem) {
//        logger.info("Adding new feedback to retrospective: " + retrospectiveName);
//        logger.debug("Adding feedback with values: " + newFeedbackItem);
//        retrospectiveRepository.createNewFeedbackForRetrospective(retrospectiveName, newFeedbackItem);
    }

    public void updateFeedbackForRetrospective (String retrospectiveName, int itemId, FeedbackItem feedbackItem) {
//        logger.info("Updating feedback for retrospective: " + retrospectiveName + ", item ID: " + itemId);
//        logger.debug("Adding feedback with values: " + feedbackItem);
//        retrospectiveRepository.updateFeedbackForRetrospective(retrospectiveName, itemId, feedbackItem);
    }
}


/**
 // before interfacing with the database, store all data in a list to simply ensure that all API functions are working and then
 // integrate with database

 private final List<Retrospective> retrospectiveList = new ArrayList<>();
 private final Log logger = LoggingUtil.getLogger(RetrospectiveRepository.class);

 public RetrospectiveRepository() {
 List<String> participants1 = new ArrayList<>(List.of("Fred", "Tom", "Joan"));
 List<String> participants2 = new ArrayList<>(List.of("Hannah", "Gavin", "Derek"));

 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 1", "Post release retrospective", LocalDate.now(), participants1, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 2", "Another post release retrospective", LocalDate.now(), participants1, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 3", "Pre release retrospective", LocalDate.now(), participants2, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 4", "Post sprint retrospective", LocalDate.now(), participants1, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 5", "Some retrospective", LocalDate.now(), participants2, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 6", "Another post release retrospective", LocalDate.now(), participants1, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 7", "Another pre release retrospective", LocalDate.now(), participants2, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 8", "Tech support retrospective", LocalDate.now(), participants1, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 9", "Another tech support retrospective", LocalDate.now(), participants2, new ArrayList<>()));
 //        retrospectiveList.add(new Retrospective(
 //                "Retrospective 10", "Some other retrospective", LocalDate.now(), participants1, new ArrayList<>()));
 }

 public Optional<Page<Retrospective>> getAllRetrospectives(Pageable pageable) {
 if (retrospectiveList.isEmpty()) {
 logger.debug("No retrospectives available. Returning empty optional.");
 return Optional.empty();
 } else {
 int start = (int) pageable.getOffset();
 int end = Math.min((start + pageable.getPageSize()), retrospectiveList.size());
 List<Retrospective> content = retrospectiveList.subList(start, end);
 Page<Retrospective> page = new PageImpl<>(content, pageable, retrospectiveList.size());
 logger.debug("Retrieved " + content.size() + " retrospectives for page: " + pageable.getPageNumber() + ", size: " + pageable.getPageSize());
 return Optional.of(page);
 }
 }

 public Optional<List<Retrospective>> getRetrospectivesByDate(LocalDate date) {
 List<Retrospective> filteredList = retrospectiveList.stream().filter(retrospective -> retrospective.getDate().equals(date)).toList();
 if (filteredList.isEmpty()) {
 logger.debug("No retrospectives found for date: " + date);
 return Optional.empty();
 } else {
 logger.debug("Retrieved " + filteredList.size() + " retrospectives for date: " + date);
 return Optional.of(filteredList);
 }
 }

 public void createNewRetrospective(Retrospective retrospective) {
 logger.debug("Adding new retrospective: " + retrospective);
 retrospectiveList.add(retrospective);
 logger.info("New retrospective added successfully: " + retrospective);
 }

 public boolean existsByName(String name) {
 boolean exists = retrospectiveList.stream().anyMatch(r -> r.getName().equals(name));
 logger.debug("Checking existence of retrospective with name '" + name + "': " + exists);
 return exists;
 }

 public void createNewFeedbackForRetrospective(String retrospectiveName, FeedbackItem newFeedbackItem) {
 //        retrospectiveList.stream()
 //                .filter(r -> r.getName().equals(retrospectiveName))
 //                .findFirst()
 //                .ifPresent(retrospective -> {
 //                    // Log the start of the method
 //                    logger.info("Attempting to create new feedback for retrospective: " + retrospectiveName);
 //
 //                    // Get the existing feedback list
 //                    List<Feedback> feedbackList = new ArrayList<>(retrospective.getFeedback());
 //
 //                    // Log the size of the existing feedback list
 //                    logger.debug("Existing feedback list size: " + feedbackList.size());
 //
 //                    // Find the new id
 //                    int newItemId = feedbackList.size() + 1;
 //
 //                    // Log the new item id
 //                    logger.debug("New item id: " + newItemId);
 //
 //                    // Add the new feedback item with the new id
 //                    feedbackList.add(new Feedback(newItemId, newFeedbackItem));
 //
 //                    // Log the addition of the new feedback item
 //                    logger.debug("New feedback item added: " + newFeedbackItem);
 //
 //                    // Update the retrospective with the new feedback list
 //                    retrospective.setFeedback(feedbackList);
 //
 //                    // Log the end of the method
 //                    logger.info("New feedback added successfully for retrospective: " + retrospectiveName);
 //                });
 }

 public void updateFeedbackForRetrospective(String retrospectiveName, int itemId, FeedbackItem newFeedbackItem) {
 //        retrospectiveList.stream()
 //                .filter(r -> r.getName().equals(retrospectiveName))
 //                .findFirst()
 //                .ifPresent(retrospective -> {
 //                    // Log the start of the method
 //                    logger.info("Attempting to update feedback for retrospective: " + retrospectiveName);
 //
 //                    // Get the existing feedback list
 //                    List<Feedback> feedbackList = new ArrayList<>(retrospective.getFeedback());
 //
 //                    // Log the size of the existing feedback list
 //                    logger.debug("Existing feedback list size: " + feedbackList.size());
 //
 //                    // Find the feedback item by its ID
 //                    Optional<Feedback> feedbackToUpdate = feedbackList.stream()
 //                            .filter(feedback -> feedback.getItem() == itemId)
 //                            .findFirst();
 //
 //                    // Log if the feedback item is found or not
 //                    if (feedbackToUpdate.isPresent()) {
 //                        logger.debug("Feedback item found with id: " + itemId);
 //                    } else {
 //                        logger.debug("Feedback item not found with id: " + itemId);
 //                    }
 //
 //                    // Update the feedback item if found
 //                    feedbackToUpdate.ifPresent(feedback -> {
 //                        // Log the update of the feedback item
 //                        logger.debug("Updating feedback item with id: " + itemId);
 //                        feedback.setItemBody(newFeedbackItem);
 //                    });
 //
 //                    // Update the retrospective with the modified feedback list
 //                    retrospective.setFeedback(feedbackList);
 //
 //                    // Log the end of the method
 //                    logger.info("Feedback updated successfully for retrospective: " + retrospectiveName);
 //                });
 }
 */
