// Program 17: Program to implement Multi-Threading (Concept: Multi-Threading by extending Thread class)

class EvenThread extends Thread {
    private int limit;

    public EvenThread(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        System.out.println("\nEven numbers from 1 to " + limit + ":");
        for (int i = 2; i <= limit; i += 2) {
            System.out.print(i + " ");
            try {
                Thread.sleep(100);  // Sleep for 100ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
        System.out.println();
    }
}

class OddThread extends Thread {
    private int limit;

    public OddThread(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        System.out.println("\nOdd numbers from 1 to " + limit + ":");
        for (int i = 1; i <= limit; i += 2) {
            System.out.print(i + " ");
            try {
                Thread.sleep(100);  // Sleep for 100ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
        System.out.println();
    }
}

class FibonacciThread extends Thread {
    private int terms;

    public FibonacciThread(int terms) {
        this.terms = terms;
    }

    @Override
    public void run() {
        System.out.println("\nFibonacci series up to " + terms + " terms:");
        int first = 0, second = 1;
        for (int i = 1; i <= terms; i++) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
            try {
                Thread.sleep(100);  // Sleep for 100ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
        System.out.println();
    }
}

public class P17_MultiThreading {
    public static void main(String[] args) {
        int limit = 20;
        int terms = 10;

        EvenThread evenThread = new EvenThread(limit);
        OddThread oddThread = new OddThread(limit);
        FibonacciThread fibonacciThread = new FibonacciThread(terms);

        System.out.println("Starting multiple threads...");

        evenThread.start();
        oddThread.start();
        fibonacciThread.start();

        try {
            evenThread.join();
            oddThread.join();
            fibonacciThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println("\nAll threads completed execution.");
    }
}
