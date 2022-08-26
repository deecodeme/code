package com.poc.code.ps.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MInSizeToCoverKCarsTest {

    @Test
    void usingSlidingWindow() {
        MInSizeToCoverKCars obj = new MInSizeToCoverKCars();
        Assertions.assertEquals(5, obj.usingSortingAndSlidingWindow(new int[]{12, 6, 5, 2}, 3));
        Assertions.assertEquals(8, obj.usingSortingAndSlidingWindow(new int[]{2, 5, 6, 9, 12}, 4));
    }
}