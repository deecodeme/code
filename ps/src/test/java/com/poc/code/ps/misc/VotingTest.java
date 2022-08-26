package com.poc.code.ps.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class VotingTest {

    @Test
    void findWinner() {
        Voting obj = new Voting();
        List<String> actual =  obj.findWinner(
                List.of(
                        Voting.Vote.of(List.of("c1", "c2", "c3")),
                        Voting.Vote.of(List.of("c1", "c2", "c3"))
                ));
        List<String> expected = List.of("c1", "c2", "c3");
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void findWinnerWithRankCollision() {
        Voting obj = new Voting();
        List<String> actual =  obj.findWinner(
                List.of(
                        Voting.Vote.of(List.of("c1", "c2", "c3")),
                        Voting.Vote.of(List.of("c2", "c1", "c3"))
                ));
        List<String> expected = List.of("c2", "c1", "c3");
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}