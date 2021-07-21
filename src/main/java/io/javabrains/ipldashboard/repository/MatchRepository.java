package io.javabrains.ipldashboard.repository;

import io.javabrains.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {
    List<Match> getAllByTeam1OrTeam2OrderByDateDesc
            (String team1, String team2, Pageable pageable);

    // Normally service layer would do that but in this project we have preferred this one.
    default List<Match> findLatestMatchesByTeam(String teamName, int count){
        return getAllByTeam1OrTeam2OrderByDateDesc(teamName,teamName, PageRequest.of(0,count));
    }
    // As an example to python default parameter value (teamName, count=3)
    default List<Match> findLatestMatchesByTeam(String teamName){
        return findLatestMatchesByTeam(teamName,3);
    }

}
