package Comsc076.Test1;

/*
 * Duy Nguyen
 * CharacterCounter.java
 * Asks for character, checks how many times character matches character array at subscript,
 * and displays/formats output
 */

import java.util.Scanner;

public class CharacterCounter {

    public static void main(String[] args) {

        // input
        Scanner input = new Scanner(System.in);
        String stringInput = "";

        // feedback loop for user to enter 1 char only to prevent confusion if they entered a string
        do {
            System.out.print("Enter 1 character to check character array: ");

            stringInput = input.next();
        } while (stringInput.length() != 1);

        char charInput = stringInput.charAt(0); // takes first char of string


        char[ ] charArray = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 't', 'h', 'e', ' ', 's', 't', 'r', 'i', 'n', 'g'};
        System.out.print("Char Array displayed as string: ");
        System.out.println(charArray);

        // displays/formats char count
        System.out.printf("Your character, '%s', occurs: %d times",
                charInput,
                charCount(charArray, 0, charInput));

    }



    // recursive method
    public static int charCount(char[] array, int start, char ch) {

        if (start >= array.length) { // base case
            return 0; // ends recursion

        } else if (array[start] == ch) {
            // adds 1 each time char matches and recursively calls
            return 1 + charCount(array, start + 1, ch);

        } else {
            return charCount(array, start + 1, ch);
        }

    }
}
