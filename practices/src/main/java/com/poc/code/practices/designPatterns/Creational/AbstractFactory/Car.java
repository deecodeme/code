package com.poc.code.practices.designPatterns.Creational.AbstractFactory;

public abstract class Car {
    private Location location;
    private CarType carType;

    public Car(Location location, CarType carType){
        this.location = location;
        this.carType = carType;
    }
}
