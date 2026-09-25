import java.util.Scanner;

public class PalindromeOrNot {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number");
        int n = sc.nextInt();
        int original_number = n;
        int reversed_number = 0;
        while(n>0){
            reversed_number = reversed_number*10 + n%10;
            n = n/10;
        }
        if (original_number == reversed_number){
            System.out.println("The number is a Palindrome");
        }else {
            System.out.println("The number is not a Palindrome");
        }
    }
}
