package com.example.gorenganindonesia.API.Services.recipe.recipeId;

import com.example.gorenganindonesia.Model.api.BasicResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RecipeService {
    @DELETE("/recipe/{recipe_id}")
    Call<BasicResponse> deleteRecipeByRecipeId(
      @Header("Authorization") String token,
      @Path("recipe_id") String recipeId
    );

    @Multipart
    @POST("/recipe")
    Call<BasicResponse> postRecipe(
            @Header("Authorization") String token,
            @Part MultipartBody.Part imageData,
            @Part("json") RequestBody jsonData
    );
}
