package com.example.hostmonitor.controller;

import com.example.hostmonitor.service.PingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HostController {

    @Autowired
    private final PingService pingService;

    @GetMapping("/{host}")
    public ResponseEntity<String> getHostStatus(@PathVariable String host) {
        String status = pingService.pingHost(host);
        return ResponseEntity.ok(status);

    }

}
