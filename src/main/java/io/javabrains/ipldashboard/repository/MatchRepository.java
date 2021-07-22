package io.javabrains.ipldashboard.repository;

import io.javabrains.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {
    List<Match> getAllByTeam1OrTeam2OrderByDateDesc
            (String team1, String team2, Pageable pageable);



    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) " +
            "and m.date between :date1 and :date2 order by m.date desc")
    List<Match> getByTeamBetweenYears
            (@Param("teamName")String teamName, @Param("date1")LocalDate date1, @Param("date2")LocalDate date2);
    /*
    //It works but ugly...
    List<Match> getAllByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc
            (String team1, LocalDate date1, LocalDate date2, String team2,
             LocalDate date3, LocalDate date4);
    */



    // Normally service layer would do that but in this project we have preferred this one.
    default List<Match> findLatestMatchesByTeam(String teamName, int count){
        return getAllByTeam1OrTeam2OrderByDateDesc(teamName,teamName, PageRequest.of(0,count));
    }
    // As an example to python default parameter value (teamName, count=3)
    default List<Match> findLatestMatchesByTeam(String teamName){
        return findLatestMatchesByTeam(teamName,3);
    }


    default List<Match> getMatchesByTeamAndYear(String teamName, int year){

        LocalDate beginningOfYear = LocalDate.of(year,1,1);
        LocalDate endOfYear = LocalDate.of(year,12,31);
        return getByTeamBetweenYears(teamName,beginningOfYear,endOfYear);

    }
}
