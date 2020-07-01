/*
 * Duy Nguyen
 * Creditcard.java
 * Takes input of credit card and returns validity/type
 */

import com.sun.source.doctree.SummaryTree;

import java.util.Scanner;

public class Creditcard {

    // Calculates how many digits in card
    public static int getSize(long card) {
        int digits = 0;

        while (card >= 1){
            card /= 10;
            ++digits;
        }
        return digits;
    }

    // Gets prefix based off how long index is
    public static long getPrefix(long card, int index) {
        int digits = getSize(card);
        if (index > digits) {
            return card;
        }
        else {
            int prefix = (int)(card / (Math.pow(10, digits - index)));
            return prefix;
        }
    }

    // Identifies card type
    public static String cardIdentifier(long card) {
        int digits = getSize(card);
        int firstNumber = (int)(card / (Math.pow(10, digits - 1)));
        String cardType;
        switch(firstNumber) {
            case 4:
                cardType = "Visa";
                break;
            case 5:
                cardType = "Master";
                break;
            case 6:
                cardType = "Discover";
                break;
            case 3:
                if (firstNumber == 7) {
                    cardType = "American Express";
                    break;
                }
            default:
                cardType = null;
                break;
        }
        return cardType;
    }

    // Checks Validity of card
    public static boolean isValid(long card)  {
        int digits = getSize(card);
        String cardType = cardIdentifier(card);
        if ((digits >= 13) && (digits <= 16)
                && (cardType != null) && ((totalSum(card) % 10) == 0)) {
            return true;
        }
        else {
            return false;
        }
    }

    // Formats validity into string
    public static String isValidFormatter(long card) {
        if (isValid(card)) {
            return "valid";
        }
        else {
            return "invalid";
        }
    }

    // Finds digit based off index of number
    public static int numberIndexer(long card, int index) {
        int digits = getSize(card);
        int number = (int)((card / Math.pow(10, (digits - index - 1))) % 10);
        return number;
    }

    // Sums the sum of double even place using getDoubledDigitSum()
    public static long sumOfDoubleEvenPlace(long card) {
        int sum = 0;
        int digits = getSize(card);
        for (int i = digits - 1; i > 0; i -= 2) {
            int doubleEven = (numberIndexer(card, i - 1) * 2);
            sum += getDoubledDigitSum(doubleEven);
        }
        return sum;
    }

    public static long totalSum(long card) {
        long totalSum = sumOfDoubleEvenPlace(card) + sumOfOddPlace(card);
        return totalSum;
    }

    // Determines if double digit and then adds sum
    public static long getDoubledDigitSum(int digit) {
        int sum = 0;
        if (digit >= 10) {
            sum += (digit % 10) + ((int)(digit / 10));
        }
        else {
            sum += digit;
        }
        return sum;
    }

    // Adds sum of each odd placed digit
    public static long sumOfOddPlace(long card) {
        int sum = 0;
        int digits = getSize(card);
        for (int i = digits; i > 0; i -= 2) {
            sum += numberIndexer(card, i - 1);
        }
        return sum;
    }

    public static void main(String[] args) {

        // Takes input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter credit card number: ");
        long card = input.nextLong();

        // Checks for card type and if valid
        String cardType = cardIdentifier(card);
        String valid = isValidFormatter(card);

        System.out.printf("This is a %s %s card number.\n", valid, cardType);
    }
}
