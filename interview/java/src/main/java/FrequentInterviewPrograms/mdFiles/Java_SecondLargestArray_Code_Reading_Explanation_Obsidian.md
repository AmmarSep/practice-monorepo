---
title: Java Code Reading — SecondLargestArray
tags:
  - java
  - interview-programs
  - code-reading
  - arrays
  - loops
aliases:
  - SecondLargestArray Explained
  - Second Largest Element in Array
---

# Java Code Reading — SecondLargestArray Explained for a Fresh Java Programmer

> [!note]
> This is the hardest of the array programs, and the most rewarding. It tracks **two** running values at once, and the order in which they are updated is critical — get it wrong and the answer is silently incorrect.
>
> It also contains a genuine **seeding bug**. The program produces the right answer for its own data but fails on a descending array. We will explain the code exactly as written, prove the bug by tracing a counter-example, and then fix it.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → How many running values do I need? → What is each one's seed? → In what ORDER must they be updated?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class SecondLargestArray
{
    public static void main(String[] args)
    {
        int arr[] = {14, 46, 47, 86, 92, 52, 48, 36, 66, 85};

        int largest = arr[0];

        int secondLargest = arr[0];

        System.out.println("The given array is:");

        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + "\t");
        }

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] > largest)
            {
                secondLargest = largest;
                largest = arr[i];

            } 
            else if (arr[i] > secondLargest)
            {
                secondLargest = arr[i];
            }
        }

        System.out.println("\nSecond largest number is:" + secondLargest);
    }
}
```

The executable logic:

```java
int arr[] = {14, 46, 47, 86, 92, 52, 48, 36, 66, 85};
int largest = arr[0];
int secondLargest = arr[0];

for (int i = 0; i < arr.length; i++) {
    if (arr[i] > largest) {
        secondLargest = largest;      // demote before promoting!
        largest = arr[i];
    } else if (arr[i] > secondLargest) {
        secondLargest = arr[i];
    }
}
```

> [!warning] Read this before you trust the program
> Both `largest` and `secondLargest` are seeded with `arr[0]` — **the same element**.
>
> For this particular array that happens to work, because `arr[0]` is `14`, the *smallest* value. But if the array were sorted in descending order, the answer would be wrong. We prove this in section 15 and fix it in section 11.
>
> The program is correct for its own data and incorrect in general. That is a very common and very dangerous category of bug.

---

# 2. What problem is this program solving?

In plain language:

> I have a list of numbers. Tell me the second biggest one.

### What do we know?

- Ten whole numbers, unsorted.
- "Second largest" means: sort them mentally, and take the one just below the top.

### What do we need?

- **One** number: the runner-up.

### What transformations are required?

Many values in, one value out — a reduction, like [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian|LargestArrayElement]]. But there is a twist that makes this genuinely harder.

> [!important] Why you cannot find the second largest by tracking only one value
> Suppose you tracked only `secondLargest` and updated it whenever you saw something bigger. You would just find the largest.
>
> The problem: **you cannot know a number is "second" until you know what is "first".** The two facts are entangled.
>
> Think of a race. You cannot identify the silver medallist by watching only for silver — you have to watch the gold position too. When someone overtakes the leader, the *old leader* becomes the runner-up.
>
> That is the whole algorithm:
>
> ```text
> A new leader appears
>       ↓
> The old leader is demoted to second place
>       ↓
> The newcomer becomes the leader
> ```
>
> Therefore we need **two** running values, and they must be updated **in the right order**.

### The two cases

Walking through the numbers one at a time, each new value falls into exactly one of three situations:

| Situation | Test | What must happen |
|---|---|---|
| bigger than the leader | `arr[i] > largest` | old leader demoted to second; newcomer becomes leader |
| between second and leader | `arr[i] > secondLargest` | newcomer becomes second; leader unchanged |
| smaller than both | neither | nothing changes |

Those three rows map directly onto `if` / `else if` / (implicit) nothing.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder. First statement in the file.

## `public class SecondLargestArray`

- `public` — usable from anywhere.
- `class` — declares a class.
- `SecondLargestArray` — `PascalCase`, matching the file name.

## `public static void main(String[] args)`

The JVM entry point.

| Part | Meaning | Why required |
|---|---|---|
| `public` | reachable from outside | the JVM is outside |
| `static` | belongs to the class | no object exists at startup |
| `void` | returns nothing | the JVM has nowhere to put a result |
| `main` | the name the JVM looks for | fixed by the launcher |
| `String[] args` | command-line arguments | text typed after the program name |

## No imports

`int`, `int[]`, `String`, `System` — primitives, arrays and `java.lang` only.

## Variables

| Variable | Type | Role |
|---|---|---|
| `arr` | `int[]` | the data |
| `largest` | `int` | running maximum |
| `secondLargest` | `int` | running runner-up |
| `i` (twice) | `int` | counter, one per loop |

**Two accumulators** rather than one. That is what makes this program a step up in difficulty.

## Structure

There are two separate loops:

1. The first **prints** the array — pure display, no computation.
2. The second **computes** the answer.

> [!tip] Separating display from computation
> Merging these into one loop would work and be marginally faster. Keeping them separate is arguably clearer: one loop has one job.
>
> This is a real design tension you will meet constantly. **A loop that does two unrelated things is harder to read and harder to change than two loops that each do one thing.** Efficiency is not the only measure of good code.

---

# 4. Line-by-line explanation

## 4.1 — `int arr[] = {14, 46, 47, 86, 92, 52, 48, 36, 66, 85};`

```java
int arr[] = {14, 46, 47, 86, 92, 52, 48, 36, 66, 85};
```

**What is this?**
Creating an array of ten integers.

**Why do we need it?**
Several values of the same type → an array.

**Note the bracket placement**

```java
int arr[] = {...};      // C-style: brackets after the NAME  ← used here
int[] arr = {...};      // Java style: brackets after the TYPE  ← preferred
```

Both are legal and produce an identical array. Java style attaches `[]` to the **type**, because "array of int" is what `arr` *is* — the `[]` describes the type, not the name.

> [!note]
> `int arr[]` is inherited from C and still compiles for compatibility. Modern Java style guides all prefer `int[] arr`, and IntelliJ will offer to convert it. Recognise both forms when reading; write the second.
>
> The other programs in this folder use `int[] arr` — this file is the exception.

**Why the shorthand `{...}` with no `new int[]`?**

```java
int[] arr = {14, 46};                  // shorthand — only valid at declaration
int[] arr = new int[]{14, 46};         // long form — valid anywhere
```

The shorthand works only when declaring. To pass an array literal to a method you need the long form:

```java
someMethod(new int[]{1, 2, 3});        // shorthand would not compile here
```

**What does Java do?**
Allocates ten `int` slots on the heap, fills them, records the length, and stores the address in `arr`.

```text
  arr
 ┌─────┐
 │ ref │
 └──┬──┘
    ▼
┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
│ 14 │ 46 │ 47 │ 86 │ 92 │ 52 │ 48 │ 36 │ 66 │ 85 │
└────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
  0    1    2    3    4    5    6    7    8    9
  ▲                   ▲
smallest            largest
```

**Worth noticing about this data**

`arr[0]` is `14` — the **smallest** value in the array. That is a lucky accident which hides the seeding bug. If `arr[0]` were the largest, the program would fail. See section 15.

---

## 4.2 — `int largest = arr[0];`

```java
int largest = arr[0];
```

**What is this?**
The first accumulator, seeded from the data.

**Why seed from `arr[0]` rather than `0`?**
Because there is no "smaller than everything" value. Seeding with `0` would break on all-negative arrays, reporting a number that is not in the data. Full reasoning in [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian|LargestArrayElement]].

**What type?**
`arr[0]` is an `int`, so `largest` is an `int`. The holder's type matches what it holds.

**What does Java do?**
Copies `14` into a new `int` slot named `largest`.

---

## 4.3 — `int secondLargest = arr[0];`

```java
int secondLargest = arr[0];
```

**What is this?**
The second accumulator — and the source of the bug.

**Why do we need a second variable?**
Because, as section 2 argued, you cannot identify the runner-up without simultaneously tracking the leader.

**Why is seeding it with `arr[0]` a problem?**

Think about what the seed *claims*. Writing `secondLargest = arr[0]` asserts:

> "The best runner-up I have found so far is `arr[0]`."

But `largest` is *also* `arr[0]`. **The same single element is being claimed as both first and second place.** A second-largest value requires at least two distinct elements to exist, and after looking at one element it simply does not exist yet.

For this array it happens not to matter, because `arr[0]` is the smallest value — so almost everything overtakes it. But consider:

```java
int[] arr = {92, 86, 47};       // descending

largest = 92
secondLargest = 92              // ← claims 92 is also the runner-up

i=0: 92 > 92 ? no.  92 > 92 ? no.   nothing
i=1: 86 > 92 ? no.  86 > 92 ? no.   nothing   ← 86 is silently ignored!
i=2: 47 > 92 ? no.  47 > 92 ? no.   nothing

Answer: 92     ✗ should be 86
```

> [!warning] The bug, stated precisely
> Because `secondLargest` starts at the maximum possible starting value (whatever `arr[0]` is), any element **smaller than `arr[0]`** fails both tests and is ignored entirely.
>
> When `arr[0]` happens to be small — as here — almost nothing is missed. When `arr[0]` is the largest element, **everything** is missed and the program reports the largest value twice.
>
> The correct seed is discussed in section 11. The short version: `secondLargest` should start at `Integer.MIN_VALUE`, or the algorithm should seed from the first *two* elements.

**Why the name `secondLargest`?**
Excellent — it states exactly what the variable means. Long names are fine when they earn their length.

---

## 4.4 — `System.out.println("The given array is:");`

```java
System.out.println("The given array is:");
```

A header, printed once, outside any loop. Without it the numbers would appear with no explanation.

---

## 4.5 — the display loop

```java
for (int i = 0; i < arr.length; i++)
{
    System.out.print(arr[i] + "\t");
}
```

**What is this?**
A loop that prints every element on one line, separated by tabs.

**Why `print` and not `println`?**

| Method | Behaviour |
|---|---|
| `println(x)` | prints `x`, then moves to a **new line** |
| `print(x)` | prints `x`, **stays on the same line** |

Using `println` here would put each number on its own line. `print` keeps them together as a row.

> [!important] This is a real decision, not a typo
> ```text
> with print:                with println:
> 14  46  47  86  92         14
>                            46
>                            47
> ```
> **The method you choose controls the shape of the output.** Whenever output looks wrong, check whether you used `print` or `println`.

**What is `"\t"`?**

An **escape sequence** — a two-character source spelling for a single character that you cannot type directly.

| Escape | Character | Purpose |
|---|---|---|
| `\t` | tab | aligns columns |
| `\n` | newline | moves to the next line |
| `\\` | backslash | a literal `\` |
| `\"` | double quote | a `"` inside a string |
| `\'` | single quote | a `'` inside a char |

The backslash means *"the next character has a special meaning"*. Without it, `"\t"` would be the two literal characters `\` and `t`.

**What is `arr[i] + "\t"`?**

String concatenation. `arr[i]` is an `int`, `"\t"` is a `String`, so `+` concatenates and the number is converted to text.

```text
14 + "\t"   →   "14\t"   →   prints as   14<tab>
```

**Why concatenate instead of two `print` calls?**

```java
System.out.print(arr[i] + "\t");     // one call
System.out.print(arr[i]);            // equivalent, two calls
System.out.print("\t");
```

Both work. One call is more concise.

**Output of this loop**

```text
14	46	47	86	92	52	48	36	66	85
```

Note there is **no newline at the end** — the cursor is still on that line. That is why the final print statement begins with `\n`.

---

## 4.6 — the computation loop header

```java
for (int i = 0; i < arr.length; i++)
```

The same structure as the display loop, and a **new, separate** `i` — the first one died with its loop.

**Why `i < arr.length`?**
`arr.length` is `10`, so this visits indexes `0..9` — every element. This is the correct, idiomatic form.

**Why start at `0` when both accumulators are already seeded from index `0`?**

The first pass compares `arr[0]` against itself twice, both failing. Harmless but redundant. Starting at `i = 1` would skip it — see section 11.

---

## 4.7 — `if (arr[i] > largest)`

```java
if (arr[i] > largest)
{
    secondLargest = largest;
    largest = arr[i];
}
```

**What is this?**
The "a new leader has appeared" case.

**Why do we need it?**
When we find something bigger than the current champion, two things change: the newcomer becomes champion, and **the old champion becomes the runner-up**.

That second half is the insight that makes this algorithm work. The old leader does not vanish — it is exactly the value that is now second-best.

> [!important] The order of these two lines is critical
> ```java
> secondLargest = largest;      // demote FIRST
> largest = arr[i];             // then promote
> ```
>
> Reverse them and the program breaks:
>
> ```java
> largest = arr[i];             // ✗ overwrites the old leader
> secondLargest = largest;      // ✗ now copies the NEW leader
> // result: largest == secondLargest, always
> ```
>
> Trace the wrong order with `largest = 47` and `arr[i] = 86`:
>
> ```text
> WRONG ORDER                        CORRECT ORDER
> largest = 86                       secondLargest = 47   (old leader saved)
> secondLargest = 86   ← the 47      largest = 86
>                        is gone
> both are 86  ✗                     largest=86, second=47  ✓
> ```
>
> This is **exactly the same principle as [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]]**: *assignment overwrites, so save a value before you destroy it.* There the rescue was a `temp` variable; here `secondLargest` plays that role. Same lesson, different clothes.

**What does Java do when the condition is true?**
Copies the current `largest` into `secondLargest`, then copies `arr[i]` into `largest`.

---

## 4.8 — `else if (arr[i] > secondLargest)`

```java
else if (arr[i] > secondLargest)
{
    secondLargest = arr[i];
}
```

**What is this?**
The "not a new leader, but better than the current runner-up" case.

**Why `else if` and not a plain `if`?**

This matters enormously.

> [!warning] Using a second plain `if` would corrupt the answer
> ```java
> if (arr[i] > largest) {
>     secondLargest = largest;
>     largest = arr[i];
> }
> if (arr[i] > secondLargest) {      // ✗ plain if, not else if
>     secondLargest = arr[i];
> }
> ```
>
> Trace with `largest = 47`, `secondLargest = 46`, `arr[i] = 86`:
>
> ```text
> First if:  86 > 47  → true
>            secondLargest = 47
>            largest = 86
>
> Second if: 86 > 47  → TRUE (86 is still bigger than the new second!)
>            secondLargest = 86        ✗
>
> Result: largest = 86, secondLargest = 86
> ```
>
> The same element got counted as both first and second place.
>
> **`else if` means "only consider this if the previous test failed".** It guarantees each element is handled by exactly one branch. That mutual exclusivity is precisely what we need here.

**What is the structure?**

```text
if (condition A) {
    // A is true
} else if (condition B) {
    // A is false AND B is true
}
// implicitly: if both are false, nothing happens
```

There is no final `else`, and none is needed. "Do nothing" is the correct action for a number smaller than both.

---

## 4.9 — `System.out.println("\nSecond largest number is:" + secondLargest);`

```java
System.out.println("\nSecond largest number is:" + secondLargest);
```

**What is `\n` doing at the start?**

The display loop used `print`, which left the cursor at the end of the number row. Without `\n`, the output would run together:

```text
14	46	47	86	92	52	48	36	66	85Second largest number is:86
```

The leading `\n` forces a line break first:

```text
14	46	47	86	92	52	48	36	66	85
Second largest number is:86
```

> [!tip]
> This is a small but real illustration of how output formatting works: `print` leaves the cursor where it is, so **the next thing printed is responsible for starting a new line if it needs one**. Mixing `print` and `println` requires you to keep track of where the cursor sits.

**What does `+` do here?**
The left operand is a `String`, so `+` concatenates and `secondLargest` is converted to text.

**Output**

```text
Second largest number is:86
```

---

# 5. How to think like the programmer

```text
Requirement
"Find the second largest number in a list"
        ↓
