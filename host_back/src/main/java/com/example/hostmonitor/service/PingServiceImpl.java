package com.example.hostmonitor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class PingServiceImpl implements PingService {

    @Override
    public String pingHost(String domain) {
        long startTime = System.currentTimeMillis();  // 요청 시작 시간 기록
        try {
            URL url = new URL(domain);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 타임아웃 5초
            connection.setReadTimeout(5000);    // 타임아웃 5초

            int responseCode = connection.getResponseCode();  // 응답 코드 확인

            long endTime = System.currentTimeMillis();  // 응답을 받은 시간 기록
            long latency = endTime - startTime;  // 레이턴시 계산

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "UP, 응답시간: " + latency + " ms";
            } else {
                return "DOWN, 응답시간: " + latency + " ms";
            }

        } catch (Exception e) {
            return "DOWN (URL 오류!)";
        }

    }
}
