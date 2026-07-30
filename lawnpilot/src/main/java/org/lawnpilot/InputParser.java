package org.lawnpilot;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

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

    public static class MowerData {
        private Mower mower;
        private String instructions;

        public MowerData(Mower mower, String instructions) {
            this.mower = mower;
            this.instructions = instructions;
        }

        public Mower getMower() {
            return mower;
        }

        public String getInstructions() {
            return instructions;
        }
    }
}
