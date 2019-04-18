package com.example.macrotracker;

public class FoodEntry {
    private String eName;
    private int ePro;
    private int eCar;
    private int eFat;
    private int eTotal;
    private int eID;

    public FoodEntry(String name, int pro, int car, int fat){
        eName = name;
        ePro = pro;
        eCar = car;
        eFat = fat;
        eTotal = addTotal(pro, car, fat);
    }

    public int getePro(){
        return ePro;
    }

    public int geteCar() {
        return eCar;
    }

    public int geteFat() {
        return eFat;
    }

    public String geteName() {
        return eName;
    }

    public int geteTotal(){
        return eTotal;
    }

    public int geteID(){
        return  eID;
    }

    public void seteID(){

    }

    public int addTotal(int x, int y, int z){
        int result =(x * 4) + (y * 4) + (z * 9);
        return result;
    }
}
