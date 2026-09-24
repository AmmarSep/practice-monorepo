---
title: Java Code Reading — EvenOdd
tags:
  - java
  - interview-programs
  - code-reading
  - arrays
  - loops
  - operators
aliases:
  - EvenOdd Explained
  - Even and Odd Numbers in Array
---

# Java Code Reading — EvenOdd Explained for a Fresh Java Programmer

> [!note]
> This program teaches the **modulo operator** `%` — the tool that answers "is this number divisible by that one?" — and it introduces **filtering**: walking through data and acting on only some of the items.
>
> It also contains a real **off-by-one bug**. The last element of the array is never examined. We will explain the code exactly as written first, prove the bug by tracing it, and only then show the fix. Reading buggy code carefully is a skill in itself — most code you meet in your career will have a bug in it somewhere.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Do I need every item or only some? → What test separates them? → What do I do with the ones that pass?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class EvenOdd {

        public static void main(String[] args) {
            // Initialize the array
            int[] arreo = new int[]{7, 3, 6, 334, 75, 78};

            // Print even numbers in the array
            System.out.println("Even numbers in the array: ");
            for (int i = 0; i < arreo.length - 1; i++) {
                if (arreo[i] % 2 == 0) {
                    System.out.println(arreo[i]);
                }
            }

            // Print odd numbers in the array
            System.out.println("Odd numbers in the array: ");
            for (int i = 0; i < arreo.length - 1; i++) {
                if (arreo[i] % 2 != 0) {
                    System.out.println(arreo[i]);
                }
            }
        }
    }
```

The executable logic:

```java
int[] arreo = new int[]{7, 3, 6, 334, 75, 78};

for (int i = 0; i < arreo.length - 1; i++) {
    if (arreo[i] % 2 == 0) { System.out.println(arreo[i]); }
}

for (int i = 0; i < arreo.length - 1; i++) {
    if (arreo[i] % 2 != 0) { System.out.println(arreo[i]); }
}
```

Two loops with the same shape, differing only in the test inside the `if`.

> [!warning] Read this before you trust the output
> The loop condition is `i < arreo.length - 1`. With six elements, that visits indexes `0` through `4` — **it never looks at index 5**, which holds `78`.
>
> `78` is an even number, and it will **not** appear in the "Even numbers" output.
>
> We will explain the code as written, prove this by tracing in section 9, and give the corrected version in section 11. Do not skip ahead — understanding *how* a silent bug hides is more valuable than being handed the fix.

---

# 2. What problem is this program solving?

In plain language:

> I have a list of numbers. Print the even ones, then print the odd ones.

### What do we know?

- Six whole numbers, all known up front: `7, 3, 6, 334, 75, 78`.
- The definition of even: divisible by 2 with nothing left over.
- The definition of odd: everything else.

### What do we need?

- Two groups of output. Not one summary number — a *selection* of the input.

### What transformations are required?

Notice the shape. This is **not** a reduction like [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]], where many values collapsed into one. Here we have **many values in, some values out**.

```text
REDUCTION (SumOfArrayEle)          FILTER (EvenOdd)
─────────────────────────          ─────────────────────────
43 53 90 79 89                     7  3  6  334  75  78
  \  \ | /  /                            │      │      │
      354                          6   334    78   (evens)
   many → one                      many → some
```

That difference tells you what tools you need:

| | Reduction | Filter |
|---|---|---|
| needs an accumulator? | **yes** | **no** |
| needs a condition? | not necessarily | **yes** — the test that selects |
| output | one value | several values |

So the ingredients here are: **a way to visit every item** (a loop), and **a test that separates the items** (an `if`).

### How do we test for evenness?

This is the one genuinely mathematical decision in the program.

> "A number is even if dividing it by 2 leaves no remainder."

So we need an operator that gives us a **remainder**. Java's remainder operator is `%`:

```text
6 % 2  →  0     no remainder → even
7 % 2  →  1     remainder of 1 → odd
```

Therefore the test is `number % 2 == 0`.

> [!important] Deriving `% 2 == 0` rather than memorising it
> Beginners memorise "even means `% 2 == 0`". Better to derive it:
>
> 1. Even means "divisible by 2".
> 2. "Divisible by X" means "dividing by X leaves nothing over".
> 3. "What is left over" is the **remainder**.
> 4. Java's remainder operator is `%`.
> 5. Therefore: `n % 2 == 0`.
>
> Derived this way, the pattern generalises immediately:
> - divisible by 3 → `n % 3 == 0`
> - divisible by 10 → `n % 10 == 0`
> - a leap-year-style test → `year % 4 == 0`
>
> And it explains the related trick used in [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]]: `n % 10` extracts the **last digit** of a number, because dividing by ten leaves the units behind.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder. First statement in the file.

## `public class EvenOdd`

- `public` — usable from anywhere.
- `class` — declares a class.
- `EvenOdd` — the name, `PascalCase`, matching `EvenOdd.java`.

The name describes exactly what the program does. Good naming.

## `public static void main(String[] args)`

The JVM entry point.

| Part | Meaning | Why required |
|---|---|---|
| `public` | reachable from outside the class | the JVM is outside |
| `static` | belongs to the class, not an object | no object exists at startup |
| `void` | returns nothing | the JVM has nowhere to put a result |
| `main` | the exact name the JVM looks for | fixed by the Java launcher |
| `String[] args` | command-line arguments | text typed after the program name |

## No imports

The program uses `int`, `int[]`, `String` and `System` — primitives, arrays, and `java.lang` classes. No `import` needed.

## Variables

| Variable | Type | Role | Lives where |
|---|---|---|---|
| `arreo` | `int[]` | the data | all of `main` |
| `i` (first loop) | `int` | counter | first loop only |
| `i` (second loop) | `int` | counter | second loop only |

> [!note] Two variables named `i`
> The two `i` variables are genuinely **separate**. Each is declared inside its own `for` header, so each lives and dies with its own loop.
>
> That is why reusing the name is legal here — the first `i` no longer exists by the time the second is declared. Had `i` been declared *before* both loops, redeclaring it would be a compile error.
>
> This is a practical benefit of declaring counters inside the loop header: the name stays free for reuse, and a stale counter can never leak into later code.

## Notable structure

There are **two loops over the same array**, with the same bounds, differing only in the `if` condition. That duplication is worth noticing — see section 11 for whether it is justified.

---

# 4. Line-by-line explanation

## 4.1 — `int[] arreo = new int[]{7, 3, 6, 334, 75, 78};`

```java
int[] arreo = new int[]{7, 3, 6, 334, 75, 78};
```

**What is this?**
Creating an array of six integers and storing a reference to it in `arreo`.

**Why do we need it?**
The requirement involves *several* numbers. A single `int` holds one value; an array holds many values of the same type.

**How would a beginner know to write it?**
Ask: **"How many values must I store?"**

- Exactly one → a simple variable
- A fixed, known number, all the same type → an **array**
- An unknown or changing number → a `List`

Six known whole numbers → `int[]`.

**What type of data is involved and why?**

`int[]` means "array whose elements are `int`". All six values are whole numbers with no decimal part, and we will do arithmetic on them (`% 2`), so `int` is right. `double[]` would be wrong — `%` on floating-point values behaves in ways you do not want here. `String[]` would be wrong — you cannot take the remainder of text.

**What does each part mean?**

```text
int[]   arreo   =   new   int[]   {7, 3, 6, 334, 75, 78}   ;
  │       │     │    │      │              │
  │       │     │    │      │              └── initial contents
  │       │     │    │      └───────────────── the type being created
  │       │     │    └──────────────────────── allocate on the heap
  │       │     └───────────────────────────── store the reference
  │       └─────────────────────────────────── the reference variable
  └─────────────────────────────────────────── the declared type
