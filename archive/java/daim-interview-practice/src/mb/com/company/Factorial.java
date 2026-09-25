package mb.com.company;

public class Factorial {
    public static void main(String[] args) {
        int toBeFoundNumber = 6;
        int factorial = 1;
        for (int i = 1; i<=toBeFoundNumber; i++){
            factorial = factorial*i;
        }
        System.out.println("Factorial of the given number : " +factorial);
    }
}
