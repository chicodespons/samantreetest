package org.lawnpilot.application;

import java.util.ArrayList;
import java.util.List;

public class MowerRunner {

    public List<String> execute(ParsedInput parsedInput) {
        List<String> finalPositions = new ArrayList<>();

        for (MowerData mowerData : parsedInput.mowers()) {
            mowerData.mower().execute(
                    mowerData.instructions(),
                    parsedInput.lawn()
            );

            finalPositions.add(mowerData.mower().toString());
        }

        return finalPositions;
    }
}
