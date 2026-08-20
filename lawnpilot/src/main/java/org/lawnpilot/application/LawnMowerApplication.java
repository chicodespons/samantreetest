package org.lawnpilot.application;

import org.lawnpilot.InputParser;
import org.lawnpilot.ParsedInput;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LawnMowerApplication {

    private static final Logger LOGGER =
            Logger.getLogger(LawnMowerApplication.class.getName());

    private static final List<String> DEFAULT_DATA = List.of(
            "5 5",
            "1 2 N",
            "LFLFLFLFF",
            "3 3 E",
            "FFRFFRFRRF"
    );

    private final InputParser inputParser;
    private final MowerRunner mowerRunner;

    public LawnMowerApplication() {
        this.inputParser = new InputParser();
        this.mowerRunner = new MowerRunner();
    }


    public void run(String[] args) {
        ParsedInput input = readInput(args);

        if (input == null) {
            return;
        }

        List<String> finalPositions = mowerRunner.execute(input);
        printPositions(finalPositions);
    }

    private ParsedInput readInput(String[] args) {
        if (args.length == 0) {
            return inputParser.parse(DEFAULT_DATA);
        }
        return readFile(args[0]);
    }

    private ParsedInput readFile(String filename) {
        try {
            Path path = Path.of(filename);
            return inputParser.parseFile(path);
        }catch (InvalidPathException e) {

            LOGGER.log(Level.SEVERE,
                    "Invalid path: " + filename,
                    e);
        } catch (IOException e) {
            LOGGER.log(
                    Level.SEVERE,
                    "Could not read file: " + filename,
                    e
            );
        }
        return null;
    }

    private void printPositions(List<String> finalPositions) {
        for (String position : finalPositions) {
            System.out.println(position);
        }
    }
}
