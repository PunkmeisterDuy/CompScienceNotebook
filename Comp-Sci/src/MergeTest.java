/*
 * Duy Nguyen
 * MergeTest.java
 * Merges two sorted lists into one combined sorted list
 */

public class MergeTest {

    public static int[] merge(int[] list1, int[] list2) {

        int listLength = list1.length + list2.length;

        int[] list3 = new int[listLength];

        int i = 0;

        while (i < 100){
            if (list1[i] > list2[i]) {
                list3[i] = list1[i];
            }
            else if (list1[i] < list2[i]) {
                list3[i] = list2[i];
            }
            else {
                list3[i] = list1[i];
                list3[i] = list2[i];
            }
        }


        return list3;
    }

    public static void main(String[] args) {

        int[] list1 = {1, 2, 3, 4, 5};
        int[] list2 = {6, 7, 8, 9 , 10};

        System.out.println(merge(list1, list2));

    }
}
