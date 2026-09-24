package Fresher;

/**
 * This class demonstrates two different ways of creating String objects in Java and their significance.
 *
 * In Java, strings can be created in two primary ways:
 *
 * 1. String Literal: Using double quotes ("")
 *    - Example: String name = "SS Ammar";
 *    - Stored in String Pool (a special memory area in the heap)
 *    - Memory efficient as identical literals share the same reference
 *    - Better performance in most cases
 *    - When a string literal is created, JVM first checks if it exists in the string pool
 *      If it exists, no new object is created, and the reference is returned
 *      If it doesn't exist, a new string object is created in the pool
 *
 * 2. String Constructor: Using the new keyword
 *    - Example: String name2 = new String("Seippu");
 *    - Always creates a new object in the heap memory (outside string pool)
 *    - Even if the string content is identical to an existing string
 *    - Less memory efficient for duplicate strings
 *    - The content "Seippu" is actually stored twice - once in the string pool and once in the heap
 *
 * Key Differences:
 * - Memory Usage: String literals are more memory-efficient as they are pooled
 * - Object Creation: new String() always creates a new object regardless of content
 * - String Comparison: When using == operator, literals with same content will be equal, 
 *   but new String() objects will not be equal even with identical content
 * - intern() Method: You can add a string created using new String() to the pool using the intern() method
 *
 * Usage Recommendation:
 * - Prefer string literals unless you specifically need a new String instance
 * - Use string literals for constants and frequently used strings
 * - Use new String() when you need to ensure a distinct object or for security-sensitive contexts
 */
public class VariousWaysOfWritingStringInJava {
    public static void main(String[] args) {
        // String Literal - stored in String Pool
        String name = "SS Ammar";

        // String Constructor - creates a new object in the heap memory
        String name2 = new String("Seippu");

        // Demonstrating basic string operations
        System.out.println("Hello "+name);  // Using string literal
        System.out.println("Hello "+name.toUpperCase());  // Converting to uppercase
        System.out.println("Hello "+name.toLowerCase());  // Converting to lowercase
        System.out.println("Hello "+name2);  // Using string created with constructor

        // Demonstrating the difference in memory references
        String literal1 = "Java";
        String literal2 = "Java";  // This refers to the same object as literal1
        String newString1 = new String("Java");
        String newString2 = new String("Java");  // This is a different object from newString1

        // Using == operator to compare references (memory addresses)
        System.out.println("\nMemory reference comparison using ==:");
        System.out.println("literal1 == literal2: " + (literal1 == literal2));  // true - same reference in pool
        System.out.println("newString1 == newString2: " + (newString1 == newString2));  // false - different objects
        System.out.println("literal1 == newString1: " + (literal1 == newString1));  // false - different objects

        // Using equals() to compare content
        System.out.println("\nContent comparison using equals():");
        System.out.println("literal1.equals(literal2): " + literal1.equals(literal2));  // true
        System.out.println("newString1.equals(newString2): " + newString1.equals(newString2));  // true
        System.out.println("literal1.equals(newString1): " + literal1.equals(newString1));  // true

        // Demonstrating intern() method
        String internedString = newString1.intern();  // Returns reference from pool
        System.out.println("\nAfter interning:");
        System.out.println("internedString == literal1: " + (internedString == literal1));  // true

        // Memory efficiency demonstration
        System.out.println("\nMemory efficiency:");
        long startTime = System.nanoTime();

        // Creating many strings with the same content using literals
        String[] literalArray = new String[1000];
        for (int i = 0; i < 1000; i++) {
            literalArray[i] = "CommonString";  // Reuses the same object from pool
        }

        long literalTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();

        // Creating many strings with the same content using new String()
        String[] newStringArray = new String[1000];
        for (int i = 0; i < 1000; i++) {
            newStringArray[i] = new String("CommonString");  // Creates 1000 different objects
        }

        long newStringTime = System.nanoTime() - startTime;

        System.out.println("Time to create 1000 literals: " + literalTime + " ns");
        System.out.println("Time to create 1000 new String objects: " + newStringTime + " ns");
        System.out.println("Ratio (new/literal): " + (double)newStringTime/literalTime);
            }
    }


    //## Memory Usage Comparison
//### String Literal () `String name = "SS Ammar"`
//- **Memory Location**: String Pool (special area in heap memory)
//- **Memory Efficiency**: High - if the same string literal is used elsewhere, it reuses the same object
//- **Object Count**: Creates only one object in the string pool
//
//### String Constructor () `String name2 = new String("Seippu")`
//- **Memory Location**: Regular heap memory + String Pool
//- **Memory Efficiency**: Lower - always creates a new object regardless of duplicates
//- **Object Count**: Actually creates **two** objects:
//    1. One in the string pool for the literal `"Seippu"`
//    2. One in the heap memory for the `new String()` object
//
//## Why String Literals Are More Efficient
//1. **String Pool Reuse**: If you create multiple string literals with the same content, they all reference the same object in memory
//2. **Single Object Creation**: Only one object is created per unique string value
//3. **Automatic Optimization**: The JVM automatically manages string pooling for literals
