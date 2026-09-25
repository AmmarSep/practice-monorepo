---
title: Java Code Reading — FabionacciSeries
tags:
  - java
  - interview-programs
  - code-reading
  - loops
  - math
aliases:
  - FabionacciSeries Explained
  - Fibonacci Series in Java
---

# Java Code Reading — FabionacciSeries Explained for a Fresh Java Programmer

> [!note]
> This program carries **two** running values that leapfrog each other, and the order in which they are updated decides whether the program works at all. If you have read [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]], you already know the principle: *assignment overwrites, so save a value before you destroy it.*
>
> The program prints `1 1 2 3 5 8 13 21 34 55` — the Fibonacci series starting from `1`. The initial `0` is computed but never printed. That is not a bug so much as a consequence of where the print statement sits, and understanding exactly why is the most valuable thing in this note.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → How many values must I remember at once? → In what order must they update? → Where does the print go?**

> [!warning] About the file name
> The file is `FabionacciSeries.java` and the class is `FabionacciSeries` — the standard spelling is **Fibonacci**. The name is misspelled in the repository, and Java requires the class name to match the file name exactly, so this note keeps the spelling as-is rather than silently correcting it. If you rename the file, rename the class in the same commit.

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class FabionacciSeries {

        public static void main(String[] args) {
            // Initialize the first two numbers of the series
            int number1 = 0;
            int number2 = 1;

            // Define the count of numbers to be printed
            int count = 10;

            // Print a message indicating the start of the Fibonacci series
            System.out.println("The Fibonacci series is as follows:");

            // Generate and print the Fibonacci series
            for (int i = 0; i < count; i++) {
                // Calculate the next number in the series
                int number3 = number1 + number2;

                // Update the values of number1 and number2 for the next iteration
                number1 = number2;
                number2 = number3;

                // Print the current number in the series
                System.out.println(number1);
            }
        }
    }
```

The executable logic:

```java
int number1 = 0;
int number2 = 1;
int count = 10;

