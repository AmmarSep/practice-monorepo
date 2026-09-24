---
title: Java Code Reading — EmplSort
tags:
  - java
  - interview-programs
  - code-reading
  - oop
  - collections
  - sorting
aliases:
  - EmplSort Explained
  - Sorting Objects with Comparator
---

# Java Code Reading — EmplSort Explained for a Fresh Java Programmer

> [!note]
> This is by far the most advanced program in the folder, and the only object-oriented one. Every other program pushed **primitives** around; this one creates **objects**, stores them in a **collection**, and sorts them with a **`Comparator`** built from **method references**.
>
> That is a lot of new vocabulary. Do not try to absorb it all at once. The single most important idea is this:
>
> > **`>` and `<` work on numbers. They do not work on objects.** So when you need to order objects, you must *supply the rule* for comparing them. A `Comparator` is that rule, packaged as an object you can pass around.
>
> Once that clicks, `Comparator.comparing(EmplSort::getName).thenComparing(EmplSort::getAge)` reads almost like English.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Can Java compare these directly? → If not, what rule do I supply? → How do I express that rule as a value?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmplSort {
    public String name;
    public int age;

    public EmplSort(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
        this.age = age;
    }

    @Override
    public String toString() {
        return "EmplSort{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        List<EmplSort> employeeList = new ArrayList<>();

        employeeList.add(new EmplSort("Niyaz", 21));
        employeeList.add(new EmplSort("Raez", 32));
        employeeList.add(new EmplSort("Siha", 53));
        employeeList.add(new EmplSort("Lafir", 23));
        employeeList.add(new EmplSort("Sabiq", 32));
        employeeList.add(new EmplSort("Lafir", 33));

        System.out.println("Before sorting");

        for(EmplSort e : employeeList){
            System.out.println(e);
        }

        employeeList.sort(Comparator.comparing(EmplSort::getName).thenComparing(EmplSort::getAge));

        System.out.println("After sorting");

        for(EmplSort s : employeeList){
            System.out.println(s);
        }
    }
}
```

> [!note] One class, two jobs
> Notice something unusual: `EmplSort` is **both** the data type (an employee has a name and an age) **and** the program that runs (it has a `main`).
>
> That is convenient for a single-file demo, but not how real code is organised. Normally you would have `Employee.java` holding the data and a separate `EmployeeDemo.java` holding `main`. The name `EmplSort` reflects the muddle — it names a *task*, but the class models an *employee*.
>
> Keep the two roles separate in your head as you read: the top half is a **blueprint for employees**, the bottom half is a **program that uses it**.

---

# 2. What problem is this program solving?

In plain language:

> I have a list of employees, each with a name and an age. Sort them by name; when two people share a name, put the younger one first. Print the list before and after.

### What do we know?

- Six employees.
- Each has **two** pieces of information that belong together.
- Two of them share the name "Lafir".

### What do we need?

- The same six employees, in a specific order.

### What transformations are required?

Two problems, and the first one is the reason this program looks so different from the others.

### Problem 1 — how do you store a name *and* an age together?

Every earlier program stored one kind of value: `int[]` for numbers, `String` for text. But an employee is **two values that belong together**.

You could try parallel arrays:

```java
String[] names = {"Niyaz", "Raez", ...};
int[] ages     = {21, 32, ...};
```

> [!warning] Why parallel arrays are a bad idea
> Nothing connects `names[3]` to `ages[3]` except your discipline. Sort one and forget the other, and every employee silently gets someone else's age.
>
> **When two values belong together, they should live in one object.** That is what a class is for — it makes the relationship structural rather than accidental.

So: define a class with two fields. That is the entire top half of the file.

### Problem 2 — how do you sort objects?

For numbers, sorting is obvious — `>` tells you which comes first.

For objects, `>` **does not compile**:

```java
EmplSort a = ..., b = ...;
if (a > b) { }        // ✗ bad operand types for binary operator '>'
```

> [!important] This is the central idea of the program
> Java has no idea how to order two employees. Should it be by name? Age? Salary? Hire date? **There is no universally correct answer**, so Java refuses to guess.
>
> Therefore *you* must supply the rule. And a rule that gets passed to a method has to be a **value** — which means the rule itself must be an object.
>
> That object is a **`Comparator`**.
>
> ```text
> primitives:  Java already knows the order    →  use  >  <
> objects:     Java cannot know the order      →  supply a Comparator
> ```
>
> Everything that follows — `Comparator.comparing`, `thenComparing`, `EmplSort::getName` — is just machinery for building that rule concisely.

### The sorting rule itself

> Sort by name. If two names are equal, sort by age.

That is a **primary** key with a **tie-breaker**, which maps directly onto `comparing(...)` followed by `thenComparing(...)`.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder.

## The imports

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
```

**Three imports** — the most in this folder. All three live in `java.util`, which is not imported automatically.

| Import | What it provides |
|---|---|
| `List` | the **interface** — "an ordered collection" |
| `ArrayList` | the **implementation** — a resizable-array `List` |
| `Comparator` | the tool for expressing an ordering rule |

**How you would discover these:** type `List` in IntelliJ, see it underlined red, press `Alt+Enter`, and choose the import. The IDE tells you which package it came from. Nobody memorises these.

## `public class EmplSort`

The class serves as both the employee blueprint and the program.

## Fields

```java
public String name;
public int age;
```

These are **instance fields** — one copy per object, unlike the local variables in every other program.

## Constructor

```java
public EmplSort(String name, int age) { ... }
```

The special method that runs when `new EmplSort(...)` is called.

## Getters and setters

```java
public String getName() { ... }
public void setName(String name) { ... }
public int getAge() { ... }
public void setAge(int age) { ... }
```

Standard accessor methods. `getName` and `getAge` are the ones the sorting actually uses.

## `toString()`

```java
@Override
public String toString() { ... }
```

Controls how the object prints. Without it, `System.out.println(e)` would show something unreadable.

## `main`

The program: build a list, print it, sort it, print it again.

---

# 4. Line-by-line explanation

## 4.1 — `public String name;` and `public int age;`

```java
public String name;
public int age;
```

**What is this?**
Two **instance fields** — the data every employee carries.

**Why do we need them?**
An employee *is* a name and an age. These fields are what make the class a meaningful type rather than an empty shell.

**How is a field different from a local variable?**

| | Local variable | Instance field |
|---|---|---|
| declared in | a method | the class body |
| lives | while the method runs | as long as the object exists |
| copies | one | **one per object** |
| default value | none — must be assigned | `null`, `0`, `false` |

Six `EmplSort` objects means **six separate `name` fields and six separate `age` fields**.

```text
employeeList
  ├─▶ EmplSort{ name="Niyaz", age=21 }
  ├─▶ EmplSort{ name="Raez",  age=32 }
  ├─▶ EmplSort{ name="Siha",  age=53 }
  ├─▶ EmplSort{ name="Lafir", age=23 }
  ├─▶ EmplSort{ name="Sabiq", age=32 }
  └─▶ EmplSort{ name="Lafir", age=33 }
```

**Why these types?**
`name` is text → `String`. `age` is a whole number → `int`. Same reasoning as always.

> [!warning] `public` fields defeat the purpose of getters
> ```java
> public String name;       // anyone can write  e.name = "x";  directly
> public String getName()   // ...so why does this exist?
> ```
>
> The class provides getters and setters — the standard way to control access — but then makes the fields `public`, so nothing is actually controlled. The two decisions contradict each other.
>
> The conventional approach is **encapsulation**:
> ```java
> private String name;                          // ← private
> public String getName() { return name; }      // read through here
> public void setName(String name) { ... }      // write through here
> ```
>
> **Why does that matter?** Because with `private` you can later add validation, logging, or a computed value without changing any calling code:
> ```java
> public void setAge(int age) {
>     if (age < 0) throw new IllegalArgumentException("age cannot be negative");
>     this.age = age;
> }
> ```
> With `public` fields, `e.age = -5;` bypasses that entirely and you can never take the ability back.
>
> **Make fields `private` unless you have a specific reason not to.** This is one of the first habits interviewers look for.

---

## 4.2 — the constructor

```java
public EmplSort(String name, int age) {
    this.name = name;
    this.age = age;
}
```

**What is this?**
A **constructor** — the special method that initialises a newly created object.

