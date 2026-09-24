package FrequentInterviewQuestions;

/**
 * This class demonstrates Polymorphism in Java
 * 
 * Polymorphism is the ability of an object to take on many forms.
 * The word polymorphism means having many forms.
 * 
 * There are two types of polymorphism in Java:
 * 1. Compile-time polymorphism (Static binding) - Method overloading
 * 2. Runtime polymorphism (Dynamic binding) - Method overriding
 */
public class PolymorphismDemo {

    public static void main(String[] args) {
        System.out.println("===== Polymorphism Demonstration =====");

        // 1. Compile-Time Polymorphism (Method Overloading)
        demonstrateMethodOverloading();

        // 2. Runtime Polymorphism (Method Overriding)
        demonstrateMethodOverriding();

        // Additional examples
        demonstrateOperatorOverloading();
        demonstratePolymorphicVariables();
        demonstrateInstanceOf();
    }

    /**
     * Demonstrates Method Overloading (Compile-time Polymorphism)
     * Method overloading occurs when a class has multiple methods with the same name
     * but different parameters (different number, type, or order of parameters).
     */
    private static void demonstrateMethodOverloading() {
        System.out.println("\n1. Method Overloading (Compile-time Polymorphism):");

        PolyMathOperation math = new PolyMathOperation();

        // Same method name but different parameter types/counts
        System.out.println("Adding two integers: " + math.add(5, 10));  // Calls add(int, int)
        System.out.println("Adding three integers: " + math.add(5, 10, 15));  // Calls add(int, int, int)
        System.out.println("Adding two doubles: " + math.add(5.5, 10.5));  // Calls add(double, double)
        System.out.println("Adding int and double: " + math.add(5, 10.5));  // Calls add(int, double)

        // The compiler determines which method to call based on the arguments
        // This is known as static binding or compile-time polymorphism
    }

    /**
     * Demonstrates Method Overriding (Runtime Polymorphism)
     * Method overriding occurs when a subclass provides a specific implementation
     * for a method that is already defined in its parent class.
     */
    private static void demonstrateMethodOverriding() {
        System.out.println("\n2. Method Overriding (Runtime Polymorphism):");

        // Create an array of PolyVehicle references pointing to different vehicle objects
        PolyVehicle[] vehicles = new PolyVehicle[3];
        vehicles[0] = new PolyVehicle("Generic Vehicle", "Unknown");
        vehicles[1] = new PolyCar("Toyota Camry", "Sedan", 4);
        vehicles[2] = new PolyMotorcycle("Harley Davidson", "Cruiser", false);

        // Polymorphic method calls - the JVM decides which method to call at runtime
        // based on the actual object type, not the reference type
        for (PolyVehicle vehicle : vehicles) {
            System.out.println("\nVehicle details: " + vehicle.getName() + " - " + vehicle.getType());

            // This method is overridden in subclasses
            vehicle.start();  // Calls the appropriate version based on the actual object
            vehicle.move();   // Common implementation from parent class
            vehicle.stop();   // Common implementation from parent class

            // This is how the overridden method is resolved at runtime
            // based on the actual object type (dynamic binding)
        }
    }

    /**
     * Demonstrates Operator Overloading (limited in Java)
     * Unlike some other languages, Java doesn't support direct operator overloading,
     * but it has some built-in operator overloading like the + operator for String concatenation.
     */
    private static void demonstrateOperatorOverloading() {
        System.out.println("\n3. Operator Overloading (Limited in Java):");

        // + operator behaves differently based on operand types
        int a = 10;
        int b = 20;
        System.out.println("Addition for integers: " + a + " + " + b + " = " + (a + b));  // Arithmetic addition

        String str1 = "Hello";
        String str2 = "World";
        System.out.println("Addition for strings: " + str1 + " + " + str2 + " = " + (str1 + str2));  // String concatenation

        System.out.println("Mixed addition: " + a + " + " + str1 + " = " + (a + str1));  // int converted to String
    }

    /**
     * Demonstrates polymorphic variables and parameter passing
     */
    private static void demonstratePolymorphicVariables() {
        System.out.println("\n4. Polymorphic Variables and Parameters:");

        // Parent class reference can hold a child class object
        PolyVehicle genericVehicle = new PolyVehicle("Generic", "Unknown");
        PolyVehicle carAsVehicle = new PolyCar("Ford Mustang", "Sports Car", 2);  // Polymorphic assignment

        System.out.println("\nCalling methods directly:");
        genericVehicle.start();  // Calls PolyVehicle's start()
        carAsVehicle.start();    // Calls PolyCar's start() - polymorphic behavior

        // This demonstrates upcasting (implicit)
        // Upcasting is always safe as a subclass is always a specialized version of its superclass

        // Polymorphic method parameters
        System.out.println("\nPolymorphic method parameters:");
        startVehicle(genericVehicle);  // Pass PolyVehicle object
        startVehicle(carAsVehicle);    // Pass PolyCar object as PolyVehicle
        startVehicle(new PolyMotorcycle("Ducati", "Sport", true));  // Pass PolyMotorcycle object as PolyVehicle
    }

