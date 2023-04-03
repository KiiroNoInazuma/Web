package com.web.cashapp.services;

import com.web.cashapp.models.Recipes;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService implements MyServices {
    private int count;
    private final Map<Integer, Recipes> service = new HashMap<>();

    @Override
    public void addRecipe(Recipes recipe) {
        count++;
        service.put(count, recipe);
    }

    @Override
    public Recipes getRecipe(int id) {
        return service.get(id);
    }

    @Override
    public boolean editRecipes(int id, Recipes recipes) {
        if (getRecipe(id) == null) {
            return false;
        } else {
            service.put(id, recipes);
            return true;
        }
    }

    @Override
    public Recipes dellRecipes(int id) {
        if (service.get(id) == null) {
            return null;
        } else {
            return service.remove(id);
        }
    }
@Override
    public Map<Integer,Recipes> allRecipes() {
        return service;
    }

}



