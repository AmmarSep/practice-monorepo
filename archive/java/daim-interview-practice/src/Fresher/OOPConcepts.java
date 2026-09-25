package Fresher;

/**
 * This class demonstrates fundamental Object-Oriented Programming (OOP) concepts in Java:
 * 1. Classes and Objects
 * 2. Inheritance
 * 3. Polymorphism
 * 4. Encapsulation
 * 5. Abstraction
 */
public class OOPConcepts {

    // Main class to demonstrate OOP concepts
    public static void main(String[] args) {
        System.out.println("=== Object-Oriented Programming Concepts ===\n");

        // 1. Classes and Objects
        System.out.println("1. Classes and Objects:\n");

        // Creating objects of the Car class
        Car car1 = new Car("Toyota", "Camry", 2022);
        Car car2 = new Car("Honda", "Civic", 2023);

        // Using object methods
        car1.displayInfo();
        car2.displayInfo();

        car1.accelerate();
        car1.brake();

        // 2. Inheritance
        System.out.println("\n2. Inheritance:\n");

        // Creating object of the child class
        ElectricCar electricCar = new ElectricCar("Tesla", "Model 3", 2023, 75);
        electricCar.displayInfo(); // Inherited method
        electricCar.accelerate(); // Overridden method
        electricCar.charge(); // New method in child class

        // 3. Polymorphism
        System.out.println("\n3. Polymorphism:\n");

        // Polymorphic references
        Vehicle vehicle1 = new Car("Ford", "Mustang", 2021);
        Vehicle vehicle2 = new ElectricCar("Nissan", "Leaf", 2022, 40);

        // Same method call, different implementations
        vehicle1.accelerate();
        vehicle2.accelerate();

        // 4. Encapsulation
        System.out.println("\n4. Encapsulation:\n");

        // Accessing private fields through public methods
        System.out.println("Car 1 make: " + car1.getMake());
        car1.setYear(2024);
        System.out.println("Updated Car 1 info:");
        car1.displayInfo();

        // 5. Abstraction
        System.out.println("\n5. Abstraction:\n");

        // Creating objects of concrete classes that implement interfaces
        Drivable sedan = new Sedan("Hyundai", "Elantra", 2022);
        Drivable truck = new Truck("Ford", "F-150", 2021);

        sedan.drive();
        truck.drive();
    }
}

// 1. Classes and Objects
// Base class demonstrating encapsulation with private fields
class Vehicle {
    // Encapsulation - private fields
    private String make;
    private String model;
    private int year;

    // Constructor
    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Getters and Setters (Encapsulation)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Method to display vehicle information
    public void displayInfo() {
        System.out.println("Vehicle: " + year + " " + make + " " + model);
    }

    // Method that can be overridden by child classes
    public void accelerate() {
        System.out.println("Vehicle is accelerating");
    }

    public void brake() {
        System.out.println("Vehicle is braking");
    }
}

// Car class extends Vehicle (Inheritance)
class Car extends Vehicle {
    // Constructor
    public Car(String make, String model, int year) {
        // Call parent constructor using super
        super(make, model, year);
    }

    // Override displayInfo method (Polymorphism)
    @Override
    public void displayInfo() {
        System.out.println("Car: " + getYear() + " " + getMake() + " " + getModel());
    }

    // Override accelerate method (Polymorphism)
    @Override
    public void accelerate() {
        System.out.println("Car is accelerating with internal combustion engine");
    }
}

// ElectricCar class extends Car (Multi-level Inheritance)
class ElectricCar extends Car {
    // Additional property specific to ElectricCar
    private int batteryCapacity; // in kWh

    // Constructor
    public ElectricCar(String make, String model, int year, int batteryCapacity) {
        super(make, model, year);
        this.batteryCapacity = batteryCapacity;
    }

    // Override displayInfo method to include battery information
    @Override
    public void displayInfo() {
        System.out.println("Electric Car: " + getYear() + " " + getMake() + " " + getModel() + 
                         " with " + batteryCapacity + " kWh battery");
    }

    // Override accelerate method with specific implementation
    @Override
    public void accelerate() {
        System.out.println("Electric Car is accelerating silently with electric motor");
    }

    // Method specific to ElectricCar
    public void charge() {
        System.out.println("Electric Car is charging the " + batteryCapacity + " kWh battery");
    }
}

// 5. Abstraction - Interface
interface Drivable {
    void drive(); // Abstract method without implementation

    // Default method with implementation (Java 8+)
    default void park() {
        System.out.println("Vehicle is parked");
    }
}

// Concrete class implementing Drivable interface
class Sedan extends Car implements Drivable {
    public Sedan(String make, String model, int year) {
        super(make, model, year);
    }

    // Implementing the abstract method from the interface
    @Override
    public void drive() {
        System.out.println("Sedan is being driven smoothly");
    }
}

// Another concrete class implementing Drivable interface
class Truck extends Vehicle implements Drivable {
    public Truck(String make, String model, int year) {
        super(make, model, year);
    }

    // Implementing the abstract method from the interface
    @Override
    public void drive() {
        System.out.println("Truck is being driven with heavy load");
    }
}
