package com.poc.code.practices.designPatterns.Structural.Decorator;

public class VegPizza implements Pizza {
    @Override
    public void create() {
        System.out.println("Veg pizza created");
    }
}
