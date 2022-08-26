package com.poc.code.demo.collectionDemo;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {
    private static class Interval {
        public int start;
        public int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        Map<Integer, Integer> sortedMap = new TreeMap<>();
        sortedMap.put(1, 1);
        sortedMap.put(7, 7);
        sortedMap.put(3, 3);
        sortedMap.put(4, 4);
        sortedMap.put(5, 5);

        Iterator<Integer> sortedIterator = sortedMap.keySet().iterator();
        while (sortedIterator.hasNext()) {
            System.out.printf("%s ", sortedIterator.next());
        }
        Map<Interval, Integer> sortedInterval = new TreeMap<>((i, j) -> i.start - j.start);
        sortedInterval.put(new Interval(2, 4), 2);
        sortedInterval.put(new Interval(5, 3), 2);
        sortedInterval.put(new Interval(10, 14), 2);
        sortedInterval.put(new Interval(1, 4), 1);
        sortedInterval.forEach((k, v) -> System.out.println(k.toString()));
    }
}
