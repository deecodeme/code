package com.poc.code.ps.array;

import java.util.Arrays;

/*
You are given an List of positions of cars as to where they are parked. You are also given an integer K.
 The cars needs to be covered with a roof. You have to find the minimum length of roof that takes to cover K cars.

 Input : 12,6,5,2      K = 3

 Explanation :  There are two possible roofs that can cover K cars. One that covers the car in 2,5,6 parking spots and
 another roof which covers 5,6,12 parking spots. The length of these two roofs are 5 and 8 respectively. Return 5
 since that's the roof with minimum length that covers K cars.
 */
public class MInSizeToCoverKCars {
    /*
    Time Complexity: O(NlogN)
    Space Complexity: O(2)
     */
    public int usingSortingAndSlidingWindow(int[] cars, int k) {
        Arrays.sort(cars);
        int i = 0;
        int j = k - 1;
        int min = Integer.MAX_VALUE;
        while (j < cars.length) {
            if (cars[j] - cars[i] + 1 < min) {
                min = cars[j] - cars[i] + 1;
            }
            i++;
            j++;
        }
        return min;
    }


}
