package com.example.gorenganindonesia.API.Services.recipe.recipeId;

import com.example.gorenganindonesia.Model.api.Recipe.GetlIngredientsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IngredientsService {
    @GET("/recipe/{recipe_id}/ingredients")
    Call<GetlIngredientsResponse> getIngredientsByRecipeId(@Path("recipe_id") String recipeId, @Header("Authorization") String token);
}
