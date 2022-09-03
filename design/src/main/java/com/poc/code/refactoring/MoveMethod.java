package com.poc.code.refactoring;

public class MoveMethod {
    public void methodToBeMoved() {
        int parameter = 10;
        System.out.println("methodToBeMoved" + parameter);
    }
}

class MoveTo {
    public MoveTo() {
    }

    public void methodUsage() {
    }
}
