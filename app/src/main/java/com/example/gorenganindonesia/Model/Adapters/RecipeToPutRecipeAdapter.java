package com.example.gorenganindonesia.Model.Adapters;

import com.example.gorenganindonesia.Model.api.Recipe.IngredientData;
import com.example.gorenganindonesia.Model.api.Recipe.PostRecipeRequest;
import com.example.gorenganindonesia.Model.api.Recipe.PutRecipeRequest;
import com.example.gorenganindonesia.Model.api.Recipe.StepData;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;

public class RecipeToPutRecipeAdapter {
    Recipe recipe;

    public RecipeToPutRecipeAdapter(Recipe recipe){
        this.recipe = recipe;
    }

    public PutRecipeRequest convert(){
        PutRecipeRequest putRecipeRequest = new PutRecipeRequest();

        putRecipeRequest.setTitle(recipe.getTitle());
        putRecipeRequest.setCategory(recipe.getCategory());
        putRecipeRequest.setMinuteDuration(recipe.getMinuteDuration());
        putRecipeRequest.setDifficulty(recipe.getDifficulty());
        putRecipeRequest.setPortion(recipe.getPortion());

        Ingredient[] ingredients = recipe.getIngredients();
        IngredientData[] ingredientsData = new IngredientData[ingredients.length];
        for(int i = 0; i < ingredientsData.length; i++){
            Ingredient ingredient = ingredients[i];
            ingredientsData[i] = new IngredientData(ingredient.getQty(), ingredient.getUnit(), ingredient.getName());
        }
        putRecipeRequest.setIngredients(ingredientsData);

        String[] steps = recipe.getSteps();
        StepData[] stepsData = new StepData[steps.length];
        for(int i = 0; i < stepsData.length; i++){
            stepsData[i] = new StepData(i+1, steps[i]);
        }
        putRecipeRequest.setSteps(stepsData);

        return putRecipeRequest;
    }
}