for (int i = 0; i < count; i++) {
    int number3 = number1 + number2;
    number1 = number2;
    number2 = number3;
    System.out.println(number1);
}
```

Four lines inside the loop, and the **order of those four lines is the entire algorithm**.

---

# 2. What problem is this program solving?

In plain language:

> Print the first ten numbers of the Fibonacci series.

### What is the Fibonacci series?

A sequence where **each number is the sum of the two before it**:

```text
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
│  │  │  │  │
│  │  │  │  └── 1 + 2
│  │  │  └───── 1 + 1
│  │  └──────── 0 + 1
│  └─────────── given
└────────────── given
```

The first two numbers are **given** (`0` and `1`) because there is nothing before them to add. Every later number is computed.

### What do we know?

- The rule: `next = previous + the one before that`.
- The two starting values: `0` and `1`.
- How many numbers to print: `10`.

### What do we need?

- Ten numbers, printed in order.

### What transformations are required?

This is **not** a reduction — we are not collapsing many values into one. We are **generating** a sequence.

```text
REDUCTION (SumOfArrayEle)      GENERATION (Fibonacci)
─────────────────────────      ─────────────────────────
many values → one value        a rule → many values
needs an accumulator           needs STATE
```

### The key question: how much must you remember?

This is the insight that drives the whole design.

> To compute the next number I need the **two** before it. So at any moment I must be holding two values — no more, no fewer.

- One variable is not enough: you cannot add "the two previous" if you only kept one.
- Three variables are unnecessary: the rule only reaches back two steps.

> [!important] "How much state do I need?" is the design question
> Every generating loop needs enough remembered values to apply its rule, and no more.
>
> | Rule | State needed |
> |---|---|
> | `next = previous + 1` (counting) | 1 value |
> | `next = previous × 2` (doubling) | 1 value |
> | **`next = prev + prevprev` (Fibonacci)** | **2 values** |
> | `next = sum of previous three` (tribonacci) | 3 values |
>
> Ask *"how far back does my rule reach?"* and the answer is exactly how many variables you need. That question generalises to far more than Fibonacci.

And because two values must move forward together, **the order of their updates matters** — which is where `number3` comes in.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder. First statement in the file.

## `public class FabionacciSeries`

- `public` — usable from anywhere.
- `class` — declares a class.
- `FabionacciSeries` — `PascalCase`, matching the (misspelled) file name.

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

`int`, `String`, `System` — primitives and `java.lang`, so no `import`.

## Variables

| Variable | Type | Role | Declared where |
|---|---|---|---|
| `number1` | `int` | state — the older of the two remembered values | before the loop |
| `number2` | `int` | state — the newer of the two | before the loop |
| `count` | `int` | how many numbers to print | before the loop |
| `i` | `int` | the counter | in the loop header |
| `number3` | `int` | **temporary** — the newly computed value | **inside** the loop |

> [!important] Note where each variable is declared, and why
> - `number1` and `number2` are declared **before** the loop because they must **survive from one pass to the next**. They carry the state forward.
> - `number3` is declared **inside** the loop because it is needed only within a single pass. It is created fresh each iteration and discarded at the end of it.
>
> **A variable's declaration position tells you its lifetime, and its lifetime tells you its role.** Reading where things are declared is a fast way to understand an unfamiliar loop before reading its body.

---

# 4. Line-by-line explanation

## 4.1 — `int number1 = 0;` and `int number2 = 1;`

```java
int number1 = 0;
int number2 = 1;
```

**What is this?**
The two seed values of the series.

**Why do we need them?**
The Fibonacci rule computes each number from the two before it — but the first two numbers have nothing before them. They must be **given**, not computed. `0` and `1` are the standard starting pair.

**How would a beginner know to write them?**
Ask: **"Does my rule work for the very first item?"** Here it does not — there is no "two before" for the first number. Whenever a rule cannot produce the start, the start must be supplied as data.

**What type and why?**
Whole numbers, added together, so `int`. `double` would introduce needless rounding; `String` cannot be added arithmetically.

> [!note] Why `int` is the risky choice here
> Fibonacci numbers grow **exponentially** — roughly 1.618× each step. `int` maxes out at `2,147,483,647`, which the series passes at the 47th term. For 10 terms this is completely safe, but see section 15.

**Why the names `number1` and `number2`?**
Honest but not descriptive — they say "there are two numbers" and nothing about their roles. Better names would state the meaning:

```java
int previous = 0;
int current = 1;
```

or

```java
int a = 0, b = 1;      // conventional in mathematical code
```

`previous` / `current` makes the update lines almost self-explanatory, as section 11 shows.

**What does Java do?**
Reserves two `int` slots and writes `0` and `1` into them.

---

## 4.2 — `int count = 10;`

```java
int count = 10;
```

**What is this?**
How many numbers to print.

**Why do we need it?**
The Fibonacci series is infinite. Something must decide when to stop, and that decision is data, not logic.

**How would a beginner know to write it?**
Ask: "What does my loop's stopping condition depend on?" It depends on how many numbers we want. Anything a decision depends on should be a named variable rather than a number buried in the loop header — otherwise changing it means hunting through the code.

**Why the name `count`?**
Good — it states exactly what the value means. `terms` or `howMany` would also work.

---

## 4.3 — `System.out.println("The Fibonacci series is as follows:");`

```java
System.out.println("The Fibonacci series is as follows:");
```

A header, printed **once**, outside the loop. Inside the loop it would repeat ten times. **Position relative to `{ }` decides how often something runs.**

---

## 4.4 — `for (int i = 0; i < count; i++) {`

```java
for (int i = 0; i < count; i++) {
```

**What is this?**
A loop that runs exactly `count` times.

**The three sections**

```text
for ( int i = 0 ; i < count ; i++ )
         │           │         │
         │           │         └── after each pass
         │           └──────────── before each pass
         └──────────────────────── once, at the start
```

**Why does `i` start at `0` and use `<`?**

Here `i` is **neither an index nor a value in the series** — it is purely a **repetition counter**. Its only job is to make the loop run ten times.

```text
i = 0,1,2,3,4,5,6,7,8,9   →  ten passes  ✓
```

> [!important] Three different meanings of `i`
> You have now seen all three, and confusing them causes real bugs:
>
> | Meaning | Example | Starts at | Condition |
> |---|---|---|---|
> | **position** in a structure | [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian\|SumOfArrayEle]] — `arr[i]` | `0` | `i < length` |
> | **value** used in arithmetic | [[Java_Factorials_Code_Reading_Explanation_Obsidian\|Factorials]] — `factorial * i` | `1` | `i <= n` |
> | **repetition count** | **this program** — `i` is never used in the body | `0` | `i < count` |
>
> The tell for the third kind: **`i` does not appear anywhere inside the loop body.** When that is true, the counter is just a tally, and `0` to `count-1` is the natural way to count `count` times.

**Why `i < count` and not `i <= count`?**

```text
i < 10   →  0,1,2,...,9   →  ten passes    ✓
i <= 10  →  0,1,2,...,10  →  eleven passes ✗
```

Counting from `0`, you reach `count` items when the counter has taken values `0` through `count-1`.

**What is `i++`?**
The increment operator. Without it the loop never terminates.

---

## 4.5 — `int number3 = number1 + number2;`

```java
int number3 = number1 + number2;
```

**What is this?**
Computing the next Fibonacci number, and parking it in a temporary variable.

**Why do we need it?**
This line *is* the Fibonacci rule: the next number is the sum of the two before it.

**But why store it in a third variable rather than assigning directly?**

This is the crux of the program, and it is the same problem as swapping two values.

We need both updates to happen:

```text
number1 should become number2's value
number2 should become the sum
```

Try it without a temporary:

```java
number1 = number2;                  // number1 = 1. The old number1 (0) is GONE.
number2 = number1 + number2;        // ✗ number1 is no longer the old value!
```

Trace that with `number1 = 0`, `number2 = 1`:

```text
number1 = number2         →  number1 = 1, number2 = 1
number2 = number1 + number2  →  number2 = 1 + 1 = 2
```

The sum used the *new* `number1`, not the old one. The series would be wrong.

> [!important] Same lesson, different program
> **Assignment overwrites. Overwriting destroys. So compute what you need from the old values *before* you overwrite them.**
>
> ```text
> SwapNumbers                 FabionacciSeries
> ───────────────────         ─────────────────────────────
> int temp = a;               int number3 = number1 + number2;
> a = b;                      number1 = number2;
> b = temp;                   number2 = number3;
>       ↑                                ↑
>   save first                    compute first
> ```
>
> `number3` plays exactly the role `temp` plays in a swap: it holds a value computed from the old state, so the old state can safely be overwritten.
>
> The same principle drove `secondLargest = largest;` in [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]]. **Three different programs, one idea.**

**Why is `number3` declared inside the loop?**
Because it is needed only within a single pass. Declaring it inside makes that lifetime explicit — and means the next pass starts with a clean slate.

Declaring it outside would also work, but would falsely suggest the value carries forward.

**What type?**
`int + int` produces an `int`, so `number3` is an `int`.

**Why the name `number3`?**
Weak. `next` or `sum` would say what it is. `number3` only says "a third number exists".

---

## 4.6 — `number1 = number2;` and `number2 = number3;`

```java
number1 = number2;
number2 = number3;
```

**What is this?**
Sliding the two-number window forward by one position.

**Why do we need it?**
On the next pass, "the two previous numbers" will be different. The pair must shift:

```text
BEFORE:   number1 = 0,  number2 = 1,  number3 = 1
                  ↓ shift left ↓
AFTER:    number1 = 1,  number2 = 1
```

Everything moves one step toward the future. The oldest value falls off the end and is forgotten.

**Why this order?**

```java
number1 = number2;      // move the newer value into the older slot
number2 = number3;      // move the newly computed value into the newer slot
```

Reverse them and it breaks:

```java
number2 = number3;      // ✗ number2's old value is destroyed
number1 = number2;      // ✗ number1 now gets number3, not the old number2
```

Trace with `number1=0, number2=1, number3=1`:

```text
CORRECT ORDER                  WRONG ORDER
number1 = number2  →  1        number2 = number3  →  1
number2 = number3  →  1        number1 = number2  →  1   (should be 1 here too,
                                                          but diverges later)
```

At the very first step both happen to give the same answer — which is exactly what makes this bug dangerous. Trace one more step and they separate.

> [!tip] The rule for shifting a window
> **Always shift from the oldest slot toward the newest.** Each assignment must read a slot that has not yet been overwritten this pass.
>
> ```text
> number1 ◀── number2 ◀── number3
>   (1st)       (2nd)
> ```
> Reading right to left, writing left to right.

---

## 4.7 — `System.out.println(number1);`

```java
System.out.println(number1);
```

**What is this?**
Printing one term of the series.

**Why is it inside the loop?**
Because a number must be printed on every pass. Outside, only the final value would appear.

**Why print `number1` and not `number2` or `number3`?**

This is the subtle part, and it determines exactly which numbers appear.

At the point where the print runs, the shift has already happened:

```text
pass 1:  number3 = 0 + 1 = 1
         number1 = 1        ← was number2 (the value 1)
         number2 = 1        ← was number3
         print number1 → 1
```

So `number1` holds the value that was `number2` *before* the shift.

> [!warning] The initial `0` is never printed
> Trace the very first pass carefully:
>
> ```text
> before: number1 = 0,  number2 = 1
> compute: number3 = 0 + 1 = 1
> shift:   number1 = 1    ← the 0 is overwritten here, unprinted
>          number2 = 1
> print:   1
> ```
>
> The `0` is **used** (it contributes to the first sum) but **overwritten before any print happens**. So the output begins at `1`:
>
> ```text
> printed:  1  1  2  3  5  8  13  21  34  55
> true series: 0  1  1  2  3  5   8  13  21  34  55
>              ↑
>           missing
> ```
>
> Is this a bug? It depends on the definition you are using. Some texts start Fibonacci at `0`, others at `1`. The program prints ten valid Fibonacci numbers — just not starting from `0`.
>
> **The fix is to move the print, not to change the arithmetic:**
> ```java
> for (int i = 0; i < count; i++) {
>     System.out.println(number1);        // print BEFORE shifting
>     int number3 = number1 + number2;
>     number1 = number2;
>     number2 = number3;
> }
> // prints 0 1 1 2 3 5 8 13 21 34
> ```
>
> **Where a print sits relative to the state update decides which values you see.** That is worth remembering far beyond this program.

---

# 5. How to think like the programmer

```text
Requirement
"Print the first ten Fibonacci numbers"
        ↓
What is the rule?
Each number is the sum of the two before it
        ↓
How far back does the rule reach?
TWO steps
        ↓
Therefore I must remember TWO values at all times
→ int number1, int number2
        ↓
Can the rule produce the first numbers?
No — nothing comes before them.
So they must be GIVEN: 0 and 1
        ↓
How many numbers do I want?
Ten → int count = 10
        ↓
How do I repeat ten times?
for (int i = 0; i < count; i++)
Here i is just a tally — it never appears in the body
        ↓
Inside the loop, what must happen?
(a) compute the next number
(b) slide the window forward
(c) print
        ↓
(a) next = number1 + number2
        ↓
(b) Careful — sliding overwrites.
If I assign number1 = number2 first,
the old number1 is gone and the sum is wrong.
        ↓
So compute FIRST, into a temporary:
int number3 = number1 + number2;
then shift:
number1 = number2;
number2 = number3;
        ↓
Which slot must be written first?
The oldest → number1, then number2.
Each write must read a slot not yet overwritten.
        ↓
(c) Where does the print go?
After the shift → the series starts at 1
Before the shift → the series starts at 0
        ↓
Trace to verify:
0,1 → print 1
1,1 → print 1
1,2 → print 2
2,3 → print 3   ✓ Fibonacci
```

> [!important] The generating-loop skeleton
> ```java
> TYPE state1 = SEED1;                    // enough state for the rule
> TYPE state2 = SEED2;
>
> for (int i = 0; i < count; i++) {
>     TYPE next = RULE(state1, state2);   // compute from OLD state
>     state1 = state2;                    // shift oldest first
>     state2 = next;
>     print(...);                         // position decides what you see
> }
> ```
>
> | Sequence | State | Rule |
> |---|---|---|
> | counting `1,2,3...` | 1 value | `next = s + 1` |
> | doubling `1,2,4,8...` | 1 value | `next = s * 2` |
> | **Fibonacci** | **2 values** | **`next = s1 + s2`** |
> | tribonacci | 3 values | `next = s1 + s2 + s3` |
> | Lucas numbers `2,1,3,4,7...` | 2 values | same rule, different seeds |
>
> Notice the last row: **Lucas numbers use the identical code with seeds `2` and `1`.** The rule and the seeds are separate decisions.

---

# 6. Deep explanation of important Java concepts used

## State variables

A **state variable** carries information from one loop iteration to the next. It must be declared **outside** the loop.

```java
int number1 = 0;                    // state — survives every pass
int number2 = 1;                    // state

for (...) {
    int number3 = number1 + number2;  // temporary — dies each pass
}
```

| | State | Temporary |
|---|---|---|
| declared | before the loop | inside the loop |
| lifetime | the whole loop | one iteration |
| purpose | carry information forward | hold an intermediate result |
| examples here | `number1`, `number2` | `number3` |

## Why a temporary is needed

Whenever two or more variables must update **simultaneously** but Java can only assign one at a time, you need somewhere to park a value computed from the old state.

```text
What we WANT (conceptually simultaneous):
    number1 ← number2
    number2 ← number1 + number2      (using the OLD number1)

What Java gives us (strictly sequential):
    one assignment at a time, each seeing the effects of the last

The bridge: compute the new value FIRST, store it, then assign.
```

This is the same pattern as:

| Program | The temporary | What it saves |
|---|---|---|
| [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian\|SwapNumbers]] | `temp` | `a`'s old value |
| [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian\|SecondLargestArray]] | `secondLargest` | the old leader |
| **FabionacciSeries** | `number3` | the sum of the old pair |

## The `for` loop as a repetition counter

```java
for (int i = 0; i < count; i++) { ... }
```

When `i` never appears in the body, the loop is purely "do this `count` times". `0` to `count-1` is the idiomatic way to express that.

## Integer overflow

`int` holds up to `2,147,483,647`. Fibonacci numbers grow by a factor of about 1.618 each step, so:

```text
F(46) = 1,836,311,903        ✓ fits
F(47) = 2,971,215,073        ✗ overflows — prints -1323752223
```

Java gives **no warning**. The value silently wraps around and goes negative.

> [!warning]
> A negative Fibonacci number is impossible mathematically, so **a negative result is proof of overflow**. That is a useful diagnostic: when a sequence that should only grow suddenly goes negative, you have overflowed.

## Variable naming and readability

Compare the same algorithm with two naming schemes:

```java
// as written
int number3 = number1 + number2;
number1 = number2;
number2 = number3;

// with meaningful names
int next = previous + current;
previous = current;
current = next;
```

The second version reads almost like the specification. `previous = current` is obviously "shift forward"; `number1 = number2` requires you to remember what `1` and `2` mean.

**Good names turn code into documentation.**

## Scope

```java
int number1 = 0;                          // scope: rest of main
for (int i = 0; i < count; i++) {         // i: this loop only
    int number3 = number1 + number2;      // number3: this iteration only
}
// number3 no longer exists
// i no longer exists
// number1, number2 still exist
```

Three different lifetimes in one small program, each matching what the variable is for.

---

# 7. Why this syntax?

## Order of the three assignment lines

```java
int number3 = number1 + number2;   // 1. compute from OLD state
number1 = number2;                 // 2. shift the older slot
number2 = number3;                 // 3. shift the newer slot
```

Any other order breaks it:

| Order | Result |
|---|---|
| compute, shift1, shift2 | ✓ correct |
| shift1, compute, shift2 | ✗ the sum uses the new `number1` |
| compute, shift2, shift1 | ✗ `number1` gets `number3` instead of the old `number2` |

## `i < count` vs `i <= count`

```text
i < 10   →  ten passes    ✓
i <= 10  →  eleven passes ✗
```

Starting at `0`, `count` items means indexes `0` to `count-1`.

## Print position

```java
// print AFTER the shift  (as written)
int number3 = number1 + number2;
number1 = number2;
number2 = number3;
System.out.println(number1);       // → 1 1 2 3 5 8 13 21 34 55

// print BEFORE the shift
System.out.println(number1);       // → 0 1 1 2 3 5 8 13 21 34
int number3 = number1 + number2;
number1 = number2;
number2 = number3;
```

Same arithmetic, different output. **The print position is a design decision, not an afterthought.**

## `=` vs `==`

`=` stores; `==` compares. This program uses only `=` — there is no comparison anywhere except the loop condition's `<`.

## `{ }` — scope and repetition

The loop body's braces do two jobs at once: they group the four statements that repeat, and they create the scope in which `number3` lives.

## `int` vs `long` vs `BigInteger`

| Type | Max Fibonacci term |
|---|---|
| `int` | F(46) |
| `long` | F(92) |
| `BigInteger` | unlimited |

For `count = 10`, `int` is fine. For `count = 50`, it is silently wrong.

---

# 8. Method discovery

## `println`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.io.PrintStream` |
| **Why can we call it?** | `System.out` is a `PrintStream` object |
| **Which overload runs?** | `println(int)` for the numbers, `println(String)` for the header |
| **Returns?** | `void` |

## Is there a built-in Fibonacci?

Type `Math.` in IntelliJ:

```text
abs()  max()  min()  pow()  sqrt()  floor()  ceil()  round()  random()  ...
```

No `fibonacci()`. There is no such method anywhere in the Java standard library.

> [!important] Why not, and what that tells you
> The same reasoning as `Math.factorial()` in [[Java_Factorials_Code_Reading_Explanation_Obsidian|Factorials]]:
>
> 1. It overflows quickly — an `int` version breaks at term 47.
> 2. Different definitions exist (does it start at `0` or `1`?), so no single implementation would satisfy everyone.
> 3. It is a four-line loop. Libraries exist for things that are hard, not things that are short.
>
> **When a method you expect does not exist, ask why.** The answer usually tells you something real about the problem — here, that the definition is ambiguous and the values overflow.

## Discovering the output-order issue

Notice that this is not an API problem at all. No amount of autocomplete would tell you that the `0` is missing. That required **tracing the code by hand**.

> [!tip] Two different skills
> | Problem | How you solve it |
> |---|---|
> | "which method reverses a string?" | autocomplete, Javadoc, search |
> | "why does my output start at 1?" | trace the state on paper |
>
> Method discovery handles *"what tool exists?"*. Tracing handles *"what does my code actually do?"* Both are essential, and neither substitutes for the other.
>
> A debugger does the tracing for you: set a breakpoint on the `println` line and watch `number1`, `number2`, `number3` change each pass. In IntelliJ that is a single click in the gutter — and it is the fastest way to understand any stateful loop.

## Discovering `BigInteger` for large terms

```text
"int overflows at term 47. I need bigger numbers."
        ↓
Search: "java arbitrary precision integer"
        ↓
java.math.BigInteger
        ↓
BigInteger.ZERO, BigInteger.ONE       ← constants for the seeds
bigInt.add(BigInteger) → BigInteger   ← note: RETURNS a new object
        ↓
Because BigInteger is immutable, the shift becomes:
    BigInteger next = a.add(b);
    a = b;
    b = next;
```

The structure is identical — only the type and the `+` change. That is a good sign that you understood the algorithm rather than the syntax.

---

# 9. Trace the program with real values

```text
Seeds:  number1 = 0,  number2 = 1
Count:  10   →  condition is  i < 10
```

## Iteration table

```text
─────────────────────────────────────────────────────────────────────────────
Pass  i   n1(in)  n2(in)   n3 = n1+n2   n1(out)  n2(out)   PRINTED
─────────────────────────────────────────────────────────────────────────────
 —    —     0       1          —           —        —         —      ← seeds
 1    0     0       1        0+1 = 1        1        1         1
 2    1     1       1        1+1 = 2        1        2         1
 3    2     1       2        1+2 = 3        2        3         2
 4    3     2       3        2+3 = 5        3        5         3
 5    4     3       5        3+5 = 8        5        8         5
 6    5     5       8       5+8 = 13        8       13         8
 7    6     8      13      8+13 = 21       13       21        13
 8    7    13      21     13+21 = 34       21       34        21
 9    8    21      34     21+34 = 55       34       55        34
10    9    34      55     34+55 = 89       55       89        55
 —   10     —       —          —           —        —         —      ← exit
─────────────────────────────────────────────────────────────────────────────
```

## The first pass, magnified

```text
START:    number1 = 0    number2 = 1

Step 1:   int number3 = number1 + number2
                      = 0 + 1
                      = 1
          state: n1=0, n2=1, n3=1

Step 2:   number1 = number2
                  = 1
          state: n1=1, n2=1, n3=1
                 ↑
            the 0 is destroyed HERE — and it was never printed

Step 3:   number2 = number3
                  = 1
          state: n1=1, n2=1

Step 4:   print number1  →  1
```

**The `0` contributed to the first sum and was then overwritten, one line before the print.** That is precisely why it never appears.

## Console output

```text
The Fibonacci series is as follows:
1
1
2
3
5
8
13
21
34
55
```

## Compared with the standard series

```text
standard:  0  1  1  2  3  5  8  13  21  34  55
printed:      1  1  2  3  5  8  13  21  34  55
           ↑
       missing
```

Ten valid Fibonacci numbers, just shifted one position along the sequence.

## Checking the arithmetic

Each printed number should be the sum of the two before it:

```text
1 + 1 = 2   ✓
1 + 2 = 3   ✓
2 + 3 = 5   ✓
3 + 5 = 8   ✓
5 + 8 = 13  ✓
8 + 13 = 21 ✓
13 + 21 = 34 ✓
21 + 34 = 55 ✓
```

The rule is implemented correctly. Only the starting point differs.

---

# 10. Visualize data where useful

## The sliding window

```text
The series:   0   1   1   2   3   5   8  13  21  34  55  89
              └─┬─┘
pass 1:      [0, 1] → next = 1

                  └─┬─┘
pass 2:          [1, 1] → next = 2

                      └─┬─┘
pass 3:              [1, 2] → next = 3

                          └─┬─┘
pass 4:                  [2, 3] → next = 5

The two-value window slides one step right each pass.
```

## The three-step shuffle

```text
BEFORE pass 4:
   number1 ┌───┐   number2 ┌───┐   number3 ┌───┐
           │ 2 │           │ 3 │           │ ? │
           └───┘           └───┘           └───┘

STEP 1: number3 = number1 + number2     (compute from the OLD pair)
           ┌───┐           ┌───┐           ┌───┐
           │ 2 │─────┬────▶│ 3 │      2+3 →│ 5 │
           └───┘     +     └───┘           └───┘

STEP 2: number1 = number2               (shift the oldest slot)
           ┌───┐           ┌───┐           ┌───┐
           │ 3 │◀──────────│ 3 │           │ 5 │
           └───┘           └───┘           └───┘
             ↑ the 2 is destroyed here — but we already used it

STEP 3: number2 = number3               (shift the newer slot)
           ┌───┐           ┌───┐           ┌───┐
           │ 3 │           │ 5 │◀──────────│ 5 │
           └───┘           └───┘           └───┘

AFTER:  number1 = 3, number2 = 5    ✓ window advanced
PRINT:  number1 → 3
```

## Where the `0` goes

```text
seeds:      n1=0   n2=1
              │
              ├──── used in the sum: 0 + 1 = 1
              │
              ▼
         n1 = number2      ← 0 is OVERWRITTEN here
              │
              ▼
         print n1 → 1      ← too late; the 0 is gone

The 0 was used but never shown.
Moving the print one line earlier would show it.
```

## Exponential growth

```text
F(1)  =           1     █
F(5)  =           5     █
F(10) =          55     █
F(20) =       6,765     ██
F(30) =     832,040     ████
F(40) =  102,334,155    ███████
F(46) = 1,836,311,903   ██████████  ← the largest that fits in an int
F(47) = 2,971,215,073   ✗ OVERFLOWS
```

Each term is roughly 1.618× the last — the golden ratio. That is why `int` runs out after only 46 terms.

---

# 11. Alternative ways to write the same logic

### The fix — print before shifting, and better names

```java
int previous = 0;
int current = 1;
int count = 10;

System.out.println("The Fibonacci series is as follows:");

for (int i = 0; i < count; i++) {
    System.out.println(previous);          // ← print FIRST

    int next = previous + current;
    previous = current;
    current = next;
}
```

Output — now starting at `0`:

```text
The Fibonacci series is as follows:
0
1
1
2
3
5
8
13
21
34
```

Two improvements: the series starts correctly, and `previous`/`current`/`next` read like the specification instead of like `number1`/`number2`/`number3`.

### One-line output

```java
int previous = 0, current = 1;

System.out.print("Fibonacci series: ");
for (int i = 0; i < 10; i++) {
    System.out.print(previous + " ");      // print, not println
    int next = previous + current;
    previous = current;
    current = next;
}
System.out.println();                      // finish the line
```

Output:

```text
Fibonacci series: 0 1 1 2 3 5 8 13 21 34 
```

`print` keeps everything on one line; the final `println()` ends it. Same technique as the display loop in [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]].

### Reusable version — a method

```java
public static void printFibonacci(int count) {
    if (count <= 0) {
        return;                            // nothing to print
    }

    int previous = 0;
    int current = 1;

    for (int i = 0; i < count; i++) {
        System.out.print(previous + " ");
        int next = previous + current;
        previous = current;
        current = next;
    }
    System.out.println();
}
```

Reusable, testable, and explicit about what happens for a non-positive count.

### Returning the n-th term instead of printing

```java
public static long fibonacci(int n) {
    if (n <= 1) {
        return n;                          // F(0)=0, F(1)=1
    }

    long previous = 0;
    long current = 1;

    for (int i = 2; i <= n; i++) {
        long next = previous + current;
        previous = current;
        current = next;
    }
    return current;
}
```

Note `long` instead of `int` — this extends the safe range from F(46) to F(92).

A method that **returns** a value is more useful than one that prints: the caller decides what to do with it. **Separating computation from presentation is a habit worth building early.**

### Recursive version — elegant and terrible

```java
public static int fibonacci(int n) {
    if (n <= 1) {
        return n;                          // base case
    }
    return fibonacci(n - 1) + fibonacci(n - 2);   // recursive case
}
```

This mirrors the mathematical definition perfectly. It is also catastrophically slow.

> [!warning] Why naive Fibonacci recursion is a trap
> Every call spawns **two** more calls, so the work doubles at each level:
>
> ```text
>                fib(5)
>            ┌─────┴─────┐
>         fib(4)       fib(3)
>        ┌──┴──┐      ┌──┴──┐
>     fib(3) fib(2) fib(2) fib(1)
>     ┌──┴──┐
>  fib(2) fib(1)
> ```
>
> `fib(3)` is computed **twice**. `fib(2)` is computed **three times**. The same values are recalculated over and over.
>
> | n | Loop steps | Recursive calls |
> |---|---|---|
> | 10 | 10 | 177 |
> | 30 | 30 | ~2.7 million |
> | 50 | 50 | ~40 **billion** (hours) |
>
> The loop is `O(n)`. The naive recursion is `O(2ⁿ)`.
>
> **This is the standard textbook example of why elegant is not the same as efficient**, and interviewers ask about it constantly.

### Recursion done properly — memoisation

```java
public static long fibonacci(int n, long[] memo) {
    if (n <= 1) {
        return n;
    }
    if (memo[n] != 0) {
        return memo[n];                    // already computed — reuse it
    }
    memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
    return memo[n];
}
```

Caching each result reduces the work to `O(n)` — every value is computed once. This is the entry point to **dynamic programming**, and Fibonacci is the classic first example.

### `BigInteger` for unlimited terms

```java
import java.math.BigInteger;

BigInteger previous = BigInteger.ZERO;
BigInteger current = BigInteger.ONE;

for (int i = 0; i < 100; i++) {
    System.out.println(previous);
    BigInteger next = previous.add(current);      // returns a NEW object
    previous = current;
    current = next;
}
```

The structure is identical — only the type and the addition change. `F(100)` is `354,224,848,179,261,915,075`, far beyond `long`.

Note `previous.add(current)` must be **assigned**, because `BigInteger` is immutable — the same rule as `String` methods in [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]].

