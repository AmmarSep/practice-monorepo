package JavaSimplePrograms;

/**
 * This class demonstrates the differences between abstract classes and interfaces in Java
 */
public class AbstractClassVsInterfaceDemo {

    public static void main(String[] args) {
        System.out.println("===== Abstract Class vs Interface Demonstration =====\n");

        // 1. Key differences explanation
        System.out.println("1. Key Differences Between Abstract Classes and Interfaces\n");
        explainKeyDifferences();

        // 2. Abstract class implementation example
        System.out.println("\n2. Abstract Class Implementation Example\n");
        demonstrateAbstractClass();

        // 3. Interface implementation example
        System.out.println("\n3. Interface Implementation Example\n");
        demonstrateInterface();

        // 4. When to use abstract class vs interface
        System.out.println("\n4. When to Use Abstract Class vs Interface\n");
        explainWhenToUse();

        // 5. Multiple inheritance with interfaces
        System.out.println("\n5. Multiple Inheritance with Interfaces\n");
        demonstrateMultipleInheritance();

        // 6. Java 8+ interface features
        System.out.println("\n6. Java 8+ Interface Features\n");
        demonstrateJava8InterfaceFeatures();

        // 7. Practical example combining both
        System.out.println("\n7. Practical Example: Combining Abstract Classes and Interfaces\n");
        demonstrateCombinedExample();
    }

    /**
     * Explains the key differences between abstract classes and interfaces
     */
    private static void explainKeyDifferences() {
        System.out.println("Abstract Classes:");
        System.out.println("1. Can have both abstract and concrete methods");
        System.out.println("2. Can have constructors");
        System.out.println("3. Can have instance variables (fields)");
        System.out.println("4. Support for private, protected members");
        System.out.println("5. A class can extend only one abstract class (single inheritance)");
        System.out.println("6. Methods are public, protected, or private");
        System.out.println("7. Use when classes sharing common functionality");
        System.out.println("8. Typically used to establish 'is-a' relationships");

        System.out.println("\nInterfaces:");
        System.out.println("1. Before Java 8: Only abstract methods (no implementations)");
        System.out.println("   Java 8+: Can have default and static methods with implementations");
        System.out.println("   Java 9+: Can have private methods");
        System.out.println("2. Cannot have constructors");
        System.out.println("3. Can only have constant variables (public static final)");
        System.out.println("4. All members are implicitly public");
        System.out.println("5. A class can implement multiple interfaces (multiple inheritance of type)");
        System.out.println("6. Methods are implicitly public and abstract");
        System.out.println("7. Use when different classes need to implement common behavior");
        System.out.println("8. Typically used to establish 'can-do' relationships");
    }

    /**
     * Demonstrates abstract class implementation
     */
    private static void demonstrateAbstractClass() {
        // Create objects of concrete subclasses
        Rectangle rectangle = new Rectangle(5, 3);
        Circle circle = new Circle(4);

        // Use abstract class methods
        System.out.println("Rectangle:");
        rectangle.displayInfo();
        System.out.println("Area: " + rectangle.calculateArea());
        System.out.println("Perimeter: " + rectangle.calculatePerimeter());
        rectangle.displayCommonInfo();

        System.out.println("\nCircle:");
        circle.displayInfo();
        System.out.println("Area: " + circle.calculateArea());
        System.out.println("Perimeter: " + circle.calculatePerimeter());
        circle.displayCommonInfo();

        // Demonstrate constructor and instance variables
        System.out.println("\nAccessing protected field from abstract class:");
        System.out.println("Rectangle color: " + rectangle.getColor());
        System.out.println("Circle color: " + circle.getColor());

        // Changing the color
        rectangle.setColor("Blue");
        System.out.println("Rectangle color after change: " + rectangle.getColor());
    }

    /**
     * Demonstrates interface implementation
     */
    private static void demonstrateInterface() {
        // Create objects of classes implementing the interface
        Car car = new Car("Toyota", "Camry");
        Bicycle bicycle = new Bicycle("Mountain Bike", 21);

        // Use interface methods
        System.out.println("Car:");
        car.start();
        car.stop();
        car.accelerate(60);
        car.brake();

        System.out.println("\nBicycle:");
        bicycle.start();
        bicycle.stop();
        bicycle.accelerate(15);
        bicycle.brake();

        // Accessing constant from interface
        System.out.println("\nMaximum legal speed (from interface): " + Vehicle.MAX_LEGAL_SPEED + " km/h");
    }

