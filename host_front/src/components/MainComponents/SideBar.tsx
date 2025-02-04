import React from 'react';
import {
  Toolbar,
  Button,
  Divider,
  List,
  ListItem,
  ListItemButton,
  ListItemText,
  Drawer,
} from '@mui/material';
import { Link } from 'react-router-dom';

interface SideBarProps {
  drawerWidth: number;
}

const SideBar = ({ drawerWidth }: SideBarProps) => {
  return (
    <>
      <Drawer
        variant="permanent"
        sx={{
          display: { xs: 'none', sm: 'block' },
          '& .MuiDrawer-paper': {
            boxSizing: 'border-box',
            width: drawerWidth,
          },
        }}
        open
      >
        <Toolbar>
          <Button color="inherit" component={Link} to="/">
            add
          </Button>
          <Button color="inherit" component={Link} to="/">
            delete
          </Button>
        </Toolbar>
        <Divider />
        <List sx={{ padding: 0 }}>
          {['A', 'B', 'C', 'D'].map((text) => (
            <ListItem key={text} disablePadding sx={{ margin: 0, padding: 0 }}>
              <ListItemButton sx={{ padding: '0px 16px' }}>
                <ListItemText primary={text} />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
      </Drawer>
    </>
  );
};

export default SideBar;
