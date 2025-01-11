package com.example.hostmonitor.service;

import com.example.hostmonitor.model.Host;
import com.example.hostmonitor.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HostMonitorServiceImpl implements HostMonitorService {
    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private PingService pingService;

    @Override
    @Scheduled(fixedRate = 10000) // 5초마다 실행
    public void monitorHosts() {
        try {
            List<Host> hosts = hostRepository.findAll();
            for (Host host : hosts) {
                String statusAndLatency = pingService.pingHost(host.getDomain());

                // 리턴 값 상태 분리
                String[] statusAndLatencyArray = statusAndLatency.split(", ");
                String status = statusAndLatencyArray[0];
                Long latency = Long.parseLong(statusAndLatencyArray[1].split(": ")[1].replace(" ms", ""));

                // 상태 레이턴시 체크시간 DB 저장
                host.setStatus(status);
                host.setLatency(latency);
                host.setLastchecked(LocalDateTime.now());
                hostRepository.save(host);

                System.out.println(host.getDomain() + " 상태: " + status + " / 응답시간 : " + latency + "ms");
            }

        } catch (Exception e) {
            System.out.println("호스트 모니터 실패: " + e.getMessage());
        }
    }

    @Override
    public Host saveHost(Host host) {
        try {
            hostRepository.save(host);
        } catch (Exception e) {
            System.out.println("호스트 모니터 실패: " + e.getMessage());
           return null;
        }
        return host;
    }
}
