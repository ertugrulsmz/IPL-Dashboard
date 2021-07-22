import {React, useEffect, useState} from 'react'
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchSmallCard} from "../components/MatchSmallCard";
import {useParams} from 'react-router-dom'

export const MatchPage = () => {

    const [matches,setMatches] = useState([]);


    const {teamName,year} = useParams();


    useEffect(() => {
            const fetchMatches = async () => {
                //fetch returns promise
                const response = await
                    fetch(`http://localhost:8085/teams/${teamName}/matches/?year=${year}`);
                const data = await response.json();
                console.log(data);
                setMatches(data);
            };

            fetchMatches();
        },[] //when Link is clicked path so teamname changes
        // it does not send request to server just change the path
        // when path changes you fetch again
    );

    return (
        <div className="MatchPage">
            <h1>Match Page</h1>

            {
                matches.map(match =>
                    <MatchDetailCard key={match.id} teamName={teamName} match={match}/>)
            }

        </div>
    );
}

