package com.example.a16022738.p05songdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "songlist.db";
    private static final int DATABASE_VERSION = 1;
    private static final String COL1 = "_id";
    private static final String COL2 = "title";
    private static final String COL3 = "singers";
    private static final String COL4 = "year";
    private static final String COL5 = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + DATABASE_NAME + "("
                + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " INT, " + COL5 + "INT ) ";
        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public long insertSong(String singers, String title, int IntOfYear, int stars){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL2, title);
        values.put(COL3, singers);
        values.put(COL4, IntOfYear);
        values.put(COL5, stars);

        long result = db.insert(DATABASE_NAME, null, values);
        db.close();
        Log.d("SQL Insert ",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Song> getAllSongs() {
        //TODO return records in Java objects
        ArrayList<Song> songs = new ArrayList<Song>();
        String selectQuery = "SELECT " + COL1 + ", "
                + COL2 + ", "
                + COL3 + ", "
                + COL4 + ", "
                + COL5
                + " FROM " + DATABASE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String Title = cursor.getString(1);
                String Singer = cursor.getString(2);
                int Year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song obj = new Song(Title,Singer,Year,stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL2, data.getTitle());
        values.put(COL3, data.getSingers());
        values.put(COL4, data.getYear());
        values.put(COL5, data.getStars());

        String condition = COL1 + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(DATABASE_NAME, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COL1 + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(DATABASE_NAME, condition, args);
        db.close();
        return result;
    }
}
