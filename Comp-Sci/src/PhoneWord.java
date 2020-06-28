/*
 * Duy Nguyen
 * PhoneWord.java
 * Converts Word into a Phone Number
 */

import java.util.Scanner;

public class PhoneWord {
    public static void main(String[] args) {

        String word;
        String phone = "";

        // Takes input and repeats prompt if input is invalid
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter a phone word that is at least 7 letters: ");
            word = input.nextLine();
            if (word.length() < 7) {
                System.out.println("Your phone word is not long enough " +
                        "for a phone number.\n");
            }
        } while (word.length() < 7);

        // Identifies char at index i and then appends number to phone
        for (int i = 0; phone.length() < 7 ; i++) {
            if (Character.isDigit(word.charAt(i))) {
                phone += word.charAt(i);
            }
            switch(word.charAt(i)) {
                case 'a': case 'b': case 'c': case 'A': case 'B': case 'C':
                    phone += "2";
                    break;
                case 'd': case 'e': case 'f': case 'D': case 'E': case 'F':
                    phone += "3";
                    break;
                case 'g': case 'h': case 'i': case 'G': case 'H': case 'I':
                    phone += "4";
                    break;
                case 'j': case 'k': case 'l': case 'J': case 'K': case 'L':
                    phone += "5";
                    break;
                case 'm': case 'n': case 'o': case 'M': case 'N': case 'O':
                    phone += "6";
                    break;
                case 'p': case 'q': case 'r': case 's':
                    case 'P': case 'Q': case 'R': case 'S':
                    phone += "7";
                    break;
                case 't': case 'u': case 'v': case 'T': case 'U': case 'V':
                    phone += "8";
                    break;
                case 'w': case 'x': case 'y': case 'z':
                    case 'W': case 'X': case 'Y': case 'Z':
                    phone += "9";
                    break;
            }
        }

        // Inserts '-' into phone number
        StringBuilder phoneNumber = new StringBuilder(phone);
        phoneNumber.insert(3, '-');

        System.out.println("Your number is: " + phoneNumber);
    }
}
