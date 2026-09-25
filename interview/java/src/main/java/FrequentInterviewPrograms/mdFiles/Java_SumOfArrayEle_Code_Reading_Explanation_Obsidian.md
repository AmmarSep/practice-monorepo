---
title: Java Code Reading — SumOfArrayEle
tags:
  - java
  - interview-programs
  - code-reading
  - arrays
  - loops
aliases:
  - SumOfArrayEle Explained
  - Sum of Array Elements
---

# Java Code Reading — SumOfArrayEle Explained for a Fresh Java Programmer

> [!note]
> This is the program where **arrays** and **loops** meet for the first time, and it introduces the single most reused pattern in all of programming: the **accumulator**.
>
> An accumulator is a variable that starts at a neutral value and grows as you walk through data. Once you recognise it here, you will see the same skeleton in counting, finding a maximum, building a string, averaging, and dozens of other problems.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Do I need to visit every item? → What holds the running answer? → What is its starting value? → How does each item change it?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class SumOfArrayEle
{
    public static void main(String[] args)
    {
        int[] arr = new int[]{43,53,90,79,89};

        int sum = 0;

        for(int i = 0; i<= arr.length-1; i++)
        {
            sum = sum +arr[i];
        }

        System.out.println("Sum of the array element: "+sum);
    }
}
```

The executable logic is four lines:

```java
int[] arr = new int[]{43,53,90,79,89};
int sum = 0;
for(int i = 0; i<= arr.length-1; i++) { sum = sum + arr[i]; }
System.out.println("Sum of the array element: " + sum);
```

Four lines, but each one carries a distinct idea: **store many values**, **prepare a running total**, **visit each value**, **report**.

---

# 2. What problem is this program solving?

In plain language:

> I have a list of five numbers. Add them all together and print the total.

### What do we know?

- There are several whole numbers.
- They are all the same kind of thing (they belong together as a group).
- We know all of them up front: `43, 53, 90, 79, 89`.

### What do we need?

- **One** number: their total.

### What transformations are required?

Notice the shape of this problem: **many values in, one value out**. That shape has a name — it is a **reduction** (also called a fold or an aggregation).

```text
43   53   90   79   89        ← many
  \   |   |   |   /
   \  |   |   |  /
        354                    ← one
```

Every reduction needs the same three ingredients, and if you can name them you can write the code:

| Ingredient | For this problem |
|---|---|
| A place to keep the running answer | a variable, `sum` |
| A **starting value** that does not change the answer | `0` — because adding zero to anything changes nothing |
| A rule applied to each item | `sum = sum + thisItem` |

> [!important] The identity value
> Why does `sum` start at `0` and not at `1` or at `arr[0]`?
>
> Because `0` is the **identity element for addition**: `x + 0 == x` for every `x`. Starting anywhere else would corrupt the answer — starting at `1` would give you `355`, not `354`.
>
> The same reasoning gives you the right starting value in other problems:
> - Summing → start at `0` (adding 0 changes nothing)
> - Multiplying → start at `1` (multiplying by 1 changes nothing) — this is exactly what [[Java_Factorials_Code_Reading_Explanation_Obsidian|Factorials]] does
> - Counting → start at `0`
> - Building text → start at `""` (the empty string)
> - Finding a maximum → start at the **first element** (there is no neutral value for "largest")
>
> Learn to ask "what starting value leaves the answer untouched?" and you will never guess again.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping this class belongs to, matching the folder on disk. Must be the first statement in the file.

## `public class SumOfArrayEle`

- `public` — usable from anywhere.
- `class` — declares a class.
- `SumOfArrayEle` — the name, `PascalCase`, matching `SumOfArrayEle.java`.

The name abbreviates "Sum of Array Elements". Abbreviating `Elements` to `Ele` saves four characters and costs a little clarity — `SumOfArrayElements` would be marginally better, but the name is perfectly understandable.

## `public static void main(String[] args)`

The JVM entry point.

| Part | Meaning | Why it must be there |
|---|---|---|
| `public` | reachable from outside the class | the JVM is outside |
| `static` | belongs to the class, not an object | no object exists yet when the program starts |
| `void` | returns nothing | the JVM has nowhere to put a return value |
| `main` | the exact name the JVM looks for | fixed by the Java launcher |
| `String[] args` | command-line arguments | text typed after the program name arrives here |

> [!tip] You have already met `String[]`
> Look at the parameter: `String[] args`. That is **an array of Strings** — the same `String[]` type you met in [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]].
>
> So you have been writing an array type since your very first Java program without realising it. `main` receives an array because a user can type any number of arguments, and an array is how Java carries "several values of the same type".

## No imports

The program uses `int`, `int[]`, `String` and `System`. Arrays and primitives are built into the language; `String` and `System` live in `java.lang`, imported automatically. So no `import` line is needed.

## Variables

| Variable | Type | Role |
|---|---|---|
| `arr` | `int[]` | the input data — five numbers |
| `sum` | `int` | the accumulator — the running total |
| `i` | `int` | the loop counter — the current index |

Notice the **three distinct roles**: data, accumulator, counter. Almost every loop-based program has exactly these three kinds of variable. Learning to classify a variable by its role makes unfamiliar code much easier to read.

---

# 4. Line-by-line explanation

## 4.1 — `int[] arr = new int[]{43,53,90,79,89};`

```java
int[] arr = new int[]{43,53,90,79,89};
```

**What is this?**
Creating an array of five integers and storing a reference to it in `arr`.

**Why do we need it?**
The requirement gives us *several* numbers, not one. A single `int` variable holds exactly one value. To hold five values we need a structure that holds many values of the same type — that is an array.

**How would a beginner know to write it?**
Ask the decisive question: **"How many values do I need to store?"**

- Exactly one → a simple variable (`int x`)
- A fixed, known number of the same type → an **array** (`int[]`)
- An unknown or changing number → a `List` (`ArrayList<Integer>`)

Here we have five known values, all whole numbers. `int[]` is the natural fit.

**What type of data is involved and why?**

`int[]` means "an array whose elements are `int`". Reading the type aloud helps:

```text
int   []      arr
 │     │       │
 │     │       └── the name of the reference
 │     └────────── "array of"
 └──────────────── "int"

→ "arr is a reference to an array of int"
```

Why not `double[]`? The values `43, 53, 90...` have no decimal parts, and their sum will not either. Why not `String[]`? You cannot add text with `+` and get arithmetic — `"43" + "53"` would produce `"4353"`.

**What does each part of the syntax mean?**

```text
int[]   arr   =   new   int[]   {43,53,90,79,89}   ;
  │      │    │    │      │            │
  │      │    │    │      │            └── the initial contents
  │      │    │    │      └─────────────── the type being created
  │      │    │    └────────────────────── allocate a new object on the heap
  │      │    └─────────────────────────── store the reference
  │      └──────────────────────────────── the reference variable's name
  └─────────────────────────────────────── the declared type
