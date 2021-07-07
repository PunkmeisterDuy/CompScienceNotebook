
/*
 * Duy Nguyen
 * CountOccurrenceOfWords.java
 * Counts how many times word (case-insensitive) in text file as argument
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.Scanner;

public class CountOccurrenceOfWords {

    public static void main(String[] args) {

        // checks if there is only 1 argument
        if (args.length != 1) {
            System.out.println("Please only enter 1 argument");
        }

        // holds words sorted as key and count as value
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();

        try {

            // input from argument as file
            Scanner input = new Scanner(new File(args[0]));

            while (input.hasNext()) {
                String line = input.nextLine();

                // splits each line by regex
                String[] words = line.split("[ @!~{}\\[\\]$#^&*\n\t\r.,;?'\")(]");

                for (String word: words) {
                    // for words without whitespace and A-Z
                    if (word.trim().length() > 0 &&
                            word.trim().matches("[A-Z|a-z]+")) {

                        // converts word into lowercase
                        String lowerCaseWord = word.toLowerCase();

                        // create new word if word isn't found
                        if (!treeMap.containsKey(lowerCaseWord)) {
                            treeMap.put(lowerCaseWord, 1);
                        } else {
                            // increments count if word is already in map
                            int count = treeMap.get(lowerCaseWord);
                            count++;
                            treeMap.put(lowerCaseWord, count);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Displays words and their count in ascending order of the words");
        // lambda expression to print each key and value
        treeMap.forEach((k, v) -> System.out.println(k + " " + v));

    }
}
   