package com.poc.code.practices.design.chess.gameMatch;

import com.poc.code.practices.design.chess.Level;
import com.poc.code.practices.design.chess.Player;
import com.poc.code.practices.design.chess.gameMatch.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchMakerServiceImplTest {
    private ProQueue proQueue;

    private IntermediateQueue intermediateQueue;

    private BeginnerQueue beginnerQueue;

    private MatchMakerService matchMakerService;

    @BeforeEach
    void setUp() {
        this.proQueue = new ProQueue();
        this.intermediateQueue = new IntermediateQueue();
        this.beginnerQueue = new BeginnerQueue();
        this.matchMakerService = new MatchMakerServiceImpl(intermediateQueue, proQueue, beginnerQueue);
    }

    @Test
    void match() {
        this.matchMakerService.match(new Player("player1", Level.PRO));
        this.matchMakerService.match(new Player("player2", Level.INTERMEDIATE));
        this.matchMakerService.match(new Player("player3", Level.BEGINNER));
        this.matchMakerService.match(new Player("player4", Level.PRO));
        this.matchMakerService.match(new Player("player5", Level.INTERMEDIATE));
        this.matchMakerService.match(new Player("player6", Level.BEGINNER));
    }
}