**How is it different from an ordinary method?**

| | Constructor | Method |
|---|---|---|
| name | **exactly** the class name | anything |
| return type | **none at all** — not even `void` | required |
| called by | `new EmplSort(...)` | `object.method(...)` |
| purpose | set up a new object | do work |

**Why do we need it?**
Without it, you would have to create a blank object and fill it in afterwards:

```java
EmplSort e = new EmplSort();
e.setName("Niyaz");
e.setAge(21);
```

Three statements, and an object that is momentarily invalid. The constructor makes it one statement, and the object is complete from the moment it exists.

**What is `this`?**

`this` refers to **the object currently being constructed or operated on**.

Look carefully at the naming collision:

```java
public EmplSort(String name, int age) {
    this.name = name;
    //   ↑       ↑
    //   │       └── the PARAMETER (the value passed in)
    //   └────────── the FIELD (this object's storage)
}
```

There are two things called `name` in scope. Java's rule: **the closest declaration wins**, so a bare `name` means the parameter. `this.name` explicitly says "the field belonging to this object".

> [!warning] What happens without `this`
> ```java
> public EmplSort(String name, int age) {
>     name = name;        // ✗ assigns the parameter to itself
>     age = age;          // ✗ same
> }
> ```
> This compiles. It also does **nothing** — the fields keep their defaults (`null` and `0`), and every employee comes out blank.
>
> IntelliJ warns *"Assignment to itself"*, but the compiler does not. **When a parameter shares a field's name, `this.` is mandatory.**
>
> The alternative is to name the parameter differently (`newName`), but matching names are the Java convention precisely because `this.` makes the intent explicit.

---

## 4.3 — getters and setters

```java
public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}
```

**What are these?**
**Accessor** methods — a controlled way to read and write a field.

**Naming convention**

| Field type | Getter | Setter |
|---|---|---|
| `String name` | `getName()` | `setName(String)` |
| `int age` | `getAge()` | `setAge(int)` |
| `boolean active` | **`isActive()`** | `setActive(boolean)` |

The `get`/`set`/`is` prefixes are the **JavaBeans convention**. It is not enforced by the compiler, but it is honoured throughout the ecosystem — frameworks, serialisation libraries, and IDEs all rely on it.

> [!tip] These are generated, not typed
> Nobody writes getters and setters by hand. In IntelliJ, press `Alt+Insert` (or `Cmd+N` on macOS) → **Generate** → **Getter and Setter**, pick the fields, done.
>
> The same menu generates `toString()`, `equals()`, `hashCode()`, and constructors. **Learning your IDE's generate menu saves an enormous amount of typing** — and produces more correct code than writing it out.

**Return types**

`getName()` returns `String`, matching the field. `getAge()` returns `int`. Setters return `void` — they change state rather than produce a value.

**Why these matter here:** `getName` and `getAge` are exactly the two methods the sorting rule refers to. Without them, `EmplSort::getName` would have nothing to point at.

---

## 4.4 — `toString()`

```java
@Override
public String toString() {
    return "EmplSort{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
}
```

**What is this?**
The method Java calls automatically whenever an object needs to become text.

**Why do we need it?**

Without it, printing an object gives you this:

```text
FrequentInterviewPrograms.EmplSort@1b6d3586
```

That is the default `toString()` from `Object` — the class name plus a hash code. Useless for reading.

With it:

```text
EmplSort{name='Niyaz', age=21}
```

> [!important] `println` calls `toString()` for you
> ```java
> System.out.println(e);
> ```
> There is no `.toString()` in that line — but `println(Object)` calls it internally. So does string concatenation:
> ```java
> System.out.println("Employee: " + e);     // toString() is called here too
> ```
>
> **Any time an object appears where text is expected, `toString()` runs.** That is why overriding it improves output everywhere at once, with no changes to the calling code.

**What is `@Override`?**

An **annotation** telling the compiler *"this method is meant to replace one inherited from a parent class."*

It is optional, but valuable:

```java
@Override
public String toSting() { ... }      // ✗ typo — compiler ERROR, caught immediately

public String toSting() { ... }      // ✗ same typo — compiles as a NEW method,
                                     //   println still shows the ugly default
```

> [!tip]
> **Always write `@Override` when you intend to override.** It converts a silent, confusing bug into an immediate compile error. This is one of the cheapest safety habits in Java.

**Why is the return type `String`?**
Because that is the signature inherited from `Object`. An override must match exactly.

**Reading the concatenation**

```java
"EmplSort{" + "name='" + name + '\'' + ", age=" + age + '}'
```

Two details worth noting:

- `'\''` is an **escape sequence** for a single-quote `char`. The backslash says "this quote is data, not a delimiter."
- `'}'` is a `char`, not a `String`. Concatenating a `char` onto a `String` works fine — the leftmost operand is a `String`, so `+` concatenates throughout.

Result: `EmplSort{name='Niyaz', age=21}`

---

## 4.5 — `List<EmplSort> employeeList = new ArrayList<>();`

```java
List<EmplSort> employeeList = new ArrayList<>();
```

**What is this?**
Creating an empty, growable list that holds `EmplSort` objects.

**Why a `List` and not an array?**

| | Array | `List` / `ArrayList` |
|---|---|---|
| size | **fixed** at creation | **grows** on demand |
| add an element | impossible — must make a new array | `list.add(x)` |
| size query | `arr.length` | `list.size()` |
| access | `arr[i]` | `list.get(i)` |
| sorting | `Arrays.sort(arr)` | `list.sort(...)` |

We add six employees one at a time. With an array you would have to know the count up front. **When the number of items is not fixed, use a `List`.**

**Why is the variable declared as `List` but created as `ArrayList`?**

This looks odd at first and is a deliberate, important idiom.

```java
List<EmplSort> employeeList = new ArrayList<>();
 ↑                                  ↑
 the INTERFACE                      the IMPLEMENTATION
 (what it can do)                   (how it does it)
```

> [!important] "Program to the interface"
> `List` is an **interface** — a contract listing what any list can do (`add`, `get`, `size`, `sort`). `ArrayList` is one **implementation** of that contract, backed by a resizable array.
>
> Declaring the variable as `List` means the rest of the code depends only on the contract. If you later needed a `LinkedList` — better for frequent insertion in the middle — you would change **one word**:
>
> ```java
> List<EmplSort> employeeList = new LinkedList<>();   // everything else unchanged
> ```
>
> Had you declared it as `ArrayList`, every line mentioning that type would need editing.
>
> **Declare the general type; instantiate the specific one.** This is one of the most-repeated pieces of Java advice, and it shows up in code review constantly.

**What is `<EmplSort>`?**

**Generics** — the type of thing the list holds.

```java
List<EmplSort>     // a list of employees
List<String>       // a list of text
List<Integer>      // a list of whole numbers (boxed)
```

Why it matters:

```java
List<EmplSort> list = new ArrayList<>();
list.add(new EmplSort("A", 1));      // ✓
list.add("hello");                    // ✗ COMPILE ERROR — caught immediately

EmplSort e = list.get(0);             // ✓ no cast needed
```

> [!important] Generics move errors from runtime to compile time
> Before generics (Java 4 and earlier), a list held plain `Object`s. You could put anything in, and every read needed a cast:
>
> ```java
> List list = new ArrayList();
> list.add("oops");
> EmplSort e = (EmplSort) list.get(0);   // ClassCastException at RUNTIME
> ```
>
> With generics the compiler rejects the bad `add` on the spot. **A compile error is always better than a runtime exception** — one costs you a minute, the other costs you a production incident.

**What is `<>` — the diamond?**

```java
List<EmplSort> employeeList = new ArrayList<EmplSort>();   // pre-Java 7, repetitive
List<EmplSort> employeeList = new ArrayList<>();           // Java 7+, inferred
```

The compiler infers the type argument from the left-hand side. Less typing, identical meaning.

**Why can't a `List` hold primitives?**

```java
List<int> list;         // ✗ does not compile
List<Integer> list;     // ✓ the wrapper class
```

Generics work only with reference types. Each primitive has a wrapper — `int`→`Integer`, `char`→`Character`, `double`→`Double` — and Java converts automatically (**autoboxing**). Not an issue here, since `EmplSort` is already a class.

---

