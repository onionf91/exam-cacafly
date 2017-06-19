package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class NativeAdService {

    public void dumpAdSourceUrl() {
        log.info("fetch ad info from : " + AD_SOURCE_URL);
    }

    public void fetchAd() {
        String obj = restTemplate.getForObject(AD_SOURCE_URL, String.class);
        log.info(obj);
    }

    @Value("${ad.source.url}")
    private String AD_SOURCE_URL;

    @Autowired
    private RestTemplate restTemplate;
}