    /**
     * Explains when to use abstract class vs interface
     */
    private static void explainWhenToUse() {
        System.out.println("When to use Abstract Classes:");
        System.out.println("1. When creating a base class that will have shared code among related classes");
        System.out.println("2. When you need to declare non-public members (protected, private)");
        System.out.println("3. When you need to share code among closely related classes");
        System.out.println("4. When you need to use instance variables (state)");
        System.out.println("5. When you want to provide default implementation of some methods but");
        System.out.println("   force subclasses to implement others");
        System.out.println("6. When you're working with a hierarchy where 'is-a' relationship applies");

        System.out.println("\nWhen to use Interfaces:");
        System.out.println("1. When different unrelated classes would implement the interface");
        System.out.println("2. When you want to specify the behavior of a class without");
        System.out.println("   concern for its implementation details");
        System.out.println("3. When you want a class to support multiple behaviors (multiple inheritance)");
        System.out.println("4. When you want to define a contract for a set of classes to implement");
        System.out.println("5. When the focus is on the capabilities of a class rather than its identity");
        System.out.println("6. When working with classes that need to exhibit a 'can-do' relationship");
    }

    /**
     * Demonstrates multiple inheritance with interfaces
     */
    private static void demonstrateMultipleInheritance() {
        // Create an object that implements multiple interfaces
        Smartphone smartphone = new Smartphone("iPhone", "14 Pro");

        System.out.println("Smartphone implementing multiple interfaces:");

        // Use methods from different interfaces
        System.out.println("\nAs a Phone:");
        smartphone.makeCall("123-456-7890");
        smartphone.receiveCall("987-654-3210");

        System.out.println("\nAs a Camera:");
        smartphone.takePhoto();
        smartphone.recordVideo();

        System.out.println("\nAs a MusicPlayer:");
        smartphone.playMusic("Favorite Song");
        smartphone.adjustVolume(8);

        System.out.println("\nAs a WebBrowser:");
        smartphone.browseWeb("www.example.com");
        smartphone.downloadFile("document.pdf");

        // This would not be possible with abstract classes (multiple inheritance)
        System.out.println("\nMultiple inheritance is achieved through interfaces,");
        System.out.println("which would not be possible with abstract classes.");
    }

    /**
     * Demonstrates Java 8+ interface features
     */
    private static void demonstrateJava8InterfaceFeatures() {
        // Create objects that implement the modern interface
        BasicCalculator basicCalc = new BasicCalculator();
        ScientificCalculator scientificCalc = new ScientificCalculator();

        System.out.println("Basic Calculator:");
        System.out.println("5 + 3 = " + basicCalc.add(5, 3));
        System.out.println("5 - 3 = " + basicCalc.subtract(5, 3));
        System.out.println("5 * 3 = " + basicCalc.multiply(5, 3));
        System.out.println("6 / 3 = " + basicCalc.divide(6, 3));
        System.out.println("Square root of 16 = " + basicCalc.squareRoot(16)); // Using default method

        System.out.println("\nScientific Calculator:");
        System.out.println("5 + 3 = " + scientificCalc.add(5, 3));
        System.out.println("5 - 3 = " + scientificCalc.subtract(5, 3));
        System.out.println("5 * 3 = " + scientificCalc.multiply(5, 3));
        System.out.println("6 / 3 = " + scientificCalc.divide(6, 3));
        System.out.println("Square root of 16 = " + scientificCalc.squareRoot(16)); // Overridden default method
        System.out.println("Sine of 30 degrees = " + scientificCalc.sin(30));
        System.out.println("Cosine of 60 degrees = " + scientificCalc.cos(60));

        // Using static method from interface
        System.out.println("\nUsing static method from Calculator interface:");
        System.out.println("Is 7 even? " + Calculator.isEven(7));
        System.out.println("Is 8 even? " + Calculator.isEven(8));
    }

