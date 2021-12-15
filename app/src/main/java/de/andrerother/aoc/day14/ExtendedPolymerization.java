package de.andrerother.aoc.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ExtendedPolymerization {

    public static void main(final String[] args) {
        ExtendedPolymerization instance = new ExtendedPolymerization();
        Map<String, String> rules = instance.readRules();
        String template = "OFSVVSFOCBNONHKFHNPK";
        String polymer = instance.createPolymerFromTemplate(template, 10, rules);
        int difference = instance.calculateDifference(polymer);
        System.out.println("Part One Answer: " + difference);
    }

    public int calculateDifference(final String polymer) {
        int lowestCount = -1;
        int highestCount = 0;
        Map<String, Integer> characterFrequencyRegister = calculateCharacterFrequencyInString(polymer);
        for (Map.Entry<String, Integer> entry : characterFrequencyRegister.entrySet()) {
            int currentCount = entry.getValue();
            if(lowestCount == -1 || lowestCount > currentCount) {
                lowestCount = currentCount;
            }
            if(highestCount < currentCount) {
                highestCount = currentCount;
            }
        }
        return highestCount - lowestCount;
    }

    public String createPolymerFromTemplate(final String template, final int iterations, final Map<String, String> ruleSet) {
        String tmpPolymer = template;
        String tmpPolymer2 = "";
        String polymer = "";
//        Map<String, String> ruleSet = ruleSet;
        System.out.println("Template: " + template);
        for(int i = 0; i < iterations; i++) {
            for(int j = 0; j < tmpPolymer.length()-1; j++) {
                String substring = tmpPolymer.substring(j,j+2);
                for (Map.Entry<String, String> entry : ruleSet.entrySet()) {
                    if(entry.getKey().equalsIgnoreCase(substring)) {
                        tmpPolymer2 += entry.getKey().charAt(0) + entry.getValue();
                    }
                }
            }
            tmpPolymer2 += tmpPolymer.charAt(tmpPolymer.length()-1);
            tmpPolymer = tmpPolymer2;
            System.out.println("After step " + (i+1) + ": " + tmpPolymer2);
            polymer = tmpPolymer2;
            tmpPolymer2 = "";
        }
        return polymer;
    }

    public Map<String, Integer> calculateCharacterFrequencyInString(final String polymer) {
        Map<String, Integer> calculations = new HashMap<>();
        for(int i = 0; i < polymer.length(); i++) {
            char c = polymer.charAt(i);
            calculations.putIfAbsent(String.valueOf(c).toUpperCase(), 0);
            calculations.computeIfPresent(String.valueOf(c).toUpperCase(), (character, count) -> count + 1);
        }
        return calculations;
    }


    private Map<String, String> createRuleSet() {
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

    private Map<String, String> readRules() {
        // Shamelessly taken from https://mkyong.com/java/java-read-a-file-from-resources-folder/
        Map<String, String> rules = new HashMap<>();
        String fileName = "rules.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    //BN -> H
                    rules.put(line.substring(0, 2), line.substring(6));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rules;
    }
}
