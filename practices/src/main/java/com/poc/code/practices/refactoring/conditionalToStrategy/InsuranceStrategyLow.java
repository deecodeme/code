package com.poc.code.practices.refactoring.conditionalToStrategy;

public class InsuranceStrategyLow extends InsuranceStrategy {
    @Override
    int getSurplus() {
        return 0;
    }

    @Override
    double getWeight() {
        return 0.365;
    }

    @Override
    int getAdjustment() {
        return 0;
    }
}
