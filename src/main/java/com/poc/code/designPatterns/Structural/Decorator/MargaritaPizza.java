package com.poc.code.designPatterns.Structural.Decorator;

public class MargaritaPizza implements Pizza {
    @Override
    public void create() {
        System.out.println("Margarita pizza created");
    }
}
