package FrequentInterviewQuestions;

/**
 * This class demonstrates Inheritance in Java
 * 
 * Inheritance is one of the core principles of OOP where a class (subclass)
 * inherits attributes and methods from another class (superclass).
 * 
 * Types of Inheritance in Java:
 * 1. Single Inheritance - One class extends one class
 * 2. Multilevel Inheritance - Class A is extended by B, and B is extended by C
 * 3. Hierarchical Inheritance - One class is extended by multiple classes
 * 4. Multiple Inheritance - Not directly supported for classes (only through interfaces)
 */
public class InheritanceDemo {

    public static void main(String[] args) {
        System.out.println("===== Inheritance Demonstration =====");

        // 1. Single Inheritance
        demonstrateSingleInheritance();

        // 2. Multilevel Inheritance
        demonstrateMultilevelInheritance();

        // 3. Hierarchical Inheritance
        demonstrateHierarchicalInheritance();

        // 4. Multiple Inheritance (through interfaces)
        demonstrateMultipleInheritance();

        // 5. Special Inheritance Features
        demonstrateSpecialInheritanceFeatures();
    }

    /**
     * Demonstrates Single Inheritance
     * A class extends one class (one level of inheritance)
     */
    private static void demonstrateSingleInheritance() {
        System.out.println("\n1. Single Inheritance:\n");

        // Create a parent class object
        Vehicle vehicle = new Vehicle("Generic Vehicle", 0);
        vehicle.displayInfo();
        vehicle.start();

        // Create a child class object
        Car car = new Car("Toyota Camry", 4, 4);
        car.displayInfo();  // Inherited method
        car.start();        // Inherited method
        car.drive();        // Car-specific method

        System.out.println("Car wheel count: " + car.getWheelCount());
        System.out.println("Car door count: " + car.getDoorCount());
    }

    /**
     * Demonstrates Multilevel Inheritance
     * A class extends another class which extends another class (chain inheritance)
     */
    private static void demonstrateMultilevelInheritance() {
        System.out.println("\n2. Multilevel Inheritance:\n");

        // Grandparent class
        ElectronicDevice device = new ElectronicDevice("Generic Device", "Unknown");
        device.turnOn();
        device.displayInfo();

        // Parent class
        Computer computer = new Computer("Generic Computer", "Dell", 8);
        computer.turnOn();       // From ElectronicDevice
        computer.displayInfo();   // From ElectronicDevice
        computer.runProgram("Notepad");  // Computer-specific

        // Child class
        Laptop laptop = new Laptop("MacBook Pro", "Apple", 16, 13.3);
        laptop.turnOn();        // From ElectronicDevice
        laptop.displayInfo();    // From ElectronicDevice
        laptop.runProgram("Photoshop");  // From Computer
        laptop.fold();          // Laptop-specific

        System.out.println("Laptop has " + laptop.getRamSize() + "GB RAM and " + 
                           laptop.getScreenSize() + " inch screen");
    }

    /**
     * Demonstrates Hierarchical Inheritance
     * Multiple classes extend the same class
     */
    private static void demonstrateHierarchicalInheritance() {
        System.out.println("\n3. Hierarchical Inheritance:\n");

        // Parent class
        Shape shape = new Shape();
        shape.draw();

        // Child class 1
        Circle circle = new Circle(5.0);
        circle.draw();  // Overridden method
        System.out.println("Circle area: " + circle.calculateArea());

        // Child class 2
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        rectangle.draw();  // Overridden method
        System.out.println("Rectangle area: " + rectangle.calculateArea());

        // Child class 3
        Triangle triangle = new Triangle(3.0, 4.0);
        triangle.draw();  // Overridden method
        System.out.println("Triangle area: " + triangle.calculateArea());
    }

    /**
     * Demonstrates Multiple Inheritance through interfaces
     * Java doesn't support multiple inheritance for classes directly,
     * but it can be achieved through interfaces
     */
    private static void demonstrateMultipleInheritance() {
        System.out.println("\n4. Multiple Inheritance (through interfaces):\n");

        // Class implementing multiple interfaces
        SmartPhone phone = new SmartPhone();

        // Calling methods from different interfaces
        phone.call("123-456-7890");  // From Phone interface
        phone.browse("https://www.example.com");  // From WebBrowser interface
        phone.takePhoto();  // From Camera interface
        phone.playMusic("MySong.mp3");  // From MusicPlayer interface
    }

    /**
     * Demonstrates special inheritance features like:
     * - super keyword
     * - constructor chaining
     * - method overriding
     * - final classes/methods
     */
    private static void demonstrateSpecialInheritanceFeatures() {
        System.out.println("\n5. Special Inheritance Features:\n");

        // Constructor chaining and super keyword
        System.out.println("Constructor chaining with super keyword:");
        SportsCar sportsCar = new SportsCar("Ferrari", 4, 2, 320);
        sportsCar.displayInfo();  // Overridden method

        // Method overriding and super to call parent method
        System.out.println("\nMethod overriding and super.method() call:");
        sportsCar.accelerate();

        // Final methods cannot be overridden
        sportsCar.serviceCar();  // This calls Car's final method

        // Demonstrating instanceof operator
        System.out.println("\nUsing instanceof operator:");
        System.out.println("sportsCar instanceof SportsCar: " + (sportsCar instanceof SportsCar));  // true
        System.out.println("sportsCar instanceof Car: " + (sportsCar instanceof Car));  // true
        System.out.println("sportsCar instanceof Vehicle: " + (sportsCar instanceof Vehicle));  // true
    }
}

