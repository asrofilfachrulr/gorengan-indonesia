package com.example.gorenganindonesia.Model.data.Rating;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Rating implements Parcelable {
    private String comment;
    private Date date;
    private String authorUsername;
    private int starCount;
    private String imageUrl;
    private int likeCount;

    public Rating(String comment, Date date, String authorUsername, int starCount, String imageUrl, int likeCount) {
        this.comment = comment;
        this.date = date;
        this.authorUsername = authorUsername;
        this.starCount = starCount;
        this.imageUrl = imageUrl;
        this.likeCount = likeCount;
    }

    protected Rating(Parcel in) {
        comment = in.readString();
        date = new Date(in.readLong());
        authorUsername = in.readString();
        starCount = in.readInt();
        imageUrl = in.readString();
        likeCount = in.readInt();
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(comment);
        dest.writeLong(date.getTime());
        dest.writeString(authorUsername);
        dest.writeString(imageUrl);
        dest.writeInt(starCount);
        dest.writeInt(likeCount);
    }
}
