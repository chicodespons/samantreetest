package org.lawnpilot;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {


    private final InputParser parser = new InputParser();


    @Test
    void parsesThreeMowers() {
        List<String> lines = List.of(
                "10 10",
                "0 0 N",
                "FF",
                "1 1 E",
                "RF",
                "2 2 S",
                "LLF"
        );

        ParsedInput input = parser.parse(lines);

        assertEquals(3, input.mowers().size());
        assertEquals("LLF", input.mowers().get(2).instructions());
    }


    @Test
    void rejectsEmptyFile() {
        List<String> lines = List.of();

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(lines));
    }

    @Test
    void rejectsUnknownDirection() {
        List<String> lines = List.of(
                "5 5",
                "1 2 X",
                "LFLF"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(lines)
        );
    }

    @Test
    void rejectsMowerWithoutInstructions() {
        List<String> lines = List.of(
                "5 5",
                "1 2 N"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(lines)
        );
    }
}