package JavaSimplePrograms;

/**
 * This class demonstrates abstraction in Java
 * Abstraction is the concept of hiding implementation details and showing only functionality to the user
 */
public class AbstractionDemo {

    public static void main(String[] args) {
        System.out.println("===== Abstraction Demonstration =====\n");

        // Abstract class demonstration
        System.out.println("--- Abstract Class Example ---\n");

        // Cannot instantiate an abstract class
        // Shape shape = new Shape(); // This would cause a compilation error

        // Creating objects of concrete subclasses
        Shape1 circle = new Circle2(5.0);
        Shape1 rectangle = new Rectangle2(4.0, 6.0);

        // Using abstract methods
        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Rectangle area: " + rectangle.calculateArea());

        // Using concrete methods from abstract class
        circle.display();
        rectangle.display();

        // Using abstract methods with custom implementations
        circle.draw();
        rectangle.draw();

        // Interface demonstration
        System.out.println("\n--- Interface Example ---\n");

        // Creating objects of classes implementing the interface
        ElectricDevice television = new Television();
        ElectricDevice refrigerator = new Refrigerator();

        // Using interface methods
        television.turnOn();
        television.turnOff();

        refrigerator.turnOn();
        refrigerator.turnOff();

        // Multiple interfaces implementation
        System.out.println("\n--- Multiple Interfaces Example ---\n");

        SmartPhone phone = new SmartPhone();
        phone.call("123-456-7890");
        phone.sendMessage("123-456-7890", "Hello there!");
        phone.browseInternet("www.example.com");
        phone.takePhoto();

        // Interfaces vs Abstract Classes
        System.out.println("\n--- Abstract Class vs Interface ---\n");

        // Abstract class with instance variables
        System.out.println("Animal examples:");
        Animal1 dog = new Dog1("Rex");
        Animal1 cat = new Cat1("Whiskers");

        dog.eat();
        dog.makeSound();
        dog.sleep();

        cat.eat();
        cat.makeSound();
        cat.sleep();

        // Interface with default methods (Java 8+)
        System.out.println("\nVehicle examples:");
        MotorVehicle car = new Car2();
        MotorVehicle motorcycle = new Motorcycle2();

        car.start();
        car.stop();
        car.honk(); // Default method

        motorcycle.start();
        motorcycle.stop();
        motorcycle.honk(); // Default method
    }
}

/**
 * Abstract class Shape demonstrating abstraction
 */
abstract class Shape1 {
    // Abstract methods - no implementation, just declaration
    public abstract double calculateArea();
    public abstract void draw();

    // Concrete method with implementation
    public void display() {
        System.out.println("This is a shape with area: " + calculateArea());
    }
}

/**
 * Circle class extending abstract Shape class
 */
class Circle2 extends Shape1 {
    private double radius;

    public Circle2(double radius) {
        this.radius = radius;
    }

    // Implementing abstract methods
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }
}

/**
 * Rectangle class extending abstract Shape class
 */
class Rectangle2 extends Shape1 {
    private double width;
    private double height;

    public Rectangle2(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Implementing abstract methods
    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with width: " + width + " and height: " + height);
    }
}

/**
 * Interface ElectricDevice demonstrating abstraction through interfaces
 */
interface ElectricDevice {
    // Interface methods are implicitly abstract and public
    void turnOn();
    void turnOff();
}

/**
 * Television class implementing ElectricDevice interface
 */
class Television implements ElectricDevice {
    @Override
    public void turnOn() {
        System.out.println("Television is turning on. Display warming up...");
    }

    @Override
    public void turnOff() {
        System.out.println("Television is turning off. Goodbye!");
    }
}

/**
 * Refrigerator class implementing ElectricDevice interface
 */
class Refrigerator implements ElectricDevice {
    @Override
    public void turnOn() {
        System.out.println("Refrigerator is turning on. Starting cooling system...");
    }

    @Override
    public void turnOff() {
        System.out.println("Refrigerator is turning off. Stopping cooling system...");
    }
}

/**
 * Multiple interfaces for demonstrating multiple interface implementation
 */
interface Phone {
    void call(String phoneNumber);
    void sendMessage(String phoneNumber, String message);
}

interface Internet {
    void browseInternet(String url);
}

interface Camera {
    void takePhoto();
}

/**
 * SmartPhone class implementing multiple interfaces
 */
class SmartPhone implements Phone, Internet, Camera {
    @Override
    public void call(String phoneNumber) {
        System.out.println("Calling " + phoneNumber + "...");
    }

    @Override
    public void sendMessage(String phoneNumber, String message) {
        System.out.println("Sending message to " + phoneNumber + ": " + message);
    }

    @Override
    public void browseInternet(String url) {
        System.out.println("Browsing to " + url);
    }

    @Override
    public void takePhoto() {
        System.out.println("Click! Photo taken.");
    }
}

/**
 * Abstract class for comparing with interfaces
 */
abstract class Animal1 {
    // Instance variables - abstract classes can have state
    protected String name;

    // Constructor - abstract classes can have constructors
    public Animal1(String name) {
        this.name = name;
    }

    // Abstract methods
    public abstract void makeSound();

    // Concrete methods
    public void eat() {
        System.out.println(name + " is eating");
    }

    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}

/**
 * Dog1 class extending abstract Animal1 class
 */
class Dog1 extends Animal1 {
    public Dog1(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof! Woof!");
    }
}

/**
 * Cat1 class extending abstract Animal1 class
 */
class Cat1 extends Animal1 {
    public Cat1(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow! Meow!");
    }
}

/**
 * Interface with default methods (Java 8+)
 */
interface MotorVehicle {
    // Abstract methods
    void start();
    void stop();

    // Default method with implementation
    default void honk() {
        System.out.println("Beep! Beep!");
    }

    // Static method in interface (Java 8+)
    static int getWheelCount(MotorVehicle vehicle) {
        if (vehicle instanceof Car2) {
            return 4;
        } else if (vehicle instanceof Motorcycle2) {
            return 2;
        }
        return 0;
    }
}

/**
 * Car2 class implementing MotorVehicle interface
 */
class Car2 implements MotorVehicle {
    @Override
    public void start() {
        System.out.println("Car engine starting...");
    }

    @Override
    public void stop() {
        System.out.println("Car engine stopping...");
    }

    // Override the default method
    @Override
    public void honk() {
        System.out.println("Car honks: Honk! Honk!");
    }
}

/**
 * Motorcycle2 class implementing MotorVehicle interface
 */
class Motorcycle2 implements MotorVehicle {
    @Override
    public void start() {
        System.out.println("Motorcycle engine starting...");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle engine stopping...");
    }

    // Using the default honk method
}
