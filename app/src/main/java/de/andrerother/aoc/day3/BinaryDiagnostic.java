package de.andrerother.aoc.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic {
    public static void main(final String[] args) {
        BinaryDiagnostic instance = new BinaryDiagnostic();
        int[] gammaBinary = instance.parseDiagnosticToBinary(instance.readDiagnostics());
        int[] epsilonBinary = instance.invertDecimalArray(gammaBinary);
        int gamma = instance.binaryToDecimal(gammaBinary);
        int epsilon = instance.binaryToDecimal(epsilonBinary);
        int result = instance.calculatePowerConsumption(gamma, epsilon);
        System.out.println("A result="+result);
    }

    private List<String> readDiagnostics() {
        // Shamelessly taken from https://mkyong.com/java/java-read-a-file-from-resources-folder/
        List<String> diagnostics = new ArrayList<>();
        String fileName = "diagnostics.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    diagnostics.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return diagnostics;
    }

    public int[] parseDiagnosticToBinary(final List<String> diagnostics) {
        if(diagnostics.isEmpty()) {
            throw new IllegalStateException();
        }

        int columnSize = getSizeOfColumns(diagnostics.get(0));
        int[] result = new int[columnSize];

        for (int i = 0; i < columnSize; i++) {
            int gamma = 0;
            int epsilon = 0;

            for (String diagnostic : diagnostics) {
                gamma += diagnostic.charAt(i) == '1' ? 1 : 0;
                epsilon += diagnostic.charAt(i) == '0' ? 1 : 0;
            }
            result[i] = gamma > epsilon ? 1 : 0;
        }
        return result;
    }

    public int binaryToDecimal(final int[] binaryDiagnostic) {
        double decimal = 0;
        for (int i = 0; i <= binaryDiagnostic.length-1; i++) {
            double result = binaryDiagnostic[i] * Math.pow(2, binaryDiagnostic.length-(i+1));
            decimal += result;
        }
        return (int) decimal;
    }

    public int[] invertDecimalArray(final int[] binaryDiagnostic) {
        int[] invertedBinaryDiagnostic = new int[binaryDiagnostic.length];
        for (int i = 0; i < binaryDiagnostic.length; i++) {
            invertedBinaryDiagnostic[i] = binaryDiagnostic[i] == 1 ? 0 : 1;
        }
        return invertedBinaryDiagnostic;
    }

    public int calculatePowerConsumption(final int gamma, final int epsilon) {
        return gamma * epsilon;
    }

    public int getSizeOfColumns(final String diagnostic) {
        return diagnostic.length();
    }
}
