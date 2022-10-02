package com.poc.code.practices.design.snakeLadder;

import com.poc.code.practices.design.snakeLadder.DiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiceImplTest {
    @Test
    public void playTest() {
        DiceImpl dice = new DiceImpl();
        for (int i = 1; i <= 10; i++) {
            Assertions.assertTrue(dice.play() >= 1 && dice.play() <= 6);
        }
    }
}