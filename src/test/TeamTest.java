package test;

import main.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamTest {

    @Test
    void getCountryTest() {
        // given
        String expectedCountry = "Poland";
        Team team = new Team(expectedCountry);

        // when
        String actualCountry = team.getCountry();

        // then
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    void equalsTest() {
        // given
        String country = "Poland";
        Team team1 = new Team(country);
        Team team2 = new Team(country);

        // when
        boolean isEqual = team1.equals(team2);

        // then
        assertTrue(isEqual);
    }
}
