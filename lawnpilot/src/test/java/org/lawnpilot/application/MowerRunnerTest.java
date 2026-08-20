package org.lawnpilot.application;

import org.junit.jupiter.api.Test;
import org.lawnpilot.domain.Direction;
import org.lawnpilot.domain.Lawn;
import org.lawnpilot.domain.Mower;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerRunnerTest {

    private final MowerRunner mowerRunner = new MowerRunner();

    @Test
    void runEveryMowerAndReturnThemInInputOrderWithCorrectPostion() {
        ParsedInput input = new ParsedInput(
                new Lawn(5,5),
                List.of(
                        new MowerData(new Mower(0,0,Direction.N), "F"),
                        new MowerData(new Mower(4,4,Direction.E), "F")
                )
        );

        List<Mower> mowers = mowerRunner.execute(input);

        assertEquals(2, mowers.size());
        assertPosition(mowers.get(0), 0, 1 , Direction.N);
        assertPosition(mowers.get(1), 5,4, Direction.E);

    }

    @Test
    void returnsEmptyListIfNoMowers() {
        ParsedInput input = new ParsedInput(
                new Lawn(5,5),
                List.of()
        );

        assertEquals(List.of(), mowerRunner.execute(input));
    }

    private void assertPosition(Mower mower, int x, int y, Direction direction) {
        assertEquals(x, mower.getX(), "x");
        assertEquals(y, mower.getY(), "y");
        assertEquals(direction, mower.getDirection(), "direction");
    }
}