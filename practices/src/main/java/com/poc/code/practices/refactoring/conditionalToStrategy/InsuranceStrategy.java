package com.poc.code.practices.refactoring.conditionalToStrategy;

public abstract class InsuranceStrategy {
    double getInsuranceAmount(double income) {
        return (income - getAdjustment()) * getWeight() + getSurplus();
    }

    abstract int getSurplus();

    abstract double getWeight();

    abstract int getAdjustment();
}
