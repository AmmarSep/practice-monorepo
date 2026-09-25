package Intermediate.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * This class demonstrates advanced usage of ThreadPoolExecutor and other
 * concurrency utilities from the java.util.concurrent package
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        System.out.println("=== ThreadPoolExecutor Demonstration ===\n");

        // Demonstrate different types of thread pools
        demonstrateFixedThreadPool();
        demonstrateCachedThreadPool();
        demonstrateScheduledThreadPool();
        demonstrateCustomThreadPool();

        // Demonstrate advanced features
        demonstrateCompletableFuture();
        demonstrateCountDownLatch();
        demonstrateCyclicBarrier();
        demonstrateSemaphore();
        demonstrateCompletionService();

        System.out.println("\nAll thread pool demonstrations completed.");
    }

    /**
     * Demonstrates a fixed thread pool with a specific number of threads
     */
    private static void demonstrateFixedThreadPool() {
        System.out.println("\n1. Fixed Thread Pool Demonstration:");
        System.out.println("Creating a fixed thread pool with 3 threads");

        // Create a fixed thread pool with 3 threads
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        try {
            // Submit tasks to the thread pool
            for (int i = 1; i <= 5; i++) {
                final int taskId = i;
                fixedThreadPool.submit(() -> {
                    System.out.println("Task " + taskId + " is executing on thread: " + 
                                     Thread.currentThread().getName());
                    try {
                        // Simulate work
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "Task " + taskId + " completed";
                });
            }

            // Wait a bit to see the tasks execute
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Properly shutdown the thread pool
            System.out.println("Shutting down fixed thread pool");
            shutdownAndAwaitTermination(fixedThreadPool);
        }
    }

    /**
     * Demonstrates a cached thread pool that creates new threads as needed
     */
    private static void demonstrateCachedThreadPool() {
        System.out.println("\n2. Cached Thread Pool Demonstration:");
        System.out.println("Creating a cached thread pool that creates new threads as needed");

        // Create a cached thread pool
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        try {
            // Submit a burst of short-lived tasks
            for (int i = 1; i <= 10; i++) {
                final int taskId = i;
                cachedThreadPool.submit(() -> {
                    System.out.println("Task " + taskId + " is executing on thread: " + 
                                     Thread.currentThread().getName());
                    try {
                        // Simulate short work
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "Task " + taskId + " completed";
                });
            }

            // Wait a bit to see the tasks execute
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Properly shutdown the thread pool
            System.out.println("Shutting down cached thread pool");
            shutdownAndAwaitTermination(cachedThreadPool);
        }
    }

    /**
     * Demonstrates a scheduled thread pool for delayed and periodic tasks
     */
    private static void demonstrateScheduledThreadPool() {
        System.out.println("\n3. Scheduled Thread Pool Demonstration:");
        System.out.println("Creating a scheduled thread pool for delayed and periodic tasks");

        // Create a scheduled thread pool with 2 threads
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        try {
            // Schedule a task to run after a delay
            System.out.println("Scheduling a task to run after 2 seconds");
            scheduledThreadPool.schedule(() -> {
                System.out.println("Delayed task executed on thread: " + 
                                 Thread.currentThread().getName());
                return "Delayed task completed";
            }, 2, TimeUnit.SECONDS);

            // Schedule a task to run periodically
            System.out.println("Scheduling a task to run every 1 second");
            ScheduledFuture<?> periodicTask = scheduledThreadPool.scheduleAtFixedRate(() -> {
                System.out.println("Periodic task executed on thread: " + 
                                 Thread.currentThread().getName() + 
                                 " at time: " + System.currentTimeMillis() % 10000);
            }, 1, 1, TimeUnit.SECONDS);

            // Let the periodic task run a few times
            Thread.sleep(5000);

            // Cancel the periodic task
            System.out.println("Cancelling periodic task");
            periodicTask.cancel(false);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Properly shutdown the thread pool
            System.out.println("Shutting down scheduled thread pool");
            shutdownAndAwaitTermination(scheduledThreadPool);
        }
    }

    /**
     * Demonstrates a custom thread pool with specific configuration
     */
    private static void demonstrateCustomThreadPool() {
        System.out.println("\n4. Custom Thread Pool Demonstration:");
        System.out.println("Creating a custom thread pool with specific configuration");

        // Parameters for custom thread pool
        int corePoolSize = 2;         // Base number of threads
        int maximumPoolSize = 4;      // Maximum number of threads
        long keepAliveTime = 10;      // Time to keep excess idle threads alive
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10); // Queue for tasks
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy(); // Policy for rejected tasks

        // Create the custom thread pool
        ThreadPoolExecutor customThreadPool = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            unit,
            workQueue,
            threadFactory,
            handler
        );

        // Set a custom name prefix for threads
        customThreadPool.setThreadFactory(r -> {
            Thread t = threadFactory.newThread(r);
            t.setName("CustomPool-" + t.getName());
            return t;
        });

        try {
            // Submit tasks to fill the work queue and trigger core pool expansion
            for (int i = 1; i <= 15; i++) {
                final int taskId = i;
                customThreadPool.submit(() -> {
                    System.out.println("Task " + taskId + " is executing on thread: " + 
                                     Thread.currentThread().getName());
                    try {
                        // Simulate work with varying duration
                        Thread.sleep(500 + new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "Task " + taskId + " completed";
                });

                // Print pool stats after each submission
                if (i % 5 == 0) {
                    System.out.println(String.format(
                        "Pool stats - Active: %d, Pool Size: %d, Core Pool Size: %d, Queue Size: %d",
                        customThreadPool.getActiveCount(),
                        customThreadPool.getPoolSize(),
                        customThreadPool.getCorePoolSize(),
                        customThreadPool.getQueue().size()
                    ));
                }
            }

            // Wait for tasks to complete
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Properly shutdown the thread pool
            System.out.println("Shutting down custom thread pool");
            shutdownAndAwaitTermination(customThreadPool);
        }
    }

    /**
     * Demonstrates CompletableFuture for asynchronous programming
     */
    private static void demonstrateCompletableFuture() {
        System.out.println("\n5. CompletableFuture Demonstration:");
        System.out.println("Using CompletableFuture for asynchronous programming");

        try {
            // Create a CompletableFuture that completes after a delay
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Task 1 executing on thread: " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    return "Result from Task 1";
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return "Task 1 was interrupted";
                }
            });

            // Create another CompletableFuture
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Task 2 executing on thread: " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                    return "Result from Task 2";
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return "Task 2 was interrupted";
                }
            });

            // Combine results when both complete
            CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> {
                System.out.println("Combining results on thread: " + Thread.currentThread().getName());
                return result1 + " and " + result2;
            });

            // Add exception handling
            CompletableFuture<String> exceptionHandledFuture = combinedFuture.exceptionally(ex -> {
                System.out.println("Exception occurred: " + ex.getMessage());
                return "Default result due to exception";
            });

            // Wait for the combined result
            String result = exceptionHandledFuture.get(5, TimeUnit.SECONDS);
            System.out.println("Final result: " + result);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted");
        } catch (ExecutionException e) {
            System.out.println("Execution exception: " + e.getCause().getMessage());
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for result");
        }
    }

    /**
     * Demonstrates CountDownLatch for coordinating multiple threads
     */
    private static void demonstrateCountDownLatch() {
        System.out.println("\n6. CountDownLatch Demonstration:");
        System.out.println("Using CountDownLatch to coordinate multiple threads");

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            // Create a CountDownLatch with a count of 3
            CountDownLatch latch = new CountDownLatch(3);
            System.out.println("Main thread waiting for 3 tasks to complete");

            // Submit tasks that will count down the latch
            for (int i = 1; i <= 3; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    try {
                        System.out.println("Task " + taskId + " starting on thread: " + 
                                         Thread.currentThread().getName());
                        // Simulate work with different durations
                        Thread.sleep(1000 * taskId);
                        System.out.println("Task " + taskId + " completed, counting down latch");
                        latch.countDown();
                        System.out.println("Latch count after Task " + taskId + ": " + getCountFromLatch(latch));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            // Wait for all tasks to complete (countdown to zero)
            System.out.println("Main thread waiting on latch");
            latch.await();
            System.out.println("Latch released, all tasks have completed");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted");
        } finally {
            // Properly shutdown the thread pool
            shutdownAndAwaitTermination(executor);
        }
    }

    /**
     * Demonstrates CyclicBarrier for synchronizing multiple threads
     */
    private static void demonstrateCyclicBarrier() {
        System.out.println("\n7. CyclicBarrier Demonstration:");
        System.out.println("Using CyclicBarrier to synchronize multiple threads at specific points");

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            // Create a CyclicBarrier with a count of 3 and a runnable that executes when the barrier trips
            CyclicBarrier barrier = new CyclicBarrier(3, () -> {
                System.out.println("\nAll threads reached the barrier, executing barrier action");
                System.out.println("Barrier action executing on thread: " + Thread.currentThread().getName());
            });

            // Submit tasks that will wait at the barrier
            for (int i = 1; i <= 3; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    try {
                        // Phase 1
                        System.out.println("Task " + taskId + " starting Phase 1 on thread: " + 
                                         Thread.currentThread().getName());
                        Thread.sleep(1000 * taskId); // Different work times
                        System.out.println("Task " + taskId + " completed Phase 1, waiting at barrier");

                        // Wait at the barrier
                        barrier.await();

                        // Phase 2 - starts after all threads reach the barrier
                        System.out.println("Task " + taskId + " starting Phase 2 on thread: " + 
                                         Thread.currentThread().getName());
                        Thread.sleep(500 * taskId); // Different work times
                        System.out.println("Task " + taskId + " completed Phase 2, waiting at barrier");

                        // Wait at the barrier again - demonstrating the cyclic nature
                        barrier.await();

                        // Phase 3 - starts after all threads reach the barrier again
                        System.out.println("Task " + taskId + " starting Phase 3 on thread: " + 
                                         Thread.currentThread().getName());
                        Thread.sleep(300);
                        System.out.println("Task " + taskId + " completed all phases");

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (BrokenBarrierException e) {
                        System.out.println("Barrier was broken: " + e.getMessage());
                    }
                });
            }

            // Wait for all tasks to complete
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted");
        } finally {
            // Properly shutdown the thread pool
            shutdownAndAwaitTermination(executor);
        }
    }

    /**
     * Demonstrates Semaphore for controlling access to resources
     */
    private static void demonstrateSemaphore() {
        System.out.println("\n8. Semaphore Demonstration:");
        System.out.println("Using Semaphore to control access to limited resources");

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(5);

        try {
            // Create a semaphore with 2 permits
            Semaphore semaphore = new Semaphore(2);
            System.out.println("Created a semaphore with 2 permits");

            // Create a resource that uses the semaphore
            Resource resource = new Resource(semaphore);

            // Submit tasks that will use the resource
            for (int i = 1; i <= 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    try {
                        System.out.println("Task " + taskId + " attempting to access the resource");
                        resource.useResource(taskId);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

                // Small delay between task submissions
                Thread.sleep(100);
            }

            // Wait for all tasks to complete
            Thread.sleep(8000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted");
        } finally {
            // Properly shutdown the thread pool
            shutdownAndAwaitTermination(executor);
        }
    }

    /**
     * Demonstrates CompletionService for handling multiple asynchronous tasks
     */
    private static void demonstrateCompletionService() {
        System.out.println("\n9. CompletionService Demonstration:");
        System.out.println("Using CompletionService to handle multiple asynchronous tasks");

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            // Create a CompletionService
            CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

            // Submit tasks with different execution times
            List<Future<String>> futures = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                final int taskId = i;
                final int sleepTime = 6 - i; // Task 5 finishes first, Task 1 last

                futures.add(completionService.submit(() -> {
                    System.out.println("Task " + taskId + " starting, will take " + 
                                     sleepTime + " seconds on thread: " + 
                                     Thread.currentThread().getName());
                    Thread.sleep(sleepTime * 1000);
                    return "Result from Task " + taskId + " after " + sleepTime + " seconds";
                }));
            }

            // Retrieve results as they become available
            System.out.println("Retrieving results as they complete:");
            for (int i = 0; i < futures.size(); i++) {
                Future<String> completedTask = completionService.take();
                String result = completedTask.get();
                System.out.println("Completed: " + result);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted");
        } catch (ExecutionException e) {
            System.out.println("Execution exception: " + e.getCause().getMessage());
        } finally {
            // Properly shutdown the thread pool
            shutdownAndAwaitTermination(executor);
        }
    }

    /**
     * Helper method to safely terminate an ExecutorService
     */
    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        // Disable new tasks from being submitted
        pool.shutdown();
        try {
            // Wait for existing tasks to terminate
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                // Cancel currently executing tasks forcefully
                pool.shutdownNow();
                // Wait for tasks to respond to being cancelled
                if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Helper method to extract count from CountDownLatch (reflection-based)
     */
    private static long getCountFromLatch(CountDownLatch latch) {
        try {
            java.lang.reflect.Field syncField = CountDownLatch.class.getDeclaredField("sync");
            syncField.setAccessible(true);
            Object sync = syncField.get(latch);
            java.lang.reflect.Field countField = sync.getClass().getSuperclass().getDeclaredField("state");
            countField.setAccessible(true);
            return countField.getLong(sync);
        } catch (Exception e) {
            return -1; // Reflection failed
        }
    }

    /**
     * Resource class that uses a semaphore to control access
     */
    static class Resource {
        private final Semaphore semaphore;

        public Resource(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public void useResource(int taskId) throws InterruptedException {
            System.out.println("Task " + taskId + " waiting to acquire permit");
            semaphore.acquire(); // Acquire a permit

            try {
                System.out.println("Task " + taskId + " acquired permit, using resource on thread: " + 
                                 Thread.currentThread().getName());
                System.out.println("Available permits: " + semaphore.availablePermits());

                // Simulate using the resource
                Thread.sleep(2000);

            } finally {
                System.out.println("Task " + taskId + " releasing permit");
                semaphore.release(); // Release the permit
                System.out.println("Available permits after Task " + taskId + ": " + semaphore.availablePermits());
            }
        }
    }
}
