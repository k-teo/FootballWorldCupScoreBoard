package main;

import main.exceptions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchesHandler {

    private final Scoreboard scoreboard;
    private final MatchesRecords matchesRecords;

    public MatchesHandler() {
        scoreboard = new Scoreboard();
        matchesRecords = new MatchesRecords();
    }

    public void startMatch(String homeTeamCountry, String awayTeamCountry)
            throws EqualCountriesException, MatchAlreadyExistsException {

        if (homeTeamCountry.equals(awayTeamCountry)) {
            throw new EqualCountriesException("Home Team and Away Team countries should be different.");
        }

        Team homeTeam = new Team(homeTeamCountry);
        Team awayTeam = new Team(awayTeamCountry);

        if (getTeamsOfOngoingMatches().contains(homeTeam) || getTeamsOfOngoingMatches().contains(awayTeam)) {
            throw new MatchAlreadyExistsException("Cannot start ongoing match");
        }

        Match match = new Match(homeTeam, awayTeam);
        scoreboard.addMatch(match);
        matchesRecords.addMatch(match);
        System.out.println("Match started");
    }

    public void updateScore(String country) throws MatchNotFoundException, WrongTeamInMatchException, UpdateFinishedMatchException {
        Team team = new Team(country);
        getTeamOngoingMatch(team).updateScore(team);
        System.out.println("Score updated");
    }

    public void finishGame(String homeTeamCountry, String awayTeamCountry) throws MatchNotFoundException {
        Team homeTeam = new Team(homeTeamCountry);
        Team awayTeam = new Team(awayTeamCountry);
        Match match = getTeamsOngoingMatch(homeTeam, awayTeam);
        match.finishGame();
        scoreboard.removeMatch(match);
        System.out.println("Match is finished");
    }

    public void displayScoreboard() {
        scoreboard.getMatchesToDisplay().forEach(System.out::println);
    }

    public void displaySummary() {
        matchesRecords.getMatchesToDisplay().forEach(System.out::println);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public MatchesRecords getMatchesRecords() {
        return matchesRecords;
    }

    private Match getTeamsOngoingMatch(Team homeTeam, Team awayTeam) throws MatchNotFoundException {
        return scoreboard.getMatches()
                .stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(() -> new MatchNotFoundException("Match cannot be found for teams: " + homeTeam.getCountry() + " " + awayTeam.getCountry()));

    }

    private Match getTeamOngoingMatch(Team team) throws MatchNotFoundException {
        return scoreboard.getMatches()
                .stream()
                .filter(match -> match.getHomeTeam().equals(team) || match.getAwayTeam().equals(team))
                .findFirst()
                .orElseThrow(() -> new MatchNotFoundException("Match cannot be found for team: " + team.getCountry()));
    }

    private List<Team> getTeamsOfOngoingMatches() {
        return scoreboard.getMatches()
                .stream()
                .flatMap(match -> Stream.of(match.getHomeTeam(), match.getAwayTeam()))
                .collect(Collectors.toList());
    }

}
