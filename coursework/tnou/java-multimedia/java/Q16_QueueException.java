import java.util.Scanner;

class QueueFullException extends Exception {
    QueueFullException(String msg) {
        super(msg);
    }
}

class QueueEmptyException extends Exception {
    QueueEmptyException(String msg) {
        super(msg);
    }
}

class Queue {
    int[] arr;
    int front, rear, size;
    
    Queue(int s) {
        size = s;
        arr = new int[size];
        front = rear = -1;
    }
    
    void enqueue(int val) throws QueueFullException {
        if (rear == size - 1) {
            throw new QueueFullException("Queue is Full");
        }
        if (front == -1) front = 0;
        arr[++rear] = val;
        System.out.println("Enqueued: " + val);
    }
    
    void dequeue() throws QueueEmptyException {
        if (front == -1 || front > rear) {
            throw new QueueEmptyException("Queue is Empty");
        }
        System.out.println("Dequeued: " + arr[front++]);
    }
    
    void display() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is empty");
        } else {
            System.out.print("Queue: ");
            for (int i = front; i <= rear; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}

public class Q16_QueueException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter queue size: ");
        Queue q = new Queue(sc.nextInt());
        while (true) {
            System.out.println("\n1.Enqueue 2.Dequeue 3.Display 4.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            try {
                if (ch == 1) {
                    System.out.print("Enter value: ");
                    q.enqueue(sc.nextInt());
                } else if (ch == 2) {
                    q.dequeue();
                } else if (ch == 3) {
                    q.display();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}
