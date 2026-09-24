import java.util.Scanner;

public class Prime {
    public static void main(String[] args) {
        System.out.println("Enter a number");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        boolean isPrime=true;
        if(n<=1){
            isPrime=false;
        }else {
            for (int i=2;i<n;i++) {
                if(n%i==0){
                    isPrime=false;
                    break;
                }
            }
        }
        if(isPrime){
            System.out.println("The number is a Prime");
        }
        else {
            System.out.println("The number is not a Prime");
        }
    }
}
