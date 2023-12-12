package com.example.gorenganindonesia.API.Handlers;

import android.app.ProgressDialog;
import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.RatingsService;
import com.example.gorenganindonesia.Activity.RatingActivity;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.BasicResponse;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.GetRatingsResponse;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.PostRatingRequest;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.PutRatingRequest;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.RatingData;
import com.example.gorenganindonesia.Model.data.Rating.Rating;
import com.example.gorenganindonesia.Util.CustomToast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingHandler {
    APIHandlerDTO dto;
    RatingsService ratingsService;

    public RatingHandler(APIHandlerDTO dto) {
        this.dto = dto;

        this.ratingsService = RetrofitClient.getInstance().create(RatingsService.class);
    }

    public APIHandlerDTO getDto() {
        return dto;
    }

    public void setDto(APIHandlerDTO dto) {
        this.dto = dto;
    }

    public void getRatings(String recipeId, String queryOrder, int index, RatingActivity activity){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Memuat Ulasan...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ratingsService
                .getRatingsByRecipeId(recipeId, token, queryOrder)
                .enqueue(new Callback<GetRatingsResponse>() {
                    @Override
                    public void onResponse(Call<GetRatingsResponse> call, Response<GetRatingsResponse> response) {
                        progressDialog.dismiss();

                        if (response.isSuccessful()) {
                            RatingData[] ratingData = response.body().getRatingData();
                            final int sz = ratingData.length;
                            Rating[] ratings = new Rating[sz];

                            for (int i = 0; i < sz; i++) {
                                Rating rating = new Rating(
                                        ratingData[i].getComment(),
                                        ratingData[i].getDate(),
                                        ratingData[i].getUsername(),
                                        ratingData[i].getStars(),
                                        ratingData[i].getImageUrl(),
                                        ratingData[i].getLikeCount()
                                );

                                ratings[i] = rating;
                            }

                            float starAvg = sz > 0 ? response.body().getExtra().getStarAvg() : 0;
                            activity.starAvg = starAvg;

                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setRatings(ratings, index);
                            ((GlobalModel) dto.context.getApplicationContext()).getRecipeViewModel().setStars(starAvg, index);
                        } else {
                            try {
                                new CustomToast("Gagal Mendapatkan Data Rating: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal Mendapatkan Data Rating", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetRatingsResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal Mendapatkan Data Rating: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }

    public void postRating(String recipeId, String comment, int stars){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        PostRatingRequest postRatingRequest = new PostRatingRequest(stars, comment);

        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Membuat Ulasan");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ratingsService
                .postRating(recipeId, token, postRatingRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            new CustomToast("Ulasan berhasil dibuat", dto.view, false).show();
                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal membuat ulasan: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal membuat ulasan", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal membuat ulasan: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }

    public void putRating(String recipeId, String comment, int stars){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        PutRatingRequest putRatingRequest = new PutRatingRequest(stars, comment);

        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Memperbarui Ulasan");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ratingsService
                .putRatingByRecipeId(recipeId, token, putRatingRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            new CustomToast("Ulasan berhasil diperbarui", dto.view, false).show();
                            if(dto.callback != null)
                                dto.callback.run();
                        } else {
                            try {
                                new CustomToast("Gagal memperbarui ulasan: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal memperbarui ulasan", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal memperbarui ulasan: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }

    public void deleteRating(String recipeId){
        String token = ((GlobalModel) dto.context.getApplicationContext()).getSessionManager()
                        .getJwtHeaderValue();

        ProgressDialog progressDialog = dto.createProgressDialog();
        progressDialog.setMessage("Menghapus Ulasan");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ratingsService
                .deleteRatingByRecipeId(recipeId, token)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            // refresh rating list
                            if(dto.callback != null)
                                dto.callback.run();

                            new CustomToast("Ulasan berhasil dihapus", dto.view, false).show();
                        } else {
                            try {
                                new CustomToast("Gagal menghapus ulasan: " + response.errorBody().string(), dto.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Gagal menghapus ulasan", dto.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        new CustomToast("Gagal menghapus ulasan: Koneksi Gagal", dto.view, false).show();
                    }
                });
    }
}
