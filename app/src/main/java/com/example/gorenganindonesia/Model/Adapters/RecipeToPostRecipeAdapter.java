package com.example.gorenganindonesia.Model.Adapters;

import com.example.gorenganindonesia.Model.api.Recipe.IngredientData;
import com.example.gorenganindonesia.Model.api.Recipe.PostRecipeRequest;
import com.example.gorenganindonesia.Model.api.Recipe.StepData;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Model.data.Step.Step;

public class RecipeToPostRecipeAdapter {
    Recipe recipe;

    public RecipeToPostRecipeAdapter(Recipe recipe){
        this.recipe = recipe;
    }

    public PostRecipeRequest convert(){
        PostRecipeRequest postRecipeRequest = new PostRecipeRequest();

        postRecipeRequest.setTitle(recipe.getTitle());
        postRecipeRequest.setCategory(recipe.getCategory());
        postRecipeRequest.setMinuteDuration(recipe.getMinuteDuration());
        postRecipeRequest.setDifficulty(recipe.getDifficulty());
        postRecipeRequest.setPortion(recipe.getPortion());

        Ingredient[] ingredients = recipe.getIngredients();
        IngredientData[] ingredientsData = new IngredientData[ingredients.length];
        for(int i = 0; i < ingredientsData.length; i++){
            Ingredient ingredient = ingredients[i];
            ingredientsData[i] = new IngredientData(ingredient.getQty(), ingredient.getUnit(), ingredient.getName());
        }
        postRecipeRequest.setIngredients(ingredientsData);

        String[] steps = recipe.getSteps();
        StepData[] stepsData = new StepData[steps.length];
        for(int i = 0; i < stepsData.length; i++){
            stepsData[i] = new StepData(i+1, steps[i]);
        }
        postRecipeRequest.setSteps(stepsData);

        return postRecipeRequest;
    }
}
