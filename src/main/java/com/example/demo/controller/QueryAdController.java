package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QueryAdController {

    @GetMapping("/query")
    String queryByTitle(@RequestParam String title) {
        log.info("query by title : " + title);
        return "hello world";
    }
}
