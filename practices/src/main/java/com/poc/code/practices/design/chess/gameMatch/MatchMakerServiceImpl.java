package com.poc.code.practices.design.chess.gameMatch;

import com.poc.code.practices.design.chess.Level;
import com.poc.code.practices.design.chess.PlayRequest;
import com.poc.code.practices.design.chess.Player;
import com.poc.code.practices.design.chess.PlayerMatch;

import java.time.Instant;
import java.util.Optional;
import java.util.logging.Logger;

public class MatchMakerServiceImpl implements MatchMakerService {
    private static final Logger logger = Logger.getLogger(MatchMakerService.class.getName());

    private IntermediateQueue intermediateQueue;
    private ProQueue proQueue;
    private BeginnerQueue beginnerQueue;

    public MatchMakerServiceImpl(IntermediateQueue intermediateQueue,
                                 ProQueue proQueue,
                                 BeginnerQueue beginnerQueue) {
        this.intermediateQueue = intermediateQueue;
        this.proQueue = proQueue;
        this.beginnerQueue = beginnerQueue;
    }

    /*
        Problem statement
        1000 users requested to play
    */
    @Override
    public Optional<PlayerMatch> match(Player player) throws IllegalArgumentException {
        Optional<PlayerMatch> playerMatchOptional = Optional.empty();
        if (player.getLevel() == Level.PRO) {
            Optional<PlayRequest> optionalPlayRequest = this.proQueue.pop();
            if (optionalPlayRequest.isPresent()) {
                playerMatchOptional = Optional.of(new PlayerMatch(player, optionalPlayRequest.get().getPlayer()));
                logger.info(String.format("Player matched: %s", playerMatchOptional.get()));
            } else {
                this.proQueue.push(new PlayRequest(player, Level.PRO, Instant.now()));
                logger.info(String.format("Player pushed to PRO waiting queue: %s", player));
            }
        } else if (player.getLevel() == Level.INTERMEDIATE) {
            Optional<PlayRequest> optionalPlayRequest = this.intermediateQueue.pop();
            if (optionalPlayRequest.isPresent()) {
                playerMatchOptional = Optional.of(new PlayerMatch(player, optionalPlayRequest.get().getPlayer()));
                logger.info(String.format("Player matched: %s", playerMatchOptional.get().toString()));
            } else {
                this.intermediateQueue.push(new PlayRequest(player, Level.INTERMEDIATE, Instant.now()));
                logger.info(String.format("Player pushed to Intermediate waiting queue: %s", player));
            }
        } else if (player.getLevel() == Level.BEGINNER) {
            Optional<PlayRequest> optionalPlayRequest = this.beginnerQueue.pop();
            if (optionalPlayRequest.isPresent()) {
                playerMatchOptional = Optional.of(new PlayerMatch(player, optionalPlayRequest.get().getPlayer()));
                logger.info(String.format("Player matched: %s", playerMatchOptional.get()));
            } else {
                this.beginnerQueue.push(new PlayRequest(player, Level.BEGINNER, Instant.now()));
                logger.info(String.format("Player pushed to pro waiting queue: %s", player));
            }
        } else {
            throw new IllegalArgumentException("Not a valid type of match level");
        }
        return playerMatchOptional;
    }
}
