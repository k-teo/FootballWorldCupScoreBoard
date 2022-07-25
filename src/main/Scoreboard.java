package main;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard implements IMatchesKeeper {

    List<Match> matches;

    public Scoreboard() {
        matches = new ArrayList<>();
    }

    @Override
    public void addMatch(Match match) {
        matches.add(match);
    }

    public void removeMatch(Match match) {
        matches.remove(match);
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
