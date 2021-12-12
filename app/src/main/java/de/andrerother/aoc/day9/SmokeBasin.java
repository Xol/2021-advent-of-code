package de.andrerother.aoc.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class SmokeBasin {

    public static void main(final String[] args) {
        SmokeBasin instance = new SmokeBasin();
        int[][] heightsMatrix = instance.readHeighmapFile();
        List<LowPoint> lowpoints = instance.searchForLowPoints(heightsMatrix);
        System.out.println("Part One Answer: " + instance.calculateSolution(lowpoints));
        List<Integer> topThreeBasinSizes = instance.findTopThreeBasinSizes(lowpoints, heightsMatrix);
        System.out.println("Part Two Answer: " + instance.multiplyBasinSizes(topThreeBasinSizes));
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

    public List<LowPoint> searchForLowPoints(final int[][] heightsMatrix) {
        List<LowPoint> result = new ArrayList<>();

        for(int i = 0; i < heightsMatrix.length; i++) {
            for(int j = 0; j < heightsMatrix[i].length; j++) {
                boolean topIsSmaller = true;
                boolean rightIsSmaller = true;
                boolean bottomIsSmaller = true;
                boolean leftIsSmaller = true;
                int top = i-1;
                int right = j+1;
                int bottom = i+1;
                int left = j-1;
                //check top
                if(top >= 0){
                    topIsSmaller = heightsMatrix[i][j] < heightsMatrix[top][j];
                }
                //check right
                if(right < heightsMatrix[i].length) {
                    rightIsSmaller = heightsMatrix[i][j] < heightsMatrix[i][right];
                }
                //check bottom
                if(bottom < heightsMatrix.length) {
                    bottomIsSmaller = heightsMatrix[i][j] < heightsMatrix[bottom][j];
                }
                //check left
                if(left >= 0){
                    leftIsSmaller = heightsMatrix[i][j] < heightsMatrix[i][left];
                }

                if(topIsSmaller && rightIsSmaller && bottomIsSmaller && leftIsSmaller) {
                    result.add(new LowPoint(heightsMatrix[i][j],i,j));
                }
            }
        }
        return result;
    }

    public List<Integer> findTopThreeBasinSizes(final List<LowPoint> lowpoints, final int[][] heightsMatrix) {
        List<Integer> result = new ArrayList<>();
        for (LowPoint lowpoint : lowpoints) {
            Set<LowPoint> basinLowPoints = new HashSet<>();
            Stack<LowPoint> stack = new Stack<>();
            stack.push(lowpoint);
            basinLowPoints.add(lowpoint);
            while (!stack.empty()) {
                stack.addAll(findNeighbors(stack.pop(), heightsMatrix));
                basinLowPoints.addAll(stack);
            }
            result.add(basinLowPoints.size());
        }
        return result.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
    }

    // Very important information to solve this puzzle: https://www.reddit.com/r/adventofcode/comments/rcfnnk/2021_day_9_part_2_when_the_sample_works_the_real/
    private List<LowPoint> findNeighbors(final LowPoint coordinate, final int[][] heightsMatrix) {
        List<LowPoint> result = new ArrayList<>();
        int i = coordinate.getI();
        int j = coordinate.getJ();
        int top = i-1;
        int right = j+1;
        int bottom = i+1;
        int left = j-1;

        // check top
        if(top >= 0 && heightsMatrix[top][j] < 9 && heightsMatrix[top][j] > heightsMatrix[i][j]){
            result.add(new LowPoint(heightsMatrix[top][j], top, j));
        }
        //check right
        if(right < heightsMatrix[i].length && heightsMatrix[i][right] < 9 && heightsMatrix[i][right] > heightsMatrix[i][j]) {
            result.add(new LowPoint(heightsMatrix[i][right], i, right));
        }
        //check bottom
        if(bottom < heightsMatrix.length && heightsMatrix[bottom][j] < 9 && heightsMatrix[bottom][j] > heightsMatrix[i][j]) {
            result.add(new LowPoint(heightsMatrix[bottom][j], bottom, j));
        }
        //check left
        if(left >= 0 && heightsMatrix[i][left] < 9 && heightsMatrix[i][left] > heightsMatrix[i][j]){
            result.add(new LowPoint(heightsMatrix[i][left], i, left));
        }
        return result;
    }

    public int calculateSolution(final List<LowPoint> lowPoints) {
        return lowPoints.stream().map(lp -> lp.getValue()+1).reduce(0, Integer::sum);
    }

    public int multiplyBasinSizes(final List<Integer> basinSizes) {
        return basinSizes.stream().reduce(1, (l1, l2) -> l1 * l2);
    }
}
