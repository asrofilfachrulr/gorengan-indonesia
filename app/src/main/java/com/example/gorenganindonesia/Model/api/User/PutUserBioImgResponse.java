package com.example.gorenganindonesia.Model.api.User;

import com.google.gson.annotations.SerializedName;

public class PutUserBioImgResponse {
    String message;
    @SerializedName("image_url")
    String imageUrl;
    @SerializedName("image_path")
    String imagePath;

    public PutUserBioImgResponse(String message, String imageUrl, String imagePath) {
        this.message = message;
        this.imageUrl = imageUrl;
        this.imagePath = imagePath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
