package com.example.gorenganindonesia.Model.data.Step;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Step implements Parcelable {
    private String description;
    private int number;

    public Step(){}

    public Step(String description, int number) {
        this.description = description;
        this.number = number;
    }

    protected Step(Parcel in) {
        description = in.readString();
        number = in.readInt();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeInt(number);
    }

    @NonNull
    @Override
    public String toString() {
        return Integer.valueOf(getNumber()) + ". " + getDescription() + "\n";
    }
}
