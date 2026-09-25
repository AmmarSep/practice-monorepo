package P15_AreaCalculation;

import java.util.Scanner;

public class P15_AreaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== AREA CALCULATION =====");

        System.out.print("Enter base of triangle: ");
        double base = scanner.nextDouble();
        System.out.print("Enter height of triangle: ");
        double height = scanner.nextDouble();

        Triangle triangle = new Triangle(base, height);
        triangle.displayArea();

        System.out.print("\nEnter length of rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter width of rectangle: ");
        double width = scanner.nextDouble();

        Rectangle rectangle = new Rectangle(length, width);
        rectangle.displayArea();

        scanner.close();
    }
}
