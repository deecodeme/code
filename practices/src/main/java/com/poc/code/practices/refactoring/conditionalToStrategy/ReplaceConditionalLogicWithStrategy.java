package com.poc.code.practices.refactoring.conditionalToStrategy;

public class ReplaceConditionalLogicWithStrategy {
    private InsuranceStrategy insuranceStrategy;

    public double calculateInsurance(double income) {
        if (income <= 10000) {
            insuranceStrategy = new InsuranceStrategyLow();
            return insuranceStrategy.getInsuranceAmount(income);
        } else if (income <= 30000) {
            insuranceStrategy = new InsuranceStrategyMedium();
            return insuranceStrategy.getInsuranceAmount(income);
        } else if (income <= 60000) {
            insuranceStrategy = new InsuranceStrategyHigh();
            return insuranceStrategy.getInsuranceAmount(income);
        } else {
            insuranceStrategy = new InsuranceStrategyVeryHigh();
            return insuranceStrategy.getInsuranceAmount(income);
        }
    }

    private double getAmountVeryHigh(double income) {
        return insuranceStrategy.getInsuranceAmount(income);
    }

    private int getSurplus() {
        return insuranceStrategy.getSurplus();
    }

    private double getWeight() {
        return insuranceStrategy.getWeight();
    }

    private int getAdjustment() {
        return insuranceStrategy.getAdjustment();
    }
}
