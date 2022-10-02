package com.poc.code.practices.designPatterns.Creational.AbstractFactory;

public interface CarFactory {
    Car buildCar(CarType carType) throws IllegalArgumentException;
}
