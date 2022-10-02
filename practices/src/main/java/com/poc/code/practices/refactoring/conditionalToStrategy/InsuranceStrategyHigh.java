package com.poc.code.practices.refactoring.conditionalToStrategy;

public class InsuranceStrategyHigh extends InsuranceStrategy {
    @Override
    int getSurplus() {
        return 76500;
    }

    @Override
    double getWeight() {
        return 0.1;
    }

    @Override
    int getAdjustment() {
        return 30000;
    }
}
