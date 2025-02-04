import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Header from '../components/MainComponents/Header.tsx';
import SideBar from '../components/MainComponents/SideBar.tsx';
import HostList from '../components/MainComponents/HostList.tsx';

const drawerWidth = 180;
const MainPage = () =>{

  return (
    <Box sx={{ display: 'flex' }}>
 
      <AppBar
        position="fixed"
        sx={{
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          ml: { sm: `${drawerWidth}px` },
        }}
      >

        {/* 헤더 컴포넌트 */}
        <Header></Header>
      </AppBar>
      
      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        aria-label="mailbox folders"
      >
       
        {/* 사이드바 컴포넌트 */}
       <SideBar drawerWidth={drawerWidth} />
      
      </Box>

      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          width: { sm: `calc(100% - ${drawerWidth}px)` },
        }}
      >
        <Toolbar />
        <Typography sx={{ marginBottom: 2 }}>

           {/* 호스트 리스트 컴포넌트 */}
          <HostList />

        </Typography>
      </Box>
    </Box>
  );
}

export default MainPage;
