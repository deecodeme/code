package com.poc.code.ps.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/*
https://leetcode.com/problems/course-schedule-ii/
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers,
return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 */
public class CourseScheduleII {
    //TODO: incorrect output
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> out = new ArrayList<>();
        int[] indegree = new int[numCourses];
        boolean[] visited = new boolean[numCourses];
        Map<Integer, List<Integer>> dep = new HashMap<>();
        Arrays.stream(prerequisites).forEach(pr -> {
            indegree[pr[0]]++;
            List<Integer> t = dep.getOrDefault(pr[1], new ArrayList<>());
            t.add(pr[0]);
            dep.put(pr[1], t);
        });
        while (true) {
            OptionalInt vOpt = IntStream.range(0, numCourses - 1).filter(k -> indegree[k] == 0 && !visited[k]).findFirst();
            if (vOpt.isEmpty()) {
                break;
            }
            int v = vOpt.getAsInt();
            out.add(v);
            dep.get(v).stream().forEach(v1 -> indegree[v1]--);
            visited[v] = true;
        }
        return out.size() != numCourses ? new int[]{} : out.stream().mapToInt(Integer::intValue).toArray();
    }
}
