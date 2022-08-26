package com.poc.code.ps.graph;

import com.poc.code.ds.DisjointSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/*
https://leetcode.com/problems/connecting-cities-with-minimum-cost/
There are n cities labeled from 1 to n. You are given the integer n and an array connections where
connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection)
 is costi.

Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities.
 If it is impossible to connect all the n cities, return -1,
The cost is the sum of the connections' costs used.


Example 1:
Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.

Example 2:
Input: n = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: There is no way to connect all cities even if all edges are used.

 */
public class ConnectCitiesMinimumCost {
    public int minimumCost(int n, int[][] connections) {
        int[] finalCost = new int[n + 1];
        int[][] cost = new int[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];
        Map<Integer, List<Integer>> adj = new HashMap();
        IntStream.range(1, n).forEach(i -> {
            finalCost[i] = Integer.MAX_VALUE;
        });
        Arrays.stream(connections).forEach(conn -> {
            int v1 = conn[0];
            int v2 = conn[1];
            List<Integer> conn1 = adj.getOrDefault(v1, new ArrayList<>());
            List<Integer> conn2 = adj.getOrDefault(v2, new ArrayList<>());
            conn1.add(v2);
            conn2.add(v1);
            adj.put(v1, conn1);
            adj.put(v2, conn2);
            cost[v1][v2] = conn[2];
            cost[v2][v1] = conn[2];
        });
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(1);
        finalCost[1] = 0;
        while (queue.size() > 0) {
            int v = queue.getFirst();
            queue.removeFirst();
            if (!visited[v]) {
                visited[v] = true;
                for (int v1 : adj.get(v)) {
                    if (finalCost[v] + cost[v][v1] < finalCost[v1]) {
                        finalCost[v1] = finalCost[v] + cost[v][v1];
                    }
                    queue.addLast(v1);
                }
            }
        }
        if (finalCost[n] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return finalCost[n];
        }
    }

    /*
    Time Complexity: O(M)*k where M is number of edges
    Space Complexity: O(2N)
     */
    public int minimumCostUsingDisjointSet(int n, int[][] connections) {
        DisjointSet ds = new DisjointSet(n + 1);
        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger cost = new AtomicInteger(0);
        Arrays.sort(connections, (o1, o2) -> o1[2] - o2[2]);
        Arrays.stream(connections).forEach(conn -> {
            int v1 = conn[0];
            int v2 = conn[1];
            if (!ds.isInSameSet(v1, v2)) {
                ds.union(v1, v2);
                count.getAndIncrement();
                cost.getAndAdd(conn[2]);
            }
        });
        if (count.get() == n - 1) {
            return cost.get();
        } else {
            return -1;
        }
    }
}
