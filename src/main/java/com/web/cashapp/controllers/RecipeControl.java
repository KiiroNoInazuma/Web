package com.web.cashapp.controllers;

import com.web.cashapp.models.Ingredients;
import com.web.cashapp.models.Recipes;
import com.web.cashapp.services.IngredientService;
import com.web.cashapp.services.MyServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


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
    public ResponseEntity<Recipes> get(@PathVariable int dd) {
        if (myServices.getRecipe(dd) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(myServices.getRecipe(dd));
  /*  public Recipes get(@PathVariable int dd) {
        return myServices.getRecipe(dd);
    }*/
    }

    @PutMapping("/edit/{id}")
    public String editRecipes(@PathVariable int id, @RequestBody Recipes recipes) {
        boolean check = myServices.editRecipes(id, recipes);
        if (check) {
            return "Рецепт " + recipes.name() + " изменен";
        } else {
            return "Нет рецепта для редактирования";
        }
    }

    @PostMapping("del/{id}")
    public String removeRecipes(@PathVariable int id) {
        Recipes rec = myServices.dellRecipes(id);
        if (rec == null) {
            return "Рецепта не существует";
        } else {
            return rec.name() + " удалено";
        }
    }

    @GetMapping("all")
    public ResponseEntity<Collection<Recipes>> all(@RequestParam String all) {
        if (!all.equals("test")) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(myServices.allRecipes().values());
        }
    }
}

