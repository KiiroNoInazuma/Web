package com.web.cashapp.controllers;

import com.web.cashapp.models.Recipes;
import com.web.cashapp.services.MyServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/recipes")

public class RecipeControl {
    private MyServices myServices;

    @PostMapping("/create")
    public String add(@RequestBody Recipes recipes) {
        myServices.addRecipe(recipes);
        return "Добавлено!";
    }

    @GetMapping("/{id}")
    public Recipes get(@PathVariable("id") int id) {
        return myServices.getRecipe(id);

    }
}
