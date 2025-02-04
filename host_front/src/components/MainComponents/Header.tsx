import React from 'react';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';
import { Link } from 'react-router-dom';
import HostAdd from '../ButtonComponents/HostAdd.tsx';

const Header = () => {
  return (
    <AppBar position="sticky">
      <Toolbar>
        <Container>
          <HostAdd></HostAdd>
          <Button color="inherit" component={Link} to="/reports">
            Edit
          </Button>
          <Button color="inherit" component={Link} to="/settings">
            Remove
          </Button>
          <Button color="inherit" component={Link} to="/settings">
            Refresh
          </Button>
        </Container>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