---

# 12. Common beginner mistakes

## Mistake 1 — shifting before computing

**Incorrect code**

```java
number1 = number2;
number2 = number1 + number2;      // ✗ number1 already changed
```

**Why it is wrong**
The sum uses the **new** `number1`, not the old one. The sequence becomes `1, 2, 4, 8, 16...` — doubling, not Fibonacci.

Trace it: `n1=0, n2=1` → `n1=1` → `n2 = 1+1 = 2` → `n1=2` → `n2 = 2+2 = 4`. Powers of two.

**Correct code**

```java
int number3 = number1 + number2;
number1 = number2;
number2 = number3;
```

**How to recognise it in future**
**Compute from the old state before you overwrite any of it.** If your Fibonacci output doubles each time, this is the bug.

---

## Mistake 2 — shifting in the wrong order

**Incorrect code**

```java
int number3 = number1 + number2;
number2 = number3;                // ✗ destroys number2 first
number1 = number2;                // ✗ number1 now gets number3
```

**Why it is wrong**
`number1` ends up holding `number3` instead of the old `number2`. Both variables converge and the series breaks.

**Correct code**
Shift the **oldest** slot first: `number1 = number2;` then `number2 = number3;`.

**How to recognise it**
Each assignment must read a slot that has not yet been written this pass. Write left to right, read right to left.

