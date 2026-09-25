package Fresher;

import java.util.Arrays;

/**
 * This class demonstrates various operations on arrays in Java
 */
public class ArrayOperations {

    // Method to find the maximum value in an array
    public static int findMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Method to find the minimum value in an array
    public static int findMin(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    // Method to calculate the average of array elements
    public static double calculateAverage(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }

    // Method to reverse an array
    public static void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            // Swap elements
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // Move towards the middle
            left++;
            right--;
        }
    }

    // Method to search for an element in the array (linear search)
    public static int linearSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i; // Return the index if found
            }
        }
        return -1; // Return -1 if not found
    }

    // Method to search for an element in a sorted array (binary search)
    public static int binarySearch(int[] sortedArray, int key) {
        int left = 0;
        int right = sortedArray.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if key is present at mid
            if (sortedArray[mid] == key) {
                return mid;
            }

            // If key is greater, ignore left half
            if (sortedArray[mid] < key) {
                left = mid + 1;
            }
            // If key is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }

        return -1; // Key not found
    }

    public static void main(String[] args) {
        int[] numbers = {5, 10, 3, 8, 15, 7, 2};
        int[] sortedNumbers = {2, 3, 5, 7, 8, 10, 15};

        System.out.println("=== Array Operations ===\n");
        System.out.println("Original array: " + Arrays.toString(numbers));

        // Basic operations
        System.out.println("\nBasic Operations:");
        System.out.println("Maximum value: " + findMax(numbers));
        System.out.println("Minimum value: " + findMin(numbers));
        System.out.println("Average value: " + calculateAverage(numbers));

        // Reverse the array
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        reverseArray(numbersCopy);
        System.out.println("\nReversed array: " + Arrays.toString(numbersCopy));

        // Search operations
        int keyToFind = 8;
        System.out.println("\nSearch Operations:");

        int linearIndex = linearSearch(numbers, keyToFind);
        System.out.println("Linear Search - Element " + keyToFind + " found at index: " + 
                          (linearIndex != -1 ? linearIndex : "Not found"));

        int binaryIndex = binarySearch(sortedNumbers, keyToFind);
        System.out.println("Binary Search - Element " + keyToFind + " found at index: " + 
                          (binaryIndex != -1 ? binaryIndex : "Not found"));

        // Built-in array operations
        System.out.println("\nBuilt-in Array Operations:");

        // Sorting
        int[] unsortedCopy = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(unsortedCopy);
        System.out.println("Sorted array: " + Arrays.toString(unsortedCopy));

        // Binary search using Arrays class
        int builtInBinaryIndex = Arrays.binarySearch(sortedNumbers, keyToFind);
        System.out.println("Built-in Binary Search - Element " + keyToFind + " found at index: " + builtInBinaryIndex);

        // Fill array with a specific value
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 10);
        System.out.println("Filled array: " + Arrays.toString(filledArray));

        // Compare arrays
        System.out.println("Arrays equal: " + Arrays.equals(sortedNumbers, unsortedCopy));

        // 2D array example
        System.out.println("\n2D Array Example:");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
