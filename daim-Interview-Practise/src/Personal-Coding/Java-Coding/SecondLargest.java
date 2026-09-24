//Find the second largest element in an array
//Traverse the array and keep track of the largest and second-largest elements

import java.util.*;
import java.util.Scanner;

/**
 * The SecondLargest class finds the second largest element in an array of integers.
 */
public class SecondLargest {
    /**
     * The main method is the entry point of the program.
     * It takes user input, creates an array, and finds the second largest element.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        // Prompt the user to enter the size of the array
        System.out.print("Enter the size of the array: ");
        n = sc.nextInt();

        int[] arr = new int[n];

        // Prompt the user to enter the elements of the array
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        // Find the largest and second largest elements in the array
        for (int i = 0; i < n; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }

        // Print the second largest element
        System.out.println("The second largest element is: " + secondLargest);
        System.out.println("The largest element is: " + largest);
    }
}
// Time complexity: O(n)
// Space complexity: O(1)