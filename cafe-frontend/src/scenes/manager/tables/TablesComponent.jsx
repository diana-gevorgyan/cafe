import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import ModalContainer from "../../ModalContainer";
import Button from "@material-ui/core/Button";
import history from "../../../utils/history";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import MenuItem from "@material-ui/core/MenuItem";
import {Alert, AlertTitle} from "@material-ui/lab";
import Select from "@material-ui/core/Select";

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

export default function TablesComponent(props) {
  const {
    tables, newTableNumber, setNewTableNumber, newTableAssignedUserId, setNewTableAssignedUserId, isModalOpen,
    setModalOpen, allWaiterUsers, saveNewTable, errors, changeAssignedWaiter
  } = props;
  const classes = useStyles();

  return (
    <div>
      <ModalContainer title={"Create Table"} isModalOpen={isModalOpen} setModalOpen={setModalOpen}>
        <div className={classes.modalContentWrapper}>
          <TextField
            required
            className={classes.modalItem}
            id="number"
            label="Number"
            variant="outlined"
            type="email"
            value={newTableNumber}
            onChange={event => setNewTableNumber(event.target.value)}
          />
          <TextField
            id="type"
            required
            select
            label="Assigned User"
            variant="outlined"
            className={classes.modalItem}
            value={newTableAssignedUserId}
            onChange={event => setNewTableAssignedUserId(event.target.value)}
          >
            {allWaiterUsers.map(user => (
              <MenuItem key={user.id} value={user.id}>{user.name}</MenuItem>
            ))}
          </TextField>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            className={classes.modalItem}
            onClick={() => saveNewTable()}
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

      <Typography variant={"h4"} align={"center"}>Tables</Typography>
      <TableContainer style={{padding: '20px 60px'}}>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell><b>ID</b></TableCell>
              <TableCell align={"center"}><b>Number</b></TableCell>
              <TableCell align={"center"}><b>Assigned Waiter</b></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {tables.map((table) => (
              <TableRow key={table.id}>
                <TableCell>{table.id}</TableCell>
                <TableCell align={"center"}>{table.number}</TableCell>
                <TableCell align={"center"}>
                  <Select onChange={(e) => changeAssignedWaiter(e, table.id)} value={table.waiter.id}>
                    {allWaiterUsers.map(user => (
                      <MenuItem key={user.id} value={user.id}>{user.name}</MenuItem>
                    ))}
                  </Select>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}