package com.poc.code.ps.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class AllPathsFromSourceToTargetTest {

    @Test
    void allPathsSourceTarget() {
        AllPathsFromSourceToTarget obj = new AllPathsFromSourceToTarget();
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        int[][] actual = (int[][]) obj.allPathsSourceTargetBacktracking(graph).toArray();
        int[][] expected = (int[][]) List.of(List.of(0, 1, 3), List.of(0, 2, 3)).toArray();
        Assertions.assertEquals(expected.length, actual.length);
    }
}