## 4.6 — `employeeList.add(new EmplSort("Niyaz", 21));`

```java
employeeList.add(new EmplSort("Niyaz", 21));
```

**What is this?**
Creating an employee object and appending it to the list — two operations in one line.

**Reading it inside-out**

```text
new EmplSort("Niyaz", 21)
        ↓
1. allocate a new EmplSort object on the heap
2. run the constructor with name="Niyaz", age=21
3. produce a reference to it
        ↓
employeeList.add( <that reference> )
        ↓
4. append the reference to the list
```

**Could this be split?**

```java
EmplSort employee = new EmplSort("Niyaz", 21);
employeeList.add(employee);
```

Identical behaviour. The one-liner is fine here because the object is used exactly once — naming it would add a line without adding clarity.

**What does `add` return?**

```java
public boolean add(E e)
```

It returns `boolean` — `true` if the collection changed. For a `List` it is always `true`, so the value is ignored. (For a `Set`, that return value is meaningful — see [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]].)

> [!important] The list stores references, not objects
> ```text
>  employeeList
>  ┌───┬───┬───┬───┬───┬───┐
>  │ref│ref│ref│ref│ref│ref│
>  └─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┘
>    │   │   │   │   │   │
>    ▼   ▼   ▼   ▼   ▼   ▼
>   six separate EmplSort objects on the heap
> ```
>
> The list holds **addresses**. Two consequences that matter:
>
> 1. **Sorting rearranges the references, not the objects.** The objects never move in memory.
> 2. **If you keep a reference elsewhere and modify it, the list sees the change** — because both point at the same object.
>
> ```java
> EmplSort e = new EmplSort("Niyaz", 21);
> employeeList.add(e);
> e.setAge(99);                       // the list's element is now 99 too
> ```
>
> This is the same reference-versus-value distinction as arrays in [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]], now with a class of your own.

**Note the duplicate names**

Two employees are called "Lafir", with ages 23 and 33. That is deliberate — it is what makes the tie-breaker in the sort observable.

---

## 4.7 — the enhanced `for` loop

```java
for(EmplSort e : employeeList){
    System.out.println(e);
}
```

**What is this?**
An **enhanced for** loop — read it as *"for each `EmplSort` `e` in `employeeList`"*.

**Why this rather than an indexed loop?**

```java
for (int i = 0; i < employeeList.size(); i++) {      // works, but noisier
    System.out.println(employeeList.get(i));
}
```

We only need each element, never its position. **If you do not need the index, do not create one** — and doing so makes off-by-one errors structurally impossible.

**What does each part mean?**

```text
for ( EmplSort   e   :   employeeList )
         │       │   │        │
         │       │   │        └── the collection to walk
         │       │   └─────────── read as "in"
         │       └─────────────── the loop variable
         └─────────────────────── its type — must match the elements
```

**What does `println(e)` do?**

`e` is an `EmplSort` object, so `println(Object)` runs — and it calls `e.toString()` internally. That is why the output is readable rather than a hash code.

**The second loop uses `s` instead of `e`**

```java
for(EmplSort s : employeeList){
    System.out.println(s);
}
```

Purely a different name for the same role. Each loop variable is scoped to its own loop, so the name is free to reuse. Using `e` in both would be marginally more consistent.

---

## 4.8 — the sort

```java
employeeList.sort(Comparator.comparing(EmplSort::getName).thenComparing(EmplSort::getAge));
```

This is the line the whole program exists for. Take it in pieces.

```text
employeeList.sort(  Comparator.comparing(EmplSort::getName)
                              .thenComparing(EmplSort::getAge)  )
     │        │            │              │
     │        │            │              └── the tie-breaker rule
     │        │            └───────────────── the primary rule
     │        └────────────────────────────── sorts this list IN PLACE
     └─────────────────────────────────────── the list being sorted
```

### `employeeList.sort(...)`

| Question | Answer |
|---|---|
| **Which interface owns it?** | `java.util.List` |
| **Accepts** | a `Comparator<? super E>` — the ordering rule |
| **Returns** | `void` |
| **Effect** | rearranges **this** list; does not produce a new one |

> [!warning] `sort` modifies the list in place
> Because it returns `void`, you cannot write `List<EmplSort> sorted = employeeList.sort(...)`. The original list **is** the sorted list afterwards.
>
> If you need to keep the original order, copy first:
> ```java
> List<EmplSort> sorted = new ArrayList<>(employeeList);
> sorted.sort(...);
> ```
>
> Same trap as `Arrays.sort` in [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]]: **a `void` return is a strong hint that something was modified in place.**

### Why does `sort` need an argument at all?

Because Java cannot order `EmplSort` objects on its own.

```java
List<Integer> nums = ...;
nums.sort(null);                  // works — Integer knows its own order

List<EmplSort> people = ...;
people.sort(null);                // ✗ ClassCastException — EmplSort has no natural order
```

Types like `Integer` and `String` implement `Comparable`, which defines a *natural* ordering. `EmplSort` does not, so an explicit rule must be supplied.

### `Comparator.comparing(...)`

| Question | Answer |
|---|---|
| **Which interface owns it?** | `java.util.Comparator` |
| **Why callable without an object?** | it is a **`static`** method — call it on the interface itself |
| **Accepts** | a function that extracts a sort key from an object |
| **Returns** | a `Comparator` |

In plain terms: *"build me a comparator that orders objects by this property."*

### `EmplSort::getName` — the method reference

**What is `::`?**

The **method reference** operator. It means *"here is a method — do not call it, just hand it over."*

```java
EmplSort::getName        // the method itself, as a value
e.getName()              // calling it, producing a String
```

> [!important] Passing behaviour, not data
> This is the conceptual leap. Every earlier program passed **data** to methods — numbers, strings, arrays. Here we pass a **method** as an argument.
>
> `Comparator.comparing` does not want a name. It wants to know **how to get** a name from any employee it is later handed. So we give it the getter itself.
>
> `EmplSort::getName` is exactly equivalent to the lambda:
> ```java
> e -> e.getName()
> ```
> which reads *"given an `e`, produce `e.getName()`"*. The method reference is just shorthand when the lambda does nothing but call one method.
>
> ```java
> Comparator.comparing(EmplSort::getName)     // method reference — concise
> Comparator.comparing(e -> e.getName())      // lambda — identical meaning
> ```
>
> Both compile to the same thing. Use the method reference when it fits.

**How does the comparator know how to order the extracted names?**

`getName()` returns a `String`, and `String` implements `Comparable` — it knows its own alphabetical order. So `comparing` can compare the extracted keys directly.

> [!note] `String` ordering is case-sensitive
> It compares by Unicode value, and every uppercase letter sorts **before** every lowercase one:
>
> ```text
> 'A' = 65 ... 'Z' = 90
> 'a' = 97 ... 'z' = 122
>
> "Zebra" < "apple"        because 'Z'(90) < 'a'(97)
> ```
>
> All six names here start with a capital and continue in lowercase, so the ordering looks conventionally alphabetical. With mixed casing it would surprise you. For case-insensitive ordering:
> ```java
> Comparator.comparing(EmplSort::getName, String.CASE_INSENSITIVE_ORDER)
> ```

### `.thenComparing(EmplSort::getAge)`

| Question | Answer |
|---|---|
| **Which interface owns it?** | `java.util.Comparator` |
| **Why can we call it here?** | `comparing(...)` **returned** a `Comparator`, and this is an instance method on it |
| **Accepts** | another key extractor |
| **Returns** | a **new** `Comparator` combining both rules |

In plain terms: *"and if the first rule says they are equal, use this one to break the tie."*

**Why can it be chained?**

Because `comparing(...)` returns a `Comparator`, and `thenComparing` is a method on `Comparator`. The same chaining logic as `sentence.trim().split(" ")` in [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]]:

```text
Comparator.comparing(EmplSort::getName)
        ↓ returns a Comparator
    .thenComparing(EmplSort::getAge)
        ↓ returns a Comparator
    passed to sort()
```

**Check the types at each step.** That is what makes any chained expression readable.

### Reading the whole line as English

```text
Comparator.comparing(EmplSort::getName).thenComparing(EmplSort::getAge)
           ─────────  ───────────────   ──────────────  ──────────────
           "order by"    "their name"   "and then by"    "their age"
```