```

**What does Java do when it reaches this line?**

1. Allocates heap memory for six `int`s.
2. Writes the six values into consecutive slots.
3. Records the length (`6`) permanently — it can never change.
4. Copies the array's address into `arreo`.

```text
   arreo                     the array object
  ┌─────┐        ┌───┬───┬───┬─────┬────┬────┐
  │ ref ├───────▶│ 7 │ 3 │ 6 │ 334 │ 75 │ 78 │    length = 6
  └─────┘        └───┴───┴───┴─────┴────┴────┘
                   0   1   2    3    4    5
```

**Why the name `arreo`?**
Honestly, this is a poor name. It appears to be "arr" + "eo" (for even/odd), but it reads as a typo and describes the container rather than the content. `numbers` or `values` would be clearly better. Worth noticing precisely because you will inherit names like this in real codebases and must learn to read past them.

---

## 4.2 — `System.out.println("Even numbers in the array: ");`

```java
System.out.println("Even numbers in the array: ");
```

**What is this?**
A header line printed before the results.

**Why do we need it?**
Without it, the program would print a column of bare numbers and the reader would have no idea what they represent. This label gives the output meaning.

**Why is it outside the loop?**
Because it should appear **once**, not once per number. If it were inside the loop:

```text
Even numbers in the array:
6
Even numbers in the array:
334
```

**Rule to take away: a header belongs outside the loop; the repeated content belongs inside.** Where you place a print statement relative to `{ }` decides how many times it runs.

**What does it return?**
`void`.

---

## 4.3 — `for (int i = 0; i < arreo.length - 1; i++) {`

```java
for (int i = 0; i < arreo.length - 1; i++) {
```

**What is this?**
A `for` loop walking the array indexes.

**Why do we need it?**
To examine every number. Writing six separate `if` statements would work only for this exact array; a loop expresses "for every element" regardless of how many there are.

**The three sections**

```text
for ( int i = 0 ; i < arreo.length - 1 ; i++ )
         │              │                 │
         │              │                 └── after each pass
         │              └──────────────────── before each pass
         └─────────────────────────────────── once, at the start
```

**Why does `i` start at `0`?**
Because `i` is an **index** here — a position in the array — and Java arrays are zero-indexed. The first element is at position `0`.

```text
value:   7   3   6   334   75   78
index:   0   1   2    3    4    5
```

Contrast with [[Java_Factorials_Code_Reading_Explanation_Obsidian|Factorials]], where `i` was a *value* to multiply and started at `1`. **Ask what `i` means before choosing its starting point.**

**What is `arreo.length`?**
The number of elements: `6`. It is a **field**, not a method — no parentheses.

> [!warning] `length` vs `length()`
> | Type | Correct form |
> |---|---|
> | array | `arr.length` — a field |
> | `String` | `str.length()` — a method |
> | `List` | `list.size()` — a different method |
>
> Three spellings for "how many". Writing `arreo.length()` gives `cannot find symbol: method length()`.

**Now the critical part: what is `arreo.length - 1`?**

```text
arreo.length      = 6
arreo.length - 1  = 5

so the condition is:  i < 5
which is true for:    i = 0, 1, 2, 3, 4
```

**Five indexes are visited. Index 5 is never reached.**

> [!warning] This is the bug
> The array has six elements at indexes `0` through `5`. This loop stops at index `4`.
>
> ```text
> index:   0   1   2    3    4    5
> value:   7   3   6   334   75   78
>          └──────visited──────┘    ↑
>                              never examined
> ```
>
> **`78` is never tested and never printed** — in either loop.
>
> Only two loop conditions are correct for visiting a whole array:
>
> | Condition | Indexes | Verdict |
> |---|---|---|
> | `i < arr.length` | 0..5 | ✓ correct, idiomatic |
> | `i <= arr.length - 1` | 0..5 | ✓ correct, wordy |
> | `i < arr.length - 1` | 0..4 | ✗ **this program — skips the last** |
> | `i <= arr.length` | 0..6 | ✗ crashes with `ArrayIndexOutOfBoundsException` |
>
> Notice the asymmetry in how the two wrong versions fail. `i <= arr.length` **crashes loudly** — you find out immediately. `i < arr.length - 1` **fails silently** — the program runs, prints plausible output, and quietly lies to you.
>
> **Silent wrong answers are far more dangerous than crashes.** A crash points at the line; a silent bug can survive for years.

**Why might the author have written `- 1`?**

An understandable confusion. The *last valid index* is `length - 1`, which is `5`. That fact is true and important. The mistake is combining it with `<` instead of `<=`:

```text
The last valid index is  length - 1  = 5.    ← correct fact
So the loop must include 5.
"Include 5"  →  i <= 5  →  i <= length - 1   ← correct code
                or        i <  length         ← correct code
NOT           →  i <  length - 1              ← excludes 5
```

The `- 1` and the `<` each subtract one from the range. Doing both subtracts two.

**What is `i++`?**
The increment operator, `i = i + 1`. Without it the loop never terminates.

---

## 4.4 — `if (arreo[i] % 2 == 0) {`

```java
if (arreo[i] % 2 == 0) {
```

**What is this?**
A condition that tests whether the current element is even.

**Why do we need it?**
The loop visits *every* element, but we only want to print *some* of them. The `if` is the filter.

**How would a beginner know to write it?**
Ask: "Do I act on every item, or only some?" Only some → you need a test. Then ask what distinguishes them → evenness → remainder on division by 2.

**What does each part mean?**

```text
if ( arreo[i]   %   2   ==   0 )
       │        │   │   │    │
       │        │   │   │    └── the value we expect the remainder to be
       │        │   │   └─────── equality comparison → produces true/false
       │        │   └─────────── the divisor
       │        └─────────────── remainder operator
       └──────────────────────── the current element
```

**What is `arreo[i]`?**
Array element access. The square brackets mean "the element at this position". Because `i` changes each pass, `arreo[i]` refers to a different number each time — that is how one line of code examines six values.

**What is `%`?**

The **remainder** operator (commonly called modulo). It performs the division and gives you **what is left over**, discarding the quotient.

```text
 7 % 2  →  1      because 7 = 3×2 + 1
 3 % 2  →  1      because 3 = 1×2 + 1
 6 % 2  →  0      because 6 = 3×2 + 0
334 % 2 →  0      because 334 = 167×2 + 0
 75 % 2 →  1      because 75 = 37×2 + 1
 78 % 2 →  0      because 78 = 39×2 + 0
```

For any integer `n`, `n % 2` is always either `0` or `1` (for non-negative `n`). That is exactly the two-way split we need.

**What is `==`?**

The equality comparison operator. It **asks a question** and produces a `boolean`.

> [!warning] `==` vs `=` — the most common bug in all of programming
> ```java
> if (arreo[i] % 2 == 0)     // ✓ asks: is the remainder zero?
> if (arreo[i] % 2 = 0)      // ✗ tries to ASSIGN 0 to an expression
> ```
>
> The second does not even compile in Java, because `arreo[i] % 2` is not a storage location.
>
> Java also protects you in a subtler way: `if` **requires** a `boolean`. In C, `if (x = 5)` compiles and silently assigns. In Java, `if (x = 5)` fails unless `x` is a `boolean`. The compiler catches the slip for you.
>
> **Say it out loud:** if you mean "**is** it equal", use `==`. If you mean "**make** it equal", use `=`.

**What does the whole condition produce?**
A `boolean`. If `true`, the block runs. If `false`, it is skipped and the loop moves on.

**Operator precedence — why no parentheses are needed**

```text
arreo[i] % 2 == 0
```

`%` binds more tightly than `==`, so Java reads this as:

```text
(arreo[i] % 2) == 0        ✓ what we want
```

not:

```text
arreo[i] % (2 == 0)        ✗ would not compile anyway
```

The precedence order for the operators you have met:

```text
highest   [ ]  ( )  .
          ++  --
          *  /  %
          +  -
          <  >  <=  >=
          ==  !=
lowest    =  +=  -=  *=  /=
```

> [!tip]
> You do not need to memorise the whole table. Remember the useful part: **arithmetic binds tighter than comparison, and comparison binds tighter than assignment.** When unsure, add parentheses — they cost nothing and make the intent explicit.

---

## 4.5 — `System.out.println(arreo[i]);`

```java
System.out.println(arreo[i]);
```

**What is this?**
Printing the current element.

**Why is it inside the `if`?**
Because we only print elements that passed the test. Placement inside `{ }` is what makes it conditional.

**Compare the three possible placements:**

```java
for (...) {
    if (...) {
        System.out.println(arreo[i]);   // ← runs only for evens        (correct)
    }
    System.out.println(arreo[i]);       // ← runs for every element     (wrong)
}
System.out.println(arreo[i]);           // ← does not compile: i is out of scope
```

**Braces determine behaviour; indentation only suggests it.** When they disagree, the braces win.

**Which `println` overload runs?**
`println(int)`, because `arreo[i]` is an `int`. No concatenation, no `String` involved.

---

## 4.6 — the second loop

```java
System.out.println("Odd numbers in the array: ");
for (int i = 0; i < arreo.length - 1; i++) {
    if (arreo[i] % 2 != 0) {
        System.out.println(arreo[i]);
    }
}
```

Structurally identical to the first loop. **One character differs**: `==` becomes `!=`.

**What is `!=`?**
The "not equal to" comparison. It produces `true` exactly when `==` would produce `false`.

```text
7 % 2 != 0   →   1 != 0   →   true    (odd)
6 % 2 != 0   →   0 != 0   →   false   (even)
```

**Why `!= 0` rather than `== 1`?**

Both work for positive numbers. `!= 0` is better, and the reason is instructive:

> [!important] Negative numbers and `%`
> In Java, `%` keeps the **sign of the left operand**:
>
> ```text
>  7 % 2  →   1
> -7 % 2  →  -1        ← negative, not 1!
> ```
>
> So for `-7`:
> ```java
> -7 % 2 == 1     →   -1 == 1   →   false   ✗ misses odd negatives
> -7 % 2 != 0     →   -1 != 0   →   true    ✓ correct
> ```
>
> **`% 2 != 0` is correct for all integers; `% 2 == 1` is wrong for negatives.**
>
> This program's array has no negatives, so both would work here — but writing the version that is correct in general costs nothing and is a genuinely good habit.

**Is the second loop's counter a new variable?**
Yes. The first `i` died when its loop ended. This is a completely separate `i`.

---

# 5. How to think like the programmer

```text
Requirement
"Print the even numbers, then the odd numbers"
        ↓
What data do I have?
Six whole numbers → int[]
        ↓
What do I need to produce?
Not one value — a SELECTION of the input
        ↓
That is a FILTER, not a reduction.
So I need no accumulator —
I need a loop plus a test.
        ↓
How do I visit every element?
A for loop over the indexes
        ↓
Where do indexes start and end?
Arrays are zero-indexed → 0 to length-1
Condition: i < length   (or i <= length-1)
        ↓
How do I test for even?
"Even" means divisible by 2.
"Divisible" means no remainder.
Java's remainder operator is %
Therefore: arreo[i] % 2 == 0
        ↓
What do I do when the test passes?
Print the element → inside the if block
        ↓
What about odd?
Odd is simply "not even" → % 2 != 0
        ↓
Do I need a second loop, or can one loop do both?
Two loops keeps the output grouped:
all evens, then all odds.
One loop with if/else would interleave them.
The requirement wants them grouped → two loops.
        ↓
Add headers so a human can read the output.
Place them OUTSIDE the loops so they print once.
```

> [!important] The filter skeleton
> ```java
> for (int i = 0; i < data.length; i++) {
>     if (TEST(data[i])) {
>         ACT(data[i]);
>     }
> }
> ```
>
> Change the two blanks and you get a different program:
>
> | Goal | TEST | ACT |
> |---|---|---|
> | print evens | `x % 2 == 0` | print it |
> | print odds | `x % 2 != 0` | print it |
> | count evens | `x % 2 == 0` | `count++` |
> | sum evens | `x % 2 == 0` | `sum += x` |
> | find multiples of 3 | `x % 3 == 0` | print it |
> | find negatives | `x < 0` | print it |
>
> Recognising the skeleton means you have learned six programs, not one.

---

# 6. Deep explanation of important Java concepts used

## Array

A **fixed-size, ordered, same-type** collection stored in consecutive memory.

- Length fixed at creation and never changeable.
- All elements the same type.
- Indexed from `0` to `length - 1`.
- Arrays are **objects** — created with `new`, living on the heap, accessed through a reference.

```text
              arreo.length = 6
        ┌───┬───┬───┬─────┬────┬────┐
arreo ─▶│ 7 │ 3 │ 6 │ 334 │ 75 │ 78 │
        └───┴───┴───┴─────┴────┴────┘
          0   1   2    3    4    5
                                 ↑
                       last valid index = length - 1
```

## The modulo / remainder operator `%`

`a % b` gives the remainder after dividing `a` by `b`.

```text
17 / 5  =  3        ← integer division: the quotient, decimals discarded
17 % 5  =  2        ← the remainder: 17 = 3×5 + 2
```

`/` and `%` are two halves of one division. Together they tell you everything:

```text
       17 ÷ 5
      ┌────┴────┐
   quotient  remainder
      3          2
    (17/5)     (17%5)
```

Common uses worth knowing:

| Test | Meaning |
|---|---|
| `n % 2 == 0` | even |
| `n % 2 != 0` | odd |
| `n % 3 == 0` | divisible by 3 |
| `n % 10` | the last digit of `n` |
| `n / 10` | `n` with its last digit removed |
| `i % 5 == 0` | every fifth iteration |
| `year % 4 == 0` | (part of) the leap-year test |

The last two rows are the engine of [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]], which peels digits off a number one at a time.

> [!warning] `%` by zero
> ```java
> int x = 5 % 0;    // throws ArithmeticException: / by zero
> ```
> Just like division, the right operand must not be zero. Not a risk in this program, since `2` is a literal — but relevant whenever the divisor is a variable.

## The `if` statement

```java
if (condition) {
    // runs only when condition is true
}
```

The condition **must** be a `boolean` in Java — you cannot write `if (5)` as you can in C. This strictness eliminates a whole family of bugs.

`if` is a **statement**: it performs an action and produces no value. That is why you cannot write `String s = if (...) ...`. Compare with the ternary operator in [[Java_TernaryOperatorTime_Code_Reading_Explanation_Obsidian|TernaryOperatorTime]], which is an *expression* and does produce a value.

Here we are choosing an **action** (print or do not print), so `if` is the right tool.

## Comparison operators

| Operator | Meaning | `7 % 2` is `1`, so... |
|---|---|---|
| `==` | equal to | `1 == 0` → `false` |
| `!=` | not equal to | `1 != 0` → `true` |
| `<` | less than | `1 < 0` → `false` |
| `>` | greater than | `1 > 0` → `true` |
| `<=` | less than or equal | `1 <= 0` → `false` |
| `>=` | greater than or equal | `1 >= 0` → `true` |

All six take two numbers and produce a `boolean`.

## Nesting and blocks

```java
for (...) {              // outer block
    if (...) {           // inner block
        println(...);    // innermost statement
    }
}
```

Each `{ }` creates a scope. The `if` block is *inside* the loop block, so it runs once per iteration — but its contents run only when the condition holds.

Reading nested code: **follow the braces, not the indentation.** Indentation is a human convention that can lie; braces are what the compiler obeys.

## Filtering vs reducing

| | Filter (this program) | Reduce ([[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian\|SumOfArrayEle]]) |
|---|---|---|
| output | some of the input | one value |
| needs an accumulator? | no | yes |
| needs a condition? | yes | no |
| after the loop | nothing to read | read the accumulator |

Many real problems combine both — "sum only the even numbers" is a filter *and* a reduce.

## Scope of a loop counter

```java
for (int i = 0; ...) { ... }     // i lives here only
// i does not exist here

for (int i = 0; ...) { ... }     // a brand-new, unrelated i
```

Declaring the counter in the header keeps it contained and lets you reuse the name.

---

# 7. Why this syntax?

## `%` vs `/`

```text
7 / 2  →  3     the quotient (integer division truncates)
7 % 2  →  1     the remainder
```

> [!warning] Integer division truncates — it does not round
> ```java
> 7 / 2   →  3      not 3.5, and not 4
> 1 / 2   →  0      not 0.5
> -7 / 2  →  -3     truncates toward zero
> ```
> Both operands are `int`, so Java performs *integer* division and discards the fractional part. To get `3.5` you need at least one operand to be floating-point:
> ```java
> 7 / 2.0     →  3.5
> (double) 7 / 2   →  3.5
> ```
> This trap appears whenever you compute an average: `sum / count` on two `int`s truncates.

## `==` vs `=`

| Operator | Job | Produces |
|---|---|---|
| `==` | asks "are these the same?" | `true` or `false` |
| `=` | commands "put this there" | stores a value |

## `!=` vs `!`

Related but different:

```java
a != b        // "not equal" — a comparison operator
!flag         // "not" — a unary operator that flips a boolean
```

You could write the odd test either way:

```java
if (arreo[i] % 2 != 0)          // used here
if (!(arreo[i] % 2 == 0))       // equivalent, but harder to read
```

Prefer the direct form. Double negatives slow readers down.

## `[ ]` — two jobs

| Where | Meaning |
|---|---|
| after a type | "array of" — `int[] arreo` |
| after an array variable | "the element at" — `arreo[i]` |

## `{ }` — two jobs

| Where | Meaning |
|---|---|
| after `new int[]` | **array initialiser** — the data |
| after `for`/`if`/method/class | **block** — a group of statements |

## `<` vs `<=` with `length`

The table worth burning in:

| Condition | Indexes visited (length 6) | Verdict |
|---|---|---|
| `i < arr.length` | 0,1,2,3,4,5 | ✓ idiomatic |
| `i <= arr.length - 1` | 0,1,2,3,4,5 | ✓ correct, verbose |
| `i < arr.length - 1` | 0,1,2,3,4 | ✗ **this program** — silently skips the last |
| `i <= arr.length` | 0,1,2,3,4,5,6 | ✗ crashes |

## `length` vs `length()` vs `size()`

| Type | Form |
|---|---|
| array | `arr.length` |
| `String` | `str.length()` |
| `List` | `list.size()` |

---

# 8. Method discovery

This program calls only `println`, so the interesting discovery question is: **is there a built-in way to test for even?**

## `println`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.io.PrintStream` |
| **Why can we call it?** | `System.out` is a `PrintStream` object |
| **Which overload runs?** | `println(int)` for `arreo[i]`, `println(String)` for the headers |
| **Returns?** | `void` |

## Is there a `isEven()` method?

Type `Math.` in IntelliJ:

```text
abs()   max()   min()   pow()   sqrt()   floor()   ceil()
round()   random()   signum()   floorMod()   floorDiv()   ...
```

No `isEven()`. It does not exist, and it never will — because `n % 2 == 0` is already shorter than any method call would be.

> [!important] When *not* to look for a method
> Not every operation has a library method, and that is fine. A rough rule:
>
> | | Look for a method | Write it yourself |
> |---|---|---|
> | complexity | non-trivial algorithm | a single operator |
> | examples | `sort`, `split`, `sqrt`, `parseInt` | `% 2 == 0`, `a > b`, `x + y` |
>
> Searching for `Math.isEven()` and finding nothing is not a dead end — it is the correct answer. **The operator *is* the tool.**

## A genuinely useful discovery: `Math.floorMod`

While reading `Math.`, you might notice `floorMod`. It is worth knowing:

```java
-7 % 2                 →  -1     (Java's % keeps the left sign)
Math.floorMod(-7, 2)   →   1     (always non-negative for a positive divisor)
```

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.lang.Math` |
| **Why can we call it without an object?** | it is `static` — call it on the class itself |
| **Accepts** | two `int`s (or two `long`s) |
| **Returns** | `int` — the remainder, matching the sign of the *divisor* |

So `Math.floorMod(n, 2) == 1` is a correct odd test even for negatives. You do not need it here, but knowing it exists is exactly the kind of payoff that comes from browsing a class's method list.

## The general discovery loop

```text
"I have an int and I want to know if it is even."
        ↓
What do I actually need? → the remainder after dividing by 2
        ↓
Is there an OPERATOR for remainder? → yes, %
        ↓
Stop. No method needed.
```

> [!tip]
> Before searching for a method, ask whether an **operator** already does the job. Java has operators for arithmetic, comparison, logic and bit manipulation. Methods exist for everything beyond that.
>
> Identify the operation first, *then* decide whether it lives in the language or in the library.

---

# 9. Trace the program with real values

```text
Array:  arreo = [7, 3, 6, 334, 75, 78]
        arreo.length = 6
        arreo.length - 1 = 5
        Condition:  i < 5     →  i = 0,1,2,3,4
        Index 5 is NEVER visited.
```

## First loop — evens

```text
─────────────────────────────────────────────────────────────────────
Pass  i   i<5?   arreo[i]   arreo[i] % 2   == 0 ?   Printed
─────────────────────────────────────────────────────────────────────
 1    0   true       7           1          false     —
 2    1   true       3           1          false     —
 3    2   true       6           0          true      6
 4    3   true      334          0          true      334
 5    4   true      75           1          false     —
 —    5   FALSE      —           —            —       —     ← exits here
─────────────────────────────────────────────────────────────────────
                          ↑
              index 5 (value 78) never reached
```

Even numbers printed: `6`, `334`

## Second loop — odds

```text
─────────────────────────────────────────────────────────────────────
Pass  i   i<5?   arreo[i]   arreo[i] % 2   != 0 ?   Printed
─────────────────────────────────────────────────────────────────────
 1    0   true       7           1          true      7
 2    1   true       3           1          true      3
 3    2   true       6           0          false     —
 4    3   true      334          0          false     —
 5    4   true      75           1          true      75
 —    5   FALSE      —           —            —       —     ← exits here
─────────────────────────────────────────────────────────────────────
```

Odd numbers printed: `7`, `3`, `75`

## Actual console output

```text
Even numbers in the array: 
6
334
Odd numbers in the array: 
7
3
75
```

## What the output should be

```text
Even numbers in the array: 
6
334
78          ← MISSING from the actual output
Odd numbers in the array: 
7
3
75
```

> [!warning] Proof of the bug
> Five of the six numbers were classified. `78` appeared in **neither** list.
>
> Count the output: 2 evens + 3 odds = 5. The array has 6 elements. **Every element must land in exactly one of the two lists, so the totals must add up to the array length.** They do not.
>
> That arithmetic check — *do the output counts sum to the input count?* — is a simple, powerful way to catch this class of bug. Whenever a program partitions data, verify the parts add up.

## The exit condition, examined closely

```text
After pass 5:  i++ makes i = 5
Check:         5 < 5  →  false
Loop exits.
arreo[5] is never evaluated.
```

Had the condition been `i < arreo.length` (that is, `i < 6`), the check `5 < 6` would be `true`, one more pass would run, `78 % 2 == 0` would be `true`, and `78` would print.

**One character — the difference between `- 1` being there or not — changes the output.**

---

# 10. Visualize data where useful

## The array and what the loop actually covers

```text
index:      0     1     2      3     4     5
          ┌────┬────┬────┬──────┬────┬────┐
arreo ──▶ │  7 │  3 │  6 │  334 │ 75 │ 78 │
          └────┴────┴────┴──────┴────┴────┘
            ▲    ▲    ▲     ▲     ▲    ▲
            │    │    │     │     │    │
            └────┴────┴─────┴─────┘    ✗
                  visited          never visited
              (i < length - 1)      (the bug)
```

## The filter in action

```text
   7      3      6     334     75     78
   │      │      │      │      │      │
   ▼      ▼      ▼      ▼      ▼      ▼
 %2=1   %2=1   %2=0   %2=0   %2=1   %2=0
   │      │      │      │      │      │
   ▼      ▼      ▼      ▼      ▼      ▼
 ODD    ODD    EVEN   EVEN    ODD   EVEN
                                      ↑
                             never evaluated

EVEN list:  6, 334        (78 missing)
ODD list:   7, 3, 75
```

## How `%` splits the numbers

```text
                n % 2
                  │
       ┌──────────┴──────────┐
       0                     1
       │                     │
     EVEN                   ODD
   6, 334, 78            7, 3, 75
```

Because `n % 2` can only ever be `0` or `1` (for non-negative `n`), this split is complete — every number lands on exactly one side.

## Loop control flow

```text
        int i = 0
            │
            ▼
      ┌─▶ i < 5 ? ─── false ──▶ done
      │     │ true
      │     ▼
      │  arreo[i] % 2 == 0 ? ── false ──┐
      │     │ true                      │
      │     ▼                           │
      │  print arreo[i]                 │
      │     │                           │
      │     ◀───────────────────────────┘
      │     ▼
      │    i++
      └─────┘
```

## The two loops side by side

```text
      FIRST LOOP                    SECOND LOOP
────────────────────────      ────────────────────────
for (i = 0; i < 5; i++)       for (i = 0; i < 5; i++)
  if (arreo[i] % 2 == 0)        if (arreo[i] % 2 != 0)
                    ▲▲                          ▲▲
                    ││                          ││
              only this differs ────────────────┘
```

---

# 11. Alternative ways to write the same logic

### The fix — correct loop bounds

The minimal change that makes the program correct:

```java
int[] numbers = {7, 3, 6, 334, 75, 78};

System.out.println("Even numbers in the array: ");
for (int i = 0; i < numbers.length; i++) {          // removed the  - 1
    if (numbers[i] % 2 == 0) {
        System.out.println(numbers[i]);
    }
}

System.out.println("Odd numbers in the array: ");
for (int i = 0; i < numbers.length; i++) {          // removed the  - 1
    if (numbers[i] % 2 != 0) {
        System.out.println(numbers[i]);
    }
}
```

Output — now complete:

```text
Even numbers in the array: 
6
334
78
Odd numbers in the array: 
7
3
75
```

### Beginner-friendly version — the enhanced `for` loop

```java
int[] numbers = {7, 3, 6, 334, 75, 78};

System.out.println("Even numbers in the array: ");
for (int number : numbers) {
    if (number % 2 == 0) {
        System.out.println(number);
    }
}

System.out.println("Odd numbers in the array: ");
for (int number : numbers) {
    if (number % 2 != 0) {
        System.out.println(number);
    }
}
```

Read `for (int number : numbers)` as "for each `number` in `numbers`".

> [!important] Why this version is genuinely better here
> **The off-by-one bug becomes structurally impossible.**
>
> There is no index, no `length`, no `- 1`, and therefore nothing to get wrong. The enhanced `for` always visits exactly every element.
>
> The rule: **if you do not need the index, do not create one.** This program never uses `i` for anything except `arreo[i]` — it only ever needs the values. That is precisely the situation the enhanced `for` was designed for.
>
> You still need the indexed form when you must know *where* you are (comparing `arr[i]` with `arr[j]`, as in [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]]) or when you must write back into the array.

### More efficient version — one loop with `if / else`

```java
int[] numbers = {7, 3, 6, 334, 75, 78};

for (int number : numbers) {
    if (number % 2 == 0) {
        System.out.println(number + " is even");
    } else {
        System.out.println(number + " is odd");
    }
}
```

Output:

```text
7 is odd
3 is odd
6 is even
334 is even
75 is odd
78 is even
```

One pass instead of two. But note the trade-off: the output is now **interleaved** rather than grouped. If the requirement is "print all evens, then all odds", the two-loop version is correct and this one is not.

> [!tip] Efficiency is not automatically better
> Halving the number of passes sounds like a win, but it changes the output format. **The requirement decides, not the performance.**
>
> Also note that `else` is the right tool for the second branch here: a number that is not even *must* be odd, so re-testing with `% 2 != 0` would be redundant. `else` says "everything that failed the first test" with no extra computation.

### Grouped output with a single pass

If you want grouped output *and* one pass, collect first and print after:

```java
import java.util.ArrayList;
import java.util.List;

int[] numbers = {7, 3, 6, 334, 75, 78};
List<Integer> evens = new ArrayList<>();
List<Integer> odds  = new ArrayList<>();

for (int number : numbers) {
    if (number % 2 == 0) {
        evens.add(number);
    } else {
        odds.add(number);
    }
}

System.out.println("Even numbers: " + evens);
System.out.println("Odd numbers:  " + odds);
```

Output:

```text
Even numbers: [6, 334, 78]
Odd numbers:  [7, 3, 75]
```

A `List` is used rather than an array because we do not know in advance how many evens there will be — and unlike an array, a `List` grows as you add.

### Concise version — streams

```java
import java.util.Arrays;

int[] numbers = {7, 3, 6, 334, 75, 78};

System.out.println("Even numbers in the array: ");
Arrays.stream(numbers).filter(n -> n % 2 == 0).forEach(System.out::println);

System.out.println("Odd numbers in the array: ");
Arrays.stream(numbers).filter(n -> n % 2 != 0).forEach(System.out::println);
```

Note that the method is literally called `filter` — the standard library uses the same vocabulary as the pattern. Recognising your problem as "a filter" leads you straight to the right API.

Show the loop first in an interview; mention streams afterwards.

### A version that is correct for negative numbers

```java
for (int number : numbers) {
    if (number % 2 == 0) {
        System.out.println(number + " is even");
    } else {
        System.out.println(number + " is odd");   // catches -7 correctly
    }
}
```

Using `% 2 == 0` as the primary test with `else` for the remainder is automatically negative-safe, because it never relies on the remainder being exactly `1`.

---

# 12. Common beginner mistakes

## Mistake 1 — `i < length - 1` (the bug in this program)

**Incorrect code**

```java
for (int i = 0; i < arreo.length - 1; i++) {
```

**Why it is wrong**
Subtracts one from the range twice: once via `<` and once via `- 1`. The last element is never visited.

**What Java does**
Nothing. No error, no warning. The program runs and prints an incomplete answer.

**Correct code**

```java
for (int i = 0; i < arreo.length; i++) {          // preferred
for (int i = 0; i <= arreo.length - 1; i++) {     // also correct
```

**How to recognise it in future**
Memorise the pair: **`<` goes with `length`; `<=` goes with `length - 1`.** Seeing `<` and `- 1` together should trigger suspicion immediately.

And verify by counting: if a program partitions data, the output counts must sum to the input count.

---

## Mistake 2 — `i <= length` (crashing off-by-one)

**Incorrect code**

```java
for (int i = 0; i <= arreo.length; i++) {
```

**Why it is wrong**
When `i` reaches `6`, `arreo[6]` does not exist.

**What Java does**

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException:
    Index 6 out of bounds for length 6
```

**Correct code**

```java
for (int i = 0; i < arreo.length; i++) {
```

**How to recognise it**
The message names the bad index and the real length. If the index equals the length exactly, you went one too far.

---

## Mistake 3 — using `=` instead of `==`

**Incorrect code**

```java
if (arreo[i] % 2 = 0) {
```

**Why it is wrong**
`=` assigns; `arreo[i] % 2` is a computed value, not a storage location.

**What Java does**
Compile error: `unexpected type: required variable, found value`.

**Correct code**

```java
if (arreo[i] % 2 == 0) {
```

**How to recognise it**
Say it aloud: "is it equal" → `==`; "make it equal" → `=`.

---

## Mistake 4 — using `/` instead of `%`

**Incorrect code**

```java
if (arreo[i] / 2 == 0) {
```

**Why it is wrong**
`/` gives the quotient, not the remainder. `arreo[i] / 2 == 0` is only true for `0` and `1` — it tests "is this number less than 2", not "is it even".

Trace it: `6 / 2` is `3`, not `0`, so `6` would not be reported as even.

**Correct code**

```java
if (arreo[i] % 2 == 0) {
```

**How to recognise it**
If almost nothing matches your divisibility test, check whether you typed `/` for `%`. Remember: `/` = how many times it fits; `%` = what is left over.

---

## Mistake 5 — putting the print outside the `if`

**Incorrect code**

```java
for (int i = 0; i < arreo.length; i++) {
    if (arreo[i] % 2 == 0) {
    }
    System.out.println(arreo[i]);      // ← outside the if
}
```

**Why it is wrong**
Prints every element, not just the evens. The `if` does nothing at all.

**Correct code**
Put the print inside the `if` block.

**How to recognise it**
If your filter appears to print everything, check the braces. Indentation can lie; braces cannot.

---

## Mistake 6 — putting the header inside the loop

**Incorrect code**

```java
for (int i = 0; i < arreo.length; i++) {
    System.out.println("Even numbers in the array: ");
    if (arreo[i] % 2 == 0) {
        System.out.println(arreo[i]);
    }
}
```

**Why it is wrong**
The header prints once per element — six times.

**Correct code**
Put the header before the loop.

**How to recognise it**
A repeated header means a print statement is inside a loop that it should be outside.

---

## Mistake 7 — testing odd with `% 2 == 1`

**Incorrect code**

```java
if (arreo[i] % 2 == 1) {     // fails for negative numbers
```

**Why it is wrong**
`-7 % 2` is `-1` in Java, not `1`. Negative odd numbers would be missed entirely.

**Correct code**

```java
if (arreo[i] % 2 != 0) {
```

**How to recognise it**
Whenever you compare a remainder to a specific non-zero value, ask what happens if the input is negative. Comparing to `0` is always safe.

---

## Mistake 8 — a stray semicolon after the `for` or `if`

**Incorrect code**

```java
for (int i = 0; i < arreo.length; i++);
{
    if (arreo[i] % 2 == 0) { ... }
}
```

or:

```java
if (arreo[i] % 2 == 0);
{
    System.out.println(arreo[i]);
}
```

**Why it is wrong**
The `;` becomes an empty body. In the `if` case, the block below then runs **unconditionally** — printing every element.

**Correct code**
No semicolon after a `for` or `if` header.

**How to recognise it**
If a condition seems to have no effect, look for a semicolon right after it. IntelliJ flags this with a warning about an empty statement.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| Modulo understanding | do you know `%` gives the remainder, and why that means "even"? |
| Loop bounds | can you spot the off-by-one? |
| Filtering | do you know the loop-plus-condition pattern? |
| Operator precedence | do you know `%` binds tighter than `==`? |
| Negative-number awareness | do you know `-7 % 2` is `-1`? |
| Code review | **can you find the bug in code you are shown?** |

> [!tip]
> That last row matters. Interviewers frequently show candidates buggy code and ask "what does this print?" This exact program — with `length - 1` — is a textbook example. Being able to trace it and say *"78 will be missing, because the loop stops at index 4"* is a strong signal.

## Likely follow-up questions

> **"What does this program actually print?"**

Trace it. Evens: `6, 334`. Odds: `7, 3, 75`. Note that `78` appears in neither, because of the `length - 1` bound.

> **"Is there a bug? Where?"**

Yes — `i < arreo.length - 1` skips the last element. The fix is `i < arreo.length`.

> **"Why `% 2 != 0` rather than `% 2 == 1` for odd?"**

Because Java's `%` keeps the sign of the left operand, so `-7 % 2` is `-1`, not `1`. Comparing to `0` is correct for all integers.

> **"Can you do this in one loop?"**

Yes, with `if / else` — but the output becomes interleaved instead of grouped. Which is right depends on the requirement.

> **"What is the time complexity?"**

`O(n)` — two separate passes, so `2n` operations, which is still linear. Explain why the constant does not change the classification.

> **"What if the array is empty?"**

Both loops' conditions are false immediately, so only the two headers print. No crash. Note that with the buggy `length - 1`, an empty array gives `0 < -1` — still false, still safe.

> **"What if the array has one element?"**

**With the bug, nothing is printed at all.** `length - 1` is `0`, so `0 < 0` is false and the loop never runs. This edge case exposes the bug dramatically.

> **"How would you count evens instead of printing them?"**

Replace the print with `count++` and declare `count` before the loop — combining filter with accumulator. Tests whether you can compose patterns.

> **"How would you check divisibility by 3?"**

`n % 3 == 0`. Tests whether you generalised the pattern or memorised the `2`.

---

# 14. Complexity

## Time complexity: `O(n)`

Let `n` be the number of elements.

Each loop visits about `n` elements, doing constant work per element (one array read, one modulo, one comparison, sometimes one print). There are two loops, so the total is roughly `2n` operations.

In beginner language:

> "Each loop looks at every number once, and there are two loops — so with 6 numbers the program does about 12 checks, and with 600 numbers about 1,200. Double the array and you double the work. Because the work grows in direct proportion to the number of elements, we call it linear time and write it `O(n)`."

> [!note] Why `2n` is still `O(n)`
> Big-O describes the **shape** of the growth, not the exact count. Both `n` and `2n` are straight lines — one is simply steeper. Constant multipliers are dropped.
>
> What *would* change the classification is a **nested** loop, where each element is compared against every other. That grows as `n × n` — `O(n²)` — and you can see it in [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]].
>
> Merging the two loops into one halves the real work but leaves the complexity at `O(n)`. That is worth saying in an interview: *"it is a genuine 2× improvement, but not an asymptotic one."*

## Space complexity: `O(1)`

The program allocates only the two counters. It prints results rather than storing them, so no memory grows with the input.

> "We use the same handful of variables whether the array has 6 elements or 6 million, so the extra memory is constant: `O(1)`."

The `List`-collecting version in section 11 would be `O(n)` space instead, since it stores every element. **Printing costs nothing; collecting costs memory** — a genuine trade-off to be aware of.

---

# 15. Edge cases

## Empty array

```java
int[] arreo = {};        // length 0
```

- `arreo.length - 1` is `-1`.
- Condition `0 < -1` is `false` immediately.
- Neither loop body runs.
- Output: just the two headers.

No crash. Correct behaviour, essentially by accident.

## Single element — where the bug becomes obvious

```java
int[] arreo = {6};       // length 1
```

- `arreo.length - 1` is `0`.
- Condition `0 < 0` is `false`.
- **Neither loop runs. Nothing is printed at all.**

> [!warning] The bug's worst case
> With a one-element array, the program classifies **nothing**. The single number vanishes entirely.
>
> This is the cleanest way to demonstrate the bug to someone. A six-element array losing one value might be missed; a one-element array producing empty output is unmistakable.
>
> **Testing with the smallest possible input is one of the most effective bug-finding techniques there is.**

With the fix (`i < arreo.length`), the output is correctly `6` under "Even numbers".

## Two elements

```java
int[] arreo = {7, 6};
```

Buggy version visits only index `0` → prints `7` as odd, misses `6`. Fixed version prints both.

## All even

```java
int[] arreo = {2, 4, 6, 8};
```

Buggy version prints `2, 4, 6` as even (missing `8`), and nothing under odd. The "Odd numbers" header still prints with nothing beneath it — a header with no content is normal and not an error.

## All odd

Symmetric. The "Even numbers" header prints with nothing beneath it.

## Negative numbers

```java
int[] arreo = {-7, -6, 3};
```

```text
-7 % 2  →  -1   →  != 0  →  odd    ✓
-6 % 2  →   0   →  == 0  →  even   ✓
```

Works correctly, precisely because the tests compare against `0` rather than `1`. Had the odd test been `% 2 == 1`, `-7` would have been classified as neither.

## Zero

```java
int[] arreo = {0, 1};
```

`0 % 2` is `0`, so zero is classified as **even**. Mathematically correct — zero is an even number.

## Very large values

```java
int[] arreo = {2147483647};       // Integer.MAX_VALUE
```

`2147483647 % 2` is `1` → odd. Correct. **`%` never overflows**, because the remainder is always smaller in magnitude than the divisor. No risk here.

## Can it crash?

Not as written. The loop bound is too *small*, never too large, so `ArrayIndexOutOfBoundsException` is impossible. `%` by a literal `2` can never divide by zero. The array is initialised at declaration, so it cannot be `null`.

> [!important]
> This program's failure mode is **silent incompleteness**, not an exception. It always runs and always finishes — it just quietly leaves data out.
>
> That is worth internalising: **"it ran without errors" is not the same as "it is correct."** Always check the output against what you expected, by hand, on a small example.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Print the even numbers in a list, then the odd numbers.

        ↓

What data do I have?
Six whole numbers that belong together.

        ↓

What holds many values of one type?
An array. Elements are whole numbers → int[]

        ↓

What do I need to produce?
Not one value — a SELECTION of the input.

        ↓

What shape is that?
Many in, some out → a FILTER.
Filters need a loop plus a condition,
but no accumulator.

        ↓

How do I visit every element?
A for loop over the indexes.

        ↓

Where do indexes start?
i is a POSITION here, and arrays are
zero-indexed → i = 0

        ↓

Where do they end?
The last valid index is length - 1 = 5.
Index 5 must be INCLUDED.
  i <= length - 1     ✓
  i <  length         ✓
  i <  length - 1     ✗  ← the mistake made here

        ↓

How do I test whether a number is even?
"Even" = divisible by 2
"Divisible" = leaves no remainder
Java's remainder operator is %
→  arreo[i] % 2 == 0

        ↓

Do I need parentheses?
% binds tighter than ==, so
arreo[i] % 2 == 0 already means
(arreo[i] % 2) == 0.  No parentheses needed.

        ↓

What do I do when the test passes?
Print the element — INSIDE the if block,
so only matching elements print.

        ↓

How do I test for odd?
Odd is "not even" → % 2 != 0
(not == 1, which breaks on negatives)

        ↓

Do I need one loop or two?
The requirement says print all evens,
THEN all odds — grouped.
One loop with if/else would interleave them.
→ two loops.

        ↓

Where do the headers go?
Outside the loops, so they print once each.

        ↓

Verify by tracing:
evens → 6, 334        ...and 78?
odds  → 7, 3, 75
2 + 3 = 5, but the array has 6 elements.
        ↓
The counts do not add up → there IS a bug.
        ↓
Find it: i < length - 1 stops at index 4.
        ↓
Fix it: i < length
```

> [!important] The habit that catches this bug
> Notice the second-to-last step. The bug was not found by staring at the loop — it was found by **checking that the output counts add up to the input count**.
>
> Whenever a program splits data into groups, the group sizes must sum to the original. That one arithmetic check catches an entire family of off-by-one bugs, and it takes five seconds.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int[] arreo = new int[]{7, 3, 6, 334, 75, 78};` | "Create a row of six boxes holding whole numbers, fill them with these six values, and let `arreo` point at that row." |
| `System.out.println("Even numbers in the array: ");` | "Print this heading once, before the list starts." |
| `for (int i = 0; i < arreo.length - 1; i++)` | "Start a counter at 0. Keep going while the counter is below *one less than* the number of boxes — which means the last box is never reached. After each pass, add 1." |
| `if (arreo[i] % 2 == 0)` | "Divide the number in box `i` by 2 and look at what is left over. If nothing is left over, the number is even." |
| `System.out.println(arreo[i]);` | "Print the number in box `i` — but only because we are inside the `if`, so only for numbers that passed the test." |
| `if (arreo[i] % 2 != 0)` | "If dividing by 2 *does* leave something over, the number is odd." |

The first loop in one sentence:

> **"Walk from the first box up to (but not including) the last box, and print each number that divides evenly by 2."**

And the whole program:

> **"Take six numbers. Walk through all but the last one and print the even ones under a heading, then walk through all but the last one again and print the odd ones under a second heading."**

Notice how the phrase **"all but the last one"** appears naturally in the plain-English translation. That is the bug, made obvious simply by saying the code out loud. **Translating code into English is a debugging technique, not just a comprehension exercise.**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| array | fixed-size, ordered, same-type collection, indexed `0` to `length-1` |
| `%` (modulo) | the remainder after division — the tool for divisibility tests |
| `/` (integer division) | the quotient, with any fractional part discarded |
| filter | loop over everything, act only on items passing a test |
| `if` statement | performs an action conditionally; requires a `boolean` |
| `boolean` | exactly two values: `true` and `false` |
| operator precedence | `%` binds tighter than `==`, which binds tighter than `=` |
| off-by-one | `<` with `- 1` skips the last element — **silently** |
| scope | a counter declared in a `for` header dies with that loop |

### Important methods

| Member | Owner | Accepts | Returns |
|---|---|---|---|
| `length` | array | — (a **field**, no parentheses) | `int` |
| `println(int)` | `java.io.PrintStream` | an `int` | `void` |
| `println(String)` | `java.io.PrintStream` | text | `void` |
| `Math.floorMod(int, int)` | `java.lang.Math` | two `int`s | `int`, sign of the divisor |

There is deliberately **no** `isEven()` method — the `%` operator already is the tool.

### Important syntax

| Syntax | Meaning |
|---|---|
| `int[] arr` | a reference to an array of `int` |
| `new int[]{...}` | create an array with the listed contents |
| `arr[i]` | the element at position `i` |
| `arr.length` | how many elements — no parentheses |
| `%` | remainder |
| `/` | quotient (truncating for `int`) |
| `==` | equal to |
| `!=` | not equal to |
| `if (cond) { }` | run the block only when `cond` is `true` |
| `for (int x : arr)` | enhanced for — "for each element, no index" |
| `{ }` | a block — determines what is conditional and what is repeated |

### Main interview concept

> **`n % 2 == 0` tests for even, and you should be able to derive it, not recall it:** even means divisible by 2, divisible means no remainder, and `%` is the remainder operator. Use `!= 0` rather than `== 1` for odd, because Java's `%` keeps the sign of the left operand and `-7 % 2` is `-1`.

### Main lesson for code reading

> **Check the loop bounds before you trust the output.**
>
> ```text
> i < arr.length            ✓ visits every element
> i <= arr.length - 1       ✓ visits every element
> i < arr.length - 1        ✗ silently skips the last     ← this program
> i <= arr.length           ✗ crashes
> ```
>
> Two of these four are wrong, and one of the wrong ones **fails silently**. When a program partitions data, verify that the output counts sum to the input count — that single arithmetic check catches this entire family of bugs.

---

### Related notes

- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — the same loop shape with correct bounds, plus the accumulator pattern
- [[Java_PrimeOrNot_Code_Reading_Explanation_Obsidian|PrimeOrNot]] — `%` used to count divisors
- [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]] — `% 10` and `/ 10` used to peel digits off a number
- [[Java_TernaryOperatorTime_Code_Reading_Explanation_Obsidian|TernaryOperatorTime]] — `if` as a statement versus `? :` as an expression
- [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]] — nested loops and `O(n²)` growth
