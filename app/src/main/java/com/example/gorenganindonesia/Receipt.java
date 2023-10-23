package com.example.gorenganindonesia;

import android.os.Parcel;
import android.os.Parcelable;

public class Receipt implements Parcelable {
    private String title;
    private String category;
    private int minuteDuration;
    private int thumb;
    private String difficulty;
    private int portion;
    private Ingredients[] ingredients;
    private String[] steps;

    public Receipt(String title, String category, int minuteDuration, int thumb, String difficulty, int portion, String[] steps, Ingredients[] ingredients) {
        this.title = title;
        this.category = category;
        this.minuteDuration = minuteDuration;
        this.thumb = thumb;
        this.difficulty = difficulty;
        this.portion = portion;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinuteDuration() {
        return minuteDuration;
    }

    public void setMinuteDuration(int minuteDuration) {
        this.minuteDuration = minuteDuration;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
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

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }

    public Ingredients[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients[] ingredients) {
        this.ingredients = ingredients;
    }

    // Implement the Parcelable.Creator for Receipt
    public static final Creator<Receipt> CREATOR = new Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };

    protected Receipt(Parcel in) {
        title = in.readString();
        category = in.readParcelable(Category.class.getClassLoader());
        minuteDuration = in.readInt();
        thumb = in.readInt();
        difficulty = in.readString();
        portion = in.readInt();
        steps = in.createStringArray();
        ingredients = in.createTypedArray(Ingredients.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(category);
        parcel.writeInt(minuteDuration);
        parcel.writeInt(thumb);
        parcel.writeString(difficulty);
        parcel.writeInt(portion);
        parcel.writeStringArray(steps);
        parcel.writeTypedArray(ingredients, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
