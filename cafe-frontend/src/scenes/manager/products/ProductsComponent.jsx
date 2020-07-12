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

export default function ProductsComponent(props) {
  const {
    products, newProductName, setNewProductName, newProductPrice, setNewProductPrice, isModalOpen,
    setModalOpen, saveNewProduct, errors
  } = props;
  const classes = useStyles();

  return (
    <div>
      <ModalContainer title={"Create Product"} isModalOpen={isModalOpen} setModalOpen={setModalOpen}>
        <div className={classes.modalContentWrapper}>
          <TextField
            className={classes.modalItem}
            id="name"
            label="Name"
            variant="outlined"
            value={newProductName}
            onChange={event => setNewProductName(event.target.value)}
          />
          <TextField
            className={classes.modalItem}
            id="price"
            label="Price"
            variant="outlined"
            type={"number"}
            value={newProductPrice}
            onChange={event => setNewProductPrice(event.target.value)}
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            className={classes.modalItem}
            onClick={() => saveNewProduct()}
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

      <Typography variant={"h4"} align={"center"}>Products</Typography>
      <TableContainer style={{padding: '20px 60px'}}>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell><b>ID</b></TableCell>
              <TableCell align={"center"}><b>Name</b></TableCell>
              <TableCell align={"center"}><b>Price</b></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {products.map((product) => (
              <TableRow key={product.id}>
                <TableCell>{product.id}</TableCell>
                <TableCell align={"center"}>{product.name}</TableCell>
                <TableCell align={"center"}>{product.price}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}
