
import './App.scss';
import {TeamPage} from "./pages/TeamPage";
import {HashRouter as Router, Route, Switch} from 'react-router-dom';
import {MatchPage} from "./pages/MatchPage";
import {HomePage} from "./pages/HomePage";


//TeamPage path is more general than upper so it takes place below
function App() {
  return (
    <div className="App">
        <Router>
            <Switch>

                <Route path="/teams/:teamName/matches/:year">
                    <MatchPage/>
                </Route>


                <Route path="/teams/:teamName">
                    <TeamPage/>
                </Route>

                <Route path="/">
                    <HomePage/>
                </Route>


            </Switch>
        </Router>
    </div>
  );
}

export default App;
