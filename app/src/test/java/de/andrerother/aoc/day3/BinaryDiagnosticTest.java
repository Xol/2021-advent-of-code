package de.andrerother.aoc.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryDiagnosticTest {
    BinaryDiagnostic underTest = new BinaryDiagnostic();

    @Test
    void parse_diagnostic_data() {
        int[] given = underTest.parseDiagnosticToBinary(createDiagnosticTestData());
        assertArrayEquals(createTestGammaRate(), given);
    }

    @Test
    void calculate_power_consumption() {
        int given = underTest.calculatePowerConsumption(22,9);
        assertEquals(198, given);
    }

    @Test
    void invert_decimal() {
        int[] given = underTest.invertDecimalArray(createTestGammaRate());
        assertArrayEquals(createInvertedTestGammaRate(), given);
    }

    @Test
    void convert_binary_to_decimal() {
        int given = underTest.binaryToDecimal(createTestGammaRate());
        int givenInverted = underTest.binaryToDecimal(createInvertedTestGammaRate());
        assertEquals(22, given);
        assertEquals(9, givenInverted);
    }

    private List<String> createDiagnosticTestData() {
        return List.of("00100","11110","10110","10111","10101","01111","00111","11100","10000","11001","00010","01010");
    }

    private int[] createTestGammaRate() {
        return new int[]{1,0,1,1,0};
    }

    private int[] createInvertedTestGammaRate() {
        return new int[]{0,1,0,0,1};
    }
}