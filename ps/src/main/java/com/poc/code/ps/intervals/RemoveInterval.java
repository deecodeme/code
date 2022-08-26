package com.poc.code.ps.intervals;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/remove-interval/
 */
public class RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] r) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] i : intervals) {
            if (i[1] <= r[0] || r[1] <= i[0]) { // case 1 not overlapping - add
                result.add(List.of(i[0], i[1]));
            } else if (i[0] >= r[0] && i[1] <= r[1]) { // case 2 - interval completely overlapping - do not add
                continue;
            } else if (r[0] > i[0] && r[1] < i[1]) {// case 3 - removed interval inside interval - divide
                if (r[0] > i[0]) {
                    result.add(List.of(i[0], r[0]));
                }
                if (r[1] < i[1]) {
                    result.add(List.of(r[1], i[1]));
                }
            } else { // case 4 - partial overlapping
                if (r[0] < i[1] && i[0] < r[0]) {
                    result.add(List.of(i[0], r[0]));
                } else if (r[1] > i[0] && r[1] < i[1]) {
                    result.add(List.of(r[1], i[1]));
                } else {
                    throw new IllegalArgumentException("more cases to be handled");
                }
            }
        }
        return result;
    }

//    public List<List<Integer>> removeIntervalUsingIntersectionLogic(int[][] intervals, int[] r) {
//        for (int[] i : intervals) {
//            int lx = Math.max(i[0], r[0]);
//            int rx = Math.min(i[1], r[1]);
//            if ()
//        }
//    }

    public boolean isIntersecting(int[] i1, int[] i2) {
        return i1[1] > i2[0]
                || i2[1] > i1[0]
                || (i1[0] == i2[0] && i1[1] == i2[1]);
    }
}
