/*
 * Duy Nguyen
 * Triangle.java
 * Computes perimeter of triangles based off length of sides
 */

import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Takes input of sides
        System.out.print("Enter length of 3 sides of triangle (separated by spaces): ");
        double side1 = input.nextDouble();
        double side2 = input.nextDouble();
        double side3 = input.nextDouble();

        //Checks for valid triangle and then calculates perimeter
        if (((side1 + side2) > side3) && ((side2 + side3) > side1) &&
                ((side1 + side3) > side2)) {
            double perimeter = side1 +side2 + side3;
            System.out.printf("Perimeter: %.2f units \n", perimeter);
        }
        else {
            System.out.println("Input of sides failed.");
            System.out.println("Please enter a triangle where any side is not greater than " +
                    "the sum of the remaining two sides");
        }
    }
}
