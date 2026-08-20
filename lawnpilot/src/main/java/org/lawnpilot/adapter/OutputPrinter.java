package org.lawnpilot.adapter;

import org.lawnpilot.domain.Mower;

import java.util.List;

public class OutputPrinter {

    public void printPositions(List<Mower> mowers) {
        for (Mower mower : mowers) {
            System.out.println(
                    mower.getX() + " " + mower.getY() + " " + mower.getDirection()
            );
        }
    }
}
