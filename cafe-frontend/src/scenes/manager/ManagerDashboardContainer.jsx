import React from 'react';
import ManagerDashboardComponent from "./ManagerDashboardComponent";
import history from "../../utils/history";
import {logout} from "../../utils/auth";

export default function ManagerDashboardContainer() {

  const navigateToCreateUsers = () => {
    history.push("/manager/create-user")
  }

  const navigateToTables = () => {
    history.push("/manager/tables")
  }

  const navigateToProducts = () => {
    history.push("/manager/products")
  }

  const handleLogOutRequest = () => {
    logout();
    history.push('/login');
  }

  return (
    <ManagerDashboardComponent
      navigateToCreateUsers={navigateToCreateUsers}
      navigateToTables={navigateToTables}
      navigateToProducts={navigateToProducts}
      handleLogOutRequest={handleLogOutRequest}
    />
  );
}
