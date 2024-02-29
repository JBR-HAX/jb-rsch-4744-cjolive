package org.jetbrains.assignment.model;

import lombok.Data;

public class Direction {

    private String direction;

    private int steps;

    public Direction(String direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
