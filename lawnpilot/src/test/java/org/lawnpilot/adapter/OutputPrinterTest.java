package org.lawnpilot.adapter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputPrinterTest {

    private final PrintStream originalOutput = System.out;

    private ByteArrayOutputStream capturedOutput;
    private OutputPrinter outputPrinter;

    @BeforeEach
    void captureStandardOutput() {
        capturedOutput = new ByteArrayOutputStream();

        System.setOut(
                new PrintStream(
                        capturedOutput,
                        true,
                        StandardCharsets.UTF_8
                )
        );

        outputPrinter = new OutputPrinter();
    }

    @AfterEach
    void restoreStandardOutput() {
        System.setOut(originalOutput);
    }

    @Test
    void printsEveryPositionOnANewLine() {
        List<String> positions = List.of(
                "1 3 N",
                "5 1 E"
        );

        outputPrinter.printPositions(positions);

        assertEquals(
                """
                1 3 N
                5 1 E
                """,
                actualOutput()
        );
    }

    private String actualOutput() {
        return capturedOutput
                .toString(StandardCharsets.UTF_8)
                .replace("\r\n", "\n");
    }
}