package com.example.carapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "car.db";

    //creating tasks table
    private static final String TABLE_CAR = "car";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_LITRE = "litre";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableSql = "CREATE TABLE " + TABLE_CAR + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BRAND + " TEXT, "
                + COLUMN_LITRE + " TEXT )";
        sqLiteDatabase.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + TABLE_CAR);
        onCreate(sqLiteDatabase);

    }

    public void insertTasks(String brand, String litre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BRAND, brand);
        cv.put(COLUMN_LITRE, litre);
        db.insert(TABLE_CAR, null, cv);
        db.close();
    }

    public ArrayList<String> getCarBrand(){
        ArrayList<String> tasks = new ArrayList<String>();

        String selectQuery = "SELECT " + COLUMN_BRAND +", " + COLUMN_LITRE +" FROM " + TABLE_CAR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                tasks.add(cursor.getString(0));
                tasks.add(cursor.getString(1));

            }while(cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return  tasks;
    }

    public ArrayList<Car> getCar(){
        ArrayList<Car> tasks = new ArrayList<Car>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_BRAND + ", "
                + COLUMN_LITRE + " FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String brand = cursor.getString(1);
                String litre = cursor.getString(2);
                Car car = new Car(id, brand, Double.parseDouble(litre));
                tasks.add(car);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  tasks;
    }
}
