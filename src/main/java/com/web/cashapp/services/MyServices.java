package com.web.cashapp.services;

import com.web.cashapp.models.Recipes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface MyServices {
    void addRecipe(Recipes recipe);

    Recipes getRecipe(int id);

    boolean editRecipes(int id, Recipes recipes);

    Recipes dellRecipes(int id);

    Map<Integer, Recipes> allRecipes();

    Path createReport(int id) throws IOException;
}
