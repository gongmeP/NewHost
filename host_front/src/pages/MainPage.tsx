import React, { useState, useEffect } from 'react';
import axiosAPI, { API_URL } from '../axiosAPI.tsx';
import { HostTS } from '../TSmodel/Hos.ts';


const MainPage = ({}) => {
  const [hosts, setHosts] = useState<HostTS[]>([]);
  const [loading, setLoading] = useState<boolean>(true); // 로딩 상태 추가

  useEffect(() => {
    const fetchHostsData = async () => {
      try {
        const res = await axiosAPI.get('/api/hosts');
        setHosts(res.data);
        console.log(res.data);
      } catch (error) {
        console.error('호스트 데이터를 가져오는 중 오류 발생:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchHostsData(); // 컴포넌트 마운트 시 데이터 가져오기

    // 10초마다 데이터 갱신
    const intervalId = setInterval(fetchHostsData, 10000);

    // 컴포넌트 언마운트 시 인터벌 제거
    return () => clearInterval(intervalId);
  }, []);

  if (loading) {
    return <div>Loading...</div>; // 데이터 로딩 중일 때 화면 표시
  }

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>호스트명</th>
            <th>상태</th>
            <th>응답시간</th>
            <th>마지막 체크</th>
          </tr>
        </thead>
        <tbody>
          {hosts.map((host) => (
            <tr key={host.id}>
              <td>{host.hostname}</td>
              <td>{host.status}</td>
              <td>{host.latency} ms</td>
              <td>{host.lastchecked}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MainPage;
