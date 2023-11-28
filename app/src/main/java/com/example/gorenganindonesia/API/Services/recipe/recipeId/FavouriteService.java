package com.example.gorenganindonesia.API.Services.recipe.recipeId;

import com.example.gorenganindonesia.Model.api.BasicResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavouriteService {
    @POST("/recipe/{recipe_id}/favourite")
    Call<BasicResponse> postFavouriteByRecipeId(
            @Path("recipe_id") String recipeId,
            @Header("Authorization") String token
    );

    @DELETE("/recipe/{recipe_id}/favourite")
    Call<BasicResponse> deleteFavouriteByRecipeId(
            @Path("recipe_id") String recipeId,
            @Header("Authorizaton") String token
    );
}