> **"Sort the employee list by name, and then by age."**

Once you can perform that translation, the line stops looking like symbol soup.

---

# 5. How to think like the programmer

```text
Requirement
"Sort employees by name, then by age"
        ↓
What data do I have?
Six people, each with a name AND an age
        ↓
Two values that belong together —
can one variable hold that?
No. Parallel arrays would let them
drift apart.
        ↓
Define a CLASS with two fields.
→ String name, int age
        ↓
How do I create one with its values ready?
A constructor
→ this.name = name (this. because the
  parameter shadows the field)
        ↓
How do I read those values from outside?
Getters — and they need the get/set naming
convention, because the sorting API relies on it
        ↓
How will they print?
By default: EmplSort@1b6d3586  ✗
So override toString()  ✓
        ↓
Where do I store six of them?
The count could change → not an array
→ List<EmplSort>
        ↓
Which List implementation?
ArrayList is the general-purpose default
        ↓
Declare as List, instantiate as ArrayList,
so the implementation can change later
        ↓
How do I sort them?
Try >  →  does not compile on objects
        ↓
Java cannot know how to order employees —
by name? age? salary? It refuses to guess.
        ↓
So I must SUPPLY the rule.
A rule passed to a method must be a value.
→ a Comparator object
        ↓
How do I build one without writing a class?
Comparator.comparing(keyExtractor)
        ↓
What is a key extractor?
Something that pulls the sort key
out of an object → the getter
→ EmplSort::getName
        ↓
What about ties?
.thenComparing(EmplSort::getAge)
        ↓
Read it back as English:
"order by name, then by age"  ✓
        ↓
Print before and after to prove it worked
```

> [!important] The transferable idea
> **When a library cannot know your rule, it asks you to pass one in.**
>
> This pattern is everywhere in modern Java:
>
> | Method | What you supply |
> |---|---|
> | `list.sort(comparator)` | how to order |
> | `stream.filter(predicate)` | which items to keep |
> | `stream.map(function)` | how to transform |
> | `list.forEach(consumer)` | what to do with each |
>
> All four take **behaviour as an argument**. Once you see that `Comparator` is just "a rule you can pass around", the rest of the functional API becomes far less mysterious.

---

# 6. Deep explanation of important Java concepts used

## Class and object

A **class** is a blueprint; an **object** is a thing built from it.

```text
class EmplSort          ← the blueprint (one)
   ↓ new EmplSort(...)
six EmplSort objects    ← the things (many)
```

Each object gets its own copy of the instance fields.

## Fields vs local variables

| | Local variable | Instance field |
|---|---|---|
| declared in | a method | the class body |
| lifetime | the method call | the object's life |
| copies | one | one per object |
| defaults | none | `null`, `0`, `false` |

## Constructor

Same name as the class, **no return type**, runs on `new`. Its job is to leave the object in a valid state.

## `this`

A reference to the current object. Required when a parameter shadows a field:

```java
this.name = name;
//   ↑        ↑
// field   parameter
```

## Encapsulation

Fields `private`, access through methods. Lets you add validation later without breaking callers. This program declares its fields `public`, which forfeits that.

## `toString()` and `@Override`

`toString()` is inherited from `Object` and called automatically whenever an object is printed or concatenated. Override it to make output readable. `@Override` turns a misspelled override into a compile error.

## `List` and `ArrayList`

| | `List` | `ArrayList` |
|---|---|---|
| what | an interface — the contract | a class — one implementation |
| can you `new` it? | no | yes |
| use for | the variable's declared type | the object you create |

Common `List` methods:

```java
list.add(x)          // append          → boolean
list.get(i)          // read by index   → E
list.size()          // count           → int
list.remove(i)       // delete          → E
list.contains(x)     // membership      → boolean
list.sort(cmp)       // order in place  → void
list.isEmpty()       // any elements?   → boolean
```

## Generics `<T>`

Type safety at compile time.

```java
List<EmplSort> list = new ArrayList<>();
list.add("oops");                  // ✗ compile error, not a runtime surprise
EmplSort e = list.get(0);          // ✓ no cast needed
```

The `<>` diamond lets the compiler infer the type argument.

## `Comparator`

An interface representing an ordering rule. Its single abstract method is:

```java
int compare(T a, T b)
```

with the contract:

```text
negative  →  a comes before b
zero      →  they tie
positive  →  a comes after b
```

You almost never write `compare` by hand any more — the factory methods build it for you:

```java
Comparator.comparing(EmplSort::getName)                 // by a key
          .thenComparing(EmplSort::getAge)              // tie-breaker
          .reversed()                                    // flip the order
Comparator.comparingInt(EmplSort::getAge)               // avoids boxing
Comparator.nullsFirst(...)                              // tolerate nulls
```

## `Comparable` vs `Comparator`

A frequent interview question:

| | `Comparable` | `Comparator` |
|---|---|---|
| lives | **inside** the class being sorted | **outside**, as a separate object |
| method | `compareTo(T other)` | `compare(T a, T b)` |
| how many | **one** per class | as many as you like |
| meaning | the *natural* order | an *alternative* order |
| examples | `String`, `Integer` | anything you pass to `sort` |

```java
// Comparable — one built-in ordering
public class EmplSort implements Comparable<EmplSort> {
    public int compareTo(EmplSort other) {
        return this.name.compareTo(other.name);
    }
}
employeeList.sort(null);          // now uses the natural order

// Comparator — many orderings, chosen at the call site
employeeList.sort(Comparator.comparing(EmplSort::getName));
employeeList.sort(Comparator.comparingInt(EmplSort::getAge));
```

> [!tip] Which should you use?
> **`Comparable`** when there is one obvious, intrinsic order (alphabetical for `String`, numeric for `Integer`).
>
> **`Comparator`** when the order depends on context — and for employees it always does. Sorting by name, age, salary or hire date are all equally valid, so none of them deserves to be "the" natural order.

## Method references and lambdas

```java
EmplSort::getName        // method reference
e -> e.getName()         // equivalent lambda
```

Both are ways of writing a **function as a value**. The four forms of method reference:

| Form | Example | Equivalent lambda |
|---|---|---|
| instance method of a type | `EmplSort::getName` | `e -> e.getName()` |
| static method | `Integer::parseInt` | `s -> Integer.parseInt(s)` |
| method of a specific object | `System.out::println` | `x -> System.out.println(x)` |
| constructor | `EmplSort::new` | `(n, a) -> new EmplSort(n, a)` |

## Enhanced `for`

```java
for (EmplSort e : employeeList) { ... }
```

Reads "for each element". No index, no bounds, no off-by-one possible.

---

# 7. Why this syntax?

## `::` — the method reference

```java
EmplSort::getName     // hand over the method
e.getName()           // call it now
```

Two colons because a single `.` already means "call". `::` says "refer to, do not invoke".

## `<>` — the diamond

```java
new ArrayList<EmplSort>()    // explicit
new ArrayList<>()            // inferred — preferred
```

## `List` on the left, `ArrayList` on the right

Interface as the declared type; implementation as the created object. Lets you swap implementations by changing one word.

## `this.name` vs `name`

Inside a method whose parameter shares a field's name, a bare `name` is the **parameter**. `this.name` is the **field**.

## `@Override`

Optional but always worth writing — it catches signature typos at compile time.

## `'\''` — the escaped quote

```java
'\''      // a char holding a single-quote character
```

Without the backslash, `'''` is a syntax error — Java cannot tell where the literal ends.

## `+` mixing `String` and `char`

```java
"name='" + name + '\''
```

The leftmost operand is a `String`, so every `+` concatenates and the `char`s are converted to text. Had two `char`s been added with no `String` present, they would have been added **numerically** — see [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]].

## `sort` returns `void`

It rearranges in place. A `void` return is a strong hint that something was mutated.

## `size()` vs `length` vs `length()`

| Type | Form |
|---|---|
| `List` | `list.size()` |
| array | `arr.length` |
| `String` | `str.length()` |

Three spellings for "how many" — a historical accident worth memorising.

---

# 8. Method discovery

## `sort`

| Question | Answer |
|---|---|
| **Owner** | `java.util.List` |
| **Accepts** | a `Comparator<? super E>` |
| **Returns** | `void` — sorts in place |
| **What if you pass `null`?** | uses the natural order, or throws `ClassCastException` if the type is not `Comparable` |

