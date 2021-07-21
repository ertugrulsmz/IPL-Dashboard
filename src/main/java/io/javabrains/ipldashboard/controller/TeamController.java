package io.javabrains.ipldashboard.controller;

import io.javabrains.ipldashboard.exception.TeamNotFoundException;
import io.javabrains.ipldashboard.model.Team;
import io.javabrains.ipldashboard.repository.MatchRepository;
import io.javabrains.ipldashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }



    @RequestMapping("/{teamName}")
    public Team getTeam(@PathVariable("teamName")String teamName){
        Team team = teamRepository.findByTeamName(teamName).
                orElseThrow(()-> new TeamNotFoundException("team does not exists"));

        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName,4));

        return team;
    }
}
