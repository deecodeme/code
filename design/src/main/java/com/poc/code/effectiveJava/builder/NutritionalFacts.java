package com.poc.code.effectiveJava.builder;

public class NutritionalFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int carbohydrate;
    private final int sodium;
    private final int vitaminA;

    public int getServingSize() {
        return servingSize;
    }

    public int getServings() {
        return servings;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public int getSodium() {
        return sodium;
    }

    public int getVitaminA() {
        return vitaminA;
    }

    private NutritionalFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.carbohydrate = builder.carbohydrate;
        this.sodium = builder.sodium;
        this.vitaminA = builder.vitaminA;
    }

    public static class Builder {
        private final int servingSize;
        private final int servings;
        private int calories;
        private int fat;
        private int carbohydrate;
        private int sodium;
        private int vitaminA;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder vitaminA(int vitaminA) {
            this.vitaminA = vitaminA;
            return this;
        }

        public NutritionalFacts build() {
            return new NutritionalFacts(this);
        }
    }

}
