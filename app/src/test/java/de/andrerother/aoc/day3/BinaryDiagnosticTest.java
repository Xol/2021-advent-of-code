package de.andrerother.aoc.day3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryDiagnosticTest {
    BinaryDiagnostic underTest = new BinaryDiagnostic();

    @Test
    void parse_diagnostic_data() {
        String given = underTest.parseDiagnosticToBinary(createDiagnosticTestData());
        assertEquals(createTestGammaRate(), given);
    }

    @Test
    void calculate_power_consumption() {
        int given = underTest.calculatePowerConsumption(22,9);
        assertEquals(198, given);
    }

    @Test
    void invert_decimal() {
        String given = underTest.invertDecimalArray(createTestGammaRate());
        assertEquals(createInvertedTestGammaRate(), given);
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

    private String createTestGammaRate() {
        return "10110";
    }

    private String createInvertedTestGammaRate() {
        return "01001";
    }
}