package com.poc.code.ps.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleIITest {

    @Test
    void findOrder() {
        CourseScheduleII obj = new CourseScheduleII();
        System.out.println(obj.findOrder(2, new int[][]{{1, 0}}));
    }
}