package Fresher;

/**
 * This class demonstrates various string manipulation techniques in Java
 */
public class StringManipulation {

    public static void main(String[] args) {
        String sampleText = "Hello, World! Welcome to Java Programming.";

        System.out.println("=== String Manipulation Examples ===\n");
        System.out.println("Original text: " + sampleText);

        // Basic string operations
        System.out.println("\n1. Basic String Operations:");
        System.out.println("Length: " + sampleText.length());
        System.out.println("Uppercase: " + sampleText.toUpperCase());
        System.out.println("Lowercase: " + sampleText.toLowerCase());

        // Character extraction
        System.out.println("\n2. Character Extraction:");
        System.out.println("First character: " + sampleText.charAt(0));
        System.out.println("Last character: " + sampleText.charAt(sampleText.length() - 1));
        System.out.println("Characters at index 7-12: " + sampleText.substring(7, 12));

        // String searching
        System.out.println("\n3. String Searching:");
        System.out.println("Contains 'Java': " + sampleText.contains("Java"));
        System.out.println("Index of 'World': " + sampleText.indexOf("World"));
        System.out.println("Last occurrence of 'o': " + sampleText.lastIndexOf("o"));
        System.out.println("Starts with 'Hello': " + sampleText.startsWith("Hello"));
        System.out.println("Ends with 'Programming.': " + sampleText.endsWith("Programming."));

        // String modification
        System.out.println("\n4. String Modification:");
        System.out.println("Replace 'Java' with 'Python': " + sampleText.replace("Java", "Python"));
        System.out.println("Replace all 'o' with 'O': " + sampleText.replace('o', 'O'));

        // String splitting
        System.out.println("\n5. String Splitting:");
        String[] words = sampleText.split(" ");
        System.out.println("Words in the text:");
        for (int i = 0; i < words.length; i++) {
            System.out.println("  Word " + (i + 1) + ": " + words[i]);
        }

        // Removing whitespace
        System.out.println("\n6. Whitespace Handling:");
        String textWithSpaces = "   Trim Example   ";
        System.out.println("Original: '" + textWithSpaces + "'");
        System.out.println("After trim: '" + textWithSpaces.trim() + "'");

        // String comparison
        System.out.println("\n7. String Comparison:");
        String str1 = "apple";
        String str2 = "Apple";
        String str3 = "apple";

        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str3: " + str3);
        System.out.println("str1 equals str2: " + str1.equals(str2));
        System.out.println("str1 equals str3: " + str1.equals(str3));
        System.out.println("str1 equalsIgnoreCase str2: " + str1.equalsIgnoreCase(str2));
        System.out.println("str1 compareTo str2: " + str1.compareTo(str2) + " (str1 > str2 because lowercase > uppercase)");

        // String joining
        System.out.println("\n8. String Joining:");
        String[] fruits = {"Apple", "Banana", "Orange", "Mango"};
        String joinedString = String.join(", ", fruits);
        System.out.println("Joined string: " + joinedString);

        // String building with StringBuilder
        System.out.println("\n9. String Building with StringBuilder:");
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        sb.append("!");
        System.out.println("Built string: " + sb.toString());
        System.out.println("Reverse: " + sb.reverse().toString());

        // Performance comparison
        System.out.println("\n10. Performance Comparison:");
        int iterations = 100000;

        // Using String concatenation
        long startTime = System.nanoTime();
        String result1 = "";
        for (int i = 0; i < iterations; i++) {
            result1 += "a";
        }
        long stringTime = System.nanoTime() - startTime;

        // Using StringBuilder
        startTime = System.nanoTime();
        StringBuilder result2 = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            result2.append("a");
        }
        long sbTime = System.nanoTime() - startTime;

        System.out.println("Time to concatenate " + iterations + " characters:");
        System.out.println("  Using String: " + stringTime + " ns");
        System.out.println("  Using StringBuilder: " + sbTime + " ns");
        System.out.println("  StringBuilder is faster by a factor of: " + (double)stringTime/sbTime);
    }
}
