package io.javabrains.ipldashboard.data;


import io.javabrains.ipldashboard.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            /*
            jdbcTemplate.query("SELECT team1,team2 FROM match",
                    (rs, row) -> "Team 1 "+ rs.getString(1)+ "Team2 " + rs.getString(2)
            ).stream().limit(3).collect(Collectors.toList()).forEach(System.out::println);*/

            Map<String, Team> teamData = new HashMap<>();

            List<Object[]> queryResult = entityManager.
                    createQuery("select m.team1, count(m.team1) from Match m " +
                    "group by m.team1",Object[].class).getResultList();

            //Firstly fill totalmatch, rest fields will be empty but will be filled
            // this is the reason it was not ("team",11)
            queryResult.stream().map(e->new Team((String)e[0],(long)e[1]))
                    .forEach(team -> teamData.put(team.getTeamName(),team));


            List<Object[]> queryResult2 = entityManager.
                    createQuery("select m.team2, count(m.team2) from Match m " +
                            "group by m.team2",Object[].class).getResultList();


            // Add total match as team also occurs in away
            queryResult2.forEach(e->{
                Team team = teamData.get((String) e[0]);
                team.setTotalMatches(team.getTotalMatches()+ (long)e[1]);
            });


            //match winner, fill the match winner field
            entityManager.createQuery("select m.matchWinner, count(m.matchWinner) from Match m group by m.matchWinner",
                    Object[].class)
                    .getResultList().
                    forEach(e ->{
                                Team team = teamData.get((String) e[0]);
                                if(team != null){ // in one row winner is N/A
                                    team.setTotalWins((long)e[1]);
                                }

            });


            //Save to Database
            teamData.values().forEach(team->entityManager.persist(team));

            //Show
            teamData.values().forEach(System.out::println);
        }
    }
}
