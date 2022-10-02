package com.poc.code.practices.effectiveJava.builder.hierarchy;

import java.util.Objects;

public class NyPizza extends Pizza {
    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    private final Size size;

    public NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    @Override
    public String toString() {
        return String.format("PIZZA with size: % and %2" + this.size, super.toString());
    }

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        Builder self() {
            return this;
        }
    }
}
