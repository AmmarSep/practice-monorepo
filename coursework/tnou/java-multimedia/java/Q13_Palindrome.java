import java.util.Scanner;

abstract class Number {
    int num;
    abstract boolean isPalindrome();
}

class PalindromeChecker extends Number {
    PalindromeChecker(int n) {
        num = n;
    }
    
    boolean isPalindrome() {
        int original = num, reverse = 0;
        while (num > 0) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }
        return original == reverse;
    }
}

public class Q13_Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        PalindromeChecker pc = new PalindromeChecker(sc.nextInt());
        System.out.println(pc.isPalindrome() ? "Palindrome" : "Not Palindrome");
        sc.close();
    }
}
