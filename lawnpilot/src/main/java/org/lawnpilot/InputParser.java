package org.lawnpilot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public ParsedInput parseFile(Path path) throws IOException {
        return parse(readLines(path));
    }

    public ParsedInput parse(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }

        Lawn lawn = parseLawn(lines);
        List<MowerData> mowers = parseMowers(lines.subList(1, lines.size()));
        return new ParsedInput(lawn, mowers);
    }

    private List<String> readLines(Path path) throws IOException {
        List<String> result = new ArrayList<>();
        for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }
        return result;
    }

    public Lawn parseLawn(List<String> lines) {
        String[] values = lines.get(0).split(" ");
        return new Lawn(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

    public List<MowerData> parseMowers(List<String> lines) {
        List<MowerData> r = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += 2) {
            String[] values = lines.get(i).split(" ");
            Mower mower = new Mower(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Direction.valueOf(values[2]));
            r.add(new MowerData(mower, lines.get(i + 1)));
        }
        return r;
    }


}
