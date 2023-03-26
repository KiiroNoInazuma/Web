package com.web.cashapp.services;

import com.web.cashapp.models.Recipes;

public interface MyServices {
    void addRecipe(Recipes recipe);
    Recipes getRecipe(int id);
}
