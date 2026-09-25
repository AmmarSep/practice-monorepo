package Fresher;

import java.util.concurrent.*;

/**
 * This class demonstrates various ways to create and manage threads in Java
 * 1. Extending Thread class
 * 2. Implementing Runnable interface
 * 3. Using Lambda expressions (Java 8+)
 * 4. ExecutorService and thread pools
 * 5. Callable and Future for returning results
 * 6. Thread synchronization
 */
public class ThreadingDemo {

    public static void main(String[] args) {
        System.out.println("=== Java Threading Demo ===\n");

        // 1. Creating threads by extending Thread class
        System.out.println("1. Creating threads by extending Thread class:\n");
        MyThread thread1 = new MyThread("Thread-1");
        MyThread thread2 = new MyThread("Thread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        // 2. Creating threads by implementing Runnable interface
        System.out.println("\n2. Creating threads by implementing Runnable interface:\n");
        Thread thread3 = new Thread(new MyRunnable("Runnable-1"));
        Thread thread4 = new Thread(new MyRunnable("Runnable-2"));

        thread3.start();
        thread4.start();

        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        // 3. Creating threads using Lambda expressions (Java 8+)
        System.out.println("\n3. Creating threads using Lambda expressions:\n");
        Thread thread5 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda-Thread: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Lambda thread interrupted");
                }
            }
        });

        thread5.start();

        try {
            thread5.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        // 4. Using ExecutorService and thread pools
        System.out.println("\n4. Using ExecutorService and thread pools:\n");
        ExecutorService executor = Executors.newFixedThreadPool(2); // Pool with 2 threads

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Task " + taskId + " executed by " + 
                                 Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Task interrupted");
                }
            });
        }

        // Shutdown the executor
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Executor interrupted");
        }

        // 5. Using Callable and Future for returning results
        System.out.println("\n5. Using Callable and Future for returning results:\n");
        ExecutorService executorWithResults = Executors.newFixedThreadPool(2);

        // Create a list of Callable tasks
        Callable<Integer> task1 = () -> {
            System.out.println("Calculating sum of first 10 numbers");
            Thread.sleep(300);
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        };

        Callable<Double> task2 = () -> {
            System.out.println("Calculating square root of 100");
            Thread.sleep(200);
            return Math.sqrt(100);
        };

        // Submit tasks and get Future objects
        Future<Integer> future1 = executorWithResults.submit(task1);
        Future<Double> future2 = executorWithResults.submit(task2);

        try {
            // Get results from futures
            Integer sum = future1.get();
            Double sqrt = future2.get();

            System.out.println("Sum result: " + sum);
            System.out.println("Square root result: " + sqrt);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error getting results: " + e.getMessage());
        }

        executorWithResults.shutdown();

        // 6. Thread synchronization
        System.out.println("\n6. Thread synchronization:\n");

        // a. Without synchronization - may cause race condition
        System.out.println("Without synchronization:");
        Counter unsafeCounter = new Counter();

        Thread incrementThread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter.increment();
            }
        });

        Thread incrementThread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                unsafeCounter.increment();
            }
        });

        incrementThread1.start();
        incrementThread2.start();

        try {
            incrementThread1.join();
            incrementThread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Expected count: 2000, Actual count: " + unsafeCounter.getCount());

        // b. With synchronization
        System.out.println("\nWith synchronization:");
        SynchronizedCounter safeCounter = new SynchronizedCounter();

        Thread syncThread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                safeCounter.increment();
            }
        });

        Thread syncThread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                safeCounter.increment();
            }
        });

        syncThread1.start();
        syncThread2.start();

        try {
            syncThread1.join();
            syncThread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Expected count: 2000, Actual count: " + safeCounter.getCount());
    }
}

// 1. Creating thread by extending Thread class
class MyThread extends Thread {
    private String threadName;

    public MyThread(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + ": " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted");
            }
        }
        System.out.println(threadName + " completed");
    }
}

// 2. Creating thread by implementing Runnable interface
class MyRunnable implements Runnable {
    private String threadName;

    public MyRunnable(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + ": " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted");
            }
        }
        System.out.println(threadName + " completed");
    }
}

// 6a. Unsynchronized counter - may cause race condition
class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

// 6b. Synchronized counter - thread-safe
class SynchronizedCounter {
    private int count = 0;

    // Method-level synchronization
    public synchronized void increment() {
        count++;
    }

    // Synchronized block example
    public void incrementWithBlock() {
        // Other non-synchronized operations could be here
        synchronized(this) {
            count++;
        }
        // Other non-synchronized operations could be here
    }

    public synchronized int getCount() {
        return count;
    }
}