## `Comparator.comparing`

| Question | Answer |
|---|---|
| **Owner** | `java.util.Comparator` |
| **Why no object needed?** | it is `static` |
| **Accepts** | a key-extractor function |
| **Returns** | `Comparator<T>` |
| **Requirement** | the extracted key must be `Comparable` |

## `thenComparing`

| Question | Answer |
|---|---|
| **Owner** | `java.util.Comparator` (an instance method) |
| **Why chainable?** | `comparing` returned a `Comparator` |
| **Accepts** | another key extractor |
| **Returns** | a new combined `Comparator` |

## How you would discover all of this in IntelliJ

```text
"I have a List of my own objects. How do I sort it?"
        ↓
Type  employeeList.  and read the list:
  add()  get()  size()  remove()  contains()  sort()  forEach()  stream()
        ↓
sort() looks right. Hover it:
  void sort(Comparator<? super E> c)
        ↓
It needs a Comparator. What is that?
Ctrl+Click on Comparator to open its source.
        ↓
The Javadoc shows static factory methods:
  comparing(...)  comparingInt(...)  naturalOrder()  reverseOrder()
        ↓
comparing() takes a "key extractor function".
For my class, the key is the name → the getter.
        ↓
Type  Comparator.comparing(  and IntelliJ
suggests EmplSort::getName
        ↓
Now: what about ties?
Type  .  after comparing(...) and read:
  thenComparing()  reversed()  thenComparingInt()
        ↓
thenComparing() is exactly the tie-breaker.
```

> [!important] Three IDE habits worth building
> 1. **`Ctrl+Click`** (or `Cmd+Click`) on any type or method jumps to its source and Javadoc. This is how you answer "what does this actually do?" in five seconds.
> 2. **`Ctrl+P`** inside a method call shows the expected parameters.
> 3. **Type the dot and read the list.** Discovery beats memorisation every time.
>
> Nobody memorises `Comparator.comparing(...).thenComparing(...)`. They discover it once, use it a few times, and it sticks.

## A useful refinement: `comparingInt`

While reading `Comparator.` you would notice:

```java
Comparator.comparingInt(EmplSort::getAge)
```

| Question | Answer |
|---|---|
| **Accepts** | a function returning `int` |
| **Returns** | `Comparator<T>` |
| **Why it exists** | avoids boxing each `int` into an `Integer` |

`comparing(EmplSort::getAge)` boxes every age; `comparingInt` does not. Functionally identical, marginally more efficient:

```java
Comparator.comparing(EmplSort::getName).thenComparingInt(EmplSort::getAge)
```

**Reading the neighbours of the method you came for** is one of the cheapest ways to learn an API.

---

# 9. Trace the program with real values

## The list as built

```text
index  name     age
─────────────────────
  0    Niyaz     21
  1    Raez      32
  2    Siha      53
  3    Lafir     23
  4    Sabiq     32
  5    Lafir     33
```

## "Before sorting" output

```text
Before sorting
EmplSort{name='Niyaz', age=21}
EmplSort{name='Raez', age=32}
EmplSort{name='Siha', age=53}
EmplSort{name='Lafir', age=23}
EmplSort{name='Sabiq', age=32}
EmplSort{name='Lafir', age=33}
```

Insertion order — a `List` preserves it.

## Applying the comparator

**Step 1 — order by name.**

`String` comparison is character by character, using Unicode values. All six names start with a capital letter:

```text
L(76) < N(78) < R(82) < S(83)

Lafir  ─┐
Lafir  ─┴─ L
Niyaz  ─── N
Raez   ─── R
Sabiq  ─┐
Siha   ─┴─ S
```

For the two `S` names, comparison moves to the second character:

```text
"Sabiq" vs "Siha"
 S == S  → tie, continue
 a(97) vs i(105)  →  'a' < 'i'
 → Sabiq comes first
```

**Step 2 — break the tie with age.**

Only the two "Lafir" entries tie on name:

```text
Lafir 23  vs  Lafir 33
name: equal → thenComparing runs
age:  23 < 33  →  23 first
```

## "After sorting" output

```text
After sorting
EmplSort{name='Lafir', age=23}
EmplSort{name='Lafir', age=33}
EmplSort{name='Niyaz', age=21}
EmplSort{name='Raez', age=32}
EmplSort{name='Sabiq', age=32}
EmplSort{name='Siha', age=53}
```

## What the comparator returns for sample pairs

```text
compare(Niyaz/21, Raez/32)
    "Niyaz".compareTo("Raez")  →  negative (N < R)
    → Niyaz first, age never consulted

compare(Lafir/33, Lafir/23)
    "Lafir".compareTo("Lafir") →  0  → tie
    → thenComparing runs
    Integer.compare(33, 23)    →  positive
    → 33 goes after 23

compare(Sabiq/32, Siha/53)
    "Sabiq".compareTo("Siha")  →  negative ('a' < 'i')
    → Sabiq first
```

> [!important] `thenComparing` only runs on a tie
> For most pairs the age is never even looked at. The second comparator is consulted **only** when the first returns `0`.
>
> That is why it is called a *tie-breaker*, and it is also why order matters: `comparing(age).thenComparing(name)` would produce a completely different result.

## The objects never move

```text
BEFORE                      AFTER
employeeList                employeeList
┌───┬───┬───┬───┬───┬───┐   ┌───┬───┬───┬───┬───┬───┐
│ 0 │ 1 │ 2 │ 3 │ 4 │ 5 │   │ 3 │ 5 │ 0 │ 1 │ 4 │ 2 │
└─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┘   └─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┘
  ▼   ▼   ▼   ▼   ▼   ▼       └───┴───┴───┴───┴───┘
 the same six objects           the SAME six objects,
 on the heap                    referenced in a new order
```

**Sorting rearranges references, not objects.** No `EmplSort` is created, destroyed, or modified.

---

# 10. Visualize data where useful

## The list and its objects

```text
employeeList (an ArrayList of references)
  ┌─────┐
  │ [0] ├──▶ EmplSort{ name="Niyaz", age=21 }
  │ [1] ├──▶ EmplSort{ name="Raez",  age=32 }
  │ [2] ├──▶ EmplSort{ name="Siha",  age=53 }
  │ [3] ├──▶ EmplSort{ name="Lafir", age=23 }
  │ [4] ├──▶ EmplSort{ name="Sabiq", age=32 }
  │ [5] ├──▶ EmplSort{ name="Lafir", age=33 }
  └─────┘
```

## Building the comparator

```text
Comparator.comparing( EmplSort::getName )
                            │
                            └── "how to get the sort key"
                            ↓
                   ┌──────────────────┐
                   │  Comparator #1   │  order by name
                   └────────┬─────────┘
                            │ .thenComparing( EmplSort::getAge )
                            ↓
                   ┌──────────────────┐
                   │  Comparator #2   │  by name, then by age
                   └────────┬─────────┘
                            │
                  passed to employeeList.sort(...)
```

## How one comparison is decided

```text
        compare(a, b)
              │
              ▼
    a.getName() vs b.getName()
              │
      ┌───────┴────────┐
   not equal          equal
      │                 │
   use that        a.getAge() vs b.getAge()
   result                │
      │                  ▼
      │            use that result
      └────────┬─────────┘
               ▼
     negative → a first
     zero     → tie
     positive → b first
```

## The reordering

```text
BEFORE                      AFTER
─────────────────           ─────────────────
Niyaz  21  ──┐         ┌──  Lafir  23
Raez   32  ─┐│         │┌─  Lafir  33
Siha   53  ┐││         ││┌─ Niyaz  21
Lafir  23  ┘││         │││  Raez   32
Sabiq  32   ││         │││  Sabiq  32
Lafir  33   └┴─────────┴┴┴─ Siha   53

           sorted by name,
           then by age for the two Lafirs
```

## `Comparable` vs `Comparator`

```text
COMPARABLE                        COMPARATOR
"I know my own order"             "Here is a rule for ordering you"

class EmplSort                    Comparator.comparing(...)
  implements Comparable {              │
    compareTo(other) { ... }           │  a separate object
  }                                    ▼
      │                          passed into sort()
      ▼
 one fixed order,                many possible orders,
 built into the class            chosen at the call site
```

