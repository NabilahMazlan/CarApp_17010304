package com.example.carapp;

public class Car {

    private int id;
    private String brand;
    private Double litre;

    public Car(int id, String brand, Double litre) {
        this.id = id;
        this.brand = brand;
        this.litre = litre;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public Double getLitre() {
        return litre;
    }
}
