package com.example.hostmonitor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class PingServiceImpl implements PingService {

    @Override
    public String pingHost(String host) {
        String result;
        try{
            ProcessBuilder builder = new ProcessBuilder("ping", "-c", "1", host);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();
            result = output.toString();
        } catch (Exception e){

            result = "PingServiceError : " + e.getMessage();

        }

        return null;
    }
}
