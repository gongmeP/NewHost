package com.example.hostmonitor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hostname;
    private String domain;
    private String status;  // UP, DOWN
    private Long latency; // 응답 시간 (ms)
    private LocalDateTime lastchecked; // 마지막 체크 시간
    private LocalDateTime date = LocalDateTime.now(); // 저장 시간
}
