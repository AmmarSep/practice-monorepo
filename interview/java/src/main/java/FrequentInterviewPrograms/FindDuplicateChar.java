package FrequentInterviewPrograms;

/**
 * This class finds and prints duplicate characters in a given string.
 */
public class FindDuplicateChar {
    /**
     * This program finds and prints duplicate characters in a given string.
     */
    /**
     * This program finds and prints duplicate characters in a given string.
     */
    public static void main(String[] args) {
        // This is the main method that the Java Virtual Machine (JVM) calls when the program starts.

        String str = new String(" ammar.S.S");
        // This line creates a new String object with the value " ammar.S.S" and assigns it to the variable 'str'.

        int count = 0;
        // This line declares an integer variable 'count' and initializes it to 0. It's used to count the number of duplicate characters.

        char[] chars = str.toCharArray();
        // This line calls the toCharArray() method on the 'str' object, which converts the string into an array of characters. The resulting array is assigned to the 'chars' variable.

        System.out.println("Duplicate characters in the string are : ");
        // This line prints a message to the console.

        for(int i=0; i<str.length();i++)
        // This is the start of a for loop that iterates over each character in the 'str' string.

        {
            for(int j= i+1; j<str.length();j++)
            // This is a nested for loop that starts from the character next to the one currently being examined by the outer loop.

            {
                if(chars[i] == chars[j])
                // This line checks if the current character in the outer loop is the same as the current character in the inner loop.

                {
                    System.out.println(chars[j]);
                    // If the characters are the same, this line prints the duplicate character to the console.

                    count++;
                    // This line increments the 'count' variable by 1, indicating that a duplicate character has been found.

                    break;
                    // This line breaks out of the inner loop as soon as a duplicate character is found.
                }
            }
        }
    }
}
