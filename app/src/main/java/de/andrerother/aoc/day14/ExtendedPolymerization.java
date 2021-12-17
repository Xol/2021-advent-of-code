package de.andrerother.aoc.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendedPolymerization {

    public static void main(final String[] args) {
        ExtendedPolymerization instance = new ExtendedPolymerization();
        List<String> rules = instance.readRules();
        String polymerTemplate = instance.getTemplateFromRules(rules);
        Map<String, String> ruleSet = instance.getRuleSet(rules);
        String polymer = instance.createPolymerFromTemplate(polymerTemplate, 10, ruleSet);
        int difference = instance.calculateDifference(polymer);
        System.out.println("Part One Answer: " + difference);
    }

    private Map<String, String> getRuleSet(final List<String> content) {
        Map<String, String> rules = new HashMap<>();
        for (int i = 2; i < content.size(); i++) {
            rules.put(content.get(i).substring(0, 2), content.get(i).substring(6));
        }
        return rules;
    }

    private String getTemplateFromRules(final List<String> content) {
        return !content.isEmpty() ? content.get(0) : "";
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
        String currentTemplate = template;
        String currentPolymer = "";
        String polymer = "";
        System.out.println("Template: " + template);
        for(int i = 0; i < iterations; i++) {
            for(int j = 0; j < currentTemplate.length()-1; j++) {
                String substring = currentTemplate.substring(j,j+2);
                for (Map.Entry<String, String> entry : ruleSet.entrySet()) {
                    if(entry.getKey().equalsIgnoreCase(substring)) {
                        currentPolymer += entry.getKey().charAt(0) + entry.getValue();
                    }
                }
            }
            currentPolymer += currentTemplate.charAt(currentTemplate.length()-1);
            currentTemplate = currentPolymer;
            System.out.println("After step " + (i+1) + ": " + currentPolymer);
            polymer = currentPolymer;
            currentPolymer = "";
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

    private List<String> readRules() {
        // Shamelessly taken from https://mkyong.com/java/java-read-a-file-from-resources-folder/
        List<String> rules = new ArrayList<>();
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
                    rules.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rules;
    }
}
