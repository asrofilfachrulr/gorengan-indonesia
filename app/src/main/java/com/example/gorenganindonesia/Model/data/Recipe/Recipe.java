package com.example.gorenganindonesia.Model.data.Recipe;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;

public class Recipe implements Parcelable {
    private String id;
    private String title;
    private String authorUsername;
    private String category;
    private int minuteDuration;
    private String imgUrl;
    private String difficulty;
    private int portion;
    private String ratingStar;
    private Ingredient[] ingredients;

    private String[] steps;


    public Recipe(String id, String title, String authorUsername, String ratingStar, String category, int minuteDuration, String imgUrl, String difficulty, int portion, String[] steps, Ingredient[] ingredients) {
        this.id = id;
        this.title = title;
        this.authorUsername = authorUsername;
        this.ratingStar = ratingStar;
        this.category = category;
        this.minuteDuration = minuteDuration;
        this.imgUrl = imgUrl;
        this.difficulty = difficulty;
        this.portion = portion;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(String ratingStar) {
        this.ratingStar = ratingStar;
    }

    // Implement the Parcelable.Creator for Receipt
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    protected Recipe(Parcel in) {
        id = in.readString();
        title = in.readString();
        authorUsername = in.readString();
        ratingStar = in.readString();
        category = in.readString();
        minuteDuration = in.readInt();
        imgUrl = in.readString();
        difficulty = in.readString();
        portion = in.readInt();
        steps = in.createStringArray();
        ingredients = in.createTypedArray(Ingredient.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(authorUsername);
        parcel.writeString(ratingStar);
        parcel.writeString(category);
        parcel.writeInt(minuteDuration);
        parcel.writeString(imgUrl);
        parcel.writeString(difficulty);
        parcel.writeInt(portion);
        parcel.writeStringArray(steps);
        parcel.writeTypedArray(ingredients, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        String text = "Resep Gorengan Indonesia\n" + getTitle() + "\n⭐" + getRatingStar() + " oleh @" + getAuthorUsername() + "\n\n" + "Bahan:";

        for(int i = 0; i < getIngredients().length; i++){
            text += "\n" + String.valueOf(i+1) + ". ";
            if(!getIngredients()[i].getQty().equals(""))
                text += getIngredients()[i].getQty() + " " + getIngredients()[i].getUnit() + " " + getIngredients()[i].getName();
            else
                text += "secukupnya";

            text += " " + getIngredients()[i].getName();
        }

        text += "\n\nLangkah-Langkah:";

        for(int i = 0; i < getSteps().length; i++){
            text += "\n" + String.valueOf(i+1) + ". " + getSteps()[i];
        }

        text += "\n\n©Gorengan Indonesia 2023";
        return text;
    }
}
