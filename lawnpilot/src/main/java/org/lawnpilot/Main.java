package org.lawnpilot;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> data = List.of("5 5", "1 2 N", "LFLFLFLFF", "3 3 E", "FFRFFRFRRF");

        InputParser ip = new InputParser();
        List<InputParser.MowerData> mowers = ip.parseMowers(data.subList(1, data.size()));
        Lawn l = ip.parseLawn(data);

        for (InputParser.MowerData mowerData : mowers) {
            mowerData.getMower().execute(mowerData.getInstructions(), l);
            System.out.println(mowerData.getMower());
        }
    }
}