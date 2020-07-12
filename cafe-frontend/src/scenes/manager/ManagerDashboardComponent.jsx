import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Avatar from '@material-ui/core/Avatar';
import FastfoodIcon from '@material-ui/icons/Fastfood';
import PersonAddIcon from '@material-ui/icons/PersonAdd';
import LayersIcon from '@material-ui/icons/Layers';
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import AppBar from "@material-ui/core/AppBar";
import Grid from "@material-ui/core/Grid";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";

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
  gridpaper: {
    height: 200,
    width: 300,
  },
  control: {
    padding: theme.spacing(2),
  },
}));

const ManagerDashboardComponent = (props) => {
  const {navigateToCreateUsers, navigateToProducts, navigateToTables, handleLogOutRequest} = props;

  const classes = useStyles();

  return (
    <div>
      <div className={classes.root}>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6" className={classes.title}>
              Manager
            </Typography>
            <Button onClick={handleLogOutRequest} style={{float: 'left'}} color="inherit">Logout</Button>
          </Toolbar>
        </AppBar>
      </div>

      <Grid style={{marginTop: '60px'}} container spacing={10}>
        <Grid container justify="center" spacing={3}>
          <Grid onClick={navigateToCreateUsers} item>
            <Card style={{padding: '60px', cursor: 'pointer'}}>
              <CardContent>
                <Typography>
                  <Avatar/>
                  Users
                </Typography>
              </CardContent>
            </Card>
          </Grid>
          <Grid item onClick={navigateToTables}>
            <Card style={{padding: '60px', cursor: 'pointer'}}>
              <CardContent>
                <Typography>
                  <Avatar>
                    <LayersIcon/>
                  </Avatar>
                  Tables
                </Typography>
              </CardContent>
            </Card>
          </Grid>
          <Grid item onClick={navigateToProducts}>
            <Card style={{padding: '60px', cursor: 'pointer'}}>
              <CardContent>
                <Typography>
                  <Avatar>
                    <FastfoodIcon/>
                  </Avatar>
                  Products
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      </Grid>
    </div>
  );
};

export default ManagerDashboardComponent;
