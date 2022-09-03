package com.poc.code.refactoring;

public class ConstructorToBuilderBuilder {
    private int variable;

    public ConstructorToBuilderBuilder setVariable(int variable) {
        this.variable = variable;
        return this;
    }

    public ConstructorToBuilder createConstructorToBuilder() {
        return new ConstructorToBuilder(variable);
    }
}