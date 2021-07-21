import {React} from 'react'

export const MatchDetailCard = (props) => {

    if(!props.match) return null;

    return (
        <div className="MatchDetailCard">
            <h1>Latest Matches</h1>
            <h4>Match Details</h4>
            <h4>{props.match.team1} vs {props.match.team2} </h4>

        </div>
    );
}


