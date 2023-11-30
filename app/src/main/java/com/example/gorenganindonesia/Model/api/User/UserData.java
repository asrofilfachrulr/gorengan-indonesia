package com.example.gorenganindonesia.Model.api.User;

import com.google.gson.annotations.SerializedName;

public class UserData {
    String id;
    String name;
    String username;
    String email;
    @SerializedName("image_url")
    String imageUrl;
    @SerializedName("thumb_url")
    String thumbUrl;

    public UserData(String id, String name, String username, String email, String imageUrl, String thumbUrl) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.thumbUrl = thumbUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
