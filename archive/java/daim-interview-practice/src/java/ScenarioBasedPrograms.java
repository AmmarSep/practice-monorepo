package java;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class demonstrates various scenario-based Java programming problems
 * 1. Singleton class Java program
 * 2. Avoid duplicates in HashMap
 * 3. List to Set
 * 4. List to Map
 * 5. Stream map scenario based
 * 6. Various ways of sorting
 * 7. equals() vs == (scenario based)
 */
public class ScenarioBasedPrograms {

    public static void main(String[] args) {
        System.out.println("===== Scenario-Based Programs Demonstration =====");
        
        // 1. Singleton class Java program
        demonstrateSingleton();
        
        // 2. Avoid duplicates in HashMap
        demonstrateAvoidDuplicatesInHashMap();
        
        // 3. List to Set
        demonstrateListToSet();
        
        // 4. List to Map
        demonstrateListToMap();
        
        // 5. Stream map scenario based
        demonstrateStreamMap();
        
        // 6. Various ways of sorting
        demonstrateVariousWaysOfSorting();
        
        // 7. equals() vs == (scenario based)
        demonstrateEqualsVsDoubleEquals();
    }
    
    /**
     * 1. Singleton class Java program
     * 
     * Singleton pattern ensures a class has only one instance and provides a global point of access to it.
     * There are multiple ways to implement the Singleton pattern in Java.
     */
    private static void demonstrateSingleton() {
        System.out.println("\n1. Singleton Class Java Program:\n");
        
        // Eager initialization
        System.out.println("Eager Initialization Singleton:");
        EagerInitializedSingleton eagerSingleton1 = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton eagerSingleton2 = EagerInitializedSingleton.getInstance();
        System.out.println("Are both instances the same object? " + (eagerSingleton1 == eagerSingleton2));
        
        // Lazy initialization
        System.out.println("\nLazy Initialization Singleton:");
        LazyInitializedSingleton lazySingleton1 = LazyInitializedSingleton.getInstance();
        LazyInitializedSingleton lazySingleton2 = LazyInitializedSingleton.getInstance();
        System.out.println("Are both instances the same object? " + (lazySingleton1 == lazySingleton2));
        
        // Thread-safe singleton
        System.out.println("\nThread-Safe Singleton:");
        ThreadSafeSingleton threadSafeSingleton1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton threadSafeSingleton2 = ThreadSafeSingleton.getInstance();
        System.out.println("Are both instances the same object? " + (threadSafeSingleton1 == threadSafeSingleton2));
        
        // Double-checked locking
        System.out.println("\nDouble-Checked Locking Singleton:");
        DCLSingleton dclSingleton1 = DCLSingleton.getInstance();
        DCLSingleton dclSingleton2 = DCLSingleton.getInstance();
        System.out.println("Are both instances the same object? " + (dclSingleton1 == dclSingleton2));
        
        // Bill Pugh Singleton
        System.out.println("\nBill Pugh Singleton:");
        BillPughSingleton billPughSingleton1 = BillPughSingleton.getInstance();
        BillPughSingleton billPughSingleton2 = BillPughSingleton.getInstance();
        System.out.println("Are both instances the same object? " + (billPughSingleton1 == billPughSingleton2));
        
        // Enum Singleton
        System.out.println("\nEnum Singleton:");
        EnumSingleton enumSingleton1 = EnumSingleton.INSTANCE;
        EnumSingleton enumSingleton2 = EnumSingleton.INSTANCE;
        System.out.println("Are both instances the same object? " + (enumSingleton1 == enumSingleton2));
        
        // Using the singletons
        eagerSingleton1.showMessage();
        lazySingleton1.showMessage();
        threadSafeSingleton1.showMessage();
        dclSingleton1.showMessage();
        billPughSingleton1.showMessage();
        enumSingleton1.showMessage();
    }
    
