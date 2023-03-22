package com.web.cashapp.services;

import com.web.cashapp.models.Recipes;

public interface MyServices {
    public void addRecipe(Recipes recipe);
    public Recipes getRecipe(int id);
}
