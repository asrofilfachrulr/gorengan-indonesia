package com.example.gorenganindonesia.API.Services.recipe.recipeId;

import com.example.gorenganindonesia.Model.api.Recipe.GetStepsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface StepsService {
    @GET("/recipe/{recipe_id}/steps")
    Call<GetStepsResponse> getStepsByRecipeId(@Path("recipe_id") String recipeId, @Header("Authorization") String token);
}
