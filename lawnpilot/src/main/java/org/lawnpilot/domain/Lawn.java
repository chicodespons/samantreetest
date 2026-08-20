package org.lawnpilot.domain;

public record Lawn(int maxX, int maxY) {

    public boolean isInside(int x, int y) {
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;
    }
}
