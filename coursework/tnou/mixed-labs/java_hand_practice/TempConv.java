import java.util.Scanner;

public class TempConv {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the temperature in Farenheit: ");
        double far = sc.nextDouble();
        double cel = (far - 32) * 5 / 9;
        System.out.println("Temperature in Celsius: " + cel);
        sc.close();
    }
}