Can I track just one running value?
No — I cannot know something is SECOND
until I know what is FIRST.
        ↓
So I need TWO running values:
largest and secondLargest
        ↓
What are their seeds?
For largest: no identity exists → arr[0]
For secondLargest: also no identity...
and it must be SMALLER than largest.
arr[0] for both is wrong — they are the same element.
(The program makes this mistake.)
Correct: Integer.MIN_VALUE, or seed from
the first TWO elements.
        ↓
What are the cases for each new element?
1. bigger than largest
2. between secondLargest and largest
3. smaller than both
        ↓
Case 1 — what must happen?
The old leader is now the runner-up.
So: demote, THEN promote.
   secondLargest = largest;
   largest = arr[i];
Order matters — assignment overwrites.
        ↓
Case 2 — what must happen?
Just replace the runner-up.
   secondLargest = arr[i];
        ↓
Case 3 — what must happen?
Nothing.
        ↓
How do I make the cases mutually exclusive?
if / else if — NOT two separate ifs,
or an element could satisfy both.
        ↓
Trace to verify with the real data:
largest 14→46→47→86→92
second      14→46→47→86
Answer: 86   ✓
        ↓
Now test an awkward case:
descending array {92, 86, 47} → reports 92  ✗
The seeding bug is real.
```

> [!important] Two lessons, both transferable
> **1. Order of assignment matters when values depend on each other.**
> `secondLargest = largest;` must precede `largest = arr[i];`, for exactly the reason a `temp` variable is needed in a swap. Overwriting destroys.
>
> **2. `else if` enforces mutual exclusivity.**
> When cases must not overlap, chained `else if` guarantees it structurally. Two independent `if`s do not.
>
> Both mistakes compile cleanly and produce plausible wrong answers. Only tracing catches them.

---

# 6. Deep explanation of important Java concepts used

## Two accumulators

Most loops carry one running value. This one carries two, and they are **coupled** — updating one requires updating the other.

```text
     largest         secondLargest
        │                  │
        └──── when a new leader arrives,
              the old leader flows down here
```

Whenever accumulators are coupled, **update order becomes part of the algorithm**, not an implementation detail.

## `if` / `else if` chains

```java
if (A) {
    // A true
} else if (B) {
    // A false, B true
} else if (C) {
    // A and B false, C true
} else {
    // none true
}
```

Java evaluates conditions **top to bottom and stops at the first true one**. At most one branch ever runs.

| Structure | Branches that can run | Use when |
|---|---|---|
| `if` / `if` / `if` | zero to three | the cases are independent |
| `if` / `else if` / `else if` | exactly zero or one | the cases are mutually exclusive |

Here the cases are mutually exclusive, so `else if` is required for correctness — not merely for style.

## `print` vs `println`

| Method | Prints | Then |
|---|---|---|
| `print(x)` | `x` | stays on the same line |
| `println(x)` | `x` | moves to a new line |
| `println()` | nothing | moves to a new line |

Both live on `PrintStream` and both return `void`.

## Escape sequences

Some characters cannot be typed literally inside a string. The backslash introduces them:

| Escape | Meaning |
|---|---|
| `\n` | newline |
| `\t` | tab |
| `\\` | a literal backslash |
| `\"` | a double quote inside a string |
| `\'` | a single quote inside a char |
| `é` | a Unicode character by code |

