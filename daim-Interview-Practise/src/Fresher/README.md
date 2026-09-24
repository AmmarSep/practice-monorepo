# String Creation in Java

## Two Ways to Create Strings

Java provides two primary ways to create String objects, each with different characteristics and performance implications:

### 1. String Literals

```java
String name = "SS Ammar";
```

**Characteristics:**
- Stored in the String Pool (a special area in Java heap memory)
- Memory efficient - identical literals share the same reference
- Better performance for string constants and repeated strings
- When creating a string literal, JVM checks if the string already exists in the pool:
  - If it exists, no new object is created, and the existing reference is returned
  - If it doesn't exist, a new string object is created in the pool

### 2. String Constructor

```java
String name2 = new String("Seippu");
```

**Characteristics:**
- Always creates a new object in the heap memory (outside the string pool)
- Less memory efficient for duplicate strings
- Creates a separate object even if the same content already exists in the string pool
- The string content is effectively stored twice - once in the compiled code/string pool and once in the heap

## Key Differences

### Memory Usage
String literals are pooled, which means identical strings share the same memory space. This is more memory-efficient when the same string appears multiple times in your code.

### Object Creation
Using `new String()` always creates a new object in the heap, regardless of whether an identical string already exists.

### String Comparison
When using the `==` operator (which compares object references in Java):
- Two string literals with the same content will be equal (same reference)
- Two strings created with `new String()` will never be equal with `==`, even with identical content
- Always use `.equals()` method to compare string content rather than `==`

### String Interning
The `.intern()` method can be used to add a string created with the constructor to the string pool:

```java
String constructed = new String("Hello");
String interned = constructed.intern();
String literal = "Hello";

system.out.println(constructed == literal); // false
system.out.println(interned == literal);    // true
```

## When to Use Each Method

### Use String Literals (Preferred):
- For string constants and frequently used strings
- When memory efficiency is important
- For general string handling (default approach)

### Use String Constructor:
- When you explicitly need a new distinct string object
- In certain security-sensitive contexts where having distinct objects is required
- When working with character arrays or other sources to create strings

## Performance Considerations

String literals generally offer better performance due to the pooling mechanism, especially when the same strings are used repeatedly. The String Pool helps reduce memory usage and can improve application performance.

---

The example in `VariousWaysOfWritingStringInJava.java` demonstrates these concepts with practical code examples.
