import {createMuiTheme} from '@material-ui/core';

export default createMuiTheme({
  typography: {
    fontFamily: [
      '-apple-system',
      'BlinkMacSystemFont',
      '"Segoe UI"',
      'Roboto',
      '"Helvetica Neue"',
      'Arial',
      'sans-serif',
      '"Apple Color Emoji"',
      '"Segoe UI Emoji"',
      '"Segoe UI Symbol"',
    ].join(','),
  },
  palette: {
    primary: {
      light: '#ffa341',
      main: '#ff7200',
      dark: '#e65100',
      contrastText: '#ffffff',
    },
    secondary: {
      light: '#6d82c0',
      main: '#3d568f',
      dark: '#002e61',
      contrastText: '#ffffff',
    },
    background: {
      backgroundColor: '#F2F5FC',
      color: '#09183A',
    },
    text: {
      primary: '#09183a',
      disabled: '#a4adc1',
    },
  },
});