package org.lawnpilot.domain;

public enum Direction {
    N(0,1),
    E(1,0),
    S(0, -1),
    W(-1, 0);

    private final int directionX;
    private final int directionY;

    Direction(int directionX, int directionY) {
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public Direction right() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }

    public Direction left() {
        return switch(this) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
        };
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }
}
