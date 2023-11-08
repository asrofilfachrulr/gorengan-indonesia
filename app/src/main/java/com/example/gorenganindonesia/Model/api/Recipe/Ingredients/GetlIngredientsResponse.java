package com.example.gorenganindonesia.Model.api.Recipe.Ingredients;

import com.google.gson.annotations.SerializedName;

public class GetlIngredientsResponse {
    private String message;
    @SerializedName("data")
    private IngredientData[] ingredientData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IngredientData[] getIngredientData() {
        return ingredientData;
    }

    public void setIngredientData(IngredientData[] ingredientData) {
        this.ingredientData = ingredientData;
    }
}
