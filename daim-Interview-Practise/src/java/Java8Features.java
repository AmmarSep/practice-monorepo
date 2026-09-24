package java;

/**
 * This class demonstrates various Java 8 features
 * 1. Lambda Expressions
 * 2. Functional Interfaces
 * 3. Stream API
 * 4. Default methods in interfaces
 * 5. Method references
 * 6. Optional class
 */
public class Java8Features {

    public static void main(String[] args) {
        System.out.println("===== Java 8 Features Demonstration =====");
        
        // Demonstrate Lambda Expressions
        demonstrateLambdaExpressions();
        
        // Demonstrate Functional Interfaces
        demonstrateFunctionalInterfaces();
        
        // Demonstrate Stream API
        demonstrateStreamAPI();
        
        // Demonstrate Default Methods
        demonstrateDefaultMethods();
        
        // Demonstrate Method References
        demonstrateMethodReferences();
        
        // Demonstrate Optional
        demonstrateOptional();
    }
    
    /**
     * Lambda expressions provide a clear and concise way to implement 
     * single method interfaces (functional interfaces) by using an expression.
     */
    private static void demonstrateLambdaExpressions() {
        System.out.println("\n1. Lambda Expressions:\n");
        
        // Traditional way using anonymous class
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional way using anonymous class");
            }
        };
        
        // Using Lambda Expression
        Runnable lambdaRunnable = () -> System.out.println("Using Lambda Expression - much more concise");
        
        // Execute both
        traditionalRunnable.run();
        lambdaRunnable.run();
        
        // Lambda with parameters
        Calculator addition = (a, b) -> a + b;
        Calculator subtraction = (a, b) -> a - b;
        
        System.out.println("Addition using lambda: 5 + 3 = " + addition.calculate(5, 3));
        System.out.println("Subtraction using lambda: 5 - 3 = " + subtraction.calculate(5, 3));
    }
    
    /**
     * Functional interfaces have a single abstract method and can be used with lambda expressions
     */
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n2. Functional Interfaces:\n");
        
        // Using Predicate - tests a condition
        java.util.function.Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));
        
        // Using Consumer - accepts a value and performs an operation
        java.util.function.Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());
        printUpperCase.accept("hello world"); // Prints: HELLO WORLD
        
        // Using Function - transforms a value
        java.util.function.Function<String, Integer> stringLength = s -> s.length();
        System.out.println("Length of 'Java 8': " + stringLength.apply("Java 8"));
        
        // Using Supplier - supplies a value
        java.util.function.Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random value: " + randomValue.get());
    }
    
    /**
     * Stream API provides a functional approach to processing collections of objects
     */
    private static void demonstrateStreamAPI() {
        System.out.println("\n3. Stream API:\n");
        
        java.util.List<String> names = java.util.Arrays.asList("John", "Jane", "Adam", "Eve", "Alice", "Bob");
        
        // Filtering
        System.out.println("Names starting with 'J':");
        names.stream()
             .filter(name -> name.startsWith("J"))
             .forEach(System.out::println);
        
        // Mapping
        System.out.println("\nName lengths:");
        names.stream()
             .map(String::length)
             .forEach(System.out::println);
        
        // Sorting
        System.out.println("\nSorted names:");
        names.stream()
             .sorted()
             .forEach(System.out::println);
        
        // Collecting results
        java.util.List<String> filteredNames = names.stream()
                                                   .filter(name -> name.length() > 3)
                                                   .collect(java.util.stream.Collectors.toList());
        System.out.println("\nNames with more than 3 characters: " + filteredNames);
    }
    
    /**
     * Default methods allow interfaces to have methods with implementation
     */
    private static void demonstrateDefaultMethods() {
        System.out.println("\n4. Default Methods in Interfaces:\n");
        
        // Create an instance of a class that implements Vehicle
        Car car = new Car();
        
        // Call both the implemented method and the default method
        car.startEngine();
        car.honk();
    }
    
    /**
     * Method references provide a way to refer to methods without executing them
     */
    private static void demonstrateMethodReferences() {
        System.out.println("\n5. Method References:\n");
        
        java.util.List<String> names = java.util.Arrays.asList("John", "Jane", "Adam", "Eve");
        
        // Using lambda expression
        names.forEach(name -> System.out.println("Lambda: " + name));
        
        // Using method reference
        System.out.println("\nUsing method reference:");
        names.forEach(System.out::println);
        
        // Static method reference
        java.util.List<Integer> numbers = java.util.Arrays.asList(5, 3, 8, 1, 9);
        numbers.stream()
               .sorted(Integer::compare) // Static method reference
               .forEach(System.out::println);
    }
    
    /**
     * Optional is a container object that may or may not contain a non-null value
     */
    private static void demonstrateOptional() {
        System.out.println("\n6. Optional Class:\n");
        
        // Creating Optional objects
        java.util.Optional<String> emptyOptional = java.util.Optional.empty();
        java.util.Optional<String> nonEmptyOptional = java.util.Optional.of("Hello Optional");
        
        // Check if value is present
        System.out.println("Empty Optional has value: " + emptyOptional.isPresent());
        System.out.println("Non-empty Optional has value: " + nonEmptyOptional.isPresent());
        
        // Get value with default
        String defaultValue = emptyOptional.orElse("Default Value");
        System.out.println("Value from empty Optional with default: " + defaultValue);
        
        // Map and filter operations
        nonEmptyOptional.map(String::toUpperCase)
                       .ifPresent(s -> System.out.println("Uppercase value: " + s));
        
        // Using orElseThrow
        try {
            emptyOptional.orElseThrow(() -> new RuntimeException("Optional is empty"));
        } catch (RuntimeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
    
    // Functional interface for calculator example
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
    
    // Interface with default method for demonstration
    interface Vehicle {
        void startEngine(); // Abstract method
        
        // Default method with implementation
        default void honk() {
            System.out.println("Beep beep!");
        }
    }
    
    // Class implementing the Vehicle interface
    static class Car implements Vehicle {
        @Override
        public void startEngine() {
            System.out.println("Car engine started");
        }
        
        // Note: No need to implement honk() as it has a default implementation
    }
}