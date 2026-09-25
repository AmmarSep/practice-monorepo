package FrequentInterviewQuestions;

/**
 * This class demonstrates Abstraction in Java
 * 
 * Abstraction is the concept of hiding implementation details and showing only the functionality.
 * It focuses on what an object does instead of how it does it.
 * 
 * Java provides abstraction through:
 * 1. Abstract classes
 * 2. Interfaces
 */
public class AbstractionDemo {

    public static void main(String[] args) {
        System.out.println("===== Abstraction Demonstration =====");

        // 1. Abstraction using Abstract Classes
        demonstrateAbstractClass();

        // 2. Abstraction using Interfaces
        demonstrateInterface();

        // 3. Comparison between Abstract Class and Interface
        compareAbstractClassAndInterface();

        // 4. Real-world abstraction examples
        demonstrateRealWorldAbstraction();
    }

    /**
     * Demonstrates abstraction using abstract classes
     */
    private static void demonstrateAbstractClass() {
        System.out.println("\n1. Abstraction using Abstract Classes:\n");

        // Cannot instantiate abstract class
        // DemoShape shape = new DemoShape(); // Compilation error

        // Using concrete implementations of abstract class
        DemoShape circle = new DemoCircle("Red", 5.0);
        DemoShape rectangle = new DemoRectangle("Blue", 4.0, 6.0);

        // Calling abstract and concrete methods
        circle.display();  // Concrete method from abstract class
        System.out.println("Area: " + circle.calculateArea());  // Implemented abstract method
        circle.draw();  // Implemented abstract method

        rectangle.display();  // Concrete method from abstract class
        System.out.println("Area: " + rectangle.calculateArea());  // Implemented abstract method
        rectangle.draw();  // Implemented abstract method

        // Accessing non-abstract methods and properties
        circle.setColor("Yellow");  // Non-abstract method
        System.out.println("Updated circle color: " + circle.getColor());
    }

    /**
     * Demonstrates abstraction using interfaces
     */
    private static void demonstrateInterface() {
        System.out.println("\n2. Abstraction using Interfaces:\n");

        // Cannot instantiate interface
        // DemoDrawable drawable = new DemoDrawable(); // Compilation error

        // Using classes that implement the interface
        DemoDrawable circle = new DemoCircle("Green", 3.0);
        DemoDrawable rectangle = new DemoRectangle("Orange", 2.0, 5.0);
        DemoDrawable triangle = new DemoTriangle("Purple", 4.0, 3.0);

        // Polymorphic behavior through interface
        System.out.println("Drawing various shapes:");
        circle.draw();
        rectangle.draw();
        triangle.draw();

        // Using default method from interface (Java 8+)
        circle.printInfo();  // Default method

        // Using static method from interface (Java 8+)
        String drawingTip = DemoDrawable.getDrawingTip();
        System.out.println("Drawing tip: " + drawingTip);

        // Using constant defined in interface
        System.out.println("Drawing tool: " + DemoDrawable.DRAWING_TOOL);
    }

    /**
     * Compares Abstract Classes and Interfaces
     */
    private static void compareAbstractClassAndInterface() {
        System.out.println("\n3. Comparison between Abstract Class and Interface:\n");

        System.out.println("Abstract Class:");
        System.out.println("- Can have constructor");
        System.out.println("- Can have concrete methods with implementation");
        System.out.println("- Can have abstract methods (without implementation)");
        System.out.println("- Can have instance variables (both final and non-final)");
        System.out.println("- Can have all access modifiers (public, protected, private, default)");
        System.out.println("- Supports single inheritance (extends only one class)");
        System.out.println("- Can extend another class and implement interfaces");
        System.out.println("- Use when objects share type and common behavior implementation");

        System.out.println("\nInterface:");
        System.out.println("- Cannot have constructor");
        System.out.println("- Cannot have concrete methods (except default and static methods in Java 8+)");
        System.out.println("- All methods are implicitly abstract (except default/static)");
        System.out.println("- Can only have constants (public static final variables)");
        System.out.println("- All methods and variables are implicitly public");
        System.out.println("- Supports multiple inheritance (implements multiple interfaces)");
        System.out.println("- Can only extend other interfaces");
        System.out.println("- Use when objects share type but might have different behavior implementations");

        // Demonstration using a class that uses both
        DemoAdvancedRectangle advShape = new DemoAdvancedRectangle("Cyan", 10.0, 8.0);
        advShape.draw();  // From DemoDrawable interface
        advShape.rotate(45);  // From DemoRotatable interface
        advShape.display();  // From DemoShape abstract class
        System.out.println("Area: " + advShape.calculateArea());  // From DemoShape abstract class
    }

    /**
     * Demonstrates real-world abstraction examples
     */
    private static void demonstrateRealWorldAbstraction() {
        System.out.println("\n4. Real-World Abstraction Examples:\n");

        // Database abstraction example
        System.out.println("Database Abstraction Example:");
        DemoDatabase mySqlDb = new DemoMySQLDatabase();
        DemoDatabase mongoDb = new DemoMongoDBDatabase();

        // Using abstracted methods without knowing specific implementation
        mySqlDb.connect();
        mySqlDb.executeQuery("SELECT * FROM users");
        mySqlDb.close();

        mongoDb.connect();
        mongoDb.executeQuery("db.users.find({})");
        mongoDb.close();

        // Vehicle abstraction example
        System.out.println("\nVehicle Abstraction Example:");
        DemoVehicle car = new DemoCar("Toyota");
        DemoVehicle bike = new DemoBicycle("Trek");

        car.start();
        car.accelerate();
        car.brake();
        car.stop();

        bike.start();
        bike.accelerate();
        bike.brake();
        bike.stop();
    }

