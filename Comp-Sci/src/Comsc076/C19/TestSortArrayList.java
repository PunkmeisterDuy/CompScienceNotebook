package Comsc076.C19;

/*
 * Duy Nguyen
 * TestSortArrayList.java (Generics)
 * Creates 3 different array lists of Integer, Double, String, sorts, and prints them
 */

import java.util.ArrayList;

public class TestSortArrayList {

    public static void main(String[] args) {

        // declares/initializes array lists
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(2); //auto boxing
        intList.add(4);
        intList.add(3);

        ArrayList<Double> doubleList = new ArrayList<Double>();
        doubleList.add(3.4);
        doubleList.add(1.2);
        doubleList.add(-12.3);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("Bob");
        stringList.add("Alice");
        stringList.add("Ted");
        stringList.add("Carol");

        //sorts lists

        sort(intList);
        sort(doubleList);
        sort(stringList);

        // prints out lists
        System.out.print("Sorted Integer List: ");
        printArrayList(intList);

        System.out.print("Sorted Double List: ");
        printArrayList(doubleList);

        System.out.print("Sorted String List: ");
        printArrayList(stringList);

    }


    // selection sort
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {

        E currentMin;
        int currentIndex;

        // list.size() - 1 to ignore last item
        for (int i = 0; i < list.size() - 1; i++) {
            currentMin = list.get(i);
            currentIndex = i;

            // iterates/compares number to current min (j = i+1 to iterate last item)
            for (int j = i + 1; j < list.size(); j++) {
                if (currentMin.compareTo(list.get(j)) > 0) {
                    currentMin = list.get(j);
                    currentIndex = j;
                }

            }
            if (currentIndex != i) { // if current index is not i, index is j
                // swaps current min with new min (i)
                list.set(currentIndex, list.get(i));
                list.set(i, currentMin);
            }

        }
    }


    // prints array list in readable format
    public static <E> void printArrayList(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

}
