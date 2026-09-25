package java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class demonstrates various Multithreading concepts in Java
 * 1. Thread Creation and Lifecycle
 * 2. Runnable vs Callable
 * 3. Synchronization and Thread Safety
 * 4. Executor Framework
 * 5. Thread Communication
 */
public class MultithreadingConcepts {

    public static void main(String[] args) {
        System.out.println("===== Multithreading Concepts Demonstration =====");
        
        // Demonstrate Thread Creation and Lifecycle
        demonstrateThreadCreationAndLifecycle();
        
        // Demonstrate Runnable vs Callable
        demonstrateRunnableVsCallable();
        
        // Demonstrate Synchronization and Thread Safety
        demonstrateSynchronization();
        
        // Demonstrate Executor Framework
        demonstrateExecutorFramework();
        
        // Demonstrate Thread Communication
        demonstrateThreadCommunication();
    }
    
    /**
     * Thread Creation and Lifecycle:
     * 
     * Thread States:
     * 1. NEW: Thread is created but not started
     * 2. RUNNABLE: Thread is executing or ready to execute
     * 3. BLOCKED: Thread is blocked waiting for a monitor lock
     * 4. WAITING: Thread is waiting indefinitely for another thread
     * 5. TIMED_WAITING: Thread is waiting for a specified time
     * 6. TERMINATED: Thread has completed execution
     */
    private static void demonstrateThreadCreationAndLifecycle() {
        System.out.println("\n1. Thread Creation and Lifecycle:\n");
        
        // Method 1: Extending Thread class
        System.out.println("Method 1: Extending Thread class");
        
        // Create a thread by extending Thread class
        Thread thread1 = new MyThread("Thread-1");
        
        // Thread is in NEW state
        System.out.println("State of " + thread1.getName() + " before start(): " + thread1.getState());
        
        // Start the thread - moves to RUNNABLE state
        thread1.start();
        
        try {
            // Wait for thread to complete
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        
        // Thread is now in TERMINATED state
        System.out.println("State of " + thread1.getName() + " after execution: " + thread1.getState());
        
        // Method 2: Implementing Runnable interface
        System.out.println("\nMethod 2: Implementing Runnable interface");
        
        // Create a thread using Runnable implementation
        Runnable runnable = new MyRunnable("Runnable-1");
        Thread thread2 = new Thread(runnable);
        
        // Thread is in NEW state
        System.out.println("State of thread2 before start(): " + thread2.getState());
        
        // Start the thread - moves to RUNNABLE state
        thread2.start();
        
        try {
            // Wait for thread to complete
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        
        // Thread is now in TERMINATED state
        System.out.println("State of thread2 after execution: " + thread2.getState());
        
        // Method 3: Using lambda expression (Java 8)
        System.out.println("\nMethod 3: Using lambda expression");
        
        Thread thread3 = new Thread(() -> {
            System.out.println("Thread running using lambda expression");
            try {
                Thread.sleep(500);  // Simulate some work
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
            System.out.println("Lambda thread completed");
        });
        
        thread3.start();
        
        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
    
    /**
     * Runnable vs Callable:
     * 
     * Runnable:
     * 1. Functional interface with run() method
     * 2. Cannot return a result
     * 3. Cannot throw checked exceptions
     * 4. Used with Thread or ExecutorService
     * 
     * Callable:
     * 1. Functional interface with call() method
     * 2. Can return a result (via Future)
     * 3. Can throw checked exceptions
     * 4. Used with ExecutorService (not directly with Thread)
     */
    private static void demonstrateRunnableVsCallable() {
        System.out.println("\n2. Runnable vs Callable:\n");
        
        // Using Runnable
        System.out.println("Using Runnable:");
        
        Runnable runnable = () -> {
            System.out.println("Runnable task is running");
            try {
                Thread.sleep(1000);  // Simulate some work
            } catch (InterruptedException e) {
                System.out.println("Runnable task interrupted: " + e.getMessage());
            }
            System.out.println("Runnable task completed");
            // Cannot return a value
            // Cannot throw checked exceptions
        };
        
        // Execute Runnable with a Thread
        Thread runnableThread = new Thread(runnable);
        runnableThread.start();
        
        try {
            runnableThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        
        // Using Callable
        System.out.println("\nUsing Callable:");
        
        Callable<Integer> callable = () -> {
            System.out.println("Callable task is running");
            Thread.sleep(1000);  // Can throw checked exceptions
            System.out.println("Callable task completed");
            return 42;  // Can return a value
        };
        
        // Execute Callable with an ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(callable);
        
        try {
            // Get the result (blocks until the task completes)
            Integer result = future.get();
            System.out.println("Callable result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Exception in Callable: " + e.getMessage());
        }
        
        // Shutdown the executor
        executor.shutdown();
        
        // Comparison
        System.out.println("\nComparison between Runnable and Callable:");
        System.out.println("1. Runnable cannot return a result, Callable can");
        System.out.println("2. Runnable cannot throw checked exceptions, Callable can");
        System.out.println("3. Runnable can be used directly with Thread, Callable cannot");
        System.out.println("4. Both can be used with ExecutorService");
    }
    
    /**
     * Synchronization and Thread Safety:
     * 
     * Techniques for thread safety:
     * 1. Synchronized methods
     * 2. Synchronized blocks
     * 3. Locks (ReentrantLock, ReadWriteLock)
     * 4. Atomic variables
     * 5. Volatile keyword
     * 6. Thread-safe collections
     */
    private static void demonstrateSynchronization() {
        System.out.println("\n3. Synchronization and Thread Safety:\n");
        
        // Problem: Race condition without synchronization
        System.out.println("Problem: Race condition without synchronization");
        
        Counter unsafeCounter = new Counter();
        
        // Create multiple threads incrementing the same counter
        Thread[] unsafeThreads = new Thread[5];
        for (int i = 0; i < unsafeThreads.length; i++) {
            unsafeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    unsafeCounter.incrementUnsafe();
                }
            });
            unsafeThreads[i].start();
        }
        
        // Wait for all threads to complete
        for (Thread t : unsafeThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
        
        System.out.println("Unsafe counter value (expected 5000): " + unsafeCounter.getValue());
        
        // Solution 1: Synchronized method
        System.out.println("\nSolution 1: Synchronized method");
        
        Counter syncMethodCounter = new Counter();
        
        Thread[] syncMethodThreads = new Thread[5];
        for (int i = 0; i < syncMethodThreads.length; i++) {
            syncMethodThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    syncMethodCounter.incrementSyncMethod();
                }
            });
            syncMethodThreads[i].start();
        }
        
        for (Thread t : syncMethodThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
        
        System.out.println("Synchronized method counter value (expected 5000): " + syncMethodCounter.getValue());
        
        // Solution 2: Synchronized block
        System.out.println("\nSolution 2: Synchronized block");
        
        Counter syncBlockCounter = new Counter();
        
        Thread[] syncBlockThreads = new Thread[5];
        for (int i = 0; i < syncBlockThreads.length; i++) {
            syncBlockThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    syncBlockCounter.incrementSyncBlock();
                }
            });
            syncBlockThreads[i].start();
        }
        
        for (Thread t : syncBlockThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
        
        System.out.println("Synchronized block counter value (expected 5000): " + syncBlockCounter.getValue());
        
        // Solution 3: Using Lock
        System.out.println("\nSolution 3: Using Lock");
        
        Counter lockCounter = new Counter();
        
        Thread[] lockThreads = new Thread[5];
        for (int i = 0; i < lockThreads.length; i++) {
            lockThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    lockCounter.incrementWithLock();
                }
            });
            lockThreads[i].start();
        }
        
        for (Thread t : lockThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
        
        System.out.println("Lock counter value (expected 5000): " + lockCounter.getValue());
        
        // Solution 4: Using AtomicInteger
        System.out.println("\nSolution 4: Using AtomicInteger");
        
        Counter atomicCounter = new Counter();
        
        Thread[] atomicThreads = new Thread[5];
        for (int i = 0; i < atomicThreads.length; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicCounter.incrementAtomic();
                }
            });
            atomicThreads[i].start();
        }
        
        for (Thread t : atomicThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
        
        System.out.println("Atomic counter value (expected 5000): " + atomicCounter.getAtomicValue());
    }
    
    /**
     * Executor Framework:
     * 
     * Components:
     * 1. Executor: Simple interface for executing tasks
     * 2. ExecutorService: Extended interface with lifecycle methods
     * 3. ScheduledExecutorService: For scheduling tasks
     * 4. ThreadPoolExecutor: Implementation with thread pool
     * 5. Executors: Factory methods for creating executor services
     */
    private static void demonstrateExecutorFramework() {
        System.out.println("\n4. Executor Framework:\n");
        
        // Single Thread Executor
        System.out.println("Single Thread Executor:");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            singleThreadExecutor.execute(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
            });
        }
        
        shutdownExecutor(singleThreadExecutor);
        
        // Fixed Thread Pool
        System.out.println("\nFixed Thread Pool (3 threads):");
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            fixedThreadPool.execute(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);  // Simulate some work
                } catch (InterruptedException e) {
                    System.out.println("Task interrupted: " + e.getMessage());
                }
            });
        }
        
        shutdownExecutor(fixedThreadPool);
        
        // Cached Thread Pool
        System.out.println("\nCached Thread Pool:");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            cachedThreadPool.execute(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
            });
        }
        
        shutdownExecutor(cachedThreadPool);
        
        // Scheduled Executor
        System.out.println("\nScheduled Executor:");
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
        
        // Schedule a task to run after a delay
        scheduledExecutor.schedule(() -> {
            System.out.println("Delayed task executed by " + Thread.currentThread().getName());
        }, 1, TimeUnit.SECONDS);
        
        // Schedule a task to run periodically
        ScheduledFuture<?> scheduledFuture = scheduledExecutor.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task executed by " + Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
        
        // Let the periodic task run a few times
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }
        
        // Cancel the periodic task
        scheduledFuture.cancel(false);
        
        shutdownExecutor(scheduledExecutor);
        
        // CompletableFuture (Java 8+)
        System.out.println("\nCompletableFuture:");
        
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Result from future1";
        });
        
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Result from future2";
        });
        
        // Combine results when both futures complete
        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> {
            return result1 + " and " + result2;
        });
        
        try {
            String result = combinedFuture.get();
            System.out.println("Combined result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Exception in CompletableFuture: " + e.getMessage());
        }
    }
    
    /**
     * Thread Communication:
     * 
     * Mechanisms:
     * 1. wait(), notify(), notifyAll()
     * 2. Condition variables with Lock
     * 3. BlockingQueue
     * 4. CountDownLatch, CyclicBarrier, Semaphore, Phaser
     */
    private static void demonstrateThreadCommunication() {
        System.out.println("\n5. Thread Communication:\n");
        
        // Using wait() and notify()
        System.out.println("Using wait() and notify():");
        
        SharedResource resource = new SharedResource();
        
        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.produce(i);
                try {
                    Thread.sleep(100);  // Simulate some work
                } catch (InterruptedException e) {
                    System.out.println("Producer interrupted: " + e.getMessage());
                }
            }
        });
        
        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                int value = resource.consume();
                try {
                    Thread.sleep(200);  // Simulate some work
                } catch (InterruptedException e) {
                    System.out.println("Consumer interrupted: " + e.getMessage());
                }
            }
        });
        
        producer.start();
        consumer.start();
        
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        
        // Using BlockingQueue
        System.out.println("\nUsing BlockingQueue:");
        
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        
        // Producer thread
        Thread queueProducer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Producing: " + i);
                    queue.put(i);  // Blocks if queue is full
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Queue producer interrupted: " + e.getMessage());
            }
        });
        
        // Consumer thread
        Thread queueConsumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    int value = queue.take();  // Blocks if queue is empty
                    System.out.println("Consuming: " + value);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                System.out.println("Queue consumer interrupted: " + e.getMessage());
            }
        });
        
        queueProducer.start();
        queueConsumer.start();
        
        try {
            queueProducer.join();
            queueConsumer.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        
        // Using CountDownLatch
        System.out.println("\nUsing CountDownLatch:");
        
        CountDownLatch latch = new CountDownLatch(3);
        
        // Create worker threads
        List<Thread> workers = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            final int workerId = i;
            Thread worker = new Thread(() -> {
                try {
                    System.out.println("Worker " + workerId + " starting");
                    Thread.sleep(workerId * 300);  // Simulate different work durations
                    System.out.println("Worker " + workerId + " finished");
                    latch.countDown();  // Decrement the latch count
                } catch (InterruptedException e) {
                    System.out.println("Worker interrupted: " + e.getMessage());
                }
            });
            workers.add(worker);
            worker.start();
        }
        
        // Main thread waits for all workers to finish
        try {
            System.out.println("Main thread waiting for workers to finish");
            latch.await();  // Block until count reaches zero
            System.out.println("All workers have finished, main thread continues");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }
    }
    
    // Helper method to shutdown an executor service
    private static void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            // Wait for tasks to complete
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
    
    // Thread class by extending Thread
    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }
        
        @Override
        public void run() {
            System.out.println(getName() + " is running");
            try {
                Thread.sleep(500);  // Simulate some work
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted: " + e.getMessage());
            }
            System.out.println(getName() + " completed");
        }
    }
    
    // Runnable implementation
    static class MyRunnable implements Runnable {
        private String name;
        
        public MyRunnable(String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            System.out.println(name + " is running");
            try {
                Thread.sleep(500);  // Simulate some work
            } catch (InterruptedException e) {
                System.out.println(name + " interrupted: " + e.getMessage());
            }
            System.out.println(name + " completed");
        }
    }
    
    // Counter class for demonstrating synchronization
    static class Counter {
        private int count = 0;
        private java.util.concurrent.atomic.AtomicInteger atomicCount = new java.util.concurrent.atomic.AtomicInteger(0);
        private Lock lock = new ReentrantLock();
        
        // Unsafe increment - not thread-safe
        public void incrementUnsafe() {
            count++;
        }
        
        // Thread-safe increment using synchronized method
        public synchronized void incrementSyncMethod() {
            count++;
        }
        
        // Thread-safe increment using synchronized block
        public void incrementSyncBlock() {
            synchronized (this) {
                count++;
            }
        }
        
        // Thread-safe increment using Lock
        public void incrementWithLock() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
        
        // Thread-safe increment using AtomicInteger
        public void incrementAtomic() {
            atomicCount.incrementAndGet();
        }
        
        public int getValue() {
            return count;
        }
        
        public int getAtomicValue() {
            return atomicCount.get();
        }
    }
    
    // Shared resource for producer-consumer example
    static class SharedResource {
        private int value;
        private boolean valueSet = false;
        
        // Producer method
        public synchronized void produce(int newValue) {
            while (valueSet) {
                try {
                    // Wait until consumer consumes the value
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            this.value = newValue;
            System.out.println("Produced: " + newValue);
            valueSet = true;
            
            // Notify consumer that value is available
            notify();
        }
        
        // Consumer method
        public synchronized int consume() {
            while (!valueSet) {
                try {
                    // Wait until producer produces a value
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            System.out.println("Consumed: " + value);
            valueSet = false;
            
            // Notify producer that value has been consumed
            notify();
            
            return value;
        }
    }
}