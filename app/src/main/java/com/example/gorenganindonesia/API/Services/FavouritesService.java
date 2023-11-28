package com.example.gorenganindonesia.API.Services;

import com.example.gorenganindonesia.Model.api.GetFavouritesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface FavouritesService {
    @GET("/favourites")
    Call<GetFavouritesResponse> getFavourites(@Header("Authorization") String token);
}
