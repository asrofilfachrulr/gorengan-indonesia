package com.example.gorenganindonesia.Model.api.Recipe;

import com.google.gson.annotations.SerializedName;

public class PostRecipeRequest {
    private String title;
    private String category;
    @SerializedName("minute_duration")
    private int minuteDuration;
    private String difficulty;
    private int portion;

    private IngredientData[] ingredients;
    private StepData[] steps;

    public PostRecipeRequest() {}

    public PostRecipeRequest(String title, String category, int minuteDuration, String difficulty, int portion, IngredientData[] ingredients, StepData[] steps) {
        this.title = title;
        this.category = category;
        this.minuteDuration = minuteDuration;
        this.difficulty = difficulty;
        this.portion = portion;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMinuteDuration() {
        return minuteDuration;
    }

    public void setMinuteDuration(int minuteDuration) {
        this.minuteDuration = minuteDuration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public IngredientData[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(IngredientData[] ingredients) {
        this.ingredients = ingredients;
    }

    public StepData[] getSteps() {
        return steps;
    }

    public void setSteps(StepData[] steps) {
        this.steps = steps;
    }
}
