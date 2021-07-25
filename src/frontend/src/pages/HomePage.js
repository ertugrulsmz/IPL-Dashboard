import {React, useEffect, useState} from 'react'
import './HomePage.scss';
import {TeamTile} from "../components/TeamTile";

export const HomePage = () => {

    const [teams,setTeams] = useState([]);



    useEffect(() => {
            const fetchAllTeams = async () => {
                //fetch returns promise
                const response = await fetch(`/teams`);
                const data = await response.json();
                console.log(data);
                setTeams(data);
            };

            fetchAllTeams();
        },[] //when Link is clicked path so teamname changes
        // it does not send request to server just change the path
        // when path changes you fetch again
    );


    return (
        <div className="HomePage">

            <div className="header-section">
                <h1 className="app-name"> Spring IPL Dashboard</h1>
            </div>

            <div className="team-grid">
                {teams.map(team => <TeamTile key={team.id} teamName={team.teamName}/>)}
            </div>


        </div>
    );
}


