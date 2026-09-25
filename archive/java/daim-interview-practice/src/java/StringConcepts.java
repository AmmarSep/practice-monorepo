package java;

/**
 * This class demonstrates various String-related concepts in Java
 * 1. String Immutability
 * 2. String Constant Pool
 * 3. String vs StringBuffer vs StringBuilder
 * 4. String comparison (== vs equals)
 */
public class StringConcepts {

    public static void main(String[] args) {
        System.out.println("===== String Concepts Demonstration =====");
        
        // Demonstrate String Immutability
        demonstrateStringImmutability();
        
        // Demonstrate String Constant Pool
        demonstrateStringConstantPool();
        
        // Demonstrate StringBuffer and StringBuilder
        demonstrateStringBufferAndStringBuilder();
        
        // Demonstrate String Comparison
        demonstrateStringComparison();
    }
    
    /**
     * String Immutability: Once a String object is created, it cannot be changed.
     * Any operation that appears to modify a String actually creates a new String object.
     * 
     * Reasons why String is immutable:
     * 1. Security: Strings are used in network connections, database URLs, usernames/passwords
     * 2. Thread Safety: Immutable objects are inherently thread-safe
     * 3. String Pool: Allows JVM to optimize memory by reusing string literals
     * 4. Hashcode Caching: String's hashcode is cached, which improves performance in collections
     */
    private static void demonstrateStringImmutability() {
        System.out.println("\n1. String Immutability:\n");
        
        String original = "Hello";
        System.out.println("Original string: " + original);
        
        // This appears to modify the string but actually creates a new String object
        String modified = original.concat(" World");
        
        System.out.println("After concatenation, original: " + original);
        System.out.println("New string: " + modified);
        
        // Memory addresses (hashcodes) are different
        System.out.println("Original string hashcode: " + System.identityHashCode(original));
        System.out.println("Modified string hashcode: " + System.identityHashCode(modified));
        
        // Other operations that create new strings
        String uppercase = original.toUpperCase();
        String replaced = original.replace('H', 'Y');
        
        System.out.println("Original after other operations: " + original);
        System.out.println("Uppercase: " + uppercase);
        System.out.println("Replaced: " + replaced);
    }
    
    /**
     * String Constant Pool is a special memory area in Java Heap where string literals are stored.
     * When a string literal is created, JVM first checks if it exists in the pool:
     * - If it exists, the reference to the pooled instance is returned
     * - If not, a new String object is created and placed in the pool
     * 
     * This mechanism helps save memory by reusing string literals.
     */
    private static void demonstrateStringConstantPool() {
        System.out.println("\n2. String Constant Pool:\n");
        
        // String literals - stored in String Constant Pool
        String s1 = "Java";
        String s2 = "Java";  // Reuses the same object from the pool
        
        // String objects created with 'new' - stored in heap memory, not in the pool
        String s3 = new String("Java");
        String s4 = new String("Java");  // Creates a new object, not reused
        
        // Comparing references (memory addresses)
        System.out.println("s1 == s2: " + (s1 == s2));  // true - same object from pool
        System.out.println("s1 == s3: " + (s1 == s3));  // false - different objects
        System.out.println("s3 == s4: " + (s3 == s4));  // false - different objects
        
        // Explicitly interning a string - adds it to the pool and returns the pooled reference
        String s5 = s3.intern();
        System.out.println("s1 == s5: " + (s1 == s5));  // true - s5 is now the pooled instance
        
        // Demonstrating memory addresses
        System.out.println("s1 hashcode: " + System.identityHashCode(s1));
        System.out.println("s2 hashcode: " + System.identityHashCode(s2));
        System.out.println("s3 hashcode: " + System.identityHashCode(s3));
        System.out.println("s4 hashcode: " + System.identityHashCode(s4));
        System.out.println("s5 hashcode: " + System.identityHashCode(s5));
    }
    
