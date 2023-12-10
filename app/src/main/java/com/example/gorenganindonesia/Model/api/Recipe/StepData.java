package com.example.gorenganindonesia.Model.api.Recipe;

public class StepData {
    int number;
    String step;

    public StepData(int number, String step) {
        this.number = number;
        this.step = step;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
