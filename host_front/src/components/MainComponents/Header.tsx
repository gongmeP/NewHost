import React from 'react';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <AppBar position="sticky">
      <Toolbar>
        <Container>
          <Button color="inherit" component={Link} to="/">
            Add
          </Button>
          <Button color="inherit" component={Link} to="/reports">
            Edit
          </Button>
          <Button color="inherit" component={Link} to="/settings">
            Remote
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
