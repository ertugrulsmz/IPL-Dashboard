import {React, useEffect, useState} from 'react'
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchSmallCard} from "../components/MatchSmallCard";
import {useParams} from 'react-router-dom'
import './TeamPge.scss';
import {PieChart} from 'react-minimal-pie-chart'

export const TeamPage = () => {

    const [team,setTeam] = useState({
        matches : []
    });

    const {teamName} = useParams();

    useEffect(() => {
            const fetchMatches = async () => {
                //fetch returns promise
                const response = await fetch(`http://localhost:8085/teams/${teamName}`);
                const data = await response.json();
                console.log(data);
                setTeam(data);
            };

            fetchMatches();
            },[teamName] //when Link is clicked path so teamname changes
                            // it does not send request to server just change the path
                            // when path changes you fetch again
        );


    //fetch returned null
    if(!team || !team.teamName ){
        return <h1>Team Not Found</h1>
    }

    return (
        <div className="TeamPage">

            <div className="team-name-section">
                <h1 className="team-name"> > {team.teamName}</h1>
            </div>

            <div className="win-loss-section">
                <h3>Wins/Losses</h3>
                <PieChart
                    data={[
                        { title: 'Wins', value: team.totalWins, color: '#4da375' },
                        { title: 'Losses', value: team.totalMatches - team.totalWins,
                            color: '#a34d5d' },
                    ]}
                />
            </div>

            <div className="match-detail-section">
                <h3>Latest Matches</h3>

                <MatchDetailCard  teamName={team.teamName} match={team.matches[0]}/>
            </div>

            {
                team.matches.slice(1).map(match =>
                    <MatchSmallCard key={match.id} teamName={team.teamName} match={match}/>)
            }

            <div className="more-link">
                <a href="#">More ></a>
            </div>


        </div>
    );
}


