package FrequentInterviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates different memory areas in Java (Stack, Heap, and Method Area)
 * 
 * 1. Stack: Stores local variables and method call frames
 * 2. Heap: Stores objects and arrays (dynamically allocated memory)
 * 3. Method Area (PermGen/Metaspace): Stores class structures, static variables, and method code
 */
public class JavaMemoryDemo {

    // Static variable - stored in Method Area (PermGen/Metaspace)
    private static int staticCounter = 0;

    // Instance variable - stored in Heap (part of object)
    private int instanceCounter = 0;

    public static void main(String[] args) {
        System.out.println("===== Java Memory Areas Demonstration =====");

        // 1. Stack Memory
        demonstrateStackMemory();

        // 2. Heap Memory
        demonstrateHeapMemory();

        // 3. Method Area (Static Memory)
        demonstrateMethodArea();

        // 4. Memory Leaks
        demonstrateMemoryLeaks();

        // 5. Garbage Collection
        demonstrateGarbageCollection();
    }

    /**
     * Demonstrates Stack memory characteristics
     */
    private static void demonstrateStackMemory() {
        System.out.println("\n1. Stack Memory:\n");

        System.out.println("The Stack stores:");
        System.out.println("- Method call frames");
        System.out.println("- Local variables");
        System.out.println("- Primitive data types (when not part of an object)");
        System.out.println("- References to objects (the actual objects are in the heap)");

        System.out.println("\nStack memory characteristics:");
        System.out.println("- LIFO (Last-In-First-Out) structure");
        System.out.println("- Fast access (automatic memory allocation and deallocation)");
        System.out.println("- Thread-specific (each thread has its own stack)");
        System.out.println("- Limited size (can cause StackOverflowError)");

        // Demonstrating local variables on stack
        System.out.println("\nDemonstrating local variables:");
        int localVar = 42;  // Primitive local variable stored on stack
        System.out.println("Local variable value: " + localVar);

        // Demonstrate method calls and stack frames
        System.out.println("\nDemonstrating method calls (stack frames):");
        methodA();  // This will create a new frame on the stack

        // Demonstrating stack overflow with recursion
        System.out.println("\nStack can overflow with excessive recursion:");
        System.out.println("Calling a recursive method with limits to avoid actual overflow");
        recursiveMethod(0, 3);  // Limiting to 3 levels to avoid actual overflow
    }

    /**
     * Demonstrates Heap memory characteristics
     */
    private static void demonstrateHeapMemory() {
        System.out.println("\n2. Heap Memory:\n");

        System.out.println("The Heap stores:");
        System.out.println("- Objects (including arrays and class instances)");
        System.out.println("- Instance variables (fields of objects)");

        System.out.println("\nHeap memory characteristics:");
        System.out.println("- Shared among all threads");
        System.out.println("- Dynamic memory allocation (using 'new' keyword)");
        System.out.println("- Garbage collected (automatic memory management)");
        System.out.println("- Larger than stack, but slower access");
        System.out.println("- Can cause OutOfMemoryError if exhausted");

        // Creating objects on the heap
        System.out.println("\nCreating objects on the heap:");

        // These object references are on the stack, but the objects themselves are on the heap
        String stringObject = new String("Hello, Heap!");  // String object on heap, reference on stack
        Person person = new Person("John", 30);  // Person object on heap, reference on stack
        int[] array = new int[5];  // Array on heap, reference on stack

        System.out.println("Created string object: " + stringObject);
        System.out.println("Created person object: " + person.getName() + ", " + person.getAge());

        // Modifying instance variables (stored in the heap)
        System.out.println("\nModifying instance variables (stored in the heap):");
        JavaMemoryDemo instance1 = new JavaMemoryDemo();
        JavaMemoryDemo instance2 = new JavaMemoryDemo();

        instance1.instanceCounter = 10;
        instance2.instanceCounter = 20;

        System.out.println("instance1 counter: " + instance1.instanceCounter);
        System.out.println("instance2 counter: " + instance2.instanceCounter);
    }

    /**
     * Demonstrates Method Area (Static Memory) characteristics
     */
    private static void demonstrateMethodArea() {
        System.out.println("\n3. Method Area (Static Memory):\n");

        System.out.println("The Method Area (also called Static Area) stores:");
        System.out.println("- Class structures and metadata");
        System.out.println("- Static variables");
        System.out.println("- Method code");
        System.out.println("- Constant pool");

        System.out.println("\nMethod Area characteristics:");
        System.out.println("- Shared among all threads");
        System.out.println("- Created when classes are loaded");
        System.out.println("- In older JVMs: Called PermGen (Permanent Generation)");
        System.out.println("- In newer JVMs (Java 8+): Called Metaspace");

        // Demonstrating static variables (stored in Method Area)
        System.out.println("\nDemonstrating static variables:");
        System.out.println("Initial static counter: " + staticCounter);

        staticCounter = 100;
        System.out.println("Modified static counter: " + staticCounter);

        // Creating instances doesn't create new copies of static variables
        JavaMemoryDemo instance1 = new JavaMemoryDemo();
        JavaMemoryDemo instance2 = new JavaMemoryDemo();

        staticCounter = 200;

        System.out.println("\nStatic counter after modification: " + staticCounter);
        System.out.println("Static counter via instance1: " + instance1.staticCounter);  // Same value
        System.out.println("Static counter via instance2: " + instance2.staticCounter);  // Same value

        // Demonstrating static methods
        System.out.println("\nStatic methods are also stored in the Method Area");
        System.out.println("The main() and all other static methods in this class are in the Method Area");
    }

