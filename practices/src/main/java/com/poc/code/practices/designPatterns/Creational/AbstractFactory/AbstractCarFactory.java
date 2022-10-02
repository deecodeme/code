package com.poc.code.practices.designPatterns.Creational.AbstractFactory;

public class AbstractCarFactory {
    public CarFactory getCarFactory(Location location){
        switch (location){
            case USA:
                return new USACarFactory();
            case INDIA:
                return new IndiaCarFactory();
            default:
                throw new IllegalArgumentException("Location not supported");
        }
    }
}
