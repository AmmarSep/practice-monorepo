package Intermediate.singleton;

import java.io.Serializable;

/**
 * This class demonstrates different implementations of the Singleton design pattern
 * The Singleton pattern ensures a class has only one instance and provides a global point of access to it.
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demonstration ===\n");

        // Eager initialization
        System.out.println("Testing Eager Initialization Singleton:");
        EagerInitializedSingleton eagerInstance1 = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton eagerInstance2 = EagerInitializedSingleton.getInstance();
        System.out.println("Are instances same? " + (eagerInstance1 == eagerInstance2));
        eagerInstance1.showMessage();

        // Lazy initialization
        System.out.println("\nTesting Lazy Initialization Singleton:");
        LazyInitializedSingleton lazyInstance1 = LazyInitializedSingleton.getInstance();
        LazyInitializedSingleton lazyInstance2 = LazyInitializedSingleton.getInstance();
        System.out.println("Are instances same? " + (lazyInstance1 == lazyInstance2));
        lazyInstance1.showMessage();

        // Thread-safe singleton
        System.out.println("\nTesting Thread-Safe Singleton:");
        ThreadSafeSingleton threadSafeInstance1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton threadSafeInstance2 = ThreadSafeSingleton.getInstance();
        System.out.println("Are instances same? " + (threadSafeInstance1 == threadSafeInstance2));
        threadSafeInstance1.showMessage();

        // Double-checked locking
        System.out.println("\nTesting Double-Checked Locking Singleton:");
        DoubleCheckedSingleton doubleCheckedInstance1 = DoubleCheckedSingleton.getInstance();
        DoubleCheckedSingleton doubleCheckedInstance2 = DoubleCheckedSingleton.getInstance();
        System.out.println("Are instances same? " + (doubleCheckedInstance1 == doubleCheckedInstance2));
        doubleCheckedInstance1.showMessage();

        // Bill Pugh Singleton
        System.out.println("\nTesting Bill Pugh Singleton:");
        BillPughSingleton billPughInstance1 = BillPughSingleton.getInstance();
        BillPughSingleton billPughInstance2 = BillPughSingleton.getInstance();
        System.out.println("Are instances same? " + (billPughInstance1 == billPughInstance2));
        billPughInstance1.showMessage();

        // Enum Singleton
        System.out.println("\nTesting Enum Singleton:");
        EnumSingleton enumInstance1 = EnumSingleton.INSTANCE;
        EnumSingleton enumInstance2 = EnumSingleton.INSTANCE;
        System.out.println("Are instances same? " + (enumInstance1 == enumInstance2));
        enumInstance1.showMessage();

        // Demonstrate multi-threading scenario
        System.out.println("\nTesting Singleton in multi-threaded environment:");
        testMultiThreadedSingleton();
    }

    /**
     * Test if singleton pattern holds in a multi-threaded environment
     */
    private static void testMultiThreadedSingleton() {
        // Create multiple threads that try to get the singleton instance
        Thread thread1 = new Thread(() -> {
            ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
            System.out.println("Thread 1: " + instance.hashCode());
        });

        Thread thread2 = new Thread(() -> {
            ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
            System.out.println("Thread 2: " + instance.hashCode());
        });

        Thread thread3 = new Thread(() -> {
            ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
            System.out.println("Thread 3: " + instance.hashCode());
        });

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Eager Initialization Singleton
 * - Instance is created when the class is loaded
 * - Thread-safe without explicit synchronization
 * - Drawback: Instance is created even if it's not used
 */
class EagerInitializedSingleton {
    // Private static instance created at class loading time
    private static final EagerInitializedSingleton INSTANCE = new EagerInitializedSingleton();

    // Private constructor to prevent instantiation
    private EagerInitializedSingleton() {
        System.out.println("Creating EagerInitializedSingleton instance");
    }

    // Public method to get the instance
    public static EagerInitializedSingleton getInstance() {
        return INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello from EagerInitializedSingleton!");
    }
}

/**
 * Lazy Initialization Singleton
 * - Instance is created only when needed
 * - Not thread-safe
 * - Good for single-threaded environments
 */
class LazyInitializedSingleton {
    // Private static instance, initially null
    private static LazyInitializedSingleton instance;

    // Private constructor to prevent instantiation
    private LazyInitializedSingleton() {
        System.out.println("Creating LazyInitializedSingleton instance");
    }

    // Public method to get the instance
    public static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from LazyInitializedSingleton!");
    }
}

/**
 * Thread-Safe Singleton
 * - Instance is created only when needed
 * - Thread-safe due to synchronized method
 * - Drawback: Performance overhead due to synchronization
 */
class ThreadSafeSingleton {
    // Private static instance, initially null
    private static ThreadSafeSingleton instance;

    // Private constructor to prevent instantiation
    private ThreadSafeSingleton() {
        System.out.println("Creating ThreadSafeSingleton instance");
    }

    // Public synchronized method to get the instance
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from ThreadSafeSingleton!");
    }
}

/**
 * Double-Checked Locking Singleton
 * - Instance is created only when needed
 * - Thread-safe with minimal synchronization
 * - Good for multi-threaded environments
 * - Note: Requires 'volatile' keyword to ensure correct behavior with Java memory model
 */
class DoubleCheckedSingleton {
    // Private static volatile instance to ensure visibility across threads
    private static volatile DoubleCheckedSingleton instance;

    // Private constructor to prevent instantiation
    private DoubleCheckedSingleton() {
        System.out.println("Creating DoubleCheckedSingleton instance");
    }

    // Public method to get the instance with double-checked locking
    public static DoubleCheckedSingleton getInstance() {
        // First check (not synchronized)
        if (instance == null) {
            // Synchronized block for thread safety
            synchronized (DoubleCheckedSingleton.class) {
                // Second check (synchronized)
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from DoubleCheckedSingleton!");
    }
}

/**
 * Bill Pugh Singleton (Static Inner Helper)
 * - Uses a static inner class to hold the instance
 * - Thread-safe without synchronization
 * - Lazy initialization without explicit code
 * - Best approach for most use cases
 */
class BillPughSingleton {
    // Private constructor to prevent instantiation
    private BillPughSingleton() {
        System.out.println("Creating BillPughSingleton instance");
    }

    // Static inner helper class that holds the instance
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    // Public method to get the instance
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello from BillPughSingleton!");
    }
}

/**
 * Enum Singleton
 * - Thread-safe
 * - Serialization handled automatically
 * - Guaranteed to be singleton by JVM
 * - Drawback: Cannot extend any class, less flexible
 */
enum EnumSingleton {
    INSTANCE;

    EnumSingleton() {
        System.out.println("Creating EnumSingleton instance");
    }

    public void showMessage() {
        System.out.println("Hello from EnumSingleton!");
    }
}

/**
 * Serializable Singleton
 * - Handles serialization/deserialization to maintain singleton property
 * - Implements readResolve method to return the singleton instance
 */
class SerializableSingleton implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final SerializableSingleton INSTANCE = new SerializableSingleton();

    private SerializableSingleton() {
        System.out.println("Creating SerializableSingleton instance");
    }

    public static SerializableSingleton getInstance() {
        return INSTANCE;
    }

    // This method is called during deserialization
    // It ensures that deserialization returns the singleton instance
    protected Object readResolve() {
        return INSTANCE; // Return the singleton instance
    }

    public void showMessage() {
        System.out.println("Hello from SerializableSingleton!");
    }
}