// Classes for Single Inheritance demonstration
class Vehicle {
    private String name;
    private int wheelCount;

    public Vehicle(String name, int wheelCount) {
        this.name = name;
        this.wheelCount = wheelCount;
    }

    public String getName() {
        return name;
    }

    public int getWheelCount() {
        return wheelCount;
    }

    public void start() {
        System.out.println(name + " is starting");
    }

    public void stop() {
        System.out.println(name + " is stopping");
    }

    public void displayInfo() {
        System.out.println("Vehicle: " + name + " with " + wheelCount + " wheels");
    }
}

class Car extends Vehicle {  // Car inherits from Vehicle
    private int doorCount;

    public Car(String name, int wheelCount, int doorCount) {
        super(name, wheelCount);  // Call parent constructor
        this.doorCount = doorCount;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public void drive() {
        System.out.println(getName() + " is driving");
    }

    // Final method - cannot be overridden by subclasses
    public final void serviceCar() {
        System.out.println("Standard car service protocol");
    }
}

// Classes for Multilevel Inheritance demonstration
class ElectronicDevice {
    private String name;
    private String brand;

    public ElectronicDevice(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public void turnOn() {
        System.out.println(name + " is turning on");
    }

    public void turnOff() {
        System.out.println(name + " is turning off");
    }

    public void displayInfo() {
        System.out.println("Device: " + name + " by " + brand);
    }
}

class Computer extends ElectronicDevice {  // Computer inherits from ElectronicDevice
    private int ramSize;  // in GB

    public Computer(String name, String brand, int ramSize) {
        super(name, brand);  // Call parent constructor
        this.ramSize = ramSize;
    }

    public int getRamSize() {
        return ramSize;
    }

    public void runProgram(String program) {
        System.out.println("Running " + program + " on computer");
    }
}

class Laptop extends Computer {  // Laptop inherits from Computer, which inherits from ElectronicDevice
    private double screenSize;  // in inches

    public Laptop(String name, String brand, int ramSize, double screenSize) {
        super(name, brand, ramSize);  // Call parent constructor
        this.screenSize = screenSize;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void fold() {
        System.out.println("Folding the laptop");
    }
}

// Classes for Hierarchical Inheritance demonstration
class Shape {
    public void draw() {
        System.out.println("Drawing a shape");
    }

    public double calculateArea() {
        return 0.0;  // Default implementation
    }
}

class Circle extends Shape {  // Circle inherits from Shape
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {  // Rectangle inherits from Shape
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with width " + width + " and height " + height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Triangle extends Shape {  // Triangle inherits from Shape
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a triangle with base " + base + " and height " + height);
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

// Classes and interfaces for Multiple Inheritance demonstration
interface Phone {
    void call(String number);
    void receiveCall();
}

interface WebBrowser {
    void browse(String url);
    void bookmark(String url);
}

interface Camera {
    void takePhoto();
    void recordVideo();
}

interface MusicPlayer {
    void playMusic(String song);
    void stopMusic();
}

// Class implementing multiple interfaces
class SmartPhone implements Phone, WebBrowser, Camera, MusicPlayer {
    @Override
    public void call(String number) {
        System.out.println("Calling " + number);
    }

    @Override
    public void receiveCall() {
        System.out.println("Receiving call");
    }

    @Override
    public void browse(String url) {
        System.out.println("Browsing " + url);
    }

    @Override
    public void bookmark(String url) {
        System.out.println("Bookmarking " + url);
    }

    @Override
    public void takePhoto() {
        System.out.println("Taking a photo");
    }

    @Override
    public void recordVideo() {
        System.out.println("Recording a video");
    }

    @Override
    public void playMusic(String song) {
        System.out.println("Playing " + song);
    }

    @Override
    public void stopMusic() {
        System.out.println("Stopping music");
    }
}

// Class for special inheritance features demonstration
class SportsCar extends Car {
    private int topSpeed;

    public SportsCar(String name, int wheelCount, int doorCount, int topSpeed) {
        super(name, wheelCount, doorCount);  // Call parent constructor
        this.topSpeed = topSpeed;
        System.out.println("SportsCar constructor called");
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    // Overriding method from parent class
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method first
        System.out.println("This is a sports car with top speed of " + topSpeed + " km/h");
    }

    // Demonstrating super to call parent methods
    public void accelerate() {
        super.start();  // Call parent's start method
        System.out.println("Sports car accelerating to " + topSpeed + " km/h");
    }

    // Cannot override final method from parent
    // @Override
    // public void serviceCar() { } // This would cause compilation error
}
