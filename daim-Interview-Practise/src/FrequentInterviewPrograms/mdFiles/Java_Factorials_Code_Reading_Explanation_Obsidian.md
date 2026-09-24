---
title: Java Code Reading — Factorials
tags:
  - java
  - interview-programs
  - code-reading
  - loops
  - math
aliases:
  - Factorials Explained
  - Factorial Program
---

# Java Code Reading — Factorials Explained for a Fresh Java Programmer

> [!note]
> If you have read [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]], you already know 90% of this program. It is the **same accumulator skeleton** with two things changed: the starting value is `1` instead of `0`, and the operation is `*` instead of `+`.
>
> That is the real lesson here. Two programs that *look* different are the same pattern wearing different clothes. Learning to see the shared skeleton is what turns memorisation into understanding.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Is there a running answer? → What starting value leaves it unchanged? → What does each step do to it?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class Factorials {

        public static void main(String[] args) {
            // Initialize the number for which factorial needs to be calculated
            int factorialFor = 6;

            // Initialize the factorial variable to 1
            int factorial = 1;

            // Calculate the factorial using a for loop
            for(int i=1; i<=factorialFor; i++){
                factorial = factorial * i;
            }

            // Print the factorial
            System.out.println(factorial);
        }
    }
```

The executable logic is just four lines:

```java
int factorialFor = 6;
int factorial = 1;
for(int i=1; i<=factorialFor; i++){ factorial = factorial * i; }
System.out.println(factorial);
```

> [!note] About the original file
> The source file contains three stacked Javadoc comment blocks above `main`, all describing the same method, and the indentation is slightly irregular. That is harmless — comments and whitespace are invisible to the compiler. The logic above is exactly what runs.

---

# 2. What problem is this program solving?

In plain language:

> Multiply together every whole number from 1 up to 6, and print the answer.

That is what "factorial of 6" means, written `6!` in mathematics:

```text
6! = 1 × 2 × 3 × 4 × 5 × 6 = 720
```

### What do we know?

- One input number: `6`.
- A rule from mathematics: multiply all whole numbers from `1` to that number.

### What do we need?

- **One** number: the product.

### What transformations are required?

Look at the shape: **a range of numbers in, one number out**. That is a **reduction**, exactly like summing — but with multiplication instead of addition.

```text
1   2   3   4   5   6           ← a range of values
 \  |   |   |   |  /
  \ |   |   |   | /
       720                       ← one value
```

The three ingredients of any reduction:

| Ingredient | For factorial |
|---|---|
| A place to keep the running answer | a variable, `factorial` |
| A **starting value** that changes nothing | `1` — because multiplying by 1 leaves a number unchanged |
| A rule applied to each item | `factorial = factorial * i` |

> [!important] Why `1` and not `0` — the identity value again
> This is the question interviewers love, because getting it wrong destroys the program silently... or rather, very loudly:
>
> ```java
> int factorial = 0;              // ✗
> factorial = 0 * 1 = 0
> factorial = 0 * 2 = 0
> factorial = 0 * 3 = 0
> ...
> result: 0
> ```
>
> **Zero annihilates multiplication.** Anything multiplied by zero is zero, so the answer can never escape.
>
> The correct starting value is the **identity element** for the operation — the value that leaves its partner unchanged:
>
> | Operation | Identity | Because |
> |---|---|---|
> | addition `+` | `0` | `x + 0 = x` |
> | multiplication `*` | `1` | `x × 1 = x` |
> | string concatenation | `""` | `"abc" + "" = "abc"` |
> | logical AND `&&` | `true` | `x && true = x` |
> | logical OR `\|\|` | `false` | `x \|\| false = x` |
>
> Do not memorise "factorial starts at 1". Ask instead: **"What value can I multiply by without changing anything?"** The answer is `1`, and you have derived it rather than recalled it.

### Where the mathematics comes from

You do not need to invent factorial — it is a definition:

```text
n! = 1 × 2 × 3 × ... × n