```

**What is `new` doing?**

`new` allocates memory for a new object and returns a **reference** to it. Arrays are objects in Java, so creating one requires `new`.

> [!important] `arr` does not contain the numbers
> This is the concept that unlocks arrays.
>
> ```text
>   arr                    the actual array object
>  ┌─────┐                ┌────┬────┬────┬────┬────┐
>  │ ref ├───────────────▶│ 43 │ 53 │ 90 │ 79 │ 89 │
>  └─────┘                └────┴────┴────┴────┴────┘
>                           0    1    2    3    4
> ```
>
> `arr` is a **signpost**, not a container. It holds the *address* of the array, which lives elsewhere in memory (on the heap).
>
> This is why arrays behave differently from `int`:
>
> ```java
> int a = 5;
> int b = a;      // b gets a COPY of 5 — independent
>
> int[] p = {1, 2, 3};
> int[] q = p;    // q gets a copy of the ADDRESS — both point at the SAME array
> q[0] = 99;
> // p[0] is now 99 too!
> ```
>
> Primitives copy values. Object references copy addresses. See [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]] for the other half of this story.

**What does Java do when it reaches this line?**

1. Allocates a block of memory on the heap large enough for five `int`s.
2. Writes `43, 53, 90, 79, 89` into consecutive slots.
3. Stores the array's length (`5`) alongside it — permanently and unchangeably.
4. Returns the address of that block.
5. Copies that address into the variable `arr`.

**Why the name `arr`?**
It is short for "array". Honest but not descriptive — it says *what kind of thing* it is rather than *what it means*. `numbers` or `values` would be better, because they describe the content. In a tiny demonstration program `arr` is acceptable; in real code, name the meaning.

> [!note] Three ways to write the same array
> All of these produce an identical array:
>
> ```java
> int[] arr = new int[]{43,53,90,79,89};   // the form used here — always legal
> int[] arr = {43,53,90,79,89};             // shorthand — only when declaring
> int arr[] = {43,53,90,79,89};             // C-style brackets — legal but discouraged
> ```
>
> The shorthand `{...}` is the most common in modern Java. The long form with `new int[]` is required when you are *not* declaring — for example when passing an array directly to a method:
>
> ```java
> someMethod(new int[]{1, 2, 3});   // the shorthand would not compile here
> ```
>
> `int arr[]` (the third form) is used by [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]]. It works, but Java style prefers the brackets attached to the type.

---

## 4.2 — `int sum = 0;`

```java
int sum = 0;
```

**What is this?**
The accumulator: a variable that will hold the running total as we walk through the array.

**Why do we need it?**
Addition happens two numbers at a time. To add five numbers you must add the first two, then add the third to that result, then the fourth, and so on. Each step needs somewhere to keep "the total so far". That somewhere is `sum`.

**How would a beginner know to write it?**
Ask: **"Do I need to remember something between one item and the next?"** Yes — the running total. Anything that must survive from one loop iteration to the next must be declared **before** the loop.

> [!warning] Why `sum` must be declared outside the loop
> ```java
> for (int i = 0; i <= arr.length - 1; i++) {
>     int sum = 0;              // ✗ created fresh every iteration
>     sum = sum + arr[i];
> }
> System.out.println(sum);      // ✗ error: cannot find symbol
> ```
>
> A variable declared inside `{ }` is created when execution enters the block and destroyed when it leaves. Declaring `sum` inside the loop would reset it to `0` on every single pass, and it would not exist at all after the loop.
>
> **Rule: a variable's declaration must live in a scope that outlives its usefulness.** `sum` is needed after the loop, so it is declared before the loop.

**What type and why?**
We are adding `int` values, and the result of adding `int`s is an `int`. Type consistency: `sum` must be an `int`.

**Why the starting value `0`?**
Because `0` is the identity for addition — adding it changes nothing. Starting at `0` means the first real addition (`0 + 43`) produces exactly `43`, which is correct.

Test the alternative: start at `1`, and you get `355`. Wrong by exactly the amount you seeded.

**What does Java do?**
Reserves 32 bits, labels it `sum`, writes `0`.

**Why the name `sum`?**
Excellent name — it describes the meaning of the data precisely. `total` would be equally good. `s` or `x` would be poor.

---

## 4.3 — `for(int i = 0; i<= arr.length-1; i++)`

```java
for(int i = 0; i<= arr.length-1; i++)
```

**What is this?**
A `for` loop that runs its body once for each index of the array.

**Why do we need it?**
We must touch every element. Writing it out by hand would be:

```java
sum = sum + arr[0];
sum = sum + arr[1];
sum = sum + arr[2];
sum = sum + arr[3];
sum = sum + arr[4];
```

That works for exactly five elements. Change the array and you must rewrite the code. A loop expresses "do this for every element" **regardless of how many there are** — which is the entire point.

**How would a beginner know to write it?**
Whenever you catch yourself about to copy-paste a line with only a number changing, that is a loop asking to be written.

**What are the three parts?**

A `for` loop header has exactly three sections separated by semicolons:

```text
for ( initialisation ; condition ; update )
       │                │           │
       │                │           └── runs AFTER each pass
       │                └────────────── checked BEFORE each pass; loop runs while true
       └─────────────────────────────── runs ONCE, before anything else
```

For this loop:

| Part | Code | Meaning |
|---|---|---|
| initialisation | `int i = 0` | create a counter starting at the first index |
| condition | `i <= arr.length-1` | keep going while `i` is a valid index |
| update | `i++` | move to the next index after each pass |

**Why does `i` start at `0`?**

Because **Java arrays are zero-indexed**. The first element lives at index `0`, not `1`.

```text
value:   43   53   90   79   89
index:    0    1    2    3    4
          ↑                   ↑
       first               last
```

An array of length 5 has valid indexes `0` through `4`. There is no `arr[5]`.

**Why the name `i`?**
`i` stands for "index" (or "iterator") and is a universally understood convention for a loop counter. This is the one place where a single-letter name is genuinely good style — every Java programmer alive reads `i` as "the loop index" instantly. Nested loops conventionally use `j` and then `k`.

**What is `arr.length`?**

The number of elements in the array: `5`.

> [!warning] `length` vs `length()` — a distinction worth burning in
> | Thing | Syntax | Why |
> |---|---|---|
> | array | `arr.length` | **no parentheses** — it is a *field* |
> | `String` | `str.length()` | **with parentheses** — it is a *method* |
> | `List` | `list.size()` | a different method name entirely |
>
> Arrays are a special language-level construct with a built-in `length` field fixed at creation. `String` is an ordinary class, so it exposes a method.
>
> If you write `arr.length()` the compiler says `cannot find symbol: method length()`. If you write `str.length` it says `cannot find symbol: variable length`. Both errors mean "you used the wrong form" — and now you know which is which.

**Why `i <= arr.length-1` rather than `i < arr.length`?**

These two conditions are **exactly equivalent**:

```text
arr.length      = 5
arr.length - 1  = 4

