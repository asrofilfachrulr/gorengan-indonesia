package com.example.gorenganindonesia.Model.api.Recipes;

public class GetAllRecipesResponse {
    private String message;
    private RecipeData[] data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RecipeData[] getData() {
        return data;
    }

    public void setData(RecipeData[] data) {
        this.data = data;
    }
}
