package test;


import main.Match;
import main.Team;
import main.exceptions.UpdateFinishedMatchException;
import main.exceptions.WrongTeamInMatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchTest {

    private static final String HOME_TEAM_COUNTRY = "Poland";
    private static final String AWAY_TEAM_COUNTRY = "Germany";
    private Match match;

    @BeforeEach
    void init() {
        match = new Match(new Team(HOME_TEAM_COUNTRY), new Team(AWAY_TEAM_COUNTRY));
    }

    @Test
    void finishGameTest(){
        // given

        // when
        match.finishGame();

        // then
        assertTrue(match.isFinished());

    }

    @Test
    void updateScoreTest() throws WrongTeamInMatchException, UpdateFinishedMatchException {
        // given

        // when
        match.updateScore(new Team(HOME_TEAM_COUNTRY));
        match.updateScore(new Team(AWAY_TEAM_COUNTRY));
        match.updateScore(new Team(AWAY_TEAM_COUNTRY));

        // then
        assertEquals(1, match.getScore().getHomeTeamScore());
        assertEquals(2, match.getScore().getAwayTeamScore());
        assertEquals(3, match.getTotalScore());
        assertFalse(match.isFinished());
    }

    @Test
    void updateScoreThrowsWrongTeamInMatchException() {
        // given
        String country = "Russia";

        // when
        Throwable thrown = assertThrows(
                WrongTeamInMatchException.class,
                () -> match.updateScore(new Team(country)));

        // then
        assertEquals(country + " does not match any team in a match", thrown.getMessage());
        assertEquals(0, match.getScore().getHomeTeamScore());
        assertEquals(0, match.getScore().getAwayTeamScore());
        assertEquals(0, match.getTotalScore());
        assertFalse(match.isFinished());
    }

    @Test
    void updateScoreThrowsUpdateFinishedMatchException() throws WrongTeamInMatchException, UpdateFinishedMatchException {
        // given
        match.updateScore(new Team(HOME_TEAM_COUNTRY));
        match.updateScore(new Team(AWAY_TEAM_COUNTRY));
        match.finishGame();

        // when
        Throwable thrown = assertThrows(
                UpdateFinishedMatchException.class,
                () -> match.updateScore(new Team(HOME_TEAM_COUNTRY)));

        // then
        assertEquals("A finished match cannot be updated", thrown.getMessage());
        assertEquals(1, match.getScore().getHomeTeamScore());
        assertEquals(1, match.getScore().getAwayTeamScore());
        assertEquals(2, match.getTotalScore());
        assertTrue(match.isFinished());
    }

    @Test
    void getHomeTeamTest() {
        // given

        // when
        Team team = match.getHomeTeam();

        // then
        assertEquals(new Team(HOME_TEAM_COUNTRY), team);

    }

    @Test
    void getAwayTeamTest() {
        // given

        // when
        Team team = match.getAwayTeam();

        // then
        assertEquals(new Team(AWAY_TEAM_COUNTRY), team);
    }

    @Test
    void isFinishedTest() {
        // given

        // when
        boolean isFinished = match.isFinished();

        // then
        assertFalse(isFinished);

    }

    @Test
    void getTotalScoreTest() throws WrongTeamInMatchException, UpdateFinishedMatchException {
        // given
        match.updateScore(new Team(HOME_TEAM_COUNTRY));
        match.updateScore(new Team(HOME_TEAM_COUNTRY));
        match.updateScore(new Team(AWAY_TEAM_COUNTRY));
        match.updateScore(new Team(AWAY_TEAM_COUNTRY));
        match.updateScore(new Team(AWAY_TEAM_COUNTRY));

        // when
        int totalScore = match.getTotalScore();

        // then
        assertEquals(2, match.getScore().getHomeTeamScore());
        assertEquals(3, match.getScore().getAwayTeamScore());
        assertEquals(5, totalScore);
    }

    @Test
    void getDateOfStartTest() {
        // given

        // when
        Date date = match.getDateOfStart();

        // then
        assertNotNull(date);
    }

    @Test
    void toStringTest() {
        // given
        String matchExpectedString = "Poland 0 - Germany 0";

        // when
        String machActualString = match.toString();

        // then
        assertEquals(matchExpectedString, machActualString);
    }
}
