package de.andrerother.aoc.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SonarSweepTest {
    SonarSweep underTest = new SonarSweep();

    @Test
    void count_increasing_measures() {
        int result = underTest.countIncreasedMeasures(createMeasuresWithSevenIncreases());
        assertEquals(7,result);
    }

    @Test
    void count_increasing_measures_with_sliding_window() {
        int result = underTest.countIncreasedMeasuresWithSlidingWindow(createMeasuresWithSevenIncreases());
        assertEquals(5, result);
    }

    private List<Integer> createMeasuresWithSevenIncreases() {
        return List.of(199,200,208,210,200,207,240,269,260,263);
    }
}