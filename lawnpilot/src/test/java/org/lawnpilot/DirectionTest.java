package org.lawnpilot;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @ParameterizedTest
    @CsvSource({"N,E", "E,S", "S,W", "W,N"})
    void testRight(Direction direction, Direction expected) {
        assertEquals(expected, direction.right());
    }

    @ParameterizedTest
    @CsvSource({"N,W", "W,S", "S,E", "E,N"})
    void testLeft(Direction direction, Direction expected) {
        assertEquals(expected, direction.left());
    }

}