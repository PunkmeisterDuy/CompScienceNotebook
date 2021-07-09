package Comsc076.C23;

/*
 * Duy Nguyen
 * Sorting.java
 * Executes, measures, and displays sort algorithm applied to varying array sizes
 */

public class Sorting {
    public static void main(String[] args) {

        String[] headers = {"Array Size",
                "Selection Sort",
                "Merge Sort",
                "Quick Sort",
                "Heap Sort",
                "Radix Sort"};

        int[] arraySizes = {50000, 100000, 150000, 200000, 250000, 300000};
        displayTable(headers, arraySizes);

        /* individually tests sorts
        int[] array = generateShuffledArray(10);
        int[] sortedArray = radixSort(array);

        System.out.print("Original Array:");
        for (int i : array) {
                System.out.print(i+ " ");
        }
        System.out.print("\nSorted Array:");

        for (int j : sortedArray) {
            System.out.print(j + " ");
        }
        System.out.println();
         */
    }


    static void displayTable(String[] headers, int[] arraySizes) {

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

        // displays row and executes sorts
        for (int i: arraySizes) {
            System.out.printf("|%,14d", i);

            //generates shuffled array from 0 to array size (exclusive)
            int[] array = generateShuffledArray(i);

            // executes, measures time, and displays sort times
            for (int j = 0; j < headers.length - 1; j++) {
                long startTime = System.currentTimeMillis( );

                // does sort method depending on j count
                switch (j) {
                    case 0:
                        selectionSort(array);
                        //selection sort
                        break;
                    case 1:
                        mergeSort(array);
                        //merge sort
                        break;
                    case 2:
                        quickSort(array);
                        // quick sort
                        break;
                    case 3:
                        heapSort(array);
                        //heap sort
                        break;
                    case 4:
                        radixSort(array);
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

        //swaps i with random index
        for (int i =0; i < size; i++) {
            int rand = (int) (size * Math.random());
            int temp = array[i];

            array[i] = array[rand];
            array[rand] = temp;
        }

        return array;
    }


    // SORT ALGORITHMS

    static int[] selectionSort(int[] array) {
        int n = array.length;
        int[] sortedArray = array.clone();

        for (int i = 0; i < n - 1; i++) {
            int currentMin = i; // up to i -1

            // compares indexes after i if j < current min
            // updates current min as it scans through remaining indexes
            for (int j = i + 1; j < n; j++) {
                if(sortedArray[j] < sortedArray[currentMin]) {
                    currentMin = j;
                }
            }
            // if there is a new current min, swap with current min
            if (currentMin!= i) {
                int temp = sortedArray[i];

                sortedArray[i] = sortedArray[currentMin];
                sortedArray[currentMin] = temp;
            }
        }
        return sortedArray;
    }

    static int[] mergeSort(int[] array) {
        int[] sortedArray = array.clone();

        mergeSort(sortedArray, 0, array.length - 1);

        return sortedArray;
    }
    // recursive helper function
    static void mergeSort(int[] array, int start, int end) {
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
    // merges sorted arrays
    static void merge(int[] array, int start, int middle, int end) {
        int[] leftArray  = new int[middle - start + 1];
        int[] rightArray = new int[end - middle];

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
            if (leftArray[leftIndex] <= rightArray[rightIndex])
            {
                array[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            }
            else
            {
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

    static int[] quickSort(int[] array) {
        int[] sortedArray = array.clone();
        quickSort(sortedArray, 0, array.length - 1);

        return sortedArray;
    }
    // recursive helper function
    static void quickSort(int[] array, int startIndex, int endIndex) {
        // verify that the start and end index have not overlapped
        if (startIndex < endIndex)
        {
            // calculates  pivotIndex
            int pivotIndex = partition(array, startIndex, endIndex);
            // sorts the left of pivot
            quickSort(array, startIndex, pivotIndex);
            // sorts the right of pivot
            quickSort(array, pivotIndex + 1, endIndex);
        }
    }
    static int partition(int[] array, int startIndex, int endIndex) {
        int pivotIndex = (startIndex + endIndex) / 2;
        int pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true){
            // searches from left until finds value > pivot value
            // values left of pivot are less than pivot
            do {
                startIndex++;
            } while (array[startIndex] < pivotValue);

            // start from right until value is < pivot value
            // values right of pivot are greater than pivot
            do {
                endIndex--;
            } while (array[endIndex] > pivotValue);

            if (startIndex >= endIndex) {
                return endIndex;
            }

            // swaps startIndex and endIndex
            int temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
    }

    static int[] heapSort(int[] array) {
        int[] sortedArray = array.clone();

        int size = array.length;
        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(sortedArray, size, i);

        // one by one extract an element from heapSort
        for (int i = size - 1; i >= 0; i--)
        {
            // move current root to end
            int temp = sortedArray[0];
            sortedArray[0] = sortedArray[i];
            sortedArray[i] = temp;

            // call max heapify on the reduced heapSort
            heapify(sortedArray, i, 0);
        }

        return sortedArray;
    }
    static void heapify(int[] array, int size, int i) {
        int max   =  i; // initialize max as root of tree
        int left  = 2 * i + 1;
        int right = 2 * i + 2;

        // if left child is larger than root
        if (left < size && array[left] > array[max])
            max = left;

        // if right child is larger than max
        if (right < size && array[right] > array[max])
            max = right;

        // if max is not root
        if (max != i)
        {
            // swaps max with index
            int temp = array[i];
            array[i] = array[max];
            array[max] = temp;

            // recursively heapify the affected sub-tree
            heapify(array, size, max);
        }
    }

    static int[] radixSort(int[] array) {
        int[] sortedArray = array.clone();

        // largest number for how many place values
        int maxValue = sortedArray[0];
        for (int i = 1; i < sortedArray.length; i++) {
            if (sortedArray[i] > maxValue) {
                maxValue = sortedArray[i];
            }
        }

        // sort the array at each placeValue
        for (int placeValue = 1; maxValue / placeValue > 0; placeValue *= 10) {
            countingSort(sortedArray, placeValue);
        }

        return sortedArray;
    }
    // sorts between range of place value
    static void countingSort(int[] array, int placeValue) {
        // step 1: create an empty array that will store the sorted version of the array
        int[] output = new int[array.length];
        // step 2: create an empty array that will track the placeValue frequency
        int[] placeValueFrequency = new int[10];

        // step 3: find the amount of times the array has a value in the placeValue we're searching for
        for (int i = 0; i < array.length; i++)
        {
            int value = (array[i] / placeValue) % 10;
            placeValueFrequency[value]++;
        }

        // step 4: reposition the indexes so that the indexes with smaller placeValues are moved to the beginning of the array
        for (int i = 1; i < 10; i++)
            placeValueFrequency[i] += placeValueFrequency[i - 1];

        // step 5: starting from the end of the array, add each index index from the original array to the output array
        // the frequency - 1 of the value in the current placeValue will represent the index to place the original index
        for (int i = array.length - 1; i >= 0; i--)
        {
            int value = (array[i] / placeValue) % 10; // the value of the current placeValue
            output[placeValueFrequency[value] - 1] = array[i];
            placeValueFrequency[value]--;
        }

        // step 6: copy the more sorted version of the array back into the original array
        for (int i = 0; i < array.length; i++)
            array[i] = output[i];
    }

    /* https://big-o.io/ helped a lot with these algorithms
     * https://www.youtube.com/channel/UCzDJwLWoYCUQowF_nG3m5OQ
     * helped visualize the algorithms
     */
}
