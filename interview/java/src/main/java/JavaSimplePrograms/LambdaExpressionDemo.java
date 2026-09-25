package JavaSimplePrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class demonstrates lambda expressions in Java
 * Lambda expressions provide a clear and concise way to implement
 * functional interfaces using an expression
 */
public class LambdaExpressionDemo {

    public static void main(String[] args) {
        System.out.println("===== Lambda Expression Demonstration =====\n");

        // Basic Lambda Expression
        System.out.println("--- Basic Lambda Expression ---\n");

        // Traditional way using anonymous class
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World - Traditional way");
            }
        };

        // Using lambda expression
        Runnable lambdaRunnable = () -> System.out.println("Hello World - Lambda way");

        System.out.println("Executing Runnable with anonymous class:");
        traditionalRunnable.run();

        System.out.println("\nExecuting Runnable with lambda expression:");
        lambdaRunnable.run();

        // Lambda expressions with parameters
        System.out.println("\n--- Lambda Expressions with Parameters ---\n");

        // Single parameter without parentheses
        Consumer<String> printer = message -> System.out.println("Message: " + message);
        printer.accept("Hello from lambda!");

        // Single parameter with parentheses
        Consumer<String> formattedPrinter = (message) -> System.out.println("Formatted: " + message.toUpperCase());
        formattedPrinter.accept("hello from lambda!");

        // Multiple parameters
        BiFunction<Integer, Integer, Integer> adder = (a, b) -> a + b;
        System.out.println("5 + 3 = " + adder.apply(5, 3));

        // Lambda with multiple statements
        BiFunction<Integer, Integer, Integer> complexCalculator = (a, b) -> {
            int sum = a + b;
            int product = a * b;
            return sum + product;
        };
        System.out.println("Complex calculation with 5 and 3: " + complexCalculator.apply(5, 3));

        // Lambda expressions with common functional interfaces
        System.out.println("\n--- Lambda with Functional Interfaces ---\n");

        // With Predicate<T>
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));

        // With Function<T, R>
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("Length of 'lambda': " + stringLength.apply("lambda"));

        // With Consumer<T>
        Consumer<String> greeter = name -> System.out.println("Hello, " + name + "!");
        greeter.accept("John");

        // With Supplier<T>
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("Random value: " + randomSupplier.get());

        // Using lambda expressions with collections
        System.out.println("\n--- Lambda with Collections ---\n");

        List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie", "David");
        System.out.println("Original list: " + names);

        // Sorting with lambda
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted alphabetically: " + names);

        // Sorting by length
        Collections.sort(names, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Sorted by length: " + names);

        // Using lambda with forEach
        System.out.println("\nPrinting each name with forEach:");
        names.forEach(name -> System.out.println("  " + name));

        // Filtering with lambda and streams
        List<String> filteredNames = names.stream()
                                         .filter(name -> name.length() > 4)
                                         .collect(Collectors.toList());
        System.out.println("\nNames with more than 4 characters: " + filteredNames);

        // Method reference as an alternative to lambda
        System.out.println("\n--- Method References ---\n");

        // Lambda expression
        Consumer<String> lambdaPrinter = s -> System.out.println(s);

        // Equivalent method reference
        Consumer<String> methodReferencePrinter = System.out::println;

        lambdaPrinter.accept("Printed with lambda");
        methodReferencePrinter.accept("Printed with method reference");

        // Sorting with method reference
        Collections.sort(names, String::compareTo);
        System.out.println("\nSorted with method reference: " + names);

        // Lambda expressions with custom functional interfaces
        System.out.println("\n--- Lambda with Custom Functional Interfaces ---\n");

        // MathOperation is a custom functional interface
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;

        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 - 5 = " + operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));

        // StringProcessor is another custom functional interface
        StringProcessor toUpperCase = s -> s.toUpperCase();
        StringProcessor removeSpaces = s -> s.replaceAll("\\s+", "");
        StringProcessor reverseString = s -> new StringBuilder(s).reverse().toString();

        String text = "Lambda Expressions";
        System.out.println("\nOriginal text: " + text);
        System.out.println("Upper case: " + processString(text, toUpperCase));
        System.out.println("Without spaces: " + processString(text, removeSpaces));
        System.out.println("Reversed: " + processString(text, reverseString));

        // Advanced lambda examples
        System.out.println("\n--- Advanced Lambda Examples ---\n");

        // Using lambdas with streams for complex operations
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Alice", 22),
            new Person("Bob", 30),
            new Person("Charlie", 28),
            new Person("Eve", 22)
        );

        // Filtering and collecting
        List<Person> youngPeople = people.stream()
                                        .filter(p -> p.getAge() < 25)
                                        .collect(Collectors.toList());
        System.out.println("People younger than 25: " + youngPeople);

        // Mapping objects to strings
        List<String> personNames = people.stream()
                                        .map(p -> p.getName())
                                        .collect(Collectors.toList());
        System.out.println("\nExtracted names: " + personNames);

        // Grouping people by age
        Map<Integer, List<Person>> peopleByAge = people.stream()
                                                     .collect(Collectors.groupingBy(p -> p.getAge()));
        System.out.println("\nPeople grouped by age:");
        peopleByAge.forEach((age, group) -> {
            System.out.println("Age " + age + ": " + group);
        });

        // Calculating averages
        double averageAge = people.stream()
                                .mapToInt(p -> p.getAge())
                                .average()
                                .orElse(0);
        System.out.println("\nAverage age: " + averageAge);

        // Finding maximum/minimum
        Person oldest = people.stream()
                            .max(Comparator.comparingInt(p -> p.getAge()))
                            .orElse(null);
        System.out.println("\nOldest person: " + oldest);

        // Reduce operation
        int totalAge = people.stream()
                           .map(p -> p.getAge())
                           .reduce(0, (a, b) -> a + b);
        System.out.println("\nSum of all ages: " + totalAge);
    }

    /**
     * Helper method for MathOperation functional interface
     */
    private static int operate(int a, int b, MathOperation operation) {
        return operation.operate(a, b);
    }

    /**
     * Helper method for StringProcessor functional interface
     */
    private static String processString(String str, StringProcessor processor) {
        return processor.process(str);
    }

    /**
     * Custom functional interface for mathematical operations
     */
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    /**
     * Custom functional interface for string processing
     */
    @FunctionalInterface
    interface StringProcessor {
        String process(String str);
    }

    /**
     * Person class for advanced lambda examples
     */
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
