package org.jetbrains.assignment.model;

public class Coordinate {

    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addX(int amount) {
        this.x += amount;
    }

    public void addY(int amount) {
        this.y += amount;
    }

    public void subtractX(int amount) {
        this.x -= amount;
    }

    public void subtractY(int amount) {
        this.y -= amount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
