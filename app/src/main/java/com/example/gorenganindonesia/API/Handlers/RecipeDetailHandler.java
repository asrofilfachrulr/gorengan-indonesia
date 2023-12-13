package com.example.gorenganindonesia.API.Handlers;

import android.app.ProgressDialog;
import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.IngredientsService;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.StepsService;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.Recipe.Ingredients.GetlIngredientsResponse;
import com.example.gorenganindonesia.Model.api.Recipe.Ingredients.IngredientData;
import com.example.gorenganindonesia.Model.api.Recipe.Steps.GetStepsResponse;
import com.example.gorenganindonesia.Model.api.Recipe.Steps.StepData;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Util.CustomToast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailHandler {
    private APIHandlerDTO dto;

    public RecipeDetailHandler(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public APIHandlerDTO getDto() {
        return dto;
    }

    public void setDto(APIHandlerDTO dto) {
        this.dto = dto;
    }


    public void getIngredients(String recipeId, int position) {
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();
        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Memuat Bahan-Bahan...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(IngredientsService.class)
                .getIngredientsByRecipeId(recipeId, token)
                .enqueue(new Callback<GetlIngredientsResponse>() {
                    @Override
                    public void onResponse(Call<GetlIngredientsResponse> call, Response<GetlIngredientsResponse> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            IngredientData[] ingredientData = response.body().getIngredientData();
                            Ingredient[] ingredients = new Ingredient[ingredientData.length];

                            for (int i = 0; i < ingredientData.length; i++) {
                                ingredients[i] = new Ingredient(ingredientData[i].getQty(), ingredientData[i].getUnit(), ingredientData[i].getName());
                            }

                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setIngredients(ingredients, position);
                        } else {
                            try {
                                new CustomToast("Error Memuat Bahan Resep: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Bahan Resep", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetlIngredientsResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Error Memuat Bahan Resep: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }

    public void getSteps(String recipeId, int position) {
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();
        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Memuat Langkah-Langkah...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(StepsService.class)
                .getStepsByRecipeId(recipeId, token)
                .enqueue(new Callback<GetStepsResponse>() {
                    @Override
                    public void onResponse(Call<GetStepsResponse> call, Response<GetStepsResponse> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            String[] steps = new String[response.body().getStepData().length];
                            for (StepData stepData : response.body().getStepData()) {
                                steps[stepData.getNumber() - 1] = stepData.getStep();
                            }

                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setSteps(steps, position);
                        } else {
                            try {
                                new CustomToast("Error Memuat Langkah Resep: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Langkah Resep", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetStepsResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal Memuat Langkah Resep: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }
}
