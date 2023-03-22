package com.web.cashapp.services;

import com.web.cashapp.models.Recipes;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService implements MyServices {
    private int count;
    private Map<Integer, Recipes> service = new HashMap<>();

    @Override
    public void addRecipe(Recipes recipe) {
        count++;
        service.put(count, recipe);
    }

    @Override
    public Recipes getRecipe(int id) {
        return service.get(id);
    }

}
