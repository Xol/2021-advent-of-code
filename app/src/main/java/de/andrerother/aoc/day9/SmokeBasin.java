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
        List<LowPoint> result = instance.searchForLowPoints(heighmap);
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

    public List<LowPoint> searchForLowPoints(final int[][] matrix) {
        List<LowPoint> result = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                boolean topIsSmaller = true;
                boolean rightIsSmaller = true;
                boolean bottomIsSmaller = true;
                boolean leftIsSmaller = true;
                //check top
                if(j-1 >= 0){
                    topIsSmaller = matrix[i][j] < matrix[i][j-1];
                }
                //check right
                if(i+1 < matrix.length) {
                    rightIsSmaller = matrix[i][j] < matrix[i+1][j];
                }
                //check bottom
                if(j+1 < matrix[i].length) {
                    bottomIsSmaller = matrix[i][j] < matrix[i][j+1];
                }
                //check left
                if(i-1 >= 0){
                    leftIsSmaller = matrix[i][j] < matrix[i-1][j];
                }

                if(topIsSmaller && rightIsSmaller && bottomIsSmaller && leftIsSmaller) {
                    result.add(new LowPoint(matrix[i][j]+1,i,j));
                }
            }
        }
        return result;
    }

    public int calculateSolution(final List<LowPoint> lowPoints) {
        return lowPoints.stream().map(LowPoint::getValueWithRisk).reduce(0, Integer::sum);
    }
}
