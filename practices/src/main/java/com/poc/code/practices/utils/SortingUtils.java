package com.poc.code.practices.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingUtils {
    public static List<Integer> sortArrayAOnArrayB(List<Integer> A, List<Integer> B) {
        Collections.sort(A, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return B.get(A.indexOf(o1)) - B.get(A.indexOf(o2));
            }
        });
        return A;
    }

    public static List<Integer> sortArrayAOnArrayBV2(List<Integer> A, List<Integer> B) {
        Collections.sort(A, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return B.get(A.indexOf(o1)).compareTo(B.get(A.indexOf(o2)));
            }
        });
        return A;
    }
}
