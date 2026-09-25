package java;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class demonstrates various Java programming problems and their solutions (Part 2)
 * 11. Max element in an array
 * 12. Second largest element in array
 * 13. Sum of odd and even numbers in an array
 * 14. Swap 2 numbers
 * 15. Array splitting with equal sum
 * 16. Sort employees based on name and age
 * 17. Finding the first non-repeated character using Java 8 streams
 * 18. Finding the second non-repeating character using Java 8 streams
 * 19. Finding duplicates in a list and their occurrences using streams
 * 20. Finding the second non-repeated number using Java 8
 */
public class JavaPrograms2 {

    public static void main(String[] args) {
        System.out.println("===== Java Programs Demonstration (Part 2) =====");
        
        // 11. Max element in an array
        findMaxElement();
        
        // 12. Second largest element in array
        findSecondLargestElement();
        
        // 13. Sum of odd and even numbers in an array
        sumOddEvenNumbers();
        
        // 14. Swap 2 numbers
        swapNumbers();
        
        // 15. Array splitting with equal sum
        splitArrayWithEqualSum();
        
        // 16. Sort employees based on name and age
        sortEmployees();
        
        // 17. Finding the first non-repeated character using Java 8 streams
        findFirstNonRepeatedChar();
        
        // 18. Finding the second non-repeating character using Java 8 streams
        findSecondNonRepeatedChar();
        
        // 19. Finding duplicates in a list and their occurrences using streams
        findDuplicatesInList();
        
        // 20. Finding the second non-repeated number using Java 8
        findSecondNonRepeatedNumber();
    }
    
    /**
     * 11. Max element in an array
     * 
     * This demonstrates different ways to find the maximum element in an array
     */
    private static void findMaxElement() {
        System.out.println("\n11. Max Element in an Array:\n");
        
        int[] numbers = {5, 9, 3, 15, 1, 2, 8, 7};
        System.out.println("Array: " + Arrays.toString(numbers));
        
        // Method 1: Using loop
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println("Maximum element (using loop): " + max);
        
        // Method 2: Using Arrays.stream
        int maxStream = Arrays.stream(numbers).max().orElse(Integer.MIN_VALUE);
        System.out.println("Maximum element (using stream): " + maxStream);
        
        // Method 3: Using Collections
        List<Integer> numberList = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        int maxCollection = Collections.max(numberList);
        System.out.println("Maximum element (using Collections): " + maxCollection);
        
        // Method 4: Using Arrays.sort
        int[] sortedNumbers = numbers.clone();
        Arrays.sort(sortedNumbers);
        int maxSorted = sortedNumbers[sortedNumbers.length - 1];
        System.out.println("Maximum element (using sort): " + maxSorted);
        
        // Method 5: Using Math.max with reduce
        int maxReduce = Arrays.stream(numbers).reduce(Integer.MIN_VALUE, Math::max);
        System.out.println("Maximum element (using reduce): " + maxReduce);
    }
    
