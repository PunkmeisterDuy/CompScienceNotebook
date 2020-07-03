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
        return Math.sqrt(((counter * sumSquares)
                - Math.pow(sumPrice, 2))
                / (counter * (counter - 1)));
    }

    public static double avgPrice(double sumPrice, int counter) {
        return (sumPrice / counter);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double priceN;
        double sumPrice = 0;
        double sumSquares = 0;
        int counter = 0;
        boolean output = true;

        do { // Takes input and stores sum of price/squares
            System.out.print("Enter price, or -1 to quit: $");
            priceN = input.nextDouble();
            if (priceN != -1) {
                counter++;
                sumPrice += priceN;
                sumSquares += Math.pow(priceN, 2);
            }
            else if ((priceN == -1) && (counter == 0)) {
                System.out.println("No data entered. Cannot calculate statistics.");
                output = false;
            }
        } while (priceN != -1);

        if (output) { // Prints output if inputs are valid
            System.out.printf("Number of Items: %d\n", counter);
            System.out.printf("Average Price: $%.2f\n", avgPrice(sumPrice, counter));
            if (counter == 1) {
                System.out.println("Cannot calculate standard deviation using one item");
            }
            else {
                System.out.printf("The standard deviation is: $%.2f\n", standardDev(sumPrice, sumSquares, counter));
            }
        }

    }
}
