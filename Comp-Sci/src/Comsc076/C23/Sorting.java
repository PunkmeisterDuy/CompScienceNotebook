package Comsc076.C23;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Sorting {
    public static void main(String[] args) throws InterruptedException {

        String[] headers = {"Array Size", "Selection Sort", "Merge Sort", "Quick Sort", "Heap Sort", "Radix Sort"};
        int[] arraySizes = {50000, 100000, 150000, 200000, 250000, 300000};

        // prints headers
        for (String i: headers) {
            System.out.printf("|%-14s", i);
        }
        System.out.println("|");

        // prints dashed line
        for (int i = 0; i < (headers.length); i++) {
            System.out.printf("|%s", "--------------");
        }
        System.out.println("|");


        // prints/updates row and executes sorts
        for (int i: arraySizes) {
            System.out.printf("|%,14d", i);

            //generates shuffled array from 0 to array size (exclusive)
            int[] randomArray = generateShuffledArray(i);
            /*
            for (int m : randomArray) {
                System.out.println(m);
            }
             */

            // executes, measures time, and displays sort times
            for (int j = 0; j < headers.length - 1; j++) {
                long startTime = System.currentTimeMillis( );

                // does sort method depending on j count
                switch (j) {
                    case 0:
                        Thread.sleep(100);
                        //selection sort
                        break;
                    case 1:
                        Thread.sleep(10);
                        //merge sort
                        break;
                    case 2:
                        Thread.sleep(1);
                        // quick sort
                        break;
                    case 3:
                        Thread.sleep(5);
                        //heap sort
                        break;
                    case 4:
                        Thread.sleep(15);
                        // radix sort
                        break;
                }
                long endTime = System.currentTimeMillis( );

                long executionTime = endTime - startTime;
                System.out.printf("|%,12dms", executionTime);
            }

            // prints dashed line
            System.out.println("|");
            for (int k = 0; k < (headers.length); k++) {
                System.out.printf("|%s", "--------------");
            }
            System.out.println("|");
        }
    }

    static int[] generateShuffledArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i;
        }

        for (int i =0; i < size; i++) {
            int rand = (int) (size * Math.random());
            int temp = array[i];

            array[i] = array[rand];
            array[rand] = temp;
        }

        return array;
    }

}
