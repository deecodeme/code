package com.poc.code.practices.designPatterns.Structural.Decorator;

public class CapsicumDecorator extends PizzaDecorator {
    private final String description = " with Capsicum";

    public CapsicumDecorator(Pizza pizza){
        super(pizza);
    }

    @Override
    public void create() {
        System.out.println("Pizza decorated" + description);
        super.create();
    }
}