//Implement binary search:
//Write a binary search algorithm to find an element in a sorted array

import java.util.Scanner;

import java.util.Scanner;

/**
 * The BinarySearch class implements a binary search algorithm to find the index of a given element in a sorted array.
 */
public class BinarySearch {

    /**
     * The main method is the entry point of the program.
     * It prompts the user to enter the number of elements in the array, the elements themselves, and the number to be searched.
     * It then calls the binarySearch method to find the index of the number in the array and prints the result.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        int n;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of elements in the array:");
        n = s.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements of the array in sorted order:");
        for(int i = 0; i < n; i++){
            arr[i] = s.nextInt();
        }
        System.out.print("Enter the number to be searched:");
        int key = s.nextInt();
        int result = binarySearch(arr, key);
        if (result == -1)
            System.out.println("Element not found");
        else
            System.out.println("Element found at index " + result);
    }

    /**
     * The binarySearch method performs a binary search on a sorted array to find the index of a given element.
     *
     * @param arr The sorted array to be searched.
     * @param key The element to be searched for.
     * @return The index of the element in the array, or -1 if the element is not found.
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}

// Time Complexity: O(log n)
// Space Complexity: O(1)

// Enter number of elements in the array:5
// Enter elements of the array in sorted order:
// 2
// 4
// 6
// 8
// 10
// Enter the number to be searched:8
// Element found at index 3