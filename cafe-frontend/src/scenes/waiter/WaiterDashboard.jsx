import React, {Component} from 'react';
import Typography from "@material-ui/core/Typography";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Button from "@material-ui/core/Button";
import {makeStyles} from "@material-ui/core/styles";
import {logout} from "../../utils/auth";
import history from "../../utils/history";

const useStyles = makeStyles((theme) => ({
  paper: {
    display: "flex",
    justifyContent: "center",
  },
  list: {
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
    marginLeft: theme.spacing(2),
    marginRight: theme.spacing(2),
  },
  root: {
    flexGrow: 1,
  },
  title: {
    flexGrow: 1,
  },
}));

const WaiterDashboard = () => {
  const handleLogOutRequest = () => {
    logout();
    history.push('/login');
  }
    const classes = useStyles();
    return (
      <>
        <div className={classes.root}>
          <AppBar position="static">
            <Toolbar>
              <Typography variant="h6" className={classes.title}>
                Waiter
              </Typography>
              <Button onClick={handleLogOutRequest} style={{float: 'left'}} color="inherit">Logout</Button>
            </Toolbar>
          </AppBar>
        </div>
        <Typography variant={"h4"} align={"center"} style={{margin: '15px'}}>Coming Soon :)</Typography>
      </>
    );
}

WaiterDashboard.propTypes = {};

export default WaiterDashboard;