    /**
     * Demonstrates a practical example combining abstract classes and interfaces
     */
    private static void demonstrateCombinedExample() {
        // Create objects of different database connections
        MySQLDatabase mysql = new MySQLDatabase("localhost", 3306, "mydb", "user", "pass");
        PostgreSQLDatabase postgres = new PostgreSQLDatabase("db.example.com", 5432, "postgres", "admin", "secure");

        System.out.println("Working with MySQL:");
        mysql.connect();
        mysql.executeQuery("SELECT * FROM users");
        mysql.exportData("users.csv", "CSV");
        mysql.importData("new_users.csv", "CSV");
        mysql.backup("mysql_backup.sql");
        mysql.disconnect();

        System.out.println("\nWorking with PostgreSQL:");
        postgres.connect();
        postgres.executeQuery("SELECT * FROM products");
        postgres.exportData("products.json", "JSON");
        postgres.importData("new_products.json", "JSON");
        postgres.backup("postgres_backup.dump");
        postgres.disconnect();
    }

    //===================================
    // Abstract Class Example
    //===================================

    /**
     * Abstract class Shape
     */
    abstract static class Shape {
        // Instance variable (state)
        protected String color;

        // Constructor in abstract class
        public Shape(String color) {
            this.color = color;
        }

        // Abstract methods (must be implemented by subclasses)
        public abstract double calculateArea();
        public abstract double calculatePerimeter();
        public abstract void displayInfo();

        // Concrete method with implementation
        public void displayCommonInfo() {
            System.out.println("This is a shape with color: " + color);
        }

        // Getter and setter for the color field
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    /**
     * Rectangle class extending the abstract Shape class
     */
    static class Rectangle extends Shape {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            super("Red"); // Call to abstract class constructor
            this.width = width;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return width * height;
        }

        @Override
        public double calculatePerimeter() {
            return 2 * (width + height);
        }

        @Override
        public void displayInfo() {
            System.out.println("Rectangle with width: " + width + ", height: " + height + ", color: " + color);
        }
    }

    /**
     * Circle class extending the abstract Shape class
     */
    static class Circle extends Shape {
        private double radius;

        public Circle(double radius) {
            super("Green"); // Call to abstract class constructor
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public double calculatePerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        public void displayInfo() {
            System.out.println("Circle with radius: " + radius + ", color: " + color);
        }
    }

    //===================================
    // Interface Example
    //===================================

    /**
     * Vehicle interface
     */
    interface Vehicle {
        // Constant (implicitly public, static, final)
        int MAX_LEGAL_SPEED = 120;

        // Abstract methods (implicitly public and abstract)
        void start();
        void stop();
        void accelerate(int speed);
        void brake();
    }

    /**
     * Car class implementing Vehicle interface
     */
    static class Car implements Vehicle {
        private String make;
        private String model;
        private boolean isRunning;
        private int currentSpeed;

        public Car(String make, String model) {
            this.make = make;
            this.model = model;
            this.isRunning = false;
            this.currentSpeed = 0;
        }

        @Override
        public void start() {
            isRunning = true;
            System.out.println(make + " " + model + " engine started.");
        }

        @Override
        public void stop() {
            isRunning = false;
            currentSpeed = 0;
            System.out.println(make + " " + model + " engine stopped.");
        }

        @Override
        public void accelerate(int speed) {
            if (isRunning) {
                currentSpeed = speed;
                System.out.println(make + " " + model + " accelerating to " + currentSpeed + " km/h.");
            } else {
                System.out.println("Cannot accelerate. Engine is not running.");
            }
        }

        @Override
        public void brake() {
            if (currentSpeed > 0) {
                currentSpeed = 0;
                System.out.println(make + " " + model + " braking. Speed reduced to 0 km/h.");
            } else {
                System.out.println(make + " " + model + " is already stopped.");
            }
        }
    }

    /**
     * Bicycle class implementing Vehicle interface
     */
    static class Bicycle implements Vehicle {
        private String type;
        private int gears;
        private boolean isMoving;
        private int currentSpeed;

        public Bicycle(String type, int gears) {
            this.type = type;
            this.gears = gears;
            this.isMoving = false;
            this.currentSpeed = 0;
        }

        @Override
        public void start() {
            isMoving = true;
            System.out.println(type + " with " + gears + " gears started moving.");
        }

        @Override
        public void stop() {
            isMoving = false;
            currentSpeed = 0;
            System.out.println(type + " stopped moving.");
        }

        @Override
        public void accelerate(int speed) {
            if (isMoving) {
                currentSpeed = Math.min(speed, 30); // Bicycles have natural speed limits
                System.out.println(type + " accelerating to " + currentSpeed + " km/h.");
            } else {
                System.out.println("Cannot accelerate. Bicycle is not moving.");
            }
        }

