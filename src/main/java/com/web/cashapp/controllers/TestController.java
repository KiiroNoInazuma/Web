package com.web.cashapp.controllers;

import com.web.cashapp.models.CashAppModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Tag(name = "Мой тест", description = "<проверочка>")
public class TestController {
    @GetMapping("/")
    public String test() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public CashAppModel info() {
        return new CashAppModel("John Rey", "CashApp", LocalDate.of(2023, 3, 5), "Мой проект");
    }
}