```java
System.out.println("a\tb");     // a<tab>b     — 3 characters printed
System.out.println("a\\tb");    // a\tb        — the backslash is literal
```

Note that `"\t"` is **one** character in the resulting string, even though it takes two characters to write.

## Array declaration styles

```java
int[] arr;      // Java style — preferred
int arr[];      // C style — legal, discouraged
```

Both declare "an array of `int`". The first keeps all type information together on the left.

## Relational operators on primitives

`>`, `<`, `>=`, `<=`, `==`, `!=` all take two numbers and produce a `boolean`. They do **not** work on objects — comparing `String`s requires `compareTo`, as in [[Java_EmplSort_Code_Reading_Explanation_Obsidian|EmplSort]].

## Scope

```java
int largest = arr[0];                    // scope: rest of main
int secondLargest = arr[0];              // scope: rest of main

for (int i = 0; i < arr.length; i++) { } // first i
for (int i = 0; i < arr.length; i++) { } // second, unrelated i
```

Both accumulators are declared before the loops because they must survive every pass and be read afterwards. Each `i` is confined to its own loop, which is why the name can be reused.

---

# 7. Why this syntax?

## `else if` vs two `if`s

The single most important syntax decision in this program.

```java
if (A) { ... }
else if (B) { ... }        // B is checked ONLY when A was false

if (A) { ... }
if (B) { ... }             // B is ALWAYS checked, even after A ran
```

With coupled accumulators the second form corrupts the state, because the first branch changes the very values the second branch tests.

## `>` vs `>=`

Using `>=` in the first test would break the algorithm on duplicates:

```java
int[] arr = {92, 92, 47};

with >  :  92 > 92 false → 92 not treated as a new leader
           secondLargest never becomes 92     (arguably correct)

with >= :  92 >= 92 true → secondLargest = 92, largest = 92
           both are 92                        (arguably wrong)
```

Whether duplicates *should* count as a distinct second place is a **requirements** question, not a syntax one:

- "second largest **value**" — `{92, 92, 47}` → `47` (distinct values)
- "second largest **element**" — `{92, 92, 47}` → `92` (positions)

The program implements neither definition reliably, because of the seeding bug. Ask an interviewer which they mean — noticing the ambiguity is itself a good signal.

## `\t` vs a space

```java
System.out.print(arr[i] + "\t");     // tab — aligns into columns
System.out.print(arr[i] + " ");      // space — variable width
```

Tabs align to fixed stops, keeping numbers of different widths in tidy columns.

## `\n` vs `println()`

```java
System.out.println("\nSecond largest...");     // used here
System.out.println();                          // equivalent alternative
System.out.println("Second largest...");
```

Both produce the same output. The `\n` form is more compact; the two-statement form is arguably clearer about intent.

## `int arr[]` vs `int[] arr`

Identical meaning. `int[] arr` is the preferred Java style because `[]` describes the type.

## `{ }` — two jobs

| Where | Meaning |
|---|---|
| after `=` in a declaration | array initialiser — the data |
| after `for`/`if`/`else`/method/class | block — a group of statements |

---

# 8. Method discovery

## `print` and `println`

| Question | `print` | `println` |
|---|---|---|
| **Owner** | `java.io.PrintStream` | `java.io.PrintStream` |
| **Accepts** | `String`, `int`, `char`, `double`, `Object`... | the same overloads |
| **Returns** | `void` | `void` |
| **Difference** | leaves the cursor in place | appends a line separator |

## How you would discover `print` in IntelliJ

Type:

```java
System.out.
```

The list includes:

```text
print()      println()      printf()      format()
flush()      write()        append()      checkError()
```

Hovering over `print` shows it does **not** add a newline. That is exactly how you would discover the tool you need for row-style output — by reading the neighbouring methods rather than reflexively reaching for `println`.

> [!tip] `printf` is worth knowing about
> ```java
> System.out.printf("%5d", arr[i]);      // right-aligned in 5 columns
> ```
> `printf` gives precise control over width, alignment and decimal places. For aligned numeric output it is better than tabs, because tab stops depend on the terminal.
>
> You would find it by browsing the same autocomplete list. **Reading the neighbours of the method you came for is one of the cheapest ways to learn an API.**

## Is there a built-in "second largest"?

No — and it is worth understanding why.

```text
arr.  →  only length, clone, equals, hashCode, toString
Math. →  max(a, b) takes TWO numbers, not a collection
```

Searching for "java second largest array" leads to:

```java
int second = Arrays.stream(arr).distinct().sorted().skip(...).findFirst()...
```

or more simply:

```java
int[] sorted = arr.clone();      // clone first — do not mutate the caller's array
Arrays.sort(sorted);
int second = sorted[sorted.length - 2];
```

> [!important] Two things to notice in that snippet
> **1. `.clone()` before sorting.** `Arrays.sort` sorts **in place**, permanently rearranging the array you pass it. If the caller still needs the original order, you must copy first. Forgetting this is a classic source of "why did my data change?" bugs.
>
> **2. `length - 2`, not `length - 1`.** After sorting ascending, the largest is at `length - 1` and the runner-up at `length - 2`. Off by one here and you get the wrong answer silently.
>
> The single-pass version in this program avoids both problems — it never mutates the input and never indexes near the end.

---

# 9. Trace the program with real values

```text
Array:  [14, 46, 47, 86, 92, 52, 48, 36, 66, 85]
Index:    0   1   2   3   4   5   6   7   8   9
Length: 10   → condition is  i < 10
Seeds:  largest = arr[0] = 14
        secondLargest = arr[0] = 14
```

## Iteration table

```text
──────────────────────────────────────────────────────────────────────────────
Pass  i  arr[i]  >largest?  >second?  Action                largest  second
──────────────────────────────────────────────────────────────────────────────
 —    —    —         —          —     seed                     14      14
 1    0   14      14>14 no   14>14 no  nothing                  14      14
 2    1   46      46>14 YES      —     second=14, largest=46    46      14
 3    2   47      47>46 YES      —     second=46, largest=47    47      46
 4    3   86      86>47 YES      —     second=47, largest=86    86      47
 5    4   92      92>86 YES      —     second=86, largest=92    92      86
 6    5   52      52>92 no   52>86 no  nothing                  92      86
 7    6   48      48>92 no   48>86 no  nothing                  92      86
 8    7   36      36>92 no   36>86 no  nothing                  92      86
 9    8   66      66>92 no   66>86 no  nothing                  92      86
10    9   85      85>92 no   85>86 no  nothing                  92      86
──────────────────────────────────────────────────────────────────────────────
Final:                                                          92      86
```

