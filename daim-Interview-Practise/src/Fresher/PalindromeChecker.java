package Fresher;

/**
 * This class demonstrates different ways to check if a string or number is a palindrome.
 * A palindrome is a word, number, phrase, or other sequence of characters
 * that reads the same forward and backward (ignoring spaces, punctuation, and capitalization).
 * 
 * Examples: "radar", "level", "A man, a plan, a canal: Panama", 121, 1221
 */
public class PalindromeChecker {

    // Method 1: Using string reversal to check palindrome
    public static boolean isPalindromeUsingReversal(String str) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Create a reversed string
        StringBuilder sb = new StringBuilder(cleanStr);
        String reversedStr = sb.reverse().toString();

        // Compare original and reversed
        return cleanStr.equals(reversedStr);
    }

    // Method 2: Using two pointers approach
    public static boolean isPalindromeUsingTwoPointers(String str) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = cleanStr.length() - 1;

        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // Method 3: Check if a number is palindrome
    public static boolean isNumberPalindrome(int number) {
        // Handle negative numbers
        if (number < 0) {
            return false;
        }

        int original = number;
        int reversed = 0;

        while (number > 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number /= 10;
        }

        return original == reversed;
    }

    // Method 4: Recursive approach for string palindrome checking
    public static boolean isPalindromeRecursive(String str) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeRecursiveHelper(cleanStr, 0, cleanStr.length() - 1);
    }

    private static boolean isPalindromeRecursiveHelper(String str, int left, int right) {
        // Base case: if we've checked all characters or have only one character left
        if (left >= right) {
            return true;
        }

        // If characters don't match, it's not a palindrome
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }

        // Recursive call to check the inner substring
        return isPalindromeRecursiveHelper(str, left + 1, right - 1);
    }

    public static void main(String[] args) {
        // Test strings
        String[] testStrings = {
            "radar",
            "level",
            "A man, a plan, a canal: Panama",
            "Race a car",
            "hello",
            "Madam, I'm Adam"
        };

        // Test numbers
        int[] testNumbers = {121, 1221, 12321, 12345, 123456, 1001};

        System.out.println("=== Palindrome Checker ===\n");

        // Check strings
        System.out.println("String Palindrome Tests:");
        for (String str : testStrings) {
            boolean method1 = isPalindromeUsingReversal(str);
            boolean method2 = isPalindromeUsingTwoPointers(str);
            boolean method3 = isPalindromeRecursive(str);

            System.out.println("\nString: \"" + str + "\"");
            System.out.println("  Using Reversal: " + (method1 ? "Palindrome" : "Not Palindrome"));
            System.out.println("  Using Two Pointers: " + (method2 ? "Palindrome" : "Not Palindrome"));
            System.out.println("  Using Recursion: " + (method3 ? "Palindrome" : "Not Palindrome"));
            System.out.println("  All methods agree: " + (method1 == method2 && method2 == method3));
        }

        // Check numbers
        System.out.println("\nNumber Palindrome Tests:");
        for (int num : testNumbers) {
            boolean result = isNumberPalindrome(num);
            System.out.println(num + ": " + (result ? "Palindrome" : "Not Palindrome"));
        }

        // Performance comparison
        System.out.println("\n=== Performance Comparison ===");
        String longPalindrome = "A man, a plan, a canal, Panama, a ham, a yak, a yam, a hat, a canal: Panama";
        int iterations = 100000;

        // Reversal method
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            isPalindromeUsingReversal(longPalindrome);
        }
        long reversalTime = System.nanoTime() - startTime;

        // Two pointers method
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            isPalindromeUsingTwoPointers(longPalindrome);
        }
        long twoPointersTime = System.nanoTime() - startTime;

        // Recursive method
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            isPalindromeRecursive(longPalindrome);
        }
        long recursiveTime = System.nanoTime() - startTime;

        System.out.println("Time for " + iterations + " iterations:");
        System.out.println("  Reversal method: " + reversalTime + " ns");
        System.out.println("  Two pointers method: " + twoPointersTime + " ns");
        System.out.println("  Recursive method: " + recursiveTime + " ns");
    }
}