---

# 11. Alternative ways to write the same logic

### Better structure — separate the data class from the program

```java
// Employee.java
public class Employee {
    private final String name;        // private, and immutable
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge()     { return age; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + "}";
    }
}
```

```java
// EmployeeSortDemo.java
public class EmployeeSortDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("Niyaz", 21),
                new Employee("Raez", 32),
                new Employee("Siha", 53),
                new Employee("Lafir", 23),
                new Employee("Sabiq", 32),
                new Employee("Lafir", 33)
        ));

        System.out.println("Before sorting");
        employees.forEach(System.out::println);

        employees.sort(Comparator.comparing(Employee::getName)
                                 .thenComparingInt(Employee::getAge));

        System.out.println("After sorting");
        employees.forEach(System.out::println);
    }
}
```

Improvements:

- **Two classes, two jobs** — data separate from program.
- **`private final` fields** — properly encapsulated and immutable, so no setters are needed.
- **`Employee`** — the class is named for what it *is*, not what happens to it.
- **`List.of(...)`** — builds the list in one expression. Note the `new ArrayList<>(...)` wrapper: `List.of` returns an **immutable** list, which `sort` would reject with `UnsupportedOperationException`.
- **`forEach(System.out::println)`** — a method reference to an existing object's method.
- **`thenComparingInt`** — avoids boxing each age.

### As a `record` — Java 16 and later

```java
public record Employee(String name, int age) { }
```

**One line** replaces the fields, constructor, getters, `toString()`, `equals()` and `hashCode()`.

```java
employees.sort(Comparator.comparing(Employee::name)     // note: name(), not getName()
                         .thenComparingInt(Employee::age));
```

> [!tip]
> A `record` is designed for exactly this: a small, immutable carrier of data. Its accessors are named after the fields (`name()`, not `getName()`), and it is immutable by default.
>
> If your Java version supports it, a `record` is the right choice for a class like this. Mentioning it in an interview shows you follow the language.

### Writing the `Comparator` by hand

Useful for understanding what `comparing` builds for you:

```java
employeeList.sort(new Comparator<EmplSort>() {
    @Override
    public int compare(EmplSort a, EmplSort b) {
        int nameResult = a.getName().compareTo(b.getName());
        if (nameResult != 0) {
            return nameResult;              // names differ — done
        }
        return Integer.compare(a.getAge(), b.getAge());   // tie-break on age
    }
});
```

This is an **anonymous inner class**. It is exactly what `Comparator.comparing(...).thenComparing(...)` produces, written out longhand — and seeing the `if (nameResult != 0)` makes the tie-breaking logic concrete.

As a lambda:

```java
employeeList.sort((a, b) -> {
    int nameResult = a.getName().compareTo(b.getName());
    return nameResult != 0 ? nameResult : Integer.compare(a.getAge(), b.getAge());
});
```

> [!warning] Never subtract to compare
> ```java
> return a.getAge() - b.getAge();      // ✗ can overflow
> ```
> If the ages were `Integer.MIN_VALUE` and `1`, the subtraction wraps around and returns the **wrong sign** — silently producing an incorrect order.
>
> `Integer.compare(a, b)` is safe and just as readable. **Always use it.**

### Other orderings

```java
// by age only
employeeList.sort(Comparator.comparingInt(EmplSort::getAge));

// by name, descending
employeeList.sort(Comparator.comparing(EmplSort::getName).reversed());

// oldest first, then alphabetically
employeeList.sort(Comparator.comparingInt(EmplSort::getAge).reversed()
                            .thenComparing(EmplSort::getName));

// case-insensitive by name
employeeList.sort(Comparator.comparing(EmplSort::getName, String.CASE_INSENSITIVE_ORDER));

// tolerate null names
employeeList.sort(Comparator.comparing(EmplSort::getName,
                                       Comparator.nullsFirst(Comparator.naturalOrder())));
```

The **same list**, six different orders, none requiring a change to `EmplSort`. That flexibility is the whole argument for `Comparator` over `Comparable`.

### Sorting a copy

```java
List<EmplSort> sorted = new ArrayList<>(employeeList);
sorted.sort(Comparator.comparing(EmplSort::getName));
// employeeList keeps its original order
```

Or with streams:

```java
List<EmplSort> sorted = employeeList.stream()
        .sorted(Comparator.comparing(EmplSort::getName)
                          .thenComparingInt(EmplSort::getAge))
        .toList();
```

`stream().sorted()` returns a **new** list and leaves the original untouched — the opposite of `list.sort()`.

---

# 12. Common beginner mistakes

## Mistake 1 — forgetting `this` in the constructor

**Incorrect code**

```java
public EmplSort(String name, int age) {
    name = name;
    age = age;
}
```

**Why it is wrong**
Assigns each parameter to itself. The fields keep their defaults, so every employee has `name == null` and `age == 0`.

**What Java does**
Compiles without error. IntelliJ warns *"Assignment to itself"*.

**Correct code**

```java
this.name = name;
this.age = age;
```

**How to recognise it in future**
If every object comes out blank, check the constructor for a missing `this.`.

---

## Mistake 2 — trying to compare objects with `>`

**Incorrect code**

```java
if (employee1 > employee2) { }
```

**What Java does**
`bad operand types for binary operator '>'`.

**Why it is wrong**
Relational operators work on numbers. Java has no idea how to order two employees.

**Correct code**

```java
if (comparator.compare(employee1, employee2) > 0) { }
// or
if (e1.getName().compareTo(e2.getName()) > 0) { }
```

**How to recognise it**
**`>` and `<` are for primitives. Objects need `compareTo` or a `Comparator`.**

---

## Mistake 3 — using `==` to compare names

**Incorrect code**

```java
if (e1.getName() == e2.getName()) { }
```

**Why it is wrong**
`==` compares references. It may appear to work for string literals (thanks to the string pool) and then fail for names built at runtime — the worst kind of bug, since it passes your tests.

**Correct code**

```java
if (e1.getName().equals(e2.getName())) { }
```

**How to recognise it**
**Objects → `.equals()`. Primitives → `==`.**

---

## Mistake 4 — expecting `sort` to return a new list

**Incorrect code**

```java
List<EmplSort> sorted = employeeList.sort(comparator);   // ✗ sort returns void
```

**Correct code**

```java
employeeList.sort(comparator);              // sorts in place
// or, for a new list:
List<EmplSort> sorted = employeeList.stream().sorted(comparator).toList();
```

**How to recognise it**
`incompatible types: void cannot be converted to List`. **A `void` return means something was modified in place.**

---

## Mistake 5 — calling the method instead of referencing it

**Incorrect code**

```java
Comparator.comparing(EmplSort.getName())     // ✗ calls it — on what object?
Comparator.comparing(EmplSort::getName())    // ✗ :: and () together
```

**Correct code**

```java
Comparator.comparing(EmplSort::getName)      // no parentheses
```

**How to recognise it**
`::` hands the method over; `()` calls it. **Never write both.**

---

## Mistake 6 — forgetting `toString()`

**Symptom**

```text
FrequentInterviewPrograms.EmplSort@1b6d3586
FrequentInterviewPrograms.EmplSort@4554617c
```

**Why it happens**
`Object.toString()` prints the class name and hash code.

**Correct code**
Override `toString()` — or generate it with `Alt+Insert`.

**How to recognise it**
Output containing `@` followed by hex digits always means a missing `toString()`.

---

## Mistake 7 — misspelling an overridden method without `@Override`

**Incorrect code**

```java
public String toSting() { ... }        // typo, no @Override
```

**Why it is wrong**
Java treats it as a brand-new method. `println` still calls the inherited `toString()`, and the output is still the hash code — with no error anywhere.

**Correct code**

```java
@Override
public String toString() { ... }
```

**How to recognise it**
**Always write `@Override`.** With it, the typo is an immediate compile error.

---

## Mistake 8 — subtracting inside a comparator

**Incorrect code**

```java
return a.getAge() - b.getAge();
```

**Why it is wrong**
Integer subtraction can overflow and flip the sign, producing a wrong ordering with no error.

**Correct code**

```java
return Integer.compare(a.getAge(), b.getAge());
```

---

## Mistake 9 — sorting an immutable list