## Watch the demotion cascade

The interesting column is how values **flow downward**:

```text
pass 2:   46 arrives → 14 is demoted → second = 14
pass 3:   47 arrives → 46 is demoted → second = 46
pass 4:   86 arrives → 47 is demoted → second = 47
pass 5:   92 arrives → 86 is demoted → second = 86
```

Every time a new leader appears, the previous leader slides into second place. **The runner-up is always a former champion** — that is the algorithm in one sentence.

## The near-miss at pass 10

```text
arr[9] = 85
85 > 92 ?  no   → not a new leader
85 > 86 ?  no   → not better than the runner-up either
→ nothing changes
```

`85` is genuinely the third largest. Correctly ignored.

## Console output

```text
The given array is:
14	46	47	86	92	52	48	36	66	85
Second largest number is:86
```

**Correct for this data.** But read section 15 before trusting the program in general.

---

# 10. Visualize data where useful

## The array and the two answers

```text
index:   0    1    2    3    4    5    6    7    8    9
       ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
  arr ▶│ 14 │ 46 │ 47 │ 86 │ 92 │ 52 │ 48 │ 36 │ 66 │ 85 │
       └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
         ▲                   ▲    ▲
         │                   │    └── largest = 92
       seed                  └─────── secondLargest = 86
```

## The two accumulators over time

```text
i:          0     1     2     3     4     5     6     7     8     9
arr[i]:    14    46    47    86    92    52    48    36    66    85

largest:   14    46    47    86    92    92    92    92    92    92
                 ▲     ▲     ▲     ▲
                 └─────┴─────┴─────┴── each promotion pushes the old value down

second:    14    14    46    47    86    86    86    86    86    86
                       ▲     ▲     ▲
                       └─────┴─────┴── receives the demoted leader
```

Read the two rows together and you can see the cascade: every value in the `second` row is the value that was in the `largest` row one step earlier.

## The demotion, step by step

```text
BEFORE pass 5:        largest ┌────┐    second ┌────┐
                              │ 86 │           │ 47 │
                              └────┘           └────┘
                     arr[4] = 92

STEP 1: secondLargest = largest      (demote — save the old leader)
                              ┌────┐           ┌────┐
                              │ 86 │──────────▶│ 86 │
                              └────┘           └────┘

STEP 2: largest = arr[i]             (promote — install the newcomer)
                              ┌────┐           ┌────┐
                        92 ──▶│ 92 │           │ 86 │
                              └────┘           └────┘

AFTER:                largest = 92,  second = 86     ✓
```

Reverse the two steps and the `86` is destroyed before it can be saved — the same failure mode as swapping without a `temp`.

## The three cases

```text
                    a new element arrives
                            │
              ┌─────────────┼─────────────┐
              │             │             │
       > largest ?    > second ?      neither
              │             │             │
         ┌────┴────┐        │             │
     demote leader │   replace second   do nothing
     promote new   │        │             │
              └────┴────────┴─────────────┘
                        continue
```

`else if` is what guarantees exactly one path is taken.

---

# 11. Alternative ways to write the same logic

### The fix — a correct seed

The minimal change that makes the program correct in general:

```java
int[] numbers = {14, 46, 47, 86, 92, 52, 48, 36, 66, 85};

int largest = Integer.MIN_VALUE;
int secondLargest = Integer.MIN_VALUE;      // ← the real fix

for (int number : numbers) {
    if (number > largest) {
        secondLargest = largest;
        largest = number;
    } else if (number > secondLargest) {
        secondLargest = number;
    }
}

System.out.println("Second largest number is: " + secondLargest);
```

Why `Integer.MIN_VALUE` works: it is `-2147483648`, the smallest possible `int`. Nothing can be below it, so **every** element passes at least one of the two tests. Nothing is silently ignored.

Trace the previously broken case:

```text
{92, 86, 47}
largest = MIN, second = MIN

92 > MIN ? YES → second = MIN, largest = 92
86 > 92 ?  no.  86 > MIN ? YES → second = 86
47 > 92 ?  no.  47 > 86 ?  no  → nothing

Answer: 86     ✓ correct
```

### More robust version — seed from the first two elements

```java
public static int secondLargest(int[] numbers) {
    if (numbers.length < 2) {
        throw new IllegalArgumentException("Need at least 2 elements");
    }

    int largest = Math.max(numbers[0], numbers[1]);
    int secondLargest = Math.min(numbers[0], numbers[1]);

    for (int i = 2; i < numbers.length; i++) {
        if (numbers[i] > largest) {
            secondLargest = largest;
            largest = numbers[i];
        } else if (numbers[i] > secondLargest) {
            secondLargest = numbers[i];
        }
    }
    return secondLargest;
}
```

Why this is arguably the best version:

- **Guarantees a real element.** Unlike `Integer.MIN_VALUE`, the answer is always a value from the array.
- **Fails loudly** on arrays too small to have a second largest, instead of returning a meaningless number.
- **Correct by construction** — after looking at two elements, a first and second genuinely exist.

> [!important] The seeding principle, generalised
> To track the top **k** values, seed from the first **k** elements — never from one element repeated.
>
> ```text
> tracking 1 value  → seed from arr[0]           → needs 1 element
> tracking 2 values → seed from arr[0], arr[1]   → needs 2 elements
> tracking k values → seed from the first k      → needs k elements
> ```
>
> The bug in this program is seeding **two** accumulators from **one** element. That is the whole story, and it generalises.

### Handling distinct values

If duplicates of the maximum should not count as second:

```java
int[] numbers = {92, 92, 86, 47};

int largest = Integer.MIN_VALUE;
int secondLargest = Integer.MIN_VALUE;

for (int number : numbers) {
    if (number > largest) {
        secondLargest = largest;
        largest = number;
    } else if (number > secondLargest && number != largest) {   // ← added guard
        secondLargest = number;
    }
}
// answer: 86, not 92
```

The extra `&& number != largest` implements "second largest **distinct value**". Which behaviour is right depends on the requirement — always ask.

### Sorting — simple but with two traps

```java
import java.util.Arrays;

int[] sorted = numbers.clone();          // do NOT mutate the caller's array
Arrays.sort(sorted);
int secondLargest = sorted[sorted.length - 2];
```

Works, but:

| Issue | Detail |
|---|---|
| slower | `O(n log n)` versus `O(n)` |
| mutates | `Arrays.sort` sorts in place — hence `.clone()` |
| duplicates | `{92, 92, 86}` sorted gives `[86, 92, 92]`, so `length-2` returns `92` |

