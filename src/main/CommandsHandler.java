package main;

import main.exceptions.FootballScoreBoardException;

public class CommandsHandler {

    private static final String START_COMMAND = "start";
    private static final String UPDATE_COMMAND = "update";
    private static final String FINISH_COMMAND = "finish";
    private static final String SCOREBOARD_COMMAND = "scoreboard";
    private static final String SUMMARY_COMMAND = "summary";
    private static final String CLOSE_COMMAND = "close";
    private static final String COUNTRY_OF_TEAM = "[country of team]";
    private static final String COUNTRY_OF_HOME_TEAM = "[country of home team]";
    private static final String COUNTRY_OF_AWAY_TEAM = "[country of away team]";

    private boolean isClosed;

    public CommandsHandler() {
        isClosed = false;
    }


    public void readLine(String command, MatchesHandler matchesHandler) {
        String[] commandElements = command.split("-");

        try{
            switch (commandElements[1]) {
                case START_COMMAND      -> matchesHandler.startMatch(commandElements[2], commandElements[3]);
                case UPDATE_COMMAND     -> matchesHandler.updateScore(commandElements[2]);
                case FINISH_COMMAND     -> matchesHandler.finishGame(commandElements[2], commandElements[3]);
                case SCOREBOARD_COMMAND -> matchesHandler.displayScoreboard();
                case SUMMARY_COMMAND    -> matchesHandler.displaySummary();
                case CLOSE_COMMAND      -> closeProgram();
                default                 -> System.err.println("Wrong command");
            }
        } catch (FootballScoreBoardException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Wrong command");
        }
    }

    public void displayInstruction() {
        System.out.println(
                """
                    Hello! You have just opened Football Word Cup Score Board
                    The program was created to show matches and scores. Are you ready to start?
                    Not so fast ;). Firstly, here is a short instruction for you:
                    Possible commands: """);
        System.out.printf("-%s -%s -%s %n", START_COMMAND, COUNTRY_OF_HOME_TEAM, COUNTRY_OF_AWAY_TEAM);
        System.out.printf("-%s -%s %n", UPDATE_COMMAND, COUNTRY_OF_TEAM);
        System.out.printf("-%s -%s -%s %n", UPDATE_COMMAND, COUNTRY_OF_HOME_TEAM, COUNTRY_OF_HOME_TEAM);
        System.out.printf("-%s // displays scoreboard by the date desc %n", SCOREBOARD_COMMAND);
        System.out.printf("-%s // displays all records by total score desc, date desc %n", SUMMARY_COMMAND);
        System.out.printf("-%s // closes program %n%n", CLOSE_COMMAND);
    }

    public boolean isClosed() {
        return isClosed;
    }

    private  void closeProgram() {
        isClosed = true;
    }


}

