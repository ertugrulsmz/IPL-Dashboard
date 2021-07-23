import {React} from 'react';
import {Link} from 'react-router-dom'
import './YearSelector.scss'

export const YearSelector = (props) =>{

    const start_year = process.env.REACT_APP_DATA_START_YEAR
    const end_year = process.env.REACT_APP_DATA_END_YEAR

    let years = []
    for(let i = start_year ; i <= end_year; i++){
        years.push(i);
    }

    return(
        <ol className={"YearSelector"}>
            {   years.map(year=>
                    (
                        <li>
                            <Link to={`/teams/${props.teamName}/matches/${year}`}>
                                {year}
                            </Link>
                        </li>
                    )
                )
            }
        </ol>
    )
}