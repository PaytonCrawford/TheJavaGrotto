package com.example.thejavagrotto.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewItem implements Parcelable {

    /**
     * @author Payton Crawford
     * POJO Item to be used in the database.
     */

    private int reviewID;
    private String reviewTitle;
    private String reviewText;

    //Creating/Inserting Data
    public ReviewItem(String reviewTitle, String reviewText) {
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
    }

    //Retrieving/Reading Data
    public ReviewItem(Integer reviewID, String reviewTitle, String reviewText) {
        this.reviewID = reviewID;
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
    }

    protected ReviewItem(Parcel in) {
        reviewID = in.readInt();
        reviewTitle = in.readString();
        reviewText = in.readString();
    }

    public ReviewItem() {

    }

    public static final Creator<ReviewItem> CREATOR = new Creator<ReviewItem>() {
        @Override
        public ReviewItem createFromParcel(Parcel in) {
            return new ReviewItem(in);
        }

        @Override
        public ReviewItem[] newArray(int size) {
            return new ReviewItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(reviewID);
        parcel.writeString(reviewTitle);
        parcel.writeString(reviewText);
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
