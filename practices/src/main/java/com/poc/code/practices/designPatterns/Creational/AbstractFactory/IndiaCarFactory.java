package com.poc.code.practices.designPatterns.Creational.AbstractFactory;

public class IndiaCarFactory implements CarFactory {
    @Override
    public Car buildCar(CarType carType) throws IllegalArgumentException {
        System.out.println(carType + " built in India Car Factory");
        switch (carType) {
            case SUV:
                return new SUV(Location.INDIA, carType);
            case Sedan:
                return new Sedan(Location.INDIA, carType);
            case HatchBack:
                return new Hatchback(Location.INDIA, carType);
            default:
                throw new IllegalArgumentException("Not a known type of Car");
        }
    }
}
