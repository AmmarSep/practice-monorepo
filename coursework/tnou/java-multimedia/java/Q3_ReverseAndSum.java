import java.util.Scanner;

public class Q3_ReverseAndSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        int original = num, reverse = 0, sum = 0;
        while (num > 0) {
            int digit = num % 10;
            reverse = reverse * 10 + digit;
            sum += digit;
            num /= 10;
        }
        System.out.println("Original: " + original);
        System.out.println("Reverse: " + reverse);
        System.out.println("Sum of digits: " + sum);
        sc.close();
    }
}
