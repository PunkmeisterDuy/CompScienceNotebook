/*
 * Duy Nguyen
 * MergeTest.java
 * Merges two sorted lists into one combined sorted list
 */

import java.util.Scanner;

public class MergeTest {

    // Merges list1 and list2 into sorted list
    public static int[] merge(int[] list1, int[] list2) {

        int list1Length = list1.length;
        int list2Length = list2.length;

        int listLength = list1Length + list2Length;

        int[] resultList = new int[listLength];

        int list1Index = 0;
        int list2Index = 0;
        int resultIndex = 0;

        while ((list1Index < list1Length) && (list2Index < list2Length)) {

            if (list1[list1Index] <= list2[list2Index]) {
                resultList[resultIndex++] = list1[list1Index++];
            }
            else {
                resultList[resultIndex++] = list2[list2Index++];
            }
        }

        while (list1Index < list1Length) {
            resultList[resultIndex++] = list1[list1Index++];
        }

        while (list2Index < list2Length) {
            resultList[resultIndex++] = list2[list2Index++];
        }

        return resultList;
    }

    // Takes input of list size and elements
    public static int[] inputList(String listName, Scanner input) {

        System.out.printf("Enter size of %s: ", listName);
        int listSize = input.nextInt();
        int[] list = new int[listSize];

        System.out.printf("Enter ordered items of %s: ", listName);
        for (int i = 0; i < listSize; i++) {
            list[i] = input.nextInt();
        }

        return list;
    }

    // Outputs list name and elements
    public static void outputList(String listName, int[] resultList) {

        System.out.printf("%s is: ", listName);

        for (int i = 0; i < resultList.length; i++) {
            System.out.print(" " + resultList[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Takes input for list1 and list2
        int[] list1 = inputList("list 1", input);
        int[] list2 = inputList("list 2", input);

        // Sorts/merges list1 and list2
        int[] resultList = merge(list1, list2);

        // Outputs readable list
        outputList("List 1", list1);
        outputList("List 2", list2);
        outputList("Merged list", resultList);
    }
}
