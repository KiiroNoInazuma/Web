package com.web.cashapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.cashapp.models.Recipes;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class RecipeService implements MyServices {
    private int count;
    private Map<Integer, Recipes> service = new HashMap<>();
    private final MyFile file;

    public RecipeService(MyFile file) {
        this.file = file;
    }

    @PostConstruct
    private void init() {
        readToFile();
    }

    @Override
    public void addRecipe(Recipes recipe) {
        count++;
        service.put(count, recipe);
        saveToFile();
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
            saveToFile();
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
    public Map<Integer, Recipes> allRecipes() {
        return service;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(service);
            file.save(json);
        } catch (JsonProcessingException j) {
            throw new RuntimeException(j);
        }
    }

    private void readToFile() {
        String json = file.read();
        try {
            service = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipes>>() {
            });
        } catch (JsonProcessingException j) {
            throw new RuntimeException(j);

        }
    }

    @Override
    public Path createReport(int id) throws IOException {
        Recipes recipes = service.get(id);
        Path path = file.createTemp("report");
        Writer writer = Files.newBufferedWriter(path);
        writer.write(recipes.name());
        path.toFile().deleteOnExit();
        writer.close();
        return path;
    }

}



