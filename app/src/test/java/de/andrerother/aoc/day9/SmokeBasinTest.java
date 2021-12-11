package de.andrerother.aoc.day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SmokeBasinTest {
    final SmokeBasin underTest = new SmokeBasin();

    @Test
    void convert_heighmap_matrix() {
        int[] expected = {2,1,9,9,9,4,3,2,1,0};
        int[] given = underTest.convertStringToVector("2199943210");
        assertArrayEquals(expected, given);
    }

    @Test
    void create_matrix() {
        int[][] expected = createMatrixForDemoData();
        int[][] given = underTest.createMatrix(5, 10, createHeighmapDemoData());
        assertArrayEquals(expected, given);
    }

    @Test
    void check_result_for_demo_data() {
        List<LowPoint> given = underTest.searchForLowPoints(createMatrixForDemoData());
        List<LowPoint> expexted = createResultForDemoData();
        assertEquals(expexted, given);
    }

    private List<LowPoint> createResultForDemoData() {
        return List.of(
            new LowPoint(2,0,1),
            new LowPoint(1,0,9),
            new LowPoint(6,2,2),
            new LowPoint(6,4,6)
        );
    }

    private List<String> createHeighmapDemoData() {
        return List.of("2199943210","3987894921","9856789892","8767896789","9899965678");
    }

    private int[][] createMatrixForDemoData() {
        return new int[][]{
            {2,1,9,9,9,4,3,2,1,0},
            {3,9,8,7,8,9,4,9,2,1},
            {9,8,5,6,7,8,9,8,9,2},
            {8,7,6,7,8,9,6,7,8,9},
            {9,8,9,9,9,6,5,6,7,8}
        };
    }
}