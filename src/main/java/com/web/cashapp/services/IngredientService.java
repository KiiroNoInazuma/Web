package com.web.cashapp.services;


import com.web.cashapp.models.Ingredients;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {
    List<Ingredients> ingredientArr = new ArrayList<>();

    public void addIngredient(Ingredients ingredient) {
        ingredientArr.add(ingredient);
    }
}
