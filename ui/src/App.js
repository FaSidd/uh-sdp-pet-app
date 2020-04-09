import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from './pages/Home';
import RegisterPet from './pages/RegisterPet';
import PetProfile from './pages/PetProfile';
import EmployeeDashboard from './pages/EmployeeDashboard';
import UserDashboard from './pages/UserDashboard';
import Login from './pages/Login';
import Register from './pages/Register';
import Navigation from './components/Navigation';
import PrivateRoute from './components/PrivateRoute';

const App = () => {
  const [auth, setAuth] = useState(false);

  useEffect(() => {
    // check local storage
    // setAuth if JWT in local
    if (localStorage.getItem('jwt') !== null) {
      setAuth(true);
    }
  }, []);

  return (
    <div>
      <Router>
        <Navigation handleAuth={setAuth} />
        <div className="App" data-testid="App">
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route exact path="/login">
              <Login handleAuth={setAuth} />
            </Route>
            <Route exact path="/register">
              <Register handleAuth={setAuth} />
            </Route>
            <PrivateRoute auth={auth} exact path="/user-dashboard">
              <UserDashboard />
            </PrivateRoute>
            <PrivateRoute auth={auth} exact path="/employee-dashboard">
              <EmployeeDashboard />
            </PrivateRoute>
            <Route path="/pet-profile/:id">
              <PetProfile />
            </Route>
            <PrivateRoute auth={auth} exact path="/pet-register">
              <RegisterPet />
            </PrivateRoute>
          </Switch>
        </div>
      </Router>
    </div>
  );
};

App.propTypes = {
  children: PropTypes.node.isRequired,
};

export default App;
