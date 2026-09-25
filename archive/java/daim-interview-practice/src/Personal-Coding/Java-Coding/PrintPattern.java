//Print a pattern:
//Create patterns using loops (e.g., pyramid, diamond, etc.).


import java.util.Scanner;

import java.util.Scanner;

/**
 * The PrintPattern class is used to print a pattern of asterisks based on user input.
 */
public class PrintPattern {

    /**
     * The main method is the entry point of the program.
     * It prompts the user to enter the number of rows and prints the pattern accordingly.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = sc.nextInt();
        int k = 0;
        
        // Loop through each row
        for (int i = 1; i <= rows; i++, k = 0) {
            // Print spaces before the asterisks
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            
            // Print asterisks
            while (k != 2 * i - 1) {
                System.out.print("*");
                k++;
            }
            
            System.out.println(); // Move to the next line
        }
        
        sc.close(); // Close the scanner
    }
}