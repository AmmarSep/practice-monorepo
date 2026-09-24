package JavaSimplePrograms;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates memory management in Java: Heap, Stack, and Static memory
 */
public class JavaMemoryDemo {

    // Static variable - stored in Method Area (PermGen/Metaspace)
    private static int staticVariable = 10;

    // Instance variable - stored in Heap
    private int instanceVariable;

    // Static block - executed when the class is loaded
    static {
        System.out.println("Static block executed. staticVariable = " + staticVariable);
        staticVariable = 20;
    }

    // Constructor
    public JavaMemoryDemo(int value) {
        this.instanceVariable = value;
    }

    public static void main(String[] args) {
        System.out.println("===== Java Memory Management Demonstration =====\n");

        // 1. Different memory areas in Java
        System.out.println("1. Different Memory Areas in Java\n");
        explainMemoryAreas();

        // 2. Stack memory example
        System.out.println("\n2. Stack Memory Example\n");
        demonstrateStackMemory();

        // 3. Heap memory example
        System.out.println("\n3. Heap Memory Example\n");
        demonstrateHeapMemory();

        // 4. Static memory example
        System.out.println("\n4. Static Memory Example\n");
        demonstrateStaticMemory();

        // 5. Memory leaks example
        System.out.println("\n5. Potential Memory Leak Example\n");
        demonstrateMemoryLeakIssue();

        // 6. Garbage collection
        System.out.println("\n6. Garbage Collection Example\n");
        demonstrateGarbageCollection();

        // 7. Out of memory error scenarios
        System.out.println("\n7. Out of Memory Error Scenarios\n");
        explainOutOfMemoryErrors();
    }

    /**
     * Explains different memory areas in Java
     */
    private static void explainMemoryAreas() {
        System.out.println("Java Memory is divided into several areas:");

        System.out.println("\na) Stack Memory:");
        System.out.println("   - Stores local variables and method call stack");
        System.out.println("   - Each thread has its own stack");
        System.out.println("   - LIFO (Last-In-First-Out) data structure");
        System.out.println("   - Variables are removed when they go out of scope");
        System.out.println("   - Fixed size (can cause StackOverflowError)");
        System.out.println("   - Fast access compared to heap");
        System.out.println("   - Primitives and references to objects are stored here");

        System.out.println("\nb) Heap Memory:");
        System.out.println("   - Stores objects and arrays (created using 'new')");
        System.out.println("   - Shared among all threads of the application");
        System.out.println("   - Larger than stack memory");
        System.out.println("   - Subject to garbage collection");
        System.out.println("   - Can grow/shrink during application execution");
        System.out.println("   - Can cause OutOfMemoryError if it's full");

        System.out.println("\nc) Method Area (PermGen/Metaspace):");
        System.out.println("   - Stores class structures, methods, static variables");
        System.out.println("   - Before Java 8: PermGen (Permanent Generation)");
        System.out.println("   - Java 8 and later: Metaspace");
        System.out.println("   - Contains the runtime constant pool");
        System.out.println("   - Shared among all threads");

        System.out.println("\nd) Runtime Constant Pool:");
        System.out.println("   - Part of Method Area");
        System.out.println("   - Contains class constants, method/field references");
        System.out.println("   - String constants are stored in String Pool");

        System.out.println("\ne) Native Method Stack:");
        System.out.println("   - For native methods (methods written in languages other than Java)");
        System.out.println("   - Each thread has its own native method stack");

        System.out.println("\nf) PC Register (Program Counter):");
        System.out.println("   - Contains the address of current executing instruction");
        System.out.println("   - Each thread has its own PC register");
    }

    /**
     * Demonstrates stack memory usage
     */
    private static void demonstrateStackMemory() {
        System.out.println("Stack Memory Example:");

        // Local primitive variables are stored in stack
        int localInt = 5;
        boolean localBoolean = true;
        char localChar = 'A';

        System.out.println("Local variables stored in stack:");
        System.out.println("localInt = " + localInt);
        System.out.println("localBoolean = " + localBoolean);
        System.out.println("localChar = " + localChar);

        // Method call demonstration - each call creates a new frame on the stack
        System.out.println("\nMethod call stack demonstration:");
        firstMethod();

        // Stack memory for reference variables
        System.out.println("\nReference variables in stack:");
        // Reference is stored in stack, the actual String object is in heap
        String referenceInStack = "This reference is stored in stack, object in heap";
        System.out.println(referenceInStack);

        // Demonstrating stack overflow with recursive method
        System.out.println("\nStack overflow can occur with deep recursion (not demonstrated here):");
        // Uncomment to demonstrate StackOverflowError:
        // recursiveMethod(1); // This would eventually cause StackOverflowError
    }

    /**
     * Helper method for stack demonstration
     */
    private static void firstMethod() {
        int firstMethodVar = 10;
        System.out.println("Inside firstMethod() - firstMethodVar = " + firstMethodVar);
        secondMethod();
        System.out.println("Back to firstMethod()");
    }

