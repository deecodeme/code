package com.poc.code.designPatterns.Structural.Decorator;

public class ChickenPizza implements Pizza {
    @Override
    public void create() {
        System.out.println("Chicken pizza created");
    }
}