    /**
     * 2. Avoid duplicates in HashMap
     * 
     * This demonstrates how to handle duplicate keys in a HashMap
     * and different strategies for dealing with them.
     */
    private static void demonstrateAvoidDuplicatesInHashMap() {
        System.out.println("\n2. Avoid Duplicates in HashMap:\n");
        
        // Create a HashMap with some initial data
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        
        System.out.println("Initial HashMap: " + map);
        
        // Scenario 1: Simple put - overwrites existing value
        System.out.println("\nScenario 1: Simple put (overwrites existing value)");
        map.put("A", 10);  // This will overwrite the existing value for key "A"
        System.out.println("After map.put(\"A\", 10): " + map);
        
        // Scenario 2: Check if key exists before putting
        System.out.println("\nScenario 2: Check if key exists before putting");
        if (!map.containsKey("D")) {
            map.put("D", 4);
        }
        System.out.println("After checking and adding 'D': " + map);
        
        // Try to add an existing key
        if (!map.containsKey("B")) {
            map.put("B", 20);  // This won't execute because "B" already exists
        }
        System.out.println("After trying to add 'B' again: " + map);
        
        // Scenario 3: Using putIfAbsent (Java 8+)
        System.out.println("\nScenario 3: Using putIfAbsent");
        map.putIfAbsent("E", 5);  // Adds the key-value pair since "E" doesn't exist
        System.out.println("After map.putIfAbsent(\"E\", 5): " + map);
        
        map.putIfAbsent("C", 30);  // Doesn't change anything since "C" already exists
        System.out.println("After map.putIfAbsent(\"C\", 30): " + map);
        
        // Scenario 4: Using compute or merge to handle duplicates with custom logic
        System.out.println("\nScenario 4: Using compute or merge");
        
        // Using compute to increment the value if the key exists
        map.compute("B", (key, oldValue) -> (oldValue == null) ? 1 : oldValue + 1);
        System.out.println("After incrementing 'B' using compute: " + map);
        
        // Using merge to add values if the key exists
        map.merge("A", 5, Integer::sum);  // Adds 5 to the existing value of "A"
        System.out.println("After merging 'A' with 5: " + map);
        
        // Scenario 5: Using a custom class as key
        System.out.println("\nScenario 5: Using a custom class as key");
        
        Map<Person, String> personMap = new HashMap<>();
        
        Person p1 = new Person("John", 30);
        Person p2 = new Person("Jane", 25);
        Person p3 = new Person("John", 30);  // Same name and age as p1
        
        personMap.put(p1, "Developer");
        personMap.put(p2, "Designer");
        
        System.out.println("Person map after adding p1 and p2: " + personMap);
        System.out.println("p1.equals(p3): " + p1.equals(p3));
        System.out.println("p1.hashCode(): " + p1.hashCode() + ", p3.hashCode(): " + p3.hashCode());
        
        // This will add a new entry if equals() and hashCode() are not properly implemented
        // If they are properly implemented, it will overwrite the existing entry
        personMap.put(p3, "Manager");
        
        System.out.println("Person map after adding p3: " + personMap);
        System.out.println("Size of person map: " + personMap.size());
    }
    
    /**
     * 3. List to Set
     * 
     * This demonstrates different ways to convert a List to a Set in Java
     */
    private static void demonstrateListToSet() {
        System.out.println("\n3. List to Set:\n");
        
        // Create a list with duplicate elements
        List<String> fruitsList = Arrays.asList("apple", "banana", "orange", "apple", "grape", "banana", "kiwi");
        System.out.println("Original list: " + fruitsList);
        System.out.println("List size: " + fruitsList.size());
        
        // Method 1: Using the HashSet constructor
        Set<String> fruitsSet1 = new HashSet<>(fruitsList);
        System.out.println("\nMethod 1: Using HashSet constructor");
        System.out.println("Set: " + fruitsSet1);
        System.out.println("Set size: " + fruitsSet1.size());
        
        // Method 2: Using addAll method
        Set<String> fruitsSet2 = new HashSet<>();
        fruitsSet2.addAll(fruitsList);
        System.out.println("\nMethod 2: Using addAll method");
        System.out.println("Set: " + fruitsSet2);
        System.out.println("Set size: " + fruitsSet2.size());
        
        // Method 3: Using Java 8 Stream API
        Set<String> fruitsSet3 = fruitsList.stream().collect(Collectors.toSet());
        System.out.println("\nMethod 3: Using Java 8 Stream API with Collectors.toSet()");
        System.out.println("Set: " + fruitsSet3);
        System.out.println("Set size: " + fruitsSet3.size());
        
        // Method 4: Using Stream API with specific Set implementation
        Set<String> fruitsSet4 = fruitsList.stream().collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("\nMethod 4: Using Stream API with LinkedHashSet");
        System.out.println("Set: " + fruitsSet4);
        System.out.println("Set size: " + fruitsSet4.size());
        
        // Method 5: Using TreeSet (sorted set)
        Set<String> fruitsSet5 = new TreeSet<>(fruitsList);
        System.out.println("\nMethod 5: Using TreeSet (sorted set)");
        System.out.println("Set: " + fruitsSet5);
        System.out.println("Set size: " + fruitsSet5.size());
        
        // Demonstrating with custom objects
        List<Person> personList = Arrays.asList(
            new Person("John", 30),
            new Person("Jane", 25),
            new Person("John", 30),  // Duplicate if equals() and hashCode() are properly implemented
            new Person("Bob", 35)
        );
        
        System.out.println("\nList of persons: " + personList);
        System.out.println("Person list size: " + personList.size());
        
        Set<Person> personSet = new HashSet<>(personList);
        System.out.println("Person set: " + personSet);
        System.out.println("Person set size: " + personSet.size());
    }
    
