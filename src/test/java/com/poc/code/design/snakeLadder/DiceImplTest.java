package com.poc.code.design.snakeLadder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class DiceImplTest {
    @Test
    public void playTest() {
        DiceImpl dice = new DiceImpl();
        for (int i = 1; i <= 10; i++) {
            Assertions.assertTrue(dice.play() >= 1 && dice.play() <= 6);
        }
    }
}