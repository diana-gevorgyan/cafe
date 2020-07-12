import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import ModalContainer from "../../ModalContainer";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";
import history from "../../../utils/history";
import {Alert, AlertTitle} from "@material-ui/lab";

const useStyles = makeStyles((theme) => ({
  table: {
    minWidth: 650,
  },
  modalContentWrapper: {
    marginTop: theme.spacing(4),
    display: "flex",
    flexDirection: 'column'
  },
  modalItem: {
    margin: theme.spacing(1)
  },
}));

export default function UsersComponent(props) {
  const {
    users, newUserName, setNewUserName, newUserEmail, setNewUserEmail, newUserType, setNewUserType, newUserPassword,
    setNewUserPassword, saveNewUser, isModalOpen, setModalOpen, errors
  } = props;

  const classes = useStyles();

  return (
    <div>
      <ModalContainer title={"Create User"} isModalOpen={isModalOpen} setModalOpen={setModalOpen}>
        <div className={classes.modalContentWrapper}>
          <TextField
            className={classes.modalItem}
            id="email"
            label="Email"
            variant="outlined"
            type="email"
            value={newUserEmail}
            onChange={event => setNewUserEmail(event.target.value)}
          />
          <TextField
            className={classes.modalItem}
            id="name"
            label="Name"
            variant="outlined"
            value={newUserName}
            onChange={event => setNewUserName(event.target.value)}
          />
          <TextField
            id="type"
            select
            label="Type"
            variant="outlined"
            className={classes.modalItem}
            value={newUserType}
            onChange={event => setNewUserType(event.target.value)}
          >
            <MenuItem key={"MANAGER"} value={"MANAGER"}>MANAGER</MenuItem>
            <MenuItem key={"WAITER"} value={"WAITER"}>WAITER</MenuItem>
          </TextField>
          <TextField
            className={classes.modalItem}
            id="password"
            label="Password"
            variant="outlined"
            type="password"
            value={newUserPassword}
            onChange={event => setNewUserPassword(event.target.value)}
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            className={classes.modalItem}
            onClick={() => saveNewUser()}
          >
            Save
          </Button>
          {errors &&
          <Alert severity="error">
            <AlertTitle>Error</AlertTitle>
            {errors.messages}
          </Alert>}
        </div>
      </ModalContainer>

      <Button
        variant="outlined"
        onClick={() => history.push('/manager')}
        style={{
          margin: '10px'
        }}
      >
        {'<- Back'}
      </Button>

      <Typography variant={"h4"} align={"center"} style={{margin: '15px'}}>Users</Typography>
      <TableContainer  style={{padding: '10px 60px',}}>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell variant="head"><b>ID</b></TableCell>
              <TableCell align={"center"}><b>Name</b></TableCell>
              <TableCell align={"center"}><b>Email</b></TableCell>
              <TableCell align={"center"}><b>Type</b></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {users.map((user) => (
              <TableRow key={user.id}>
                <TableCell scope="row">{user.id}</TableCell>
                <TableCell scope="row" align={"center"}>{user.name}</TableCell>
                <TableCell scope="row" align={"center"}>{user.email}</TableCell>
                <TableCell scope="row" align={"center"}>{user.type}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}
