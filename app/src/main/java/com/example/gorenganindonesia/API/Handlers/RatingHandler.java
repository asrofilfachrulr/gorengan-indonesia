package com.example.gorenganindonesia.API.Handlers;

import android.view.View;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.RatingsService;
import com.example.gorenganindonesia.Activity.RatingActivity;
import com.example.gorenganindonesia.Model.DAO.APIHandlerDAO;
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
    APIHandlerDAO dao;
    RatingsService ratingsService;

    public RatingHandler(APIHandlerDAO dao) {
        this.dao = dao;

        this.ratingsService = RetrofitClient.getInstance().create(RatingsService.class);
    }

    public APIHandlerDAO getDao() {
        return dao;
    }

    public void setDao(APIHandlerDAO dao) {
        this.dao = dao;
    }

    public void getRatings(String recipeId, String queryOrder, int index, RatingActivity activity){
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        dao.loadingView.setVisibility(View.VISIBLE);
        dao.loadingText.setText("Memuat Ulasan...");

        ratingsService
                .getRatingsByRecipeId(recipeId, token, queryOrder)
                .enqueue(new Callback<GetRatingsResponse>() {
                    @Override
                    public void onResponse(Call<GetRatingsResponse> call, Response<GetRatingsResponse> response) {
                        dao.loadingView.setVisibility(View.GONE);

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
                                        ratingData[i].getThumbUrl(),
                                        ratingData[i].getLikeCount()
                                );

                                ratings[i] = rating;
                            }

                            float starAvg = response.body().getExtra().getStarAvg();
                            activity.starAvg = starAvg;

                            ((GlobalModel) dao.context.getApplicationContext()).getRecipeViewModel().setRatings(ratings, index);
                            ((GlobalModel) dao.context.getApplicationContext()).getRecipeViewModel().setStars(response.body().getExtra().getStarAvg(), index);
                        } else {
                            try {
                                new CustomToast("Error Mendapatkan Data: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Data", dao.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetRatingsResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Error Memuat Data", dao.view, false).show();
                    }
                });
    }

    public void postRating(String recipeId, String comment, int stars){
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        PostRatingRequest postRatingRequest = new PostRatingRequest(stars, comment);

        dao.loadingText.setText("Membuat Ulasan...");

        ratingsService
                .postRating(recipeId, token, postRatingRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if(response.isSuccessful()){
                            new CustomToast("Ulasan berhasil dibuat", dao.view, false).show();
                            if(dao.callback != null)
                                dao.callback.run();
                        } else {
                            dao.loadingView.setVisibility(View.GONE);
                            try {
                                new CustomToast("Gagal membuat ulasan: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Data", dao.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Error Jaringan", dao.view, false).show();
                    }
                });
    }

    public void putRating(String recipeId, String comment, int stars){
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager()
                .getJwtHeaderValue();

        PutRatingRequest putRatingRequest = new PutRatingRequest(stars, comment);

        dao.loadingText.setText("Memperbarui Ulasan...");

        ratingsService
                .putRatingByRecipeId(recipeId, token, putRatingRequest)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if (response.isSuccessful()) {
                            new CustomToast("Ulasan berhasil diperbarui", dao.view, false).show();
                            if(dao.callback != null)
                                dao.callback.run();
                        } else {
                            dao.loadingView.setVisibility(View.GONE);
                            try {
                                new CustomToast("Gagal memperbarui ulasan: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Data", dao.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Error Jaringan", dao.view, false).show();
                    }
                });
    }

    public void deleteRating(String recipeId){
        String token = ((GlobalModel) dao.context.getApplicationContext()).getSessionManager()
                        .getJwtHeaderValue();

        dao.loadingText.setText("Menghapus ulasan...");
        dao.loadingView.setVisibility(View.VISIBLE);

        ratingsService
                .deleteRatingByRecipeId(recipeId, token)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        if(response.isSuccessful()){
                            // refresh rating list
                            if(dao.callback != null)
                                dao.callback.run();

                            new CustomToast("Ulasan berhasil dihapus", dao.view, false).show();
                        } else {
                            dao.loadingView.setVisibility(View.GONE);
                            try {
                                new CustomToast("Gagal menghapus ulasan: " + response.errorBody().string(), dao.view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Data", dao.view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        dao.loadingView.setVisibility(View.GONE);
                        new CustomToast("Error Jaringan", dao.view, false).show();
                    }
                });
    }
}
