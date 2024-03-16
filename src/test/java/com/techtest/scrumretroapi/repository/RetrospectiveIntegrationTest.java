package com.techtest.scrumretroapi.repository;

import com.techtest.scrumretroapi.entity.Retrospective;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RetrospectiveIntegrationTest {

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @Test
    @Transactional
    public void testGetAllRetrospectives() {
        Retrospective retrospective = new Retrospective();
        retrospective.setName("Retrospective 1");
        retrospective.setSummary("Retrospective Summary");
        retrospective.setDate(LocalDate.now());
        retrospective.setParticipants(Arrays.asList("Harry", "Megan", "Tom"));

        retrospectiveRepository.save(retrospective);
        List<Retrospective> retro = retrospectiveRepository.findRetrospectivesByDate(LocalDate.now());
    }
}
