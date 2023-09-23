package com.poc.code.practices.demo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    private static class Item implements Comparable {
        public String name;
        public Integer id;

        public Item(String name, Integer id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Item)) {
                throw new RuntimeException("not a value type");
            }

            if (this.id < ((Item) o).id) {
                return -1;
            } else if (this.id > ((Item) o).id) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Item{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
        }
    }

    private static Comparator<Item> BY_ID_SMALLEST_FIRST = (o1, o2) -> {
        if (o1.id < o2.id) {
            return -1;
        } else if (o1.id > o2.id) {
            return 1;
        } else {
            return 0;
        }
    };

    private static Comparator<Item> BY_ID_HIGHEST_FIRST = (o1, o2) -> {
        if (o1.id < o2.id) {
            return 1;
        } else if (o1.id > o2.id) {
            return -1;
        } else {
            return 0;
        }
    };

    public static void main(String[] args) {
        System.out.println("Priority queue demo");
        PriorityQueue<Item> pq = new PriorityQueue<>(BY_ID_HIGHEST_FIRST);
        pq.add(new Item("deepak", 1));
        pq.add(new Item("deepak", 2));
        pq.add(new Item("deepak", 3));

        System.out.println(pq.peek().toString());
        System.out.println("Iterator");
        Iterator<Item> itr = pq.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
