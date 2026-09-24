package PracticePrograms;

import java.util.Scanner;

public class SwapTwoNumbers {
    public static void main(String[] args) {
        int a,b,temp;
        System.out.println("Enter the two numbers : ");
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        System.out.println("Number before swapping : " +a+b);
        temp=a;
        a=b;
        b=temp;
        System.out.println("Number after swapping : " +a+b);
    }
}
