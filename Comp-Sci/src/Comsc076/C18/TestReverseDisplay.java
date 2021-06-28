package Comsc076.C18;

/*
 * Duy Nguyen
 * TestReverseDisplay.java (Recursion)
 * Asks for input of string and displays the string in reverse
 */

import java.util.Scanner;

public class TestReverseDisplay {

    public static void main(String[] args) {

        // asks for input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String stringInput = input.nextLine();

        reverseDisplay(stringInput);

    }


    public static void reverseDisplay(String value) {
        reverseDisplay(value, value.length()); // passes to recursive helper method
    }

    public static void reverseDisplay(String value, int high) {

        if (high == 0) {
            return;
            // base case
        } else {
            System.out.print(value.charAt(high-1));
            reverseDisplay(value, high - 1); // recursive call
        }

        /* base case: putting chars from high index to low index
         * recursive call: reverseDisplay(value, high - 1)
         *
         * if value is reversed or 0, end
         * else not reversed, print char at high and continously interate down
         *
         */


    }
}