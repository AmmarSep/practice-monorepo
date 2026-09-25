//Check if a number is prime:
//Implement a function to verify if a given number is prime
//You can assume that the number is always a positive integer.

/**
 * The Prime class checks whether a given number is prime or not.
 */
public class Prime {

    /**
     * Checks whether a given number is prime.
     *
     * @param num The number to check.
     * @return True if the number is prime, false otherwise.
     */
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main method to test the isPrime method.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println(isPrime(2)); // true
        System.out.println(isPrime(17)); // true
        System.out.println(isPrime(20)); // false
    }
}
//Compare this snippet from PrintPattern.java:
// //Print a pattern:
// //Create patterns using loops (e.g., pyramid, diamond, etc.).
// 
// 
// import java.util.Scanner;
// 
// import java.util.Scanner;
// 
// /**
//  * The PrintPattern class is used to print a pattern of asterisks based on user input.
//  */
// public class PrintPattern {
// 
//     /**
//      * The main method is the entry point of the program.
//      * It prompts the user to enter the number of rows and prints the pattern accordingly.
//      *
//      * @param args The command-line arguments.
//      */
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         System.out.println("Enter the number of rows: ");
//         int rows = sc.nextInt();
//         int k = 0;
//         
//         // Loop through each row
//         for (int i = 1; i <= rows; i++, k = 0) {
//             // Print spaces before the asterisks
//             for (int j = 1; j <= rows - i; j++) {
//                 System.out.print(" ");
//             }
//             
//             // Print asterisks
//             while (k != 2 * i - 1) {
//                 System.out.print("*");
//                 k++;