package com.example.gorenganindonesia.Model.api.Recipe.Ratings;

import com.google.gson.annotations.SerializedName;

public class GetRatingsResponse {
    private String message;
    @SerializedName("data")
    private RatingData[] ratingData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RatingData[] getRatingData() {
        return ratingData;
    }

    public void setRatingData(RatingData[] ratingData) {
        this.ratingData = ratingData;
    }
}
