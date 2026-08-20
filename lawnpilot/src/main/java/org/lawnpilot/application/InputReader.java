package org.lawnpilot.application;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputReader {

    private static final Logger LOGGER = Logger.getLogger(InputReader.class.getName());
    private static final List<String> DEFAULT_DATA = List.of(
            "5 5",
            "1 2 N",
            "LFLFLFLFF",
            "3 3 E",
            "FFRFFRFRRF"
    );

    public List<String> readInput(String[] args) {
        if (args.length == 0) {
            return DEFAULT_DATA;
        }

        return readFile(args[0]);

    }

    private List<String> readFile(String filename) {
        List<String> cleanedLines = new ArrayList<>();

        try {
            Path path = Path.of(filename);
            List<String> fileLines =
                    Files.readAllLines(path, StandardCharsets.UTF_8);

            for (String line : fileLines) {
                String trimmedLine = line.trim();

                if (!trimmedLine.isEmpty()) {
                    cleanedLines.add(trimmedLine);
                }
            }

        } catch (InvalidPathException e) {

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
        return cleanedLines;
    }


}
