package com.poc.code.design.chess.gameMatch;

import com.poc.code.design.chess.Player;
import com.poc.code.design.chess.PlayerMatch;

import java.util.Optional;

public interface MatchMakerService {
    Optional<PlayerMatch> match(Player player);
}
