package org.lawnpilot.application;

import org.lawnpilot.adapter.InputParser;
import org.lawnpilot.adapter.InputReader;
import org.lawnpilot.adapter.OutputPrinter;
import org.lawnpilot.domain.Mower;

import java.util.List;

public class LawnMowerApplication {

    private final InputReader inputReader;
    private final InputParser inputParser;
    private final MowerRunner mowerRunner;
    private final OutputPrinter outputPrinter;

    public LawnMowerApplication(InputReader inputReader,
                                InputParser inputParser,
                                MowerRunner mowerRunner,
                                OutputPrinter outputPrinter) {
        this.inputReader = inputReader;
        this.inputParser = inputParser;
        this.mowerRunner = mowerRunner;
        this.outputPrinter = outputPrinter;

    }


    public void run(String[] args) {
        List<String> input = inputReader.readInput(args);
        ParsedInput parsedInput = inputParser.parse(input);
        List<Mower> finalPositions = mowerRunner.execute(parsedInput);
        outputPrinter.printPositions(finalPositions);

    }

}
