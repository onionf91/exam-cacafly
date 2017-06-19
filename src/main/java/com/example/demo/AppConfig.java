package com.example.demo;

import com.example.demo.service.NativeAdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner init() {
        return (args) -> {
            nativeAdService.dumpAdSourceUrl();
        };
    }

    @Autowired
    private NativeAdService nativeAdService;
}
