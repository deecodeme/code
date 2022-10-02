package com.poc.code.practices.design.snakeLadder;

import com.poc.code.practices.design.snakeLadder.PlayerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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