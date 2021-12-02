package de.andrerother.aoc.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SonarSweep {
    public static void main(final String[] args) {
        SonarSweep instance = new SonarSweep();
        System.out.println("Part One Answer: " + instance.countIncreasedMeasures(instance.readReportFile()));
        System.out.println("Part Two Answer: " + instance.countIncreasedMeasuresWithSlidingWindow(instance.readReportFile()));
    }

    public int countIncreasedMeasures(final List<Integer> measures) {
        int count = 0;
        int lastMeasures = measures.isEmpty() ? 0 : measures.get(0);
        int currentMeasure;

        for (Integer measure : measures) {
            currentMeasure = measure;
            if (currentMeasure > lastMeasures) {
                count++;
            }
            lastMeasures = currentMeasure;
        }
        return count;
    }

    public int countIncreasedMeasuresWithSlidingWindow(final List<Integer> measures) {
        final int slidingWindowSize = 3;
        int index = 0;
        int sum;
        List<Integer> threeMeasurementSum = new ArrayList<>();

        do {
            if(measures.size()-index <= 2) {
                break;
            }
            sum = 0;
            for (int i = 0; i < slidingWindowSize; i++) {
                sum += measures.get(i + index);
            }
            threeMeasurementSum.add(sum);
            index++;
        } while (index < measures.size());
        return countIncreasedMeasures(threeMeasurementSum);
    }

    public List<Integer> readReportFile() {
        // Shamelessly taken from https://mkyong.com/java/java-read-a-file-from-resources-folder/
        List<Integer> depthMeasurements = new ArrayList<>();
        String fileName = "report.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                String depth;
                while ((depth = reader.readLine()) != null) {
                    depthMeasurements.add(Integer.valueOf(depth));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return depthMeasurements;
    }
}
