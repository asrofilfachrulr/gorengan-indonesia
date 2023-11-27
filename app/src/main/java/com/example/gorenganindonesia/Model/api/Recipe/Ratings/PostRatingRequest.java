package com.example.gorenganindonesia.Model.api.Recipe.Ratings;

public class PostRatingRequest {
    int stars;
    String comment;

    public PostRatingRequest(int stars, String comment) {
        this.stars = stars;
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
