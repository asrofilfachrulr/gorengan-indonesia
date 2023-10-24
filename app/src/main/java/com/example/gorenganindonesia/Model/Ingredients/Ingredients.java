package com.example.gorenganindonesia.Model.Ingredients;
import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {
    private String qty;
    private String unit;
    private String name;

    public Ingredients(String qty, String unit, String name) {
        this.qty = qty;
        this.unit = unit;
        this.name = name;
    }

    protected Ingredients(Parcel in) {
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

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
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
