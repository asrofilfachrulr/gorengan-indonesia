package com.example.gorenganindonesia.Model.api.Recipe;

public class IngredientData {
    String qty;
    String unit;
    String name;

    public IngredientData(String qty, String unit, String name) {
        this.qty = qty;
        this.unit = unit;
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
