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
        String gammaBinary = instance.parseDiagnosticToBinary(instance.readDiagnostics());
        String epsilonBinary = instance.invertDecimalArray(gammaBinary);
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

    public String parseDiagnosticToBinary(final List<String> diagnostics) {
        if(diagnostics.isEmpty()) {
            throw new IllegalStateException();
        }

        int columnSize = getSizeOfColumns(diagnostics.get(0));
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < columnSize; i++) {
            int gamma = 0;
            int epsilon = 0;

            for (String diagnostic : diagnostics) {
                gamma += diagnostic.charAt(i) == '1' ? 1 : 0;
                epsilon += diagnostic.charAt(i) == '0' ? 1 : 0;
            }
            result.append(gamma > epsilon ? "1" : "0");
        }
        return result.toString();
    }

    public int binaryToDecimal(final String binaryDiagnostic) {
        return Integer.parseInt(binaryDiagnostic, 2);
    }

    public String invertDecimalArray(final String binaryDiagnostic) {
        StringBuilder invertedBinary = new StringBuilder();
        for (int i = 0; i < binaryDiagnostic.length(); i++) {
            invertedBinary.append(binaryDiagnostic.charAt(i) == '1' ? "0" : "1");
        }
        return invertedBinary.toString();
    }

    public int calculatePowerConsumption(final int gamma, final int epsilon) {
        return gamma * epsilon;
    }

    public int getSizeOfColumns(final String diagnostic) {
        return diagnostic.length();
    }
}