i <= 4     is true for i = 0,1,2,3,4      ← this program's form
i <  5     is true for i = 0,1,2,3,4      ← the conventional form
```

Both visit indexes `0` to `4` — all five elements. The program is **correct**.

> [!tip] Which form should you write?
> The idiomatic Java form is `i < arr.length`. Prefer it, for three reasons:
>
> 1. It is shorter and has no arithmetic to get wrong.
> 2. Every Java programmer's eye is trained on it, so it reads faster.
> 3. `<=` combined with `-1` gives you **two** chances to make an off-by-one error instead of one.
>
> The form used here is not wrong — just less conventional. But note how easily it *could* have gone wrong: `i <= arr.length` (forgetting the `-1`) would crash, and `i < arr.length-1` would silently skip the last element. That second mistake is exactly the bug that lives in [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]].

**What is `i++`?**

The **increment operator**. `i++` means `i = i + 1`.

Without it the loop would never end, because `i` would stay `0` forever and the condition would stay `true`. That is an **infinite loop**.

**What does Java do when it reaches this line?**

1. Runs `int i = 0` once.
2. Checks `0 <= 4` → `true`, so runs the body.
3. Runs `i++` → `i` becomes `1`.
4. Checks `1 <= 4` → `true`, runs the body.
5. ...repeats...
6. After the pass with `i = 4`, runs `i++` → `i` becomes `5`.
7. Checks `5 <= 4` → `false`, so the loop **stops** and execution continues after the closing brace.

Note that the condition is checked **five times successfully and once unsuccessfully** — six checks in total, five body executions.

---

## 4.4 — `sum = sum + arr[i];`

```java
sum = sum + arr[i];
```

**What is this?**
The accumulation step. The heart of the program.

**Why do we need it?**
This is the rule applied to each element: "add this element into the running total."

**How would a beginner know to write it?**
Ask: **"What should happen to my running answer when I see one more item?"** For a sum, the answer is "add it in".

**What does each part mean?**

```text
sum   =   sum   +   arr[i]   ;
 │    │    │    │      │
 │    │    │    │      └── read the element at position i
 │    │    │    └───────── addition
 │    │    └────────────── read the CURRENT value of sum
 │    └─────────────────── store the result back into sum
 └──────────────────────── the destination
```

> [!important] Read the right-hand side first
> `sum = sum + arr[i]` looks self-referential and confuses many beginners. It is not circular, because assignment has a strict order:
>
> ```text
> Step 1: evaluate everything on the RIGHT
>         sum + arr[i]  →  e.g. 96 + 90  →  186
>
> Step 2: store that result into the variable on the LEFT
>         sum  ←  186
> ```
>
> The old value of `sum` is read *before* the new value is written. There is no paradox.
>
> This is why `=` is called assignment, not equality. As a mathematical equation `sum = sum + 90` has no solution. As a Java command it means "make sum's new value be its old value plus 90".

**What is `arr[i]`?**

Array element access. The square brackets mean "give me the element at this position".

```text
arr[0] → 43
arr[1] → 53
arr[2] → 90
arr[3] → 79
arr[4] → 89
```

Because `i` changes on every pass, `arr[i]` refers to a different element each time. **That is the mechanism by which one line of code touches five values.**

**What type is involved?**
`arr[i]` is an `int` (an element of an `int[]`). `sum` is an `int`. `int + int` produces an `int`, which is stored into an `int`. Every type matches.

**What does Java do when it reaches this line?**
Reads `sum`, reads `arr[i]`, adds them, writes the result back into `sum`.

**Where does the result go?**
Back into `sum`, overwriting the previous total.

> [!tip] The shorthand
> ```java
> sum += arr[i];      // exactly equivalent to  sum = sum + arr[i];
> ```
> `+=` is a **compound assignment operator**. It is shorter, and it makes the intent ("add into") more obvious. Java also has `-=`, `*=`, `/=`, and `%=`.
>
> Both forms compile to the same thing. Write whichever you find clearer while learning; `+=` is what you will see in professional code.

---

## 4.5 — `System.out.println("Sum of the array element: "+sum);`

```java
System.out.println("Sum of the array element: "+sum);
```

**What is this?**
Printing the label and the final total.

**Why do we need it?**
The loop computes the answer but leaves it invisible inside `sum`. Printing is how the program reports its result.

**What does each part mean?**

| Part | What it is |
|---|---|
| `System` | a class in `java.lang` |
| `out` | a `static` field of type `PrintStream` |
| `println` | prints its argument, then a newline |
| `"Sum of the array element: "` | a `String` literal (note the trailing space) |
| `+` | string concatenation, because the left side is a `String` |
| `sum` | the `int` to append |

**What does Java do?**

```text
"Sum of the array element: "  +  354
        String                     int
                ↓
   int is converted to its text form "354"
                ↓
"Sum of the array element: 354"      ← a String
                ↓
        passed to println
```

**Where does the result go?**
To the console:

```text
Sum of the array element: 354
```

> [!note] Why this line is safe from the `58` trap
> In [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]], `"label" + a + b` printed `58` because two numbers were concatenated one after the other.
>
> Here there is only **one** value after the label, so there is nothing to accidentally glue together. The output is unambiguous.
>
> The trap only appears when you concatenate two or more numbers in a row without a separator between them.

---

# 5. How to think like the programmer

```text
Requirement
"Add up all the numbers in a list"
        ↓
How many values do I have?
Several — five, all whole numbers
        ↓
What holds several values of one type?
An array → int[]
        ↓
How many values do I need to produce?
One — the total
        ↓
So this is MANY IN, ONE OUT — a reduction
        ↓
Every reduction needs three things:
  (a) somewhere to keep the running answer
  (b) a starting value that changes nothing
  (c) a rule for folding in each item
        ↓
(a) Where does the running answer live?
A variable → int sum
        ↓
(b) What starting value leaves the answer untouched?
For addition, zero → sum = 0
        ↓
(c) What is the rule per item?
"Add it to the total" → sum = sum + item
        ↓
How do I reach every item?
Walk the indexes with a for loop
        ↓
Where do the valid indexes start and end?
Arrays are zero-indexed → 0 to length-1
        ↓
Write the loop header
for (int i = 0; i <= arr.length-1; i++)
        ↓
How do I refer to "the current item"?
arr[i]
        ↓
