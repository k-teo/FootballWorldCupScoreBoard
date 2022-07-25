package test;

import main.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    private Score score;

    @BeforeEach
    void init() {
        score = new Score();
    }

    @Test
    void updateScore() {
        // given
        int homeTeamScore = 1;
        int awayTeamScore = 2;
        int totalScore = homeTeamScore + awayTeamScore;

        // when
        score.updateScore(homeTeamScore, awayTeamScore);

        // then
        assertEquals(homeTeamScore, score.getHomeTeamScore());
        assertEquals(awayTeamScore, score.getAwayTeamScore());
        assertEquals(totalScore, score.getTotalScore());
    }

    @Test
    void getHomeTeamScore() {
        // given
        int expectedHomeTeamScore = 4;
        score.updateScore(expectedHomeTeamScore, 0);

        // when
        int actualHomeTeamScore = score.getHomeTeamScore();

        // then
        assertEquals(expectedHomeTeamScore, actualHomeTeamScore);
    }

    @Test
    void getAwayTeamScore() {
        // given
        int expectedAwayTeamScore = 6;
        score.updateScore(0, expectedAwayTeamScore);

        // when
        int actualAwayTeamScore = score.getAwayTeamScore();

        // then
        assertEquals(expectedAwayTeamScore, actualAwayTeamScore);
    }

    @Test
    void getGameScore() {
        // given
        int homeTeamScore = 2;
        int awayTeamScore = 3;
        int expectedTotalScore = homeTeamScore + awayTeamScore;
        score.updateScore(homeTeamScore, awayTeamScore);

        // when
        int actualHomeTeamScore = score.getTotalScore();

        // then
        assertEquals(expectedTotalScore, actualHomeTeamScore);
    }

}
