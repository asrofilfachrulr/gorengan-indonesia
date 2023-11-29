package com.example.gorenganindonesia.API.Handlers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.IngredientsService;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.StepsService;
import com.example.gorenganindonesia.Model.DAO.APIHandlerDAO;
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
    private APIHandlerDAO dao;

    public RecipeDetailHandler(APIHandlerDAO dao) {
        this.dao = dao;
    }

    public APIHandlerDAO getDao() {
        return dao;
    }

    public void setDao(APIHandlerDAO dao) {
        this.dao = dao;
    }


    public void getIngredients(String recipeId, int position) {
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();
        RetrofitClient
                .getInstance()
                .create(IngredientsService.class)
                .getIngredientsByRecipeId(recipeId, token)
                .enqueue(new Callback<GetlIngredientsResponse>() {
                    @Override
                    public void onResponse(Call<GetlIngredientsResponse> call, Response<GetlIngredientsResponse> response) {
                        if (response.isSuccessful()) {
                            IngredientData[] ingredientData = response.body().getIngredientData();
                            Ingredient[] ingredients = new Ingredient[ingredientData.length];

                            for (int i = 0; i < ingredientData.length; i++) {
                                ingredients[i] = new Ingredient(ingredientData[i].getQty(), ingredientData[i].getUnit(), ingredientData[i].getName());
                            }

                            ((GlobalModel) dao.context.getApplicationContext()).getRecipeViewModel().setIngredients(ingredients, position);
                        } else {
                            try {
                                new CustomToast("Error Memuat Bahan Resep: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Bahan Resep", dao.view, false).show();
                            }
                        }
                        dao.loadingView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<GetlIngredientsResponse> call, Throwable t) {
                        new CustomToast("Error Jaringan: Gagal Mendapat Bahan Resep", dao.view, false).show();
                        dao.loadingView.setVisibility(View.GONE);
                    }
                });
    }

    public void getSteps(String recipeId, int position) {
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
                .getInstance()
                .create(StepsService.class)
                .getStepsByRecipeId(recipeId, token)
                .enqueue(new Callback<GetStepsResponse>() {
                    @Override
                    public void onResponse(Call<GetStepsResponse> call, Response<GetStepsResponse> response) {
                        if (response.isSuccessful()) {
                            String[] steps = new String[response.body().getStepData().length];
                            for (StepData stepData : response.body().getStepData()) {
                                steps[stepData.getNumber() - 1] = stepData.getStep();
                            }

                            ((GlobalModel) dao.context.getApplicationContext()).getRecipeViewModel().setSteps(steps, position);
                        } else {
                            try {
                                new CustomToast("Error Memuat Langkah Resep: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Langkah Resep", dao.view, false).show();
                            }
                        }
                        dao.loadingView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<GetStepsResponse> call, Throwable t) {
                        new CustomToast("Error Jaringan: Gagal Mendapat Langkah Resep", dao.view, false).show();
                        dao.loadingView.setVisibility(View.GONE);
                    }
                });
    }
}
