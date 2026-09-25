package Fresher;

/**
 * The ReverseString class provides a simple demonstration of reversing a string.
 * It utilizes a for-loop to iterate through the characters of the input string in reverse order
 * and constructs the reversed string by concatenating characters to an initially empty string.
 *
 * This class includes a main method which serves as the entry point of execution.
 * The input string is hardcoded within the main method and the reversed string is printed to the console.
 */
public class ReverseString {
    /**
     * The main method serves as the entry point for the application and demonstrates
     * string reversal by iterating over the characters of a hardcoded string in reverse order.
     * The reversed string is then printed to the console.
     *
     * @param args the command-line arguments passed to the program (not used in this implementation)
     */
    public static void main(String[] args) {
        String str = "Ammar Seippu";
        String revStr = "";
        /**
         * String reversal loop.
         * This loop iterates through the input string (str) in reverse order, starting from the last character
         * and moving towards the first character.
         *
         * @details
         * - Initialization: Starts from the last index of the string (str.length()-1)
         * - Condition: Continues as long as the index is valid (i >= 0)
         * - Iteration: Decrements the index by 1 after each iteration (i--)
         * - Operation: For each character at position i, it appends that character to revStr
         *
         * Time Complexity: O(n²) where n is the length of the input string.
         * This is because String concatenation (revStr += char) creates a new String object in each iteration,
         * which is an O(n) operation. When performed n times, it results in O(n²) complexity.
         *
         * Space Complexity: O(n) where n is the length of the input string.
         * The space required grows linearly with the size of the input string.
         *
         * Note: For better performance with large strings, consider using StringBuilder instead
         * of String concatenation to achieve O(n) time complexity.
         */
        for(int i=str.length()-1; i>=0; i--){
            revStr += str.charAt(i);
        }
        System.out.println(revStr);
    }
}
