package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        MatchesHandler matchesHandler = new MatchesHandler();
        CommandsHandler commandsHandler = new CommandsHandler();
        commandsHandler.displayInstruction();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {
            commandsHandler.readLine(reader.readLine().replaceAll("\\s+","").toLowerCase(), matchesHandler);
        } while (!commandsHandler.isClosed());
    }
}
