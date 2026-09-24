//package FrequentInterviewQuestions;
//
///**
// * This class demonstrates the differences between Abstract Classes and Interfaces in Java
// *
// * Key differences:
// * 1. Methods: Abstract class can have both abstract and concrete methods, while
// *    interfaces traditionally had only abstract methods (prior to Java 8)
// * 2. Variables: Abstract class can have instance variables, interfaces can only have constants
// * 3. Constructors: Abstract class can have constructors, interfaces cannot
// * 4. Access Modifiers: Abstract class methods can have any access modifier, interface methods are implicitly public
// * 5. Inheritance: A class can extend only one abstract class, but implement multiple interfaces
// * 6. Purpose: Abstract class for 'is-a' relationship, interface for 'can-do' relationship
// */
//public class AbstractClassVsInterface {
//
//    public static void main(String[] args) {
//        System.out.println("===== Abstract Class vs Interface Demonstration =====");
//
//        // 1. Basic Differences
//        demonstrateBasicDifferences();
//
//        // 2. Multiple Inheritance
//        demonstrateMultipleInheritance();
//
//        // 3. Evolution with Java 8+
//        demonstrateJava8Features();
//
//        // 4. When to Use Each
//        demonstrateWhenToUse();
//
//        // 5. Real-world Examples
//        demonstrateRealWorldExamples();
//    }
//
//    /**
//     * Demonstrates basic differences between abstract classes and interfaces
//     */
//    private static void demonstrateBasicDifferences() {
//        System.out.println("\n1. Basic Differences:\n");
//
//        // Creating objects from concrete implementations
//        System.out.println("Creating objects from implementations:");
//
//        // Abstract class implementation
//        DemoAnimal dog = new DemoDog("Buddy", 3);
//        dog.eat();          // Concrete method from abstract class
//        dog.makeSound();    // Abstract method implemented by subclass
//        System.out.println("Dog age: " + dog.getAge());  // Using instance variable
//
//        // Interface implementation
//        DemoFlyable bird = new DemoBird("Sparrow");
//        bird.fly();         // Abstract method from interface
//        bird.land();        // Default method from interface (Java 8+)
//        System.out.println("Maximum flying speed: " + DemoFlyable.MAX_SPEED);  // Constant from interface
//
//        System.out.println("\nKey Differences:");
//        System.out.println("1. Abstract Class:");
//        System.out.println("   - Can have instance variables with any access modifier");
//        System.out.println("   - Can have constructors");
//        System.out.println("   - Can have both abstract and concrete methods");
//        System.out.println("   - Methods can have any access modifier");
//
//        System.out.println("\n2. Interface:");
//        System.out.println("   - Can only have constants (public static final)");
//        System.out.println("   - Cannot have constructors");
//        System.out.println("   - Originally only abstract methods, now can have default and static methods");
//        System.out.println("   - Methods are implicitly public");
//    }
//
//    /**
//     * Demonstrates multiple inheritance through interfaces
//     */
//    private static void demonstrateMultipleInheritance() {
//        System.out.println("\n2. Multiple Inheritance:\n");
//
//        // Class implementing multiple interfaces
//        DemoDuck duck = new DemoDuck("Donald", 2);
//        duck.eat();         // From DemoAnimal abstract class
//        duck.makeSound();    // From DemoAnimal abstract class
//        duck.fly();         // From DemoFlyable interface
//        duck.swim();        // From DemoSwimmable interface
//
//        System.out.println("\nA class can extend only one abstract class but implement multiple interfaces:");
//        System.out.println("DemoDuck extends DemoAnimal and implements both DemoFlyable and DemoSwimmable");
//
//        // Using different interfaces with the same object
//        System.out.println("\nUsing different interface references for the same object:");
//
//        DemoFlyable flyingDuck = duck;
//        flyingDuck.fly();   // Accessing as a DemoFlyable
//
//        DemoSwimmable swimmingDuck = duck;
//        swimmingDuck.swim(); // Accessing as a DemoSwimmable
//
//        // Cannot extend multiple abstract classes
//        System.out.println("\nJava does not support extending multiple abstract classes");
//        System.out.println("This would cause the infamous 'diamond problem' in multiple inheritance");
//    }
//
//    /**
//     * Demonstrates Java 8+ features for interfaces
//     */
//    private static void demonstrateJava8Features() {
//        System.out.println("\n3. Evolution with Java 8+:\n");
//
//        // Default methods in interfaces
//        DemoFlyable bird = new DemoBird("Eagle");
//        bird.fly();         // Abstract method
//        bird.land();        // Default method
//
//        // Default methods can be overridden
//        DemoFlyable airplane = new DemoAirplane();
//        airplane.fly();
//        airplane.land();    // Overridden default method
//
//        // Static methods in interfaces
//        String flyingTip = DemoFlyable.getFlyingTip();
//        System.out.println("Flying tip: " + flyingTip);  // Static method in interface
//
//        // Private methods in interfaces (Java 9+)
//        System.out.println("\nJava 9+ also introduced private methods in interfaces");
//        System.out.println("This allows for better code organization and reuse within the interface");
//
//        System.out.println("\nWith these changes, the distinction between abstract classes and");
//        System.out.println("interfaces has become less clear, but fundamental differences remain:");
//        System.out.println("- Interfaces still cannot have instance variables");
//        System.out.println("- Interfaces still cannot have constructors");
//        System.out.println("- Classes can still implement multiple interfaces but extend only one class");
//    }
//
//    /**
//     * Demonstrates when to use abstract classes vs interfaces
//     */
//    private static void demonstrateWhenToUse() {
//        System.out.println("\n4. When to Use Each:\n");
//
//        System.out.println("Use Abstract Class when:");
//        System.out.println("- You want to share code among closely related classes");
//        System.out.println("- You need to declare non-static or non-final fields (instance variables)");
//        System.out.println("- You want to provide a common base implementation");
//        System.out.println("- You're working with an 'is-a' relationship");
//        System.out.println("- Your abstract class would be the base for tightly coupled class hierarchy");
//        System.out.println("Example: DemoAnimal as base for DemoDog, DemoCat, etc.");
//
//        System.out.println("\nUse Interface when:");
//        System.out.println("- You want to define a contract that unrelated classes can implement");
//        System.out.println("- You want to take advantage of multiple inheritance");
//        System.out.println("- You're specifying behavior but not concerned with who implements it");
//        System.out.println("- You're working with a 'can-do' relationship");
//        System.out.println("- You want to decouple the definition from the implementation");
//        System.out.println("Example: DemoFlyable can be implemented by DemoBird, DemoAirplane, etc.");
//
//        System.out.println("\nDemo Examples:");
//        System.out.println("- DemoAbstractVehicle class: Base for closely related DemoCar, DemoMotorcycle classes");
//        DemoCar car = new DemoSportsCar();
//        car.startEngine();  // Common implementation from abstract class
//        car.accelerate();   // Specialized implementation
//        car.applyBrakes();  // Specialized implementation
//
//        System.out.println("\n- DemoDrivable interface: Implemented by unrelated DemoVehicle, DemoSimulator classes");
//        DemoDrivable carDriver = new DemoCar();  // Vehicle implementation
//        DemoDrivable simulator = new DemoDrivingSimulator();  // Non-vehicle implementation
//        carDriver.drive();  // Same contract, different implementation
//        simulator.drive();  // Same contract, different implementation
//    }
//
//    /**
//     * Demonstrates real-world examples of abstract classes and interfaces
//     */
//    private static void demonstrateRealWorldExamples() {
//        System.out.println("\n5. Real-world Examples:\n");
//
//        System.out.println("Examples from Java Standard Library:");
//
//        System.out.println("Abstract Classes:");
//        System.out.println("- java.io.InputStream, java.io.OutputStream, java.io.Reader, java.io.Writer");
//        System.out.println("  Common functionality for I/O operations with some abstract methods");
//        System.out.println("- java.util.AbstractList, java.util.AbstractSet, java.util.AbstractMap");
//        System.out.println("  Provide default implementations for collection interfaces");
//
//        System.out.println("\nInterfaces:");
//        System.out.println("- java.lang.Comparable, java.util.Comparator");
//        System.out.println("  Define how objects should be compared, implemented by many unrelated classes");
//        System.out.println("- java.lang.Runnable, java.util.concurrent.Callable");
//        System.out.println("  Define task execution contracts for multithreading");
//        System.out.println("- java.util.List, java.util.Set, java.util.Map");
//        System.out.println("  Define contracts for different types of collections");
//
//        // Demo with two different logger implementations
//        System.out.println("\nExample with Logger implementations:");
//
//        DemoLogger consoleLogger = new DemoConsoleLogger();
//        DemoLogger fileLogger = new DemoFileLogger("app.log");
//
//        // Both implement the same interface but work differently
//        consoleLogger.log("This is a console message");
//        fileLogger.log("This is a file message");
//
//        // Both inherit common functionality from AbstractLogger
//        consoleLogger.logError("Console error");  // Uses template method from abstract class
//        fileLogger.logError("File error");        // Uses template method from abstract class
//    }
//}