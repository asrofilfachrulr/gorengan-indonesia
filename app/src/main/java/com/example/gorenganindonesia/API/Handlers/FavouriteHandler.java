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
    private APIHandlerDTO dto;

    public FavouriteHandler(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public APIHandlerDTO getDto() {
        return dto;
    }

    public void setDto(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public void getFavourites(){
        dto.loadingText.setText("Memuat Favorit...");
        dto.loadingView.setVisibility(View.VISIBLE);
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
            .getInstance()
            .create(FavouritesService.class)
            .getFavourites(token)
            .enqueue(new Callback<GetFavouritesResponse>() {
                @Override
                public void onResponse(Call<GetFavouritesResponse> call, Response<GetFavouritesResponse> response) {
                    if(response.isSuccessful()){
                        String[] recipeids = response.body().getFavourites();

                        List<Recipe> favRecipes = ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().getRecipesByIds(recipeids);
                        ((GlobalModel) dto.context.getApplicationContext()).getFavouriteViewModel().setFavourites(favRecipes);
                        dto.loadingView.setVisibility(View.GONE);
                    } else {
                        int statusCode = response.code();
                        dto.loadingView.setVisibility(View.GONE);
                        try {
                            if(statusCode != 404)
                                new CustomToast("Gagal Mendapatkan Daftar Favorit: " + response.errorBody().string(), dto.view, false).show();
                        } catch (IOException e) {
                            new CustomToast("Gagal Mengolah Daftar Favorit", dto.view, false).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetFavouritesResponse> call, Throwable t) {
                    dto.loadingView.setVisibility(View.GONE);
                    new CustomToast("Gagal Mendapatkan Daftar Favorit: Koneksi Gagal", dto.view, false).show();
                }
            });
    }

    public void postFavourite(String recipeId){
        dto.loadingText.setText("Menambahkan Favorit...");
        dto.loadingView.setVisibility(View.VISIBLE);
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
                .getInstance()
                .create(FavouriteService.class)
                .postFavouriteByRecipeId(recipeId, token)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if(response.isSuccessful()){
                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            dto.loadingView.setVisibility(View.GONE);
                            try {
                                new CustomToast("Gagal Menambahkan Favorit: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Menambahkan Favorit", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dto.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Menambahkan Favorit: Koneksi Gagal", dto.view, false).show();

                    }
                });
    }

    public void deleteFavourite(String recipeId){
        dto.loadingText.setText("Menghapus Favorit...");
        dto.loadingView.setVisibility(View.VISIBLE);
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        RetrofitClient
                .getInstance()
                .create(FavouriteService.class)
                .deleteFavouriteByRecipeId(recipeId, token)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if(response.isSuccessful()){

                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            dto.loadingView.setVisibility(View.GONE);
                            try {
                                new CustomToast("Gagal Menghapus Favorit: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Menghapus Favorit", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dto.loadingView.setVisibility(View.GONE);
                        new CustomToast("Gagal Menghapus Favorit: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }
}
