package com.example.demo;

import com.example.demo.service.NativeAdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner init() {
        return (args) -> {
            nativeAdService.dumpAdSourceUrl();
            nativeAdService.fetchAd();
            nativeAdService.parseAd();
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private NativeAdService nativeAdService;
}