    /**
     * 4. List to Map
     * 
     * This demonstrates different ways to convert a List to a Map in Java
     */
    private static void demonstrateListToMap() {
        System.out.println("\n4. List to Map:\n");
        
        // Create a list of persons
        List<Person> persons = Arrays.asList(
            new Person("John", 30),
            new Person("Jane", 25),
            new Person("Bob", 35),
            new Person("Alice", 28)
        );
        
        System.out.println("Original list of persons: " + persons);
        
        // Method 1: Using a loop
        Map<String, Integer> nameToAgeMap1 = new HashMap<>();
        for (Person person : persons) {
            nameToAgeMap1.put(person.getName(), person.getAge());
        }
        
        System.out.println("\nMethod 1: Using a loop");
        System.out.println("Map: " + nameToAgeMap1);
        
        // Method 2: Using Java 8 Stream API with Collectors.toMap
        Map<String, Integer> nameToAgeMap2 = persons.stream()
                                                   .collect(Collectors.toMap(
                                                       Person::getName,
                                                       Person::getAge
                                                   ));
        
        System.out.println("\nMethod 2: Using Java 8 Stream API with Collectors.toMap");
        System.out.println("Map: " + nameToAgeMap2);
        
        // Method 3: Using Stream API with specific Map implementation
        Map<String, Integer> nameToAgeMap3 = persons.stream()
                                                   .collect(Collectors.toMap(
                                                       Person::getName,
                                                       Person::getAge,
                                                       (existing, replacement) -> existing, // Merge function for duplicates
                                                       LinkedHashMap::new // Specific map implementation
                                                   ));
        
        System.out.println("\nMethod 3: Using Stream API with LinkedHashMap");
        System.out.println("Map: " + nameToAgeMap3);
        
        // Method 4: Using person objects as values
        Map<String, Person> nameToPersonMap = persons.stream()
                                                    .collect(Collectors.toMap(
                                                        Person::getName,
                                                        person -> person
                                                    ));
        
        System.out.println("\nMethod 4: Using person objects as values");
        System.out.println("Map: " + nameToPersonMap);
        
        // Method 5: Handling duplicate keys
        List<Person> personsWithDuplicates = Arrays.asList(
            new Person("John", 30),
            new Person("Jane", 25),
            new Person("John", 35),  // Duplicate key "John"
            new Person("Alice", 28)
        );
        
        System.out.println("\nList with duplicate keys: " + personsWithDuplicates);
        
        try {
            // This will throw an IllegalStateException due to duplicate keys
            Map<String, Integer> mapWithDuplicates = personsWithDuplicates.stream()
                                                                         .collect(Collectors.toMap(
                                                                             Person::getName,
                                                                             Person::getAge
                                                                         ));
            System.out.println("Map: " + mapWithDuplicates); // This line won't execute
        } catch (IllegalStateException e) {
            System.out.println("Exception caught: " + e.getMessage());
            
            // Handling duplicate keys by keeping the last occurrence
            Map<String, Integer> mapWithLastWins = personsWithDuplicates.stream()
                                                                       .collect(Collectors.toMap(
                                                                           Person::getName,
                                                                           Person::getAge,
                                                                           (oldValue, newValue) -> newValue // Keep the new value
                                                                       ));
            
            System.out.println("Map with last value winning: " + mapWithLastWins);
            
            // Handling duplicate keys by keeping the first occurrence
            Map<String, Integer> mapWithFirstWins = personsWithDuplicates.stream()
                                                                        .collect(Collectors.toMap(
                                                                            Person::getName,
                                                                            Person::getAge,
                                                                            (oldValue, newValue) -> oldValue // Keep the old value
                                                                        ));
            
            System.out.println("Map with first value winning: " + mapWithFirstWins);
        }
        
        // Method 6: Grouping by a property
        Map<Integer, List<Person>> ageToPersonsMap = persons.stream()
                                                          .collect(Collectors.groupingBy(Person::getAge));
        
        System.out.println("\nMethod 6: Grouping by age");
        System.out.println("Map: " + ageToPersonsMap);
    }
    
