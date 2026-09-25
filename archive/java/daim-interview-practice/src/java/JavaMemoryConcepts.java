package java;

/**
 * This class demonstrates Java Memory Management concepts
 * 1. Stack Memory
 * 2. Heap Memory
 * 3. Static Memory (Method Area)
 * 4. Memory Lifecycle
 */
public class JavaMemoryConcepts {

    // Static variable - stored in Method Area (part of Heap in modern JVMs)
    private static int staticVariable = 10;
    
    // Instance variable - stored in Heap
    private int instanceVariable;
    
    public JavaMemoryConcepts(int value) {
        this.instanceVariable = value;
    }
    
    public static void main(String[] args) {
        System.out.println("===== Java Memory Concepts Demonstration =====");
        
        // Demonstrate Stack Memory
        demonstrateStackMemory();
        
        // Demonstrate Heap Memory
        demonstrateHeapMemory();
        
        // Demonstrate Static Memory
        demonstrateStaticMemory();
        
        // Demonstrate Memory Lifecycle
        demonstrateMemoryLifecycle();
    }
    
    /**
     * Stack Memory in Java:
     * 
     * Characteristics:
     * 1. Stores local variables and method call information
     * 2. LIFO (Last-In-First-Out) data structure
     * 3. Memory is automatically allocated/deallocated as methods are called/returned
     * 4. Each thread has its own stack
     * 5. Fixed size (can cause StackOverflowError if exceeded)
     * 6. Faster memory allocation compared to heap
     * 7. Stores primitive data types and references to objects in heap
     */
    private static void demonstrateStackMemory() {
        System.out.println("\n1. Stack Memory:\n");
        
        // Local primitive variables are stored on the stack
        int localInt = 5;
        boolean localBoolean = true;
        char localChar = 'A';
        
        System.out.println("Local variables on stack:");
        System.out.println("localInt: " + localInt);
        System.out.println("localBoolean: " + localBoolean);
        System.out.println("localChar: " + localChar);
        
        // Method calls create new stack frames
        System.out.println("\nCalling a method creates a new stack frame:");
        methodWithLocalVariables(10);
        
        // Demonstrating stack overflow with recursion
        System.out.println("\nRecursion can cause StackOverflowError if too deep:");
        System.out.println("(Not demonstrated to avoid crashing the program)");
        // recursiveMethod(1); // Uncommenting this would eventually cause StackOverflowError
        
        // References are stored on stack, but objects are on heap
        System.out.println("\nObject references are stored on stack, but objects are on heap:");
        Person1 person = new Person1("John", 30);
        System.out.println("person reference is on stack, Person object with name=" + 
                          person.getName() + " and age=" + person.getAge() + " is on heap");
    }
    
    // Helper method to demonstrate stack frames
    private static void methodWithLocalVariables(int parameter) {
        // New stack frame is created with these variables
        int localVar1 = parameter * 2;
        int localVar2 = parameter + 5;
        
        System.out.println("Inside method: parameter=" + parameter + 
                          ", localVar1=" + localVar1 + ", localVar2=" + localVar2);
        
        // When this method returns, its stack frame is removed
    }
    
    // Recursive method to demonstrate stack overflow (commented out to avoid crashes)
    private static void recursiveMethod(int depth) {
        System.out.println("Recursion depth: " + depth);
        recursiveMethod(depth + 1);  // This would eventually cause StackOverflowError
    }
    
    /**
     * Heap Memory in Java:
     * 
     * Characteristics:
     * 1. Stores objects and arrays
     * 2. Shared among all threads
     * 3. Dynamic memory allocation
     * 4. Garbage collected (objects without references are removed)
     * 5. Larger size compared to stack
     * 6. Slower memory allocation compared to stack
     * 7. Divided into generations for garbage collection (Young, Old, Permanent/Metaspace)
     */
    private static void demonstrateHeapMemory() {
        System.out.println("\n2. Heap Memory:\n");
        
        // Objects are created on the heap
        System.out.println("Creating objects on the heap:");
        
        // String objects are stored on the heap (String Pool is also in the heap)
        String str = new String("Hello");  // Explicitly created on heap, not in string pool
        String literal = "World";          // Created in String Pool (still in heap)
        
        System.out.println("String object: " + str);
        System.out.println("String literal: " + literal);
        
        // Arrays are stored on the heap
        int[] array = new int[5];
        array[0] = 1;
        array[1] = 2;
        
        System.out.println("\nArrays are stored on the heap:");
        System.out.println("array[0]: " + array[0] + ", array[1]: " + array[1]);
        
        // Creating multiple objects
        System.out.println("\nCreating multiple objects:");
        Person1[] people = new Person1[3];
        people[0] = new Person1("Alice", 25);
        people[1] = new Person1("Bob", 30);
        people[2] = new Person1("Charlie", 35);
        
        for (Person1 p : people) {
            System.out.println("Person: " + p.getName() + ", " + p.getAge());
        }
        
        // Demonstrating garbage collection
        System.out.println("\nDemonstrating garbage collection:");
        System.out.println("When objects no longer have references, they become eligible for garbage collection");
        
        Person1 tempPerson = new Person1("Temporary", 20);
        System.out.println("Created tempPerson: " + tempPerson.getName());
        
        tempPerson = null;  // Object becomes eligible for garbage collection
        System.out.println("Set tempPerson to null, object is now eligible for garbage collection");
        
        // Request garbage collection (though JVM may not honor this immediately)
        System.gc();
        System.out.println("Requested garbage collection");
    }
    
