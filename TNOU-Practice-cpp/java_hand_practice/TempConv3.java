import java.util.Scanner;

public class TempConv3
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the temperature in Fahrenheit:");
        double fahrenheit = scanner.nextDouble();
        double celcius = (fahrenheit - 32) * 5 / 9;
        System.out.println("The temperature in Celcius: " + celcius);
        scanner.close();

    }
}