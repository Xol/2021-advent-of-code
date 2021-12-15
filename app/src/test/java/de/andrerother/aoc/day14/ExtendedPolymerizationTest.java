package de.andrerother.aoc.day14;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExtendedPolymerizationTest {
    public static final String POLYMER_DEMO_STRING = "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB";
    final ExtendedPolymerization underTest = new ExtendedPolymerization();

    @Test
    void calculate_character_frequency() {
        Map<String, Integer> given = underTest.calculateCharacterFrequencyInString(POLYMER_DEMO_STRING);
        assertEquals(createDemoElementQuantity(), given);
    }

    @Test
    void calculate_difference_of_demo_polymer() {
        int given = underTest.calculateDifference(POLYMER_DEMO_STRING);
        assertEquals(18, given);
    }

    @Test
    void transform_template_to_polymer(){
        String given = underTest.createPolymerFromTemplate("NNCB", 4, createDemoRuleSet());
        assertEquals(POLYMER_DEMO_STRING, given);
    }

    private Map<String, Integer> createDemoElementQuantity() {
        return Map.of("B",23, "C", 10, "H", 5, "N", 11);
    }

    private Map<String, String> createDemoRuleSet() {
        Map<String, String> demoSet = new HashMap<>();
        demoSet.put("CH","B");
        demoSet.put("HH","N");
        demoSet.put("CB","H");
        demoSet.put("NH","C");
        demoSet.put("HB","C");
        demoSet.put("HC","B");
        demoSet.put("HN","C");
        demoSet.put("NN","C");
        demoSet.put("BH","H");
        demoSet.put("NC","B");
        demoSet.put("NB","B");
        demoSet.put("BN","B");
        demoSet.put("BB","N");
        demoSet.put("BC","B");
        demoSet.put("CC","N");
        demoSet.put("CN","C");
        return demoSet;
    }
}