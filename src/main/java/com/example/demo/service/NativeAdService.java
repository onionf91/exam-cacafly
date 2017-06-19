package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NativeAdService {

    public void dumpAdSourceUrl() {
        log.info("fetch ad info from : " + AD_SOURCE_URL);
    }

    @Value("${ad.source.url}")
    private String AD_SOURCE_URL;
}
