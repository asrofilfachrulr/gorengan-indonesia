package com.example.gorenganindonesia.Model.api;

import com.google.gson.annotations.SerializedName;

public class GetFavouritesResponse {
    private String message;
    @SerializedName("data")
    private String[] favourites;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getFavourites() {
        return favourites;
    }

    public void setFavourites(String[] favourites) {
        this.favourites = favourites;
    }
}
