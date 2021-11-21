package services;

import com.sun.istack.NotNull;
import dao.RecipeDao;
import models.Medications;
import models.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeService {
    RecipeDao recipeDao = new RecipeDao();

    public void saveRecipe(Recipe recipe) {
        List<Recipe> recipes = recipeDao.getAllRecipe();
        List<String> recipeRec;
        if (!recipes.isEmpty()) {
            recipeRec = recipes.stream()
                    .map(Recipe::getTipsForUse)
                    .collect(Collectors.toList());
            if (!recipeRec.contains(recipe.getTipsForUse())) {
                recipeDao.saveRecipe(recipe);
            } else {
                System.out.println("This recipe already exist");
            }
        } else {
            recipeDao.saveRecipe(recipe);
        }
    }
}
