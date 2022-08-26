package com.poc.code.ps.misc;

import java.util.*;

/*
/**
 * For a list of votes, return an ordered set of candidate in descending order of their votes.
 1st pref : 3 point
 2nd pref : 2 points
 3rd pref : 1 point
 */
public class Voting {
    public List<String> findWinner(List<Vote> votes) {
        Map<String, VotingDetails> candidateVotesWeight = new HashMap<>();
        int globalPos = 0;
        for (int i = 0; i < votes.size(); i++) {
            Vote vote = votes.get(i);
            for (int pos = 0; pos < vote.candidateVoting.size(); pos++) {
                String candidate = vote.candidateVoting.get(pos);
                int voteWeight = VotingWeight.getPrefWeight(pos);
                VotingDetails votingDetails = candidateVotesWeight.getOrDefault(candidate, new VotingDetails());
                votingDetails.weightedVote += voteWeight;
                votingDetails.pos = globalPos;
                globalPos++;
                candidateVotesWeight.put(candidate, votingDetails);
            }
        }
        List<String> candidates = new ArrayList<>(candidateVotesWeight.keySet());
        Collections.sort(candidates, (c1, c2) -> {
            if (candidateVotesWeight.get(c1).weightedVote == candidateVotesWeight.get(c2).weightedVote)
                return candidateVotesWeight.get(c1).pos - candidateVotesWeight.get(c2).pos;
            return candidateVotesWeight.get(c2).weightedVote - candidateVotesWeight.get(c1).weightedVote;
        });
        return candidates;
    }

    static class VotingDetails {
        public int weightedVote;
        public int pos;


    }


    /*
    Vote : List.Of("C1", "C2", "C3")
     */
    static class Vote {
        public List<String> candidateVoting;

        private Vote(List<String> candidateVoting) {
            this.candidateVoting = candidateVoting;
        }

        public static Vote of(List<String> candidateVoting) {
            return new Vote(candidateVoting);
        }
    }

    private enum VotingWeight {
        PREF_1(3), PREF_2(2), PREF_3(1);

        VotingWeight(int value) {
            this.value = value;
        }

        public int value;

        public static int getPrefWeight(int pos) {
            switch (pos) {
                case 0:
                    return PREF_1.value;
                case 1:
                    return PREF_2.value;
                case 2:
                    return PREF_3.value;
                default:
                    throw new IllegalArgumentException("Not a valid argument");
            }
        }

    }
}
