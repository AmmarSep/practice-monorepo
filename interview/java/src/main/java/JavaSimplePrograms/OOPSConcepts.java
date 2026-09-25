package JavaSimplePrograms;

/**
 * This class demonstrates the core Object-Oriented Programming (OOP) concepts in Java
 */
public class OOPSConcepts {

    public static void main(String[] args) {
        System.out.println("===== OOP Concepts Demonstration =====\n");

        // Creating objects of different classes
        Animal genericAnimal = new Animal("Generic Animal", 5);
        Dog dog = new Dog("Rex", 3, "Golden Retriever");
        Cat cat = new Cat("Whiskers", 2, "Grey");

        // Demonstrating inheritance and polymorphism
        System.out.println("=== Inheritance and Polymorphism ===\n");
        System.out.println("Calling makeSound() on different objects:\n");

        genericAnimal.makeSound(); // Animal implementation
        dog.makeSound();           // Dog's overridden implementation
        cat.makeSound();           // Cat's overridden implementation

        // Polymorphic reference - Animal reference but Dog/Cat behavior
        System.out.println("\nPolymorphism with Animal references:\n");
        Animal dogReference = new Dog("Buddy", 4, "Labrador");
        Animal catReference = new Cat("Mittens", 3, "White");

        dogReference.makeSound(); // Calls Dog's version
        catReference.makeSound(); // Calls Cat's version

        // Demonstrating encapsulation
        System.out.println("\n=== Encapsulation ===\n");
        System.out.println("Getting/Setting properties through accessor methods:\n");

        System.out.println("Original dog name: " + dog.getName());
        dog.setName("Max"); // Using setter method - controlled access
        System.out.println("Updated dog name: " + dog.getName());

        // This would cause an error because age is private:
        // dog.age = -5; // Can't directly access private members

        // Instead, we use the setter that validates:
        System.out.println("Original dog age: " + dog.getAge());
        dog.setAge(5);     // Valid age, will update
        System.out.println("Updated to valid age: " + dog.getAge());
        dog.setAge(-5);    // Invalid age, won't update
        System.out.println("Attempted update to invalid age: " + dog.getAge());

        // Demonstrating abstraction
        System.out.println("\n=== Abstraction ===\n");
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);

        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Rectangle area: " + rectangle.calculateArea());

        // Demonstrating abstraction through interfaces
        System.out.println("\nInterface-based abstraction:\n");
        Swimmer dolphin = new Dolphin();
        Swimmer fish = new Fish();

        dolphin.swim();
        fish.swim();

        // Demonstrating object composition
        System.out.println("\n=== Composition ===\n");
        Address address = new Address("123 Main St", "Springfield", "12345");
        Person person = new Person("John Doe", 30, address);

        System.out.println(person.getDetails());
    }
}

/**
 * Base Animal class demonstrating OOP principles
 */
class Animal {
    // Encapsulation: Private members accessible only through methods
    private String name;
    private int age;

    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Accessor methods (getters and setters) for encapsulation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // Encapsulation example: validating input before updating field
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Error: Age cannot be negative");
        }
    }

    // Method that will be overridden by subclasses (polymorphism)
    public void makeSound() {
        System.out.println(name + " makes a generic sound");
    }
}

/**
 * Dog class extending Animal - demonstrates inheritance
 */
class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        // Calls the parent constructor using super
        super(name, age);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Method overriding (polymorphism)
    @Override
    public void makeSound() {
        System.out.println(getName() + " the " + breed + " dog barks: Woof! Woof!");
    }

    // Additional method specific to Dog class
    public void fetch() {
        System.out.println(getName() + " fetches the ball");
    }
}

/**
 * Cat class extending Animal - demonstrates inheritance
 */
class Cat extends Animal {
    private String color;

    public Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Method overriding (polymorphism)
    @Override
    public void makeSound() {
        System.out.println(getName() + " the " + color + " cat meows: Meow! Meow!");
    }

    // Additional method specific to Cat class
    public void purr() {
        System.out.println(getName() + " purrs contentedly");
    }
}

/**
 * Abstract class Shape - demonstrates abstraction
 */
abstract class Shape {
    // Abstract method that must be implemented by subclasses
    public abstract double calculateArea();

    // Concrete method that can be inherited
    public void display() {
        System.out.println("Area: " + calculateArea());
    }
}

/**
 * Circle class implementing abstract Shape class
 */
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

/**
 * Rectangle class implementing abstract Shape class
 */
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

/**
 * Swimmer interface - demonstrates abstraction through interfaces
 */
interface Swimmer {
    void swim();
}

/**
 * Dolphin class implementing Swimmer interface
 */
class Dolphin implements Swimmer {
    @Override
    public void swim() {
        System.out.println("Dolphin swims gracefully through the water");
    }
}

/**
 * Fish class implementing Swimmer interface
 */
class Fish implements Swimmer {
    @Override
    public void swim() {
        System.out.println("Fish swims by moving its tail");
    }
}

/**
 * Address class for composition example
 */
class Address {
    private String street;
    private String city;
    private String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getFullAddress() {
        return street + ", " + city + ", " + zipCode;
    }
}

/**
 * Person class that has an Address - demonstrates composition
 */
class Person {
    private String name;
    private int age;
    private Address address; // Composition: Person has-an Address

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getDetails() {
        return "Person: " + name + ", Age: " + age + ", Address: " + address.getFullAddress();
    }
}
