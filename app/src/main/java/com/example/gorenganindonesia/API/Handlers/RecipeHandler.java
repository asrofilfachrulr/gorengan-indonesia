package com.example.gorenganindonesia.API.Handlers;

import android.util.Log;
import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.RecipesService;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.Recipes.GetAllRecipesResponse;
import com.example.gorenganindonesia.Model.api.Recipes.RecipeData;
import com.example.gorenganindonesia.Model.data.Category.CategoryData;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Util.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeHandler {
    APIHandlerDTO dto;

    public RecipeHandler(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public APIHandlerDTO getDto() {
        return dto;
    }

    public void setDto(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public void getAllRecipes(){
        dto.loadingView.setVisibility(View.VISIBLE);
        dto.loadingText.setText("Memuat Resep...");
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
                .getInstance()
                .create(RecipesService.class)
                .getAllRecipes(token)
                .enqueue(new Callback<GetAllRecipesResponse>() {
                    @Override
                    public void onResponse(Call<GetAllRecipesResponse> call, Response<GetAllRecipesResponse> response) {
                        int statusCode = response.code();
                        dto.loadingView.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<Recipe> tempRecipes = new ArrayList<>();

                            for (RecipeData recipeData : response.body().getData()) {
                                tempRecipes.add(new Recipe(
                                        recipeData.getId(),
                                        recipeData.getTitle(),
                                        recipeData.getUsername(),
                                        recipeData.getStars(),
                                        recipeData.getCategory(),
                                        recipeData.getMinuteDuration(),
                                        recipeData.getImgUrl(),
                                        recipeData.getDifficulty(),
                                        recipeData.getPortion(),
                                        null,
                                        null,
                                        null
                                ));
                            }

                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setRecipesData(tempRecipes);
                            //TODO: Automate category list from available recipes on backend or database!
                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setCategoriesData(CategoryData.generate());

                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            dto.loadingView.setVisibility(View.GONE);
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("Status code: ", String.valueOf(statusCode));
                                Log.e("Error Response Body", errorBody);

                                JSONObject errorJson = new JSONObject(errorBody);
                                String errorMessage = errorJson.optString("message");

                                new CustomToast("Gagal Mendapatkan Data Resep: " + errorMessage, dto.view).show();

                            } catch (IOException | JSONException e) {
                                Log.e("error", e.toString());
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetAllRecipesResponse> call, Throwable t) {
                        dto.loadingView.setVisibility(View.GONE);

                        new CustomToast("Gagal Mendapatkan Resep: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }
}
