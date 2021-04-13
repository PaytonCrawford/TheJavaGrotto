package com.example.thejavagrotto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thejavagrotto.pojo.ReviewItem;

import java.util.ArrayList;

public class CoffeeDB extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "thejavagrotto";

    //Review Table
    public static final String TABLE_REVIEWS = "reviews";

    //Table Columns
    public static final String COLUMN_ID= "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TEXT = "text";

    //Create Table Query
    public static final String CREATE_REVIEWS_TABLE = "CREATE TABLE " + TABLE_REVIEWS +"(" +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_TEXT + " TEXT)";

    public CoffeeDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_REVIEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addReview(ReviewItem reviewItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, reviewItem.getReviewTitle());
        values.put(COLUMN_TEXT, reviewItem.getReviewText());
        db.insert(TABLE_REVIEWS, null, values);
        db.close();
    }

    public ReviewItem getReview(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ReviewItem reviewItem = null;
        Cursor cursor = db.query(TABLE_REVIEWS,
                new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_TEXT}, COLUMN_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor.moveToFirst()) {
            reviewItem = new ReviewItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }
        db.close();
        return reviewItem;
    }

    public ArrayList<ReviewItem> getAllReviews() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REVIEWS, null);
        ArrayList<ReviewItem> reviewItem = new ArrayList<>();
        while (cursor.moveToNext()) {
            reviewItem.add(new ReviewItem (
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
        }
        db.close();
        return reviewItem;
    }

    public int updateReview(ReviewItem reviewItem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, reviewItem.getReviewTitle());
        values.put(COLUMN_TEXT, reviewItem.getReviewText());
        return db.update(TABLE_REVIEWS, values, COLUMN_ID + "=?",
                new String[]{(reviewItem.getReviewText())});
    }

    public void deleteReview(int review) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REVIEWS, COLUMN_ID + "=?", new String[]{String.valueOf(review)});
        db.close();
    }

}
