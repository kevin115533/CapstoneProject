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

    private static final String createFoodTable = "CREATE TABLE " + ColumnNames.TableFoodNames.TABLE_FOODS + "(" +
            ColumnNames.TableFoodNames.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ColumnNames.TableFoodNames.COLUMN_FOODNAME + " TEXT, " +
            ColumnNames.TableFoodNames.COLUMN_PROTEIN + " INTEGER, " +
            ColumnNames.TableFoodNames.COLUMN_CARB + " INTEGER, " +
            ColumnNames.TableFoodNames.COLUMN_FAT + " INTEGER, " +
            ColumnNames.TableFoodNames.COLUMN_TOTAL + " INTEGER " +
            ");";

    public static final String createGoalTable = "CREATE TABLE " + ColumnNames.TableGoalNames.TABLE_GOALS + "(" +
            ColumnNames.TableFoodNames.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ColumnNames.TableGoalNames.COLUMN_GOAL_PROTEIN + " INTEGER, " +
            ColumnNames.TableGoalNames.COLUMN_GOAL_CARB + " INTEGER, " +
            ColumnNames.TableGoalNames.COLUMN_GOAL_FAT + " INTEGER, " +
            ColumnNames.TableGoalNames.COLUMN_GOAL_CALORIE + " INTEGER " +
            ");";

    public MyDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createFoodTable);
        db.execSQL(createGoalTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + ColumnNames.TableFoodNames.TABLE_FOODS);
        db.execSQL(" DROP TABLE IF EXISTS " + ColumnNames.TableGoalNames.TABLE_GOALS);
        onCreate(db);
    }

    public boolean addFoodToDb(Food food) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnNames.TableFoodNames.COLUMN_FOODNAME, food.getFoodName());
        values.put(ColumnNames.TableFoodNames.COLUMN_PROTEIN, food.getProtein());
        values.put(ColumnNames.TableFoodNames.COLUMN_CARB, food.getCarb());
        values.put(ColumnNames.TableFoodNames.COLUMN_FAT, food.getFat());
        values.put(ColumnNames.TableFoodNames.COLUMN_TOTAL, food.get_total());
        long result = db.insert(ColumnNames.TableFoodNames.TABLE_FOODS, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addGoalsToDB(Goals goals){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnNames.TableGoalNames.COLUMN_GOAL_PROTEIN, goals.getgPro());
        values.put(ColumnNames.TableGoalNames.COLUMN_GOAL_CARB, goals.getgCar());
        values.put(ColumnNames.TableGoalNames.COLUMN_GOAL_FAT, goals.getgFat());
        //long result = db.insert(ColumnNames.TableGoalNames.TABLE_GOALS, null, values);
        int result = db.update(ColumnNames.TableGoalNames.TABLE_GOALS,values,null, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<FoodEntry> getList() {
        ArrayList<FoodEntry> foodList = new ArrayList<FoodEntry>();
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {ColumnNames.TableFoodNames.COLUMN_ID, ColumnNames.TableFoodNames.COLUMN_FOODNAME, ColumnNames.TableFoodNames.COLUMN_PROTEIN, ColumnNames.TableFoodNames.COLUMN_CARB, ColumnNames.TableFoodNames.COLUMN_FAT, ColumnNames.TableFoodNames.COLUMN_TOTAL};
        Cursor c = db.query(ColumnNames.TableFoodNames.TABLE_FOODS, columns, null, null, null, null, null);

        int iID = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_ID);
        int iName = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_FOODNAME);
        int iPro = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_PROTEIN);
        int iCar = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_CARB);
        int iFat = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_FAT);
        int iTot = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_TOTAL);

        c.moveToFirst();

        for (int i = 1; i <= c.getCount(); i++) {
            foodList.add(new FoodEntry(c.getString(iName), c.getInt(iPro), c.getInt(iCar), c.getInt(iFat)));
            c.moveToNext();
        }

        c.close();
        return foodList;
    }

    public Goals consumedGoals(){
        String[] columns = {ColumnNames.TableFoodNames.COLUMN_PROTEIN, ColumnNames.TableFoodNames.COLUMN_CARB, ColumnNames.TableFoodNames.COLUMN_FAT};
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(ColumnNames.TableFoodNames.TABLE_FOODS, columns, null, null, null, null, null);

        int iPro = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_PROTEIN);
        int iCar = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_CARB);
        int iFat = c.getColumnIndex(ColumnNames.TableFoodNames.COLUMN_FAT);
        int cPro = 0;
        int cCar = 0;
        int cFat = 0;

        c.moveToFirst();

        for (int i = 1; i <= c.getCount(); i++) {
            cPro = cPro + c.getInt(iPro);
            cCar = cCar + c.getInt(iCar);
            cFat = cFat + c.getInt(iFat);
            c.moveToNext();
        }

        Goals cGoals = new Goals(cPro, cCar, cFat);
        c.close();
        return cGoals;
    }

    public Goals setGoals(){
        String[] columns = {ColumnNames.TableGoalNames.COLUMN_GOAL_PROTEIN,ColumnNames.TableGoalNames.COLUMN_GOAL_CARB,ColumnNames.TableGoalNames.COLUMN_GOAL_FAT,ColumnNames.TableGoalNames.COLUMN_GOAL_CALORIE };
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(ColumnNames.TableGoalNames.TABLE_GOALS, columns, null, null, null, null,null);
        int sPro = c.getColumnIndex(ColumnNames.TableGoalNames.COLUMN_GOAL_PROTEIN);
        int sCar = c.getColumnIndex(ColumnNames.TableGoalNames.COLUMN_GOAL_CARB);
        int sFat = c.getColumnIndex(ColumnNames.TableGoalNames.COLUMN_GOAL_FAT);

        c.moveToFirst();

        int gPro = c.getInt(sPro);
        int gCar = c.getInt(sCar);
        int gFat = c.getInt(sFat);
        c.close();
        Goals sGoals = new Goals(gPro, gCar, gFat);
        return sGoals;
    }
}
