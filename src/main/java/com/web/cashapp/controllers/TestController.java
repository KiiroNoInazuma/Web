package com.web.cashapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/ha")
    public String test(@RequestParam String x) {
        return "TEST!!! " + x;
    }
}
