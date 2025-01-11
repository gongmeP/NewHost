import React, { useState } from 'react';
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TextField,
} from '@mui/material';
import axiosAPI, { API_URL } from '../../axiosAPI.tsx';

const HostAdd = () => {
  const [open, setOpen] = useState(false);
  const [newHost, setNewHost] = useState({
    hostname: '',
    domain: '',
    manager: '',
  });

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNewHost({
      ...newHost,
      [e.target.name]: e.target.value, // name value로 newHost 에 추가
    });
  };
  const handleAddHost = async () => {
    try {
      const res = await axiosAPI.post('/api/hosts/add', newHost);
      alert('새로운 호스트가 추가되었습니다!');
      setNewHost({
        hostname: '',
        domain: '',
        manager: '',
      });
      handleClose(); // 추가 후 팝업 닫기
    } catch (error) {
      alert('새로운 호스트가 추가되었습니다!');
    }
  };
  return (
    <>
      <Button color="inherit" onClick={handleClickOpen}>
        Add
      </Button>
      {/* 팝업 창 */}
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>새 호스트 추가</DialogTitle>
        <DialogContent>
          <TextField
            label="호스트 이름"
            fullWidth
            margin="normal"
            name="hostname"
            value={newHost.hostname}
            onChange={handleInputChange}
          />
          <TextField
            label="호스트 주소"
            fullWidth
            margin="normal"
            name="domain"
            value={newHost.domain}
            onChange={handleInputChange}
          />
          <TextField
            label="담당자"
            fullWidth
            margin="normal"
            name="manager"
            value={newHost.manager}
            onChange={handleInputChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            취소
          </Button>
          <Button onClick={handleAddHost} color="primary">
            추가
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default HostAdd;
