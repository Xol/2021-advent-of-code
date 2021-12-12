package de.andrerother.aoc.day9;

import java.util.Objects;

class LowPoint {
    private int value;
    private int i;
    private int j;

    LowPoint(final int value, final int i, final int j){
        this.value = value;
        this.i = i;
        this.j = j;
    }

    public int getValue() {
        return value;
    }
    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LowPoint lowPoint = (LowPoint) o;
        return value == lowPoint.value &&
                i == lowPoint.i &&
                j == lowPoint.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, i, j);
    }
}