    // Abstract Class Example
    static abstract class DemoShape {
        private String color;

        // Constructor in abstract class
        public DemoShape(String color) {
            this.color = color;
        }

        // Concrete method (with implementation)
        public void display() {
            System.out.println("This is a " + color + " shape");
        }

        // Getter and setter
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        // Abstract methods (without implementation)
        public abstract double calculateArea();
        public abstract void draw();
    }

    // Interface Example
    interface DemoDrawable {
        // Constant (implicitly public static final)
        String DRAWING_TOOL = "Pencil";

        // Abstract method (implicitly public abstract)
        void draw();

        // Default method with implementation (Java 8+)
        default void printInfo() {
            System.out.println("I am a drawable object, drawn with " + DRAWING_TOOL);
        }

        // Static method (Java 8+)
        static String getDrawingTip() {
            return "Use light strokes for better control";
        }
    }

    // Another interface for multiple inheritance demonstration
    interface DemoRotatable {
        void rotate(double degrees);

        default void resetRotation() {
            System.out.println("Resetting rotation to 0 degrees");
        }
    }

    // Concrete implementation of DemoShape abstract class
    static class DemoCircle extends DemoShape implements DemoDrawable {
        private double radius;

        public DemoCircle(String color, double radius) {
            super(color);  // Call to abstract class constructor
            this.radius = radius;
        }

        // Implementation of abstract method from DemoShape
        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        // Implementation of abstract method from DemoShape
        // Also implements the interface method
        @Override
        public void draw() {
            System.out.println("Drawing a " + getColor() + " circle with radius " + radius);
        }
    }

    // Another concrete implementation of DemoShape
    static class DemoRectangle extends DemoShape implements DemoDrawable {
        private double width;
        private double height;

        public DemoRectangle(String color, double width, double height) {
            super(color);
            this.width = width;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return width * height;
        }

        @Override
        public void draw() {
            System.out.println("Drawing a " + getColor() + " rectangle with width " + 
                             width + " and height " + height);
        }
    }

    // Class implementing just the interface (not extending abstract class)
    static class DemoTriangle implements DemoDrawable {
        private String color;
        private double base;
        private double height;

        public DemoTriangle(String color, double base, double height) {
            this.color = color;
            this.base = base;
            this.height = height;
        }

        @Override
        public void draw() {
            System.out.println("Drawing a " + color + " triangle with base " + 
                             base + " and height " + height);
        }
    }

    // Class extending abstract class and implementing multiple interfaces
    static class DemoAdvancedRectangle extends DemoShape implements DemoDrawable, DemoRotatable {
        private double width;
        private double height;
        private double currentRotation = 0;

        public DemoAdvancedRectangle(String color, double width, double height) {
            super(color);
            this.width = width;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return width * height;
        }

        @Override
        public void draw() {
            System.out.println("Drawing an advanced " + getColor() + " rectangle with width " + 
                             width + " and height " + height + " at " + currentRotation + " degrees");
        }

        @Override
        public void rotate(double degrees) {
            this.currentRotation = degrees;
            System.out.println("Rotated to " + degrees + " degrees");
        }
    }

    // Real-world abstraction example: Database
    static abstract class DemoDatabase {
        public abstract void connect();
        public abstract void executeQuery(String query);
        public abstract void close();

        // Common method for all databases
        public void printStatus() {
            System.out.println("Database status: OK");
        }
    }

    static class DemoMySQLDatabase extends DemoDatabase {
        @Override
        public void connect() {
            System.out.println("Connecting to MySQL database...");
        }

        @Override
        public void executeQuery(String query) {
            System.out.println("Executing MySQL query: " + query);
        }

        @Override
        public void close() {
            System.out.println("Closing MySQL connection");
        }
    }

    static class DemoMongoDBDatabase extends DemoDatabase {
        @Override
        public void connect() {
            System.out.println("Connecting to MongoDB database...");
        }

        @Override
        public void executeQuery(String query) {
            System.out.println("Executing MongoDB query: " + query);
        }

        @Override
        public void close() {
            System.out.println("Closing MongoDB connection");
        }
    }

    // Real-world abstraction example: Vehicle
    static abstract class DemoVehicle {
        protected String brand;

        public DemoVehicle(String brand) {
            this.brand = brand;
        }

        // Abstract methods
        public abstract void start();
        public abstract void accelerate();
        public abstract void brake();
        public abstract void stop();
    }

    static class DemoCar extends DemoVehicle {
        public DemoCar(String brand) {
            super(brand);
        }

        @Override
        public void start() {
            System.out.println(brand + " car: Starting engine");
        }

        @Override
        public void accelerate() {
            System.out.println(brand + " car: Pressing gas pedal");
        }

        @Override
        public void brake() {
            System.out.println(brand + " car: Applying brakes");
        }

        @Override
        public void stop() {
            System.out.println(brand + " car: Stopping engine");
        }
    }

    static class DemoBicycle extends DemoVehicle {
        public DemoBicycle(String brand) {
            super(brand);
        }

        @Override
        public void start() {
            System.out.println(brand + " bicycle: Starting to pedal");
        }

        @Override
        public void accelerate() {
            System.out.println(brand + " bicycle: Pedaling faster");
        }

        @Override
        public void brake() {
            System.out.println(brand + " bicycle: Squeezing brake levers");
        }

        @Override
        public void stop() {
            System.out.println(brand + " bicycle: Stopping pedaling");
        }
    }
}
