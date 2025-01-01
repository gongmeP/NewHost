package com.example.hostmonitor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hostname;
    private String ipAddress;
    private String status;  // UP, DOWN
    private Double latency; // 응답 시간 (ms)
    private LocalDateTime lastChecked; // 마지막 체크 시간
    private LocalDateTime date = LocalDateTime.now(); // 저장 시간
}