import React from 'react';
import ReactDOM from 'react-dom';
import './styles/App.css';
import {Router} from 'react-router';
import App from './App';
import history from "./utils/history";

ReactDOM.render(
  <React.StrictMode>
    <Router history={history}>
      <App/>
    </Router>
  </React.StrictMode>,
  document.getElementById('root'),
);
