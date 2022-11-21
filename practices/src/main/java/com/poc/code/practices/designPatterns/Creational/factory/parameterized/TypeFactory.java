package com.poc.code.practices.designPatterns.Creational.factory.parameterized;

public class TypeFactory {
    public Type getType(TypeEnum type) {
        switch (type) {
            case A:
                return new ConcreteTypeA();
            case B:
                return new ConcreteTypeB();
            case C:
                return new ConcreteTypeC();
            default:
                throw new IllegalArgumentException("Not a valid Type");
        }
    }
}
