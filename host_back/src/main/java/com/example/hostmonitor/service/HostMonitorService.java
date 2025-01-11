package com.example.hostmonitor.service;

import com.example.hostmonitor.model.Host;
import com.example.hostmonitor.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface HostMonitorService {

    void monitorHosts();

    Host saveHost (Host host);

}
