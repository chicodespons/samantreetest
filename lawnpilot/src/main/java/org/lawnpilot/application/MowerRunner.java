package org.lawnpilot.application;

import org.lawnpilot.domain.Mower;

import java.util.ArrayList;
import java.util.List;

public class MowerRunner {

    public List<Mower> execute(ParsedInput parsedInput) {
        List<Mower> mowers = new ArrayList<>();

        for (MowerData mowerData : parsedInput.mowers()) {
            mowerData.mower().execute(
                    mowerData.instructions(),
                    parsedInput.lawn()
            );

            mowers.add(mowerData.mower());
        }

        return mowers;
    }
}
