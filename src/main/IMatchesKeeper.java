package main;

import java.util.List;

public interface IMatchesKeeper {

    public void addMatch(Match match);

    public List<Match> getMatches();

    public List<Match> getMatchesToDisplay();
}
