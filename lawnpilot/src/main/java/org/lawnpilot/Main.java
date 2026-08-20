package org.lawnpilot;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER =
            Logger.getLogger(Main.class.getName());

    private static final String DEFAULT_DATA_OPTION = "1";
    private static final String FILE_OPTION = "2";

    private static final List<String> DEFAULT_DATA = List.of(
            "5 5",
            "1 2 N",
            "LFLFLFLFF",
            "3 3 E",
            "FFRFFRFRRF"
    );

    public static void main(String[] args) {
        InputParser inputParser = new InputParser();

        try (Scanner scanner = new Scanner(System.in)) {
            ParsedInput input = askForInput(scanner, inputParser);
            executeMowers(input);
        }
    }

    private static ParsedInput askForInput(
            Scanner scanner,
            InputParser inputParser
    ) {
        while (true) {
            printMenu();

            String choice = scanner.nextLine().trim();

            if (DEFAULT_DATA_OPTION.equals(choice)) {
                return inputParser.parse(DEFAULT_DATA);
            }

            if (FILE_OPTION.equals(choice)) {
                ParsedInput input = readFromFile(scanner, inputParser);

                if (input != null) {
                    return input;
                }
            }

            if (!FILE_OPTION.equals(choice)) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Choose an input source:");
        System.out.println("1 - Use default data");
        System.out.println("2 - Read data from a file");
        System.out.print("Your choice: ");
    }

    private static ParsedInput readFromFile(
            Scanner scanner,
            InputParser inputParser
    ) {
        System.out.print("Enter the absolute file path: ");
        String pathInput = scanner.nextLine().trim();

        try {
            Path path = Path.of(pathInput);
            LOGGER.info(() -> "Reading input from: " + path);

            return inputParser.parseFile(path);
        } catch (InvalidPathException e) {
            System.out.println("The file path is invalid. Please try again.");
        } catch (IOException e) {
            System.out.println("The file could not be read. Please try again.");
            LOGGER.warning(() -> "Could not read file: " + pathInput);
        }

        return null;
    }

    private static void executeMowers(ParsedInput input) {
        System.out.println();
        System.out.println("Final mower positions:");

        for (MowerData mowerData : input.mowers()) {
            executeMower(mowerData, input.lawn());
            System.out.println(mowerData.mower());
        }
    }

    private static void executeMower(MowerData mowerData, Lawn lawn) {
        mowerData.mower().execute(
                mowerData.instructions(),
                lawn
        );
    }
}