---

## Mistake 3 — declaring the state variables inside the loop

**Incorrect code**

```java
for (int i = 0; i < count; i++) {
    int number1 = 0;
    int number2 = 1;
    int number3 = number1 + number2;
    ...
}
```

**Why it is wrong**
The state resets to `0, 1` every pass, so the program prints `1` ten times.

**Correct code**
Declare `number1` and `number2` before the loop. Only `number3` belongs inside.

**How to recognise it**
If your sequence never progresses, the state is being reset. **Anything that must carry forward is declared outside.**

---

## Mistake 4 — printing the wrong variable

**Incorrect code**

```java
System.out.println(number2);      // after the shift
```

**Why it is wrong**
`number2` is one step ahead, so the output starts at `1, 2, 3, 5, 8...` — skipping a term.

**Correct code**
Print `number1` after the shift, or print `number1` **before** the shift to include the `0`.

**How to recognise it**
Check the first two or three printed values against the known series. If they are shifted, look at which variable you print and where.

---

## Mistake 5 — using `i <= count`

**Incorrect code**

```java
for (int i = 0; i <= count; i++) {
```

**Why it is wrong**
Prints eleven numbers instead of ten.

**Correct code**

```java
for (int i = 0; i < count; i++) {
```

**How to recognise it**
Count the output lines. Starting at `0`, `count` items requires `i < count`.