    /**
     * 12. Second largest element in array
     * 
     * This demonstrates different ways to find the second largest element in an array
     */
    private static void findSecondLargestElement() {
        System.out.println("\n12. Second Largest Element in Array:\n");
        
        int[] numbers = {5, 9, 3, 15, 1, 2, 8, 7};
        System.out.println("Array: " + Arrays.toString(numbers));
        
        // Method 1: Using sorting
        int[] sortedNumbers = numbers.clone();
        Arrays.sort(sortedNumbers);
        int secondLargest = sortedNumbers[sortedNumbers.length - 2];
        System.out.println("Second largest element (using sort): " + secondLargest);
        
        // Method 2: Using two variables
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        
        for (int num : numbers) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num != max) {
                secondMax = num;
            }
        }
        
        System.out.println("Second largest element (using two variables): " + secondMax);
        
        // Method 3: Using Java 8 streams
        int secondLargestStream = Arrays.stream(numbers)
                                       .boxed()
                                       .sorted(Comparator.reverseOrder())
                                       .skip(1)
                                       .findFirst()
                                       .orElse(Integer.MIN_VALUE);
        
        System.out.println("Second largest element (using streams): " + secondLargestStream);
        
        // Method 4: Using TreeSet (handles duplicates)
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : numbers) {
            treeSet.add(num);
        }
        
        // Remove the largest element
        treeSet.pollLast();
        
        // Get the new largest element (which is the second largest of the original array)
        int secondLargestTreeSet = treeSet.last();
        System.out.println("Second largest element (using TreeSet): " + secondLargestTreeSet);
        
        // Method 5: Using a priority queue (min heap)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : numbers) {
            minHeap.add(num);
            if (minHeap.size() > 2) {
                minHeap.poll(); // Remove the smallest element
            }
        }
        
        // The smallest element in the heap is the second largest in the array
        minHeap.poll(); // Remove the smallest (which is the second largest in the array)
        int secondLargestHeap = minHeap.peek();
        System.out.println("Second largest element (using min heap): " + secondLargestHeap);
    }
    
    /**
     * 13. Sum of odd and even numbers in an array
     * 
     * This demonstrates different ways to calculate the sum of odd and even numbers in an array
     */
    private static void sumOddEvenNumbers() {
        System.out.println("\n13. Sum of Odd and Even Numbers in an Array:\n");
        
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Array: " + Arrays.toString(numbers));
        
        // Method 1: Using loops
        int sumEven = 0;
        int sumOdd = 0;
        
        for (int num : numbers) {
            if (num % 2 == 0) {
                sumEven += num;
            } else {
                sumOdd += num;
            }
        }
        
        System.out.println("Sum of even numbers (using loop): " + sumEven);
        System.out.println("Sum of odd numbers (using loop): " + sumOdd);
        
        // Method 2: Using Java 8 streams
        int sumEvenStream = Arrays.stream(numbers)
                                 .filter(n -> n % 2 == 0)
                                 .sum();
        
        int sumOddStream = Arrays.stream(numbers)
                                .filter(n -> n % 2 != 0)
                                .sum();
        
        System.out.println("Sum of even numbers (using streams): " + sumEvenStream);
        System.out.println("Sum of odd numbers (using streams): " + sumOddStream);
        
        // Method 3: Using partitioning by
        Map<Boolean, Integer> sumByParity = Arrays.stream(numbers)
                                                 .boxed()
                                                 .collect(Collectors.partitioningBy(
                                                     n -> n % 2 == 0,
                                                     Collectors.summingInt(Integer::intValue)
                                                 ));
        
        System.out.println("Sum of even numbers (using partitioningBy): " + sumByParity.get(true));
        System.out.println("Sum of odd numbers (using partitioningBy): " + sumByParity.get(false));
        
        // Method 4: Using reduce
        int sumEvenReduce = Arrays.stream(numbers)
                                 .filter(n -> n % 2 == 0)
                                 .reduce(0, Integer::sum);
        
        int sumOddReduce = Arrays.stream(numbers)
                                .filter(n -> n % 2 != 0)
                                .reduce(0, Integer::sum);
        
        System.out.println("Sum of even numbers (using reduce): " + sumEvenReduce);
        System.out.println("Sum of odd numbers (using reduce): " + sumOddReduce);
    }
    
    /**
     * 14. Swap 2 numbers
     * 
     * This demonstrates different ways to swap two numbers without using a temporary variable
     */
    private static void swapNumbers() {
        System.out.println("\n14. Swap 2 Numbers:\n");
        
        // Method 1: Using a temporary variable
        int a = 5, b = 10;
        System.out.println("Before swap (using temp): a = " + a + ", b = " + b);
        
        int temp = a;
        a = b;
        b = temp;
        
        System.out.println("After swap (using temp): a = " + a + ", b = " + b);
        
        // Method 2: Using addition and subtraction
        int c = 15, d = 25;
        System.out.println("\nBefore swap (using addition/subtraction): c = " + c + ", d = " + d);
        
        c = c + d;  // c = 15 + 25 = 40
        d = c - d;  // d = 40 - 25 = 15
        c = c - d;  // c = 40 - 15 = 25
        
        System.out.println("After swap (using addition/subtraction): c = " + c + ", d = " + d);
        
        // Method 3: Using multiplication and division (works only if both numbers are non-zero)
        int e = 5, f = 10;
        System.out.println("\nBefore swap (using multiplication/division): e = " + e + ", f = " + f);
        
        e = e * f;  // e = 5 * 10 = 50
        f = e / f;  // f = 50 / 10 = 5
        e = e / f;  // e = 50 / 5 = 10
        
        System.out.println("After swap (using multiplication/division): e = " + e + ", f = " + f);
        
        // Method 4: Using XOR (bitwise exclusive OR)
        int g = 7, h = 9;
        System.out.println("\nBefore swap (using XOR): g = " + g + ", h = " + h);
        
        g = g ^ h;  // g = 7 ^ 9
        h = g ^ h;  // h = (7 ^ 9) ^ 9 = 7
        g = g ^ h;  // g = (7 ^ 9) ^ 7 = 9
        
        System.out.println("After swap (using XOR): g = " + g + ", h = " + h);
        
        // Method 5: Using a single line expression (in Java)
        int i = 12, j = 17;
        System.out.println("\nBefore swap (single line): i = " + i + ", j = " + j);
        
        // This works by creating an array and then assigning values from it
        j = (i + j) - (i = j);
        
        System.out.println("After swap (single line): i = " + i + ", j = " + j);
    }
    
    /**
     * 15. Array splitting with equal sum
     * 
     * This demonstrates how to split an array into two subarrays with equal sum
     * Example: {1,2,3,4} can be split into {1,4} and {2,3} both with sum = 5
     */
    private static void splitArrayWithEqualSum() {
        System.out.println("\n15. Array Splitting with Equal Sum:\n");
        
        int[] array1 = {1, 2, 3, 4};
        System.out.println("Array: " + Arrays.toString(array1));
        
        // Find if the array can be split into two subarrays with equal sum
        boolean canSplit = findEqualSumPartition(array1);
        System.out.println("Can be split into equal sum partitions: " + canSplit);
        
        if (canSplit) {
            List<Integer> partition1 = new ArrayList<>();
            List<Integer> partition2 = new ArrayList<>();
            findEqualSumPartitions(array1, partition1, partition2);
            
            System.out.println("Partition 1: " + partition1 + ", Sum: " + 
                              partition1.stream().mapToInt(Integer::intValue).sum());
            System.out.println("Partition 2: " + partition2 + ", Sum: " + 
                              partition2.stream().mapToInt(Integer::intValue).sum());
        }
        
        // Try with another array
        int[] array2 = {5, 2, 3, 1, 9};
        System.out.println("\nArray: " + Arrays.toString(array2));
        
        canSplit = findEqualSumPartition(array2);
        System.out.println("Can be split into equal sum partitions: " + canSplit);
        
        if (canSplit) {
            List<Integer> partition1 = new ArrayList<>();
            List<Integer> partition2 = new ArrayList<>();
            findEqualSumPartitions(array2, partition1, partition2);
            
            System.out.println("Partition 1: " + partition1 + ", Sum: " + 
                              partition1.stream().mapToInt(Integer::intValue).sum());
            System.out.println("Partition 2: " + partition2 + ", Sum: " + 
                              partition2.stream().mapToInt(Integer::intValue).sum());
        }
        
        // Try with an array that can't be split
        int[] array3 = {1, 2, 3};
        System.out.println("\nArray: " + Arrays.toString(array3));
        
        canSplit = findEqualSumPartition(array3);
        System.out.println("Can be split into equal sum partitions: " + canSplit);
    }
    
    // Helper method to check if an array can be split into two subarrays with equal sum
    private static boolean findEqualSumPartition(int[] arr) {
        int totalSum = Arrays.stream(arr).sum();
        
        // If the total sum is odd, we can't split into two equal parts
        if (totalSum % 2 != 0) {
            return false;
        }
        
        int targetSum = totalSum / 2;
        
        // Use dynamic programming to find if there's a subset with sum = targetSum
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true;  // Empty subset has sum 0
        
        for (int num : arr) {
            for (int j = targetSum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[targetSum];
    }
    
    // Helper method to find the actual partitions
    private static void findEqualSumPartitions(int[] arr, List<Integer> partition1, List<Integer> partition2) {
        int totalSum = Arrays.stream(arr).sum();
        int targetSum = totalSum / 2;
        
        // Use backtracking to find one partition
        boolean[] used = new boolean[arr.length];
        findPartition(arr, 0, 0, targetSum, used);
        
        // Fill the partitions based on the 'used' array
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                partition1.add(arr[i]);
            } else {
                partition2.add(arr[i]);
            }
        }
    }
    
    // Helper method for backtracking
    private static boolean findPartition(int[] arr, int index, int currentSum, int targetSum, boolean[] used) {
        if (currentSum == targetSum) {
            return true;
        }
        
        if (index >= arr.length || currentSum > targetSum) {
            return false;
        }
        
        // Include current element
        used[index] = true;
        if (findPartition(arr, index + 1, currentSum + arr[index], targetSum, used)) {
            return true;
        }
        
        // Exclude current element
        used[index] = false;
        if (findPartition(arr, index + 1, currentSum, targetSum, used)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 16. Sort employees based on name and age
     * 
     * This demonstrates how to sort a list of employees based on name, and if names are same, then by age
     */
    private static void sortEmployees() {
        System.out.println("\n16. Sort Employees Based on Name and Age:\n");
        
        // Create a list of employees
        List<Employee> employees = Arrays.asList(
            new Employee("John", 30),
            new Employee("Alice", 25),
            new Employee("Bob", 35),
            new Employee("John", 25),
            new Employee("Alice", 30),
            new Employee("Charlie", 40)
        );
        
        System.out.println("Original list of employees:");
        employees.forEach(System.out::println);
        
        // Method 1: Using Comparable interface
        System.out.println("\nSorted by name and age (using Comparable):");
        List<Employee> sortedEmployees1 = new ArrayList<>(employees);
        Collections.sort(sortedEmployees1);
        sortedEmployees1.forEach(System.out::println);
        
        // Method 2: Using Comparator
        System.out.println("\nSorted by name and age (using Comparator):");
        List<Employee> sortedEmployees2 = new ArrayList<>(employees);
        Collections.sort(sortedEmployees2, new EmployeeComparator());
        sortedEmployees2.forEach(System.out::println);
        
        // Method 3: Using Java 8 streams and lambda expressions
        System.out.println("\nSorted by name and age (using streams and lambda):");
        List<Employee> sortedEmployees3 = employees.stream()
                                                 .sorted(Comparator.comparing(Employee::getName)
                                                                  .thenComparing(Employee::getAge))
                                                 .collect(Collectors.toList());
        sortedEmployees3.forEach(System.out::println);
        
        // Method 4: Using method references
        System.out.println("\nSorted by name and age (using method references):");
        List<Employee> sortedEmployees4 = employees.stream()
                                                 .sorted(Comparator.comparing(Employee::getName)
                                                                  .thenComparingInt(Employee::getAge))
                                                 .collect(Collectors.toList());
        sortedEmployees4.forEach(System.out::println);
    }
    
    // Employee class for sorting demonstration
    static class Employee implements Comparable<Employee> {
        private String name;
        private int age;
        
        public Employee(String name, int age) {
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
            return "Employee{name='" + name + "', age=" + age + "}";
        }
        
        @Override
        public int compareTo(Employee other) {
            // First compare by name
            int nameComparison = this.name.compareTo(other.name);
            
            // If names are equal, compare by age
            if (nameComparison == 0) {
                return Integer.compare(this.age, other.age);
            }
            
            return nameComparison;
        }
    }
    
    // Comparator for Employee class
    static class EmployeeComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee e1, Employee e2) {
            // First compare by name
            int nameComparison = e1.getName().compareTo(e2.getName());
            
            // If names are equal, compare by age
            if (nameComparison == 0) {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
            
            return nameComparison;
        }
    }
    
    /**
     * 17. Finding the first non-repeated character using Java 8 streams
     * 
     * This demonstrates how to find the first non-repeated character in a string using Java 8 streams
     */
    private static void findFirstNonRepeatedChar() {
        System.out.println("\n17. Finding the First Non-Repeated Character Using Java 8 Streams:\n");
        
        String[] strings = {"aabccd", "hello", "aabbcc", "java", "programming"};
        
        for (String str : strings) {
            System.out.println("String: " + str);
            
            // Method 1: Using LinkedHashMap to maintain order
            Map<Character, Long> charFrequency = new LinkedHashMap<>();
            
            // Count frequency of each character
            for (char c : str.toCharArray()) {
                charFrequency.put(c, charFrequency.getOrDefault(c, 0L) + 1);
            }
            
            // Find first character with frequency 1
            Character firstNonRepeated = null;
            for (Map.Entry<Character, Long> entry : charFrequency.entrySet()) {
                if (entry.getValue() == 1) {
                    firstNonRepeated = entry.getKey();
                    break;
                }
            }
            
            System.out.println("First non-repeated character (using LinkedHashMap): " + 
                              (firstNonRepeated != null ? firstNonRepeated : "None"));
            
            // Method 2: Using Java 8 streams
            Character firstNonRepeatedStream = str.chars()
                                                .mapToObj(c -> (char) c)
                                                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                                                .entrySet()
                                                .stream()
                                                .filter(entry -> entry.getValue() == 1)
                                                .map(Map.Entry::getKey)
                                                .findFirst()
                                                .orElse(null);
            
            System.out.println("First non-repeated character (using streams): " + 
                              (firstNonRepeatedStream != null ? firstNonRepeatedStream : "None"));
            
            System.out.println();
        }
    }
    
    /**
     * 18. Finding the second non-repeating character using Java 8 streams
     * 
     * This demonstrates how to find the second non-repeated character in a string using Java 8 streams
     */
    private static void findSecondNonRepeatedChar() {
        System.out.println("\n18. Finding the Second Non-Repeating Character Using Java 8 Streams:\n");
        
        String[] strings = {"aabccd", "hello", "aabbcc", "java", "programming"};
        
        for (String str : strings) {
            System.out.println("String: " + str);
            
            // Method 1: Using LinkedHashMap to maintain order
            Map<Character, Long> charFrequency = new LinkedHashMap<>();
            
            // Count frequency of each character
            for (char c : str.toCharArray()) {
                charFrequency.put(c, charFrequency.getOrDefault(c, 0L) + 1);
            }
            
            // Find second character with frequency 1
            List<Character> nonRepeatedChars = new ArrayList<>();
            for (Map.Entry<Character, Long> entry : charFrequency.entrySet()) {
                if (entry.getValue() == 1) {
                    nonRepeatedChars.add(entry.getKey());
                    if (nonRepeatedChars.size() == 2) {
                        break;
                    }
                }
            }
            
            Character secondNonRepeated = nonRepeatedChars.size() >= 2 ? nonRepeatedChars.get(1) : null;
            System.out.println("Second non-repeated character (using LinkedHashMap): " + 
                              (secondNonRepeated != null ? secondNonRepeated : "None"));
            
            // Method 2: Using Java 8 streams
            Character secondNonRepeatedStream = str.chars()
                                                 .mapToObj(c -> (char) c)
                                                 .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                                                 .entrySet()
                                                 .stream()
                                                 .filter(entry -> entry.getValue() == 1)
                                                 .map(Map.Entry::getKey)
                                                 .skip(1)  // Skip the first non-repeated character
                                                 .findFirst()
                                                 .orElse(null);
            
            System.out.println("Second non-repeated character (using streams): " + 
                              (secondNonRepeatedStream != null ? secondNonRepeatedStream : "None"));
            
            System.out.println();
        }
    }
    
    /**
     * 19. Finding duplicates in a list and their occurrences using streams
     * 
     * This demonstrates how to find duplicates in a list and count their occurrences using Java 8 streams
     */
    private static void findDuplicatesInList() {
        System.out.println("\n19. Finding Duplicates in a List and Their Occurrences Using Streams:\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6, 7, 8, 1, 5, 9);
        System.out.println("List: " + numbers);
        
        // Method 1: Using HashMap
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        
        for (Integer num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        System.out.println("\nDuplicates and their occurrences (using HashMap):");
        frequencyMap.entrySet().stream()
                   .filter(entry -> entry.getValue() > 1)
                   .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " times"));
        
        // Method 2: Using Java 8 streams
        System.out.println("\nDuplicates and their occurrences (using streams):");
        numbers.stream()
              .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
              .entrySet().stream()
              .filter(entry -> entry.getValue() > 1)
              .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " times"));
        
        // Method 3: Using streams to get only the duplicate elements
        System.out.println("\nOnly duplicate elements (using streams):");
        Set<Integer> duplicates = numbers.stream()
                                        .filter(i -> Collections.frequency(numbers, i) > 1)
                                        .collect(Collectors.toSet());
        System.out.println(duplicates);
        
        // Example with strings
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple", "grape");
        System.out.println("\nList of words: " + words);
        
        System.out.println("\nDuplicate words and their occurrences:");
        words.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " times"));
    }
    
    /**
     * 20. Finding the second non-repeated number using Java 8
     * 
     * This demonstrates how to find the second non-repeated number in a list using Java 8 streams
     */
    private static void findSecondNonRepeatedNumber() {
        System.out.println("\n20. Finding the Second Non-Repeated Number Using Java 8:\n");
        
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6, 7, 8, 1, 5, 9);
        System.out.println("List: " + numbers1);
        
        // Method 1: Using LinkedHashMap to maintain order
        Map<Integer, Long> frequencyMap = new LinkedHashMap<>();
        
        for (Integer num : numbers1) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0L) + 1);
        }
        
        List<Integer> nonRepeatedNumbers = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                nonRepeatedNumbers.add(entry.getKey());
                if (nonRepeatedNumbers.size() == 2) {
                    break;
                }
            }
        }
        
        Integer secondNonRepeated = nonRepeatedNumbers.size() >= 2 ? nonRepeatedNumbers.get(1) : null;
        System.out.println("Second non-repeated number (using LinkedHashMap): " + 
                          (secondNonRepeated != null ? secondNonRepeated : "None"));
        
        // Method 2: Using Java 8 streams
        Integer secondNonRepeatedStream = numbers1.stream()
                                                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                                                .entrySet().stream()
                                                .filter(entry -> entry.getValue() == 1)
                                                .map(Map.Entry::getKey)
                                                .skip(1)  // Skip the first non-repeated number
                                                .findFirst()
                                                .orElse(null);
        
        System.out.println("Second non-repeated number (using streams): " + 
                          (secondNonRepeatedStream != null ? secondNonRepeatedStream : "None"));
        
        // Try with another list
        List<Integer> numbers2 = Arrays.asList(1, 1, 2, 2, 3, 4, 5, 5);
        System.out.println("\nList: " + numbers2);
        
        Integer secondNonRepeatedStream2 = numbers2.stream()
                                                  .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                                                  .entrySet().stream()
                                                  .filter(entry -> entry.getValue() == 1)
                                                  .map(Map.Entry::getKey)
                                                  .skip(1)  // Skip the first non-repeated number
                                                  .findFirst()
                                                  .orElse(null);
        
        System.out.println("Second non-repeated number (using streams): " + 
                          (secondNonRepeatedStream2 != null ? secondNonRepeatedStream2 : "None"));
    }
}