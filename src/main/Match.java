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

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public int getTotalScore() {
        return -1;
    }

    @Override
    public String toString() {
        return "";
    }
}