---

## Mistake 6 — wrong seed values

**Incorrect code**

```java
int number1 = 1;
int number2 = 1;
```

**Why it is wrong**
This starts the series at the *second* `1`, so the output is `2, 3, 5, 8...` — shifted by two positions.

Note that `int number1 = 0; int number2 = 0;` is worse still: `0 + 0 = 0` forever, printing ten zeros. **A sequence that never changes almost always means the seeds are wrong.**

**Correct code**

```java
int number1 = 0;
int number2 = 1;
```

**How to recognise it**
The seeds define the sequence. `0, 1` gives Fibonacci; `2, 1` gives Lucas numbers; `0, 0` gives nothing at all.

---

## Mistake 7 — ignoring overflow

**Incorrect assumption**

```java
int count = 50;
```

**Why it is wrong**
F(47) exceeds `int`. Terms 47 onward print wrong — and negative.

**Correct code**

```java
long previous = 0, current = 1;      // safe to F(92)
```

**How to recognise it**
**A negative Fibonacci number is impossible**, so a negative output is proof of overflow.

---

## Mistake 8 — putting the header inside the loop

**Incorrect code**

```java
for (int i = 0; i < count; i++) {
    System.out.println("The Fibonacci series is as follows:");
    ...
}
```

**Why it is wrong**
The header prints ten times.

