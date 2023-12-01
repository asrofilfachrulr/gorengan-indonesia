package com.example.gorenganindonesia.Model.api;

import com.google.gson.annotations.SerializedName;

public class AccountResponse {
    private String id;
    private String name;
    private String username;
    private String email;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("image_path")
    private String imagePath;

    public AccountResponse(String id, String name, String username, String email, String imageUrl, String imagePath) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