Where must sum be declared?
Before the loop — it must survive all iterations
and still exist afterwards
        ↓
What do I do with the answer?
Print it
```

> [!important] The accumulator skeleton — memorise the shape, not the code
> ```java
> TYPE accumulator = STARTING_VALUE;      // before the loop
>
> for (int i = 0; i < data.length; i++) {
>     accumulator = COMBINE(accumulator, data[i]);
> }
>
> use(accumulator);                        // after the loop
> ```
>
> Fill in the blanks differently and you get a different program:
>
> | Problem | TYPE | STARTING_VALUE | COMBINE |
> |---|---|---|---|
> | sum | `int` | `0` | `acc + item` |
> | product / factorial | `int` | `1` | `acc * item` |
> | count all | `int` | `0` | `acc + 1` |
> | count matching | `int` | `0` | `if (test) acc + 1` |
> | maximum | `int` | `data[0]` | `if (item > acc) item` |
> | build text | `String` | `""` | `acc + item` |
>
> Four of the programs in this folder are just different fillings of this one skeleton. Learn the skeleton and you have learned them all at once.

---

# 6. Deep explanation of important Java concepts used

## Array

An array is a **fixed-size, ordered collection of values of the same type**, stored in consecutive memory.

Three properties define it:

1. **Fixed length.** Set when created, never changeable. `arr` will always have exactly 5 slots. To "grow" an array you must create a new one and copy.
2. **Homogeneous.** Every element is the same type. An `int[]` cannot hold a `String`.
3. **Zero-indexed.** Positions run from `0` to `length - 1`.

```text
              arr.length = 5
        ┌────┬────┬────┬────┬────┐
  arr ─▶│ 43 │ 53 │ 90 │ 79 │ 89 │
        └────┴────┴────┴────┴────┘
   index   0    1    2    3    4
                              ↑
                    last valid index = length - 1
```

**Arrays are objects.** That is why they are created with `new`, why they live on the heap, and why a variable holds a reference rather than the data itself.

## `int[]` — reading the type

```text
int[]   →  "array of int"
int     →  "a single int"
```

The `[]` is part of the **type**, not part of the name. That is why `int[] arr` is preferred over `int arr[]` — it keeps all the type information on the left where it belongs.

## Index and element access

```java
arr[i]
```

- `arr` — which array.
- `[ ]` — the subscript operator.
- `i` — which position, counting from `0`.

Java **checks every index at runtime**. If `i` is negative or `>= arr.length`, the program throws `ArrayIndexOutOfBoundsException` immediately rather than silently reading adjacent memory.

> [!tip]
> This runtime check is a feature, not a nuisance. In C, reading past the end of an array silently returns garbage or corrupts memory. Java stops you at the exact line, with the offending index in the message:
>
> ```text
> Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException:
>     Index 5 out of bounds for length 5
> ```
>
> Read that message carefully: it tells you *both* the bad index and the array's real length. Nearly every array bug is diagnosable from that one line.

## The `for` loop

```java
for (initialisation; condition; update) {
    body
}
```

Execution order:

```text
1. initialisation           (once)
   ↓
2. check condition ─── false ──▶ exit loop
   ↓ true
3. run body
   ↓
4. run update
   ↓
   back to step 2
```

Use a `for` loop when you know **how many times** to repeat, or when you need the **index**. Use a `while` loop when you repeat until some condition changes and there is no natural counter — as in [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]].

## Scope

A variable exists only inside the `{ }` block where it was declared.

```java
int sum = 0;                                   // scope: rest of main
for (int i = 0; i <= arr.length-1; i++) {      // scope of i: the loop only
    sum = sum + arr[i];                        // both visible here
}
System.out.println(sum);                       // sum ✓ visible
// System.out.println(i);                      // ✗ i no longer exists
```

The counter `i` is declared **inside the loop header**, so it dies with the loop. That is deliberate and good — it prevents you from accidentally using a stale counter later, and it lets you reuse the name `i` in the next loop.

## The accumulator pattern

A variable that:

1. Is declared **before** the loop (so it survives every iteration and outlives the loop).
2. Starts at a value that does not distort the result.
3. Is **updated using its own previous value** inside the loop.
4. Is **read after** the loop.

If you can spot those four traits in unfamiliar code, you instantly know what the loop is for — even before reading the body.

## Increment operator `++`

```java
i++;      // post-increment: use the current value, then add 1
++i;      // pre-increment:  add 1, then use the new value
```

As a standalone statement — which is how a `for` loop's update section uses it — they are **identical**. The difference only matters when the result is used within a larger expression:

```java
int i = 5;
int a = i++;   // a = 5, i = 6   (a got the old value)

int j = 5;
int b = ++j;   // b = 6, j = 6   (b got the new value)
```

In a `for` header, always write `i++`. It is the universal convention.

## Compound assignment `+=`

```java
sum += arr[i];     // identical to  sum = sum + arr[i];
```

Available for all arithmetic operators: `+=`, `-=`, `*=`, `/=`, `%=`.

---

# 7. Why this syntax?

## `[]` — three completely different jobs

This is genuinely confusing at first, because the same brackets mean different things depending on position:

| Where it appears | What it means | Example |
|---|---|---|
| after a **type** | "array of that type" | `int[] arr` |
| after `new TYPE` with a number | "create an array this big" | `new int[5]` |
| after an **array variable** | "the element at this position" | `arr[i]` |

```text
int[] arr = new int[]{43,53,90,79,89};
   ↑             ↑
 "array of"   "create an array of"

sum = sum + arr[i];
               ↑
        "element number i"
```

## `{ }` — two completely different jobs

| Where | Meaning |
|---|---|
| after `new int[]`, or after `=` in a declaration | **array initialiser** — the contents |
| after `for (...)`, `if (...)`, a method or class header | **block** — a group of statements |

```java
int[] arr = new int[]{43,53,90,79,89};   // ← initialiser: data
for (...) { sum = sum + arr[i]; }        // ← block: instructions
```

Same symbol, entirely unrelated purposes. Judge by what comes before it.

## `;` inside `for(...)` vs at the end of a statement

Inside a `for` header, semicolons **separate the three sections** — they do not terminate statements:

```java
for (int i = 0 ; i <= arr.length-1 ; i++)
              ↑                    ↑
         separator            separator      ← exactly two, never three
