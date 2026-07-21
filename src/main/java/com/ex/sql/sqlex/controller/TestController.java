package com.ex.sql.sqlex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-get")
    public String testGet() {
        return "Hello";
    }

}
