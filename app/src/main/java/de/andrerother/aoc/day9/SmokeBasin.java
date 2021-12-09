package de.andrerother.aoc.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmokeBasin {

    public static void main(final String[] args) {
        SmokeBasin instance = new SmokeBasin();
        int[][] heighmap = instance.readHeighmapFile();
        List<Integer> result = instance.searchForLowPoints(heighmap);
        System.out.println("Part One Answer: " + instance.calculateSolution(result));
    }
    public int[][] readHeighmapFile() {
        int rows;
        int columns;
        // Shamelessly taken from https://mkyong.com/java/java-read-a-file-from-resources-folder/
        String fileName = "heighmap.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        List<String> content = new ArrayList<>();
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.add(line);
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        rows = content.size();
        columns = content.get(0).length();
        return createMatrix(rows, columns, content);
    }

    public int[][] createMatrix(final int rows, final int columns, final List<String> fileContent) {
        var matrix = new int[rows][columns];
        for (int i = 0; i < fileContent.size(); i++) {
            matrix[i] = convertStringToVector(fileContent.get(i));
        }
        return matrix;
    }

    public int[] convertStringToVector(final String line) {
        return Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray();
    }

    public List<Integer> searchForLowPoints(final int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                boolean topIsSmaller;
                boolean rightIsSmaller;
                boolean bottomIsSmaller;
                boolean leftIsSmaller;
                //check top
                if(j-1>=0){
                    topIsSmaller = matrix[i][j] < matrix[i][j-1];
                } else {
                    topIsSmaller = true;
                }
                //check right
                if(i+1 < matrix.length) {
                    rightIsSmaller = matrix[i][j] < matrix[i+1][j];
                } else {
                    rightIsSmaller = true;
                }
                //check bottom
                if(j+1 < matrix[i].length) {
                    bottomIsSmaller = matrix[i][j] < matrix[i][j+1];
                } else {
                    bottomIsSmaller = true;
                }
                //check left
                if(i-1 >= 0){
                    leftIsSmaller = matrix[i][j] < matrix[i-1][j];
                } else {
                    leftIsSmaller = true;
                }

                if(topIsSmaller && rightIsSmaller && bottomIsSmaller && leftIsSmaller) {
                    result.add(matrix[i][j]+1);
                }
            }
        }
        return result;
    }

    public int calculateSolution(final List<Integer> lowPointsWithRisk) {
        return lowPointsWithRisk.stream().reduce(0, Integer::sum);
    }
}