    /**
     * 5. Stream map scenario based
     * 
     * This demonstrates various scenarios using the map operation in Java 8 Streams
     */
    private static void demonstrateStreamMap() {
        System.out.println("\n5. Stream Map Scenario Based:\n");
        
        // Scenario 1: Transform a list of strings to uppercase
        List<String> names = Arrays.asList("john", "jane", "bob", "alice");
        System.out.println("Original list of names: " + names);
        
        List<String> uppercaseNames = names.stream()
                                          .map(String::toUpperCase)
                                          .collect(Collectors.toList());
        
        System.out.println("Uppercase names: " + uppercaseNames);
        
        // Scenario 2: Calculate the length of each string
        List<Integer> nameLengths = names.stream()
                                        .map(String::length)
                                        .collect(Collectors.toList());
        
        System.out.println("Name lengths: " + nameLengths);
        
        // Scenario 3: Transform a list of objects
        List<Person> persons = Arrays.asList(
            new Person("John", 30),
            new Person("Jane", 25),
            new Person("Bob", 35),
            new Person("Alice", 28)
        );
        
        System.out.println("\nOriginal list of persons: " + persons);
        
        // Extract names from persons
        List<String> extractedNames = persons.stream()
                                           .map(Person::getName)
                                           .collect(Collectors.toList());
        
        System.out.println("Extracted names: " + extractedNames);
        
        // Create a new list of PersonDTO objects
        List<PersonDTO> personDTOs = persons.stream()
                                          .map(p -> new PersonDTO(p.getName(), p.getAge()))
                                          .collect(Collectors.toList());
        
        System.out.println("Person DTOs: " + personDTOs);
        
        // Scenario 4: Chaining multiple map operations
        List<String> nameDescriptions = persons.stream()
                                             .map(p -> p.getName() + " (" + p.getAge() + ")")
                                             .map(String::toUpperCase)
                                             .collect(Collectors.toList());
        
        System.out.println("\nName descriptions: " + nameDescriptions);
        
        // Scenario 5: Using flatMap to flatten nested collections
        List<List<Integer>> nestedLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        
        System.out.println("\nNested lists: " + nestedLists);
        
        List<Integer> flattenedList = nestedLists.stream()
                                               .flatMap(Collection::stream)
                                               .collect(Collectors.toList());
        
        System.out.println("Flattened list: " + flattenedList);
        
        // Scenario 6: Combining map with other stream operations
        double averageAge = persons.stream()
                                  .map(Person::getAge)
                                  .mapToInt(Integer::intValue)
                                  .average()
                                  .orElse(0.0);
        
        System.out.println("\nAverage age: " + averageAge);
        
        // Find the longest name
        String longestName = persons.stream()
                                   .map(Person::getName)
                                   .max(Comparator.comparingInt(String::length))
                                   .orElse("");
        
        System.out.println("Longest name: " + longestName);
    }
    
