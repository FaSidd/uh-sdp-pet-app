import React from 'react';
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

function App() {
  return (
    <div>
      <Router>
        <Navigation />
        <div className="App" data-testid="App">
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route exact path="/login">
              <Login />
            </Route>
            <Route exact path="/register">
              <Register />
            </Route>
            <Route exact path="/user-dashboard">
              <UserDashboard />
            </Route>
            <Route exact path="/employee-dashboard">
              <EmployeeDashboard />
            </Route>
            <Route path="/pet-profile/:id">
              <PetProfile />
            </Route>
            <Route exact path="/pet-register">
              <RegisterPet />
            </Route>
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
