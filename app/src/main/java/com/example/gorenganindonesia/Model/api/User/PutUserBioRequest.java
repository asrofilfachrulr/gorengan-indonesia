package com.example.gorenganindonesia.Model.api.User;

import com.google.gson.annotations.SerializedName;

public class PutUserBioRequest {
    String username;
    String email;
    @SerializedName("full_name")
    String fullName;

    public PutUserBioRequest(String username, String email, String fullName) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
