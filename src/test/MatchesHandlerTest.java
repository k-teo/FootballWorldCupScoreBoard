package test;


import main.MatchesHandler;
import main.Team;
import main.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchesHandlerTest {

    private MatchesHandler matchesHandler;
    private static final String POLAND = "Poland";
    private static final String GERMANY = "Germany";

    @BeforeEach
    void init() {
        matchesHandler = new MatchesHandler();
    }

    @Test
    void startMatchTest() throws MatchAlreadyExistsException, EqualCountriesException {
        // given

        // when
        matchesHandler.startMatch(POLAND, GERMANY);

        // then
        assertEquals(1, matchesHandler.getMatchesRecords().getMatches().size());
        assertEquals(1, matchesHandler.getScoreboard().getMatches().size());
        assertEquals(new Team(POLAND), matchesHandler.getScoreboard().getMatches().get(0).getHomeTeam());
        assertEquals(new Team(GERMANY), matchesHandler.getScoreboard().getMatches().get(0).getAwayTeam());
    }

    @Test
    void startMatchThrowsEqualCountriesExceptionTest() {
        // given

        // when
        Throwable thrown = assertThrows(EqualCountriesException.class, () -> matchesHandler.startMatch(POLAND, POLAND));

        // then
        assertEquals("Home Team and Away Team countries should be different.", thrown.getMessage());
        assertEquals(0, matchesHandler.getMatchesRecords().getMatches().size());
        assertEquals(0, matchesHandler.getScoreboard().getMatches().size());
    }

    @Test
    void startMatchThrowsMatchAlreadyExistsExceptionTest() throws MatchAlreadyExistsException, EqualCountriesException {
        // given
        matchesHandler.startMatch(POLAND, GERMANY);

        // when
        Throwable thrown = assertThrows(
                MatchAlreadyExistsException.class,
                () -> matchesHandler.startMatch(POLAND, GERMANY));

        // then
        assertEquals("Cannot start ongoing match", thrown.getMessage());
        assertEquals(1, matchesHandler.getMatchesRecords().getMatches().size());
        assertEquals(1, matchesHandler.getScoreboard().getMatches().size());
        assertEquals(new Team(POLAND), matchesHandler.getScoreboard().getMatches().get(0).getHomeTeam());
        assertEquals(new Team(GERMANY), matchesHandler.getScoreboard().getMatches().get(0).getAwayTeam());
    }

    @Test
    void updateScoreTest() throws FootballScoreBoardException {
        // given
        matchesHandler.startMatch(POLAND, GERMANY);

        // when
        matchesHandler.updateScore(POLAND);

        // then
        assertEquals(new Team(POLAND), matchesHandler.getScoreboard().getMatches().get(0).getHomeTeam());
        assertEquals(new Team(GERMANY), matchesHandler.getScoreboard().getMatches().get(0).getAwayTeam());
        assertEquals(1, matchesHandler.getScoreboard().getMatches().get(0).getScore().getHomeTeamScore());
        assertEquals(0, matchesHandler.getScoreboard().getMatches().get(0).getScore().getAwayTeamScore());
    }

    @Test
    void updateScoreThrowsMatchNotFoundExceptionTest() {
        // given

        // when
        Throwable thrown = assertThrows(MatchNotFoundException.class, () -> matchesHandler.updateScore(POLAND));

        // then
        assertEquals("Match cannot be found for team: " + POLAND, thrown.getMessage());
        assertTrue(matchesHandler.getMatchesRecords().getMatches().isEmpty());
        assertTrue(matchesHandler.getScoreboard().getMatches().isEmpty());
    }

    @Test
    void finishGameTest() throws MatchNotFoundException, EqualCountriesException, MatchAlreadyExistsException {
        // given
        matchesHandler.startMatch(POLAND, GERMANY);

        // when
        matchesHandler.finishGame(POLAND, GERMANY);

        // then
        assertEquals(0, matchesHandler.getScoreboard().getMatches().size());
        assertEquals(1, matchesHandler.getMatchesRecords().getMatches().size());
        assertTrue(matchesHandler.getMatchesRecords().getMatches().get(0).isFinished());
    }

    @Test
    void finishGameThrowsMatchNotFoundExceptionTest() {
        // given

        // when
        Throwable thrown = assertThrows(
                MatchNotFoundException.class,
                () -> matchesHandler.finishGame(POLAND, GERMANY));

        // then
        assertEquals("Match cannot be found for teams: " + POLAND + " " + GERMANY, thrown.getMessage());
        assertTrue(matchesHandler.getMatchesRecords().getMatches().isEmpty());
        assertTrue(matchesHandler.getScoreboard().getMatches().isEmpty());
    }
}
