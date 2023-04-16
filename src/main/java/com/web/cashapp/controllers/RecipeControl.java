package com.web.cashapp.controllers;

import com.web.cashapp.models.Ingredients;
import com.web.cashapp.models.Recipes;
import com.web.cashapp.services.FileService;
import com.web.cashapp.services.IngredientService;
import com.web.cashapp.services.MyServices;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;


@AllArgsConstructor
@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты")
@OpenAPIDefinition(info =
@Info(title = "My SWAG", description = "тестики"))

public class RecipeControl {
    private MyServices myServices;
    private IngredientService ingServ;
    private FileService fileService;


    @PostMapping("/create")
    @Operation(summary = "<СОЗДАНИЕ РЕЦЕПТА>", description = "Создается 1 рецепт")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рейцепт добавлен",
            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Recipes.class)))})})
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

    @GetMapping("file/export")
    public ResponseEntity<InputStreamResource> download() throws FileNotFoundException {
        File file = fileService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tsttst.json")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("file/export/{id}")
    public ResponseEntity<InputStreamResource> downloadTemp(@PathVariable int id) throws IOException {
        Path path = myServices.createReport(id);
        if (Files.size(path) != 0) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=trent.txt")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @PostMapping(value = "file/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upload(@RequestParam MultipartFile file) throws IOException {
        fileService.clear();
        File datafile = fileService.getDataFile();
        try {
            FileOutputStream fos = new FileOutputStream(datafile);
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        /*try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
             FileOutputStream fos = new FileOutputStream(datafile);
             BufferedOutputStream bos = new BufferedOutputStream(fos)
        ) {
            byte[] buffer = new byte[1024];
            while (bis.read() > 0) bos.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}

