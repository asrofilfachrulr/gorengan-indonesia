package com.example.gorenganindonesia.API.Services.recipe.recipeId;

import com.example.gorenganindonesia.Model.api.Recipe.Ratings.GetRatingsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RatingsService {
    @GET("/recipe/{recipe_id}/ratings")
    Call<GetRatingsResponse> getRatingsByRecipeId(
            @Path("recipe_id") String recipeId,
            @Header("Authorization") String token,
            @Query("order_by") String queryOrder
    );
}
