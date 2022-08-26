package com.poc.code.ps.misc;

/*
https://leetcode.com/problems/online-election/

You are given two integer arrays persons and times. In an election, the ith vote was cast for persons[i] at time times[i].

For each query at a time t, find the person that was leading the election at time t. Votes cast at time t will count towards our query.
 In the case of a tie, the most recent vote (among tied candidates) wins.

Implement the TopVotedCandidate class:

TopVotedCandidate(int[] persons, int[] times) Initializes the object with the persons and times arrays.
int q(int t) Returns the number of the person that was leading the election at time t according to the mentioned rules.

Example 1:
Input
["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
Output
[null, 0, 1, 1, 0, 0, 1]
 */

import java.util.HashMap;
import java.util.Map;

/*
Problem Solving
[0, 1,  1,  0,  0,  1,  0]
[0, 5, 10, 15, 20, 25, 30]

[0(0:1), 5(0:1, 1:1), 10(0:1, 1:2), 15(0:2, 1:2)]

o(logT)

person range 1 to 5000
 */
public class TopVotedCandidate {
    private int[] times;
    private Map<Integer, Integer> timeWinnerMap = new HashMap<>();

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        Integer currentWinnerVoteCount = -1;
        Integer currentWinnerPerson = -1;
        Map<Integer, Integer> personCurrentVoteCount = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int t = times[i], p = persons[i];
            int v = personCurrentVoteCount.getOrDefault(p, 0) + 1;
            personCurrentVoteCount.put(p, v);
            if (v >= currentWinnerVoteCount) {
                currentWinnerVoteCount = v;
                currentWinnerPerson = p;
            }
            timeWinnerMap.put(t, currentWinnerPerson);
        }
    }

    public int q(int t) {
        return timeWinnerMap.getOrDefault(findJustLesserOrEqualTime(times, t), -1);
    }

    public int findJustLesserOrEqualTime(int[] times, int t) {
        int l = 0;
        int h = times.length-1;
        while (l < h) {
            int m = l + (h - l) / 2 + 1;
            if (times[m] > t) {
                h = m - 1;
            } else {
                l = m;
            }
        }
        return times[l];
    }

}
