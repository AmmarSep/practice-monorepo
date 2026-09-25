package Fresher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class demonstrates exception handling techniques in Java
 */
public class ExceptionHandlingDemo {

    // Method demonstrating basic try-catch
    public static void divideNumbers() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter numerator: ");
            int numerator = scanner.nextInt();

            System.out.print("Enter denominator: ");
            int denominator = scanner.nextInt();

            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
            System.out.println("Exception details: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid integers");
            System.out.println("Exception details: " + e.getMessage());
        }
    }

    // Method demonstrating try-catch-finally
    public static void readFile(String fileName) {
        FileReader reader = null;

        try {
            reader = new FileReader(fileName);
            System.out.println("File opened successfully");
            // Read operations would go here...
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + fileName);
            System.out.println("Exception details: " + e.getMessage());
        } finally {
            System.out.println("Executing finally block");
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("File closed successfully");
                } catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }
    }

    // Method demonstrating try-with-resources
    public static void readFileWithResources(String fileName) {
        System.out.println("\nUsing try-with-resources:");

        try (FileReader reader = new FileReader(fileName)) {
            System.out.println("File opened successfully");
            // Read operations would go here...
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + fileName);
            System.out.println("Exception details: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        // No finally needed - resources are automatically closed
    }

    // Method demonstrating custom exception
    static class NegativeNumberException extends Exception {
        public NegativeNumberException(String message) {
            super(message);
        }
    }

    public static int calculateSquareRoot(int number) throws NegativeNumberException {
        if (number < 0) {
            throw new NegativeNumberException("Cannot calculate square root of negative number: " + number);
        }
        return (int) Math.sqrt(number);
    }

    // Method demonstrating exception propagation
    public static void methodA() throws IOException {
        methodB();
    }

    public static void methodB() throws IOException {
        methodC();
    }

    public static void methodC() throws IOException {
        throw new IOException("Exception thrown in methodC");
    }

    public static void main(String[] args) {
        System.out.println("=== Exception Handling Demo ===\n");

        // 1. Basic try-catch
        System.out.println("1. Basic try-catch example with division:\n");
        // Note: Uncomment to run interactively
        // divideNumbers();

        // Simulating division by zero
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
            System.out.println("Exception details: " + e.getMessage());
        }

        System.out.println("\n2. Multiple catch blocks:\n");
        // Array index out of bounds
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("Accessing element at index 5: " + numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index out of bounds");
            System.out.println("Exception details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());
        }

        System.out.println("\n3. try-catch-finally example:\n");
        readFile("nonexistentfile.txt");

        System.out.println("\n4. try-with-resources example:\n");
        readFileWithResources("nonexistentfile.txt");

        System.out.println("\n5. Custom exception example:\n");
        try {
            int result = calculateSquareRoot(-25);
            System.out.println("Square root: " + result);
        } catch (NegativeNumberException e) {
            System.out.println("Custom exception caught: " + e.getMessage());
        }

        System.out.println("\n6. Exception propagation example:\n");
        try {
            methodA();
        } catch (IOException e) {
            System.out.println("Exception propagated to main: " + e.getMessage());
            System.out.println("\nStack trace:");
            e.printStackTrace();
        }

        System.out.println("\nProgram completed successfully!");
    }
}