Interviewers ask about this deliberately. Say it works, then name all three problems.

### Streams

```java
import java.util.Arrays;

int secondLargest = Arrays.stream(numbers)
        .distinct()
        .boxed()
        .sorted((a, b) -> b - a)
        .skip(1)
        .findFirst()
        .orElseThrow();
```

Concise but harder to read, and still `O(n log n)`. Show the single-pass loop first.

### One clean pass with the enhanced `for`

```java
int largest = Integer.MIN_VALUE;
int secondLargest = Integer.MIN_VALUE;

for (int number : numbers) {          // no index needed
    if (number > largest) {
        secondLargest = largest;
        largest = number;
    } else if (number > secondLargest) {
        secondLargest = number;
    }
}
```

The program never uses `i` except as `arr[i]`, so the index is unnecessary — and removing it removes any possibility of an off-by-one error.

---

# 12. Common beginner mistakes

## Mistake 1 — seeding both accumulators from the same element

**Incorrect code**

```java
int largest = arr[0];
int secondLargest = arr[0];      // ← the bug in this program
```

**Why it is wrong**
A single element cannot be both first and second place. Any value smaller than `arr[0]` fails both tests and is ignored entirely. On a descending array, everything is ignored and the program reports the largest twice.

**What Java expects**
Nothing — this compiles fine. It is a logic error.

**Correct code**

```java
int largest = Integer.MIN_VALUE;
int secondLargest = Integer.MIN_VALUE;
```

or seed from the first **two** elements.

**How to recognise it in future**
**Test with a descending array.** `{92, 86, 47}` should give `86`. If it gives `92`, the seed is wrong.

---

## Mistake 2 — assigning in the wrong order

**Incorrect code**

```java
if (arr[i] > largest) {
    largest = arr[i];             // ✗ destroys the old leader
    secondLargest = largest;      // ✗ copies the NEW leader
}
```

**Why it is wrong**
By the time `secondLargest` is assigned, `largest` already holds the new value. The old leader is gone. `largest` and `secondLargest` end up equal.

**Correct code**

```java
if (arr[i] > largest) {
    secondLargest = largest;      // save first
    largest = arr[i];             // then overwrite
}
```

**How to recognise it**
If `secondLargest` always equals `largest`, the assignments are the wrong way round. **Assignment overwrites — save before you destroy.** Same principle as [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]].

---

## Mistake 3 — using two `if`s instead of `if` / `else if`

**Incorrect code**

```java
if (arr[i] > largest) {
    secondLargest = largest;
    largest = arr[i];
}
if (arr[i] > secondLargest) {      // ✗ plain if
    secondLargest = arr[i];
}
```

**Why it is wrong**
After the first branch promotes `arr[i]` to `largest`, the second test compares that same element against the newly demoted `secondLargest` — and often passes. The element is counted twice, so `largest == secondLargest`.

**Correct code**

```java
} else if (arr[i] > secondLargest) {
```

**How to recognise it**
Whenever the branches **modify the values the later branches test**, they must be mutually exclusive. Use `else if`.

---

## Mistake 4 — forgetting the demotion entirely

**Incorrect code**

```java
if (arr[i] > largest) {
    largest = arr[i];             // ← no demotion
} else if (arr[i] > secondLargest) {
    secondLargest = arr[i];
}
```

**Why it is wrong**
For a strictly ascending array `{1, 2, 3, 4, 5}`, every element takes the first branch and `secondLargest` is never updated. It keeps its seed.

**Correct code**
Include `secondLargest = largest;` in the first branch.

**How to recognise it**
Test with an ascending array. If `secondLargest` never changes, the demotion is missing.

---

## Mistake 5 — declaring accumulators inside the loop

**Incorrect code**

```java
for (int i = 0; i < arr.length; i++) {
    int largest = arr[0];
    int secondLargest = arr[0];
    ...
}
System.out.println(secondLargest);   // error: cannot find symbol
```

**Why it is wrong**
Both are reset every pass and do not exist after the loop.

**Correct code**
Declare them before the loop.

---

## Mistake 6 — using `println` in the display loop

**Incorrect code**

```java
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i] + "\t");
}
```

**Why it is wrong**
Each number lands on its own line, so the tabs are pointless and the output is ten lines instead of one.

**Correct code**

```java
System.out.print(arr[i] + "\t");
```

**How to recognise it**
If output that should be a row appears as a column, you used `println` where `print` was needed.

---

## Mistake 7 — forgetting `\n` before the final message

**Incorrect code**

```java
System.out.println("Second largest number is:" + secondLargest);
```

**Why it is wrong**
The display loop left the cursor mid-line, so the message runs on directly after `85`.

**Correct code**

```java
System.out.println("\nSecond largest number is:" + secondLargest);
```

**How to recognise it**
Whenever you mix `print` and `println`, keep track of where the cursor is.

---

## Mistake 8 — assuming the array has at least two elements

**Incorrect assumption**

```java
int[] arr = {42};      // only one element
```

With the original seeding, `largest` and `secondLargest` are both `42`, and the program reports `42` as the second largest — which is meaningless.

**Correct code**

```java
if (arr.length < 2) {
    throw new IllegalArgumentException("Need at least 2 elements");
}
```

**How to recognise it**
Whenever you track `k` values, ask whether the input can be smaller than `k`.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| Multi-variable state | can you track two coupled values correctly? |
| Assignment order | do you know demotion must precede promotion? |
| `else if` semantics | do you know why two `if`s break it? |
| Seeding | **do you spot that both seeds being `arr[0]` is wrong?** |
| Efficiency | do you know sorting is unnecessary? |
| Edge cases | duplicates, arrays of length 0 or 1, descending order |

> [!tip]
> This is one of the most common intermediate interview questions precisely because there are so many ways to get it subtly wrong. The naive solutions look right and pass the obvious test case. **Volunteering a failing case for your own code is a strong signal** — it shows you test rather than assume.

## Likely follow-up questions

> **"Trace your code on `{92, 86, 47}`."**

With the original seeding it wrongly returns `92`. With `Integer.MIN_VALUE` it correctly returns `86`. This is the standard probe for the seeding bug.

> **"Why must `secondLargest = largest;` come before `largest = arr[i];`?"**

Because assignment overwrites. Reversed, the old leader is destroyed before it can be saved, and both variables end up equal. Same principle as a swap needing a `temp`.

> **"Why `else if` rather than a second `if`?"**

Because the first branch modifies `secondLargest`, which the second branch tests. With a plain `if`, the same element can satisfy both and be counted as first *and* second.

