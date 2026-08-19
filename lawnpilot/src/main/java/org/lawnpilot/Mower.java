package org.lawnpilot;

public class Mower {

    private int x;
    private int y;
    private Direction direction;

    public Mower(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void execute(String instructions, Lawn lawn) {
        for (char c : instructions.toCharArray()) {
            if (c == 'L')
                turnLeft();
            else if (c == 'R')
                turnRight();
            else if (c == 'F')
                move(lawn);
        }
    }

    private void turnLeft() {
        direction = direction.left();
    }

    private void turnRight() {
        direction = direction.right();
    }

    private void move(Lawn lawn) {
        int nextX = x + direction.getDirectionX();
        int nextY = y + direction.getDirectionY();

        if (lawn.isInside(nextX, nextY)) {
            x = nextX;
            y = nextY;
        }
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
