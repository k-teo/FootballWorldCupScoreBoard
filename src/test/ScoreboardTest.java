package test;


import main.Match;
import main.Scoreboard;
import main.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScoreboardTest {

    private Scoreboard scoreboard;

    private static final String POLAND = "Poland";
    private static final String GERMANY = "Germany";
    private static final String RUSSIA = "Russia";
    private static final String ENGLAND = "England";

    @BeforeEach
    void init() {
        scoreboard = new Scoreboard();
    }

    @Test
    void addMatchTest() {
        // given
        Match match = new Match(new Team(POLAND), new Team(GERMANY));

        // when
        scoreboard.addMatch(match);
        List<Match> matches = scoreboard.getMatches();

        // then
        assertEquals(1, matches.size());
        assertTrue(matches.contains(match));
    }

    @Test
    void removeMatchTest() {
        // given
        Match match1 = new Match(new Team(POLAND), new Team(GERMANY));
        Match match2 = new Match(new Team(RUSSIA), new Team(ENGLAND));
        scoreboard.addMatch(match1);
        scoreboard.addMatch(match2);

        // when
        scoreboard.removeMatch(match1);

        // then
        List<Match> matches = scoreboard.getMatches();
        assertEquals(1, matches.size());
        assertFalse(matches.contains(match1));
        assertTrue(matches.contains(match2));
    }

    @Test
    void getMatchesTest() {
        // given
        Match match1 = new Match(new Team(POLAND), new Team(GERMANY));
        Match match2 = new Match(new Team(RUSSIA), new Team(ENGLAND));
        scoreboard.addMatch(match1);
        scoreboard.addMatch(match2);

        // when
        List<Match> matches = scoreboard.getMatches();

        // then
        assertEquals(2, matches.size());
        assertTrue(matches.contains(match1));
        assertTrue(matches.contains(match2));
    }

    @Test
    void getMatchesToDisplayTest() throws InterruptedException {
        // given
        Match match1 = new Match(new Team(POLAND), new Team(GERMANY));
        Thread.sleep(1500);
        Match match2 = new Match(new Team(RUSSIA), new Team(ENGLAND));
        scoreboard.addMatch(match1);
        scoreboard.addMatch(match2);

        // when
        List<Match> matches = scoreboard.getMatchesToDisplay();

        // then
        assertEquals(2, matches.size());
        assertTrue(matches.contains(match1));
        assertTrue(matches.contains(match2));
        assertEquals(match2, matches.get(0));
        assertEquals(match1, matches.get(1));
    }


}
