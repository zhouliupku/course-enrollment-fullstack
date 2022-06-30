import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';
import LoginDialog from './LoginDialog';
import { TOKEN_COOKIE_NAME } from '../Constant';
import cookie from 'react-cookies'


export default function MenuBar() {
  const [open, setOpen] = React.useState(false);   // hook
  // truthy
  let hasLoggedIn = !!cookie.load(TOKEN_COOKIE_NAME);
  let buttonText = hasLoggedIn ? "Logout": "Login";
  const handleClickOpen = () => {
    if (hasLoggedIn) {

      // log out: remove cookie
      cookie.remove(TOKEN_COOKIE_NAME);
      window.location.reload();

    }else{
      setOpen(true);
    }
    
  };

  const handleClose = () => {
    setOpen(false);
  };

  
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Course Registration Service
          </Typography>
          <Button color="inherit" component={Link} to="/">AllCourses</Button>
          <Button color="inherit" component={Link} to="enrolled-courses">EnrolledCourses</Button>
          <Button color="inherit" onClick={handleClickOpen}>{buttonText}</Button>
        </Toolbar>
      </AppBar>
      <LoginDialog open={open} handleClose={handleClose}/>  
    </Box>
  );
}