    /**
     * Helper method for stack demonstration
     */
    private static void secondMethod() {
        int secondMethodVar = 20;
        System.out.println("Inside secondMethod() - secondMethodVar = " + secondMethodVar);
        thirdMethod();
        System.out.println("Back to secondMethod()");
    }

    /**
     * Helper method for stack demonstration
     */
    private static void thirdMethod() {
        int thirdMethodVar = 30;
        System.out.println("Inside thirdMethod() - thirdMethodVar = " + thirdMethodVar);
        System.out.println("Call stack: main() -> demonstrateStackMemory() -> firstMethod() -> secondMethod() -> thirdMethod()");
    }

    /**
     * Recursive method that could cause StackOverflowError
     */
    private static void recursiveMethod(int depth) {
        System.out.println("Recursive depth: " + depth);
        recursiveMethod(depth + 1);
    }

    /**
     * Demonstrates heap memory usage
     */
    private static void demonstrateHeapMemory() {
        System.out.println("Heap Memory Example:");

        // Objects are stored in the heap
        JavaMemoryDemo object1 = new JavaMemoryDemo(100);
        JavaMemoryDemo object2 = new JavaMemoryDemo(200);

        System.out.println("Created objects in heap:");
        System.out.println("object1.instanceVariable = " + object1.instanceVariable);
        System.out.println("object2.instanceVariable = " + object2.instanceVariable);

        // Arrays are stored in the heap
        int[] array = new int[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 10;
        }

        System.out.println("\nArray created in heap:");
        for (int i = 0; i < array.length; i++) {
            System.out.println("array[" + i + "] = " + array[i]);
        }

        // Creating a large object in heap
        System.out.println("\nCreating a large object in heap:");
        int[] largeArray = new int[1000000]; // ~4MB
        System.out.println("Created large array of size: " + largeArray.length + " integers");

        // String objects in heap (but string literals go to string pool)
        System.out.println("\nString objects in heap:");
        String literal = "String literal goes to string pool"; // String pool (special area in heap)
        String object = new String("String object created with 'new' goes to regular heap"); // Regular heap

        System.out.println(literal);
        System.out.println(object);
    }

    /**
     * Demonstrates static memory (Method Area) usage
     */
    private static void demonstrateStaticMemory() {
        System.out.println("Static Memory (Method Area) Example:");

        // Accessing static variable - shared among all instances
        System.out.println("staticVariable = " + staticVariable);

        // Static method is stored in Method Area
        System.out.println("\nStatic methods are stored in Method Area");

        // Modifying static variable affects all instances
        staticVariable = 30;
        System.out.println("After modification: staticVariable = " + staticVariable);

        // Creating objects doesn't create new copies of static variables
        JavaMemoryDemo obj1 = new JavaMemoryDemo(1);
        JavaMemoryDemo obj2 = new JavaMemoryDemo(2);

        System.out.println("\nStatic variable value through different objects:");
        System.out.println("Through obj1: " + staticVariable);
        System.out.println("Through obj2: " + staticVariable);

        // Modifying through one object affects all
        staticVariable = 40;
        System.out.println("\nAfter modification through class:");
        System.out.println("Through obj1: " + staticVariable);
        System.out.println("Through obj2: " + staticVariable);

        // Static classes and constants are also stored in Method Area
        System.out.println("\nStatic constants example:");
        System.out.println("Math.PI = " + Math.PI);
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
    }

    /**
     * Demonstrates potential memory leak issues
     */
    private static void demonstrateMemoryLeakIssue() {
        System.out.println("Potential Memory Leak Scenarios:");

        System.out.println("\n1. Static collections growing unbounded:");
        System.out.println("   - Static collections that keep growing can cause memory leaks");
        System.out.println("   - Objects referenced by static collections won't be garbage collected");

        System.out.println("\n2. Unclosed resources:");
        System.out.println("   - Forgetting to close streams, connections, etc.");
        System.out.println("   - Use try-with-resources to automatically close resources");

        System.out.println("\n3. Improper implementation of equals and hashCode:");
        System.out.println("   - Can lead to duplicate objects in collections like HashSet");

        System.out.println("\n4. Inner class references:");
        System.out.println("   - Non-static inner classes hold implicit reference to outer class");
        System.out.println("   - Can prevent garbage collection of outer class");

        System.out.println("\n5. Thread-local variables:");
        System.out.println("   - ThreadLocal variables not removed can cause memory leaks");

        System.out.println("\n6. Caches without proper size limits:");
        System.out.println("   - Unbounded caches can grow indefinitely");
        System.out.println("   - Use WeakHashMap or time-based expiration strategies");

        // Example of creating a potential memory leak (don't do this in production!)
        class MemoryLeakExample {
            private final List<Object> leakyList = new ArrayList<>();

            public void addToList(Object obj) {
                leakyList.add(obj);
                System.out.println("Added object to list. Current size: " + leakyList.size());
            }
        }

        System.out.println("\nSimulating a potential memory leak:");
        MemoryLeakExample leakDemo = new MemoryLeakExample();
        for (int i = 0; i < 10; i++) {
            // In a real application, this could run indefinitely and cause memory issues
            leakDemo.addToList(new byte[1024]); // Adding 1KB objects to a static list
        }

        System.out.println("\nMitigating memory leaks:");
        System.out.println("1. Use memory profiling tools (JVisualVM, MAT, YourKit)");
        System.out.println("2. Implement proper resource management");
        System.out.println("3. Use weak references when appropriate");
        System.out.println("4. Set collection size limits");
        System.out.println("5. Nullify references when done");
    }

