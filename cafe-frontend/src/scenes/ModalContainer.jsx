import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";

function getModalStyle() {
    return {
        top: `50%`,
        left: `50%`,
        transform: `translate(-50%, -50%)`,
    };
}

const useStyles = makeStyles((theme) => ({
    paper: {
        position: 'absolute',
        width: 400,
        backgroundColor: theme.palette.background.paper,
        border: '2px solid #000',
        boxShadow: theme.shadows[5],
        padding: theme.spacing(2, 4, 3),
    },
}));

export default function ModalContainer(props) {
    const {children, title, isModalOpen, setModalOpen} = props;
    const classes = useStyles();
    const [modalStyle] = React.useState(getModalStyle);

    const handleOpen = () => {
        setModalOpen(true);
    };

    const handleClose = () => {
        setModalOpen(false);
    };

    return (
        <div>
            <Button
                onClick={handleOpen}
                variant="outlined"
                color="primary"
                style={{
                    float: 'right',
                    margin: '10px'
                }}>
                + {title}
            </Button>
            <Modal
                open={isModalOpen}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <div style={modalStyle} className={classes.paper}>
                    <Typography variant={"h4"} align={'center'}>{title}</Typography>
                    {children}
                </div>
            </Modal>
        </div>
    );
}