        @Override
        public void brake() {
            if (currentSpeed > 0) {
                currentSpeed = 0;
                System.out.println(type + " braking. Speed reduced to 0 km/h.");
            } else {
                System.out.println(type + " is already stopped.");
            }
        }
    }

    //===================================
    // Multiple Inheritance with Interfaces
    //===================================

    interface Phone {
        void makeCall(String number);
        void receiveCall(String number);
    }

    interface Camera {
        void takePhoto();
        void recordVideo();
    }

    interface MusicPlayer {
        void playMusic(String song);
        void adjustVolume(int level);
    }

    interface WebBrowser {
        void browseWeb(String url);
        void downloadFile(String filename);
    }

    /**
     * Smartphone class implementing multiple interfaces
     */
    static class Smartphone implements Phone, Camera, MusicPlayer, WebBrowser {
        private String brand;
        private String model;

        public Smartphone(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }

        // Phone interface methods
        @Override
        public void makeCall(String number) {
            System.out.println(brand + " " + model + " is calling " + number);
        }

        @Override
        public void receiveCall(String number) {
            System.out.println(brand + " " + model + " is receiving call from " + number);
        }

        // Camera interface methods
        @Override
        public void takePhoto() {
            System.out.println(brand + " " + model + " is taking a photo");
        }

        @Override
        public void recordVideo() {
            System.out.println(brand + " " + model + " is recording a video");
        }

        // MusicPlayer interface methods
        @Override
        public void playMusic(String song) {
            System.out.println(brand + " " + model + " is playing " + song);
        }

        @Override
        public void adjustVolume(int level) {
            System.out.println(brand + " " + model + " volume adjusted to " + level);
        }

        // WebBrowser interface methods
        @Override
        public void browseWeb(String url) {
            System.out.println(brand + " " + model + " is browsing " + url);
        }

        @Override
        public void downloadFile(String filename) {
            System.out.println(brand + " " + model + " is downloading " + filename);
        }
    }

    //===================================
    // Java 8+ Interface Features
    //===================================

    /**
     * Modern Calculator interface with default and static methods (Java 8+)
     */
    interface Calculator {
        // Abstract methods
        double add(double a, double b);
        double subtract(double a, double b);
        double multiply(double a, double b);
        double divide(double a, double b);

        // Default method with implementation
        default double squareRoot(double a) {
            return Math.sqrt(a);
        }

        // Static method with implementation
        static boolean isEven(int number) {
            return number % 2 == 0;
        }
    }

    /**
     * BasicCalculator implementing the Calculator interface
     */
    static class BasicCalculator implements Calculator {
        @Override
        public double add(double a, double b) {
            return a + b;
        }

        @Override
        public double subtract(double a, double b) {
            return a - b;
        }

        @Override
        public double multiply(double a, double b) {
            return a * b;
        }

        @Override
        public double divide(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        }

        // Using the default implementation of squareRoot from the interface
    }

    /**
     * ScientificCalculator implementing the Calculator interface with extended functionality
     */
    static class ScientificCalculator implements Calculator {
        @Override
        public double add(double a, double b) {
            return a + b;
        }

        @Override
        public double subtract(double a, double b) {
            return a - b;
        }

        @Override
        public double multiply(double a, double b) {
            return a * b;
        }

        @Override
        public double divide(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        }

        // Override the default method
        @Override
        public double squareRoot(double a) {
            if (a < 0) {
                throw new IllegalArgumentException("Cannot calculate square root of negative number");
            }
            return Math.sqrt(a);
        }

        // Additional methods specific to scientific calculator
        public double sin(double degrees) {
            double radians = Math.toRadians(degrees);
            return Math.sin(radians);
        }

        public double cos(double degrees) {
            double radians = Math.toRadians(degrees);
            return Math.cos(radians);
        }
    }

    //===================================
    // Combined Example
    //===================================

    /**
     * Abstract Database class
     */
    abstract static class Database {
        protected String host;
        protected int port;
        protected String databaseName;
        protected String username;
        protected String password;
        protected boolean isConnected;

        public Database(String host, int port, String databaseName, String username, String password) {
            this.host = host;
            this.port = port;
            this.databaseName = databaseName;
            this.username = username;
            this.password = password;
            this.isConnected = false;
        }

        // Abstract methods
        public abstract void connect();
        public abstract void disconnect();
        public abstract void executeQuery(String query);

        // Concrete method
        public void displayConnectionInfo() {
            System.out.println("Connection to " + databaseName + " at " + host + ":" + port);
            System.out.println("Status: " + (isConnected ? "Connected" : "Disconnected"));
        }

