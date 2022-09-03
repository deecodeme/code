package com.poc.code.design.snakeLadder;

import java.util.Random;

public class DiceImpl implements Dice{
    private Random random;

    public DiceImpl(){
        this.random = new Random();
    }

    @Override
    public int play() {
        return this.random.nextInt(6)+1;
    }
}
