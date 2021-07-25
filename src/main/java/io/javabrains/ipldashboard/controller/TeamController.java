package io.javabrains.ipldashboard.controller;

import io.javabrains.ipldashboard.exception.TeamNotFoundException;
import io.javabrains.ipldashboard.model.Match;
import io.javabrains.ipldashboard.model.Team;
import io.javabrains.ipldashboard.repository.MatchRepository;
import io.javabrains.ipldashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("")
    public Iterable<Team> getAllTeams(){
        return  this.teamRepository.findAll();
    }


    @RequestMapping("/{teamName}")
    public Team getTeam(@PathVariable("teamName")String teamName){
        Team team = teamRepository.findByTeamName(teamName).
                orElseThrow(()-> new TeamNotFoundException("team does not exists"));

        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName,4));

        return team;
    }

    @RequestMapping("/{teamName}/matches")
    public List<Match> getMatchesByTeam
            (@PathVariable("teamName")String teamName,@RequestParam int year){

        return matchRepository.getMatchesByTeamAndYear(teamName,year);
    }



}
