package org.lawnpilot.application;

import org.junit.jupiter.api.Test;
import org.lawnpilot.Direction;
import org.lawnpilot.Lawn;
import org.lawnpilot.Mower;
import org.lawnpilot.MowerData;
import org.lawnpilot.ParsedInput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerRunnerTest {

    private final MowerRunner mowerRunner = new MowerRunner();

    @Test
    void executesMowersAndReturnsTheirFinalPositions() {
        Lawn lawn = new Lawn(5, 5);

        MowerData firstMower = new MowerData(
                new Mower(1, 2, Direction.N),
                "LFLFLFLFF"
        );

        MowerData secondMower = new MowerData(
                new Mower(3, 3, Direction.E),
                "FFRFFRFRRF"
        );

        ParsedInput input = new ParsedInput(
                lawn,
                List.of(firstMower, secondMower)
        );

        List<String> positions = mowerRunner.execute(input);

        assertEquals(
                List.of(
                        "1 3 N",
                        "5 1 E"
                ),
                positions
        );
    }
}