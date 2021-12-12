package de.andrerother.aoc.day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        int[][] expected = createHeightsMatrixFromHighmap();
        int[][] given = underTest.createMatrix(5, 10, createRawHeighmap());
        assertArrayEquals(expected, given);
    }

    @Test
    void check_result_for_demo_data() {
        List<LowPoint> given = underTest.searchForLowPoints(createHeightsMatrixFromHighmap());
        List<LowPoint> expected = createLowPoints();
        assertIterableEquals(expected, given);
    }

    @Test
    void calculate_result_lowpoints_with_risk() {
        int given = underTest.calculateSolution(createLowPoints());
        assertEquals(15, given);
    }

    @Test
    void check_basin_sizes() {
        List<Integer> given = underTest.findTopThreeBasinSizes(createLowPoints(), createHeightsMatrixFromHighmap());
        List<Integer> expected = createTopThreeBasinSizes();
        assertEquals(expected, given);
    }

    @Test
    void multiply_the_basins(){
        assertEquals(1134, underTest.multiplyBasinSizes(createTopThreeBasinSizes()));
    }

    private List<LowPoint> createLowPoints() {
        return List.of(
                new LowPoint(1,0,1),
                new LowPoint(0,0,9),
                new LowPoint(5,2,2),
                new LowPoint(5,4,6)
        );
    }

    private List<String> createRawHeighmap() {
        return List.of("2199943210","3987894921","9856789892","8767896789","9899965678");
    }

    private int[][] createHeightsMatrixFromHighmap() {
        return new int[][]{
            {2,1,9,9,9,4,3,2,1,0},
            {3,9,8,7,8,9,4,9,2,1},
            {9,8,5,6,7,8,9,8,9,2},
            {8,7,6,7,8,9,6,7,8,9},
            {9,8,9,9,9,6,5,6,7,8}
        };
    }

    private List<Integer> createTopThreeBasinSizes() {
        return List.of(14, 9, 9);
    }
}