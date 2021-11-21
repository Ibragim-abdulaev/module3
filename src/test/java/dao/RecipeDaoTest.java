package dao;

import models.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeDaoTest {
    private static RecipeDao recipeDao;

    @BeforeAll
    static void beforeAll() {
        recipeDao = new RecipeDao();
        Recipe recipe1 = new Recipe("Take Aspirin 4 tablets daily");
        Recipe recipe2 = new Recipe("Take Trombonete 1 tablets daily");
        recipeDao.saveRecipe(recipe1);
        recipeDao.saveRecipe(recipe2);
    }

    @Test
    void saveRecipe() {
        Recipe recipe3 = new Recipe("Amoxil one dropper per day");
        recipeDao.saveRecipe(recipe3);
        Assertions.assertEquals(recipe3.getId(), recipeDao.getRecipeById(recipe3.getId()).getId());
    }

    @Test
    void updateRecipe() {
        Recipe recipe = recipeDao.getRecipeById(1);
        recipe.setTipsForUse("Aspirin 1 tablets daily");
        recipeDao.updateRecipe(recipe);
        Assertions.assertEquals(recipe.getTipsForUse(), recipeDao.getRecipeById(recipe.getId()).getTipsForUse());
    }

    @Test
    void getRecipeById() {
        Recipe recipe = new Recipe("Siofor one tablets per day");
        recipeDao.saveRecipe(recipe);
        Recipe recipes = recipeDao.getRecipeById(recipe.getId());
        Assertions.assertEquals(recipes.getTipsForUse(), recipe.getTipsForUse());
    }

    @Test
    void getAllRecipe() {
        Assertions.assertEquals(3, recipeDao.getAllRecipe().size());
    }

    @Test
    void deleteRecipe() {
        Recipe recipe = new Recipe();
        recipeDao.saveRecipe(recipe);
        recipeDao.deleteRecipe(recipe.getId());
        assertNull(recipeDao.getRecipeById(recipe.getId()));
    }
}