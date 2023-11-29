package com.example.gorenganindonesia.API.Handlers;

import android.util.Log;
import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.RecipesService;
import com.example.gorenganindonesia.Model.DAO.APIHandlerDAO;
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
    APIHandlerDAO dao;

    public RecipeHandler(APIHandlerDAO dao) {
        this.dao = dao;
    }

    public APIHandlerDAO getDao() {
        return dao;
    }

    public void setDao(APIHandlerDAO dao) {
        this.dao = dao;
    }

    public void getAllRecipes(){
        dao.loadingView.setVisibility(View.VISIBLE);
        dao.loadingText.setText("Memuat Resep...");
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
                .getInstance()
                .create(RecipesService.class)
                .getAllRecipes(token)
                .enqueue(new Callback<GetAllRecipesResponse>() {
                    @Override
                    public void onResponse(Call<GetAllRecipesResponse> call, Response<GetAllRecipesResponse> response) {
                        int statusCode = response.code();
                        dao.loadingView.setVisibility(View.GONE);

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

                            ((GlobalModel) dao.context.getApplicationContext()).getRecipeViewModel().setRecipesData(tempRecipes);
                            //TODO: Automate category list from available recipes on backend or database!
                            ((GlobalModel) dao.context.getApplicationContext()).getRecipeViewModel().setCategoriesData(CategoryData.generate());

                            if(dao.callback != null)
                                dao.callback.run();
                        } else {
                            dao.loadingView.setVisibility(View.GONE);
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("Status code: ", String.valueOf(statusCode));
                                Log.e("Error Response Body", errorBody);

                                JSONObject errorJson = new JSONObject(errorBody);
                                String errorMessage = errorJson.optString("message");

                                new CustomToast("Error: " + errorMessage, dao.view).show();

                            } catch (IOException | JSONException e) {
                                Log.e("error", e.toString());
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetAllRecipesResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);

                        new CustomToast("Error Jaringan: Gagal Mendapatkan Resep", dao.view, false).show();
                    }
                });
    }
}
