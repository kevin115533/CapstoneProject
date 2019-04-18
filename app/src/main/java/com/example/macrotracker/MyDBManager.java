package com.example.macrotracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "foods.db";

    public MyDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + ColumnNames.CNames.TABLE_FOODS + "(" +
                ColumnNames.CNames.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnNames.CNames.COLUMN_FOODNAME + " TEXT, " +
                ColumnNames.CNames.COLUMN_PROTEIN + " INTEGER, " +
                ColumnNames.CNames.COLUMN_CARB + " INTEGER, " +
                ColumnNames.CNames.COLUMN_FAT + " INTEGER, " +
                ColumnNames.CNames.COLUMN_TOTAL + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + ColumnNames.CNames.TABLE_FOODS);
        onCreate(db);
    }

    public boolean addFoodToDb(Food food) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnNames.CNames.COLUMN_FOODNAME, food.getFoodName());
        values.put(ColumnNames.CNames.COLUMN_PROTEIN, food.getProtein());
        values.put(ColumnNames.CNames.COLUMN_CARB, food.getCarb());
        values.put(ColumnNames.CNames.COLUMN_FAT, food.getFat());
        values.put(ColumnNames.CNames.COLUMN_TOTAL, food.get_total());
        long result = db.insert(ColumnNames.CNames.TABLE_FOODS, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<FoodEntry> getList() {
        ArrayList<FoodEntry> foodList = new ArrayList<FoodEntry>();
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {ColumnNames.CNames.COLUMN_ID, ColumnNames.CNames.COLUMN_FOODNAME, ColumnNames.CNames.COLUMN_PROTEIN, ColumnNames.CNames.COLUMN_CARB, ColumnNames.CNames.COLUMN_FAT, ColumnNames.CNames.COLUMN_TOTAL};
        Cursor c = db.query(ColumnNames.CNames.TABLE_FOODS, columns, null, null, null, null, null);

        int iID = c.getColumnIndex(ColumnNames.CNames.COLUMN_ID);
        int iName = c.getColumnIndex(ColumnNames.CNames.COLUMN_FOODNAME);
        int iPro = c.getColumnIndex(ColumnNames.CNames.COLUMN_PROTEIN);
        int iCar = c.getColumnIndex(ColumnNames.CNames.COLUMN_CARB);
        int iFat = c.getColumnIndex(ColumnNames.CNames.COLUMN_FAT);
        int iTot = c.getColumnIndex(ColumnNames.CNames.COLUMN_TOTAL);

        c.moveToFirst();

        for (int i = 1; i <= c.getCount(); i++) {
            foodList.add(new FoodEntry(c.getString(iName), c.getInt(iPro), c.getInt(iCar), c.getInt(iFat)));
            c.moveToNext();
        }

        c.close();
        return foodList;
    }
}
