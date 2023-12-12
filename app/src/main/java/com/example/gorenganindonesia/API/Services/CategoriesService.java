package com.example.gorenganindonesia.API.Services;

import com.example.gorenganindonesia.Model.api.GetCategoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {
    @GET("/categories")
    Call<GetCategoriesResponse> getCategories();
}
