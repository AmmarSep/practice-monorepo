package JavaSimplePrograms;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class demonstrates the main features introduced in Java 8
 */
public class Java8Features {

    public static void main(String[] args) {
        System.out.println("===== Java 8 Features Demonstration =====\n");

        // 1. Lambda Expressions
        demonstrateLambdaExpressions();

        // 2. Functional Interfaces
        demonstrateFunctionalInterfaces();

        // 3. Stream API
        demonstrateStreamAPI();

        // 4. Default methods in interfaces
        demonstrateDefaultMethods();

        // 5. Method references
        demonstrateMethodReferences();

        // 6. Optional class
        demonstrateOptional();
    }

    /**
     * 1. Lambda Expressions
     * 
     * Lambda expressions provide a concise way to represent an instance of
     * a functional interface (an interface with a single abstract method).
     */
    private static void demonstrateLambdaExpressions() {
        System.out.println("1. Lambda Expressions:\n");

        // Traditional anonymous class
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional anonymous class implementation");
            }
        };

        // Lambda expression
        Runnable lambdaRunnable = () -> System.out.println("Lambda expression implementation");

        traditionalRunnable.run();
        lambdaRunnable.run();

        // Lambda with parameters
        Calculator addition = (a, b) -> a + b;
        Calculator subtraction = (a, b) -> a - b;

        System.out.println("Addition: 5 + 3 = " + addition.calculate(5, 3));
        System.out.println("Subtraction: 5 - 3 = " + subtraction.calculate(5, 3));

        // Lambda with multiple statements
        Calculator complexOperation = (a, b) -> {
            int result = a * b;
            result = result + a;
            return result;
        };

        System.out.println("Complex operation: (5 * 3) + 5 = " + complexOperation.calculate(5, 3));
        System.out.println();
    }

    /**
     * 2. Functional Interfaces
     * 
     * Functional interfaces are interfaces with exactly one abstract method.
     * Java 8 introduced several built-in functional interfaces in the java.util.function package.
     */
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("2. Functional Interfaces:\n");

        // Predicate - takes one argument and returns a boolean
        System.out.println("Predicate Example:");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));

        // Consumer - takes one argument and returns no result (void)
        System.out.println("\nConsumer Example:");
        Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
        printUpperCase.accept("hello world");

        // Function - takes one argument and produces a result
        System.out.println("\nFunction Example:");
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Java 8': " + stringLength.apply("Java 8"));

        // Supplier - takes no arguments and returns a result
        System.out.println("\nSupplier Example:");
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random value: " + randomValue.get());
        System.out.println();
    }

    /**
     * 3. Stream API
     * 
     * Stream API provides a functional approach to process collections of objects.
     * It supports various operations like filter, map, reduce, etc.
     */
    private static void demonstrateStreamAPI() {
        System.out.println("3. Stream API:\n");

        List<String> names = Arrays.asList("John", "Sarah", "Mark", "Tanya", "Eric", "Adam");
        System.out.println("Original list: " + names);

        // Filter operation
        List<String> filteredNames = names.stream()
                                         .filter(name -> name.length() > 4)
                                         .collect(Collectors.toList());
        System.out.println("Names with more than 4 characters: " + filteredNames);

        // Map operation
        List<Integer> nameLengths = names.stream()
                                        .map(String::length)
                                        .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths);

        // Sorted operation
        List<String> sortedNames = names.stream()
                                       .sorted()
                                       .collect(Collectors.toList());
        System.out.println("Sorted names: " + sortedNames);

        // ForEach operation
        System.out.println("\nPrinting names with forEach:");
        names.stream().forEach(name -> System.out.println("  " + name));

        // Reduce operation
        String concatenatedNames = names.stream()
                                       .reduce("", (a, b) -> a + ", " + b);
        System.out.println("\nConcatenated names: " + concatenatedNames.substring(2));

        // Count, max, min operations
        long count = names.stream().count();
        String maxName = names.stream().max(String::compareTo).orElse("");
        String minName = names.stream().min(String::compareTo).orElse("");

        System.out.println("\nCount: " + count);
        System.out.println("Max name: " + maxName);
        System.out.println("Min name: " + minName);
        System.out.println();
    }

    /**
     * 4. Default methods in interfaces
     * 
     * Java 8 allows interfaces to have default methods with implementation.
     */
    private static void demonstrateDefaultMethods() {
        System.out.println("4. Default Methods in Interfaces:\n");

        // Create an instance of a class implementing the Vehicle interface
        Vehicle car = new Car("Toyota");

        // Call the default method from the interface
        car.start();

        // Call the overridden method from the implementing class
        car.showInfo();

        // Call the static method from the interface
        Vehicle.honk();
        System.out.println();
    }

    /**
     * 5. Method references
     * 
     * Method references provide a way to refer to methods without executing them.
     * They are compact, easy-to-read lambda expressions for methods that already have a name.
     */
    private static void demonstrateMethodReferences() {
        System.out.println("5. Method References:\n");

        List<String> names = Arrays.asList("John", "Sarah", "Mark", "Tanya", "Eric");

        // Using lambda expression
        System.out.println("Using lambda expression:");
        names.forEach(name -> System.out.println(name));

        // Using method reference (::)
        System.out.println("\nUsing method reference:");
        names.forEach(System.out::println);

        // Static method reference
        List<Integer> numbers = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);

        // Using lambda
        System.out.println("\nSorted numbers using lambda:");
        numbers.stream()
              .sorted((a, b) -> Integer.compare(a, b))
              .forEach(n -> System.out.print(n + " "));

        // Using static method reference
        System.out.println("\n\nSorted numbers using static method reference:");
        numbers.stream()
              .sorted(Integer::compare)
              .forEach(n -> System.out.print(n + " "));

        // Instance method reference
        System.out.println("\n\nString lengths using instance method reference:");
        names.stream()
            .map(String::length)
            .forEach(len -> System.out.print(len + " "));

        System.out.println("\n");
    }

    /**
     * 6. Optional class
     * 
     * Optional is a container object which may or may not contain a non-null value.
     * It is used to represent null with absent value.
     */
    private static void demonstrateOptional() {
        System.out.println("6. Optional Class:\n");

        // Creating Optional objects
        java.util.Optional<String> empty = java.util.Optional.empty();
        java.util.Optional<String> nonEmpty = java.util.Optional.of("Hello");

        System.out.println("Empty Optional: " + empty.isPresent());
        System.out.println("Non-Empty Optional: " + nonEmpty.isPresent());

        // Optional with nullable value
        String nullableName = null;
        java.util.Optional<String> optionalName = java.util.Optional.ofNullable(nullableName);
        System.out.println("Optional with null value: " + optionalName.isPresent());

        // orElse example
        String defaultValue = optionalName.orElse("Default");
        System.out.println("Value or default: " + defaultValue);

        // orElseGet example
        String suppliedValue = optionalName.orElseGet(() -> "Supplied Value");
        System.out.println("Value or supplied: " + suppliedValue);

        // map example
        java.util.Optional<String> upperCaseName = nonEmpty.map(String::toUpperCase);
        System.out.println("Mapped value: " + upperCaseName.orElse(""));

        // filter example
        boolean isFiltered = nonEmpty.filter(n -> n.length() > 10).isPresent();
        System.out.println("Is filtered (length > 10): " + isFiltered);

        System.out.println();
    }

    // Functional interface for calculator example
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    // Interface with default method for demonstration
    interface Vehicle {
        String getBrand();

        // Default method
        default void start() {
            System.out.println("Starting vehicle...");
        }

        // Another default method
        default void showInfo() {
            System.out.println("Vehicle information");
        }

        // Static method in interface
        static void honk() {
            System.out.println("Honk! Honk!");
        }
    }

    // Class implementing the Vehicle interface
    static class Car implements Vehicle {
        private String brand;

        public Car(String brand) {
            this.brand = brand;
        }

        @Override
        public String getBrand() {
            return brand;
        }

        // Overriding a default method
        @Override
        public void showInfo() {
            System.out.println("Car brand: " + getBrand());
        }
    }
}
