package FrequentInterviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This class demonstrates Lambda Expressions in Java 8
 * 
 * Lambda expressions provide a clear and concise way to implement
 * functional interfaces using an expression. They enable you to
 * treat functionality as a method argument.
 */
public class LambdaExpressionDemo {

    public static void main(String[] args) {
        System.out.println("===== Lambda Expression Demonstration =====");

        // 1. Basic Lambda Expressions
        demonstrateBasicLambdas();

        // 2. Lambda Expressions with Collections
        demonstrateLambdasWithCollections();

        // 3. Method References
        demonstrateMethodReferences();

        // 4. Lambda Variable Capture
        demonstrateVariableCapture();

        // 5. Lambda Expression vs Anonymous Class
        compareLambdaVsAnonymousClass();
    }

    /**
     * Demonstrates basic lambda expressions with various functional interfaces
     */
    private static void demonstrateBasicLambdas() {
        System.out.println("\n1. Basic Lambda Expressions:\n");

        // No parameters
        Runnable noParamLambda = () -> System.out.println("Lambda with no parameters");
        noParamLambda.run();

        // One parameter
        Consumer<String> oneParamLambda = message -> System.out.println(message);
        oneParamLambda.accept("Lambda with one parameter");

        // Multiple parameters
        BiFunction<Integer, Integer, Integer> twoParamLambda = (a, b) -> a + b;
        System.out.println("Sum of 5 and 3: " + twoParamLambda.apply(5, 3));

        // Lambda with multiple statements using block
        Function<Integer, Integer> blockLambda = x -> {
            int result = x * 2;
            result += 5;
            return result;
        };
        System.out.println("Result of block lambda with input 10: " + blockLambda.apply(10));

        // Lambda with custom functional interface
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;

        System.out.println("\nMath operations using custom functional interface:");
        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 - 5 = " + operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));
    }

    /**
     * Demonstrates using lambda expressions with Java collections
     */
    private static void demonstrateLambdasWithCollections() {
        System.out.println("\n2. Lambda Expressions with Collections:\n");

        List<Person> people = Arrays.asList(
            new Person("John", 28),
            new Person("Mary", 22),
            new Person("Bob", 31),
            new Person("Alice", 25),
            new Person("Charlie", 28)
        );

        // Sorting with lambda expression
        System.out.println("Original list:");
        printPeople(people);

        // Sort by age
        Collections.sort(people, (p1, p2) -> p1.getAge() - p2.getAge());
        System.out.println("\nSorted by age:");
        printPeople(people);

        // Sort by name
        Collections.sort(people, (p1, p2) -> p1.getName().compareTo(p2.getName()));
        System.out.println("\nSorted by name:");
        printPeople(people);

        // Filtering with lambda
        System.out.println("\nPeople older than 25:");
        List<Person> olderThan25 = filterPeople(people, p -> p.getAge() > 25);
        printPeople(olderThan25);

        System.out.println("\nPeople with names starting with 'A':");
        List<Person> namesWithA = filterPeople(people, p -> p.getName().startsWith("A"));
        printPeople(namesWithA);

        // Transforming with lambda
        System.out.println("\nGetting all names:");
        List<String> names = transformPeople(people, Person::getName);
        names.forEach(System.out::println);

        // Using forEach with lambda
        System.out.println("\nUsing forEach with lambda:");
        people.forEach(person -> {
            System.out.println("Person: " + person.getName() + ", " + 
                    person.getAge() + " years old");
        });
    }

    /**
     * Demonstrates method references as shorthand for lambda expressions
     */
    private static void demonstrateMethodReferences() {
        System.out.println("\n3. Method References:\n");

        List<String> messages = Arrays.asList("Hello", "World", "Java", "Lambda");

        // Regular lambda expression
        System.out.println("Using lambda expression:");
        messages.forEach(message -> System.out.println(message));

        // Method reference to static method
        System.out.println("\nUsing method reference to static method:");
        messages.forEach(System.out::println);

        // Method reference to instance method of a particular object
        StringProcessor processor = new StringProcessor();
        System.out.println("\nUsing method reference to instance method:");
        messages.forEach(processor::process);

        // Method reference to instance method of an arbitrary object of a particular type
        System.out.println("\nUsing method reference to instance method of arbitrary object:");
        messages.forEach(String::toUpperCase);  // This doesn't print anything, just creates new strings
        List<String> upperCaseMessages = transformStrings(messages, String::toUpperCase);
        upperCaseMessages.forEach(System.out::println);

        // Method reference to constructor
        System.out.println("\nUsing method reference to constructor:");
        Function<String, StringBuilder> stringBuilderFunction = StringBuilder::new;
        messages.forEach(msg -> {
            StringBuilder sb = stringBuilderFunction.apply(msg);
            System.out.println("StringBuilder created: " + sb);
        });
    }