    /**
     * Demonstrates potential memory leaks in Java
     */
    private static void demonstrateMemoryLeaks() {
        System.out.println("\n4. Memory Leaks:\n");

        System.out.println("Common causes of memory leaks in Java:");
        System.out.println("1. Unclosed resources (streams, connections, etc.)");
        System.out.println("2. Improper implementation of long-lived objects that hold references");
        System.out.println("3. Adding objects to collections and never removing them");
        System.out.println("4. Incorrect implementation of object cache");
        System.out.println("5. Inner classes holding implicit references to outer class");

        // Demonstrating a potential memory leak with a static collection
        System.out.println("\nSimulating a potential memory leak with a static collection:");
        for (int i = 0; i < 5; i++) {
            MemoryLeakSimulator.addToCache("Key-" + i, "Large Value-" + i);
        }

        System.out.println("Added 5 entries to a static cache that's never cleared");
        System.out.println("In a real application with many entries, this could cause memory issues");

        System.out.println("\nMemory leak prevention:");
        System.out.println("1. Always close resources in finally blocks or try-with-resources");
        System.out.println("2. Use WeakHashMap for caches when appropriate");
        System.out.println("3. Be careful with static collections and fields");
        System.out.println("4. Remove listeners and callbacks when no longer needed");
        System.out.println("5. Avoid circular references when possible");
    }

    /**
     * Demonstrates Garbage Collection concepts
     */
    private static void demonstrateGarbageCollection() {
        System.out.println("\n5. Garbage Collection:\n");

        System.out.println("Garbage Collection basics:");
        System.out.println("- Automatic process to reclaim unused memory in the heap");
        System.out.println("- Frees memory from objects that are no longer reachable");
        System.out.println("- Uses 'Mark and Sweep' algorithm in most implementations");
        System.out.println("- Not directly controllable (though can be suggested with System.gc())");

        // Creating objects that will become eligible for GC
        System.out.println("\nCreating objects and making them eligible for GC:");

        for (int i = 0; i < 1000; i++) {
            // These objects will be eligible for GC after the loop
            new Person("Temporary" + i, i);
        }

        Person person = new Person("John", 30);
        System.out.println("Created person: " + person.getName());

        // Making the object eligible for garbage collection
        person = null;  // Removing the reference
        System.out.println("Set person reference to null, object is now eligible for GC");

        // Demonstrating finalize method (deprecated in recent Java versions)
        GarbageCollectionDemo gcDemo = new GarbageCollectionDemo("Demo Object");
        System.out.println("Created GC demo object: " + gcDemo.getName());

        gcDemo = null;  // Make object eligible for GC

        // Suggesting garbage collection (no guarantee it will run immediately)
        System.out.println("\nSuggesting garbage collection (may or may not run immediately):");
        System.gc();
        Runtime.getRuntime().runFinalization();

        System.out.println("\nGarbage Collection characteristics:");
        System.out.println("- Non-deterministic (can't predict when it will run)");
        System.out.println("- Can pause application execution ('Stop the World' events)");
        System.out.println("- Different GC algorithms available (Serial, Parallel, CMS, G1, ZGC, etc.)");
        System.out.println("- Can be tuned using JVM parameters");
    }

    // Helper methods for stack demonstration
    private static void methodA() {
        int a = 1;  // Local variable on the stack
        System.out.println("In methodA, local variable a = " + a);
        methodB();  // Call another method, creating another stack frame
    }

    private static void methodB() {
        int b = 2;  // Local variable on the stack
        System.out.println("In methodB, local variable b = " + b);
        methodC();  // Call another method, creating another stack frame
    }

    private static void methodC() {
        int c = 3;  // Local variable on the stack
        System.out.println("In methodC, local variable c = " + c);
        // When this method completes, its stack frame is removed
    }

    // Recursive method to demonstrate stack frames
    private static void recursiveMethod(int depth, int maxDepth) {
        // Local variables on the stack
        int localVar = depth * 10;

        System.out.println("Recursive call at depth " + depth + ", localVar = " + localVar);

        if (depth < maxDepth) {
            // Recursive call adds another frame to the stack
            recursiveMethod(depth + 1, maxDepth);
        } else {
            System.out.println("Reached maximum depth, returning...");
        }

        // When this method returns, its stack frame is removed
        System.out.println("Exiting call at depth " + depth);
    }
}

/**
 * Helper class to demonstrate memory leak
 */
class MemoryLeakSimulator {
    // Static collection that grows but never shrinks - potential memory leak
    private static final List<Object[]> CACHE = new ArrayList<>();

    public static void addToCache(String key, String value) {
        CACHE.add(new Object[] { key, value });
        System.out.println("Added to cache: " + key);
    }

    // Missing method to remove from cache or clear it
}

/**
 * Helper class to demonstrate garbage collection and finalize
 */
class GarbageCollectionDemo {
    private String name;

    public GarbageCollectionDemo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // finalize method is called before garbage collection (deprecated in newer Java versions)
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Finalize method called for object: " + name);
            // Cleanup operations would go here
        } finally {
            super.finalize();
        }
    }
}