**Correct code**
Put it before the loop.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| State management | do you know two values must be carried, and why? |
| Assignment order | **do you know why the temporary is needed?** |
| Loop counting | `i < count` for exactly `count` items |
| Trace accuracy | can you predict the exact output, including the missing `0`? |
| Recursion trade-offs | do you know the naive recursion is `O(2ⁿ)`? |
| Overflow awareness | do you know `int` breaks at term 47? |

> [!tip]
> Fibonacci is one of the most-asked interview questions precisely because it has an easy answer (the loop) and several deep follow-ups (recursion cost, memoisation, overflow, `O(log n)` matrix exponentiation). Getting the loop right is the entry ticket; the follow-ups are where the interview actually happens.

## Likely follow-up questions

> **"Why do you need `number3`? Can you avoid it?"**

You need it because assignment overwrites — computing the sum after shifting would use the wrong value. You *can* avoid it with a trick:

```java
number2 = number1 + number2;      // number2 becomes the next term
number1 = number2 - number1;      // recovers the old number2
```

It works but is far less readable, and it is the same category of cleverness as the arithmetic swap in `SwapNumbers`. Mention it, then say you would not ship it.

> **"Write it recursively. What is the complexity?"**

`fib(n) = fib(n-1) + fib(n-2)` with base case `n <= 1`. It is `O(2ⁿ)` because subproblems are recomputed exponentially many times. `fib(50)` would take hours.

