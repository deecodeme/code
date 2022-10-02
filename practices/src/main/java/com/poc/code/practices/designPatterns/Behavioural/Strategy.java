package com.poc.code.practices.designPatterns.Behavioural;

public class Strategy {
    private Behaviour behaviour;

    public Strategy(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void behave() {
        System.out.println("Checking behaviour");
        behaviour.behave();
    }

    public static void main(String[] args) {
        new Strategy(new BehaviourA()).behave();
        new Strategy(new BehaviourB()).behave();
        new Strategy(new BehaviourC()).behave();
    }
}

interface Behaviour {
    void behave();
}

class BehaviourA implements Behaviour {
    @Override
    public void behave() {
        System.out.println("Behaving as per BehaviourA");
    }
}

class BehaviourB implements Behaviour {
    @Override
    public void behave() {
        System.out.println("Behaving as per BehaviourB");
    }
}

class BehaviourC implements Behaviour {
    @Override
    public void behave() {
        System.out.println("Behaving as per BehaviourC");
    }
}
