package com.poc.code.ps.misc;

import java.util.*;

/*
https://leetcode.com/problems/rank-teams-by-votes/
In a special ranking system, each voter gives a rank from highest to lowest to all teams participated
in the competition.The ordering of teams is decided by who received the most position-one votes.
If two or more teams tie in the first position, we consider the second position to resolve the conflict,
if they tie again, we continue this process until the ties are resolved. If two or more teams are still
tied after considering all positions, we rank them alphabetically based on their team letter. Given an
array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according
to the ranking system described above.
Return a string of all teams sorted by the ranking system.

Example 1:
Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
Output: "ACB"
Explanation: Team A was ranked first place by 5 voters. No other team was voted as first place
so team A is the first team.
Team B was ranked second by 2 voters and was ranked third by 3 voters.
Team C was ranked second by 3 voters and was ranked third by 2 voters.
As most of the voters ranked C second, team C is the second team and team B is the third.
 */
public class RankTeamsByVotes {
    public String rankTeams(String[] votes) {
        int teamCounts = votes[0].length();
        Map<Character, int[]> teamVoteMap = new HashMap<>();
        for (String vote : votes) {
            for (int i = 0; i < teamCounts; i++) {
                char team = vote.charAt(i);
                teamVoteMap.putIfAbsent(team, new int[teamCounts]);
                teamVoteMap.get(team)[i]++;
            }
        }
        List<Character> teamNames = new ArrayList<>(teamVoteMap.keySet());
        Collections.sort(teamNames, (t1, t2) -> {
            for (int i = 0; i < teamCounts; i++) {
                if (teamVoteMap.get(t1)[i] != teamVoteMap.get(t2)[i]) {
                    return teamVoteMap.get(t2)[i] - teamVoteMap.get(t1)[i];
                }
            }
            return t1 - t2;
        });
        StringBuilder result = new StringBuilder();
        for (Character team : teamNames) {
            result.append(team);
        }
        return result.toString();
    }
}
