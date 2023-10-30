package com.example.gorenganindonesia.Model.data.Receipt;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;

public class Receipt implements Parcelable {
    private String id;
    private String title;
    private String authorName;
    private String category;
    private int minuteDuration;
    private int thumb;
    private String difficulty;
    private int portion;
    private String ratingStar;
    private Ingredient[] ingredients;

    private String[] steps;


    public Receipt(String id, String title, String authorName, String ratingStar, String category, int minuteDuration, int thumb, String difficulty, int portion, String[] steps, Ingredient[] ingredients) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.ratingStar = ratingStar;
        this.category = category;
        this.minuteDuration = minuteDuration;
        this.thumb = thumb;
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

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(String ratingStar) {
        this.ratingStar = ratingStar;
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
        id = in.readString();
        title = in.readString();
        authorName = in.readString();
        ratingStar = in.readString();
        category = in.readString();
        minuteDuration = in.readInt();
        thumb = in.readInt();
        difficulty = in.readString();
        portion = in.readInt();
        steps = in.createStringArray();
        ingredients = in.createTypedArray(Ingredient.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(authorName);
        parcel.writeString(ratingStar);
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

    @NonNull
    @Override
    public String toString() {
        String text = "Resep Gorengan Indonesia\n" + getTitle() + "\n⭐" + getRatingStar() + " oleh @" + getAuthorName() + "\n\n" + "Bahan:";

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
