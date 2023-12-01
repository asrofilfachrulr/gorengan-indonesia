package com.example.gorenganindonesia.API.Handlers;

import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.FavouritesService;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.FavouriteService;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.GetFavouritesResponse;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Util.CustomToast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteHandler {
    private APIHandlerDTO dao;

    public FavouriteHandler(APIHandlerDTO dao) {
        this.dao = dao;
    }

    public APIHandlerDTO getDao() {
        return dao;
    }

    public void setDao(APIHandlerDTO dao) {
        this.dao = dao;
    }

    public void getFavourites(){
        dao.loadingText.setText("Memuat Favorit...");
        dao.loadingView.setVisibility(View.VISIBLE);
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
            .getInstance()
            .create(FavouritesService.class)
            .getFavourites(token)
            .enqueue(new Callback<GetFavouritesResponse>() {
                @Override
                public void onResponse(Call<GetFavouritesResponse> call, Response<GetFavouritesResponse> response) {
                    if(response.isSuccessful()){
                        String[] recipeids = response.body().getFavourites();

                        List<Recipe> favRecipes = ((GlobalModel) dao.context.getApplicationContext()).getRecipeViewModel().getRecipesByIds(recipeids);
                        ((GlobalModel) dao.context.getApplicationContext()).getFavouriteViewModel().setFavourites(favRecipes);
                        dao.loadingView.setVisibility(View.GONE);
                    } else {
                        int statusCode = response.code();
                        dao.loadingView.setVisibility(View.GONE);
                        try {
                            if(statusCode != 404)
                                new CustomToast("Gagal Mendapatkan Daftar Favorit: " + response.errorBody().string(), dao.view, false).show();
                        } catch (IOException e) {
                            new CustomToast("Gagal Mengolah Daftar Favorit", dao.view, false).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetFavouritesResponse> call, Throwable t) {
                    dao.loadingView.setVisibility(View.GONE);
                    new CustomToast("Gagal Mendapatkan Daftar Favorit: Koneksi Gagal", dao.view, false).show();
                }
            });
    }

    public void postFavourite(String recipeId){
        dao.loadingText.setText("Menambahkan Favorit...");
        dao.loadingView.setVisibility(View.VISIBLE);
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
                .getInstance()
                .create(FavouriteService.class)
                .postFavouriteByRecipeId(recipeId, token)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if(response.isSuccessful()){
                            if(dao.callback != null)
                                dao.callback.run();
                        } else {
                            dao.loadingView.setVisibility(View.GONE);
                            try {
                                new CustomToast("Gagal Menambahkan Favorit: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Menambahkan Favorit", dao.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Menambahkan Favorit: Koneksi Gagal", dao.view, false).show();

                    }
                });
    }

    public void deleteFavourite(String recipeId){
        dao.loadingText.setText("Menghapus Favorit...");
        dao.loadingView.setVisibility(View.VISIBLE);
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
                .getInstance()
                .create(FavouriteService.class)
                .deleteFavouriteByRecipeId(recipeId, token)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if(response.isSuccessful()){

                            if(dao.callback != null)
                                dao.callback.run();
                        } else {
                            dao.loadingView.setVisibility(View.GONE);
                            try {
                                new CustomToast("Gagal Menghapus Favorit: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Menghapus Favorit", dao.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Menghapus Favorit: Koneksi Gagal", dao.view, false).show();
                    }
                });
    }
}
