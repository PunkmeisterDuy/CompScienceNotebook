/*
 * Duy Nguyen
 * Statistics.java
 * Calculates avg price/standard deviation of input prices/items
 */

import java.util.Scanner;

public class Statistics {

    // Calculates Standard Deviation
    public static double standardDev(double sumPrice, double sumSquares,
                                     int counter) {
        double numerator = counter * sumSquares - Math.pow(sumPrice, 2);
        double denominator = counter * (counter - 1);
        double omega = Math.sqrt(numerator / denominator);

        return omega;
    }

    public static double avgPrice(double sumPrice, int counter) {
        double average = sumPrice / counter;
        return average;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double priceN;
        double sumPrice = 0;
        double sumSquares = 0;
        int counter = 0;

        do { // Takes input and stores sum of price/squares
            System.out.print("Enter price, or -1 to quit: $");
            priceN = input.nextDouble();

            if (priceN != -1) {
                counter++;
                sumPrice += priceN;
                sumSquares += Math.pow(priceN, 2);
            }
        } while (priceN != -1);

        System.out.printf("Number of Items: %d\n", counter);

        if (counter == 0) {
            System.out.println("No data entered. Cannot calculate statistics.");
        }
        else {
            System.out.printf("Average Price: $%.2f\n",
                              avgPrice(sumPrice, counter));

            if (counter == 1) {
                System.out.println("Cannot calculate " +
                        "standard deviation using one item");
            }
            else {
                System.out.printf("The standard deviation is: $%.2f\n",
                                  standardDev(sumPrice, sumSquares, counter));
            }
        }

    }
}
