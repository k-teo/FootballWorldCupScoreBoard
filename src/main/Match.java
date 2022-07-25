package main;


import main.exceptions.UpdateFinishedMatchException;
import main.exceptions.WrongTeamInMatchException;

import java.time.Instant;
import java.util.Date;

public class Match {

    private final Score score;
    private final Team homeTeam;
    private final Team awayTeam;
    private final Date dateOfStart;
    private boolean isFinished;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        score = new Score();
        isFinished = false;
        dateOfStart = Date.from(Instant.now());
    }

    public void finishGame(){
        isFinished = true;
    }

    public void updateScore(Team team) throws WrongTeamInMatchException, UpdateFinishedMatchException {
        if (!isFinished) {
            if(team.equals(homeTeam)) {
                score.updateScore(1, 0);
            } else if (team.equals(awayTeam)) {
                score.updateScore(0, 1);
            } else {
                throw new WrongTeamInMatchException(team.getCountry() + " does not match any team in a match");
            }
        } else {
            throw new UpdateFinishedMatchException("A finished match cannot be updated");
        }
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Score getScore() {
        return score;
    }

    public int getTotalScore() {
        return score.getTotalScore();
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    @Override
    public String toString() {
        return homeTeam.getCountry() + " " + score.getHomeTeamScore() + " - "
                + awayTeam.getCountry() + " " + score.getAwayTeamScore();
    }
}
