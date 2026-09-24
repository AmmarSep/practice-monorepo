//Check if a string is a palindrome:
//Compare the characters from both ends of the string
//If the characters from both ends are equal, repeat the process with the inner characters
//If the characters are not equal, return false
//If the string is empty or has one character, return true
//Example:
//Input: "madam"
//Output: true
//Input: "hello"
//Output: false
//Input: "Able was I ere I saw Elba"
//Output: true

/**
 * The IsStringPalindrome class checks whether a given string is a palindrome or not.
 * A palindrome is a word, phrase, number, or other sequence of characters that reads the same forward and backward.
 */
public class IsStringPalindrome {

    /**
     * Checks whether a given string is a palindrome.
     *
     * @param str The input string to check.
     * @return True if the string is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String str) {
        // Remove all non-alphanumeric characters and convert the string to lowercase
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeHelper(str);
    }

    /**
     * Helper method to check whether a given string is a palindrome.
     *
     * @param str The input string to check.
     * @return True if the string is a palindrome, false otherwise.
     */
    private static boolean isPalindromeHelper(String str) {
        // If the string is empty or has one character, return true
        if (str.length() <= 1) {
            return true;
        }

        // Compare the characters from both ends of the string
        if (str.charAt(0) != str.charAt(str.length() - 1)) {
            return false;
        }

        // Repeat the process with the inner characters
        return isPalindromeHelper(str.substring(1, str.length() - 1));
    }

    /**
     * Main method to test the isPalindrome method.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println(isPalindrome("madam")); // true
        System.out.println(isPalindrome("hello")); // false
        System.out.println(isPalindrome("Able was I ere I saw Elba")); // true
    }
}