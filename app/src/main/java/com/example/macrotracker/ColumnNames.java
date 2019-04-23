package com.example.macrotracker;

public class ColumnNames {
    private ColumnNames(){}

    public static final class TableFoodNames{
        public static final String TABLE_FOODS = "foods";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_FOODNAME = "foodName";
        public static final String COLUMN_PROTEIN = "protein";
        public static final String COLUMN_CARB = "carb";
        public static final String COLUMN_FAT = "fat";
        public static final String COLUMN_TOTAL = "totalCalories";
    }

    public static final class TableGoalNames{
        public static final String TABLE_GOALS = "goals";
        public static final String COLUMN_GOAL_PROTEIN = "goalPro";
        public static final String COLUMN_GOAL_CARB = "goalCar";
        public static final String COLUMN_GOAL_FAT = "goalFat";
        public static final String COLUMN_GOAL_CALORIE = "goalCal";
    }
}
