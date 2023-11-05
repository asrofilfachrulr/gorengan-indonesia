package com.example.gorenganindonesia.API;

import com.example.gorenganindonesia.Model.api.Recipes.GetAllRecipesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RecipesService {
    @GET("/recipes")
    Call<GetAllRecipesResponse> getAllRecipes(@Header("Authorization") String token);
}
