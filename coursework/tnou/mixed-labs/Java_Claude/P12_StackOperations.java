// Program 12: Program for implementing stack Operations (Concept: Constructor)

import java.util.Scanner;

class Stack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    // Constructor
    Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full! Cannot push " + value);
        } else {
            stackArray[++top] = value;
            System.out.println(value + " pushed to stack");
        }
    }

    int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Cannot pop");
            return -1;
        } else {
            int value = stackArray[top--];
            System.out.println(value + " popped from stack");
            return value;
        }
    }

    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        } else {
            return stackArray[top];
        }
    }

    boolean isEmpty() {
        return (top == -1);
    }

    boolean isFull() {
        return (top == maxSize - 1);
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
        } else {
            System.out.print("Stack elements: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(stackArray[i] + " ");
            }
            System.out.println();
        }
    }
}

public class P12_StackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter stack size: ");
        int size = scanner.nextInt();

        Stack stack = new Stack(size);

        while (true) {
            System.out.println("\n===== STACK OPERATIONS =====");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to push: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    int top = stack.peek();
                    if (top != -1) {
                        System.out.println("Top element: " + top);
                    }
                    break;
                case 4:
                    stack.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
