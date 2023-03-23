package com.web.cashapp.controllers;

import com.web.cashapp.models.Ingredients;
import com.web.cashapp.models.Recipes;
import com.web.cashapp.services.IngredientService;
import com.web.cashapp.services.MyServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/recipes")

public class RecipeControl {
    private MyServices myServices;
    private IngredientService ingServ;


    @PostMapping("/create")
    public String add(@RequestBody Recipes recipes) {
        myServices.addRecipe(recipes);
        return "Рецепт добавлен!";
    }

    @PostMapping("/create/ingredient")
    public String addIng(@RequestBody Ingredients ingredients) {
        ingServ.addIngredient(ingredients);
        return "Ингредиент добавлен!";
    }


    @GetMapping("{dd}")
    public Recipes get(@PathVariable("dd") int dd) {
        return myServices.getRecipe(dd);
    }
}

