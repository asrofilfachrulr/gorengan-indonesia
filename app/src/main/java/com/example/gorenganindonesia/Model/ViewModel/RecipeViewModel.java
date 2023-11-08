package com.example.gorenganindonesia.Model.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Rating.Rating;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Model.data.Recipe.RecipeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RecipeViewModel extends ViewModel {
    private final MutableLiveData<List<Recipe>> mRecipes;
    private final MutableLiveData<List<String>> mCategories;

    public RecipeViewModel() {
        this.mRecipes = new MutableLiveData<>();
        this.mRecipes.setValue(new ArrayList<>());

        this.mCategories = new MutableLiveData<>();
        this.mCategories.setValue(new ArrayList<>());
    }

    public void setRecipesData(List<Recipe> recipes) {
        this.mRecipes.setValue(recipes);
    }

    public void setCategoriesData(List<String> categories) {
        this.mCategories.setValue(categories);
    }

    public List<Recipe> getMyRecipes(String myName) {
        List<Recipe> myRecipes = new ArrayList<>();

        for (Recipe recipe : mRecipes.getValue()) {
            if (recipe.getAuthorUsername().contains(myName))
                myRecipes.add(recipe);
        }
        return myRecipes;
    }

    public int getRecipePos(String recipeId) {
        List<Recipe> recipes = mRecipes.getValue();

        for (int i = 0; i < recipes.size(); i++)
            if (recipes.get(i).getId().equals(recipeId))
                return i;

        return -1;
    }

    public MutableLiveData<List<Recipe>> getAllRecipes() {
        return mRecipes;
    }

    public Recipe getRecipeById(String recipeId) {
        List<Recipe> recipes = mRecipes.getValue();

        for (Recipe recipe : recipes)
            if (recipe.getId().equals(recipeId))
                return recipe;

        return null;
    }

    public List<Recipe> getRecipesByIds(String[] recipeIds) {
        HashMap<String, Boolean> recipeIdsMap = new HashMap<>();

        for (String recipeId : recipeIds)
            recipeIdsMap.put(recipeId, true);

        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : mRecipes.getValue())
            if (recipeIdsMap.size() == 0) break;
            else if (recipeIdsMap.get(recipe.getId()) != null) {
                recipes.add(recipe);
                recipeIdsMap.remove(recipe.getId());
            }

        return recipes;
    }

    public MutableLiveData<List<String>> getCategories() {
        return mCategories;
    }

    // currently delete means change the author to admin
    public void deleteMyReceipt(Recipe recipe, String myName) {
        List<Recipe> recipes = mRecipes.getValue();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            if (r.getAuthorUsername().equals(myName) && r.getId().equals(recipe.getId())) {
                System.out.println("Removed " + r.getTitle() + " from mReceipts");
                recipes.remove(i);
                r.setAuthorUsername("admin");
                recipes.set(i, r);
            }
        }

        mRecipes.setValue(recipes);
    }

    public void setIngredients(Ingredient[] ingredients, String recipeId) {
        List<Recipe> recipes = mRecipes.getValue();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            if (r.getId().equals(recipeId)) {
                r.setIngredients(ingredients);
                recipes.set(i, r);
            }
        }

        mRecipes.setValue(recipes);
    }

    public void setIngredients(Ingredient[] ingredients, int position) {
        List<Recipe> recipes = mRecipes.getValue();

        Recipe r = recipes.get(position);
        r.setIngredients(ingredients);
        recipes.set(position, r);

        mRecipes.setValue(recipes);
    }

    public void setSteps(String[] steps, String recipeId) {
        List<Recipe> recipes = mRecipes.getValue();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            if (r.getId().equals(recipeId)) {
                r.setSteps(steps);
                recipes.set(i, r);
            }
        }

        mRecipes.setValue(recipes);
    }

    public void setSteps(String[] steps, int position) {
        List<Recipe> recipes = mRecipes.getValue();

        Recipe r = recipes.get(position);
        r.setSteps(steps);
        recipes.set(position, r);

        mRecipes.setValue(recipes);
    }

    public void setRatings(Rating[] ratings, int position) {
        List<Recipe> recipes = mRecipes.getValue();

        Recipe r = recipes.get(position);
        r.setRatings(ratings);
        recipes.set(position, r);

        mRecipes.setValue(recipes);
    }

    public void setIngredientsAndSteps(Ingredient[] ingredients, String[] steps, String recipeId) {
        List<Recipe> recipes = mRecipes.getValue();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            if (r.getId().equals(recipeId)) {
                r.setIngredients(ingredients);
                r.setSteps(steps);
                recipes.set(i, r);
            }
        }

        mRecipes.setValue(recipes);
    }

    public void addReceipt(Recipe recipe) {
        List<Recipe> recipes = mRecipes.getValue();
        recipes.add(recipe);

        mRecipes.setValue(recipes);
    }
}
