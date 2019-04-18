package com.example.macrotracker;

public class Food {
    private int _id;
    private String _foodName;
    private int _protein;
    private int _carb;
    private int _fat;
    private int _total;

    public Food(String name,int intP,int intC,int intF){
        set_foodName(name);
        set_protein(intP);
        set_carb(intC);
        set_fat(intF);
        set_total(intP, intC, intF);
    }
    public void set_id(int id) {
        id = _id;
    }

    public void set_foodName(String foodName) {
        _foodName = foodName;
    }

    public void set_protein(int protein) {
        _protein = protein;
    }

    public void set_carb(int carb) {
        _carb = carb;
    }

    public void set_fat(int fat) {
        _fat = fat;
    }

    public int getId() {
        return _id;
    }

    public String getFoodName() {
        return _foodName;
    }

    public int getProtein() {
        return _protein;
    }

    public int getCarb() {
        return _carb;
    }

    public int getFat() {
        return _fat;
    }

    public int get_total() {
        return _total;
    }

    public void set_total(int x, int y, int z){
        _total = (x * 4) + (y * 4) + (z * 9);
    }

}
