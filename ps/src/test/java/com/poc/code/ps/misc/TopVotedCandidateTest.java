package com.poc.code.ps.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopVotedCandidateTest {

    @Test
    void findJustLesserOrEqualTime() {
        int[] times = new int[]{0, 5, 10, 15, 20, 25, 30};
        TopVotedCandidate obj = new TopVotedCandidate(new int[]{}, new int[]{});
        Assertions.assertEquals(0, obj.findJustLesserOrEqualTime(times, 2));
        Assertions.assertEquals(10, obj.findJustLesserOrEqualTime(times, 10));
        Assertions.assertEquals(10, obj.findJustLesserOrEqualTime(times, 12));
        Assertions.assertEquals(30, obj.findJustLesserOrEqualTime(times, 31));
    }
}