    /**
     * Demonstrates variable capture in lambda expressions
     */
    private static void demonstrateVariableCapture() {
        System.out.println("\n4. Lambda Variable Capture:\n");

        // Capturing a final variable
        final int factor = 2;
        Function<Integer, Integer> multiplier = num -> num * factor;
        System.out.println("5 multiplied by factor 2: " + multiplier.apply(5));

        // Capturing an effectively final variable
        int offset = 10;  // Effectively final
        Function<Integer, Integer> adder = num -> num + offset;
        System.out.println("5 plus offset 10: " + adder.apply(5));

        // offset = 20;  // Uncommenting this would cause a compilation error

        // Using non-final variables in enclosing scope
        int[] counter = {0};  // Using array as a mutable container

        Runnable incrementer = () -> {
            counter[0]++;  // Modifying the array content is allowed
            System.out.println("Counter: " + counter[0]);
        };

        incrementer.run();
        incrementer.run();
        incrementer.run();

        // Instance variables can be modified freely
        VariableCapture vc = new VariableCapture();
        vc.demonstrateInstanceVariableCapture();
    }

    /**
     * Compares lambda expressions with anonymous inner classes
     */
    private static void compareLambdaVsAnonymousClass() {
        System.out.println("\n5. Lambda Expression vs Anonymous Class:\n");

        System.out.println("Using anonymous class:");
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class running");
                System.out.println("'this' refers to the anonymous class instance");
                System.out.println("Class name: " + this.getClass().getName());
            }
        };
        anonymousRunnable.run();

        System.out.println("\nUsing lambda expression:");
        Runnable lambdaRunnable = () -> {
            System.out.println("Lambda expression running");
            System.out.println("'this' refers to the enclosing class instance");
            System.out.println("Class name: " + LambdaExpressionDemo.class.getName());
            // In a lambda, 'this' refers to the enclosing class, not the lambda itself
        };
        lambdaRunnable.run();

        // Performance measurement
        System.out.println("\nPerformance comparison:");
        measurePerformance("Anonymous class", () -> {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    // Do nothing
                }
            };
            r.run();
        });

        measurePerformance("Lambda expression", () -> {
            Runnable r = () -> { /* Do nothing */ };
            r.run();
        });
    }

    /**
     * Measures the performance of a task
     */
    private static void measurePerformance(String name, Runnable task) {
        long start = System.nanoTime();

        // Execute the task many times for measurement
        for (int i = 0; i < 1000000; i++) {
            task.run();
        }

        long end = System.nanoTime();
        System.out.println(name + " took " + (end - start) / 1000000 + " ms");
    }

    /**
     * Helper method to print a list of people
     */
    private static void printPeople(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getName() + ", " + person.getAge());
        }
    }

    /**
     * Helper method to filter a list of people based on a predicate
     */
    private static List<Person> filterPeople(List<Person> people, Predicate<Person> predicate) {
        List<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (predicate.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    /**
     * Helper method to transform a list of people to a list of another type
     */
    private static <R> List<R> transformPeople(List<Person> people, Function<Person, R> function) {
        List<R> result = new ArrayList<>();
        for (Person person : people) {
            result.add(function.apply(person));
        }
        return result;
    }

    /**
     * Helper method to transform a list of strings
     */
    private static List<String> transformStrings(List<String> strings, Function<String, String> function) {
        List<String> result = new ArrayList<>();
        for (String str : strings) {
            result.add(function.apply(str));
        }
        return result;
    }

    /**
     * Helper method to perform a math operation
     */
    private static int operate(int a, int b, MathOperation operation) {
        return operation.operate(a, b);
    }
}

/**
 * Example functional interface
 */
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

/**
 * Helper class for method reference demonstration
 */
class StringProcessor {
    public void process(String str) {
        System.out.println("Processing: " + str);
    }
}

/**
 * Helper class for variable capture demonstration
 */
class VariableCapture {
    private int instanceCounter = 0;

    public void demonstrateInstanceVariableCapture() {
        System.out.println("\nCapturing instance variables:");

        Runnable incrementer = () -> {
            instanceCounter++;  // Can modify instance variables
            System.out.println("Instance counter: " + instanceCounter);
        };

        incrementer.run();
        incrementer.run();

        // Can also modify instance variables outside the lambda
        instanceCounter = 10;
        System.out.println("After external modification: " + instanceCounter);
        incrementer.run();
    }
}
