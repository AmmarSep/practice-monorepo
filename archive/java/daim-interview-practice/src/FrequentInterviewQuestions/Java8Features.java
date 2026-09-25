package FrequentInterviewQuestions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class demonstrates the key features introduced in Java 8
 * Java 8 was a major release with significant enhancements to the language
 */
public class Java8Features {

    public static void main(String[] args) {
        System.out.println("===== Java 8 Features Demonstration =====");

        // Create a list for demonstrations
        List<String> languages = Arrays.asList("Java", "Python", "JavaScript", "C#", "Ruby");

        // 1. Lambda Expressions
        demonstrateLambdaExpressions(languages);

        // 2. Stream API
        demonstrateStreamAPI(languages);

        // 3. Functional Interfaces
        demonstrateFunctionalInterfaces();

        // 4. Default and Static Methods in Interfaces
        demonstrateDefaultMethods();

        // 5. Optional Class
        demonstrateOptional();

        // 6. New Date/Time API
        demonstrateDateTime();

        // 7. Method References
        demonstrateMethodReferences(languages);
    }

    private static void demonstrateLambdaExpressions(List<String> languages) {
        System.out.println("\n1. Lambda Expressions:");

        // Before Java 8
        System.out.println("Traditional anonymous class:");
        languages.forEach(new java.util.function.Consumer<String>() {
            @Override
            public void accept(String lang) {
                System.out.println("  " + lang);
            }
        });

        // With Lambda Expression
        System.out.println("\nUsing Lambda:");
        languages.forEach(lang -> System.out.println("  " + lang));

        // Even shorter with method reference (will be covered later)
        System.out.println("\nUsing Method Reference:");
        languages.forEach(System.out::println);
    }

    private static void demonstrateStreamAPI(List<String> languages) {
        System.out.println("\n2. Stream API:");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filter operation
        List<Integer> evenNumbers = numbers.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);

        // Map operation
        List<Integer> squaredNumbers = numbers.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());
        System.out.println("Squared numbers: " + squaredNumbers);

        // Reduce operation
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("Sum of all numbers: " + sum);

        // Multiple operations in a pipeline
        int sumOfSquaresOfEven = numbers.stream()
                .filter(num -> num % 2 == 0) // Keep only even numbers
                .map(num -> num * num)       // Square each number
                .reduce(0, Integer::sum);    // Sum them up using method reference
        System.out.println("Sum of squares of even numbers: " + sumOfSquaresOfEven);

        // Other common operations
        long count = languages.stream()
                .filter(lang -> lang.length() > 4)
                .count();
        System.out.println("Languages with more than 4 characters: " + count);

        // Finding an element
        Optional<String> firstJLanguage = languages.stream()
                .filter(lang -> lang.startsWith("J"))
                .findFirst();
        System.out.println("First language starting with J: " + 
                firstJLanguage.orElse("Not found"));
    }

    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n3. Functional Interfaces:");

        // Predicate - takes one argument, returns boolean
        System.out.println("Using Predicate:");
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));

        // Function - takes one argument, returns a value
        System.out.println("\nUsing Function:");
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Java': " + stringLength.apply("Java"));

        // Chaining functions
        Function<Integer, Integer> doubled = num -> num * 2;
        Function<String, Integer> lengthThenDouble = stringLength.andThen(doubled);
        System.out.println("Double the length of 'Python': " + 
                lengthThenDouble.apply("Python"));
    }

    private static void demonstrateDefaultMethods() {
        System.out.println("\n4. Default and Static Methods in Interfaces:");

        // Create an instance of a class implementing our interface
        Java8Vehicle car = new Java8Car();
        car.startEngine();
        car.honk(); // Using the default method

        // Using static method from interface
        int wheelCount = Java8Vehicle.getStandardWheelCount();
        System.out.println("Standard wheel count: " + wheelCount);
    }

    private static void demonstrateOptional() {
        System.out.println("\n5. Optional Class:");

        // Traditional null check
        String nullableName = null;
        String result;

        if (nullableName != null) {
            result = nullableName.toUpperCase();
        } else {
            result = "Name not provided";
        }
        System.out.println("Traditional approach: " + result);

        // Using Optional
        Optional<String> optionalName = Optional.ofNullable(nullableName);
        String resultWithOptional = optionalName
                .map(String::toUpperCase)
                .orElse("Name not provided");
        System.out.println("Using Optional: " + resultWithOptional);

        // Creating non-empty Optional
        Optional<String> nonEmptyOptional = Optional.of("Java");
        System.out.println("Is non-empty optional present? " + nonEmptyOptional.isPresent());
        nonEmptyOptional.ifPresent(name -> System.out.println("Name is: " + name));
    }

    private static void demonstrateDateTime() {
        System.out.println("\n6. New Date/Time API:");

        // Current date
        LocalDate today = LocalDate.now();
        System.out.println("Today's date: " + today);

        // Adding days
        LocalDate nextWeek = today.plusDays(7);
        System.out.println("Date after 7 days: " + nextWeek);

        // Current date and time
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current date and time: " + now);

        // Creating specific date
        LocalDate dateOfBirth = LocalDate.of(1990, 5, 15);
        System.out.println("Date of birth: " + dateOfBirth);

        // Comparing dates
        boolean isBefore = dateOfBirth.isBefore(today);
        System.out.println("Is birth date before today? " + isBefore);
    }

    private static void demonstrateMethodReferences(List<String> languages) {
        System.out.println("\n7. Method References:");

        // Static method reference
        languages.forEach(Java8Features::printInUpperCase);

        // Instance method reference of particular object
        StringProcessor processor = new StringProcessor();
        languages.forEach(processor::process);

        // Instance method reference of arbitrary object of particular type
        languages.sort(String::compareToIgnoreCase);
        System.out.println("Sorted languages: " + languages);
    }

    // Helper method for method reference demo
    private static void printInUpperCase(String str) {
        System.out.println(str.toUpperCase());
    }

    // Helper class for method reference demo
    static class StringProcessor {
        public void process(String str) {
            System.out.println("Processing: " + str);
        }
    }
}

// Interface with default and static methods for demonstration
interface Java8Vehicle {
    void startEngine();

    // Default method
    default void honk() {
        System.out.println("Beep beep!");
    }

    // Static method
    static int getStandardWheelCount() {
        return 4;
    }
}

// Implementation of the interface
class Java8Car implements Java8Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Car engine started");
    }
}
