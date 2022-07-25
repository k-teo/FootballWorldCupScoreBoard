package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        Comparator<Match> compareByTotalScoreDesc = Comparator.comparingInt(Match::getTotalScore).reversed();
        Comparator<Match> compareByDateOfStartDesc = Comparator.comparing(Match::getDateOfStart).reversed();

        return matches
                .stream()
                .sorted(compareByTotalScoreDesc
                        .thenComparing(compareByDateOfStartDesc))
                .collect(Collectors.toList());
    }
}
