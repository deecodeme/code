package com.poc.code.practices.designPatterns.Structural.Decorator;

public class MushroomDecorator extends PizzaDecorator {
    private final String description = " with Mushroom";
    public MushroomDecorator(Pizza pizza){
        super(pizza);
    }

    @Override
    public void create() {
        System.out.println("Pizza decorated" + description);
        super.create();
    }
}
