package java;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class demonstrates various Java programming problems and their solutions
 * 1. Using streams to filter even numbers
 * 2. Reverse string
 * 3. Fibonacci series
 * 4. Factorial
 * 5. Prime or not
 * 6. Palindrome or not
 * 7. Odd or even
 * 8. Find duplicate char in string
 * 9. Count duplicate char in string
 * 10. Count number of words in string
 */
public class JavaPrograms1 {

    public static void main(String[] args) {
        System.out.println("===== Java Programs Demonstration (Part 1) =====");

        // 1. Using streams to filter even numbers
        filterEvenNumbers();

        // 2. Reverse string
        reverseString();

        // 3. Fibonacci series
        fibonacciSeries();

        // 4. Factorial
        factorial();

        // 5. Prime or not
        checkPrime();

        // 6. Palindrome or not
        checkPalindrome();

        // 7. Odd or even
        checkOddEven();

        // 8. Find duplicate char in string
        findDuplicateChars();

        // 9. Count duplicate char in string
        countDuplicateChars();

        // 10. Count number of words in string
        countWords();
    }

    /**
     * 1. Using streams to filter even numbers
     * 
     * This demonstrates how to use Java 8 streams to filter even numbers from a list
     */
    private static void filterEvenNumbers() {
        System.out.println("\n1. Using Streams to Filter Even Numbers:\n");

        // Create a list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Original list: " + numbers);

        // Traditional approach (for loop)
        List<Integer> evenNumbersTraditional = new ArrayList<>();
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                evenNumbersTraditional.add(num);
            }
        }
        System.out.println("Even numbers (traditional approach): " + evenNumbersTraditional);

        // Using streams
        List<Integer> evenNumbersStream = numbers.stream()
                                                .filter(num -> num % 2 == 0)  // Filter even numbers
                                                .collect(Collectors.toList()); // Collect to list
        System.out.println("Even numbers (stream approach): " + evenNumbersStream);

        // Using streams with method reference
        List<Integer> evenNumbersMethodRef = numbers.stream()
                                                  .filter(JavaPrograms1::isEven)  // Using method reference
                                                  .collect(Collectors.toList());
        System.out.println("Even numbers (method reference): " + evenNumbersMethodRef);

        // Additional stream operations
        int sumOfEvenNumbers = numbers.stream()
                                     .filter(num -> num % 2 == 0)
                                     .mapToInt(Integer::intValue)
                                     .sum();
        System.out.println("Sum of even numbers: " + sumOfEvenNumbers);

        // Generate even numbers using stream
        List<Integer> generatedEvenNumbers = IntStream.rangeClosed(1, 10)
                                                    .filter(num -> num % 2 == 0)
                                                    .boxed()
                                                    .collect(Collectors.toList());
        System.out.println("Generated even numbers from 1 to 10: " + generatedEvenNumbers);
    }

    // Helper method for even number check
    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * 2. Reverse string
     * 
     * This demonstrates different ways to reverse a string in Java
     */
    private static void reverseString() {
        System.out.println("\n2. Reverse String:\n");

        String original = "Hello World";
        System.out.println("Original string: " + original);

        // Method 1: Using StringBuilder/StringBuffer
        String reversed1 = new StringBuilder(original).reverse().toString();
        System.out.println("Reversed (StringBuilder): " + reversed1);

        // Method 2: Using character array
        char[] charArray = original.toCharArray();
        for (int i = 0, j = charArray.length - 1; i < j; i++, j--) {
            // Swap characters
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
        }
        String reversed2 = new String(charArray);
        System.out.println("Reversed (char array): " + reversed2);

        // Method 3: Using recursion
        String reversed3 = reverseRecursively(original);
        System.out.println("Reversed (recursion): " + reversed3);

        // Method 4: Using Java 8 streams
        String reversed4 = original.chars()
                                 .mapToObj(c -> (char) c)
                                 .collect(StringBuilder::new, 
                                          (sb, c) -> sb.insert(0, c),
                                          StringBuilder::append)
                                 .toString();
        System.out.println("Reversed (streams): " + reversed4);

        // Method 5: Manual character-by-character
        StringBuilder reversed5 = new StringBuilder();
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed5.append(original.charAt(i));
        }
        System.out.println("Reversed (manual): " + reversed5.toString());
    }

    // Helper method for recursive string reversal
    private static String reverseRecursively(String str) {
        // Base case: empty string or single character
        if (str.length() <= 1) {
            return str;
        }
        // Recursive case: reverse all characters except the first, then add the first character at the end
        return reverseRecursively(str.substring(1)) + str.charAt(0);
    }

    /**
     * 3. Fibonacci series
     * 
     * This demonstrates different ways to generate Fibonacci series
     * Fibonacci series: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     */
    private static void fibonacciSeries() {
        System.out.println("\n3. Fibonacci Series:\n");

        int n = 10; // Number of Fibonacci numbers to generate
        System.out.println("Generating first " + n + " Fibonacci numbers:");

        // Method 1: Using iteration
        System.out.print("Fibonacci (iterative): ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciIterative(i) + " ");
        }
        System.out.println();

        // Method 2: Using recursion
        System.out.print("Fibonacci (recursive): ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }
        System.out.println();

        // Method 3: Using dynamic programming (memoization)
        System.out.print("Fibonacci (dynamic programming): ");
        Map<Integer, Long> memo = new HashMap<>();
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciMemoization(i, memo) + " ");
        }
        System.out.println();

        // Method 4: Using Java 8 streams
        System.out.print("Fibonacci (streams): ");
        Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
              .limit(n)
              .map(f -> f[0])
              .forEach(num -> System.out.print(num + " "));
        System.out.println();
    }

    // Helper method for iterative Fibonacci
    private static long fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        long fib = 0;
        long prev1 = 1;
        long prev2 = 0;

        for (int i = 2; i <= n; i++) {
            fib = prev1 + prev2;
            prev2 = prev1;
            prev1 = fib;
        }

        return fib;
    }

    // Helper method for recursive Fibonacci (inefficient for large n)
    private static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Helper method for Fibonacci using memoization
    private static long fibonacciMemoization(int n, Map<Integer, Long> memo) {
        if (n <= 1) {
            return n;
        }

        // Check if already calculated
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Calculate and store
        long fib = fibonacciMemoization(n - 1, memo) + fibonacciMemoization(n - 2, memo);
        memo.put(n, fib);

        return fib;
    }

    /**
     * 4. Factorial
     * 
     * This demonstrates different ways to calculate factorial
     * Factorial of n (n!): 1 * 2 * 3 * ... * n
     */
    private static void factorial() {
        System.out.println("\n4. Factorial:\n");

        int n = 5;
        System.out.println("Calculating factorial of " + n + ":");

        // Method 1: Using iteration
        long factorialIterative = 1;
        for (int i = 1; i <= n; i++) {
            factorialIterative *= i;
        }
        System.out.println("Factorial (iterative): " + factorialIterative);

        // Method 2: Using recursion
        long factorialRecursive = factorialRecursive(n);
        System.out.println("Factorial (recursive): " + factorialRecursive);

        // Method 3: Using Java 8 streams
        long factorialStream = IntStream.rangeClosed(1, n)
                                      .reduce(1, (a, b) -> a * b);
        System.out.println("Factorial (streams): " + factorialStream);

        // Method 4: Using BigInteger for large factorials
        java.math.BigInteger factorialBig = java.math.BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorialBig = factorialBig.multiply(java.math.BigInteger.valueOf(i));
        }
        System.out.println("Factorial (BigInteger): " + factorialBig);

        // Calculating factorial of a larger number
        int largeN = 20;
        java.math.BigInteger largeFact = java.math.BigInteger.ONE;
        for (int i = 1; i <= largeN; i++) {
            largeFact = largeFact.multiply(java.math.BigInteger.valueOf(i));
        }
        System.out.println("Factorial of " + largeN + ": " + largeFact);
    }

    // Helper method for recursive factorial
    private static long factorialRecursive(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    /**
     * 5. Prime or not
     * 
     * This demonstrates different ways to check if a number is prime
     * Prime number: A number greater than 1 that is only divisible by 1 and itself
     */
    private static void checkPrime() {
        System.out.println("\n5. Prime or Not:\n");

        int[] numbersToCheck = {2, 3, 4, 5, 7, 10, 11, 13, 17, 20};

        System.out.println("Checking if numbers are prime:");

        // Method 1: Basic approach
        for (int num : numbersToCheck) {
            System.out.println(num + " is " + (isPrimeBasic(num) ? "prime" : "not prime") + " (basic approach)");
        }

        // Method 2: Optimized approach
        System.out.println("\nOptimized approach:");
        for (int num : numbersToCheck) {
            System.out.println(num + " is " + (isPrimeOptimized(num) ? "prime" : "not prime"));
        }

        // Method 3: Using Java 8 streams
        System.out.println("\nUsing streams:");
        for (int num : numbersToCheck) {
            boolean isPrime = num > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(num))
                                                .noneMatch(i -> num % i == 0);
            System.out.println(num + " is " + (isPrime ? "prime" : "not prime"));
        }

        // Generate prime numbers up to 50
        System.out.println("\nPrime numbers up to 50:");
        List<Integer> primes = IntStream.rangeClosed(2, 50)
                                      .filter(JavaPrograms1::isPrimeOptimized)
                                      .boxed()
                                      .collect(Collectors.toList());
        System.out.println(primes);
    }

    // Helper method for basic prime check
    private static boolean isPrimeBasic(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Helper method for optimized prime check
    private static boolean isPrimeOptimized(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        // Check divisibility by numbers of form 6k±1 up to sqrt(n)
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 6. Palindrome or not
     * 
     * This demonstrates different ways to check if a string is a palindrome
     * Palindrome: A word, phrase, or sequence that reads the same backward as forward
     */
    private static void checkPalindrome() {
        System.out.println("\n6. Palindrome or Not:\n");

        String[] stringsToCheck = {"radar", "hello", "level", "Java", "madam", "racecar", "A man a plan a canal Panama"};

        System.out.println("Checking if strings are palindromes:");

        // Method 1: Using StringBuilder
        for (String str : stringsToCheck) {
            boolean isPalindrome = str.replaceAll("\\s+", "").equalsIgnoreCase(
                    new StringBuilder(str.replaceAll("\\s+", "")).reverse().toString());
            System.out.println("\"" + str + "\" is " + (isPalindrome ? "a palindrome" : "not a palindrome") + 
                              " (StringBuilder approach)");
        }

        // Method 2: Character by character comparison
        System.out.println("\nCharacter by character comparison:");
        for (String str : stringsToCheck) {
            System.out.println("\"" + str + "\" is " + 
                              (isPalindromeManual(str) ? "a palindrome" : "not a palindrome"));
        }

        // Method 3: Using Java 8 streams
        System.out.println("\nUsing streams:");
        for (String str : stringsToCheck) {
            String processed = str.replaceAll("\\s+", "").toLowerCase();
            boolean isPalindrome = IntStream.range(0, processed.length() / 2)
                                          .allMatch(i -> processed.charAt(i) == 
                                                        processed.charAt(processed.length() - i - 1));
            System.out.println("\"" + str + "\" is " + (isPalindrome ? "a palindrome" : "not a palindrome"));
        }

        // Check if a number is a palindrome
        System.out.println("\nChecking if numbers are palindromes:");
        int[] numbersToCheck = {121, 123, 1221, 12321, 12345};

        for (int num : numbersToCheck) {
            System.out.println(num + " is " + (isPalindromeNumber(num) ? "a palindrome" : "not a palindrome"));
        }
    }

    // Helper method for manual palindrome check
    private static boolean isPalindromeManual(String str) {
        // Remove spaces and convert to lowercase
        String processed = str.replaceAll("\\s+", "").toLowerCase();

        int left = 0;
        int right = processed.length() - 1;

        while (left < right) {
            if (processed.charAt(left) != processed.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // Helper method to check if a number is a palindrome
    private static boolean isPalindromeNumber(int num) {
        // Convert to string and check
        String numStr = String.valueOf(num);
        return numStr.equals(new StringBuilder(numStr).reverse().toString());

        // Alternative approach without string conversion
        /*
        int original = num;
        int reversed = 0;

        while (num > 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }

        return original == reversed;
        */
    }

    /**
     * 7. Odd or even
     * 
     * This demonstrates different ways to check if a number is odd or even
     */
    private static void checkOddEven() {
        System.out.println("\n7. Odd or Even:\n");

        int[] numbersToCheck = {0, 1, 2, 3, 4, 5, 10, 15, 20, 25, -1, -2};

        System.out.println("Checking if numbers are odd or even:");

        // Method 1: Using modulo operator
        for (int num : numbersToCheck) {
            String result = (num % 2 == 0) ? "even" : "odd";
            System.out.println(num + " is " + result + " (modulo approach)");
        }

        // Method 2: Using bitwise AND operator
        System.out.println("\nUsing bitwise AND operator:");
        for (int num : numbersToCheck) {
            // If the last bit is 0, the number is even; if it's 1, the number is odd
            String result = ((num & 1) == 0) ? "even" : "odd";
            System.out.println(num + " is " + result);
        }

        // Method 3: Using Java 8 streams to filter odd and even numbers
        System.out.println("\nFiltering odd and even numbers using streams:");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = numbers.stream()
                                         .filter(n -> n % 2 == 0)
                                         .collect(Collectors.toList());

        List<Integer> oddNumbers = numbers.stream()
                                        .filter(n -> n % 2 != 0)
                                        .collect(Collectors.toList());

        System.out.println("Original numbers: " + numbers);
        System.out.println("Even numbers: " + evenNumbers);
        System.out.println("Odd numbers: " + oddNumbers);
    }

    /**
     * 8. Find duplicate char in string
     * 
     * This demonstrates different ways to find duplicate characters in a string
     */
    private static void findDuplicateChars() {
        System.out.println("\n8. Find Duplicate Characters in String:\n");

        String[] stringsToCheck = {"programming", "hello", "java", "mississippi", "aabbc"};

        System.out.println("Finding duplicate characters in strings:");

        // Method 1: Using HashMap
        for (String str : stringsToCheck) {
            System.out.println("\nDuplicate characters in \"" + str + "\" (HashMap approach):");

            Map<Character, Integer> charCountMap = new HashMap<>();

            // Count occurrences of each character
            for (char c : str.toCharArray()) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }

            // Print characters with count > 1
            for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
                if (entry.getValue() > 1) {
                    System.out.println("'" + entry.getKey() + "' appears " + entry.getValue() + " times");
                }
            }
        }

        // Method 2: Using Java 8 streams
        System.out.println("\nUsing Java 8 streams:");

        for (String str : stringsToCheck) {
            System.out.println("\nDuplicate characters in \"" + str + "\":");

            // Group characters by their count and filter those with count > 1
            Map<Character, Long> charCountMap = str.chars()
                                                 .mapToObj(c -> (char) c)
                                                 .collect(Collectors.groupingBy(
                                                     Function.identity(), Collectors.counting()));

            charCountMap.entrySet().stream()
                       .filter(entry -> entry.getValue() > 1)
                       .forEach(entry -> System.out.println("'" + entry.getKey() + 
                                                          "' appears " + entry.getValue() + " times"));
        }

        // Method 3: Using Set to find duplicates
        System.out.println("\nUsing Set to find duplicates:");

        for (String str : stringsToCheck) {
            System.out.println("\nDuplicate characters in \"" + str + "\":");

            Set<Character> seenChars = new HashSet<>();
            Set<Character> duplicateChars = new HashSet<>();

            for (char c : str.toCharArray()) {
                if (!seenChars.add(c)) {  // If character is already in the set, add() returns false
                    duplicateChars.add(c);
                }
            }

            if (duplicateChars.isEmpty()) {
                System.out.println("No duplicate characters found");
            } else {
                System.out.println("Duplicate characters: " + duplicateChars);
            }
        }
    }

    /**
     * 9. Count duplicate char in string
     * 
     * This demonstrates different ways to count occurrences of each character in a string
     */
    private static void countDuplicateChars() {
        System.out.println("\n9. Count Duplicate Characters in String:\n");

        String[] stringsToCheck = {"programming", "hello", "java", "mississippi"};

        System.out.println("Counting character occurrences in strings:");

        // Method 1: Using HashMap
        for (String str : stringsToCheck) {
            System.out.println("\nCharacter count in \"" + str + "\" (HashMap approach):");

            Map<Character, Integer> charCountMap = new HashMap<>();

            // Count occurrences of each character
            for (char c : str.toCharArray()) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }

            // Print all character counts
            for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
                System.out.println("'" + entry.getKey() + "': " + entry.getValue());
            }
        }

        // Method 2: Using Java 8 streams
        System.out.println("\nUsing Java 8 streams:");

        for (String str : stringsToCheck) {
            System.out.println("\nCharacter count in \"" + str + "\":");

            // Group characters by their count
            Map<Character, Long> charCountMap = str.chars()
                                                 .mapToObj(c -> (char) c)
                                                 .collect(Collectors.groupingBy(
                                                     Function.identity(), Collectors.counting()));

            charCountMap.forEach((key, value) -> 
                System.out.println("'" + key + "': " + value));
        }

        // Method 3: Using array for ASCII characters
        System.out.println("\nUsing array for ASCII characters:");

        for (String str : stringsToCheck) {
            System.out.println("\nCharacter count in \"" + str + "\":");

            // Array to store count of ASCII characters (0-127)
            int[] charCount = new int[128];

            // Count occurrences
            for (char c : str.toCharArray()) {
                charCount[c]++;
            }

            // Print counts for characters that appear in the string
            for (int i = 0; i < charCount.length; i++) {
                if (charCount[i] > 0) {
                    System.out.println("'" + (char) i + "': " + charCount[i]);
                }
            }
        }
    }

    /**
     * 10. Count number of words in string
     * 
     * This demonstrates different ways to count words in a string
     */
    private static void countWords() {
        System.out.println("\n10. Count Number of Words in String:\n");

        String[] stringsToCheck = {
            "Hello world",
            "Java programming is fun",
            "   Multiple   spaces   between   words   ",
            "One",
            "",
            "Hello, world! How are you today?"
        };

        System.out.println("Counting words in strings:");

        // Method 1: Using split
        for (String str : stringsToCheck) {
            System.out.println("\nString: \"" + str + "\"");

            // Split by whitespace and count non-empty words
            String[] words = str.trim().split("\\s+");
            int wordCount = str.trim().isEmpty() ? 0 : words.length;

            System.out.println("Word count (split method): " + wordCount);
        }

        // Method 2: Using StringTokenizer
        System.out.println("\nUsing StringTokenizer:");

        for (String str : stringsToCheck) {
            System.out.println("\nString: \"" + str + "\"");

            StringTokenizer tokenizer = new StringTokenizer(str);
            int wordCount = tokenizer.countTokens();

            System.out.println("Word count (StringTokenizer): " + wordCount);
        }

        // Method 3: Manual counting
        System.out.println("\nManual counting:");

        for (String str : stringsToCheck) {
            System.out.println("\nString: \"" + str + "\"");

            int wordCount = 0;
            boolean isWord = false;

            for (int i = 0; i < str.length(); i++) {
                // If current character is a letter or digit
                if (Character.isLetterOrDigit(str.charAt(i))) {
                    if (!isWord) {
                        wordCount++;
                        isWord = true;
                    }
                } else {
                    isWord = false;
                }
            }

            System.out.println("Word count (manual counting): " + wordCount);
        }

        // Method 4: Using Java 8 streams
        System.out.println("\nUsing Java 8 streams:");

        for (String str : stringsToCheck) {
            System.out.println("\nString: \"" + str + "\"");

            // Count words using pattern and stream
            long wordCount = Pattern.compile("\\s+")
                                  .splitAsStream(str.trim())
                                  .filter(word -> word.length() > 0)
                                  .count();

            System.out.println("Word count (streams): " + wordCount);
        }
    }
}
