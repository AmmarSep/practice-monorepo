// Program 16: Program for queue implementation (Concept: Exception Handling; User defined Exception)

import java.util.Scanner;

class QueueFullException extends Exception {
    public QueueFullException(String message) {
        super(message);
    }
}

class QueueEmptyException extends Exception {
    public QueueEmptyException(String message) {
        super(message);
    }
}

class Queue {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int currentSize;

    public Queue(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    public void enqueue(int value) throws QueueFullException {
        if (isFull()) {
            throw new QueueFullException("Queue is full! Cannot enqueue " + value);
        }
        rear = (rear + 1) % maxSize;
        queueArray[rear] = value;
        currentSize++;
        System.out.println(value + " enqueued to queue");
    }

    public int dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty! Cannot dequeue");
        }
        int value = queueArray[front];
        front = (front + 1) % maxSize;
        currentSize--;
        System.out.println(value + " dequeued from queue");
        return value;
    }

    public int peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty!");
        }
        return queueArray[front];
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public boolean isFull() {
        return (currentSize == maxSize);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
        } else {
            System.out.print("Queue elements: ");
            int i = front;
            for (int count = 0; count < currentSize; count++) {
                System.out.print(queueArray[i] + " ");
                i = (i + 1) % maxSize;
            }
            System.out.println();
        }
    }
}

public class P16_QueueException {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter queue size: ");
        int size = scanner.nextInt();

        Queue queue = new Queue(size);

        while (true) {
            System.out.println("\n===== QUEUE OPERATIONS =====");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter value to enqueue: ");
                        int value = scanner.nextInt();
                        queue.enqueue(value);
                        break;
                    case 2:
                        queue.dequeue();
                        break;
                    case 3:
                        int front = queue.peek();
                        System.out.println("Front element: " + front);
                        break;
                    case 4:
                        queue.display();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (QueueFullException | QueueEmptyException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }
}
