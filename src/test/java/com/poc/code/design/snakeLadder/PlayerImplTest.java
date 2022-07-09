package com.poc.code.design.snakeLadder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void getCurrentPos() {
        PlayerImpl player = new PlayerImpl("name");
        Assertions.assertEquals("name", player.getName());
    }

    @Test
    public void setCurrentPos() {
        PlayerImpl player = new PlayerImpl("name");
        player.setName("newName");
        Assertions.assertEquals("newName", player.getName());
    }

    @Test
    void setName() {
    }

    @Test
    void getName() {
    }
}