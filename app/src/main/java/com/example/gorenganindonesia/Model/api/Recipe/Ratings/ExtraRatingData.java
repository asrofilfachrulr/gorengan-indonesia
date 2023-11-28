package com.example.gorenganindonesia.Model.api.Recipe.Ratings;

import com.google.gson.annotations.SerializedName;

public class ExtraRatingData {
    @SerializedName("star_avg")
    private float starAvg;

    public float getStarAvg() {
        return starAvg;
    }

    public void setStarAvg(float starAvg) {
        this.starAvg = starAvg;
    }
}
