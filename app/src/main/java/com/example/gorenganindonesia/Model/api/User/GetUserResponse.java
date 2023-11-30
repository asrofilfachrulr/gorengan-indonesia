package com.example.gorenganindonesia.Model.api.User;

import com.google.gson.annotations.SerializedName;

public class GetUserResponse {
    String message;
    @SerializedName("data")
    UserData userData;

    public GetUserResponse(String message, UserData userData) {
        this.message = message;
        this.userData = userData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
