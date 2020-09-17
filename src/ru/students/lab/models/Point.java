package ru.students.lab.models;

import java.time.LocalDateTime;

public class Point {
    private final Double x;
    private final Double y;
    private final Double r;
    private Boolean result;
    private final LocalDateTime createdTime;
    private final String computedTime;

    public Point(double x, double y, double r, LocalDateTime createdTime, String computedTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.createdTime = createdTime;
        this.computedTime = computedTime;
        checkInsideFunc();
    }

    public Point(double x, double y, double r, boolean result, LocalDateTime createdTime, String computedTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.createdTime = createdTime;
        this.computedTime = computedTime;
    }

    public void checkInsideFunc() {
        if (x == null || y == null || r == null)
            throw new NullPointerException();

        this.result = (x <= 0 && y >= 0 && y <= 2*x + r) //linear function
                || (y <= 0 && x >= 0 && y >= -Math.sqrt(r * r / 4 - x * x)) //circular function
                || (y >= 0 && x >= 0 && y <= r && x <= r); //lines on r
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getComputedTime() {
        return computedTime;
    }

    public boolean isResult() {
        return result;
    }
}
