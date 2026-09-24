import java.util.Scanner;
import java.util.Arrays;

public class Q7_SortingArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        System.out.print("Ascending: ");
        for (int num : arr) System.out.print(num + " ");
        System.out.print("\nDescending: ");
        for (int i = n - 1; i >= 0; i--) System.out.print(arr[i] + " ");
        System.out.println();
        sc.close();
    }
}
