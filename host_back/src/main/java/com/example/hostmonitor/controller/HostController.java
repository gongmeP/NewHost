package com.example.hostmonitor.controller;

import com.example.hostmonitor.model.Host;
import com.example.hostmonitor.repository.HostRepository;
import com.example.hostmonitor.service.PingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class HostController {

    @Autowired
    private final PingService pingService;

    @Autowired
    private HostRepository hostRepository;


    @GetMapping("/api/hosts")
    public ResponseEntity<?> getAllHosts() {
        List<Host> hosts = hostRepository.findAll(); // DB에서 모든 호스트 정보 가져오기

        if (hosts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 내용이 없으면 204 상태 코드 반환
        }

        return new ResponseEntity<>(hosts, HttpStatus.OK);  // 200 OK 상태 코드와 함께 리스트 반환
    }

    @PostMapping("/api/hosts/add")
    public ResponseEntity<Host> saveHost(@RequestBody Host host) {
        Host hosts = hostRepository.save(host); // 정보 저장
        return new ResponseEntity<>(hosts, HttpStatus.OK);
    }


}
