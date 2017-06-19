package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 60000)
    public void task() {
        nativeAdService.fetchAd();
        nativeAdService.parseAd();
    }

    @Autowired
    private NativeAdService nativeAdService;
}