> **"How would you make the recursion fast?"**

Memoisation — cache each computed value in an array or map, reducing it to `O(n)`. This is the classic introduction to dynamic programming.

> **"What is the time and space complexity of the loop version?"**

`O(n)` time, `O(1)` space — only three `int`s regardless of `count`.

> **"What does this program actually print?"**

`1 1 2 3 5 8 13 21 34 55`. The initial `0` is used in the first sum but overwritten before the print. Spotting this is the whole point of the question.

> **"How would you print it starting from 0?"**

Move the `println` above the computation. **The arithmetic does not change — only the print position.**

> **"What happens with `count = 50`?"**

`int` overflows at term 47 and the output goes negative. Use `long` (to F(92)) or `BigInteger` (unlimited).

> **"Can you do better than `O(n)`?"**

Yes — matrix exponentiation or fast doubling gives `O(log n)`. There is also Binet's closed-form formula, but floating-point rounding makes it unreliable beyond about F(70). Knowing these exist is a strong signal even if you cannot derive them on the spot.

---

# 14. Complexity

## Time complexity: `O(n)`

Let `n` be `count`.

The loop runs `n` times, doing constant work per pass: one addition, two assignments, one print.

In beginner language:

> "The loop runs once for each number we want. Ten numbers means ten passes; a thousand numbers means a thousand. Double the count and you double the work. Because the work grows in direct proportion to the count, we call it linear time and write it `O(n)`."

## Space complexity: `O(1)`

Four `int`s at most — `number1`, `number2`, `number3`, `i` — regardless of `count`.

> "We use the same handful of variables whether we print 10 numbers or 10 million, so the extra memory is constant: `O(1)`."

> [!important] Why the iterative version wins so decisively
> | Approach | Time | Space | F(50) takes |
> |---|---|---|---|
> | **loop (this program)** | `O(n)` | `O(1)` | microseconds |
> | naive recursion | `O(2ⁿ)` | `O(n)` stack | **hours** |
> | memoised recursion | `O(n)` | `O(n)` | microseconds |
> | matrix / fast doubling | `O(log n)` | `O(1)` | microseconds |
>
> The naive recursion is not slightly worse — it is *astronomically* worse. `2⁵⁰` is about a quadrillion.
>
> **Fibonacci is the canonical demonstration that the shape of an algorithm matters more than any micro-optimisation.** No amount of tuning rescues an exponential algorithm.

## Why the recursion is exponential

Each call makes two more calls, and the same subproblems recur:

```text
fib(5) needs fib(4) and fib(3)
fib(4) needs fib(3) and fib(2)      ← fib(3) computed AGAIN
fib(3) needs fib(2) and fib(1)      ← fib(2) computed AGAIN
```

The call tree has roughly `2ⁿ` nodes. Memoisation collapses it to `n` by remembering each answer the first time.

---

# 15. Edge cases

## `count = 0`

```java
int count = 0;
```

- Condition `0 < 0` is `false` immediately.
- The loop never runs.
- Output: only the header.

Correct — zero numbers requested, zero printed.

## `count = 1`

One pass: prints `1`.

With the *fixed* version (print before shifting) it would print `0`, which is the more conventional first term. Another illustration of how much the print position matters.

## `count` negative

```java
int count = -5;
```

`0 < -5` is `false`, so nothing prints. No crash, and arguably the right behaviour — though a defensive version would say so explicitly:

```java
if (count <= 0) {
    System.out.println("Count must be positive");
    return;
}
```

## `count = 46` — the last safe value

```text
F(46) = 1,836,311,903
Integer.MAX_VALUE = 2,147,483,647
```

Fits, just. This is the largest term an `int` version handles correctly.

## `count = 47` — silent overflow

```text
True F(47):  2,971,215,073
Printed:    -1,323,752,223
```

> [!warning] Silent integer overflow
> No exception, no warning. The value wraps around and goes negative.
>
> **A Fibonacci number can never be negative**, so a negative output is unambiguous proof of overflow. That makes this failure easier to detect than most — but only if you are looking.
>
> **Fixes:**
> ```java
> long previous = 0, current = 1;                     // safe to F(92)
> BigInteger previous = BigInteger.ZERO;              // unlimited
> ```

## `count = 93` with `long`

`F(92)` is `7,540,113,804,746,346,429` — the largest that fits in a `long`. `F(93)` overflows. `BigInteger` is the only correct choice beyond that.

## Different seeds

The same code with different starting values produces different sequences:

| Seeds | Sequence | Name |
|---|---|---|
| `0, 1` | 0 1 1 2 3 5 8... | Fibonacci |
| `2, 1` | 2 1 3 4 7 11 18... | Lucas numbers |
| `1, 1` | 1 1 2 3 5 8... | Fibonacci, offset by one |
| `0, 0` | 0 0 0 0 0... | degenerate — nothing grows |

> [!tip]
> That last row is a useful diagnostic. **If a generating loop produces a constant sequence, check the seeds first** — the rule is probably fine and the starting values are not.

## Can it crash?

