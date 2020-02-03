package com.epam.brest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.spi.NumberFormatProvider;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        List<String> linesPriceKm = Files.readAllLines(Paths.get("priceKm.txt"), Charset.defaultCharset());
        List<String> linesPriceKg = Files.readAllLines(Paths.get("priceKg.txt"), Charset.defaultCharset());


        Integer priceKm;
        Integer priceKg;
        Double[] enteredValues = new Double[4];

        Scanner scanner = new Scanner(System.in);
        String inputValue;
        int i = 0;
        do {

            if (i == 0) {
                System.out.println("Please, enter distance or Q for exit: ");
            } else {
                System.out.println("Please, enter weight or Q for exit: ");
            }

            inputValue = scanner.next();

            if (!isExitValue(inputValue)) {
                if (isCorrectDoubleValue(inputValue)) {
                    enteredValues[i] = Double.parseDouble(inputValue);
                    i++;
                }
            }

            if (i == 2) {
                if (enteredValues[0] > 0 && enteredValues[0] <= 10) {
                    priceKm = Integer.parseInt(linesPriceKm.get(0).substring(1));
                } else if (enteredValues[0] > 10 && enteredValues[0] <= 100) {
                    priceKm = Integer.parseInt(linesPriceKm.get(1));
                } else {
                    priceKm = Integer.parseInt(linesPriceKm.get(2));
                }
                if (enteredValues[1] > 0 && enteredValues[1] <= 100) {
                    priceKg = Integer.parseInt(linesPriceKg.get(0).substring(1));
                } else if (enteredValues[0] > 100 && enteredValues[0] <= 500) {
                    priceKg = Integer.parseInt(linesPriceKg.get(1));
                } else {
                    priceKg = Integer.parseInt(linesPriceKg.get(2));
                }
                Double caclResult = enteredValues[0] * priceKm + enteredValues[1] * priceKg;
                System.out.println("Price = " + enteredValues[0] + " * " + priceKm + " + " + enteredValues[1] + " * " + priceKg + " = " + caclResult);
                i = 0;
            }

        } while (!isExitValue(inputValue));

        System.out.println("Finish!");

    }

    private static boolean isExitValue(String value) {
        return value.equalsIgnoreCase("Q");
    }

    private static boolean isCorrectDoubleValue(String value) {
        boolean checkResult;
        try {
            double enteredDoubleValue = Double.parseDouble(value);
            checkResult = enteredDoubleValue >= 0;
        } catch (NumberFormatException ex) {
            checkResult = false;
        }
        return checkResult;
    }

}