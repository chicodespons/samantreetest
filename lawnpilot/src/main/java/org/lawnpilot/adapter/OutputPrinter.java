package org.lawnpilot.adapter;

import java.util.List;

public class OutputPrinter {

    public void printPositions(List<String> finalPositions) {
        for (String position : finalPositions) {
            System.out.println(position);
        }
    }
}
