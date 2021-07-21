package Comsc076;

/*
 * Duy Nguyen
 * Duy_Nguyen_Test2.java
 * Merge sorts a Integer list and Circle using generics
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class TestHelp {
    public static void main(String[] args) {

        // merge sorts a list of Integers
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        mergeSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();


        Circle[] list1 = {new Circle(2), new Circle(3), new Circle(2),
                new Circle(5), new Circle(6), new Circle(1), new Circle(2),
                new Circle(3), new Circle(14), new Circle(12)};

        // sorts circles by radius

        // mergeSort(list1, new CompareRadius()) // method breaks on runtime
        // I was unable to merge sort due to an index out of bounds exception
        Arrays.sort(list1, new CompareRadius());
        // prints circles
        for (int i = 0; i < list1.length; i++) {
            System.out.print(list1[i] + " | ");
        }
    }

    //public static <E extends Comparable<E>> void mergeSort(E[] list) {
    //}

    static <E extends Comparable<E>> void mergeSort(E[] list) {
        mergeSort(list, 0, list.length - 1);
    }
    public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {
        mergeSort(list, 0, list.length, comparator);
    }

    // recursive helper method
    public static <E extends Comparable<E>> void mergeSort(E[] array, int start, int end) {
        // base case
        // until recursively call's length == 1
        if (start < end)
        {
            // find the middle point
            int middle = (start + end) / 2;

            mergeSort(array, start, middle); // recursively sorts first half
            mergeSort(array, middle + 1, end);  // recursively sort second half

            // merge the sorted halves
            merge(array, start, middle, end);
        }
    }
    public static <E> void mergeSort(E[] array, int start, int end, Comparator<? super E> comparator) {
        if (start < end) {
            // find the middle point
            int middle = (start + end) / 2;

            mergeSort(array, start, middle, comparator); // recursively sorts first half
            mergeSort(array, middle + 1, end, comparator);  // recursively sort second half

            // merge the sorted halves
            merge(array, start, middle, end, comparator);
        }
    }

    // merges sorted arrays
    static <E extends Comparable<E>> void merge(E[] array, int start, int middle, int end) {
        E[] leftArray  = (E[]) new Comparable[middle - start + 1];
        E[] rightArray = (E[]) new Comparable[end - middle];

        // fills in left array
        for (int i = 0; i < leftArray.length; ++i)
            leftArray[i] = array[start + i];

        // fills in right array
        for (int i = 0; i < rightArray.length; ++i)
            rightArray[i] = array[middle + 1 + i];

        /* Merge the temp arrays */

        // initial indexes of first and second subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        int currentIndex = start;

        // compare each index of the subarrays adding the lowest value to the currentIndex
        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0 ) {
                array[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            }
            else {
                array[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        // copies remaining elements of leftArray
        while (leftIndex < leftArray.length) {
            array[currentIndex++] = leftArray[leftIndex++];
        }

        // copies remaining elements of rightArray
        while (rightIndex < rightArray.length) {
            array[currentIndex++] = rightArray[rightIndex++];
        }
    }

    static <E> void merge(E[] array, int start, int middle, int end, Comparator<? super E> comparator) {
        int leftArraySize = middle - start + 1 + 1;
        int rightArraySize = end - middle + 1;

        ArrayList<E> leftArray = new ArrayList(leftArraySize);
        ArrayList<E> rightArray = new ArrayList(rightArraySize);

        // fills in left array
        for (int i = 0; i < leftArraySize; ++i) {
            leftArray.add(i, array[start + i]);
        }

        // fills in right array
        for (int i = 0; i < rightArraySize; ++i) {
            rightArray.add(i, array[middle + i + 1]);
        }

        /* Merge the temp arrays */

        // initial indexes of first and second subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        int currentIndex = start;

        // compare each index of the subarrays adding the lowest value to the currentIndex
        while (leftIndex < leftArraySize && rightIndex < rightArraySize) {
            if (comparator.compare(leftArray.get(leftIndex), rightArray.get(rightIndex)) <= 0 ) {
                array[currentIndex] = leftArray.get(leftIndex);
                leftIndex++;
            }
            else if (comparator.compare(leftArray.get(leftIndex), rightArray.get(rightIndex)) > 0){
                array[currentIndex] = rightArray.get(rightIndex);
                rightIndex++;
            }
            currentIndex++;
        }

        // copies remaining elements of leftArray
        while (leftIndex < leftArraySize) {
            array[currentIndex++] = leftArray.get(leftIndex++);
        }

        // copies remaining elements of rightArray
        while (rightIndex < rightArraySize) {
            array[currentIndex++] = rightArray.get(rightIndex++);
        }
    }
}

class CompareRadius implements Comparator<Circle> {
    @Override
    public int compare(Circle circle1, Circle circle2) {
        if (circle1.getRadius() > circle2.getRadius()) {
            return 1;
        } else if (circle1.getRadius() < circle2.getRadius()) {
            return -1;
        } else {
            return 0;
        }
    }
}

// imported from TestGeometricObjects assignment
class Circle extends GeometricObject {
    private int radius;
    private double area;
    private double perimeter;

    Circle() {
        radius = 0;
        area = 0;
        perimeter = 0;
    }

    public Circle(int radius) {
        this.radius = radius;
        getArea();
        getPerimeter();
    }

    Circle(int radius, String color) {
        this.radius = radius;
        getArea();
        getPerimeter();
        setColor(color);
    }

    Circle(int radius, boolean filled) {
        this.radius = radius;
        getArea();
        getPerimeter();
        setFilled(filled);
    }

    Circle(int radius, String color, boolean filled) {
        this.radius = radius;
        getArea();
        getPerimeter();
        setColor(color);
        setFilled(filled);
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        area = (Math.PI) * (radius ^ 2);
        return area;
    }

    public double getPerimeter() {
        perimeter = (2 * Math.PI * radius);
        return perimeter;
    }

    private void setRadius(int radius) {
        this.radius = radius;
    }

    public String toString() {
        return "Radius: " + radius;
        //"\nArea: " + Math.floor(getArea() * 100) / 100 +
        //"\nPerimeter: " + Math.floor(getPerimeter() * 100) / 100 +
        //"\nColor: " + getColor() +
        //"\nFilled: " + isFilled();
    }
}

abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    /**
     * Construct a default geometric object
     */
    protected GeometricObject() {
        dateCreated = new java.util.Date();
    }

    /**
     * Construct a geometric object with color and filled value
     */
    protected GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    /**
     * Return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set a new color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Return filled. Since filled is boolean,
     * the get method is named isFilled
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Set a new filled
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Get dateCreated
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Return a string representation of this object
     */
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color +
                " and filled: " + filled;
    }

    /**
     * Abstract method getArea
     */
    public abstract double getArea();

    /**
     * Abstract method getPerimeter
     */
    public abstract double getPerimeter();
}


    /* book's code
    public static void mergeSort(int[] list) {
        if (list.length > 1) {
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0 , firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            merge(firstHalf, secondHalf, list);

        }
    }

    private static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2]) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
    }
     */

