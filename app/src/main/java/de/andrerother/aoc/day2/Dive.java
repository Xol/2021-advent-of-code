package de.andrerother.aoc.day2;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Dive {
    public static final String MOVEMENT_FORWARD = "forward";
    public static final String MOVEMENT_UP = "up";
    public static final String MOVEMENT_DOWN = "down";

    public static void main(final String[] args) {
        Dive instance = new Dive();
        System.out.println("Part one answer: " + instance.pilot(instance.readMovementFile()));
        System.out.println("Part two answer: " + instance.pilotToAim(instance.readMovementFile()));
    }

    public int pilot(final List<Pair<String, Integer>> movements) {
        int horizontalPosition = 0;
        int depth = 0;

        for (Pair<String, Integer> movement : movements) {
            String direction = movement.getLeft();
            int units = movement.getRight();

            switch (direction) {
                case MOVEMENT_FORWARD:
                    horizontalPosition += units;
                    break;
                case MOVEMENT_DOWN:
                    depth += units;
                    break;
                case MOVEMENT_UP:
                    depth -= units;
                    break;
                default:
                    System.out.println("Nothing...");
            }
        }
        return horizontalPosition * depth;
    }

    public int pilotToAim(final List<Pair<String, Integer>> movements) {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        for (Pair<String, Integer> movement : movements) {
            String direction = movement.getLeft();
            int units = movement.getRight();

            switch (direction) {
                case MOVEMENT_FORWARD:
                    horizontalPosition += units;
                    depth += aim * units;
                    break;
                case MOVEMENT_DOWN:
                    aim += units;
                    break;
                case MOVEMENT_UP:
                    aim -= units;
                    break;
                default:
                    System.out.println("Nothing...");
            }
        }
        return horizontalPosition * depth;
    }

    public List<Pair<String, Integer>> readMovementFile() {
        // Shamelessly taken from https://mkyong.com/java/java-read-a-file-from-resources-folder/
        List<Pair<String, Integer>> movements = new ArrayList<>();
        String fileName = "movements.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] splittedMovement = line.split("\\s+");
                    Pair<String, Integer> movement =  new ImmutablePair<>(splittedMovement[0], Integer.valueOf(splittedMovement[1]));
                    movements.add(movement);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return movements;
    }
}