    /**
     * Static Memory (Method Area) in Java:
     * 
     * Characteristics:
     * 1. Part of Heap memory in modern JVMs (previously separate)
     * 2. Stores class structures, static variables, method code, etc.
     * 3. Created when classes are loaded
     * 4. Shared across all instances of a class
     * 5. Exists for the lifetime of the class (until class is unloaded)
     */
    private static void demonstrateStaticMemory() {
        System.out.println("\n3. Static Memory (Method Area):\n");
        
        // Static variables are shared across all instances
        System.out.println("Static variables are shared across all instances:");
        System.out.println("Initial staticVariable: " + staticVariable);
        
        // Create instances
        JavaMemoryConcepts instance1 = new JavaMemoryConcepts(100);
        JavaMemoryConcepts instance2 = new JavaMemoryConcepts(200);
        
        // Change static variable through one instance
        staticVariable = 20;
        
        // Both instances see the change
        System.out.println("After changing staticVariable to 20:");
        System.out.println("staticVariable via class: " + JavaMemoryConcepts.staticVariable);
        System.out.println("staticVariable via instance1: " + staticVariable);
        System.out.println("staticVariable via instance2: " + staticVariable);
        
        // Instance variables are separate for each instance
        System.out.println("\nInstance variables are separate for each instance:");
        System.out.println("instance1.instanceVariable: " + instance1.instanceVariable);
        System.out.println("instance2.instanceVariable: " + instance2.instanceVariable);
        
        // Static methods and blocks
        System.out.println("\nStatic methods and blocks are stored in Method Area:");
        StaticExample.printStaticMessage();
        
        // Class metadata
        System.out.println("\nClass metadata is stored in Method Area:");
        System.out.println("JavaMemoryConcepts.class: " + JavaMemoryConcepts.class);
    }
    
    /**
     * Memory Lifecycle in Java:
     * 
     * 1. Allocation: Memory is allocated when objects are created
     * 2. Usage: Memory is used during the lifetime of objects
     * 3. Deallocation: Memory is freed when objects are no longer referenced
     * 4. Garbage Collection: Process of automatically reclaiming memory
     */
    private static void demonstrateMemoryLifecycle() {
        System.out.println("\n4. Memory Lifecycle:\n");
        
        System.out.println("1. Allocation:");
        System.out.println("   - Stack: Automatic when methods are called");
        System.out.println("   - Heap: When 'new' operator is used");
        System.out.println("   - Static: When classes are loaded");
        
        System.out.println("\n2. Usage:");
        System.out.println("   - Stack: During method execution");
        System.out.println("   - Heap: While objects have references");
        System.out.println("   - Static: Throughout program execution");
        
        System.out.println("\n3. Deallocation:");
        System.out.println("   - Stack: Automatic when methods return");
        System.out.println("   - Heap: Garbage collection when objects have no references");
        System.out.println("   - Static: When classes are unloaded (rare)");
        
        System.out.println("\n4. Garbage Collection Process:");
        System.out.println("   - Mark: Identify live objects");
        System.out.println("   - Sweep: Remove unmarked objects");
        System.out.println("   - Compact: Reorganize memory (in some GC algorithms)");
        
        // Memory leaks
        System.out.println("\nMemory Leaks:");
        System.out.println("Memory leaks occur when objects are no longer needed but still referenced");
        System.out.println("Common causes:");
        System.out.println("1. Unclosed resources (streams, connections)");
        System.out.println("2. Improper caching");
        System.out.println("3. Inner classes holding references to outer classes");
        System.out.println("4. Thread-local variables");
        
        // OutOfMemoryError
        System.out.println("\nOutOfMemoryError:");
        System.out.println("Occurs when JVM cannot allocate more memory");
        System.out.println("Common causes:");
        System.out.println("1. Memory leaks");
        System.out.println("2. Heap size too small for application needs");
        System.out.println("3. Creating too many objects in a short time");
    }
    
    // Helper class for demonstrating heap memory
    static class Person1 {
        private String name;
        private int age;
        
        public Person1(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        // Finalize method (deprecated in newer Java versions)
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing Person: " + name);
            super.finalize();
        }
    }
    
    // Helper class for demonstrating static memory
    static class StaticExample {
        // Static variable
        private static String staticMessage = "This is a static message";
        
        // Static block - executed when class is loaded
        static {
            System.out.println("StaticExample class is being loaded");
            staticMessage += " (modified in static block)";
        }
        
        // Static method
        public static void printStaticMessage() {
            System.out.println(staticMessage);
        }
    }
}