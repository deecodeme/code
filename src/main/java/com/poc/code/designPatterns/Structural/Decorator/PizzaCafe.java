package com.poc.code.designPatterns.Structural.Decorator;

public class PizzaCafe {
    public static void main(String[] args) {
        Pizza pizza = new CapsicumDecorator(new MushroomDecorator(new VegPizza()));
        pizza.create();

        System.out.println("Chicken pizza order...");
        Pizza chickenPizza = new CapsicumDecorator(new ChickenPizza());
        chickenPizza.create();
    }
}
