package com.poc.code.practices.design.chess.gameMatch;

import com.poc.code.practices.design.chess.Player;
import com.poc.code.practices.design.chess.PlayerMatch;

import java.util.Optional;

public interface MatchMakerService {
    Optional<PlayerMatch> match(Player player);
}
