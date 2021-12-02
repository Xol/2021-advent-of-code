package de.andrerother.aoc.day1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        List<Integer> testMeasures = new ArrayList<>();
        testMeasures.add(199);
        testMeasures.add(200);
        testMeasures.add(208);
        testMeasures.add(210);
        testMeasures.add(200);
        testMeasures.add(207);
        testMeasures.add(240);
        testMeasures.add(269);
        testMeasures.add(260);
        testMeasures.add(263);
        return testMeasures;
    }
}