```

> [!warning] The stray-semicolon bug
> ```java
> for (int i = 0; i <= arr.length-1; i++);     // ← note the ; at the end
> {
>     sum = sum + arr[i];
> }
> ```
> This compiles, runs, and produces the **wrong answer**. The `;` becomes the loop's entire (empty) body, so the loop spins 5 times doing nothing. The block below then runs once, and `arr[i]` fails to compile because `i` is out of scope.
>
> Rule: **a `for` header is never followed by a semicolon.**

## `<=` vs `<`

| Condition | With `arr.length = 5` | Indexes visited | Verdict |
|---|---|---|---|
| `i < arr.length` | `i < 5` | 0,1,2,3,4 | ✓ idiomatic |
| `i <= arr.length-1` | `i <= 4` | 0,1,2,3,4 | ✓ this program — correct but wordy |
| `i <= arr.length` | `i <= 5` | 0,1,2,3,4,5 | ✗ **crashes** at `arr[5]` |
| `i < arr.length-1` | `i < 4` | 0,1,2,3 | ✗ **silently skips the last element** |

Study that table. Two of those four are wrong, and the fourth one fails *silently* — which is far more dangerous than a crash. That exact silent failure is the bug in [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]].

## `+` — addition or concatenation?

| Line | Left operand | `+` means | Result |
|---|---|---|---|
| `sum + arr[i]` | `int` | addition | `int` |
| `"Sum...: " + sum` | `String` | concatenation | `String` |

Same symbol, decided entirely by the operand types.

## `length` vs `length()` vs `size()`

| Type | Correct form |
|---|---|
| array | `arr.length` |
| `String` | `str.length()` |
| `List` / `ArrayList` | `list.size()` |

Three different spellings for "how many". There is no elegant reason — it is a historical accident of Java's design. Memorise the three.

---

# 8. Method discovery

This program calls only `println`, but the *discovery skills* it teaches apply everywhere.

## `println`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.io.PrintStream` |
| **Why can we call it here?** | `System.out` is a `PrintStream` object |
| **What does it do?** | writes the argument's text form, then a newline |
| **What arguments does it accept?** | overloaded: `String`, `int`, `double`, `char`, `boolean`, `Object`, ... |
| **Which overload runs here?** | the `String` one — concatenation happens first |
| **What does it return?** | `void` |

## Discovering `length` — and why autocomplete behaves oddly for arrays

Type this in IntelliJ:

```java
arr.
```

The suggestion list is surprisingly short:

```text
length          ← a field, no parentheses
clone()
equals()
hashCode()
toString()
getClass()
```

That is *all* an array offers. Arrays are a primitive language construct, not a rich class — they have exactly one useful member of their own, `length`, plus what they inherit from `Object`.

Compare with a `String`:

```java
str.
```

which offers `length()`, `charAt()`, `substring()`, `split()`, `trim()`, `toUpperCase()`, `contains()`, `replace()`, `indexOf()`, and dozens more.

> [!important] The lesson from that comparison
> **The short array list is exactly why array algorithms are written manually with loops.** There is no `arr.sum()` because arrays simply do not have methods like that.
>
> When you need array operations beyond `length`, you look in the **utility classes**:
>
> ```java
> import java.util.Arrays;
>
> Arrays.sort(arr);              // sort in place
> Arrays.toString(arr);          // "[43, 53, 90, 79, 89]"
> Arrays.stream(arr).sum();      // 354
> Arrays.fill(arr, 0);           // set every element
> Arrays.copyOf(arr, 10);        // resize into a new array
> ```
>
> This is a general Java pattern: when a type is too primitive to carry its own methods, a companion class named after it (`Arrays`, `Collections`, `Objects`, `Math`) provides `static` helpers.
>
> Try typing `Arrays.` in IntelliJ and read the list. It is one of the highest-value five minutes a beginner can spend.

## How you would find "sum an array" from scratch

```text
"I have an int[] and I want the total."
        ↓
Type  arr.  in the IDE  →  no sum() method exists
        ↓
So either write the loop myself (the accumulator pattern)
or look for a utility class
        ↓
Search: "java sum int array"
        ↓
Find: Arrays.stream(arr).sum()
        ↓
Check the Javadoc:
  Arrays.stream(int[]) returns an IntStream
  IntStream.sum() returns an int
        ↓
Types line up:  int total = Arrays.stream(arr).sum();
```

> [!tip]
> Notice the final step: **check what the method returns before deciding what variable to store it in.** `sum()` returns `int`, so the holder is an `int`. Verifying the return type before writing the left-hand side is the habit that makes method chaining safe — the same habit that made `String[] words = ...split(" ")` correct in [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]].

---

# 9. Trace the program with real values

```text
Array:  arr = [43, 53, 90, 79, 89]
        arr.length = 5
        arr.length - 1 = 4
        so the condition is:  i <= 4
```

## Iteration table

```text
─────────────────────────────────────────────────────────────────────────
Pass  i   i<=4?   arr[i]   sum (before)   sum + arr[i]   sum (after)
─────────────────────────────────────────────────────────────────────────
 —    —     —       —           0             —              0    ← start
 1    0    true     43          0          0 + 43            43
 2    1    true     53         43         43 + 53            96
 3    2    true     90         96         96 + 90           186
 4    3    true     79        186        186 + 79           265
 5    4    true     89        265        265 + 89           354
 —    5   false     —         354             —             354    ← exit
─────────────────────────────────────────────────────────────────────────
```

## Reading the exit row carefully

After the fifth pass, `i++` makes `i` equal `5`. The condition `5 <= 4` is `false`, so the loop stops **without running the body a sixth time**.

This matters: `arr[5]` is never evaluated. Had it been, the program would crash with `ArrayIndexOutOfBoundsException`. The loop condition is precisely what protects you.

## Verifying by hand

```text
43 + 53 = 96
96 + 90 = 186
186 + 79 = 265
265 + 89 = 354      ✓
```

## Console output

```text
Sum of the array element: 354
```

---

# 10. Visualize data where useful

## The array in memory

```text
     arr
   ┌─────┐
   │ ref │
   └──┬──┘
      │
      ▼
   ┌──────┬──────┬──────┬──────┬──────┐
   │  43  │  53  │  90  │  79  │  89  │      length = 5
   └──────┴──────┴──────┴──────┴──────┘
      0      1      2      3      4
```

## The accumulator growing

```text
i = 0     sum:   0 ──(+43)──▶  43
                     ▲
                   arr[0]

i = 1     sum:  43 ──(+53)──▶  96
                     ▲
                   arr[1]

i = 2     sum:  96 ──(+90)──▶ 186
                     ▲
                   arr[2]

i = 3     sum: 186 ──(+79)──▶ 265
                     ▲
                   arr[3]

i = 4     sum: 265 ──(+89)──▶ 354
                     ▲
                   arr[4]

                             ┌─────┐
                    result = │ 354 │
                             └─────┘
```

## Many values in, one value out

```text
    43    53    90    79    89
     │     │     │     │     │
     └──┬──┘     │     │     │
        96       │     │     │
        └────┬───┘     │     │
            186        │     │
             └─────┬───┘     │
                  265        │
                   └─────┬───┘
                        354
```

