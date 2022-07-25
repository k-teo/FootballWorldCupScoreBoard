package main;

import java.util.ArrayList;
import java.util.List;

public class MatchesRecords implements IMatchesKeeper {

    List<Match> matches;

    public MatchesRecords() {
        matches = new ArrayList<>();
    }

    @Override
    public void addMatch(Match match) {
        matches.add(match);
    }

    @Override
    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public List<Match> getMatchesToDisplay() {
      return null;
    }
}
