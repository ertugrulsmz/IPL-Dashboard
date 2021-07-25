import {React} from 'react';
import './TeamTile.scss';
import {Link} from 'react-router-dom'

export const TeamTile = ({teamName}) =>{

    return(
        <div className={"TeamTile"}>
            <Link to={`teams/${teamName}`}>
                <h1>{teamName}</h1>
            </Link>

        </div>
    )
}