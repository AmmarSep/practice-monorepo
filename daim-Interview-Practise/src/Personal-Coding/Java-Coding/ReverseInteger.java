//Reverse an integer
//Reverse the digits of an integer (e.g., 12345 becomes 54321)
//java
// Define a class named ReverseInteger
public class ReverseInteger {
    // Define the main method that the JVM will call
    public static void main(String[] args) {
        // Call the reverseInt method with 12345 as the argument and print the result
        System.out.println(reverseInt(12345));
    }

    // Define a method named reverseInt that takes an integer as an argument
    public static int reverseInt(int n) {
        // Initialize a variable named reversed to 0. This will hold the reversed number
        int reversed = 0;

        // As long as n is not 0, keep looping
        while (n != 0) {
            // Multiply reversed by 10 and add the last digit of n
            reversed = reversed * 10 + n % 10;

            // Remove the last digit from n
            n = n / 10;
        }

        // Return the reversed number
        return reversed;
    }
}