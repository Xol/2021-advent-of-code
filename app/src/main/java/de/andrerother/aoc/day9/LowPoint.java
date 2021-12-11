package de.andrerother.aoc.day9;

import java.util.Objects;

class LowPoint {
    private int valueWithRisk;
    private int i;
    private int j;

    LowPoint(final int value, final int i, final int j){
        this.valueWithRisk = value;
        this.i = i;
        this.j = j;
    }

    public int getValueWithRisk() {
        return valueWithRisk;
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
        return valueWithRisk == lowPoint.valueWithRisk &&
                i == lowPoint.i &&
                j == lowPoint.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueWithRisk, i, j);
    }
}
