package com.poc.code.practices.refactoring;

public class InheritanceToDelegation {
    private final MyMasterClass masterClass = new MyMasterClass();

    void abstractMethod() {
        masterClass.abstractMethod();
    }

    private class MyMasterClass extends MasterClass {
        @Override
        void abstractMethod() {
            System.out.println("abstractMethod in InheritanceToDelegation");
        }
    }
}

abstract class MasterClass {
    abstract void abstractMethod();

    void implementedMethod() {
        System.out.println("implementedMethod in MasterClass");
    }

}
