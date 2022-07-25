package main;

import main.exceptions.*;

public class MatchesHandler {

    private final Scoreboard scoreboard;
    private final MatchesRecords matchesRecords;

    public MatchesHandler() {
        scoreboard = new Scoreboard();
        matchesRecords = new MatchesRecords();
    }

    public void startMatch(String homeTeamCountry, String awayTeamCountry)
            throws EqualCountriesException, MatchAlreadyExistsException {

    }

    public void updateScore(String country) throws MatchNotFoundException, WrongTeamInMatchException, UpdateFinishedMatchException {
    }

    public void finishGame(String homeTeamCountry, String awayTeamCountry) throws MatchNotFoundException {

    }

    public void displayScoreboard() {

    }

    public void displaySummary() {

    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public MatchesRecords getMatchesRecords() {
        return matchesRecords;
    }

}