> **"What if the array has duplicates of the maximum, like `{92, 92, 86}`?"**

Ask what "second largest" means: second-largest *value* (`86`) or second-largest *element* (`92`). Both are defensible; the requirement must decide. Recognising the ambiguity is the point.

> **"What is the time complexity?"**

`O(n)` — a single pass with constant work per element. Better than the `O(n log n)` sorting approach.

> **"Could you sort and take `arr[length - 2]`?"**

Yes, but it is slower, it mutates the caller's array unless you clone first, and it returns `92` for `{92, 92, 86}`.

> **"What if the array has fewer than two elements?"**

There is no second largest. Throw `IllegalArgumentException`. The current code silently returns `arr[0]`.

> **"Now find the third largest. And the k-th largest."**

Third largest: add a `thirdLargest` with a three-level cascade. For general `k`, tracking `k` variables stops scaling — use a min-heap of size `k` (`PriorityQueue`), giving `O(n log k)`. Knowing when the simple approach stops being appropriate is the real answer.

---

# 14. Complexity

## Time complexity: `O(n)`

Let `n` be the number of elements.

There are two loops, each visiting every element once with constant work per pass. Total: about `2n` operations.

In beginner language:

> "The display loop looks at each of the 10 numbers once, and the computation loop looks at each of them once. That is about 20 steps. With 1,000 numbers it would be about 2,000. Double the array and you double the work. Because the work grows in direct proportion to the number of elements, we call it linear time and write it `O(n)`."

> [!note] Why `2n` is still `O(n)`
> Big-O describes the shape of the growth, not the exact count. `n` and `2n` are both straight lines. Constant multipliers are dropped.
>
> The computation loop alone is `O(n)`, and that is the part that matters — the display loop exists only to show the input.

**Is `O(n)` optimal?** Yes. Any unexamined element could be the second largest, so every element must be looked at at least once.

**Compared with sorting:** `O(n)` versus `O(n log n)`. For a million elements that is roughly one million operations versus twenty million. **Recognising that sorting is overkill is the main efficiency insight of this problem.**

## Space complexity: `O(1)`

Extra memory: `largest`, `secondLargest`, and one `i` at a time. Four `int`s at most, regardless of array size.

> "We use the same handful of variables whether the array holds 10 elements or 10 million, so the extra memory is constant: `O(1)`."

The sorting approach needs `O(n)` extra space if you clone to avoid mutating the input — another point in favour of the single pass.

---

# 15. Edge cases

## Descending array — the bug exposed

```java
int[] arr = {92, 86, 47};
```

```text
Seeds:  largest = 92,  secondLargest = 92

i=0: 92 > 92 ? no.   92 > 92 ? no.   nothing
i=1: 86 > 92 ? no.   86 > 92 ? no.   nothing    ← 86 ignored!
i=2: 47 > 92 ? no.   47 > 92 ? no.   nothing

Reported: 92     ✗  should be 86
```

> [!warning] This is the program's real bug
> Because `secondLargest` is seeded with `arr[0]` — the largest value in a descending array — **no element can ever beat it**. Every value fails both tests and is silently discarded.
>
> The program then reports the largest value as the second largest, with no error and no warning.
>
> **Why the original array hides this:** `arr[0]` is `14`, the *smallest* value, so nearly everything overtakes it immediately. The program is correct by luck, not by construction.
>
> **The fix:** seed both with `Integer.MIN_VALUE`, or seed from the first two elements. See section 11.
>
> **How to catch this class of bug:** always test with data whose first element is the largest. A "works on my data" program is not a working program.

## Already-sorted ascending array

```java
int[] arr = {1, 2, 3, 4, 5};
```

```text
largest: 1 → 2 → 3 → 4 → 5
second:  1 → 1 → 2 → 3 → 4

Reported: 4     ✓ correct
```

Works, because every element takes the first branch and the demotion cascade runs correctly.

## All elements equal

```java
int[] arr = {7, 7, 7};
```

```text
Seeds: largest = 7, second = 7
7 > 7 ? no.  7 > 7 ? no.   (three times)
Reported: 7
```

Arguably correct if you mean "second largest element", and wrong if you mean "second largest distinct value" — in which case there is no answer at all. A requirements question, not a code question.

## Two elements

```java
int[] arr = {5, 9};
```

```text
Seeds: largest = 5, second = 5
i=0: 5 > 5 ? no.  5 > 5 ? no.
i=1: 9 > 5 ? YES → second = 5, largest = 9

Reported: 5     ✓ correct
```

But reverse them:

```java
int[] arr = {9, 5};
```

```text
Seeds: largest = 9, second = 9
i=0: 9 > 9 ? no.  9 > 9 ? no.
i=1: 5 > 9 ? no.  5 > 9 ? no.

Reported: 9     ✗ should be 5
```

**The same two numbers give the right answer in one order and the wrong answer in the other.** That is the seeding bug in its clearest possible form.

## Single element

```java
int[] arr = {42};
```

Both accumulators stay `42`. Reports `42`. There is no second largest of a one-element array — the program should reject the input rather than invent an answer.

## Empty array

```java
int[] arr = {};
```

`arr[0]` throws `ArrayIndexOutOfBoundsException` at the seeding line, before any loop starts. Same behaviour as [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian|LargestArrayElement]], and for the same reason: seeding from data requires data to exist.

## All negative

```java
int[] arr = {-5, -3, -9};
```

```text
Seeds: largest = -5, second = -5
-5 > -5 ? no.  -5 > -5 ? no.
-3 > -5 ? YES → second = -5, largest = -3
-9 > -3 ? no.  -9 > -5 ? no.

Reported: -5     ✓ correct
```

Works here because `arr[0]` happens not to be the largest. Seeding from the data does at least avoid the `max = 0` trap.

## Very large values

```java
int[] arr = {2147483647, 2147483646};
```

Works. **No arithmetic is performed anywhere** in this program — only comparisons — so overflow is impossible.

> [!note]
> But note one subtlety about the *fixed* version: if you seed with `Integer.MIN_VALUE` and the array genuinely contains `Integer.MIN_VALUE`, you cannot distinguish "found it" from "never updated". Seeding from the first two elements avoids that ambiguity entirely — which is why it is the more robust fix.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Find the second largest number in a list.

        ↓

Can I do this by tracking one value?
No. I cannot know something is SECOND
until I know what is FIRST.
The two facts are entangled.

        ↓

So I need TWO running values:
int largest
int secondLargest

        ↓

