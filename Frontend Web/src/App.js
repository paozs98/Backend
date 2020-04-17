import React from 'react';
import Login from './components/login/login';
import Grupos from './components/grupos/grupos';
import Home from './components/home/home';
import Estudiantes from './components/estudiantes/estudiantes';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

export default function App() {
  return (
    <Router>
        <Switch>
          <Route path="/home"> <Home /></Route>
          <Route path="/grupos"> <Grupos /></Route>
          <Route path="/estudiantes"> <Estudiantes /></Route>
          <Route path="/"><Login /></Route>
        </Switch>
    </Router>
  );
}