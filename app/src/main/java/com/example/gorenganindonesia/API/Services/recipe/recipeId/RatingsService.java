package com.example.gorenganindonesia.API.Services.recipe.recipeId;

import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.GetRatingsResponse;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.PostRatingRequest;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.PutRatingRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RatingsService {
    @GET("/recipe/{recipe_id}/ratings")
    Call<GetRatingsResponse> getRatingsByRecipeId(
            @Path("recipe_id") String recipeId,
            @Header("Authorization") String token,
            @Query("order_by") String queryOrder
    );

    @POST("/recipe/{recipe_id}/rating")
    Call<BasicResponse> postRating(
            @Path("recipe_id") String recipeId,
            @Header("Authorization") String token,
            @Body PostRatingRequest postRatingRequest
    );

    @PUT("/recipe/{recipe_id}/rating")
    Call<BasicResponse> putRatingByRecipeId(
            @Path("recipe_id") String recipeId,
            @Header("Authorization") String token,
            @Body PutRatingRequest putRatingRequest
    );

    @DELETE("/recipe/{recipe_id}/rating")
    Call<BasicResponse> deleteRatingByRecipeId(
            @Path("recipe_id") String recipeId,
            @Header("Authorization") String token
    );
}
