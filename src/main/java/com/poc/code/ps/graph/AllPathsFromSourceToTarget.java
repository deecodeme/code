package com.poc.code.ps.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/all-paths-from-source-to-target/
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to
node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
(i.e., there is a directed edge from node i to node graph[i][j]).

Example 1:
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 */
public class AllPathsFromSourceToTarget {
    /*
    Time Complexity:
    Space Complexity:
     */
    public List<List<Integer>> allPathsSourceTargetBacktracking(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> paths = new ArrayList<>();
        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();
        path.add(0);
        visited[0] = true;
        allPathsSourceTargetBacktracking(graph, paths, visited, 0, n - 1, path);
        return paths;
    }

    /*
    TODO Time Complexity: N*2^N - still need to figure out why
    Space Complexity:

    Number of paths with element N
    N(1) -> 1 = 1
    N(2) -> 1 * N(2-1)= 1 = 2
    N(3) -> 2 * N(3-1) = 2 = 4
    N(4) -> 3 * N(4-1) = 6 = 12

    N(N) = 1 + 1 * N(2-1) +  2 * N(3-1) +  3 * N(4-1)
    N(N)  = 1 + 1* N(1) + 2*N(2) + 3*N(3)...+ (N-1)*N(N-1)
    N(N) =
    */
    private void allPathsSourceTargetBacktracking(int[][] graph, List<List<Integer>> paths, boolean[] visited, int v, int target, List<Integer> path) {
        if (v == target) {
            paths.add(List.copyOf(path));
        }

        for (int v1 : graph[v]) {
            if (!visited[v1]) {
                path.add(v1);
                visited[v1] = true;
                allPathsSourceTargetBacktracking(graph, paths, visited, v1, target, path);
                path.remove(path.size() - 1);
                visited[v1] = false;
            }
        }
    }

    /*
    Time Complexity:
    Space Complexity:
     */
    public List<List<Integer>> allPathsSourceTargetDP(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Map<Integer, List<List<Integer>>> cache = new HashMap<>();
        cache.put(n - 1, List.of(List.of(n - 1)));
        return allPathsSourceTargetDpUtil(graph, 0, cache);
    }

    /*
    Time Complexity:
    Space Complexity:
    */
    private List<List<Integer>> allPathsSourceTargetDpUtil(int[][] graph, int v, Map<Integer, List<List<Integer>>> cache) {
        if (cache.containsKey(v)) {
            return cache.get(v);
        }
        List<List<Integer>> output = new ArrayList<>();
        for (int v1 : graph[v]) {
            List<List<Integer>> next = allPathsSourceTargetDpUtil(graph, v1, cache);
            for (List<Integer> l : next) {
                List<Integer> t = new ArrayList<>();
                t.add(v);
                t.addAll(l);
                output.add(t);
            }
        }
        return output;
    }
}
