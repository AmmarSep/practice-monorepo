// Program 10: Program for finding volume of a sphere (Concept: Class and Object)

import java.util.Scanner;

class Sphere {
    double radius;

    Sphere(double r) {
        radius = r;
    }

    double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    double calculateSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }
}

public class P10_SphereVolume {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter radius of the sphere: ");
        double radius = scanner.nextDouble();

        Sphere sphere = new Sphere(radius);

        System.out.println("Volume of sphere: " + sphere.calculateVolume());
        System.out.println("Surface area of sphere: " + sphere.calculateSurfaceArea());

        scanner.close();
    }
}
