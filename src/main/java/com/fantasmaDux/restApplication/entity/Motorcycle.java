package com.fantasmaDux.restApplication.entity;

public class Motorcycle {
    private String brand;
    private String model;
    private int year;
    public Motorcycle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public Motorcycle() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

//    private String category;
//    private int engineCapacity;
//    private int power;
//    private double weight;
//    private int maxSpeed;
//    private double price;

