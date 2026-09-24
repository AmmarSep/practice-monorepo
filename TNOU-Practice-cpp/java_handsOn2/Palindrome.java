import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number:");
        int n=sc.nextInt();
        int original = n;
        int reversed = 0;
        while(n>0){
            reversed = reversed*10+n%10;
            n=n/10;
        }
        if(reversed==original){
            System.out.println("The number is a Palindrome");
        }else {
            System.out.println("The number is not a Palindrome");
        }
    }
}
