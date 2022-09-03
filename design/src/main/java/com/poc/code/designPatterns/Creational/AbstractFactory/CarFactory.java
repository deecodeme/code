package com.poc.code.designPatterns.Creational.AbstractFactory;

public interface CarFactory {
    Car buildCar(CarType carType) throws IllegalArgumentException;
}