**Incorrect code**

```java
List<EmplSort> list = List.of(new EmplSort("A", 1), new EmplSort("B", 2));
list.sort(comparator);        // UnsupportedOperationException
```

**Why it is wrong**
`List.of(...)` returns an **immutable** list. Sorting would modify it.

**Correct code**

```java
List<EmplSort> list = new ArrayList<>(List.of(...));
```

**How to recognise it**
`UnsupportedOperationException` on a collection means you tried to modify an immutable one.

---

## Mistake 10 — using `length` or `length()` on a `List`

**Incorrect code**

```java
employeeList.length          // ✗
employeeList.length()        // ✗
```

**Correct code**

```java
employeeList.size()
```

**How to recognise it**
`List` → `size()`. Array → `length`. `String` → `length()`.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| OOP basics | class, object, constructor, fields, `this` |
| Encapsulation | **do you notice the fields should be `private`?** |
| `toString()` | do you know why it matters and what `@Override` does? |
| Collections | `List` vs array; interface vs implementation |
| Generics | why `List<EmplSort>` rather than raw `List` |
| **`Comparable` vs `Comparator`** | the single most likely question |
| Method references | do you know `::` is shorthand for a lambda? |
| Multi-level sorting | `thenComparing` for tie-breaks |

## Likely follow-up questions

> **"What is the difference between `Comparable` and `Comparator`?"**

`Comparable` lives inside the class and defines one natural order via `compareTo`. `Comparator` is a separate object defining any number of alternative orders via `compare`. Use `Comparable` when one ordering is obviously intrinsic; use `Comparator` when the ordering depends on context — as it does for employees.

> **"Why can't you just use `>` on objects?"**

Relational operators work only on numeric primitives. Java cannot know whether employees should be ordered by name, age, or salary, so it requires you to supply the rule.

> **"What does `EmplSort::getName` mean?"**

A method reference — the method itself passed as a value, equivalent to the lambda `e -> e.getName()`. `Comparator.comparing` needs to know *how to extract* a sort key, not what the key is.

> **"Why declare the variable as `List` but create an `ArrayList`?"**

Programming to the interface. The code depends only on the `List` contract, so swapping to `LinkedList` later requires changing one word.

> **"What happens if you do not override `toString()`?"**

You get `ClassName@hashcode`. Overriding it makes every print statement readable, with no changes at the call sites.

> **"Why should the fields be `private`?"**

Encapsulation — it lets you add validation or change the internal representation later without breaking callers. `public` fields make the getters and setters pointless.

> **"What is the time complexity of the sort?"**

`O(n log n)`. `List.sort` uses TimSort, which is **stable** and performs near-linearly on partially sorted data.

> **"What does 'stable sort' mean, and why does it matter here?"**

A stable sort preserves the relative order of elements that compare equal. It matters because it means you can sort by age first and then by name to achieve the same effect as a compound comparator — although the compound comparator is clearer.

> **"How would you sort by age descending, then name ascending?"**

```java
Comparator.comparingInt(EmplSort::getAge).reversed()
          .thenComparing(EmplSort::getName)
```

> **"What if a name could be `null`?"**

`compareTo` would throw `NullPointerException`. Wrap it: `Comparator.comparing(EmplSort::getName, Comparator.nullsFirst(Comparator.naturalOrder()))`.

> **"Could this class be a `record`?"**

Yes — `public record Employee(String name, int age) { }` replaces the fields, constructor, accessors, `toString`, `equals` and `hashCode` in one line.

---

# 14. Complexity

## Time complexity: `O(n log n)`

Dominated entirely by the sort.

| Operation | Complexity |
|---|---|
| six `add` calls | `O(1)` each, `O(n)` total |
| printing twice | `O(n)` each |
| **`sort`** | **`O(n log n)`** |

In beginner language:

> "Adding an employee and printing one both take a fixed amount of work, so those parts grow in direct proportion to the number of employees. The sorting is different: a good sorting algorithm cannot simply look at each item once — it must compare items against each other. The best general-purpose sorts need about `n × log n` comparisons. For 6 employees that is roughly 15; for 1,000 it is about 10,000 — far fewer than the million a nested-loop approach would need."

> [!note] Why `O(n log n)` and not `O(n²)`
> Compare with [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]], where every item was compared with every other — `O(n²)`.
>
> Good sorting algorithms avoid that by dividing the problem in half repeatedly. `log₂(1000)` is about 10, so each element participates in only ~10 comparisons rather than ~1000.
>
> | `n` | `n²` | `n log n` |
> |---|---|---|
> | 10 | 100 | ~33 |
> | 1,000 | 1,000,000 | ~10,000 |
> | 1,000,000 | 10¹² | ~20,000,000 |
>
> `O(n log n)` is provably the best possible for a general comparison sort.

**What `List.sort` actually uses:** TimSort — a hybrid of merge sort and insertion sort. It is **stable**, and it detects already-ordered runs, so nearly-sorted data can approach `O(n)`.

## Cost of the comparator

Each comparison calls `getName()` and `compareTo` on the names — `O(k)` where `k` is the name length. The age comparison runs only on ties. So strictly the total is `O(n log n × k)`, though `k` is small and usually ignored.

## Space complexity: `O(n)`

The list holds `n` references. TimSort needs `O(n)` temporary space in the worst case.

The comparator itself is `O(1)` — one small object, created once, reused for every comparison.

---

# 15. Edge cases

## Empty list

```java
List<EmplSort> employeeList = new ArrayList<>();
```

Both `for` loops do nothing; `sort` on an empty list is a no-op. Output: just the two headers. No crash.

## Single element

Nothing to compare. `sort` returns immediately. Correct.

## All names identical

```java
new EmplSort("Lafir", 33)
new EmplSort("Lafir", 23)
new EmplSort("Lafir", 45)
```

The name comparison always returns `0`, so `thenComparing` decides every pair: ages `23, 33, 45`. **This is the case that proves the tie-breaker works** — worth testing explicitly.

## Identical name *and* age

```java
new EmplSort("Lafir", 30)
new EmplSort("Lafir", 30)
```

Both comparators return `0`. Because TimSort is **stable**, their original relative order is preserved. No crash, no arbitrary reordering.

## Case-sensitive names

```java
new EmplSort("alice", 30)
new EmplSort("Bob", 25)
```

```text
'B' = 66,  'a' = 97
"Bob" < "alice"
```

**"Bob" sorts before "alice"** — surprising if you expected dictionary order. Uppercase always precedes lowercase in Unicode.

> [!warning]
> This is a real bug source in production systems. If human-friendly alphabetical order is wanted:
> ```java
> Comparator.comparing(EmplSort::getName, String.CASE_INSENSITIVE_ORDER)
> ```
> Or, for genuinely correct locale-aware ordering (accents, non-Latin scripts), use `java.text.Collator`.

## A `null` name

```java
new EmplSort(null, 30)
```

`compareTo` throws `NullPointerException` inside the sort — with a confusing stack trace pointing into JDK internals rather than your code.

```java
Comparator.comparing(EmplSort::getName,
                     Comparator.nullsFirst(Comparator.naturalOrder()))
```

## A `null` element in the list

```java
employeeList.add(null);
```

The comparator calls `getName()` on `null` → `NullPointerException`.

Note that the **printing** loop would survive this: `println(null)` prints the text `"null"` rather than throwing. So the "before sorting" output would look fine and the crash would come later — a good reminder that surviving one stage proves nothing about the next.

## Negative or zero ages

```java
new EmplSort("Test", -5)
```

Sorts fine — the comparator only orders, it does not validate. Nothing prevents impossible data.

> [!tip]
> This is where the `public` fields hurt. With `private` fields you could reject bad input at the boundary:
> ```java
> public EmplSort(String name, int age) {
>     if (age < 0) throw new IllegalArgumentException("age cannot be negative: " + age);
>     this.name = Objects.requireNonNull(name, "name");
>     this.age = age;
> }
> ```
> **A constructor is the natural place to enforce that an object can never exist in an invalid state.**

## Very large lists

A million employees sort in well under a second. `O(n log n)` scales comfortably.

## Modifying an object after sorting

```java
employeeList.sort(comparator);
employeeList.get(0).setName("Zzz");     // possible, because fields are public and setters exist
```

