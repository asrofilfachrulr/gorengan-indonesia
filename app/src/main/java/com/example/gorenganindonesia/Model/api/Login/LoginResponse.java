package com.example.gorenganindonesia.Model.api.Login;

import com.example.gorenganindonesia.Model.api.AccountResponse;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("token")
    private String token;
    @SerializedName("error")
    private String error;
    @SerializedName("account")
    private AccountResponse accountResponse;

    public AccountResponse getAccount() {
        return accountResponse;
    }

    public void setAccount(AccountResponse accountResponse) {
        this.accountResponse = accountResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
