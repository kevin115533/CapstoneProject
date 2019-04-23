package com.example.macrotracker;

public class Goals {
    private int gPro;
    private int gCar;
    private int gFat;

    Goals(int pro , int car, int fat){
        setgPro(pro);
        setgCar(car);
        setgFat(fat);
    }

    public int getgPro() {
        return gPro;
    }

    public void setgPro(int x) {
        gPro = x;
    }

    public int getgCar() {
        return gCar;
    }

    public void setgCar(int x) {
        gCar = x;
    }

    public int getgFat() {
        return gFat;
    }

    public void setgFat(int x) {
        gFat = x;
    }
}
