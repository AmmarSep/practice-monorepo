// Program 7: Program for sorting the given numbers in Ascending and Descending order

import java.util.Scanner;
import java.util.Arrays;

public class P7_SortingNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] numbers = new int[n];

        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        // Ascending order
        int[] ascending = numbers.clone();
        Arrays.sort(ascending);

        System.out.println("\nAscending Order:");
        for (int num : ascending) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Descending order
        System.out.println("\nDescending Order:");
        for (int i = ascending.length - 1; i >= 0; i--) {
            System.out.print(ascending[i] + " ");
        }
        System.out.println();

        scanner.close();
    }
}
