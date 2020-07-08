/*
 * Duy Nguyen
 * MergeTest.java
 * Merges two sorted lists into one combined sorted list
 */

import java.util.Scanner;

public class MergeTest {

    public static int[] merge(int[] list1, int[] list2) {

        int list1Length = list1.length;
        int list2Length = list2.length;

        int listLength = list1Length + list2Length;

        int[] resultList = new int[listLength];

        int list1Idx = 0;
        int list2Idx = 0;

        for (int i = 0; i < listLength; i++) {

            if ((list1Idx < list1Length) && (list2Idx < list2Length)) {

                int item1 = list1[list1Idx];
                int item2 = list2[list2Idx];
                // Isn't initialized before to avoid out of index error

                if (item1 < item2) {
                    resultList[i] = item1;
                    list1Idx++;
                } else if (item1 > item2) {
                    resultList[i] = item2;
                    list2Idx++;
                } else {
                    // defaults to list 1 if equal
                    resultList[i] = item1;
                    list1Idx++;
                }
            }
            else if (list1Idx >= list1Length) {
                resultList[i] = list2[list2Idx];
                list2Idx++;
            }
            else {
                resultList[i] = list1[list1Idx];
                list1Idx++;
            }
        }
        return resultList;
    }

    public static int[] input(String listName, Scanner input) {

        System.out.printf("Enter size of %s: ", listName);
        int listSize = input.nextInt();
        int[] list = new int[listSize];

        System.out.printf("Enter ordered items of %s: ", listName);
        for (int i = 0; i < listSize; i++) {
            list[i] = input.nextInt();
        }
        return list;
    }

    public static void output(int[] resultList) {
        System.out.print("The merged list is:");

        for (int i = 0; i < resultList.length; i++) {
            System.out.print(" " + resultList[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Takes input for list1 and list2
        int[] list1 = input("list 1", input);
        int[] list2 = input("list 2", input);

        // Sorts/merges list1 and list2
        int[] resultList = merge(list1, list2);

        // Outputs readable list
        output(resultList);
    }
}
