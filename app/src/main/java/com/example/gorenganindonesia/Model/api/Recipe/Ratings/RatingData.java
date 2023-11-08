package com.example.gorenganindonesia.Model.api.Recipe.Ratings;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RatingData {
    private String username;
    @SerializedName("thumb_url")
    private String thumbUrl;
    private int stars;
    @SerializedName("like_count")
    private int likeCount;
    private String comment;
    private Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
