package Comsc076.C18;

/*
 * Duy Nguyen
 * TestReverseDisplay.java (Recursion)
 * Asks for string input and prints all permutations of string recursively
 */

import java.util.Scanner;

public class TestDisplayPermutation {
    public static void main(String[] args) {

        // asks for input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String stringInput = input.nextLine();


        displayPermutation(stringInput);

    }

    public static void displayPermutation(String string) {
        displayPermutation("", string);
    }

    // recursive helper method
    public static void displayPermutation(String string1, String string2) {

        if (string2.isEmpty()) { // base case
            System.out.println(string1);
        } else {

            for (int i = 0; i < string2.length(); i++) {
                // for each letter recursively call, and add another char that
                // has not been already used in the string

                displayPermutation(
                        string1 + string2.charAt(i) ,
                        (string2.substring(0, i) + string2.substring(i+1))
                        // uses string2 with chars that hasn't been added to string1
                        // substring is exclusive with second parameter
                );
            }
        }
    }
}


