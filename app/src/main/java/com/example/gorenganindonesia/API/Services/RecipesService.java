package com.example.gorenganindonesia.API.Services;

import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.Recipes.GetAllRecipesResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RecipesService {
    @GET("/recipes")
    Call<GetAllRecipesResponse> getAllRecipes(@Header("Authorization") String token);
}
