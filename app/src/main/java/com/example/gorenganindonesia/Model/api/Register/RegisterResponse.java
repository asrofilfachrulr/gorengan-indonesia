package com.example.gorenganindonesia.Model.api.Register;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private String error;

    public RegisterResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
