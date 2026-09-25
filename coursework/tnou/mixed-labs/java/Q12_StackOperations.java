import java.util.Scanner;

class Stack {
    int[] arr;
    int top, size;
    
    Stack(int s) {
        size = s;
        arr = new int[size];
        top = -1;
    }
    
    void push(int val) {
        if (top == size - 1) {
            System.out.println("Stack Overflow");
        } else {
            arr[++top] = val;
            System.out.println("Pushed: " + val);
        }
    }
    
    void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
        } else {
            System.out.println("Popped: " + arr[top--]);
        }
    }
    
    void display() {
        if (top == -1) {
            System.out.println("Stack is empty");
        } else {
            System.out.print("Stack: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}

public class Q12_StackOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter stack size: ");
        Stack s = new Stack(sc.nextInt());
        while (true) {
            System.out.println("\n1.Push 2.Pop 3.Display 4.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            if (ch == 1) {
                System.out.print("Enter value: ");
                s.push(sc.nextInt());
            } else if (ch == 2) {
                s.pop();
            } else if (ch == 3) {
                s.display();
            } else {
                break;
            }
        }
        sc.close();
    }
}
