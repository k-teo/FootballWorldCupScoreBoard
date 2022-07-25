package main.exceptions;

public class WrongTeamInMatchException extends FootballScoreBoardException {

    public WrongTeamInMatchException(String message) {
        super(message);
    }
}
