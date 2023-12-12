package com.example.gorenganindonesia.API.Handlers;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.RecipesService;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.RecipeService;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.Recipes.GetAllRecipesResponse;
import com.example.gorenganindonesia.Model.api.Recipes.RecipeData;
import com.example.gorenganindonesia.Model.data.Category.CategoryData;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Util.ToastUseCase;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();
        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Memuat Resep...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(RecipesService.class)
                .getAllRecipes(token)
                .enqueue(new Callback<GetAllRecipesResponse>() {
                    @Override
                    public void onResponse(Call<GetAllRecipesResponse> call, Response<GetAllRecipesResponse> response) {
                        int statusCode = response.code();
                        progressDialog.dismiss();

                        if (response.isSuccessful()) {
                            List<Recipe> tempRecipes = new ArrayList<>();

                            for (RecipeData recipeData : response.body().getData()) {
                                tempRecipes.add(new Recipe(
                                        recipeData.getId(),
                                        recipeData.getTitle(),
                                        recipeData.getUsername(),
                                        recipeData.getStars(),
                                        WordUtils.capitalizeFully(recipeData.getCategory()),
                                        recipeData.getMinuteDuration(),
                                        recipeData.getImgUrl(),
                                        WordUtils.capitalizeFully(recipeData.getDifficulty()),
                                        recipeData.getPortion(),
                                        null,
                                        null,
                                        null
                                ));
                            }

                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setRecipesData(tempRecipes);
                            //TODO: Automate category list from available recipes on backend or database!
//                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setCategoriesData(CategoryData.generate());

                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            progressDialog.dismiss();
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
                        progressDialog.dismiss();

                        new CustomToast("Gagal Mendapatkan Resep: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }

    public void postRecipe(MultipartBody.Part imagePart, RequestBody jsonData){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();
        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Membuat Resep");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(RecipeService.class)
                .postRecipe(token, imagePart, jsonData)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            try {
                                if(dto.negativeCallback != null)
                                    dto.negativeCallback.run();
                                Log.e("Error", response.errorBody().string());
                            } catch (IOException e) {
                                ToastUseCase.showMessage(dto.view,"Gagal Membuat Resep");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        progressDialog.dismiss();

                        ToastUseCase.showMessage(dto.view,"Gagal Membuat Resep: Koneksi Gagal");
                    }
                });
    }

    public void deleteRecipe(String recipeId){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();
        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Menghapus Resep");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(RecipeService.class)
                .deleteRecipeByRecipeId(token, recipeId)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            if(dto.callback != null) dto.callback.run();
                        } else {
                            try {
                                ToastUseCase.showMessage(dto.view,"Gagal Menghapus Resep: " + response.errorBody().string());
                                Log.e("Error", response.errorBody().string());
                            } catch (IOException e) {
                                ToastUseCase.showMessage(dto.view,"Gagal Menghapus Resep");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        ToastUseCase.showMessage(dto.view,"Gagal Membuat Resep: Koneksi Gagal");
                    }
                });
    }
}