    /**
     * Demonstrates garbage collection in Java
     */
    private static void demonstrateGarbageCollection() {
        System.out.println("Garbage Collection Example:");

        System.out.println("\nCreating objects and then abandoning references:");
        for (int i = 0; i < 5; i++) {
            // These objects become eligible for garbage collection
            // once this loop iteration completes
            new JavaMemoryDemo(i);
            System.out.println("Created temporary object " + (i + 1));
        }

        // Objects with circular references
        System.out.println("\nCreating objects with circular references:");
        CircularReference obj1 = new CircularReference();
        CircularReference obj2 = new CircularReference();
        obj1.setReference(obj2);
        obj2.setReference(obj1);
        System.out.println("Created circular reference between obj1 and obj2");

        // Nullifying references to make objects eligible for garbage collection
        System.out.println("\nNullifying references:");
        obj1 = null;
        obj2 = null;
        System.out.println("Set obj1 and obj2 to null, making objects eligible for garbage collection");

        // Requesting garbage collection (though JVM might not honor immediately)
        System.out.println("\nRequesting garbage collection (JVM may not honor immediately):");
        System.gc();
        Runtime.getRuntime().gc();

        // Demonstrating finalize method (deprecated in newer Java versions)
        System.out.println("\nObjects with finalize method:");
        ObjectWithFinalize obj = new ObjectWithFinalize();
        obj = null;
        System.out.println("Set object with finalize method to null");
        System.gc();

        // Memory statistics
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();

        System.out.println("\nMemory Statistics:");
        System.out.println("Total Memory: " + formatMemorySize(totalMemory));
        System.out.println("Free Memory: " + formatMemorySize(freeMemory));
        System.out.println("Used Memory: " + formatMemorySize(usedMemory));
        System.out.println("Max Memory: " + formatMemorySize(maxMemory));
    }

    /**
     * Helper method to format memory size in human-readable form
     */
    private static String formatMemorySize(long bytes) {
        if (bytes < 1024) {
            return bytes + " bytes";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytes / (1024.0 * 1024));
        } else {
            return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
        }
    }

    /**
     * Explains OutOfMemoryError scenarios
     */
    private static void explainOutOfMemoryErrors() {
        System.out.println("Out of Memory Error (OOM) Scenarios:");

        System.out.println("\n1. java.lang.OutOfMemoryError: Java heap space");
        System.out.println("   - Occurs when the JVM cannot allocate an object due to lack of space in the heap");
        System.out.println("   - Caused by memory leaks or insufficient heap size");
        System.out.println("   - Can be resolved by increasing heap size with -Xmx flag");

        System.out.println("\n2. java.lang.OutOfMemoryError: GC overhead limit exceeded");
        System.out.println("   - Occurs when the garbage collector is running all the time");
        System.out.println("   - JVM spends too much time on garbage collection with little progress");
        System.out.println("   - Usually indicates a memory leak");

        System.out.println("\n3. java.lang.OutOfMemoryError: PermGen space (before Java 8)");
        System.out.println("   - Occurs when PermGen space is full");
        System.out.println("   - Can happen with class loaders creating too many classes");
        System.out.println("   - Can be resolved with -XX:MaxPermSize");

        System.out.println("\n4. java.lang.OutOfMemoryError: Metaspace (Java 8+)");
        System.out.println("   - Similar to PermGen error but for Metaspace");
        System.out.println("   - Can be resolved with -XX:MaxMetaspaceSize");

        System.out.println("\n5. java.lang.OutOfMemoryError: Unable to create new native thread");
        System.out.println("   - Occurs when the JVM can't create any more threads");
        System.out.println("   - Can happen due to OS limits or thread leaks");

        System.out.println("\n6. java.lang.OutOfMemoryError: Direct buffer memory");
        System.out.println("   - Occurs when direct byte buffers use too much memory");
        System.out.println("   - Common in applications using NIO heavily");

        System.out.println("\nExample code that could cause OutOfMemoryError (not executed):");
        System.out.println("List<Object> list = new ArrayList<>();");
        System.out.println("while(true) {");
        System.out.println("    list.add(new byte[1024*1024]); // Add 1MB objects indefinitely");
        System.out.println("}");
    }

    /**
     * Helper class for demonstrating circular references
     */
    static class CircularReference {
        private CircularReference reference;

        public void setReference(CircularReference ref) {
            this.reference = ref;
        }
    }

    /**
     * Helper class for demonstrating finalize method
     * Note: finalize is deprecated in newer Java versions
     */
    static class ObjectWithFinalize {
        @Override
        protected void finalize() {
            System.out.println("Finalize method called - object being garbage collected");
            try {
                super.finalize();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
