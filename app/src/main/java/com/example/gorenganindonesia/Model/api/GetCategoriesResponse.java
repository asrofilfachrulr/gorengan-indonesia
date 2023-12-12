package com.example.gorenganindonesia.Model.api;

public class GetCategoriesResponse {
    String message;
    String[] data;

    public GetCategoriesResponse(String message, String[] data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
