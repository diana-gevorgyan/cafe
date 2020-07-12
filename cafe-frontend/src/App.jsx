import React from 'react';
import './styles/App.css';
import {Route, Switch} from 'react-router';
import {ThemeProvider} from '@material-ui/core/styles';
import theme from './styles/globalTheme';
import LoginContainer from "./scenes/login/LoginContainer";
import WaiterDashboard from "./scenes/waiter/WaiterDashboard";
import ManagerDashboardContainer from "./scenes/manager/ManagerDashboardContainer";
import CreateUserContainer from "./scenes/manager/users/UsersContainer";
import TablesContainer from "./scenes/manager/tables/TablesContainer";
import ProductsContainer from "./scenes/manager/products/ProductsContainer";

export default function App() {
  return (
    <div className="app">
      <ThemeProvider theme={theme}>
        <Switch>
          <Route exact path="/" component={LoginContainer}/>
          <Route exact path="/login" component={LoginContainer}/>
          <Route path="/manager/tables" component={TablesContainer}/>
          <Route path="/manager/products" component={ProductsContainer}/>
          <Route path="/manager/create-user" component={CreateUserContainer}/>
          <Route path="/manager" component={ManagerDashboardContainer}/>
          <Route path="/waiter" component={WaiterDashboard}/>
        </Switch>
      </ThemeProvider>
    </div>
  );
}
