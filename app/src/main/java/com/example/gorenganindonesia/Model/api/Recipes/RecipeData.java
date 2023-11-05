package com.example.gorenganindonesia.Model.api.Recipes;

import com.google.gson.annotations.SerializedName;

public class RecipeData {
    private String id;
    private String username;
    private String title;
    private String category;
    @SerializedName("img_url")
    private String imgUrl;
    private String difficulty;
    private int portion;
    @SerializedName("minute_duration")
    private int minuteDuration;
    @SerializedName("star_rating")
    private String starRating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public int getMinuteDuration() {
        return minuteDuration;
    }

    public void setMinuteDuration(int minuteDuration) {
        this.minuteDuration = minuteDuration;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }
}