0! = 1        (by definition — an empty product is the identity)
1! = 1
2! = 1 × 2 = 2
3! = 1 × 2 × 3 = 6
4! = 24
5! = 120
6! = 720      ← this program
```

Notice the recursive structure hiding in that table: `6! = 6 × 5!`. That observation is what makes the recursive solution in section 11 possible.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder on disk. First statement in the file.

## `public class Factorials`

- `public` — usable from anywhere.
- `class` — declares a class.
- `Factorials` — the name, `PascalCase`, matching `Factorials.java`.

> [!note] A small naming observation
> The class is called `Factorials` (plural) but computes exactly one factorial. `Factorial` would be marginally more accurate. This is worth noticing only because it is good practice to read names critically — a name that promises something the code does not deliver is a small but real source of confusion.

## `public static void main(String[] args)`

The JVM entry point.

| Part | Meaning | Why required |
|---|---|---|
| `public` | reachable from outside the class | the JVM is outside |
| `static` | belongs to the class, not an object | no object exists when the program starts |
| `void` | returns nothing | the JVM has nowhere to put a return value |
| `main` | the exact name the JVM looks for | fixed by the Java launcher |
| `String[] args` | command-line arguments | text typed after the program name arrives here |

## No imports

The program uses `int`, `String` and `System` — primitives and `java.lang` classes, all available without an `import`.

## Variables

| Variable | Type | Role | Declared where |
|---|---|---|---|
| `factorialFor` | `int` | the input — how far to multiply | before the loop |
| `factorial` | `int` | the accumulator — the running product | before the loop |
| `i` | `int` | the counter — the current multiplier | inside the loop header |

The same three roles as every loop program: **data, accumulator, counter**. Classifying variables this way before reading the body makes any loop far easier to understand.

## Comments

The program is heavily commented:

```java
// Initialize the factorial variable to 1
int factorial = 1;
```

> [!tip] What makes a comment useful
> This comment restates *what* the code does, which the code already says perfectly clearly. A more valuable comment explains *why*:
>
> ```java
> int factorial = 1;   // 1 is the identity for multiplication; starting at 0 would give 0
> ```
>
> The rule of thumb: **code says what, comments should say why.** When you find yourself writing a comment that just translates the line into English, either delete it or upgrade it into a reason.

---

# 4. Line-by-line explanation

## 4.1 — `int factorialFor = 6;`

```java
int factorialFor = 6;
```

**What is this?**
A declaration with initialisation, holding the number whose factorial we want.

**Why do we need it?**
The loop needs to know where to stop. Without this value there is no way to express "up to 6".

**How would a beginner know to write it?**
Ask: "What does my computation depend on?" It depends on the target number. Anything a computation depends on must exist as named data first.

You could technically hard-code `6` directly into the loop:

```java
for (int i = 1; i <= 6; i++)
```

but then changing the target means hunting through the code. Naming the input in one place is what makes a program easy to modify — a habit worth building from day one.

**What type of data is involved?**
A whole number, `6`.

**Why was this data type selected?**

| Candidate | Verdict |
|---|---|
| `int` | **chosen** — factorial is only defined for whole numbers, and `int` is Java's standard whole-number type |
| `double` | wrong — `6.5!` is not defined in ordinary mathematics; also introduces rounding error |
| `long` | would work; unnecessary for a small input, though see the overflow discussion |
| `String` | wrong — you cannot loop up to a piece of text |

The decisive question: **will I do arithmetic or numeric comparison with this?** Yes, `i <= factorialFor`. So it must be a number, and a whole one.

**Why the name `factorialFor`?**
It reads as "the number we are computing the factorial *for*". A little awkward, but genuinely descriptive. Common alternatives: `n` (the mathematical convention), `number`, or `target`.

**What does Java do when it reaches this line?**
Reserves 32 bits on the stack, labels it `factorialFor`, writes `6`.

---

## 4.2 — `int factorial = 1;`

```java
int factorial = 1;
```

**What is this?**
The accumulator: the variable that will hold the running product.

**Why do we need it?**
Multiplication happens two numbers at a time. To multiply six numbers you multiply the first two, then multiply that result by the third, and so on. Each step needs somewhere to store "the product so far".

**How would a beginner know to write it?**
Ask: **"Do I need to remember something between one loop pass and the next?"** Yes — the running product. Anything that must survive across iterations is declared **before** the loop.

**Why must it be declared outside the loop?**

```java
for (int i = 1; i <= factorialFor; i++) {
    int factorial = 1;             // ✗ reset to 1 on every pass
    factorial = factorial * i;
}
System.out.println(factorial);     // ✗ error: cannot find symbol
```

A variable declared inside `{ }` is created on entry to the block and destroyed on exit. Declaring `factorial` inside would reset it every pass, and it would not exist afterwards for printing.

**Why the starting value `1`?**

Because `1` is the identity for multiplication. The first real step is `1 * 1 = 1`, which is correct; had we started at `0`, every step would produce `0`.

**What type and why?**
We are multiplying `int` values, and `int * int` produces an `int`. Type consistency demands `int`.

**Why the name `factorial`?**
It names the meaning of the data exactly. `product` or `result` would also be fine.

---

## 4.3 — `for(int i=1; i<=factorialFor; i++){`

```java
for(int i=1; i<=factorialFor; i++){
```

**What is this?**
A `for` loop that produces the numbers `1, 2, 3, 4, 5, 6` in turn.

**Why do we need it?**
Without a loop we would write:

```java
factorial = 1 * 1 * 2 * 3 * 4 * 5 * 6;
```

which works only for the number 6. Change `factorialFor` to `7` and this line is silently wrong. A loop expresses "for every number up to the target", **whatever the target is**.

**How would a beginner know to write it?**
Whenever a rule says "for every number from A to B", that is a `for` loop with `i = A` and a condition bounded by `B`.

**The three sections**

```text
for ( int i=1 ; i<=factorialFor ; i++ )
        │            │             │
        │            │             └── after each pass: move to the next number
        │            └──────────────── before each pass: are we still within range?
        └───────────────────────────── once, at the start: begin at 1
```

**Why does `i` start at `1`, not `0`?**

This is the most important difference between this loop and every array loop you have seen.

> [!important] Array loops vs counting loops
> In [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] the loop started at `0`:
>
> ```java
> for (int i = 0; i < arr.length; i++)     // i is a POSITION
> ```
>
> Here it starts at `1`:
>
> ```java
> for (int i = 1; i <= factorialFor; i++)  // i is a VALUE
> ```
>
> The difference is what `i` *means*:
>
> | | Array loop | Counting loop |
> |---|---|---|
> | `i` represents | a **position** in a structure | an actual **number** in a range |
> | starts at | `0`, because arrays are zero-indexed | whatever the mathematics says — here `1` |
> | condition | `i < length` | `i <= target` |
> | used as | `arr[i]` | `i` itself |
>
> **Do not blindly start every loop at `0`.** Ask what `i` means. If it is an index into an array, start at `0`. If it is a number you are computing with, start wherever the problem starts.
>
> And starting at `0` here would be a disaster:
> ```text
> factorial = 1 * 0 = 0     ← annihilated on the very first pass
> ```

**Why `<=` and not `<`?**

Because `6` itself must be included: `6! = 1×2×3×4×5×6`. Using `i < factorialFor` would stop at `5` and compute `5! = 120` — a silent wrong answer.

```text
i <= 6   →  1,2,3,4,5,6   →  720   ✓
i <  6   →  1,2,3,4,5     →  120   ✗
```

> [!tip] How to choose between `<` and `<=` without guessing
> Always ask: **"Should the boundary value itself be processed?"**
>
> - Factorial of 6 → yes, 6 must be multiplied in → `<=`
> - Array of length 5 → no, index 5 does not exist → `<`
>
> Answer that question *before* writing the operator and you will never guess again.

**What is `i++`?**
The increment operator: `i = i + 1`. Without it, `i` stays `1` forever and the loop never ends.

**What does Java do when it reaches this line?**

```text
1. int i = 1                    (once)
2. is 1 <= 6 ?  true   → run body → i++ → i = 2
3. is 2 <= 6 ?  true   → run body → i++ → i = 3
4. is 3 <= 6 ?  true   → run body → i++ → i = 4
5. is 4 <= 6 ?  true   → run body → i++ → i = 5
6. is 5 <= 6 ?  true   → run body → i++ → i = 6
7. is 6 <= 6 ?  true   → run body → i++ → i = 7
8. is 7 <= 6 ?  false  → exit
```

Seven checks, six body executions.

---

## 4.4 — `factorial = factorial * i;`

```java
factorial = factorial * i;
```

**What is this?**
The accumulation step — the heart of the program.

**Why do we need it?**
This is the rule applied at each step: "multiply the running product by the current number."

**How would a beginner know to write it?**
Ask: "What should happen to my running answer when I reach the next number?" For a factorial, "multiply it in".

**What does each part mean?**

```text
factorial   =   factorial   *   i   ;
    │       │       │       │   │
    │       │       │       │   └── the current number: 1, then 2, then 3...
    │       │       │       └────── multiplication
    │       │       └────────────── read the CURRENT value of factorial
    │       └────────────────────── store the result back
    └────────────────────────────── the destination
```

> [!important] Why this is not circular
> `factorial = factorial * i` looks like it defines itself. It does not, because assignment has a strict order:
>
> ```text
> Step 1: evaluate the RIGHT side completely
>         factorial * i   →   e.g. 6 * 4   →   24
>
> Step 2: store that result into the LEFT side
>         factorial  ←  24
> ```
>
> The old value is read before the new one is written. `=` is a **command** ("make it become"), not a mathematical equation.

**What types are involved?**
`factorial` is `int`, `i` is `int`, `int * int` is `int`, stored into an `int`. Every type matches.

**What does Java do?**
Reads `factorial`, reads `i`, multiplies, writes the product back into `factorial`.

> [!tip] The shorthand
> ```java
> factorial *= i;      // exactly equivalent
> ```
> `*=` is a compound assignment operator, alongside `+=`, `-=`, `/=` and `%=`. It makes the intent — "multiply into" — slightly more direct.

---

## 4.5 — `System.out.println(factorial);`

```java
System.out.println(factorial);
```

**What is this?**
Printing the final result.

**Why do we need it?**
The loop computes the answer but leaves it invisible inside `factorial`. Printing is how the program reports it.

**What does each part mean?**

| Part | What it is |
|---|---|
| `System` | a class in `java.lang` |
| `out` | a `static` field of type `PrintStream` |
| `println` | prints its argument, then a newline |
| `factorial` | the `int` argument |

**Which overload runs?**
`println(int)`, because the argument is an `int`. No concatenation happens — there is no `String` here at all.

**What does it produce?**

```text
720
```

**What does it return?**
`void`. Nothing to store.

> [!warning] The output has no label
> The program prints a bare `720`. Someone running it sees a number with no explanation of what it means.
>
> A small improvement:
> ```java
> System.out.println("Factorial of " + factorialFor + " is: " + factorial);
> ```
> which prints `Factorial of 6 is: 720`.
>
> **Programs communicate with humans.** Labelling output costs one line and makes the program self-explanatory. Notice that every other program in this folder labels its output — this one is the exception.

---

# 5. How to think like the programmer

```text
Requirement
"Compute 6 factorial"
        ↓
What does factorial mean?
6! = 1 × 2 × 3 × 4 × 5 × 6
        ↓
What data do I have?
One number, the target → int factorialFor = 6
        ↓
What do I need to produce?
One number, the product
        ↓
Range in, one value out — this is a reduction
        ↓
Every reduction needs:
  (a) a place for the running answer
  (b) a starting value that changes nothing
  (c) a rule per item
        ↓
(a) int factorial
        ↓
(b) What can I multiply by without changing anything?
1  →  int factorial = 1
(NOT 0 — zero annihilates multiplication)
        ↓
(c) "multiply the running product by this number"
factorial = factorial * i
        ↓
Where do the numbers 1..6 come from?
A counting loop
        ↓
Where does the counter start?
i is a VALUE here, not an array index.
The mathematics starts at 1 → i = 1
        ↓
Where does it stop?
6 must be included → i <= factorialFor
        ↓
Where must factorial be declared?
Before the loop — it must survive all passes
and be readable afterwards
        ↓
Trace to verify:
1 → 1 → 2 → 6 → 24 → 120 → 720  ✓
        ↓
Print the answer
```

> [!important] The skeleton, filled in differently
> ```java
> TYPE accumulator = IDENTITY;
> for (RANGE) {
>     accumulator = COMBINE(accumulator, item);
> }
> use(accumulator);
> ```
>
> | Program | IDENTITY | COMBINE | Range starts at |
> |---|---|---|---|
> | [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian\|SumOfArrayEle]] | `0` | `acc + item` | `0` (index) |
> | **Factorials** | `1` | `acc * item` | `1` (value) |
> | [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian\|LargestArrayElement]] | `arr[0]` | `max(acc, item)` | `0` (index) |
> | [[Java_ReverseString_Code_Reading_Explanation_Obsidian\|ReverseString]] | `""` | `acc + char` | `length-1` (index, backwards) |
>
> Four programs, one skeleton. This is what it means to learn a *pattern* rather than a *program*.

---

# 6. Deep explanation of important Java concepts used

## `int`

A **primitive** 32-bit whole number, range `-2,147,483,648` to `2,147,483,647`. It holds its value directly rather than a reference. The default choice for whole numbers in Java.

The range matters enormously here — see the overflow discussion in section 15.

## Variable roles in a loop

Every loop-based program has variables playing distinct roles. Naming the role is the fastest way to understand unfamiliar code:

| Role | Here | Characteristics |
|---|---|---|
| **input / data** | `factorialFor` | set once, only read |
| **accumulator** | `factorial` | declared before the loop, updated from its own value, read after |
| **counter** | `i` | declared in the header, changes every pass, dies with the loop |

## The `for` loop

```java
for (initialisation; condition; update) {
    body
}
```

```text
1. initialisation           (once)
   ↓
2. check condition ─── false ──▶ exit
   ↓ true
3. run body
   ↓
4. run update
   ↓
   back to step 2
```

Use `for` when you know how many times to repeat or need a counter. Use `while` when you repeat until something changes with no natural counter — as in [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]].

## Counting loops vs index loops

Worth stating separately because conflating them causes real bugs:

```java
// COUNTING loop — i is a value you compute with
for (int i = 1; i <= n; i++) {
    product = product * i;
}

// INDEX loop — i is a position in a structure
for (int i = 0; i < arr.length; i++) {
    sum = sum + arr[i];
}
```

The tell: **if `i` appears inside `[ ]`, it is an index and starts at `0`. If `i` is used directly in arithmetic, it is a value and starts wherever the problem starts.**

## Scope

```java
int factorial = 1;                              // scope: rest of main
for (int i = 1; i <= factorialFor; i++) {       // scope of i: the loop only
    factorial = factorial * i;                  // both visible
}
System.out.println(factorial);                  // factorial ✓
// System.out.println(i);                       // ✗ i is gone
```

Declaring `i` in the header rather than before the loop is deliberate good practice: it cannot leak, cannot be accidentally reused, and the name is free for the next loop.

## Multiplication and integer overflow

`*` multiplies two numbers. For `int` operands the result is an `int` — and if the true product does not fit in 32 bits, Java **silently wraps around** rather than reporting an error.

```text
Integer.MAX_VALUE = 2,147,483,647

12! = 479,001,600          ✓ fits
13! = 6,227,020,800        ✗ true value exceeds int
     → actually prints 1,932,053,504
```

This is not a rare theoretical concern. Factorial grows so fast that `int` fails at **13** — a number you could easily type by accident.

## Compound assignment `*=`

```java
factorial *= i;      // identical to  factorial = factorial * i;
```

Available for `+=`, `-=`, `*=`, `/=`, `%=`.

## Increment `++`

```java
i++;      // post-increment: yield the current value, then add 1
++i;      // pre-increment:  add 1, then yield the new value
```

As a standalone statement — which is how a `for` update section uses it — the two are identical. The distinction only matters inside a larger expression.

---

# 7. Why this syntax?

## `<=` vs `<` — the decision that defines this program

| Condition | Values of `i` | Result | Correct? |
|---|---|---|---|
| `i <= factorialFor` | 1,2,3,4,5,6 | `720` | ✓ |
| `i < factorialFor` | 1,2,3,4,5 | `120` | ✗ computes 5! |

Note that the wrong version **produces a perfectly plausible number**. `120` is a real factorial — just of the wrong input. There is no crash and no warning.

> [!warning] Plausible wrong answers are the hardest bugs
> A program that crashes tells you something is wrong. A program that prints `120` instead of `720` tells you nothing at all unless you already know the right answer.
>
> This is why **tracing by hand** matters. Compute `6!` on paper first, then check the program agrees.

## `*` vs `+`

One character apart, and it changes the entire meaning:

```text
factorial = factorial * i;    →  720   (product)
factorial = factorial + i;    →  22    (sum: 1+1+2+3+4+5+6)
```

Both compile. Both run. Only one is a factorial. The compiler cannot help you here — types match either way. **The compiler checks grammar, not meaning.**

## `=` vs `==`

| Operator | Job |
|---|---|
| `=` | assignment — "put the right value into the left variable" |
| `==` | comparison — "are these the same?" produces `true`/`false` |

This program uses `=` twice for initialisation, once inside the loop, and `<=` for comparison. No `==` appears anywhere.

## `;` inside `for(...)`

The two semicolons **separate** the three sections; they do not terminate statements:

```java
for (int i=1 ; i<=factorialFor ; i++)
             ↑                 ↑
        separator         separator     ← exactly two
```

> [!warning] The stray-semicolon bug
> ```java
> for(int i=1; i<=factorialFor; i++);       // ← trailing semicolon
> {
>     factorial = factorial * i;
> }
> ```
> The `;` becomes the loop's empty body. The loop spins six times doing nothing, then the block runs once — and fails to compile because `i` is out of scope.
>
> **A `for` header is never followed by a semicolon.**

## `{ }` — the loop body

Groups the statements that repeat. With a single statement the braces are technically optional:

```java
for(int i=1; i<=factorialFor; i++)
    factorial = factorial * i;      // legal
```

> [!tip]
> Write the braces anyway, always. Without them, adding a second line later silently breaks the loop:
>
> ```java
> for(int i=1; i<=factorialFor; i++)
>     factorial = factorial * i;
>     System.out.println(factorial);   // ← NOT in the loop, despite the indentation
> ```
>
> Indentation is for humans; braces are for the compiler. When they disagree, the compiler wins.

## `( )` — three jobs

| Context | Job |
|---|---|
| after `for` | wraps the three loop sections |
| after a method name | wraps the arguments — `println(factorial)` |
| around an expression | forces evaluation order — `(a + b) * c` |

## `public`, `static`, `void`

| Keyword | Meaning |
|---|---|
| `public` | accessible from anywhere |
| `static` | belongs to the class, callable without an object |
| `void` | returns nothing |

---

# 8. Method discovery

This program calls only `println`, so the discovery lesson has to come from a different angle: **what do you do when no method exists for what you want?**

## `println`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.io.PrintStream` |
| **Why can we call it?** | `System.out` is a `PrintStream` object |
| **What does it do?** | writes the argument's text form, then a newline |
| **Which overload runs here?** | `println(int)`, because `factorial` is an `int` |
| **What does it return?** | `void` |

## Is there a built-in factorial method?

A reasonable beginner question. Let us answer it the way you would in the IDE.

Type `Math.` in IntelliJ and read the list:

```text
abs()      max()     min()     pow()     sqrt()
floor()    ceil()    round()   random()  cbrt()
log()      exp()     sin()     cos()     tan()
...
```

There is **no `Math.factorial()`**. It genuinely does not exist in the Java standard library.

> [!important] What to do when the method you want does not exist
> This is a normal and frequent situation, and the response is a decision tree:
>
> ```text
> Does a standard-library method do it?
>   │
>   ├── yes → use it (check its arguments and return type)
>   │
>   └── no
>       │
>       ├── Can I compose it from methods that DO exist?
>       │      e.g. Math.pow, Arrays.stream, String.split
>       │
>       └── No → write the loop yourself.
>              This is the normal outcome for
>              small mathematical algorithms.
> ```
>
> Factorial lands in the last box. That is not a failure — implementing it *is* the exercise.

## Why is there no `Math.factorial()`?

Genuinely interesting, and a good interview talking point:

1. It overflows almost immediately. An `int` version breaks at 13, a `long` version at 21. A library method that fails on such small inputs is a trap.
2. The correct general implementation needs `BigInteger`, which lives in a different package and has different performance characteristics.
3. It is a three-line loop. Libraries exist for things that are hard or easy to get wrong, not for things that are trivial.

## How you would discover `BigInteger` for large factorials

```text
"int overflows at 13!. I need bigger numbers."
        ↓
Search: "java arbitrary precision integer"
        ↓
Find: java.math.BigInteger
        ↓
Check the Javadoc:
  BigInteger.valueOf(long)  →  BigInteger
  bigInt.multiply(BigInteger) →  BigInteger
        ↓
Note: multiply RETURNS a new BigInteger —
      it does not modify in place,
      because BigInteger is immutable.
        ↓
So the accumulation must be written as:
  result = result.multiply(BigInteger.valueOf(i));
```

> [!tip]
> Notice that last step. Because `multiply` **returns** a new object rather than changing the existing one, you *must* assign the result back. Writing `result.multiply(...)` alone would compute the product and throw it away.
>
> This is the same trap as `str.trim()` on an immutable `String`. **Always check whether a method modifies in place or returns a new value** — the Javadoc's return type tells you immediately.

---

# 9. Trace the program with real values

```text
Input:   factorialFor = 6
Start:   factorial = 1
```

## Iteration table

```text
──────────────────────────────────────────────────────────────────────
Pass   i    i <= 6 ?   factorial (before)   × i    factorial (after)
──────────────────────────────────────────────────────────────────────
 —     —       —              1              —            1     ← start
 1     1     true             1            1 × 1          1
 2     2     true             1            1 × 2          2
 3     3     true             2            2 × 3          6
 4     4     true             6            6 × 4         24
 5     5     true            24           24 × 5        120
 6     6     true           120          120 × 6        720
 —     7    false           720             —           720     ← exit
──────────────────────────────────────────────────────────────────────
```

## Reading the first pass carefully

```text
i = 1:   factorial = 1 * 1 = 1
```

Nothing changes. That pass is mathematically pointless — multiplying by 1 never does anything.

> [!note] Could the loop start at 2?
> Yes:
> ```java
> for (int i = 2; i <= factorialFor; i++)
> ```
> This produces the identical answer while skipping the redundant first pass.
>
> But starting at `1` is better, because it matches the mathematical definition `n! = 1 × 2 × ... × n` exactly. **Code that mirrors its specification is easier to verify.** Saving one multiplication is not worth obscuring the relationship between the code and the definition.

## Reading the exit row

After the sixth pass, `i++` makes `i` equal `7`. The check `7 <= 6` is `false`, so the loop stops. `i` never contributes a seventh multiplication.

## Verify by hand

```text
1 × 1 = 1
1 × 2 = 2
2 × 3 = 6
6 × 4 = 24
24 × 5 = 120
120 × 6 = 720        ✓
```

## Console output

```text
720
```

Just the number — no label.

---

# 10. Visualize data where useful

## The accumulator growing

```text
i:            1       2       3       4       5       6
              │       │       │       │       │       │
              ×       ×       ×       ×       ×       ×
              │       │       │       │       │       │
factorial:  1 ┴──▶ 1 ┴──▶ 2 ┴──▶ 6 ┴──▶ 24 ┴──▶ 120 ┴──▶ 720
            ↑                                              ↑
        identity                                        answer
```

## Explosive growth — why overflow arrives so fast

```text
 1!  =                     1
 2!  =                     2
 3!  =                     6
 4!  =                    24
 5!  =                   120
 6!  =                   720   ← this program
 7!  =                 5,040
 8!  =                40,320
 9!  =               362,880
10!  =             3,628,800
11!  =            39,916,800
12!  =           479,001,600   ← the largest that fits in an int
─────────────────────────────────────────────────────────────
13!  =         6,227,020,800   ✗ int OVERFLOWS silently here
...
20!  = 2,432,902,008,176,640,000   ← the largest that fits in a long
21!  ✗ long overflows here too
```

Look at how few rows there are before it breaks. Every step multiplies by a larger number than the last, so the growth accelerates. This is why factorial is the standard example of "faster than exponential".

## The loop's control flow

```text
        int i = 1
            │
            ▼
      ┌─▶ i <= 6 ? ─── false ──▶ print factorial ──▶ done
      │     │ true
      │     ▼
      │  factorial = factorial * i
      │     │
      │     ▼
      │    i++
      └─────┘
```

## Sum vs product — the same skeleton, different filling

```text
        SUM                          PRODUCT (factorial)
─────────────────────────      ─────────────────────────
int sum = 0;                   int factorial = 1;
      ↑ identity for +               ↑ identity for ×

sum = sum + item;              factorial = factorial * i;
          ↑ operation                        ↑ operation

Two characters differ.
Everything else is identical.
```

---

# 11. Alternative ways to write the same logic

### Beginner-friendly version — labelled output

```java
int number = 6;
int factorial = 1;

for (int i = 1; i <= number; i++) {
    factorial *= i;
}

System.out.println("Factorial of " + number + " is: " + factorial);
```

Output:

```text
Factorial of 6 is: 720
```

Two improvements: `*=` states the intent more directly, and the output now explains itself. **This is the version to write in an interview.**

### Reusable version — extract a method

```java
public static int factorial(int n) {
    int result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
    return result;
}

public static void main(String[] args) {
    System.out.println("6! = " + factorial(6));
    System.out.println("5! = " + factorial(5));
    System.out.println("0! = " + factorial(0));
}
```

Why this is better:

- **Reusable** — compute any factorial without duplicating the loop.
- **Testable** — you can check `factorial(0) == 1` automatically.
- **Named** — the method name documents the intent.

Note that `factorial(0)` correctly returns `1`: the loop condition `1 <= 0` is `false` immediately, so the body never runs and `result` stays at its identity value. **Initialising to the identity handles the empty case for free** — exactly as it did for the empty array in [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]].

### Recursive version

```java
public static int factorial(int n) {
    if (n <= 1) {
        return 1;              // base case — stops the recursion
    }
    return n * factorial(n - 1);   // recursive case
}
```

This mirrors the mathematical definition `n! = n × (n-1)!` directly.

How it unwinds for `n = 4`:

```text
factorial(4)
  → 4 * factorial(3)
        → 3 * factorial(2)
              → 2 * factorial(1)
                    → 1              ← base case, stops here
              ← 2 * 1  = 2
        ← 3 * 2  = 6
  ← 4 * 6  = 24
```

> [!important] Every recursion needs a base case
> The `if (n <= 1) return 1;` is not optional decoration. Without it the method calls itself forever and the program dies with `StackOverflowError`.
>
> Two mandatory ingredients for any recursion:
> 1. A **base case** that returns without recursing.
> 2. A **recursive case** that moves measurably *closer* to the base case — here `n - 1`.
>
> Miss either one and you get infinite recursion.

**Loop or recursion?** For factorial, the loop is better: it is faster, uses constant memory, and cannot stack-overflow. The recursive version exists mainly to teach recursion. Interviewers often ask for both to see whether you understand the trade-off.

### Robust version — `long` for a wider range

```java
public static long factorial(int n) {
    long result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
    return result;
}
```

Extends the safe range from `12!` to `20!`. Still overflows eventually, just later.

### Fully correct version — `BigInteger`

```java
import java.math.BigInteger;

public static BigInteger factorial(int n) {
    BigInteger result = BigInteger.ONE;
    for (int i = 1; i <= n; i++) {
        result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
}
```

`BigInteger` has no size limit beyond available memory, so `100!` (a 158-digit number) computes exactly.

Two things to notice:

- `BigInteger.ONE` is the identity — the same concept, just spelled differently for this type.
- `result = result.multiply(...)` — you **must** assign the result back, because `BigInteger` is immutable and `multiply` returns a *new* object.

### Version with input validation

```java
public static int factorial(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("Factorial is undefined for negatives: " + n);
    }
    int result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
    return result;
}
```

The original silently returns `1` for negative input, which is a wrong answer disguised as a right one. Failing loudly is better than answering wrongly.

---

# 12. Common beginner mistakes

## Mistake 1 — initialising the accumulator to `0`

**Incorrect code**

```java
int factorial = 0;
for (int i = 1; i <= factorialFor; i++) {
    factorial = factorial * i;
}
```

**Why it is wrong**
`0 × anything = 0`. The accumulator can never escape zero, so the answer is always `0`.

**What Java expects**
The starting value must be the identity for the operation. For multiplication that is `1`.

**Correct code**

```java
int factorial = 1;
```

**How to recognise it in future**
If a product-style program always prints `0`, check the seed value first. And ask the general question: *"What value can I combine with, without changing the result?"*

---

## Mistake 2 — starting the counter at `0`

**Incorrect code**

```java
for (int i = 0; i <= factorialFor; i++) {
    factorial = factorial * i;
}
```

**Why it is wrong**
The first pass computes `1 * 0 = 0`, and every later multiplication keeps it at `0`.

**What Java expects**
Nothing — this compiles perfectly. It is a *logic* error, not a syntax error.

**Correct code**

```java
for (int i = 1; i <= factorialFor; i++) {
```

**How to recognise it**
Ask what `i` *means*. Here it is a value being multiplied, not an array index, so zero-based starting does not apply. Reflexively typing `int i = 0` is a habit worth breaking.

---

## Mistake 3 — using `<` instead of `<=`

**Incorrect code**

```java
for (int i = 1; i < factorialFor; i++) {
```

**Why it is wrong**
Stops at `5`, computing `120` (which is `5!`) rather than `720`.

**Why it is dangerous**
`120` is a plausible-looking number. Nothing crashes. You would only notice by checking the arithmetic.

**Correct code**

```java
for (int i = 1; i <= factorialFor; i++) {
```

**How to recognise it**
Ask before writing the operator: *"Must the boundary value itself be processed?"* For factorial, yes.

---

## Mistake 4 — using `+` instead of `*`

**Incorrect code**

```java
factorial = factorial + i;
```

**Why it is wrong**
Computes `1+1+2+3+4+5+6 = 22`, a sum rather than a product.

**Correct code**

```java
factorial = factorial * i;
```

**How to recognise it**
The compiler cannot help — both operators are valid on `int`. Only tracing catches it. This is why hand-tracing a small case is a habit worth building.

---

## Mistake 5 — declaring the accumulator inside the loop

**Incorrect code**

```java
for (int i = 1; i <= factorialFor; i++) {
    int factorial = 1;
    factorial = factorial * i;
}
System.out.println(factorial);   // error: cannot find symbol
```

**Why it is wrong**
`factorial` is recreated and reset each pass, and does not exist after the loop.

**Correct code**
Declare it before the loop.

**How to recognise it**
`cannot find symbol` on a variable you know you declared → you declared it in a narrower scope.

---

## Mistake 6 — writing `factorial = i` instead of `factorial = factorial * i`

**Incorrect code**

```java
factorial = i;
```

**Why it is wrong**
Replaces the running product instead of multiplying into it. The answer becomes `6` — just the last value of `i`.

**Correct code**

```java
factorial = factorial * i;
```

**How to recognise it**
If your "product" equals the final counter value, you forgot to include the accumulator on the right-hand side.

---

## Mistake 7 — ignoring overflow

**Incorrect assumption**

```java
int factorialFor = 20;
```

**Why it is wrong**
`20!` is about `2.4 × 10^18`, far beyond `int`'s limit of about `2.1 × 10^9`. The result wraps silently to a meaningless number — possibly negative.

**Correct approach**
Use `long` (safe to `20!`) or `BigInteger` (unlimited), and validate the input.

**How to recognise it**
If a factorial result is negative, or smaller than the previous factorial, you have overflowed. **A factorial can never be negative, so a negative result is proof of overflow.**

---

## Mistake 8 — a stray semicolon after the `for` header

**Incorrect code**

```java
for(int i=1; i<=factorialFor; i++);
{
    factorial = factorial * i;
}
```

**Why it is wrong**
The `;` becomes the loop body. The loop does nothing six times, and the block below fails to compile because `i` is out of scope.

**Correct code**
No semicolon after the header.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| The accumulator pattern | do you know why the seed is `1` and not `0`? |
| Loop bounds | do you use `<=` because 6 must be included? |
| Counter semantics | do you know why `i` starts at `1`, not `0`? |
| Overflow awareness | do you know `int` breaks at `13!`? |
| Recursion | can you write it both ways and compare them? |
| Edge cases | do you know `0!` is `1`, and that negatives are undefined? |

## Likely follow-up questions

> **"Why does `factorial` start at 1?"**

Because `1` is the identity for multiplication. Starting at `0` would zero the entire result. Give the general principle — "the seed must be the value that leaves the operand unchanged" — rather than just the specific answer.

> **"Write it recursively."**

Show the base case `if (n <= 1) return 1;` and the recursive case `return n * factorial(n-1);`. Emphasise that the base case is mandatory.

> **"Which is better, loop or recursion?"**

The loop: `O(1)` space versus `O(n)` stack frames, no `StackOverflowError` risk, and faster. Recursion is more elegant and mirrors the definition. For factorial specifically, prefer the loop.

> **"What is the time complexity?"**

`O(n)` — one multiplication per number from 1 to n.

> **"What is `0!`?"**

`1`, by definition — an empty product is the identity. Point out that the code already handles this correctly for free: the loop body never runs and `factorial` stays `1`.

> **"What happens for negative input?"**

The loop never runs and it returns `1` — a silently wrong answer, since factorial is undefined for negatives. Best practice is to throw `IllegalArgumentException`.

> **"What is the largest factorial an `int` can hold?"**

`12! = 479,001,600`. `13!` overflows. With `long`, `20!` is the limit. Beyond that you need `BigInteger`.

> **"How would you compute `100!`?"**

`BigInteger`, with `BigInteger.ONE` as the seed and `result = result.multiply(BigInteger.valueOf(i))`. Mention that `BigInteger` is immutable, so the result must be reassigned.

> **"Can you compute it without a loop or recursion?"**

`IntStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b)`. Note that `reduce`'s first argument is the identity — the same concept appearing explicitly in the API.

---

# 14. Complexity

## Time complexity: `O(n)`

Let `n` be the input number (here `6`).

The loop runs exactly `n` times, doing one multiplication, one comparison and one increment per pass — a fixed amount of work each time. Total work is proportional to `n`.

In beginner language:

> "To compute `6!` the program does 6 multiplications. To compute `100!` it does 100. Double the input and you double the work. Because the work grows in direct proportion to the input, we call it linear time and write it `O(n)`."

> [!note] A subtlety worth knowing
> This `O(n)` treats each multiplication as one fixed-cost operation, which is true for `int` and `long` because the CPU multiplies 32- or 64-bit numbers in constant time.
>
> With `BigInteger` it is no longer true — multiplying enormous numbers costs more as the numbers grow, so the real complexity is higher. Recognising when the "one operation = constant time" assumption breaks down is a genuinely advanced observation to volunteer.

**Can it be faster?** Not meaningfully. Computing `n!` requires combining `n` values, so `O(n)` multiplications is essentially the floor.

## Space complexity: `O(1)`

Three `int` variables — `factorialFor`, `factorial`, `i` — regardless of the input. Computing `100!` uses exactly the same amount of memory as `6!` (ignoring overflow).

> "The memory we need does not grow with the input, so it is constant: `O(1)`."

## The recursive version's complexity

| | Loop | Recursion |
|---|---|---|
| Time | `O(n)` | `O(n)` |
| Space | `O(1)` | **`O(n)`** |

Recursion is worse in space because each pending call keeps a stack frame alive:

```text
factorial(6) waiting for
  factorial(5) waiting for
    factorial(4) waiting for
      factorial(3) waiting for
        factorial(2) waiting for
          factorial(1) → returns 1
```

Six frames exist simultaneously. For `n = 100,000`, that is 100,000 frames — and the JVM's stack is finite, so it throws `StackOverflowError`.

This is exactly the trade-off an interviewer wants you to articulate.

---

# 15. Edge cases

## `factorialFor = 0`

```java
int factorialFor = 0;
```

- Condition `1 <= 0` is `false` on the first check.
- The body never runs.
- `factorial` stays `1`.
- Output: `1`

**Correct.** `0! = 1` by mathematical definition. The code handles it without any special case, purely because the seed is the identity value.

## `factorialFor = 1`

One pass: `1 * 1 = 1`. Output `1`. Correct.

## `factorialFor = 12`

```text
12! = 479,001,600
Integer.MAX_VALUE = 2,147,483,647
```

Fits. This is the **largest input an `int` version handles correctly**.

## `factorialFor = 13` — silent overflow

```text
True value:   6,227,020,800
Printed:      1,932,053,504
```

> [!warning] Silent integer overflow
> Java gives no exception, no warning, no message. The value wraps around like an odometer.
>
> Worse, the wrapped value is a plausible-looking positive number. Nothing about the output announces that it is wrong.
>
> **Fixes, in increasing order of robustness:**
> ```java
> long factorial = 1;                        // safe to 20!
> BigInteger factorial = BigInteger.ONE;     // unlimited
> ```
> Or detect it:
> ```java
> if (factorialFor > 12) {
>     System.out.println("Result will overflow an int");
> }
> ```
>
> **A negative factorial result is always proof of overflow**, since real factorials are always positive.

## `factorialFor = 20` and `21`

`20!` fits in a `long` (`2,432,902,008,176,640,000`). `21!` overflows even a `long`. Beyond 20, `BigInteger` is the only correct choice.

## Negative input

```java
int factorialFor = -5;
```

- Condition `1 <= -5` is `false` immediately.
- Output: `1`

> [!warning] A wrong answer that looks right
> Factorial is **undefined** for negative numbers. Returning `1` is not "close enough" — it is simply incorrect, and it is indistinguishable from the correct answer for `0!`.
>
> The current implementation performs no validation. A defensive version:
>
> ```java
> if (factorialFor < 0) {
>     throw new IllegalArgumentException("Factorial undefined for: " + factorialFor);
> }
> ```
>
> Failing loudly beats answering wrongly. This is a genuinely good point to raise in an interview.

## Very large input

```java
int factorialFor = 1000000;
```

The loop runs a million times and finishes quickly — but the answer overflowed long ago and is meaningless. **Fast and wrong is still wrong.**

## Can it crash?

No. There is no array access, no division, no object dereference, no input parsing. The program always terminates and never throws. Its failure mode is *silent incorrectness*, not an exception — which is precisely why the overflow discussion matters so much here.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Compute the factorial of 6.

        ↓

What does factorial mean?
6! = 1 × 2 × 3 × 4 × 5 × 6 = 720

        ↓

What data do I have?
One whole number, the target.
int factorialFor = 6;

        ↓

What do I need to produce?
One whole number, the product.

        ↓

What shape is this problem?
A range of numbers in, one number out.
That is a reduction — same shape as summing.

        ↓

Every reduction needs three things:
(a) a place for the running answer
(b) a starting value that changes nothing
(c) a rule applied to each item

        ↓

(a) Where does the running answer live?
A variable. Type must match what I am
multiplying → int factorial

        ↓

(b) What starting value leaves a product unchanged?
Multiplying by 1 changes nothing → 1
NOT 0 — zero annihilates every product.
int factorial = 1;

        ↓

(c) What is the rule for one number?
"Multiply the running product by it"
factorial = factorial * i;

        ↓

Where do the numbers 1..6 come from?
A loop that counts.

        ↓

Where should the counter start?
Careful — is i an index or a value?
It is a VALUE I multiply by, not an array position.
The definition starts at 1 → int i = 1

        ↓

Where should it stop?
6 itself must be multiplied in → i <= factorialFor
(i < factorialFor would compute 5! = 120)

        ↓

How does the counter advance?
i++

        ↓

Where must factorial be declared?
BEFORE the loop — it must survive every pass
and still exist for printing afterwards.

        ↓

Trace to verify:
1 → 1 → 2 → 6 → 24 → 120 → 720   ✓

        ↓

Sanity-check the edges:
factorialFor = 0 → loop never runs → 1  ✓ (0! = 1)
factorialFor = 13 → overflows int       ⚠ note the limit

        ↓

Report the answer:
System.out.println(factorial);
(better: label it —
 "Factorial of " + factorialFor + " is: " + factorial)
```

> [!important] The four real decisions
> Everything else was mechanical. Four choices needed actual thought:
>
> 1. **Seed `1`, not `0`** — because 1 is the identity for multiplication.
> 2. **Start `i` at `1`, not `0`** — because `i` is a value, not an index.
> 3. **`<=`, not `<`** — because 6 must be included.
> 4. **Declare `factorial` outside the loop** — because it must outlive each pass.
>
> Each came from asking a question, not from remembering a program. Learn the questions.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int factorialFor = 6;` | "Make a whole-number box called `factorialFor` and put `6` in it — this is the number we want the factorial of." |
| `int factorial = 1;` | "Make a whole-number box called `factorial` and start it at `1`, because multiplying by 1 changes nothing, so this is a safe empty starting point for a product." |
| `for(int i=1; i<=factorialFor; i++)` | "Make a counter `i` starting at 1. As long as `i` has not gone past `factorialFor`, run the body, then add 1 to `i`." |
| `factorial = factorial * i;` | "Take whatever is currently in `factorial`, multiply it by the current counter value, and put the answer back into `factorial`." |
| `System.out.println(factorial);` | "Print whatever number is in `factorial`, then move to a new line." |

The loop in one sentence:

> **"Walk through the numbers 1, 2, 3, 4, 5, 6 and multiply each one into the running product."**

And the whole program:

> **"Start a running product at 1, multiply it by every whole number from 1 up to and including 6, then print the result."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| accumulator | a variable declared before a loop that builds up an answer |
| identity value | the seed that leaves the result unchanged — `0` for `+`, `1` for `*` |
| counting loop | `i` is a value you compute with, so it starts where the maths starts |
| index loop | `i` is a position, so it starts at `0` |
| scope | a variable lives only inside the `{ }` where it was declared |
| `<=` vs `<` | decided by asking whether the boundary value must be processed |
| integer overflow | `int` silently wraps past ±2.1 billion — factorial breaks at `13!` |
| recursion | a method calling itself; needs a base case plus progress toward it |
| immutability | `BigInteger` returns new objects, so results must be reassigned |

### Important methods

| Member | Owner | Accepts | Returns |
|---|---|---|---|
| `println(int)` | `java.io.PrintStream` | an `int` | `void` |
| `BigInteger.valueOf(long)` | `java.math.BigInteger` | a `long` | `BigInteger` |
| `multiply(BigInteger)` | `BigInteger` | a `BigInteger` | a **new** `BigInteger` |
| `BigInteger.ONE` | `BigInteger` | — (a constant) | the identity for multiplication |

There is deliberately **no** `Math.factorial()` in Java.

### Important syntax

| Syntax | Meaning |
|---|---|
| `for (a; b; c)` | initialise once; test before each pass; update after each pass |
| `<=` | less than or equal — includes the boundary value |
| `*` | multiplication |
| `*=` | shorthand for `x = x * y` |
| `i++` | add one to `i` |
| `=` | assignment — evaluate the right side, store into the left |
| `{ }` | a block, which also defines scope |
| `;` inside `for(...)` | separates the three sections — exactly two of them |

### Main interview concept

> **The seed value must be the identity for the operation.** `0` for addition, `1` for multiplication, `""` for string building. Derive it by asking *"what value can I combine with, without changing anything?"* — never memorise it. As a bonus, the identity seed makes the empty case (`0!`, an empty array) come out correct for free.

### Main lesson for code reading

> **Two programs with the same skeleton are the same program.**
>
> ```text
> SumOfArrayEle          Factorials
> ───────────────        ───────────────
> int sum = 0;           int factorial = 1;
> for (...) {            for (...) {
>   sum = sum + x;         factorial = factorial * i;
> }                      }
> ```
>
> When you meet unfamiliar code, do not read it word by word. Ask: *Is there an accumulator? What is its seed? What is the per-item rule? What is read after the loop?* Those four answers describe the whole loop — and they let you recognise a pattern you already know wearing unfamiliar clothes.

---

### Related notes

- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — the same skeleton with seed `0` and `+`
- [[Java_FabionacciSeries_Code_Reading_Explanation_Obsidian|FabionacciSeries]] — a loop carrying two running values instead of one
- [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]] — accumulating into a `String` with seed `""`
- [[Java_PrimeOrNot_Code_Reading_Explanation_Obsidian|PrimeOrNot]] — a counting loop with `%` and a counter accumulator
- [[Java_LargestArrayElement_Code_Reading_Explanation_Obsidian|LargestArrayElement]] — an accumulator seeded from the data instead of an identity
