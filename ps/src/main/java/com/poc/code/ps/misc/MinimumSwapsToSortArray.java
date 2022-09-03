package com.poc.code.ps.misc;

import java.util.Arrays;
import java.util.Comparator;

/*
Input: {4, 3, 2, 1}
Output: 2
Explanation: Swap index 0 with 3 and 1 with 2 to
              form the sorted array {1, 2, 3, 4}.

Input: {1, 5, 4, 3, 2}
Output: 2

Input: {4, 3, 2, 1}
Sorted:{1, 2, 3, 4}

Input: {4, 5, 2, 1, 3}
Sorted:{1, 2, 3, 4, 5}

1(0) -> 4()
5 -> 2 -> 3 -> 5
 */
public class MinimumSwapsToSortArray {
    public int usingGraphCycles(int[] input) {
        Position[] pos = new Position[input.length];
        for (int i = 0; i < input.length; i++)
            pos[i] = (new Position(i, input[i]));
        Arrays.sort(pos, Comparator.comparingInt(Position::getVal));
        int totalSwaps = 0;
        boolean[] visited = new boolean[input.length];
        for (int i = 0; i < pos.length; i++) {
            if (visited[i] || pos[i].originalPos == i)
                continue;
            int j = i;
            int cycleSize = 0;
            while (!visited[j]) {
                visited[j] = true;
                j = pos[j].originalPos;
                cycleSize++;
            }
            totalSwaps += cycleSize - 1;
        }
        return totalSwaps;
    }

    private static class Position {
        int originalPos;
        int val;

        public Position(int originalPos, int val) {
            this.originalPos = originalPos;
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
}