    /**
     * 6. Various ways of sorting
     * 
     * This demonstrates different ways to sort collections in Java
     */
    private static void demonstrateVariousWaysOfSorting() {
        System.out.println("\n6. Various Ways of Sorting:\n");
        
        // Sorting arrays
        System.out.println("Sorting Arrays:");
        
        // Sorting an array of primitives
        int[] numbers = {5, 2, 9, 1, 5, 6};
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
        
        // Sorting an array of objects
        String[] fruits = {"banana", "apple", "orange", "grape", "kiwi"};
        System.out.println("\nOriginal string array: " + Arrays.toString(fruits));
        
        Arrays.sort(fruits);
        System.out.println("Sorted string array: " + Arrays.toString(fruits));
        
        // Sorting part of an array
        int[] partialSort = {5, 2, 9, 1, 5, 6};
        System.out.println("\nOriginal array: " + Arrays.toString(partialSort));
        
        Arrays.sort(partialSort, 1, 4);  // Sort elements at index 1, 2, and 3
        System.out.println("Partially sorted array: " + Arrays.toString(partialSort));
        
        // Sorting in reverse order
        Integer[] boxedNumbers = {5, 2, 9, 1, 5, 6};
        System.out.println("\nOriginal boxed array: " + Arrays.toString(boxedNumbers));
        
        Arrays.sort(boxedNumbers, Collections.reverseOrder());
        System.out.println("Reverse sorted array: " + Arrays.toString(boxedNumbers));
        
        // Sorting Lists
        System.out.println("\nSorting Lists:");
        
        List<String> fruitsList = new ArrayList<>(Arrays.asList("banana", "apple", "orange", "grape", "kiwi"));
        System.out.println("Original list: " + fruitsList);
        
        // Using Collections.sort
        Collections.sort(fruitsList);
        System.out.println("Sorted list: " + fruitsList);
        
        // Using List.sort (Java 8+)
        fruitsList.sort(Comparator.naturalOrder());
        System.out.println("Sorted list (natural order): " + fruitsList);
        
        fruitsList.sort(Comparator.reverseOrder());
        System.out.println("Sorted list (reverse order): " + fruitsList);
        
        // Sorting by string length
        fruitsList.sort(Comparator.comparing(String::length));
        System.out.println("Sorted by length: " + fruitsList);
        
        // Sorting custom objects
        System.out.println("\nSorting Custom Objects:");
        
        List<Person> persons = new ArrayList<>(Arrays.asList(
            new Person("John", 30),
            new Person("Jane", 25),
            new Person("Bob", 35),
            new Person("Alice", 28),
            new Person("John", 22)
        ));
        
        System.out.println("Original persons list: " + persons);
        
        // Sort by name
        persons.sort(Comparator.comparing(Person::getName));
        System.out.println("Sorted by name: " + persons);
        
        // Sort by age
        persons.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("Sorted by age: " + persons);
        
        // Sort by name and then by age
        persons.sort(Comparator.comparing(Person::getName).thenComparingInt(Person::getAge));
        System.out.println("Sorted by name and then by age: " + persons);
        
        // Using Java 8 Streams for sorting
        System.out.println("\nSorting with Java 8 Streams:");
        
        List<Person> sortedByName = persons.stream()
                                         .sorted(Comparator.comparing(Person::getName))
                                         .collect(Collectors.toList());
        
        System.out.println("Stream sorted by name: " + sortedByName);
        
        List<Person> sortedByAgeDesc = persons.stream()
                                            .sorted(Comparator.comparingInt(Person::getAge).reversed())
                                            .collect(Collectors.toList());
        
        System.out.println("Stream sorted by age (descending): " + sortedByAgeDesc);
    }
    
    /**
     * 7. equals() vs == (scenario based)
     * 
     * This demonstrates the difference between equals() method and == operator in Java
     */
    private static void demonstrateEqualsVsDoubleEquals() {
        System.out.println("\n7. equals() vs == (Scenario Based):\n");
        
        // Scenario 1: Primitive types
        System.out.println("Scenario 1: Primitive types");
        
        int a = 5;
        int b = 5;
        int c = 10;
        
        System.out.println("a == b: " + (a == b));  // true
        System.out.println("a == c: " + (a == c));  // false
        
        // Scenario 2: String literals
        System.out.println("\nScenario 2: String literals");
        
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "world";
        
        System.out.println("s1 == s2: " + (s1 == s2));          // true (both reference the same string in the pool)
        System.out.println("s1 == s3: " + (s1 == s3));          // false
        System.out.println("s1.equals(s2): " + s1.equals(s2));  // true
        System.out.println("s1.equals(s3): " + s1.equals(s3));  // false
        
        // Scenario 3: String objects
        System.out.println("\nScenario 3: String objects");
        
        String s4 = new String("hello");
        String s5 = new String("hello");
        
        System.out.println("s1 == s4: " + (s1 == s4));          // false (different objects)
        System.out.println("s4 == s5: " + (s4 == s5));          // false (different objects)
        System.out.println("s1.equals(s4): " + s1.equals(s4));  // true (same content)
        System.out.println("s4.equals(s5): " + s4.equals(s5));  // true (same content)
        
        // Scenario 4: String interning
        System.out.println("\nScenario 4: String interning");
        
        String s6 = s4.intern();  // Get the canonical representation from the pool
        
        System.out.println("s1 == s6: " + (s1 == s6));  // true (s6 now references the same pooled string as s1)
        
        // Scenario 5: Custom objects
        System.out.println("\nScenario 5: Custom objects");
        
        Person p1 = new Person("John", 30);
        Person p2 = new Person("John", 30);
        Person p3 = p1;  // Same reference as p1
        
        System.out.println("p1 == p2: " + (p1 == p2));          // false (different objects)
        System.out.println("p1 == p3: " + (p1 == p3));          // true (same reference)
        System.out.println("p1.equals(p2): " + p1.equals(p2));  // true (if equals is properly implemented)
        System.out.println("p1.equals(p3): " + p1.equals(p3));  // true
        
        // Scenario 6: Wrapper classes
        System.out.println("\nScenario 6: Wrapper classes");
        
        Integer i1 = 127;
        Integer i2 = 127;
        Integer i3 = 128;
        Integer i4 = 128;
        
        System.out.println("i1 == i2: " + (i1 == i2));  // true (Integer caches values between -128 and 127)
        System.out.println("i3 == i4: " + (i3 == i4));  // false (outside the cache range)
        
        System.out.println("i1.equals(i2): " + i1.equals(i2));  // true
        System.out.println("i3.equals(i4): " + i3.equals(i4));  // true
        
        // Scenario 7: null comparison
        System.out.println("\nScenario 7: null comparison");
        
        String nullStr = null;
        
        System.out.println("nullStr == null: " + (nullStr == null));  // true
        
        // This would throw NullPointerException:
        // System.out.println("nullStr.equals(null): " + nullStr.equals(null));
        
        // Safe way to compare potentially null objects
        System.out.println("Objects.equals(nullStr, null): " + Objects.equals(nullStr, null));  // true
        System.out.println("Objects.equals(s1, nullStr): " + Objects.equals(s1, nullStr));      // false
        System.out.println("Objects.equals(s1, s2): " + Objects.equals(s1, s2));                // true
    }
    
