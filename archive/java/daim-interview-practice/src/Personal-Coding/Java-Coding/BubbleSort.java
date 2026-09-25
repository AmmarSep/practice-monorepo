//Java code for bubble sort
/**
 * The BubbleSort class implements the bubble sort algorithm to sort an array of integers in ascending order.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };
        bubbleSort(arr);
        System.out.println("Sorted array");
        printArray(arr);
    }

    /**
     * Sorts the given array of integers using the bubble sort algorithm.
     *
     * @param arr the array to be sorted
     */
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // Iterate through the array from 0 to n-1-i
            // The largest element will be bubbled to the end in each iteration
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements and swap them if the current element is greater than the next element
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Prints the elements of the given array.
     *
     * @param arr the array to be printed
     */
    static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // Print each element followed by a space
            System.out.print(arr[i] + " ");
        }
        System.out.println(); // Print a new line after printing all elements
    }
}