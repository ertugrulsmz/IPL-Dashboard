import {React, useEffect, useState} from 'react'
import {MatchDetailCard} from "../components/MatchDetailCard";
import {useParams} from 'react-router-dom'
import './MatchPage.scss'
import {YearSelector} from "../components/YearSelector";


export const MatchPage = () => {

    const [matches,setMatches] = useState([]);


    const {teamName,year} = useParams();


    useEffect(() => {
            const fetchMatches = async () => {
                //fetch returns promise
                const response = await
                    fetch(`/teams/${teamName}/matches/?year=${year}`);
                const data = await response.json();
                console.log(data);
                setMatches(data);
            };

            fetchMatches();
        },[year] //when Link is clicked path so teamname changes
        // it does not send request to server just change the path
        // when path changes you fetch again
    );

    return (
        <div className="MatchPage">

            <div className="year-selector">
                <h3>Select Year</h3>
                <YearSelector teamName={teamName} />
            </div>

            <div>
                <h1 className={"page-heading"}>{teamName} matches in {year}</h1>
                {
                    matches.length > 0 ?
                    matches.map(match =>
                    <MatchDetailCard key={match.id} teamName={teamName} match={match}/>)
                        :<h3 className={"no-match-text"}>No Match is Found</h3>


                }
            </div>

        </div>
    );
}