No. There are no arrays, no divisions, no object dereferences, and no input parsing. The program always terminates and never throws.

Its only failure mode is **silent numeric incorrectness** through overflow — which, as with [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]], is more dangerous than a crash because nothing announces it.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Print the first ten Fibonacci numbers.

        ↓

What is the rule?
Each number is the sum of the two before it.

        ↓

How far back does the rule reach?
TWO steps.

        ↓

Therefore I must remember TWO values at all times.
Not one (can't apply the rule),
not three (the rule never looks that far back).
→ int number1, int number2

        ↓

Can the rule produce the first numbers?
No — nothing precedes them.
So they must be GIVEN, not computed.
→ number1 = 0, number2 = 1

        ↓

How many numbers do I want?
Ten → int count = 10

        ↓

How do I repeat exactly ten times?
for (int i = 0; i < count; i++)
Note: i is only a tally here —
it never appears inside the body.

        ↓

Inside the loop, what has to happen?
(a) compute the next number
(b) move the two-value window forward
(c) print something

        ↓

(a) next = number1 + number2

        ↓

(b) Careful. Moving the window overwrites.
If I write number1 = number2 first,
the old number1 is destroyed —
and the sum needs it.

        ↓

So: COMPUTE FIRST, into a temporary.
    int number3 = number1 + number2;
Then shift:
    number1 = number2;
    number2 = number3;

(Exactly the temp-variable principle
 from SwapNumbers.)

        ↓

Which slot do I write first?
The oldest. Each write must read a slot
not yet overwritten this pass.
number1 ◀ number2 ◀ number3

        ↓

(c) Where does the print go?
AFTER the shift → number1 holds the old number2
                → series starts at 1
BEFORE the shift → number1 still holds the seed
                → series starts at 0

This program prints after → starts at 1.
The 0 is used but never shown.

        ↓

Trace to verify:
0,1 → 1
1,1 → 1
1,2 → 2
2,3 → 3
3,5 → 5      ✓ each is the sum of the two before

        ↓

Check the edges:
count = 0  → nothing prints  ✓
count = 47 → int overflows   ⚠ note the limit
```

> [!important] The two decisions that mattered
> Everything else was mechanical:
>
> 1. **The temporary variable** — forced by the fact that assignment overwrites, and the sum needs both old values.
> 2. **The print position** — decides whether the output starts at `0` or `1`, with no change to the arithmetic at all.
>
> Both came from tracing, not from syntax knowledge. **When a loop carries state, trace it on paper before trusting it.**

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int number1 = 0;` | "Remember the first starting number, zero." |
| `int number2 = 1;` | "Remember the second starting number, one. These two are given, because there is nothing before them to add." |
| `int count = 10;` | "We want ten numbers in total." |
| `for (int i = 0; i < count; i++)` | "Do the following ten times." |
| `int number3 = number1 + number2;` | "Add the two numbers I am remembering to get the next one in the series — and park it somewhere safe, because I am about to overwrite the numbers I just used." |
| `number1 = number2;` | "Slide the window forward: the newer remembered number becomes the older one." |
| `number2 = number3;` | "And the number I just computed becomes the newer one." |
| `System.out.println(number1);` | "Print the older of the two numbers I am now remembering." |

The loop in one sentence:

> **"Ten times over: add the two numbers you are holding to get the next one, slide both along by one place, then print the older of the pair."**

And the whole program:

> **"Start by holding 0 and 1. Ten times, work out the next Fibonacci number from the pair you are holding, shuffle the pair forward, and print. Because the printing happens after the shuffle, the very first 0 is used but never shown."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| state variable | carries information between iterations; declared **before** the loop |
| temporary variable | holds one pass's intermediate result; declared **inside** the loop |
| assignment overwrites | compute from old values **before** overwriting any of them |
| sliding window | shift the oldest slot first, so each write reads unmodified data |
| repetition counter | an `i` that never appears in the body — `i < count` runs `count` times |
| print position | decides *which* values you see, without changing the arithmetic |
| integer overflow | `int` wraps past ±2.1 billion — Fibonacci breaks at F(47) |
| exponential recursion | naive `fib(n)` is `O(2ⁿ)`; memoisation makes it `O(n)` |

### Important methods

| Member | Owner | Accepts | Returns |
|---|---|---|---|
| `println(int)` | `java.io.PrintStream` | an `int` | `void` |
| `print(String)` | `java.io.PrintStream` | text | `void` — no newline |
| `BigInteger.ZERO` / `.ONE` | `java.math.BigInteger` | — (constants) | the seeds |
| `add(BigInteger)` | `BigInteger` | a `BigInteger` | a **new** `BigInteger` |

There is deliberately **no** `Math.fibonacci()` in Java.

### Important syntax

| Syntax | Meaning |
|---|---|
| `for (a; b; c)` | initialise once; test before each pass; update after |
| `i < count` | runs exactly `count` times when starting from `0` |
| `i++` | add one |
| `=` | assignment — evaluate the right, store into the left |
| `+` | addition between numbers; concatenation with a `String` |
| `{ }` | a block — determines both repetition and scope |
| `long` | 64-bit whole number — extends Fibonacci to F(92) |

### Main interview concept

> **When two values must update together, compute the new value from the old pair *first*, then shift.** `int number3 = number1 + number2;` must precede both assignments, and the shift must write the oldest slot first. This is the same principle as the `temp` variable in a swap and the demotion in a second-largest scan — **save or compute before you destroy**.

### Main lesson for code reading

> **When a loop carries state, trace it — the output depends on details no amount of reading will reveal.**
>
> ```text
> compute → shift → print     gives  1 1 2 3 5 8 ...
> print → compute → shift     gives  0 1 1 2 3 5 ...
> ```
>
> Identical arithmetic, different first term. The only difference is where one line sits. Read where variables are **declared** to learn their lifetimes, then trace two or three passes on paper to learn what the loop actually produces.

---

### Related notes

- [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]] — the temp-variable principle this program depends on
- [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]] — two coupled values and an order-sensitive update
- [[Java_Factorials_Code_Reading_Explanation_Obsidian|Factorials]] — a counting loop, and recursion with a base case
- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — one accumulator instead of two state variables
- [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]] — another loop whose state changes shape each pass
