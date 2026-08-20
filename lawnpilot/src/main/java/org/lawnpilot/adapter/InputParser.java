package org.lawnpilot.adapter;

import org.lawnpilot.application.MowerData;
import org.lawnpilot.application.ParsedInput;
import org.lawnpilot.domain.Direction;
import org.lawnpilot.domain.Lawn;
import org.lawnpilot.domain.Mower;

import java.util.ArrayList;
import java.util.List;

public class InputParser {


    public ParsedInput parse(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }

        Lawn lawn = parseLawn(lines.get(0));
        List <String> mowerLines = lines.subList(1, lines.size());
        List<MowerData> mowers = parseMowers(mowerLines);

        return new ParsedInput(lawn, mowers);
    }


    public Lawn parseLawn(String line) {
        String[] values = line.split(" ");

        int maxX = Integer.parseInt(values[0]);
        int maxY = Integer.parseInt(values[1]);

        return new Lawn(maxX, maxY);
    }

    private List<MowerData> parseMowers(List<String> lines) {
        if (lines.size() % 2 != 0) {
            throw new IllegalArgumentException(
                    "Every mower position must be followed by instructions"
            );
        }

        List<MowerData> mowers = new ArrayList<>();

        for (int index = 0; index < lines.size(); index += 2) {
            Mower mower = parseMower(lines.get(index));
            String instructions = lines.get(index + 1);

            mowers.add(new MowerData(mower, instructions));
        }

        return mowers;
    }

    private Mower parseMower(String line) {
        String[] values = line.split(" ");

        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        Direction direction = Direction.valueOf(values[2]);

        return new Mower(x, y, direction);
    }


}