        // Concrete method with protected access
        protected void logActivity(String activity) {
            System.out.println("[LOG] " + activity + " - " + java.time.LocalDateTime.now());
        }
    }

    /**
     * DataImportExport interface
     */
    interface DataImportExport {
        void exportData(String filename, String format);
        void importData(String filename, String format);

        // Default method
        default void validateFormat(String format) {
            if (!format.equals("CSV") && !format.equals("JSON") && !format.equals("XML")) {
                throw new IllegalArgumentException("Unsupported format: " + format);
            }
            System.out.println("Format " + format + " is valid.");
        }
    }

    /**
     * Backup interface
     */
    interface Backup {
        void backup(String filename);

        // Default method
        default void verifyBackup(String filename) {
            System.out.println("Verifying backup: " + filename);
            System.out.println("Backup verification complete.");
        }
    }

    /**
     * MySQLDatabase class extending Database and implementing interfaces
     */
    static class MySQLDatabase extends Database implements DataImportExport, Backup {

        public MySQLDatabase(String host, int port, String databaseName, String username, String password) {
            super(host, port, databaseName, username, password);
        }

        @Override
        public void connect() {
            System.out.println("Connecting to MySQL database: " + databaseName);
            // Connection logic...
            isConnected = true;
            logActivity("Connected to MySQL database");
        }

        @Override
        public void disconnect() {
            System.out.println("Disconnecting from MySQL database: " + databaseName);
            // Disconnection logic...
            isConnected = false;
            logActivity("Disconnected from MySQL database");
        }

        @Override
        public void executeQuery(String query) {
            if (!isConnected) {
                System.out.println("Cannot execute query. Not connected to database.");
                return;
            }
            System.out.println("Executing MySQL query: " + query);
            // Query execution logic...
            logActivity("Executed query on MySQL database");
        }

        @Override
        public void exportData(String filename, String format) {
            validateFormat(format); // Using default method from interface
            System.out.println("Exporting MySQL data to " + filename + " in " + format + " format");
            // Export logic...
            logActivity("Exported data to " + filename);
        }

        @Override
        public void importData(String filename, String format) {
            validateFormat(format); // Using default method from interface
            System.out.println("Importing data from " + filename + " in " + format + " format to MySQL");
            // Import logic...
            logActivity("Imported data from " + filename);
        }

        @Override
        public void backup(String filename) {
            System.out.println("Creating MySQL backup to file: " + filename);
            // Backup logic...
            verifyBackup(filename); // Using default method from interface
            logActivity("Created backup: " + filename);
        }
    }

    /**
     * PostgreSQLDatabase class extending Database and implementing interfaces
     */
    static class PostgreSQLDatabase extends Database implements DataImportExport, Backup {

        public PostgreSQLDatabase(String host, int port, String databaseName, String username, String password) {
            super(host, port, databaseName, username, password);
        }

        @Override
        public void connect() {
            System.out.println("Connecting to PostgreSQL database: " + databaseName);
            // Connection logic...
            isConnected = true;
            logActivity("Connected to PostgreSQL database");
        }

        @Override
        public void disconnect() {
            System.out.println("Disconnecting from PostgreSQL database: " + databaseName);
            // Disconnection logic...
            isConnected = false;
            logActivity("Disconnected from PostgreSQL database");
        }

        @Override
        public void executeQuery(String query) {
            if (!isConnected) {
                System.out.println("Cannot execute query. Not connected to database.");
                return;
            }
            System.out.println("Executing PostgreSQL query: " + query);
            // Query execution logic...
            logActivity("Executed query on PostgreSQL database");
        }

        @Override
        public void exportData(String filename, String format) {
            validateFormat(format); // Using default method from interface
            System.out.println("Exporting PostgreSQL data to " + filename + " in " + format + " format");
            // Export logic...
            logActivity("Exported data to " + filename);
        }

        @Override
        public void importData(String filename, String format) {
            validateFormat(format); // Using default method from interface
            System.out.println("Importing data from " + filename + " in " + format + " format to PostgreSQL");
            // Import logic...
            logActivity("Imported data from " + filename);
        }

        @Override
        public void backup(String filename) {
            System.out.println("Creating PostgreSQL backup to file: " + filename);
            // Backup logic...
            verifyBackup(filename); // Using default method from interface
            logActivity("Created backup: " + filename);
        }
    }
}