## The loop's control flow

```text
        int i = 0
            │
            ▼
      ┌─▶ i <= 4 ? ─── false ──▶ print sum ──▶ done
      │     │ true
      │     ▼
      │  sum = sum + arr[i]
      │     │
      │     ▼
      │    i++
      └─────┘
```

---

# 11. Alternative ways to write the same logic

### Beginner-friendly version — the conventional loop condition

```java
int[] numbers = {43, 53, 90, 79, 89};
int sum = 0;

for (int i = 0; i < numbers.length; i++) {
    sum += numbers[i];
}

System.out.println("Sum of the array elements: " + sum);
```

Three small improvements, none of which change the behaviour:

- `i < numbers.length` instead of `i <= numbers.length-1` — the idiomatic form.
- `sum += ...` instead of `sum = sum + ...` — states the intent more directly.
- `numbers` instead of `arr` — the name describes the content, not the container.

### Cleaner version — the enhanced `for` loop

```java
int[] numbers = {43, 53, 90, 79, 89};
int sum = 0;

for (int number : numbers) {
    sum += number;
}

System.out.println("Sum of the array elements: " + sum);
```

Read `for (int number : numbers)` as **"for each `number` in `numbers`"**. The colon means "in".

| | Indexed `for` | Enhanced `for` |
|---|---|---|
| gives you | the index `i` | the element directly |
| off-by-one risk | yes | **impossible** |
| can you modify elements? | yes, via `arr[i] = ...` | no — `number` is a copy |
| can you skip or step? | yes | no |
| do you know the position? | yes | no |

> [!tip] The rule for choosing
> **If you do not need the index, do not create one.**
>
> Summing needs only the values, so the enhanced `for` is the better choice — and it makes every off-by-one bug in this note structurally impossible.
>
> You *do* need the indexed form when you must know where you are (comparing `arr[i]` with `arr[j]`, as in [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]]) or when you must write back into the array.

### Concise version — streams

```java
import java.util.Arrays;

int[] numbers = {43, 53, 90, 79, 89};
int sum = Arrays.stream(numbers).sum();

System.out.println("Sum of the array elements: " + sum);
```

One line, no loop, no accumulator, no index. `Arrays.stream(int[])` returns an `IntStream`; `IntStream.sum()` returns an `int`.

This is what you would write in production code. It is **not** what you should write in an interview for "sum an array" — the interviewer wants to see whether you can write the loop. Mention the stream version *after* showing the loop, as evidence you know the standard library.

### More robust version — handling large sums

```java
int[] numbers = {43, 53, 90, 79, 89};
long sum = 0;                          // long, not int

for (int number : numbers) {
    sum += number;
}

System.out.println("Sum of the array elements: " + sum);
```

Why `long`? See the overflow discussion in edge cases. With five small values it is unnecessary, but with a large array of large values, an `int` accumulator can overflow silently.

### The version you should not write

```java
int sum = arr[0] + arr[1] + arr[2] + arr[3] + arr[4];
```

It produces `354` correctly. It is still bad, because it only works for arrays of exactly length 5. Add a sixth number and the code is silently wrong. **A loop expresses the intent; unrolled addition expresses a coincidence.**

---

# 12. Common beginner mistakes

## Mistake 1 — declaring the accumulator inside the loop

**Incorrect code**

```java
for (int i = 0; i <= arr.length-1; i++) {
    int sum = 0;
    sum = sum + arr[i];
}
System.out.println(sum);   // error: cannot find symbol
```

**Why it is wrong**
`sum` is created fresh and reset to `0` on every pass, so it never accumulates. And it does not exist after the loop, so the print fails to compile.

**What Java expects**
Anything that must survive across iterations must be declared in an enclosing scope.

**Correct code**

```java
int sum = 0;
for (int i = 0; i <= arr.length-1; i++) {
    sum = sum + arr[i];
}
System.out.println(sum);
```

**How to recognise it in future**
`cannot find symbol` on a variable you *know* you declared almost always means you declared it inside a narrower block.

---

## Mistake 2 — off by one, going too far

**Incorrect code**

```java
for (int i = 0; i <= arr.length; i++) {      // <= without the -1
    sum = sum + arr[i];
}
```

**Why it is wrong**
When `i` reaches `5`, `arr[5]` does not exist — valid indexes stop at `4`.

**What Java does**

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException:
    Index 5 out of bounds for length 5
