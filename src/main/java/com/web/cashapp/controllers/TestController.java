package com.web.cashapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String test() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String info() {
        String myName = "John Rey; ";
        String nameProject = "CashApp; ";
        String date = "05.03.2023 15:06; ";
        String description = "Мой проект;";

        return myName+nameProject+date+description;
    }
}
