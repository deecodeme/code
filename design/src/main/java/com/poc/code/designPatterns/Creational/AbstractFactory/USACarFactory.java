package com.poc.code.designPatterns.Creational.AbstractFactory;

public class USACarFactory implements CarFactory {
    @Override
    public Car buildCar(CarType carType) throws IllegalArgumentException {
        System.out.println(carType + " built in USA Car Factory");
        switch (carType) {
            case SUV:
                return new SUV(Location.USA, carType);
            case Sedan:
                return new Sedan(Location.USA, carType);
            case HatchBack:
                return new Hatchback(Location.USA, carType);
            default:
                throw new IllegalArgumentException("Not a known type of Car");
        }
    }
}
