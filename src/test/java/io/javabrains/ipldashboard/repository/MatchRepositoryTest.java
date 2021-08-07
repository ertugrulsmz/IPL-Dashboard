package io.javabrains.ipldashboard.repository;

import io.javabrains.ipldashboard.model.Match;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatchRepositoryTest {

    @Autowired
    MatchRepository matchRepository;

    @Test
    void findLatestMatchesByTeam() {

        //given
        String teamName = "Chennai Super Kings";

        //when
        List<Match> matches = matchRepository.findLatestMatchesByTeam(teamName);

        //then

        assertNotNull(matches);
        assertEquals(matches.size(), 3);
    }


}