    /**
     * Comparison of String, StringBuffer, and StringBuilder:
     * 
     * 1. String: Immutable, thread-safe, slower for concatenation operations
     * 2. StringBuffer: Mutable, thread-safe (synchronized methods), medium performance
     * 3. StringBuilder: Mutable, not thread-safe, best performance for single-threaded scenarios
     * 
     * Use String for: Simple string operations, thread safety is required, immutability is desired
     * Use StringBuffer for: Frequent modifications in multi-threaded environments
     * Use StringBuilder for: Frequent modifications in single-threaded environments
     */
    private static void demonstrateStringBufferAndStringBuilder() {
        System.out.println("\n3. String vs StringBuffer vs StringBuilder:\n");
        
        // Performance comparison
        System.out.println("Performance comparison for 100,000 concatenations:");
        
        // Using String concatenation (very slow due to creating many objects)
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += "a";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("String concatenation time: " + (endTime - startTime) + "ms");
        
        // Using StringBuffer (thread-safe, synchronized)
        startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            buffer.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (endTime - startTime) + "ms");
        
        // Using StringBuilder (not thread-safe, faster)
        startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            builder.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (endTime - startTime) + "ms");
        
        // Demonstrating mutability
        System.out.println("\nDemonstrating mutability:");
        
        // StringBuffer operations
        StringBuffer sbuffer = new StringBuffer("Hello");
        System.out.println("Original StringBuffer: " + sbuffer);
        
        sbuffer.append(" World");
        System.out.println("After append: " + sbuffer);
        
        sbuffer.insert(5, " Java");
        System.out.println("After insert: " + sbuffer);
        
        sbuffer.replace(6, 10, "Python");
        System.out.println("After replace: " + sbuffer);
        
        sbuffer.delete(6, 13);
        System.out.println("After delete: " + sbuffer);
        
        sbuffer.reverse();
        System.out.println("After reverse: " + sbuffer);
        
        // StringBuilder has the same methods as StringBuffer
        // The only difference is that StringBuffer is synchronized (thread-safe)
    }
    
    /**
     * String comparison in Java can be done in two ways:
     * 1. Using == operator: Compares object references (memory addresses)
     * 2. Using equals() method: Compares the content of strings
     * 
     * Always use equals() for comparing string content!
     */
    private static void demonstrateStringComparison() {
        System.out.println("\n4. String Comparison (== vs equals):\n");
        
        // String literals from pool
        String s1 = "Hello";
        String s2 = "Hello";
        
        // String objects on heap
        String s3 = new String("Hello");
        String s4 = new String("Hello");
        
        // Using == operator (compares references)
        System.out.println("s1 == s2: " + (s1 == s2));  // true - same object from pool
        System.out.println("s1 == s3: " + (s1 == s3));  // false - different objects
        System.out.println("s3 == s4: " + (s3 == s4));  // false - different objects
        
        // Using equals() method (compares content)
        System.out.println("s1.equals(s2): " + s1.equals(s2));  // true - same content
        System.out.println("s1.equals(s3): " + s1.equals(s3));  // true - same content
        System.out.println("s3.equals(s4): " + s3.equals(s4));  // true - same content
        
        // Case-sensitive comparison
        String s5 = "hello";
        System.out.println("s1.equals(s5): " + s1.equals(s5));  // false - different case
        System.out.println("s1.equalsIgnoreCase(s5): " + s1.equalsIgnoreCase(s5));  // true - ignores case
        
        // Scenario: String concatenation at compile time
        String s6 = "Hello" + " World";  // Concatenated by compiler, stored in pool
        String s7 = "Hello World";  // Directly from pool
        System.out.println("s6 == s7: " + (s6 == s7));  // true - compiler optimization
        
        // Scenario: String concatenation at runtime
        String hello = "Hello";
        String world = " World";
        String s8 = hello + world;  // Concatenated at runtime, not in pool
        System.out.println("s7 == s8: " + (s7 == s8));  // false - different objects
        System.out.println("s7.equals(s8): " + s7.equals(s8));  // true - same content
    }
}