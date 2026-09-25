package JavaSimplePrograms;

/**
 * This class demonstrates polymorphism in Java
 * Polymorphism allows objects to be treated as instances of their parent class rather than their actual class
 */
public class PolymorphismDemo {

    public static void main(String[] args) {
        System.out.println("===== Polymorphism Demonstration =====\n");

        // Static Polymorphism (Compile-time Polymorphism) - Method Overloading
        System.out.println("--- Static Polymorphism (Method Overloading) ---\n");
        Calculator calculator = new Calculator();

        // Same method name, different parameters
        System.out.println("Addition of two integers: " + calculator.add(5, 3));
        System.out.println("Addition of three integers: " + calculator.add(5, 3, 2));
        System.out.println("Addition of two doubles: " + calculator.add(5.5, 3.5));
        System.out.println("Concatenation of two strings: " + calculator.add("Hello", "World"));

        // Dynamic Polymorphism (Runtime Polymorphism) - Method Overriding
        System.out.println("\n--- Dynamic Polymorphism (Method Overriding) ---\n");

        // Parent class reference
        Vehicle vehicle = new Vehicle();
        vehicle.start();
        vehicle.accelerate();
        vehicle.stop();

        // Child class objects with parent class reference
        System.out.println();
        Vehicle car = new Car();            // Car object with Vehicle reference
        Vehicle motorcycle = new Motorcycle(); // Motorcycle object with Vehicle reference
        Vehicle truck = new Truck();        // Truck object with Vehicle reference

        // Each object executes its own version of the methods
        System.out.println("\nCar behaviors:");
        car.start();
        car.accelerate();
        car.stop();

        System.out.println("\nMotorcycle behaviors:");
        motorcycle.start();
        motorcycle.accelerate();
        motorcycle.stop();

        System.out.println("\nTruck behaviors:");
        truck.start();
        truck.accelerate();
        truck.stop();

        // Polymorphism with method parameters
        System.out.println("\n--- Polymorphism with Method Parameters ---\n");
        VehicleInspector inspector = new VehicleInspector();

        inspector.inspectVehicle(car);
        inspector.inspectVehicle(motorcycle);
        inspector.inspectVehicle(truck);

        // Demonstrating instanceof operator with polymorphism
        System.out.println("\n--- Using instanceof with Polymorphism ---\n");
        Vehicle[] vehicles = {new Car(), new Motorcycle(), new Truck()};

        for (Vehicle v : vehicles) {
            if (v instanceof Car) {
                System.out.println("This is a Car");
                // Casting to access specific methods
                ((Car) v).honk();
            } else if (v instanceof Motorcycle) {
                System.out.println("This is a Motorcycle");
                ((Motorcycle) v).wheelie();
            } else if (v instanceof Truck) {
                System.out.println("This is a Truck");
                ((Truck) v).loadCargo();
            }
        }

        // Interface-based polymorphism
        System.out.println("\n--- Interface-based Polymorphism ---\n");
        Drawable circle = new Circle1();
        Drawable rectangle = new Rectangle1();
        Drawable triangle = new Triangle();

        circle.draw();
        rectangle.draw();
        triangle.draw();

        // Polymorphic collections
        System.out.println("\n--- Polymorphic Collections ---\n");
        Drawable[] shapes = {new Circle1(), new Rectangle1(), new Triangle()};

        for (Drawable shape : shapes) {
            shape.draw();
        }
    }
}

/**
 * Calculator class demonstrating Method Overloading (Static Polymorphism)
 */
class Calculator {
    // Method overloading - same name, different parameter types or count

    // Addition of two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Addition of three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Addition of two doubles
    public double add(double a, double b) {
        return a + b;
    }

    // "Addition" of two strings (concatenation)
    public String add(String a, String b) {
        return a + " " + b;
    }
}

/**
 * Base Vehicle class for Method Overriding (Dynamic Polymorphism) examples
 */
class Vehicle {
    public void start() {
        System.out.println("Vehicle is starting");
    }

    public void accelerate() {
        System.out.println("Vehicle is accelerating");
    }

    public void stop() {
        System.out.println("Vehicle is stopping");
    }
}

/**
 * Car class extending Vehicle
 */
class Car extends Vehicle {
    // Method overriding - same name, same parameters, different implementation
    @Override
    public void start() {
        System.out.println("Car is starting with ignition key");
    }

    @Override
    public void accelerate() {
        System.out.println("Car is accelerating by pressing gas pedal");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping with brake pedal");
    }

    // Method specific to Car
    public void honk() {
        System.out.println("Car honks: Beep! Beep!");
    }
}

/**
 * Motorcycle class extending Vehicle
 */
class Motorcycle extends Vehicle {
    @Override
    public void start() {
        System.out.println("Motorcycle is starting with a kick or button");
    }

    @Override
    public void accelerate() {
        System.out.println("Motorcycle is accelerating by twisting the throttle");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle is stopping with hand and foot brakes");
    }

    // Method specific to Motorcycle
    public void wheelie() {
        System.out.println("Motorcycle performs a wheelie!");
    }
}

/**
 * Truck class extending Vehicle
 */
class Truck extends Vehicle {
    @Override
    public void start() {
        System.out.println("Truck is starting with a large diesel engine");
    }

    @Override
    public void accelerate() {
        System.out.println("Truck is accelerating slowly due to heavy load");
    }

    @Override
    public void stop() {
        System.out.println("Truck is stopping with air brakes");
    }

    // Method specific to Truck
    public void loadCargo() {
        System.out.println("Truck is loading cargo");
    }
}

/**
 * VehicleInspector class demonstrating polymorphic method parameters
 */
class VehicleInspector {
    // This method can accept any Vehicle or its subclasses
    public void inspectVehicle(Vehicle vehicle) {
        System.out.println("Inspecting a vehicle");
        vehicle.start();
        vehicle.stop();
    }
}

/**
 * Drawable interface for interface-based polymorphism
 */
interface Drawable {
    void draw();
}

/**
 * Circle class implementing Drawable interface
 */
class Circle1 implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

/**
 * Rectangle class implementing Drawable interface
 */
class Rectangle1 implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}

/**
 * Triangle class implementing Drawable interface
 */
class Triangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a triangle");
    }
}
