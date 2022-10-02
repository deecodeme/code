package com.poc.code.practices.design.snakeLadder;

public class PlayerImpl implements Player {
    private String name;
    private int pos;

    public PlayerImpl(String name) {
        this.name = name;
        this.pos = 0;
    }

    @Override
    public int getCurrentPos() {
        return this.pos;
    }

    @Override
    public void setCurrentPos(int pos) {
        this.pos = pos;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
