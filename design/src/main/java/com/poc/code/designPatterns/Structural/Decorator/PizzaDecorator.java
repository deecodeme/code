package com.poc.code.designPatterns.Structural.Decorator;

public class PizzaDecorator implements Pizza{
    private Pizza pizza;

    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }

    @Override
    public void create() {
        pizza.create();
    }
}
