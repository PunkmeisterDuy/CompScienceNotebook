/*
 * Duy Nguyen
 * MergeTest.java
 * Merges two sorted lists into one combined sorted list
 */

public class MergeTest {

    public static int[] merge(int[] list1, int[] list2) {

        int listLength = list1.length + list2.length;

        int[] resultList = new int[listLength];

        int list1Idx = 0;
        int list2Idx = 0;

        for (int i = 0; i < listLength; i++) {

            if ((list1Idx < list1.length) && (list2Idx < list2.length)) {

                int item1 = list1[list1Idx];
                int item2 = list2[list2Idx];

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
            else if (list1Idx >= list1.length) {
                resultList[i] = list2[list2Idx];
                list2Idx++;
            }
            else if (list2Idx >= list2.length) {
                resultList[i] = list1[list1Idx];
                list1Idx++;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {

        int[] list1 = {1, 2, 3, 4, 5};
        int[] list2 = {5, 6, 7, 8, 9 , 10};
        int[] list3 = merge(list1, list2);

        for (int i = 0; i < list3.length; i++) {
            System.out.println(list3[i]);
        }

    }
}
