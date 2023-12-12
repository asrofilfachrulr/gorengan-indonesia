package com.example.gorenganindonesia.API.Handlers;

import android.app.ProgressDialog;
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
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();
        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Memuat Daftar Favorit...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitClient
            .getInstance()
            .create(FavouritesService.class)
            .getFavourites(token)
            .enqueue(new Callback<GetFavouritesResponse>() {
                @Override
                public void onResponse(Call<GetFavouritesResponse> call, Response<GetFavouritesResponse> response) {
                    progressDialog.dismiss();
                    if(response.isSuccessful()){
                        String[] recipeids = response.body().getFavourites();

                        List<Recipe> favRecipes = ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().getRecipesByIds(recipeids);
                        ((GlobalModel) dto.context.getApplicationContext()).getFavouriteViewModel().setFavourites(favRecipes);
                    } else {
                        int statusCode = response.code();
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
                    progressDialog.dismiss();
                    new CustomToast("Gagal Mendapatkan Daftar Favorit: Koneksi Gagal", dto.view, false).show();
                }
            });
    }

    public void postFavourite(String recipeId){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Menambahkan Favorit");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(FavouriteService.class)
                .postFavouriteByRecipeId(recipeId, token)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal Menambahkan Favorit: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Menambahkan Favorit", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal Menambahkan Favorit: Koneksi Gagal", dto.view, false).show();

                    }
                });
    }

    public void deleteFavourite(String recipeId){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager().getJwtHeaderValue();

        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Menghapus Resep dari Favorit");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(FavouriteService.class)
                .deleteFavouriteByRecipeId(recipeId, token)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){

                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal Menghapus Favorit: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Menghapus Favorit", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal Menghapus Favorit: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }
}
