import React from 'react';
import {Toolbar, Button, Container } from '@mui/material';
import { Link } from 'react-router-dom';
import HostAdd from '../ButtonComponents/HostAdd.tsx';

const Header = () => {
  return (
    <Toolbar>
    <Container>
      {/* 추가 컴포넌트 */}
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
  );
};

export default Header;
