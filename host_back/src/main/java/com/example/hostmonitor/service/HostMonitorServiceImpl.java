package com.example.hostmonitor.service;

import com.example.hostmonitor.model.Host;
import com.example.hostmonitor.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.rmi.UnknownHostException;
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
    @Scheduled(fixedRate = 10000) // 10초마다 실행
    public void monitorHosts() {
        try {
            List<Host> hosts = hostRepository.findAll();
            for (Host host : hosts) {
                String statusAndLatency = null;
                try {
                    statusAndLatency = pingService.pingHost(host.getDomain());
                } catch (Exception e) {
                    // 다른 예외 발생 시 URL 오류 처리
                    statusAndLatency = "URL 오류, 응답시간: -1 ms";
                    System.out.println("예외 발생: " + e.getMessage() + " / 도메인: " + host.getDomain());
                }

                // host에 http 없으면 추가
                if (!host.getDomain().startsWith("http://") && !host.getDomain().startsWith("https://")) {
                    host.setDomain("https://" + host.getDomain());
                }

                // 리턴 값 상태 분리
                String[] statusAndLatencyArray = statusAndLatency.split(", ");
                String status = statusAndLatencyArray[0];
                Long latency = -1L;  // 기본값을 -1로 설정 (URL 오류일 경우)
                if (!status.equals("URL 오류")) {
                    try {
                        latency = Long.parseLong(statusAndLatencyArray[1].split(": ")[1].replace(" ms", ""));
                    } catch (Exception e) {
                        System.out.println("응답 시간 파싱 실패: " + e.getMessage());
                    }
                }

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
