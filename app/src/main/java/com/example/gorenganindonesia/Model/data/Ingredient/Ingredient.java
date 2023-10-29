package com.example.gorenganindonesia.Model.data.Ingredient;
import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    private String qty;
    private String unit;
    private String name;

    public Ingredient(String qty, String unit, String name) {
        this.qty = qty;
        this.unit = unit;
        this.name = name;
    }

    protected Ingredient(Parcel in) {
        qty = in.readString();
        unit = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(qty);
        dest.writeString(unit);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

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
