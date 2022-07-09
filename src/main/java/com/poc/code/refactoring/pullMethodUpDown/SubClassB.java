package com.poc.code.refactoring.pullMethodUpDown;

public class SubClassB extends SuperClass {
    public SubClassB() {
        super();
        System.out.println("Constructor SubClassB");
    }

    public void method(){
        System.out.println("Greetings! from method");
    }
}
