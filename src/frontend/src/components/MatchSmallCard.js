import {React} from 'react'
import {Link} from 'react-router-dom'

export const MatchSmallCard = (props) => {

    const otherTeam = props.match.team1 === props.teamName ? props.match.team2 :
        props.match.team1
    const otherTeamRoute = `/teams/${otherTeam}`

    return (
        <div className="MatchSmallCard">
            <h3> vs <Link to={otherTeamRoute}>{otherTeam}</Link> </h3>
            <p>
                {props.match.matchWinner} won
                by {props.match.resultMargin} {props.match.result}
            </p>
        </div>


    );
}


