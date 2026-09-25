import java.util.Scanner;

class Sphere {
    double radius;
    
    Sphere(double r) {
        radius = r;
    }
    
    double volume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }
}

public class Q10_SphereVolume {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter radius: ");
        double r = sc.nextDouble();
        Sphere s = new Sphere(r);
        System.out.println("Volume: " + s.volume());
        sc.close();
    }
}