The list is now **out of order**, with nothing to warn you. This is one more argument for immutable data (`private final` fields, no setters, or a `record`).

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Sort employees by name, then by age.

        ↓

What data do I have?
Six people, each with a name AND an age.

        ↓

Can one variable hold two related values?
No. Parallel arrays would let name[3] and
age[3] drift apart with nothing to stop it.

        ↓

Two values that belong together
→ define a CLASS with two fields
   String name, int age

        ↓

How do I create one with its values set?
A constructor — same name as the class,
no return type.
Parameters shadow the fields,
so use this.name = name

        ↓

How does the outside read those values?
Getters. Follow the get/set convention,
because the sorting API relies on it.

        ↓

How will these objects print?
By default: EmplSort@1b6d3586   ✗
→ override toString()
→ and write @Override so a typo
  becomes a compile error

        ↓

Where do six of them live?
An array is fixed-size and I am adding
one at a time → use a List

        ↓

Which List?
ArrayList is the general-purpose default.
Declare the variable as List (the interface)
so the implementation can change later.

        ↓

Why List<EmplSort> and not plain List?
Generics — the compiler then rejects
list.add("oops") instead of letting it
blow up at runtime.

        ↓

Now: how do I sort them?
Try  a > b   →  does not compile.

        ↓

Why not? Because Java has no idea
whether employees order by name, age,
salary or hire date. It refuses to guess.

        ↓

So I must supply the rule.
A rule passed into a method has to be
a VALUE → so the rule is an object
→ a Comparator

        ↓

Do I have to write a whole class for it?
No — Comparator has static factories:
Comparator.comparing(keyExtractor)

        ↓

What is a key extractor?
Something that pulls the sort key out
of an object. For "by name", that is
exactly the getter.
→ EmplSort::getName
   (the method as a value, not a call —
    shorthand for e -> e.getName())

        ↓

What about the two employees named Lafir?
A tie-breaker:
.thenComparing(EmplSort::getAge)
which runs ONLY when the first
comparison returns 0.

        ↓

Read the finished line back as English:
"order by name, then by age"   ✓

        ↓

Does sort return a new list?
No — void. It rearranges in place.
If I needed the original order,
I would copy first.

        ↓

Prove it worked: print before and after.

        ↓

Check awkward cases:
all names equal   → tie-breaker decides  ✓
name AND age equal → stable sort keeps order ✓
mixed case names   → "Bob" before "alice"  ⚠
null name          → NullPointerException  ⚠
```

> [!important] The moment that mattered
> Everything up to `a > b` was ordinary. The **compile error on `>`** is the pivot of the whole program.
>
> That error is Java telling you something true: *there is no single correct way to order employees, so you have to say which one you want.* Once you accept that, `Comparator` stops being intimidating machinery and becomes the obvious answer to a reasonable question.
>
> **When the compiler refuses something, ask what it is protecting you from.** The answer usually teaches you more than the workaround.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `public String name;` | "Every employee has a name, stored as text." |
| `public EmplSort(String name, int age)` | "To make a new employee, you must supply a name and an age." |
| `this.name = name;` | "Put the supplied name into *this particular* employee's name slot — `this.` is needed because the parameter has the same name as the field." |
| `public String getName()` | "Anyone who wants this employee's name can ask for it here." |
| `@Override public String toString()` | "When Java needs to turn this employee into text — for printing, say — use this format instead of the unreadable default. And check at compile time that I spelled the method correctly." |
| `List<EmplSort> employeeList = new ArrayList<>();` | "Make an empty, growable list that can hold only employees. Refer to it by the general `List` type so I could swap the implementation later." |
| `employeeList.add(new EmplSort("Niyaz", 21));` | "Build a new employee named Niyaz aged 21, and put a reference to them at the end of the list." |
| `for(EmplSort e : employeeList)` | "For each employee in the list, call them `e` and do the following." |
| `System.out.println(e);` | "Print this employee — which quietly calls `toString()` to decide how they should look." |
| `Comparator.comparing(EmplSort::getName)` | "Build an ordering rule that sorts employees by whatever `getName()` returns." |
| `.thenComparing(EmplSort::getAge)` | "And when two employees have the same name, break the tie using their age." |
| `employeeList.sort(...)` | "Rearrange this list into that order — in place, changing the list itself." |

The sort line in one sentence:

> **"Reorder the employee list alphabetically by name, and where two share a name, put the younger first."**

And the whole program:

> **"Define what an employee is — a name and an age. Make six of them and put them in a list. Show the list, reorder it by name with age as a tie-breaker, and show it again."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| class / object | a blueprint, and the things built from it |
| instance field | one copy per object; lives as long as the object |
| constructor | same name as the class, no return type, runs on `new` |
| `this` | the current object — required when a parameter shadows a field |
| encapsulation | `private` fields plus accessor methods, so you can add rules later |
| `toString()` | called automatically whenever an object becomes text |
| `@Override` | turns a misspelled override into a compile error |
| `List` vs array | growable versus fixed-size |
| interface vs implementation | declare `List`, instantiate `ArrayList` |
| generics `<T>` | compile-time type safety; no casts needed |
| `Comparator` | an ordering rule packaged as an object |
| `Comparable` | one natural order, defined inside the class |
| method reference `::` | a method passed as a value; shorthand for a lambda |
| stable sort | equal elements keep their relative order |

### Important methods

| Member | Owner | Accepts | Returns |
|---|---|---|---|
| `add(E)` | `List` | an element | `boolean` |
| `get(int)` | `List` | an index | `E` |
| `size()` | `List` | — | `int` |
| `sort(Comparator)` | `List` | an ordering rule | **`void`** — in place |
| `forEach(Consumer)` | `Iterable` | an action | `void` |
| `comparing(f)` | `Comparator` (**static**) | a key extractor | `Comparator<T>` |
| `comparingInt(f)` | `Comparator` (static) | an `int` extractor | `Comparator<T>` — no boxing |
| `thenComparing(f)` | `Comparator` (instance) | a key extractor | a combined `Comparator` |
| `reversed()` | `Comparator` | — | the reversed `Comparator` |
| `compareTo(T)` | `Comparable` | another object | `int` — negative / zero / positive |
| `Integer.compare(a,b)` | `Integer` (static) | two `int`s | `int` — safe, no overflow |

### Important syntax

| Syntax | Meaning |
|---|---|
| `new ClassName(args)` | create an object, running its constructor |
| `this.field` | the current object's field |
| `@Override` | "this replaces an inherited method" |
| `List<T>` | a list holding only `T` |
| `new ArrayList<>()` | the diamond — the type argument is inferred |
| `Type::method` | a method reference |
| `e -> e.getName()` | the equivalent lambda |
| `for (T x : collection)` | enhanced for — "for each" |
| `list.size()` / `arr.length` / `str.length()` | three spellings of "how many" |

### Main interview concept

> **`>` and `<` work on primitives, not objects.** Java cannot know whether employees should be ordered by name, age, or salary, so you supply the rule as a `Comparator`. `Comparator.comparing(EmplSort::getName).thenComparing(EmplSort::getAge)` reads as *"order by name, then by age"* — where `::` passes the getter itself as a value so the comparator knows how to extract each sort key, and `thenComparing` runs only when the first comparison returns `0`.

### Main lesson for code reading

> **When a long chained expression looks intimidating, split it at the dots and name each piece by its return type.**
>
> ```text
> Comparator.comparing(EmplSort::getName).thenComparing(EmplSort::getAge)
> ─────────  ─────────  ──────────────── ──────────────  ──────────────
>  the class  a static   the sort key     an instance      the tie-break
>             factory    extractor        method on the    key
>             →Comparator                 Comparator it
>                                         returned →Comparator
> ```
>
> Each step returns a `Comparator`, which is exactly why the next call is legal — the same reasoning that made `sentence.trim().split(" ")` readable in [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]]. **Ask what each fragment returns, and chained code stops being a wall of symbols.**

---

### Related notes

- [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]] — method chaining, and checking return types at each step
- [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]] — sorting versus a single scan, and in-place mutation
- [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian|LargestArrayElement]] — comparing primitives with `>`, which objects cannot do
- [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]] — `==` versus `.equals()`, and collections from `java.util`
- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — arrays and references, the foundation this program builds on
