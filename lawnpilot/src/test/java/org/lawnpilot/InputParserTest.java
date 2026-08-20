package org.lawnpilot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @TempDir
    Path tempDir;

    private final InputParser parser = new InputParser();

    private Path writeFile(String name, String content) throws IOException {
        Path file = tempDir.resolve(name);
        Files.writeString(file, content, StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void parsesThreeMowers() throws IOException {
        Path file = writeFile("three.txt", """
                10 10
                0 0 N
                FF
                1 1 E
                RF
                2 2 S
                LLF
                """);

        ParsedInput input = parser.parseFile(file);

        assertEquals(3, input.mowers().size());
        assertEquals("LLF", input.mowers().get(2).instructions());
    }

    @Test
    void ignoresBlankLinesAndSurroundingWhitespace() throws IOException {
        Path file = writeFile("messy.txt", """
                  5 5  
                
                1 2 N
                LFLF
                
                
                  3 3 E  
                FFRF
                
                """);

        ParsedInput input = parser.parseFile(file);

        assertEquals(2, input.mowers().size());
        assertEquals("FFRF", input.mowers().get(1).instructions());
    }



    @Test
    void rejectsEmptyFile() throws IOException {
        Path file = writeFile("empty.txt", "\n\n\n");

        assertThrows(IllegalArgumentException.class, () -> parser.parseFile(file));
    }

    @Test
    void rejectsUnknownDirection()throws IOException {
        Path file = writeFile("baddir.txt", """
                5 5
                1 2 X
                LFLF
                """);

        assertThrows(IllegalArgumentException.class, () -> parser.parseFile(file));
    }

    @Test
    void rejectsMissingFile() {
        assertThrows(java.io.IOException.class,
                () -> parser.parseFile(tempDir.resolve("does-not-exist.txt")));
    }
}