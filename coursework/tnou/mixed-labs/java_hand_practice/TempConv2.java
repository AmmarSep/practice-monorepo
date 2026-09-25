import java.util.Scanner;

public class TempConv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the temperature in Celsius: ");
        double cel = sc.nextDouble();
        double far = (cel * 9 / 5) + 32;
        System.out.println("Temperature in Farenheit: " + far);
        sc.close();
    }
}