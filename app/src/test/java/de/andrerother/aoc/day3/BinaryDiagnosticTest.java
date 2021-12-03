package de.andrerother.aoc.day3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryDiagnosticTest {
    BinaryDiagnostic underTest = new BinaryDiagnostic();
    final int[] expected = {1,0,1,1,0};
    final int[] expectedInvert = {0,1,0,0,1};

    @Test
    void parse_diagnostic_data() {
        int[] given = underTest.parseDiagnosticToBinary(createDiagnosticTestData());
        assertArrayEquals(expected, given);
    }

    @Test
    void calculate_power_consumption() {
        int given = underTest.calculatePowerConsumption(22,9);
        assertEquals(198, given);
    }

    @Test
    void invert_decimal() {
        int[] given = underTest.invertDecimalArray(expected);
        assertArrayEquals(expectedInvert, given);
    }

    @Test
    void convert_binary_to_decimal() {
        int given = underTest.binaryToDecimal(expected);
        int givenInverted = underTest.binaryToDecimal(expectedInvert);
        assertEquals(22, given);
        assertEquals(9, givenInverted);
    }

    private List<String> createDiagnosticTestData() {
        List<String> demoData = new ArrayList<>();
        demoData.add("00100");
        demoData.add("11110");
        demoData.add("10110");
        demoData.add("10111");
        demoData.add("10101");
        demoData.add("01111");
        demoData.add("00111");
        demoData.add("11100");
        demoData.add("10000");
        demoData.add("11001");
        demoData.add("00010");
        demoData.add("01010");
        return demoData;
    }
}