    /**
     * Helper method that accepts any PolyVehicle type (or its subclasses)
     */
    private static void startVehicle(PolyVehicle vehicle) {
        System.out.println("Starting: " + vehicle.getName());
        vehicle.start();  // Polymorphic call

        // We can check the actual type at runtime
        if (vehicle instanceof PolyCar) {
            System.out.println("This is a car with " + ((PolyCar) vehicle).getDoorCount() + " doors");
            // This requires downcasting (explicit casting from parent to child)
            // Downcasting can be dangerous and should be checked with instanceof
        } else if (vehicle instanceof PolyMotorcycle) {
            PolyMotorcycle bike = (PolyMotorcycle) vehicle;  // Downcasting
            System.out.println("This is a motorcycle" + 
                (bike.hasWindshield() ? " with a windshield" : " without a windshield"));
        }
    }

    /**
     * Demonstrates instanceof operator and type checking/casting
     */
    private static void demonstrateInstanceOf() {
        System.out.println("\n5. instanceof Operator and Type Casting:");

        // Create objects
        PolyVehicle vehicle = new PolyVehicle("Generic", "Basic");
        PolyVehicle car = new PolyCar("Honda Civic", "Sedan", 4);
        PolyCar actualCar = new PolyCar("BMW", "Coupe", 2);

        // Check types with instanceof
        System.out.println("vehicle instanceof PolyVehicle: " + (vehicle instanceof PolyVehicle));  // true
        System.out.println("vehicle instanceof PolyCar: " + (vehicle instanceof PolyCar));  // false
        System.out.println("car instanceof PolyVehicle: " + (car instanceof PolyVehicle));  // true
        System.out.println("car instanceof PolyCar: " + (car instanceof PolyCar));  // true
        System.out.println("actualCar instanceof PolyVehicle: " + (actualCar instanceof PolyVehicle));  // true

        // Safe downcasting with instanceof check
        System.out.println("\nSafe downcasting example:");
        processVehicle(vehicle);
        processVehicle(car);
        processVehicle(actualCar);
    }

    /**
     * Helper method that safely downcasts when appropriate
     */
    private static void processVehicle(PolyVehicle v) {
        System.out.println("Processing vehicle: " + v.getName());

        if (v instanceof PolyCar) {
            // Safe downcasting after instanceof check
            PolyCar c = (PolyCar) v;
            c.honk();  // Car-specific method
            System.out.println("This car has " + c.getDoorCount() + " doors");
        } else {
            System.out.println("This is not a car");
        }
    }
}

// Classes for demonstrating Method Overloading
class PolyMathOperation {
    // Overloaded methods - same name, different parameters
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double add(int a, double b) {
        return a + b;
    }
}

// Classes for demonstrating Method Overriding
class PolyVehicle {
    private String name;
    private String type;

    public PolyVehicle(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // Methods that can be overridden by subclasses
    public void start() {
        System.out.println("Vehicle starting");
    }

    public void move() {
        System.out.println("Vehicle moving");
    }

    public void stop() {
        System.out.println("Vehicle stopped");
    }
}

class PolyCar extends PolyVehicle {
    private int doorCount;

    public PolyCar(String name, String type, int doorCount) {
        super(name, type);  // Call parent constructor
        this.doorCount = doorCount;
    }

    public int getDoorCount() {
        return doorCount;
    }

    // Car-specific method
    public void honk() {
        System.out.println("Car honking: Beep beep!");
    }

    // Overriding the parent method
    @Override
    public void start() {
        System.out.println("Car engine starting");
    }
}

class PolyMotorcycle extends PolyVehicle {
    private boolean hasWindshield;

    public PolyMotorcycle(String name, String type, boolean hasWindshield) {
        super(name, type);  // Call parent constructor
        this.hasWindshield = hasWindshield;
    }

    public boolean hasWindshield() {
        return hasWindshield;
    }

    // Overriding the parent method
    @Override
    public void start() {
        System.out.println("Motorcycle engine kick-starting");
    }
}