What is the insight that connects them?
When a new leader appears, the OLD LEADER
is exactly the value that becomes second.
Nothing is lost — it flows downward.

        ↓

What are the seeds?
largest: no identity exists → from the data
second:  no identity exists → from the data
BUT they must not be the SAME element.
Seeding both with arr[0] claims one element
is simultaneously first and second.  ✗
Correct: Integer.MIN_VALUE, or the first TWO.

        ↓

What are the cases per element?
1. bigger than largest      → new leader
2. between second and largest → new runner-up
3. smaller than both          → ignore

        ↓

Case 1 — in what order?
secondLargest = largest;   ← save the old leader FIRST
largest = arr[i];          ← then install the new one
Reverse this and the old leader is destroyed.
(Same principle as needing a temp in a swap.)

        ↓

Case 2 —
secondLargest = arr[i];

        ↓

Case 3 — nothing.

        ↓

How do I stop an element matching two cases?
Case 1 CHANGES the value case 2 tests.
So they must be mutually exclusive.
→ if / else if, never two separate ifs.

        ↓

Do I need a final else?
No. "Do nothing" is the default.

        ↓

How do I visit every element?
for (int i = 0; i < arr.length; i++)
(or the enhanced for — no index is needed)

        ↓

Trace with the real data:
largest  14→46→47→86→92
second      14→46→47→86
Answer: 86  ✓

        ↓

Now TEST A HOSTILE CASE:
descending {92, 86, 47} → reports 92  ✗
        ↓
The seeding bug is real, and it is
invisible on the original data.
        ↓
Fix the seeds.
```

> [!important] The habit that matters most here
> Notice the second-to-last step. The trace on the *given* data passed. The bug only appeared when a **deliberately awkward** input was tried.
>
> **Testing your own code with data designed to break it is the single most valuable debugging habit there is.** For array problems, the standard hostile cases are: empty, one element, all equal, ascending, descending, and all negative.
>
> This program passes four of those six and fails two — and its own data is not one of the six.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int arr[] = {14, 46, ...};` | "Create a row of ten boxes holding whole numbers and fill them with these values." |
| `int largest = arr[0];` | "Assume for now that the first number is the biggest, since it is the only one I have seen." |
| `int secondLargest = arr[0];` | "Also assume the first number is the runner-up — which is really a claim that one number holds two places at once, and is the flaw in this program." |
| `System.out.print(arr[i] + "\t");` | "Print the number in box `i` followed by a tab, and stay on the same line so they form a row." |
| `if (arr[i] > largest)` | "Is this number bigger than the champion so far?" |
| `secondLargest = largest;` | "If so, the old champion is now the runner-up — save it before I overwrite it." |
| `largest = arr[i];` | "And this new number becomes the champion." |
| `else if (arr[i] > secondLargest)` | "Otherwise — and only if the first test failed — is it at least better than the runner-up?" |
| `secondLargest = arr[i];` | "If so, it becomes the new runner-up. The champion is unchanged." |
| `System.out.println("\n..." + secondLargest);` | "Start a fresh line, then print the label followed by the runner-up's value." |

The loop in one sentence:

> **"Walk through every number; if it beats the champion, demote the champion to runner-up and crown the newcomer; otherwise, if it merely beats the runner-up, make it the new runner-up."**

And the whole program:

> **"Show the numbers in a row. Then walk through them keeping track of the best and second-best seen so far, letting each dethroned champion slide into second place, and finally report the runner-up."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| coupled accumulators | two running values where updating one requires updating the other |
| assignment order | save before you overwrite — `second = largest` must precede `largest = arr[i]` |
| `else if` | makes branches mutually exclusive; required when a branch changes what a later branch tests |
| seeding | tracking `k` values requires seeding from `k` distinct elements |
| `Integer.MIN_VALUE` | the smallest possible `int`; a safe seed for a running maximum |
| `print` vs `println` | `print` stays on the line; `println` starts a new one |
| escape sequence | `\t`, `\n`, `\\` — backslash plus a letter, producing one character |
| `int arr[]` vs `int[] arr` | identical; the second is preferred Java style |

### Important methods

| Member | Owner | Accepts | Returns |
|---|---|---|---|
| `print(String)` | `java.io.PrintStream` | text | `void` — no newline |
| `println(String)` | `java.io.PrintStream` | text | `void` — adds a newline |
| `printf(String, ...)` | `java.io.PrintStream` | a format string plus values | `PrintStream` |
| `Math.max(int,int)` | `java.lang.Math` | two numbers | the larger |
| `Math.min(int,int)` | `java.lang.Math` | two numbers | the smaller |
| `Arrays.sort(int[])` | `java.util.Arrays` | an array | `void` — sorts **in place** |
| `clone()` | array | — | a copy of the array |

### Important syntax

| Syntax | Meaning |
|---|---|
| `int arr[]` | C-style array declaration — legal, but prefer `int[] arr` |
| `{...}` after `=` | array initialiser — only valid at declaration |
| `arr[i]` | the element at position `i` |
| `arr.length` | element count — a field, no parentheses |
| `>` | greater than |
| `if / else if` | mutually exclusive branches, checked top to bottom |
| `\t` | a tab character |
| `\n` | a newline character |
| `Integer.MIN_VALUE` | `-2147483648`, the smallest `int` |

### Main interview concept

> **To track the top `k` values, seed from `k` distinct elements — never from one element repeated.** Seeding both `largest` and `secondLargest` from `arr[0]` claims a single element occupies two places, and any value below `arr[0]` is then silently ignored. And within the update, **demote before you promote**: `secondLargest = largest;` must come first, or the old leader is destroyed before it can be saved.

### Main lesson for code reading

> **When a program tracks more than one running value, read the update order and the branch structure before anything else.**
>
> ```text
> secondLargest = largest;    ← save the old value
> largest = arr[i];           ← then overwrite
>
> if (...) { }                ← changes what the next test sees
> else if (...) { }           ← so it MUST be else if
> ```
>
> Both of these compile cleanly either way and produce plausible wrong answers when reversed. Neither the compiler nor a casual reading will catch them — **only tracing on hostile input will.** Always test with a descending array, a single element, and all-equal values.

---

### Related notes

- [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian|LargestArrayElement]] — one running maximum, and the seeding reasoning this program extends
- [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]] — "save before you destroy", the same principle as demote-before-promote
- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — the accumulator pattern with an identity seed
- [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] — `if` conditions and another bug that only shows on the right input
- [[Java_EmplSort_Code_Reading_Explanation_Obsidian|EmplSort]] — sorting objects, and why `>` does not work on them