    // Person class for demonstrations
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
            return name + "(" + age + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            
            Person person = (Person) obj;
            return age == person.age && Objects.equals(name, person.name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
    
    // DTO class for map demonstration
    static class PersonDTO {
        private String name;
        private int age;
        
        public PersonDTO(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}

// 1. Eager Initialization Singleton
class EagerInitializedSingleton {
    // Private static instance created at class loading time
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();
    
    // Private constructor to prevent instantiation
    private EagerInitializedSingleton() {
        System.out.println("EagerInitializedSingleton instance created");
    }
    
    // Public method to provide access to the instance
    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
    
    public void showMessage() {
        System.out.println("Hello from EagerInitializedSingleton");
    }
}

// 2. Lazy Initialization Singleton
class LazyInitializedSingleton {
    // Private static instance, not created until needed
    private static LazyInitializedSingleton instance;
    
    // Private constructor to prevent instantiation
    private LazyInitializedSingleton() {
        System.out.println("LazyInitializedSingleton instance created");
    }
    
    // Public method to provide access to the instance (not thread-safe)
    public static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
    
    public void showMessage() {
        System.out.println("Hello from LazyInitializedSingleton");
    }
}

// 3. Thread-Safe Singleton
class ThreadSafeSingleton {
    // Private static instance, not created until needed
    private static ThreadSafeSingleton instance;
    
    // Private constructor to prevent instantiation
    private ThreadSafeSingleton() {
        System.out.println("ThreadSafeSingleton instance created");
    }
    
    // Public synchronized method to provide access to the instance (thread-safe but inefficient)
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
    
    public void showMessage() {
        System.out.println("Hello from ThreadSafeSingleton");
    }
}

// 4. Double-Checked Locking Singleton
class DCLSingleton {
    // Private volatile static instance
    private static volatile DCLSingleton instance;
    
    // Private constructor to prevent instantiation
    private DCLSingleton() {
        System.out.println("DCLSingleton instance created");
    }
    
    // Public method with double-checked locking
    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
    
    public void showMessage() {
        System.out.println("Hello from DCLSingleton");
    }
}

// 5. Bill Pugh Singleton (using static inner helper class)
class BillPughSingleton {
    // Private constructor to prevent instantiation
    private BillPughSingleton() {
        System.out.println("BillPughSingleton instance created");
    }
    
    // Static inner helper class - loaded only when getInstance() is called
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    // Public method to provide access to the instance
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
    
    public void showMessage() {
        System.out.println("Hello from BillPughSingleton");
    }
}

// 6. Enum Singleton
enum EnumSingleton {
    INSTANCE;
    
    EnumSingleton() {
        System.out.println("EnumSingleton instance created");
    }
    
    public void showMessage() {
        System.out.println("Hello from EnumSingleton");
    }
}