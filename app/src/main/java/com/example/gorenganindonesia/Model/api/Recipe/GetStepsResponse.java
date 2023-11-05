package com.example.gorenganindonesia.Model.api.Recipe;

import com.google.gson.annotations.SerializedName;

public class GetStepsResponse {
    private String message;
    @SerializedName("data")
    private StepData[] stepData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StepData[] getStepData() {
        return stepData;
    }

    public void setStepData(StepData[] stepData) {
        this.stepData = stepData;
    }
}