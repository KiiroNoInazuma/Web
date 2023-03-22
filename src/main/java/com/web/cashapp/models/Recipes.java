package com.web.cashapp.models;

import java.util.List;

public record Recipes(String name, int cookingTime, List<Ingredients> ingredients, List<String>manual) {
}
