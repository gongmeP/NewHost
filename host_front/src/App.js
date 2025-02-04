import './App.css';
import { Route, Routes, Router } from 'react-router-dom';
import MainPage from './components/MainComponents/HostList.tsx';
import Headers from './components/MainComponents/Header.tsx';
import HeaderWithDrawer from './pages/MainPage.tsx';

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/1" element={<Headers />} />
      <Route path="/2" element={<HeaderWithDrawer />} />
    </Routes>
  );
};

export default App;
