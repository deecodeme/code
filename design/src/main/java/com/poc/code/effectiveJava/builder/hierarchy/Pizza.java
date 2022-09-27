package com.poc.code.effectiveJava.builder.hierarchy;

import java.util.EnumSet;
import java.util.Set;

public abstract class Pizza {
    final Set<Topping> toppings;

    public enum Topping {
        HAM,
        MUSHROOM,
        ONION,
        CAPCICUM,
        OLIVES
    }

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        abstract Pizza build();

        abstract T self();
    }

    public Pizza(Builder<?> builder) {
        this.toppings = builder.toppings.clone();
    }

    @Override
    public String toString() {
        StringBuilder pizzaStr = new StringBuilder();
        pizzaStr.append("Toppings: ");
        this.toppings.stream().forEach(topping -> pizzaStr.append(topping.toString() + ", "));
        return pizzaStr.toString();
    }
}
