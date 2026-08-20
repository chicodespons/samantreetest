package org.lawnpilot.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {

    private final InputReader inputReader = new InputReader();

    @TempDir
    Path tempDir;

    @Test
    void returnDefaultDataWhenNoFilenameIsGiven() {
        List<String> lines = inputReader.readInput(new String[0]);

        assertEquals(
                List.of(
                        "5 5",
                        "1 2 N",
                        "LFLFLFLFF",
                        "3 3 E",
                        "FFRFFRFRRF"
                ), lines
        );
    }

    @Test
    void readsAndCleansLinesFromFile() throws IOException {
        Path inputFile = tempDir.resolve("input.txt");

        Files.writeString(
                inputFile,
                """
                      5 5

                    1 2 N
                    LFLFLFLFF

                      3 3 E
                    FFRFFRFRRF
                    """,
                StandardCharsets.UTF_8
        );

        String[] arguments = {inputFile.toString()};
        List<String> lines = inputReader.readInput(arguments);

        assertEquals(
                List.of(
                        "5 5",
                        "1 2 N",
                        "LFLFLFLFF",
                        "3 3 E",
                        "FFRFFRFRRF"
                ), lines
        );
    }

}