```

**Correct code**

```java
for (int i = 0; i < arr.length; i++) {
```

**How to recognise it**
The exception message names the bad index and the real length. If the index equals the length exactly, you went one too far.

---

## Mistake 3 — off by one, stopping too early

**Incorrect code**

```java
for (int i = 0; i < arr.length - 1; i++) {   // < with the -1
    sum = sum + arr[i];
}
```

**Why it is wrong**
This visits indexes `0` to `3` and **silently skips the last element**. The output would be `265` instead of `354`.

**Why this is the more dangerous mistake**
There is no exception, no warning, no red text. The program runs happily and reports a wrong number. You would only notice if you checked the arithmetic by hand.

> [!warning]
> This exact bug is present in [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] in this same folder, where it causes the last array element never to be examined. Silent wrong answers are worse than crashes — always verify your loop bounds against a hand calculation.

**Correct code**

```java
for (int i = 0; i < arr.length; i++) {
```

**How to recognise it**
Only two forms are correct: `i < arr.length` **or** `i <= arr.length - 1`. If you see `<` with `-1`, or `<=` without `-1`, something is wrong.

---

## Mistake 4 — forgetting to initialise the accumulator

**Incorrect code**

```java
int sum;
for (int i = 0; i < arr.length; i++) {
    sum = sum + arr[i];       // error: variable sum might not have been initialized
}
```

**Why it is wrong**
The very first pass reads `sum` before anything has been written into it. Java refuses to compile code that might read an uninitialised local variable.

**What Java expects**
Local variables have **no** default value — unlike fields, which default to `0`, `false` or `null`. You must assign before reading.

**Correct code**

```java
int sum = 0;
```

**How to recognise it**
`variable X might not have been initialized` always means: give it a starting value.

---

## Mistake 5 — using `length()` on an array

**Incorrect code**

```java
for (int i = 0; i < arr.length(); i++) {     // error: cannot find symbol
```

**Why it is wrong**
Arrays expose `length` as a **field**, not a method.

**Correct code**

```java
for (int i = 0; i < arr.length; i++) {
```

**How to recognise it**
`cannot find symbol: method length()` on an array → drop the parentheses. `cannot find symbol: variable length` on a `String` → add them.

---

## Mistake 6 — writing `sum = arr[i]` instead of `sum = sum + arr[i]`

**Incorrect code**

```java
sum = arr[i];
```

**Why it is wrong**
This *replaces* the total instead of adding to it. After the loop, `sum` holds only the last element, `89`.

**What Java expects**
Accumulation must reference the accumulator's own previous value.

**Correct code**

```java
sum = sum + arr[i];      // or  sum += arr[i];
```

**How to recognise it**
If your "total" equals the last element of the array, you forgot to include the old total.

---

## Mistake 7 — a semicolon after the `for` header

**Incorrect code**

```java
for (int i = 0; i < arr.length; i++);
{
    sum = sum + arr[i];
}
```

**Why it is wrong**
The `;` becomes the loop body. The loop runs five times doing nothing, and the block below is not part of the loop at all.

**Correct code**

```java
for (int i = 0; i < arr.length; i++) {
    sum = sum + arr[i];
}
```

**How to recognise it**
If a loop's body appears not to run, check for a stray semicolon on the header line. IntelliJ warns about this with a highlighted empty statement.

---

## Mistake 8 — modifying the loop counter inside the body

**Incorrect code**

```java
for (int i = 0; i < arr.length; i++) {
    sum = sum + arr[i];
    i++;                    // ← skips every other element
}
```

**Why it is wrong**
`i` now advances twice per pass, so only elements `0` and `2` and `4` are visited.

**Correct code**
Let the `for` header own the counter. Do not touch `i` in the body.

**How to recognise it**
If roughly half your data seems to be ignored, look for an extra `i++`.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| Array fundamentals | do you know arrays are zero-indexed with a fixed `length`? |
| Loop control | can you write correct bounds without an off-by-one? |
| The accumulator pattern | do you know why `sum` starts at `0` and lives outside the loop? |
| Scope | can you explain why `sum` must be declared before the loop? |
| `length` vs `length()` | small detail, instantly reveals experience level |
| Overflow awareness | do you consider what happens with large values? |

## Likely follow-up questions

> **"What is the time complexity?"**

`O(n)` — the loop visits each of the `n` elements exactly once. Explain *why*, not just the symbol.

> **"What is the space complexity?"**

`O(1)` extra space. You allocate `sum` and `i` regardless of the array's size. The array itself is input, not extra space.

> **"What happens with an empty array?"**

The loop body never executes and `sum` stays `0`. That is the mathematically correct answer — the sum of nothing is zero. It is also a good demonstration that initialising to the identity value handles the empty case for free.

> **"What if the sum exceeds `Integer.MAX_VALUE`?"**

It overflows silently and wraps to a negative number. Fix by declaring `long sum = 0;`. Volunteering this shows genuine care.

> **"Can you do it without a loop?"**

`Arrays.stream(arr).sum()`, or recursion. Show the loop first, then mention these.

> **"Now compute the average."**

Watch for the trap: `sum / arr.length` is **integer division** and truncates. `354 / 5` gives `70`, not `70.8`. The fix is `(double) sum / arr.length`.

> **"Sum only the even numbers."**

Add a condition inside the loop: `if (arr[i] % 2 == 0) sum += arr[i];`. This tests whether you can extend the pattern rather than just reproduce it.

> **"What is the difference between the indexed `for` and the enhanced `for`?"**

The enhanced form gives you elements, not indexes; it cannot go out of bounds; it cannot write back to the array. Use it whenever you do not need the index.

---

# 14. Complexity

## Time complexity: `O(n)`

Let `n` be the number of elements in the array.

The loop body runs once per element. Each run does a fixed amount of work: read `arr[i]`, add, store, increment, compare. So total work is proportional to `n`.

In beginner language:

> "The loop visits each element exactly once. With 5 elements it does 5 additions; with 5,000 elements it does 5,000. Double the array and you double the work. Because the work grows in direct proportion to the number of elements, we call it linear time and write it `O(n)`."

> [!note] Why we say `O(n)` and not `O(5n)` or `O(n + 3)`
> Big-O describes the **shape of the growth**, not the exact count. The constant multiplier and any fixed setup cost do not change whether the graph is a straight line.
>
> Five operations per element or fifty — both are straight lines, both are `O(n)`. What would change the classification is a *nested* loop, which makes the work grow with `n × n` — that is `O(n²)`, and you can see it in [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]].

**Can it be faster?** No. To add every number you must at minimum look at every number. `O(n)` is the theoretical floor for this problem.

## Space complexity: `O(1)`

Extra memory used, beyond the input:

- `sum` — one `int`, 32 bits
- `i` — one `int`, 32 bits

That is **two variables, regardless of whether the array holds 5 elements or 5 million**.

> "The extra memory we need does not grow with the input, so it is constant: `O(1)`."

The array itself is `O(n)`, but it was given to us as input — we did not allocate it as working space. When quoting space complexity, we normally mean *auxiliary* space: what the algorithm needs on top of its input.

---

# 15. Edge cases

## Empty array

```java
int[] arr = {};        // length 0
```

- The condition `0 <= -1` is `false` on the very first check.
- The body never runs.
- `sum` remains `0`.
- Output: `Sum of the array element: 0`

**Correct.** The sum of no numbers is zero. Notice that this works *for free* because we initialised to the identity value.

> [!tip]
> Compare with finding a **maximum**, where the empty case is genuinely broken — `int max = arr[0];` throws `ArrayIndexOutOfBoundsException` on an empty array, because there is no neutral value for "largest". See [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian|LargestArrayElement]].
>
> Sum handles empty gracefully; max does not. That difference comes entirely from whether an identity value exists.

## Single element

```java
int[] arr = {42};
```

One pass: `sum = 0 + 42 = 42`. Correct.

## All zeros

```java
int[] arr = {0, 0, 0};
```

`sum` stays `0`. Correct.

## Negative numbers

```java
int[] arr = {43, -53, 90};
```

`sum = 0 + 43 - 53 + 90 = 80`. Works perfectly — `+` handles negatives without any special case.

## Sum exceeding `int` range

```java
int[] arr = {2000000000, 2000000000};
```

The true sum is `4,000,000,000`, but `int` maxes out at `2,147,483,647`.

```text
sum = 0 + 2000000000            = 2000000000
sum = 2000000000 + 2000000000   = -294967296     ← wrapped around!
```

> [!warning] Silent integer overflow
> Java does **not** warn you. There is no exception, no message. The value simply wraps around from the maximum to the minimum, like an odometer rolling over.
>
> Output would be `Sum of the array element: -294967296` — obviously wrong, but only if you happen to notice.
>
> **The fix:**
> ```java
> long sum = 0;      // 64 bits, up to about 9.2 quintillion
> ```
> `long` can hold `int` values without any cast, and `long + int` produces a `long` automatically.
>
> For this program's data (`43, 53, 90, 79, 89` summing to `354`) overflow is impossible, so `int` is perfectly fine here. But being *aware* of the limit is what an interviewer is listening for.

## `null` array

```java
int[] arr = null;
```

`arr.length` throws `NullPointerException` immediately, before the loop starts. The program has no `null` check.

A defensive version:

```java
if (arr == null || arr.length == 0) {
    System.out.println("Sum of the array element: 0");
    return;
}
```

Not needed here — `arr` is initialised on the line it is declared and can never be `null`. Worth knowing for code that receives arrays from elsewhere.

## Very large array

```java
int[] arr = new int[10_000_000];
```

Works fine, just takes longer — 10 million additions. Memory for the array is about 40 MB. The `O(1)` extra space claim still holds: still just `sum` and `i`.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Add up all the numbers in a list and print the total.

        ↓

What data do I have?
Five whole numbers that belong together.

        ↓

Can one variable hold five values?
No. int holds exactly one.

        ↓

What holds many values of the same type?
An array.

        ↓

What type of elements?
Whole numbers → int
Therefore the array type is int[]

        ↓

How do I create it with known values?
int[] arr = {43, 53, 90, 79, 89};

        ↓

What do I need to produce?
One number: the total.

        ↓

Many in, one out — this is a reduction.
Every reduction needs:
  a place for the running answer,
  a starting value, and
  a per-item rule.

        ↓

Where does the running answer live?
A variable → int sum
Type must match what I am adding → int

        ↓

What starting value leaves the answer unchanged?
Adding zero changes nothing → sum = 0

        ↓

Where must I declare it?
BEFORE the loop, because:
  it must survive every iteration, and
  I must read it after the loop ends.

        ↓

How do I reach every element?
Loop over the indexes.

        ↓

What are the valid indexes?
Arrays are zero-indexed → 0 up to length-1
With 5 elements → 0,1,2,3,4

        ↓

Write the loop header:
for (int i = 0; i <= arr.length-1; i++)
(equivalently, and more idiomatically:
 for (int i = 0; i < arr.length; i++))

        ↓

How do I say "the element I am currently on"?
arr[i]

        ↓

What is the rule for one element?
"Add it into the total"
sum = sum + arr[i];

        ↓

Verify the assignment is not circular:
right side evaluated first (old sum + element),
then stored into sum. Fine.

        ↓

Trace it to be sure:
0 → 43 → 96 → 186 → 265 → 354  ✓

        ↓

What do I do with the answer?
Print it with a label:
System.out.println("Sum of the array element: " + sum);
```

> [!important] What to take away
> Every decision in this program came from a **question**, not from memory:
>
> - "How many values?" → array
> - "What kind of values?" → `int[]`
> - "Many in, one out?" → accumulator
> - "What starting value is neutral?" → `0`
> - "Must it survive the loop?" → declare it outside
> - "Where do indexes start?" → `0`
>
> Learn the questions. The code is just the answers written down.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int[] arr = new int[]{43,53,90,79,89};` | "Create a row of five boxes, each able to hold a whole number, fill them with 43, 53, 90, 79 and 89, and let `arr` point at that row." |
| `int sum = 0;` | "Make a whole-number box called `sum` and start it at zero, because adding zero to anything changes nothing." |
| `for(int i = 0; i<= arr.length-1; i++)` | "Make a counter `i` starting at 0. As long as `i` is still a valid position in the row — that is, no bigger than one less than the number of boxes — do the body, then add 1 to `i`." |
| `sum = sum + arr[i];` | "Take whatever is currently in `sum`, add to it the number in box number `i`, and put the answer back into `sum`." |
| `System.out.println("Sum of the array element: "+sum);` | "Take the text `'Sum of the array element: '`, stick the number in `sum` onto the end of it, and print the whole line." |

The loop in one sentence:

> **"Walk from the first box to the last box, and each time you arrive at a box, add its number into the running total."**

And the whole program:

> **"Put five numbers in a row, start a running total at zero, walk along the row adding each number into the total, then print the total."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| array | fixed-size, ordered, same-type collection stored contiguously |
| `int[]` | "array of `int`" — the `[]` belongs to the type |
| zero-indexed | first element is at `0`, last at `length - 1` |
| reference | an array variable holds an address, not the data |
| `new` | allocates an object on the heap and returns its reference |
| accumulator | a variable declared before a loop that builds up an answer |
| identity value | the starting value that does not distort the result (`0` for sum, `1` for product) |
| scope | a variable lives only inside the `{ }` where it was declared |
| off-by-one | the family of bugs caused by wrong loop bounds |
| overflow | `int` silently wraps past ±2.1 billion |

### Important methods

| Member | Owner | Form | Returns |
|---|---|---|---|
| `length` | array | **field**, no parentheses | `int` |
| `length()` | `String` | **method** | `int` |
| `size()` | `List` | method | `int` |
| `println(String)` | `PrintStream` | method | `void` |
| `Arrays.stream(int[]).sum()` | `java.util.Arrays` | static utility | `int` |

### Important syntax

| Syntax | Meaning |
|---|---|
| `int[] arr` | declares a reference to an array of `int` |
| `new int[]{...}` | creates an array with the listed contents |
| `{...}` after `=` | array initialiser (data) |
| `{...}` after `for`/`if` | block of statements (instructions) |
| `arr[i]` | the element at position `i` |
| `arr.length` | the number of elements — no parentheses |
| `i++` | add one to `i` |
| `sum += x` | shorthand for `sum = sum + x` |
| `for (a; b; c)` | initialise; test before each pass; update after each pass |
| `for (int x : arr)` | enhanced for — "for each element `x` in `arr`" |
| `+` | addition between numbers, concatenation when a `String` is involved |

### Main interview concept

> **The accumulator pattern.** Declare a running answer *before* the loop, start it at the value that leaves the result unchanged, update it from its own previous value inside the loop, and read it after. Sum, product, count, maximum and string-building are all the same skeleton with different fillings.

### Main lesson for code reading

> When you meet a loop, do not read it line by line — **classify its variables by role first**:
>
> ```text
> arr  → the DATA        (what we walk over)
> sum  → the ACCUMULATOR (what we build up)
> i    → the COUNTER     (where we currently are)
> ```
>
> Then ask three questions: *What is the accumulator's starting value? How does each item change it? What is read after the loop?* Answer those and you understand the loop completely, whatever the details of its body.

---

### Related notes

- [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]] — `String[]` and `.length` on an array
- [[Java_Factorials_Code_Reading_Explanation_Obsidian|Factorials]] — the same accumulator skeleton, starting at `1` and multiplying
- [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian|LargestArrayElement]] — accumulator seeded with `arr[0]` instead of an identity value
- [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] — the same loop shape, with the off-by-one bug this note warns about
- [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]] — value semantics versus reference semantics
