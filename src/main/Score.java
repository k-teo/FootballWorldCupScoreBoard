package main;

public class Score {

    private int homeTeamScore;
    private int awayTeamScore;

    public Score() {
        homeTeamScore = 0;
        awayTeamScore = 0;
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore += homeTeamScore;
        this.awayTeamScore += awayTeamScore;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }



}
