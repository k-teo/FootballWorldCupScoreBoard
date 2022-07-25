package test;


import main.MatchesRecords;
import main.Match;
import main.Team;
import main.exceptions.UpdateFinishedMatchException;
import main.exceptions.WrongTeamInMatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchesRecordsTest {

    private MatchesRecords matchesRecords;

    private static final String POLAND = "Poland";
    private static final String GERMANY = "Germany";
    private static final String RUSSIA = "Russia";
    private static final String ENGLAND = "England";
    private static final String SPAIN = "Spain";
    private static final String PORTUGAL = "Portugal";

    @BeforeEach
    void init() {
        matchesRecords = new MatchesRecords();
    }

    @Test
    void addMatchTest() {
        // given
        Match match = new Match(new Team(POLAND), new Team(GERMANY));

        // when
        matchesRecords.addMatch(match);
        List<Match> matches = matchesRecords.getMatches();

        // then
        assertEquals(1, matches.size());
        assertTrue(matches.contains(match));
    }

    @Test
    void displayMatches() throws InterruptedException, WrongTeamInMatchException, UpdateFinishedMatchException {
        // given
        Match match1 = new Match(new Team(POLAND), new Team(GERMANY));
        match1.updateScore(new Team(POLAND));
        Thread.sleep(1500);
        Match match2 = new Match(new Team(RUSSIA), new Team(ENGLAND));
        Thread.sleep(1500);
        Match match3 = new Match(new Team(SPAIN), new Team(PORTUGAL));
        match3.updateScore(new Team(PORTUGAL));

        matchesRecords.addMatch(match1);
        matchesRecords.addMatch(match2);
        matchesRecords.addMatch(match3);

        // when
        List<Match> matches = matchesRecords.getMatchesToDisplay();

        // then
        assertEquals(3, matches.size());
        assertTrue(matches.contains(match1));
        assertTrue(matches.contains(match2));
        assertTrue(matches.contains(match3));
        assertEquals(match3, matches.get(0));
        assertEquals(match1, matches.get(1));
        assertEquals(match2, matches.get(2));
    }
}
