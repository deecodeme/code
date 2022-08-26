package com.poc.code.ps.algos;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/first-unique-number/

You have a queue of integers, you need to retrieve the first unique integer in the queue.
Implement the FirstUnique class:
FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1
if there is no such integer.
void add(int value) insert value to the queue.

Example 1:
Input:
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output:
[null,2,null,2,null,3,null,-1]


Problem Solving:
1. Maintain a queue of incoming stream
2. Maintain a HashMap for counter

1. Check the count in hashmap.
1.1 if duplicate, remove the node from the queue
1.2 else, insert into queue
 */
public class FirstUniqueInIntegerStream {
    private Map<Integer, Integer> lookup;
    private Set<Integer> queue;

    public FirstUniqueInIntegerStream(int[] nums) {
        this.lookup = new HashMap<>();
        this.queue = new LinkedHashSet<>();
        for (int n : nums) {
            int count = lookup.getOrDefault(n, 0);
            if (count == 0) {
                lookup.put(n, 1);
                queue.add(n);
            } else {
                lookup.put(n, count + 1);
                this.queue.remove(n);
            }
        }
    }

    public int showFirstUnique() {
        if (queue.isEmpty()) {
            return -1;
        }
        return this.queue.iterator().next();
    }

    public void add(int value) {
        if (!lookup.containsKey(value)) {
            lookup.put(value, 1);
            this.queue.add(value);
        } else {
            this.lookup.put(value, this.lookup.get(value) + 1);
            this.queue.remove(value);
        }
    }
}
