package com.poc.code.practices.refactoring;

public class ReplaceMethodWithMethodObject {
    public double price() {
        return new PriceCalculator().calculate(this);
    }

//    public double price() {
//        public double calculate(ReplaceMethodWithMethodObject obj) {
//            // Perform long computation.
//            double primaryBasePrice = 0;
//            double secondaryBasePrice = 0;
//            double tertiaryBasePrice;
//
//            return primaryBasePrice * secondaryBasePrice;
//        }

    private class PriceCalculator {
        double primaryBasePrice = 0;
        double secondaryBasePrice = 0;
        double tertiaryBasePrice;

        public double calculate(ReplaceMethodWithMethodObject obj) {
            // Perform long computation.
            return primaryBasePrice * secondaryBasePrice;
        }
    }
}
