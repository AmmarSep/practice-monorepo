package JavaSimplePrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * This class demonstrates functional interfaces in Java
 * A functional interface is an interface with exactly one abstract method
 */
@FunctionalInterface
interface MyFunctionalInterface {
    // Single abstract method
    void execute();

    // Default method (doesn't count as abstract)
    default void print(String text) {
        System.out.println(text);
    }

    // Static method (doesn't count as abstract)
    static void printStatic(String text) {
        System.out.println(text);
    }
}

/**
 * Custom functional interface with one parameter
 */
@FunctionalInterface
interface Calculator1 {
    int calculate(int x, int y);
}

public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
        System.out.println("===== Functional Interface Demonstration =====\n");

        // Basic functional interface implementation
        System.out.println("--- Basic Custom Functional Interface ---\n");

        // Using anonymous class
        MyFunctionalInterface anonymousImpl = new MyFunctionalInterface() {
            @Override
            public void execute() {
                System.out.println("Executing with anonymous class");
            }
        };

        // Using lambda expression
        MyFunctionalInterface lambdaImpl = () -> System.out.println("Executing with lambda expression");

        // Call the method
        anonymousImpl.execute();
        lambdaImpl.execute();

        // Using default method
        lambdaImpl.print("Using default method");

        // Using static method
        MyFunctionalInterface.printStatic("Using static method");

        // Calculator functional interface example
        System.out.println("\n--- Calculator Functional Interface ---\n");

        // Different implementations using lambda expressions
        Calculator1 addition = (x, y) -> x + y;
        Calculator1 subtraction = (x, y) -> x - y;
        Calculator1 multiplication = (x, y) -> x * y;
        Calculator1 division = (x, y) -> y != 0 ? x / y : 0; // Avoid division by zero

        System.out.println("Addition: 10 + 5 = " + addition.calculate(10, 5));
        System.out.println("Subtraction: 10 - 5 = " + subtraction.calculate(10, 5));
        System.out.println("Multiplication: 10 * 5 = " + multiplication.calculate(10, 5));
        System.out.println("Division: 10 / 5 = " + division.calculate(10, 5));
        System.out.println("Division by zero: 10 / 0 = " + division.calculate(10, 0));

        // Built-in Functional Interfaces
        System.out.println("\n--- Built-in Functional Interfaces ---\n");

        // 1. Predicate - Takes one input and returns boolean
        System.out.println("1. Predicate<T> Example:\n");
        demoPredicateInterface();

        // 2. Consumer - Takes one input and returns no result (void)
        System.out.println("\n2. Consumer<T> Example:\n");
        demoConsumerInterface();

        // 3. Function - Takes one input and produces one output
        System.out.println("\n3. Function<T,R> Example:\n");
        demoFunctionInterface();

        // 4. Supplier - Takes no input and returns a result
        System.out.println("\n4. Supplier<T> Example:\n");
        demoSupplierInterface();

        // 5. UnaryOperator - Special case of Function where input and output are the same type
        System.out.println("\n5. UnaryOperator<T> Example:\n");
        demoUnaryOperatorInterface();

        // 6. BinaryOperator - Takes two inputs of the same type and returns one output of that type
        System.out.println("\n6. BinaryOperator<T> Example:\n");
        demoBinaryOperatorInterface();

        // 7. BiFunction - Takes two inputs and returns one output
        System.out.println("\n7. BiFunction<T,U,R> Example:\n");
        demoBiFunctionInterface();
    }

    /**
     * Demonstrates the use of Predicate functional interface
     * Predicate<T> represents a predicate (boolean-valued function) of one argument
     */
    private static void demoPredicateInterface() {
        // Predicate for checking if a number is even
        Predicate<Integer> isEven = n -> n % 2 == 0;

        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));

        // Predicate for checking if a string starts with 'J'
        Predicate<String> startsWithJ = s -> s.startsWith("J");

        System.out.println("Does 'Java' start with 'J'? " + startsWithJ.test("Java"));
        System.out.println("Does 'Python' start with 'J'? " + startsWithJ.test("Python"));

        // Combining predicates using and(), or(), negate()
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Predicate for numbers greater than 5
        Predicate<Integer> greaterThan5 = n -> n > 5;

        // Predicate for even numbers greater than 5
        Predicate<Integer> evenAndGreaterThan5 = isEven.and(greaterThan5);

        // Predicate for even numbers or numbers greater than 5
        Predicate<Integer> evenOrGreaterThan5 = isEven.or(greaterThan5);

        // Predicate for odd numbers (negate of isEven)
        Predicate<Integer> isOdd = isEven.negate();

        System.out.println("\nFiltering a list of numbers:");
        System.out.println("Original list: " + numbers);

        System.out.println("Even numbers: " + filterList(numbers, isEven));
        System.out.println("Numbers greater than 5: " + filterList(numbers, greaterThan5));
        System.out.println("Even numbers greater than 5: " + filterList(numbers, evenAndGreaterThan5));
        System.out.println("Even numbers or numbers greater than 5: " + filterList(numbers, evenOrGreaterThan5));
        System.out.println("Odd numbers: " + filterList(numbers, isOdd));
    }

    /**
     * Helper method to filter a list using a predicate
     */
    private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    /**
     * Demonstrates the use of Consumer functional interface
     * Consumer<T> represents an operation that accepts a single input argument and returns no result
     */
    private static void demoConsumerInterface() {
        // Consumer that prints a string in uppercase
        Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());

        // Consumer that prints a string in lowercase
        Consumer<String> printLowerCase = s -> System.out.println(s.toLowerCase());

        // Consumer that prints the length of a string
        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());

        // Using accept() method
        System.out.println("Using accept() method:");
        printUpperCase.accept("Hello World");
        printLowerCase.accept("Hello World");
        printLength.accept("Hello World");

        // Chaining consumers using andThen()
        System.out.println("\nChaining consumers with andThen():");
        Consumer<String> printDetails = printUpperCase
                                         .andThen(printLowerCase)
                                         .andThen(printLength);

        printDetails.accept("Java Programming");

        // Example with a list of strings
        List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve");

        System.out.println("\nProcessing a list of names:");
        processStrings(names, s -> System.out.println("Name: " + s));
    }

    /**
     * Helper method to process a list of strings using a consumer
     */
    private static void processStrings(List<String> list, Consumer<String> consumer) {
        for (String item : list) {
            consumer.accept(item);
        }
    }

    /**
     * Demonstrates the use of Function functional interface
     * Function<T,R> represents a function that accepts one argument and produces a result
     */
    private static void demoFunctionInterface() {
        // Function that converts a string to its length
        Function<String, Integer> stringLength = s -> s.length();

        // Function that converts a string to uppercase
        Function<String, String> toUpperCase = s -> s.toUpperCase();

        // Using apply() method
        System.out.println("Using apply() method:");
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        System.out.println("Uppercase of 'hello': " + toUpperCase.apply("hello"));

        // Composing functions using compose() and andThen()
        System.out.println("\nComposing functions:");

        // First apply toUpperCase, then stringLength
        Function<String, Integer> lengthOfUpperCase = stringLength.compose(toUpperCase);
        System.out.println("Length of uppercase 'hello': " + lengthOfUpperCase.apply("hello"));

        // First apply stringLength, then double the result
        Function<String, Integer> doubleLengthOfString = stringLength.andThen(n -> n * 2);
        System.out.println("Double length of 'hello': " + doubleLengthOfString.apply("hello"));

        // Example with a list of strings
        List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve");

        System.out.println("\nMapping a list of names to their lengths:");
        List<Integer> nameLengths = mapList(names, stringLength);
        System.out.println("Names: " + names);
        System.out.println("Lengths: " + nameLengths);
    }

    /**
     * Helper method to map a list using a function
     */
    private static <T, R> List<R> mapList(List<T> list, Function<T, R> function) {
        List<R> mappedList = new ArrayList<>();
        for (T item : list) {
            mappedList.add(function.apply(item));
        }
        return mappedList;
    }

    /**
     * Demonstrates the use of Supplier functional interface
     * Supplier<T> represents a supplier of results, taking no arguments and returning a result
     */
    private static void demoSupplierInterface() {
        // Supplier that returns a random integer between 1 and 100
        Supplier<Integer> randomInt = () -> new Random().nextInt(100) + 1;

        // Supplier that returns the current timestamp
        Supplier<Long> currentTime = () -> System.currentTimeMillis();

        // Supplier that returns a random name
        Supplier<String> randomName = () -> {
            String[] names = {"John", "Jane", "Adam", "Eve", "Mike", "Sara"};
            return names[new Random().nextInt(names.length)];
        };

        // Using get() method
        System.out.println("Using get() method:");
        System.out.println("Random integer: " + randomInt.get());
        System.out.println("Current time: " + currentTime.get());
        System.out.println("Random name: " + randomName.get());

        // Generate a list of random integers
        System.out.println("\nGenerating a list of 5 random integers:");
        List<Integer> randomNumbers = generateList(5, randomInt);
        System.out.println(randomNumbers);

        // Generate a list of random names
        System.out.println("\nGenerating a list of 5 random names:");
        List<String> names = generateList(5, randomName);
        System.out.println(names);
    }

    /**
     * Helper method to generate a list using a supplier
     */
    private static <T> List<T> generateList(int size, Supplier<T> supplier) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * Demonstrates the use of UnaryOperator functional interface
     * UnaryOperator<T> represents an operation on a single operand that produces a result of the same type
     */
    private static void demoUnaryOperatorInterface() {
        // UnaryOperator that squares an integer
        UnaryOperator<Integer> square = n -> n * n;

        // UnaryOperator that doubles an integer
        UnaryOperator<Integer> doubleIt = n -> n * 2;

        // UnaryOperator that reverses a string
        UnaryOperator<String> reverse = s -> new StringBuilder(s).reverse().toString();

        // Using apply() method
        System.out.println("Using apply() method:");
        System.out.println("Square of 5: " + square.apply(5));
        System.out.println("Double of 5: " + doubleIt.apply(5));
        System.out.println("Reverse of 'hello': " + reverse.apply("hello"));

        // Composing UnaryOperators using andThen()
        System.out.println("\nComposing UnaryOperators:");
        Function<Integer, Integer> squareThenDouble = square.andThen(doubleIt);
        Function<Integer, Integer> doubleThenSquare = square.compose(doubleIt);

        System.out.println("Square then double 5: " + squareThenDouble.apply(5)); // (5^2) * 2 = 50
        System.out.println("Double then square 5: " + doubleThenSquare.apply(5)); // (5*2)^2 = 100

        // Example with a list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("\nTransforming a list of integers:");
        List<Integer> squaredNumbers = transformList(numbers, square);
        List<Integer> doubledNumbers = transformList(numbers, doubleIt);

        System.out.println("Original numbers: " + numbers);
        System.out.println("Squared numbers: " + squaredNumbers);
        System.out.println("Doubled numbers: " + doubledNumbers);
    }

    /**
     * Helper method to transform a list using a UnaryOperator
     */
    private static <T> List<T> transformList(List<T> list, UnaryOperator<T> operator) {
        List<T> transformedList = new ArrayList<>();
        for (T item : list) {
            transformedList.add(operator.apply(item));
        }
        return transformedList;
    }

    /**
     * Demonstrates the use of BinaryOperator functional interface
     * BinaryOperator<T> represents an operation upon two operands of the same type, producing a result of the same type
     */
    private static void demoBinaryOperatorInterface() {
        // BinaryOperator that adds two integers
        BinaryOperator<Integer> add = (a, b) -> a + b;

        // BinaryOperator that multiplies two integers
        BinaryOperator<Integer> multiply = (a, b) -> a * b;

        // BinaryOperator that returns the maximum of two integers
        BinaryOperator<Integer> max = (a, b) -> Math.max(a, b);

        // BinaryOperator that concatenates two strings
        BinaryOperator<String> concat = (s1, s2) -> s1 + s2;

        // Using apply() method
        System.out.println("Using apply() method:");
        System.out.println("5 + 3 = " + add.apply(5, 3));
        System.out.println("5 * 3 = " + multiply.apply(5, 3));
        System.out.println("Max of 5 and 3 = " + max.apply(5, 3));
        System.out.println("Concat 'Hello' and 'World' = " + concat.apply("Hello", "World"));

        // Using BinaryOperator.maxBy() and BinaryOperator.minBy()
        System.out.println("\nUsing BinaryOperator.maxBy() and BinaryOperator.minBy():");
        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(Integer::compare);
        BinaryOperator<Integer> minBy = BinaryOperator.minBy(Integer::compare);

        System.out.println("Max of 5 and 3 using maxBy: " + maxBy.apply(5, 3));
        System.out.println("Min of 5 and 3 using minBy: " + minBy.apply(5, 3));

        // Example with a list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("\nReducing a list of integers:");
        Integer sum = reduceList(numbers, 0, add);
        Integer product = reduceList(numbers, 1, multiply);

        System.out.println("Numbers: " + numbers);
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
    }

    /**
     * Helper method to reduce a list using a BinaryOperator
     */
    private static <T> T reduceList(List<T> list, T identity, BinaryOperator<T> operator) {
        T result = identity;
        for (T item : list) {
            result = operator.apply(result, item);
        }
        return result;
    }

    /**
     * Demonstrates the use of BiFunction functional interface
     * BiFunction<T,U,R> represents a function that accepts two arguments and produces a result
     */
    private static void demoBiFunctionInterface() {
        // BiFunction that adds an integer and a double
        BiFunction<Integer, Double, Double> addMixed = (i, d) -> i + d;

        // BiFunction that concatenates two strings with a separator
        BiFunction<String, String, String> concatWithSeparator = (s1, s2) -> s1 + " - " + s2;

        // BiFunction that returns a Person object from a name and age
        BiFunction<String, Integer, Person2> createPerson = (name, age) -> new Person2(name, age);

        // Using apply() method
        System.out.println("Using apply() method:");
        System.out.println("5 + 3.5 = " + addMixed.apply(5, 3.5));
        System.out.println("Concat 'Hello' and 'World' with separator: " + 
                         concatWithSeparator.apply("Hello", "World"));

        Person2 person = createPerson.apply("John", 30);
        System.out.println("Created person: " + person);

        // Composing BiFunction with Function using andThen()
        System.out.println("\nComposing BiFunction with Function:");
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Function<Integer, String> convertToString = n -> "Result: " + n;

        String result = multiply.andThen(convertToString).apply(5, 3);
        System.out.println(result);

        // Example with pairs of values
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(10, 20, 30, 40, 50);

        System.out.println("\nCombining two lists of integers:");
        List<Integer> combined = zipLists(numbers1, numbers2, (a, b) -> a + b);

        System.out.println("List 1: " + numbers1);
        System.out.println("List 2: " + numbers2);
        System.out.println("Combined (sum): " + combined);
    }

    /**
     * Helper method to zip two lists using a BiFunction
     */
    private static <T, U, R> List<R> zipLists(List<T> list1, List<U> list2, BiFunction<T, U, R> function) {
        List<R> result = new ArrayList<>();
        int minSize = Math.min(list1.size(), list2.size());

        for (int i = 0; i < minSize; i++) {
            result.add(function.apply(list1.get(i), list2.get(i)));
        }

        return result;
    }

    /**
     * Person class for BiFunction example
     */
    static class Person2 {
        private String name;
        private int age;

        public Person2(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
}
