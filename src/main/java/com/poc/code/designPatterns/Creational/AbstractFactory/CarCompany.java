package com.poc.code.designPatterns.Creational.AbstractFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CarCompany {
    public static void main(String args[]){
        AbstractCarFactory carFactory = new AbstractCarFactory();
        Executor executor = Executors.newFixedThreadPool(10);
        executor.execute(() ->{
            try{
                carFactory.getCarFactory(Location.INDIA).buildCar(CarType.Sedan);
            }catch (IllegalArgumentException e){
                System.out.println("Exception: " +e);
            }
        });
    }
}
