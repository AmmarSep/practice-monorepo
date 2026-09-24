---
title: Java Code Reading — PrimeOrNot
tags:
  - java
  - interview-programs
  - code-reading
  - loops
  - math
  - conditionals
aliases:
  - PrimeOrNot Explained
  - Prime Number Check in Java
---

# Java Code Reading — PrimeOrNot Explained for a Fresh Java Programmer

> [!note]
> This program teaches **nested conditions** — an `if` inside another `if` — and it demonstrates a very common beginner strategy: instead of answering the question directly, **count something and then check the count**.
>
> That indirection is worth studying. The program never asks "is this prime?" It asks "how many divisors does it have?" and then translates the count into an answer. Recognising when to convert a hard question into an easier countable one is a genuinely useful problem-solving move.
>
> The implementation is correct but does far more work than necessary — and understanding *why* leads naturally to one of the most satisfying optimisations in beginner programming.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Can I answer directly, or must I count first? → What am I counting? → What count means yes?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class PrimeOrNot
{
    public static void main(String[] args)
    {
        int numpr = 15;

        int count = 0;

        if (numpr > 1)
        {
            for (int i = 1; i <= numpr; i++)
            {
                if (numpr % i == 0)
                    count++;
            }
            if (count == 2)
            {
                System.out.println("This number is a prime number");
            } 
            else 
            {
                System.out.println("This number is not a prime number");
            }
        } 
        else 
        {
            System.out.println("Not a prime number");
        }
    }
}
```

The executable logic:

```java
int numpr = 15;
int count = 0;

if (numpr > 1) {
    for (int i = 1; i <= numpr; i++) {
        if (numpr % i == 0) count++;
    }
    if (count == 2) { /* prime */ } else { /* not prime */ }
} else {
    /* not prime */
}
```

With `numpr = 15` the divisors are `1, 3, 5, 15` — a count of `4`, so the output is **"This number is not a prime number"**.

---

# 2. What problem is this program solving?

In plain language:

> Decide whether a number is prime, and say so.

### What is a prime number?

> A whole number **greater than 1** whose only positive divisors are **1 and itself**.

```text
 2  → divisors: 1, 2           → exactly 2 → PRIME
 3  → divisors: 1, 3           → exactly 2 → PRIME
 4  → divisors: 1, 2, 4        → 3 divisors → not prime
15  → divisors: 1, 3, 5, 15    → 4 divisors → not prime
 1  → divisors: 1              → only 1 → not prime (by definition)
```

### What do we know?

- One whole number.
- The definition above, which has **two separate parts**: "greater than 1" and "only divisible by 1 and itself".

### What do we need?

- A yes/no answer, printed.

### What transformations are required?

Here is the design insight that shapes the whole program.

> [!important] Turning a hard question into a countable one
> "Is this number prime?" is not something you can compute with a single operation. But look at the definition again:
>
> > "...whose **only** positive divisors are 1 and itself."
>
> Restated: **a prime has exactly two divisors.**
>
> That reformulation is the key. "Exactly two" is a *count*, and counting is easy:
>
> ```text
> Hard question:   "Is 15 prime?"
>         ↓ reformulate using the definition
> Easy question:   "How many divisors does 15 have?"
>         ↓ count them
> Answer:          4
>         ↓ translate back
> Conclusion:      4 ≠ 2, so not prime
> ```
>
> **When a question is hard to answer directly, look for a countable quantity the definition mentions.** This move — replacing a predicate with a count — appears constantly in real programming.

### The two-part definition needs two-part code

Notice that the definition has a **precondition** ("greater than 1") separate from the **main rule** ("only two divisors").

That is exactly why the program has an outer `if` wrapping the counting logic:

```text
if (numpr > 1)          ← the precondition
    count divisors      ← the main rule
    check the count
else
    not prime           ← fails the precondition
```

**The shape of the code mirrors the shape of the definition.** When you find yourself unsure how to structure a program, re-read the specification and count its clauses.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder. First statement in the file.

## `public class PrimeOrNot`

- `public` — usable from anywhere.
- `class` — declares a class.
- `PrimeOrNot` — `PascalCase`, matching `PrimeOrNot.java`.

The opening brace sits on its own line, as in [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]]. Java accepts both styles; the same-line form is more common. Purely cosmetic.

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

`int`, `String`, `System` only — primitives and `java.lang`, so nothing to import.

## Variables

| Variable | Type | Role |
|---|---|---|
| `numpr` | `int` | the input — the number being tested |
| `count` | `int` | the accumulator — how many divisors found |
| `i` | `int` | the counter — the candidate divisor |

The familiar trio again: **data, accumulator, counter**. But notice that `count` here is a *counting* accumulator, not a summing one — its update is `count++`, not `count = count + something`.

## Structure — three levels of nesting

```text
if (numpr > 1)                    ← level 1
    for (...)                     ← level 2
        if (numpr % i == 0)       ← level 3
            count++;
    if (count == 2) ... else ...  ← level 2
else ...                          ← level 1
```

Three levels is about the limit of what reads comfortably. Section 11 shows how extracting a method flattens it.

---

# 4. Line-by-line explanation

## 4.1 — `int numpr = 15;`

```java
int numpr = 15;
```

**What is this?**
The number to test.

**Why do we need it?**
The program needs something to work on. Hard-coding it keeps the program simple and its output predictable; a real version would read it from the user, as [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]] does.

**What type and why?**
Primality is defined only for whole numbers, and we will use `%` on it, so `int`. `double` would be meaningless — there is no such thing as a prime `2.5`.

**Why the name `numpr`?**
Presumably "number prime". It is cramped and slightly cryptic. `number`, `candidate`, or simply `n` would read better. Worth noticing because you will inherit names like this and must learn to read past them.

**What does Java do?**
Reserves 32 bits, labels it `numpr`, writes `15`.

---

## 4.2 — `int count = 0;`

```java
int count = 0;
```

**What is this?**
The accumulator — a tally of how many divisors have been found.

**Why do we need it?**
The loop examines candidate divisors one at a time. Something must remember how many succeeded, and that memory must survive from one pass to the next.

**How would a beginner know to write it?**
Ask: **"Do I need to remember something between one loop pass and the next?"** Yes — the running tally. Anything that survives iterations is declared **before** the loop.

**Why the starting value `0`?**

Before checking anything, we have found zero divisors. That is both the truthful starting state and the identity for addition — adding to `0` gives exactly what you added.

```text
count = 0
found a divisor → count = 1
found another   → count = 2
```

Starting at `1` would claim we had already found a divisor before looking, and every answer would be off by one.

**What type?**
A whole number of things → `int`.

**Where is it declared?**

Outside the outer `if`, at the top of `main`. It could equally have been declared inside the `if (numpr > 1)` block, since that is the only place it is used:

```java
if (numpr > 1) {
    int count = 0;          // also valid — narrower scope
    ...
}
```

> [!tip] Declare variables in the narrowest scope that works
> A variable visible in more places than it needs to be is a small invitation to misuse it. Declaring `count` inside the `if` would tell the reader "this only matters here".
>
> This is a minor style point, not a bug. But building the habit early makes larger programs much easier to reason about.

---

## 4.3 — `if (numpr > 1)`

```java
if (numpr > 1)
```

**What is this?**
The precondition check — the first half of the definition of "prime".

**Why do we need it?**

Because the counting logic **gives the wrong answer for `1`**, and for anything below it.

Trace `numpr = 1`:

```text
i = 1:  1 % 1 == 0  →  count = 1
loop ends (i <= 1)
count == 2 ?  1 == 2  →  false  →  "not a prime number"
```

That happens to be right. But now trace `numpr = 0` **without** the guard:

```text
i = 1:  0 % 1 == 0  →  count = 1
loop ends (i <= 0 is false after i becomes 2... )
```

Actually, with `numpr = 0` the loop condition `1 <= 0` is false immediately, so `count` stays `0` and the answer is "not prime" — also right by accident.

The real reason the guard matters is **clarity and correctness by construction**, not luck:

> [!important] Why `1` is not prime, and why it needs its own branch
> `1` has exactly **one** divisor: itself. A prime needs exactly **two**.
>
> This is not an arbitrary rule. If `1` were prime, the Fundamental Theorem of Arithmetic — every number has a *unique* prime factorisation — would break, because you could insert any number of `1`s:
>
> ```text
> 12 = 2 × 2 × 3
>    = 1 × 2 × 2 × 3
>    = 1 × 1 × 2 × 2 × 3      ← no longer unique
> ```
>
> So mathematics excludes `1` by definition, and the definition says "greater than 1" explicitly. **The code has an outer `if` because the definition has a precondition.**
>
> Interviewers ask "is 1 prime?" precisely because it separates people who know the definition from people who know a formula.

**What does `>` produce?**
A `boolean`. `15 > 1` is `true`, so the main branch runs.

**Why `>` and not `>=`?**

```text
numpr > 1   →  1 fails the test  →  handled by else  ✓
numpr >= 1  →  1 passes          →  counted, gets 1 divisor, reported not prime
```

Both end up printing "not prime" for `1`, but `> 1` states the rule directly instead of relying on the count to sort it out. **Code that mirrors its specification is easier to verify.**

---

## 4.4 — `for (int i = 1; i <= numpr; i++)`

```java
for (int i = 1; i <= numpr; i++)
```

**What is this?**
A loop that tries every candidate divisor from `1` up to `numpr` itself.

**Why do we need it?**
To count divisors, we must test each possible one.

**The three sections**

```text
for ( int i = 1 ; i <= numpr ; i++ )
          │           │          │
          │           │          └── after each pass
          │           └───────────── before each pass
          └───────────────────────── once, at the start
```

**Why does `i` start at `1` and not `0`?**

Two reasons, and the second is critical.

1. **`1` is a genuine divisor** of every number, so it must be counted.
2. **`numpr % 0` would crash.**

```java
int x = 15 % 0;      // ArithmeticException: / by zero
```

> [!warning] Division and modulo by zero
> ```text
> Exception in thread "main" java.lang.ArithmeticException: / by zero
> ```
>
> Starting the loop at `0` would throw on the very first pass. Here `i` is a **value used in arithmetic**, not an array index — so the zero-based habit does not apply.
>
> This is the third distinct meaning of `i` you have met:
>
> | Meaning | Example | Starts at |
> |---|---|---|
> | array **position** | [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian\|SumOfArrayEle]] — `arr[i]` | `0` |
> | arithmetic **value** | **this program**, [[Java_Factorials_Code_Reading_Explanation_Obsidian\|Factorials]] — `numpr % i` | `1` |
> | repetition **tally** | [[Java_FabionacciSeries_Code_Reading_Explanation_Obsidian\|FabionacciSeries]] — `i` unused in the body | `0` |
>
> **Ask what `i` means before choosing where it starts.** Reflexively typing `int i = 0` is a habit worth breaking.

**Why `i <= numpr` and not `i < numpr`?**

Because `numpr` divides itself, and that divisor must be counted.

```text
i <= 15  →  1,2,...,15   →  finds 1,3,5,15  →  count = 4   ✓
i <  15  →  1,2,...,14   →  finds 1,3,5     →  count = 3   ✗
```

With `<`, **every** number would be reported as not prime, because the count could never reach `2` for a prime (a prime's divisors are `1` and itself, and itself would be excluded).

Ask the standard question: **"must the boundary value itself be processed?"** Yes — so `<=`.

**What is `i++`?**
The increment operator. Without it the loop never ends.

---

## 4.5 — `if (numpr % i == 0)`

```java
if (numpr % i == 0)
    count++;
```

**What is this?**
The divisor test.

**Why do we need it?**
Not every candidate is a divisor. This filters the ones that are.

**What does each part mean?**

```text
if ( numpr   %   i   ==   0 )
       │     │   │   │    │
       │     │   │   │    └── remainder must be zero
       │     │   │   └─────── equality comparison
       │     │   └─────────── the candidate divisor
       │     └─────────────── remainder operator
       └───────────────────── the number being tested
```

**How would a beginner know that `% == 0` means "divides evenly"?**

Derive it rather than memorise it:

```text
"i divides numpr"
        ↓ means
"dividing numpr by i leaves nothing over"
        ↓ and "what is left over" is
the remainder
        ↓ and Java's remainder operator is
%
        ↓ therefore
numpr % i == 0
```

Exactly the same derivation as the even test in [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] — `n % 2 == 0` is just this with `i` fixed at `2`.

**Worked examples with `numpr = 15`:**

```text
15 % 1  = 0   →  1 divides 15    ✓ count
15 % 2  = 1   →  no
15 % 3  = 0   →  3 divides 15    ✓ count
15 % 4  = 3   →  no
15 % 5  = 0   →  5 divides 15    ✓ count
15 % 6  = 3   →  no
...
15 % 15 = 0   →  15 divides 15   ✓ count
```

**Operator precedence**

`%` binds tighter than `==`, so `numpr % i == 0` already means `(numpr % i) == 0`. No parentheses needed.

**What is `count++`?**

Shorthand for `count = count + 1`.

| Form | Meaning |
|---|---|
| `count++` | add one |
| `count += 1` | add one |
| `count = count + 1` | add one |

All three are identical here. `count++` is the idiomatic form for "one more".

> [!note] This is a *counting* accumulator
> Compare the two accumulator shapes you have now seen:
>
> ```java
> sum = sum + arr[i];    // SUMMING — adds the item's VALUE
> count++;               // COUNTING — adds 1, ignoring the value
> ```
>
> Both start at `0`. The difference is whether you care *what* you found or only *that* you found it. Here we only need the tally, so `count++` is right.

---

## 4.6 — the missing braces

```java
if (numpr % i == 0)
    count++;
```

Notice there are **no braces** around `count++`.

Java allows a single statement after `if` without braces. This compiles and works correctly.

> [!warning] Write the braces anyway — always
> Without braces, adding a second line later silently breaks the logic:
>
> ```java
> if (numpr % i == 0)
>     count++;
>     System.out.println("Found divisor: " + i);   // ← NOT in the if!
> ```
>
> Despite the indentation, that print runs on **every** iteration, not only when a divisor is found. The indentation says one thing and the compiler does another.
>
> **Indentation is for humans; braces are for the compiler. When they disagree, the compiler wins.**
>
> This is not a hypothetical concern — it caused a real, widely-publicised security vulnerability in production software. Always write the braces:
>
> ```java
> if (numpr % i == 0) {
>     count++;
> }
> ```

Note that the rest of this program *does* use braces consistently. This one spot is the exception.

---

## 4.7 — `if (count == 2)`

```java
if (count == 2)
{
    System.out.println("This number is a prime number");
} 
else 
{
    System.out.println("This number is not a prime number");
}
```

**What is this?**
Translating the count back into an answer.

**Why exactly `2`?**

Because a prime has exactly two divisors: `1` and itself.

```text
count = 1  →  only itself  →  the number is 1  →  not prime
count = 2  →  1 and itself →  PRIME
count > 2  →  extra divisors exist  →  not prime
```

> [!important] The count is a proxy for the answer
> This is the payoff of the reformulation in section 2. We never wrote code that "checks primality" — we wrote code that counts, and then read the count.
>
> ```text
> count == 2  ⟺  the number is prime
> ```
>
> Being able to move between "the property I want" and "a number I can compute" is a core problem-solving skill. It shows up everywhere: checking for duplicates by counting occurrences, detecting balance by counting brackets, validating input by counting matches.

**Why `==` and not `.equals()`?**
`count` is an `int` **primitive**. Primitives use `==`; objects use `.equals()`. Writing `count.equals(2)` gives `int cannot be dereferenced`.

**Why `if / else` rather than a ternary?**
We are choosing between two **actions** (which message to print), not two values to store. `if` is the right tool — see [[Java_TernaryOperatorTime_Code_Reading_Explanation_Obsidian|TernaryOperatorTime]] for the distinction.

---

## 4.8 — the outer `else`

```java
else 
{
    System.out.println("Not a prime number");
}
```

**What is this?**
The branch for numbers that fail the precondition — `1`, `0`, and negatives.

**Why is it needed?**
Because the definition explicitly requires "greater than 1". Without this branch, those inputs would fall through the counting logic and produce answers by accident rather than by design.

**An inconsistency worth noticing**

The three messages have three different shapes:

```text
"This number is a prime number"          ← main branch, prime
"This number is not a prime number"      ← main branch, not prime
"Not a prime number"                     ← outer else  ← different wording!
```

The third omits "This number is". None of them shows the actual number.

More consistent and more useful:

```java
System.out.println(numpr + " is a prime number");
System.out.println(numpr + " is not a prime number");
System.out.println(numpr + " is not a prime number");
```

Small, but **consistent output is easier to read, easier to test, and easier to trust**.

---

# 5. How to think like the programmer

```text
Requirement
"Decide whether a number is prime"
        ↓
What is the definition?
"A whole number greater than 1 whose only
 positive divisors are 1 and itself"
        ↓
That definition has TWO parts:
(a) greater than 1        ← a precondition
(b) only 1 and itself     ← the main rule
        ↓
So the code needs two parts too:
an outer if for (a), the logic for (b) inside it
        ↓
Can I check (b) directly?
Not in one operation. But restate it:
"only 1 and itself" = "exactly TWO divisors"
        ↓
"Exactly two" is a COUNT — and counting is easy
        ↓
So: count the divisors, then check if the count is 2
        ↓
What do I count into?
An accumulator → int count = 0
Declared before the loop so it survives every pass
        ↓
How do I test one candidate?
"i divides numpr" = "no remainder"
→ numpr % i == 0
        ↓
Which candidates do I try?
Every whole number from 1 to numpr
        ↓
Why start at 1, not 0?
Two reasons:
  1 is a real divisor
  numpr % 0 would throw ArithmeticException
        ↓
Why <= numpr, not < numpr?
Because numpr divides itself, and that
divisor must be counted.
(With < , no number could ever reach 2.)
        ↓
What does the tally mean?
count == 2 → prime
anything else → not prime
        ↓
Trace 15:
divisors 1,3,5,15 → count 4 → not prime  ✓
        ↓
Check the edges:
2  → 1,2      → count 2 → prime      ✓
1  → caught by the outer if          ✓
0, negatives → caught by the outer if ✓
```

> [!important] The transferable move
> **When a property is hard to test directly, find a number the definition mentions and compute that instead.**
>
> | Property | Countable proxy |
> |---|---|
> | "is prime" | number of divisors == 2 |
> | "has duplicates" | any character's occurrence count > 1 |
> | "is balanced" | open brackets minus closed == 0 |
> | "is a perfect number" | sum of proper divisors == the number |
>
> Every one of these replaces a yes/no question with an arithmetic one. That substitution is the actual skill.

---

# 6. Deep explanation of important Java concepts used

## The modulo operator `%`

`a % b` gives the remainder after dividing `a` by `b`.

```text
       15 ÷ 4
      ┌────┴────┐
   quotient  remainder
      3          3
    (15/4)     (15%4)
```

| Test | Meaning |
|---|---|
| `n % i == 0` | `i` divides `n` exactly |
| `n % 2 == 0` | `n` is even |
| `n % 10` | the last digit of `n` |
| `n / 10` | `n` with the last digit removed |

> [!warning] `%` by zero throws
> ```java
> int x = 15 % 0;      // ArithmeticException: / by zero
> ```
> Both `/` and `%` require a non-zero right operand. This is why the loop starts at `1`.
>
> Note that **floating-point** division does *not* throw — `15.0 / 0` gives `Infinity`. Only integer division and modulo throw. A genuinely surprising asymmetry.

## Counting accumulator

```java
int count = 0;           // before the loop
for (...) {
    if (test) {
        count++;         // add one, regardless of the value
    }
}
use(count);              // after the loop
```

| Accumulator kind | Update | Used for |
|---|---|---|
| summing | `sum += value` | totals |
| counting | `count++` | tallies |
| selecting | `best = value` (conditional) | max, min |
| building | `s = s + piece` | strings |

All four share the same skeleton: **declare before, update inside, read after**.

## Nested `if` statements

```java
if (outer) {
    if (inner) {
        // both true
    }
}
```

The inner condition is evaluated **only** when the outer one passed. That ordering is what makes preconditions work — you can safely assume the outer condition holds inside.

> [!tip] Nesting versus `&&`
> Two levels of `if` can often be flattened with `&&`:
>
> ```java
> if (a) { if (b) { X } }        // nested
> if (a && b) { X }              // flattened — equivalent
> ```
>
> But **not here**, because each level has its own `else` with a different message. When branches diverge, nesting is the honest structure.
>
> Java's `&&` is **short-circuiting**: if `a` is `false`, `b` is never evaluated. That makes `if (arr != null && arr.length > 0)` safe — the second test never runs when the first fails.

## `if` / `else` chains

```java
if (condition) {
    // condition true
} else {
    // condition false
}
```

Exactly one branch runs. `else` covers everything the `if` did not, with no re-testing needed.

## The increment operator `++`

```java
count++;      // post-increment: yield the old value, then add 1
++count;      // pre-increment:  add 1, then yield the new value
```

As a standalone statement the two are identical. The distinction only matters inside a larger expression.

## Braces and single statements

```java
if (cond)
    statement;          // legal — one statement only

if (cond) {
    statement;          // preferred — always safe to extend
}
```

## `==` for primitives

`count` and `numpr` are `int` primitives, so `==` compares their values — which is exactly right. `.equals()` would not compile.

## Scope

```java
int numpr = 15;                          // scope: all of main
int count = 0;                           // scope: all of main

if (numpr > 1) {                         // block scope
    for (int i = 1; i <= numpr; i++) {   // i: this loop only
        ...
    }
    // i no longer exists
    if (count == 2) { }                  // count still visible  ✓
}
```

---

# 7. Why this syntax?

## `<=` vs `<` in the loop

| Condition | Candidates tried | `count` for 15 | Verdict |
|---|---|---|---|
| `i <= numpr` | 1..15 | 4 | ✓ correct |
| `i < numpr` | 1..14 | 3 | ✗ misses the number itself |

With `<`, no number could ever reach a count of `2`, so **everything** would be reported as not prime. A prime like `7` would find only `1` and be misreported.

## `i = 1` vs `i = 0`

```java
for (int i = 0; ...)     // numpr % 0 → ArithmeticException on pass 1
for (int i = 1; ...)     // ✓ correct
```

## `>` vs `>=` in the precondition

```java
if (numpr > 1)      // 1 is excluded — matches the definition  ✓
if (numpr >= 1)     // 1 is included, then rejected by the count
```

Both give the right output for `1`, but `> 1` states the rule directly.

## `==` vs `=`

`==` asks; `=` stores. `if (count = 2)` fails to compile because `if` requires a `boolean`.

## `%` vs `/`

```text
15 % 4  →  3     the remainder — used for divisibility
15 / 4  →  3     the quotient — truncated, not rounded
```

Coincidentally equal here. Try `15 % 2` (`1`) versus `15 / 2` (`7`) to see the difference clearly.

## `count++` vs `count += 1` vs `count = count + 1`

Identical. `count++` is idiomatic for "one more".

## Braces on a single statement

Optional after `if`, but always write them. See the warning in section 4.6.

## `if / else` vs the ternary

Choosing an **action** → `if`. Choosing a **value** → `? :`.

---

# 8. Method discovery

## `println`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.io.PrintStream` |
| **Why can we call it?** | `System.out` is a `PrintStream` object |
| **Which overload runs?** | `println(String)` in all three branches |
| **Returns** | `void` |

## Is there a built-in `isPrime()`?

Type `Math.` in IntelliJ:

```text
abs()  max()  min()  pow()  sqrt()  floor()  ceil()  round()
random()  signum()  floorMod()  floorDiv()  cbrt()  hypot()  ...
```

**No `isPrime()`.** It does not exist in `Math`, and it does not exist in `Integer` either.

> [!important] But something related *does* exist
> Search for "java isProbablePrime" and you find:
>
> ```java
> import java.math.BigInteger;
>
> boolean prime = BigInteger.valueOf(15).isProbablePrime(20);
> ```
>
> | Question | Answer |
> |---|---|
> | **Which class owns it?** | `java.math.BigInteger` |
> | **Accepts** | an `int` "certainty" parameter |
> | **Returns** | `boolean` |
> | **Why "Probable"?** | it uses a **probabilistic** test — the certainty parameter controls the error probability |
>
> Read that method name carefully. **`isProbablePrime`, not `isPrime`.** The library is telling you something important: for very large numbers, exact primality testing is expensive, so the practical algorithms are probabilistic.
>
> **Method names are documentation.** A name containing "Probable", "Unsafe", "Try", or "Maybe" is warning you about the contract before you even open the Javadoc.

## Discovering `Math.sqrt` — the optimisation

The most valuable discovery here comes from asking a question about the algorithm rather than the API:

```text
"Do I really need to test every number up to 15?"
        ↓
Think about divisors in pairs:
  15 = 1 × 15
  15 = 3 × 5
  15 = 5 × 3      ← the same pair, reversed
  15 = 15 × 1     ← the same pair, reversed
        ↓
Every divisor pair has one member ≤ √n
and one member ≥ √n.
        ↓
So if no divisor exists below √n,
none exists above it either.
        ↓
I only need to test up to √n.
        ↓
Is there a square-root method?
Type Math. → sqrt()
        ↓
Math.sqrt(double) → returns double
        ↓
So the loop condition becomes:
  i <= Math.sqrt(numpr)
or better, avoiding floating point:
  i * i <= numpr
```

| Question | Answer |
|---|---|
| **Which class owns `sqrt`?** | `java.lang.Math` |
| **Why callable without an object?** | it is `static` — call it on the class |
| **Accepts** | a `double` |
| **Returns** | a `double` |
| **Why prefer `i * i <= n`?** | it stays in integer arithmetic, avoiding floating-point rounding entirely |

> [!tip]
> Notice that this discovery came from **reasoning about the problem**, not from browsing an API. Autocomplete would never have suggested "test fewer numbers".
>
> **Method discovery answers "what tool exists?". Algorithmic thinking answers "do I need to do this much work?"** Both matter, and the second is what interviews usually probe.

---

# 9. Trace the program with real values

## Case A — `numpr = 15` (the program's actual input)

```text
numpr = 15,  count = 0
Precondition:  15 > 1  →  true, enter the main branch
Loop:  i from 1 to 15
```

```text
────────────────────────────────────────────────────────
Pass   i    15 % i    == 0 ?    count after
────────────────────────────────────────────────────────
  1    1      0       true          1     ← 1 divides 15
  2    2      1       false         1
  3    3      0       true          2     ← 3 divides 15
  4    4      3       false         2
  5    5      0       true          3     ← 5 divides 15
  6    6      3       false         3
  7    7      1       false         3
  8    8      7       false         3
  9    9      6       false         3
 10   10      5       false         3
 11   11      4       false         3
 12   12      3       false         3
 13   13      2       false         3
 14   14      1       false         3
 15   15      0       true          4     ← 15 divides 15
────────────────────────────────────────────────────────
Final count = 4
```

Final check:

```text
count == 2 ?   4 == 2   →  false
```

Output:

```text
This number is not a prime number
```

**Correct** — 15 = 3 × 5.

> [!note] Notice how much work was wasted
> The answer was decided at pass 3, when `3` was found to be a divisor. From that moment `count` could only grow past `2`, so the number could not possibly be prime.
>
> The loop nevertheless continued for **twelve more passes**. Section 11 shows how to stop early.

## Case B — `numpr = 7` (a prime)

```text
────────────────────────────────────────────
Pass   i    7 % i    == 0 ?    count after
────────────────────────────────────────────
  1    1      0      true          1
  2    2      1      false         1
  3    3      1      false         1
  4    4      3      false         1
  5    5      2      false         1
  6    6      1      false         1
  7    7      0      true          2
────────────────────────────────────────────
count = 2  →  PRIME  ✓
```

Only `1` and `7` divide it — exactly two divisors.

## Case C — `numpr = 2` (the smallest prime)

```text
i = 1:  2 % 1 = 0  →  count = 1
i = 2:  2 % 2 = 0  →  count = 2
count == 2  →  PRIME  ✓
```

Correct. `2` is the only even prime.

## Case D — `numpr = 1`

```text
Precondition:  1 > 1  →  FALSE
The counting logic is skipped entirely.
Output: "Not a prime number"   ✓
```

The outer `if` catches it before any counting happens.

## The divisor pairs of 15 — why √n is enough

```text
15 = 1 × 15
15 = 3 × 5
     ─┬─  ─┬─
      │    └── ≥ √15 (≈ 3.87)
      └─────── ≤ √15

Every pair straddles √15.
Testing 1, 2, 3 finds one member of every pair.
Testing 4..15 finds only the partners we already knew about.
```

That is the whole justification for the `i * i <= n` optimisation.

---

# 10. Visualize data where useful

## The divisor search for 15

```text
i:      1    2    3    4    5    6    7    8    9   10   11   12   13   14   15
15%i:   0    1    0    3    0    3    1    7    6    5    4    3    2    1    0
        │         │         │                                                │
        ▼         ▼         ▼                                                ▼
      DIVISOR   DIVISOR   DIVISOR                                        DIVISOR

count:  1    1    2    2    3    3    3    3    3    3    3    3    3    3    4
                  ▲
        answer already decided here — but the loop runs 12 more times
```

## Prime versus composite

```text
        7 (prime)                    15 (composite)
   ┌────┬────┬────┬────┐       ┌────┬────┬────┬────┐
   │  1 │    │    │  7 │       │  1 │  3 │  5 │ 15 │
   └────┴────┴────┴────┘       └────┴────┴────┴────┘
      2 divisors                    4 divisors
          ↓                             ↓
        PRIME                       NOT PRIME
```

## The decision tree

```text
                    numpr
                      │
              ┌───────┴───────┐
          numpr > 1 ?
              │               │
            true            false
              │               │
        count divisors   "Not a prime number"
              │
        ┌─────┴─────┐
    count == 2 ?
        │           │
      true        false
        │           │
    "is a       "is not a
     prime"      prime"
```

The shape of this tree is the shape of the definition — a precondition, then a rule.

## Divisor pairs straddle the square root

```text
n = 36,  √36 = 6

  1 × 36
  2 × 18
  3 × 12
  4 × 9
  6 × 6      ← the square root itself
  ─┬─   ─┬─
   │     └── every partner is ≥ 6
   └──────── every first member is ≤ 6

Testing 1..6 finds every pair.
Testing 7..36 finds nothing new.
```

---

# 11. Alternative ways to write the same logic

### Beginner-friendly version — early exit

The most important improvement, and the easiest to understand:

```java
int number = 15;
boolean isPrime = true;

if (number <= 1) {
    isPrime = false;
} else {
    for (int i = 2; i < number; i++) {
        if (number % i == 0) {
            isPrime = false;
            break;                    // ← stop as soon as we know
        }
    }
}

System.out.println(number + (isPrime ? " is a prime number" : " is not a prime number"));
```

Two changes:

- **Start at `2`** — we already know `1` divides everything, so testing it tells us nothing.
- **`break` on the first divisor** — once a divisor is found, the answer is settled. Continuing is pure waste.

> [!important] Why `break` matters so much here
> For `15`, the original loop runs **15** times. This version runs **2** times (`i = 2` fails, `i = 3` succeeds and breaks).
>
> For a large composite like `1,000,000`, the original runs a million times; this one stops at `i = 2`.
>
> **`break` exits the innermost loop immediately.** Use it whenever the remaining iterations cannot change the answer. Recognising that moment is the skill.

### The standard optimal version — test only up to √n

```java
public static boolean isPrime(int number) {
    if (number <= 1) {
        return false;                 // 0, 1, and negatives
    }
    if (number == 2) {
        return true;                  // the only even prime
    }
    if (number % 2 == 0) {
        return false;                 // every other even number
    }

    for (int i = 3; i * i <= number; i += 2) {    // odd divisors only
        if (number % i == 0) {
            return false;
        }
    }
    return true;
}
```

Four optimisations stacked:

| Change | Effect |
|---|---|
| `return false` instead of `break` | exits the method immediately |
| `i * i <= number` | tests only up to √n |
| `i += 2` | skips even divisors |
| handle `2` separately | keeps the odd-skipping correct |

> [!important] Why `i * i <= number` rather than `i <= Math.sqrt(number)`
> Both express the same bound, but `i * i` is better:
>
> - **No floating point.** `Math.sqrt` returns a `double`, and comparing an `int` with a `double` risks subtle rounding errors at exact squares.
> - **No repeated method call.** `Math.sqrt` would be evaluated on every pass unless you hoist it into a variable.
>
> Staying in integer arithmetic when you can is a good general habit.

**The performance difference is dramatic:**

| `n` | Original (1..n) | Optimised (√n, odds only) |
|---|---|---|
| 15 | 15 passes | 1 pass |
| 101 | 101 | 4 |
| 1,000,003 | 1,000,003 | ~500 |
| 1,000,000,007 | 1 **billion** | ~15,800 |

The last row is the difference between "instant" and "minutes".

### Reusable version — a method returning `boolean`

```java
public static boolean isPrime(int number) { ... }

public static void main(String[] args) {
    int number = 15;
    if (isPrime(number)) {
        System.out.println(number + " is a prime number");
    } else {
        System.out.println(number + " is not a prime number");
    }
}
```

Why this is better:

- **Reusable** on any number.
- **Testable** — `assert isPrime(7)` can be automated.
- **Flatter** — the nesting disappears, because `return` replaces the outer `else`.
- **Separates computation from presentation** — the method decides, the caller displays.

> [!tip] `return` flattens nested conditions
> ```java
> // nested — three levels
> if (n > 1) {
>     for (...) { if (...) { ... } }
>     if (count == 2) { ... } else { ... }
> } else { ... }
>
> // guard clauses — flat
> if (n <= 1) return false;
> for (...) { if (...) return false; }
> return true;
> ```
>
> Handling the exceptional cases first and returning early is called the **guard clause** pattern. It keeps the main logic at one level of indentation and is one of the highest-value readability habits you can build.

### Keeping the counting approach, but bounded

If you want to preserve the "count the divisors" idea while fixing the waste:

```java
int count = 0;
for (int i = 1; i * i <= number; i++) {
    if (number % i == 0) {
        count++;                              // i is a divisor
        if (i != number / i) {
            count++;                          // so is its partner
        }
    }
}
boolean isPrime = (number > 1 && count == 2);
```

This counts divisors in **pairs**, adding both members at once. The `i != number / i` guard avoids double-counting a perfect square's root (for `36`, `6 × 6` is one divisor, not two).

Clever, but harder to read than the early-exit version. Worth understanding; not worth preferring.

### Printing all primes up to a limit

```java
for (int n = 2; n <= 50; n++) {
    if (isPrime(n)) {
        System.out.print(n + " ");
    }
}
```

A common follow-up. Note how having `isPrime` as a **method** makes this trivial — which is the payoff of extracting it.

### The Sieve of Eratosthenes — for many primes at once

```java
public static void printPrimesUpTo(int limit) {
    boolean[] isComposite = new boolean[limit + 1];

    for (int i = 2; i * i <= limit; i++) {
        if (!isComposite[i]) {
            for (int j = i * i; j <= limit; j += i) {
                isComposite[j] = true;         // mark every multiple
            }
        }
    }

    for (int i = 2; i <= limit; i++) {
        if (!isComposite[i]) {
            System.out.print(i + " ");
        }
    }
}
```

Instead of testing each number independently, this **crosses off multiples**. For finding *all* primes up to a limit it is far faster than calling `isPrime` repeatedly.

> [!tip]
> The right algorithm depends on the question:
>
> | Question | Best approach |
> |---|---|
> | "is *this* number prime?" | trial division up to √n |
> | "list all primes up to N" | Sieve of Eratosthenes |
> | "is this 200-digit number prime?" | `BigInteger.isProbablePrime` |
>
> **Knowing which tool fits which question is more valuable than knowing any one of them deeply.**

---

# 12. Common beginner mistakes

## Mistake 1 — starting the loop at `0`

**Incorrect code**

```java
for (int i = 0; i <= numpr; i++) {
    if (numpr % i == 0) count++;
}
```

**Why it is wrong**
`numpr % 0` divides by zero.

**What Java does**

```text
Exception in thread "main" java.lang.ArithmeticException: / by zero
```

**Correct code**

```java
for (int i = 1; i <= numpr; i++) {
```

**How to recognise it in future**
`i` here is an arithmetic **value**, not an array index. **Ask what `i` means before choosing where it starts.**

---

## Mistake 2 — using `<` instead of `<=`

**Incorrect code**

```java
for (int i = 1; i < numpr; i++) {
```

**Why it is wrong**
The number itself is never tested, so `count` can never reach `2`. **Every** number is reported as not prime, including genuine primes.

**Correct code**

```java
for (int i = 1; i <= numpr; i++) {
```

**How to recognise it**
If a primality checker says *nothing* is prime, the upper bound is excluding the number itself. Test with `7` — a correct program says prime.

---

## Mistake 3 — forgetting the `numpr > 1` check

**Incorrect code**

```java
int count = 0;
for (int i = 1; i <= numpr; i++) {
    if (numpr % i == 0) count++;
}
if (count == 2) { /* prime */ }
```

**Why it is wrong**
For `1`, `count` becomes `1`, so it correctly reports not prime — by luck. But the code no longer *states* the rule, and a reader cannot tell whether `1` was considered.

**Correct code**
Keep the explicit precondition, or use a guard clause: `if (numpr <= 1) return false;`

**How to recognise it**
**When a definition has a precondition, the code should have one too.** Relying on the main logic to accidentally handle the exception is fragile.

---

## Mistake 4 — checking `count == 1` or `count <= 2`

**Incorrect code**

```java
if (count == 1) { /* prime */ }      // ✗ off by one
if (count <= 2) { /* prime */ }      // ✗ includes 1
```

**Why it is wrong**
A prime has **exactly two** divisors: `1` and itself. `count == 1` describes only the number `1`; `count <= 2` wrongly admits it.

**Correct code**

```java
if (count == 2) {
```

**How to recognise it**
Write out the divisors of `7` by hand: `1, 7`. Two of them. **Verify a rule against a concrete example before trusting it.**

---

## Mistake 5 — using `=` instead of `==`

**Incorrect code**

```java
if (count = 2) {
```

**What Java does**
`incompatible types: int cannot be converted to boolean`.

**Correct code**

```java
if (count == 2) {
```

**How to recognise it**
`if` requires a `boolean`. **Java catches this at compile time** — in C it would silently assign.

---

## Mistake 6 — declaring `count` inside the loop

**Incorrect code**

```java
for (int i = 1; i <= numpr; i++) {
    int count = 0;
    if (numpr % i == 0) count++;
}
if (count == 2) { }      // error: cannot find symbol
```

**Why it is wrong**
`count` resets every pass and does not exist after the loop.

**Correct code**
Declare it before the loop.

**How to recognise it**
`cannot find symbol` on a variable you know you declared → you declared it in a narrower scope.

---

## Mistake 7 — a stray semicolon after the `for` or `if`

**Incorrect code**

```java
for (int i = 1; i <= numpr; i++);
{
    if (numpr % i == 0) count++;
}
```

**Why it is wrong**
The `;` becomes the loop body. The loop spins doing nothing, and the block below fails to compile because `i` is out of scope.

**Correct code**
No semicolon after a loop or `if` header.

---

## Mistake 8 — omitting braces and then adding a line

**Incorrect code**

```java
if (numpr % i == 0)
    count++;
    System.out.println("divisor: " + i);    // runs EVERY pass
```

**Why it is wrong**
Without braces, only the first statement is conditional. The indentation misleads.

**Correct code**

```java
if (numpr % i == 0) {
    count++;
    System.out.println("divisor: " + i);
}
```

**How to recognise it**
**Always write the braces.** The cost is two characters; the benefit is that indentation and behaviour can never diverge.

---

## Mistake 9 — using `.equals()` on an `int`

**Incorrect code**

```java
if (count.equals(2)) {
```

**What Java does**
`int cannot be dereferenced`.

**Correct code**

```java
if (count == 2) {
```

**How to recognise it**
`X cannot be dereferenced` always means "you called a method on a primitive". **Primitives use operators; objects use methods.**

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| Definition knowledge | **do you know 1 is not prime, and why?** |
| Modulo | do you know `% == 0` means "divides evenly"? |
| Loop bounds | `i = 1` (not 0) and `i <= n` (not `<`) |
| Efficiency awareness | do you spot the wasted iterations? |
| Optimisation | can you derive the √n bound? |
| Early exit | do you know to `break` or `return`? |
| Edge cases | 0, 1, 2, negatives |

> [!tip]
> Prime checking is one of the most-asked beginner interview questions, and the follow-up is almost always *"can you make it faster?"* The naive loop is the entry ticket; the √n bound and the early exit are what the interview is actually about.
>
> Being able to **explain why** √n suffices — divisors come in pairs straddling the square root — is far more impressive than reciting the bound.

## Likely follow-up questions

> **"Is 1 a prime number?"**

No. A prime needs exactly two distinct divisors; `1` has only one. Add that if `1` were prime, unique prime factorisation would break.

> **"Is 2 a prime number?"**

Yes — it is the smallest prime and the only even one. Its divisors are `1` and `2`.

> **"What does this program print for 15?"**

"This number is not a prime number", because 15 has four divisors: 1, 3, 5, 15.

> **"Can you make it faster?"**

Three answers, in increasing sophistication:
1. `break` as soon as a divisor is found.
2. Test only up to √n, because divisors come in pairs.
3. Skip even numbers after handling `2`.

> **"Why is testing up to √n enough?"**

Every divisor pair `a × b = n` has one member ≤ √n and one ≥ √n. If no divisor exists at or below √n, none exists above it either.

> **"What is the time complexity?"**

The original is `O(n)`. With the √n bound it becomes `O(√n)` — a dramatic improvement. For a billion, that is 1,000,000,000 operations versus about 31,600.

> **"How would you find all primes below 100?"**

Call `isPrime` in a loop, or use the Sieve of Eratosthenes — which is much faster when you want *many* primes rather than one answer.

> **"What about negative numbers?"**

Primality is defined only for integers greater than 1. Negatives should return `false`. The outer `if (numpr > 1)` handles them.

> **"How would you test a 200-digit number?"**

Trial division is hopeless at that scale. Use `BigInteger.isProbablePrime`, which applies a probabilistic test. Note the "Probable" in the name.

---

# 14. Complexity

## Time complexity: `O(n)` as written

The loop runs from `1` to `numpr`, doing constant work per pass.

In beginner language:

> "The loop tests every number from 1 up to the number itself. Checking 15 takes 15 passes; checking 1,000 takes 1,000. Double the input and you double the work. Because the work grows in direct proportion to the *value* of the input, we call it linear time and write it `O(n)`."

> [!warning] Why `O(n)` is genuinely bad here
> For most algorithms in this folder, `n` is the *size* of a collection — a ten-element array means ten steps.
>
> Here `n` is the **value of the number itself**. Checking whether `1,000,000,007` is prime takes a **billion** iterations.
>
> That is a meaningful distinction. An algorithm that is linear in the *value* of its input is exponential in the *number of digits* — and digits are what you actually type. This is why primality testing was historically considered hard.

## With the optimisations

| Version | Complexity | Passes for `n = 1,000,003` |
|---|---|---|
| original (1..n) | `O(n)` | 1,000,003 |
| with `break` | `O(n)` worst case, `O(1)` typical | 1 (it is even? no — a few) |
| up to √n | `O(√n)` | ~1,000 |
| √n, odds only | `O(√n)` | ~500 |

> [!important] Why `O(√n)` is such a large win
> ```text
> n = 100            → √n = 10        (10× fewer)
> n = 10,000         → √n = 100       (100× fewer)
> n = 1,000,000      → √n = 1,000     (1,000× fewer)
> n = 1,000,000,000  → √n = 31,623    (31,623× fewer)
> ```
>
> The saving grows as the input grows. **This is what makes algorithmic improvements different from micro-optimisations** — a faster loop body saves a constant factor; a better bound changes the shape of the curve.

**Note on `break`:** it does not change the worst case. For a **prime** input, no divisor is ever found, so the loop still runs to completion. `break` helps composites — often enormously, since half of all numbers are even and fail on the first test.

## Space complexity: `O(1)`

Three `int`s — `numpr`, `count`, `i` — regardless of input size.

> "We use the same three variables whether we test 15 or 15 billion, so the extra memory is constant: `O(1)`."

The Sieve of Eratosthenes trades this away: it uses `O(n)` space for its `boolean[]` in exchange for finding *all* primes at once. **A classic time-versus-space trade.**

---

# 15. Edge cases

## `numpr = 2` — the smallest prime

```text
2 > 1  →  enter the main branch
i = 1:  2 % 1 = 0  →  count = 1
i = 2:  2 % 2 = 0  →  count = 2
count == 2  →  PRIME  ✓
```

Correct. Worth testing explicitly because `2` is the only even prime, and optimised versions that skip even numbers must handle it as a special case.

## `numpr = 1`

```text
1 > 1  →  FALSE
Output: "Not a prime number"   ✓
```

Caught by the precondition before any counting. Correct, and correct *by design* rather than by accident.

## `numpr = 0`

```text
0 > 1  →  FALSE
Output: "Not a prime number"   ✓
```

Correct. Note that without the outer `if`, the loop condition `1 <= 0` would be false immediately and `count` would stay `0` — also giving "not prime", but by luck.

## Negative numbers

```java
int numpr = -7;
```

```text
-7 > 1  →  FALSE
Output: "Not a prime number"   ✓
```

Correct. Primality is defined only for integers greater than 1.

> [!note]
> Without the guard, the loop `for (int i = 1; i <= -7; i++)` would not run at all (`1 <= -7` is false), leaving `count = 0` and again giving "not prime". The guard makes the intent explicit rather than relying on the loop bounds to work out.

## Perfect squares

```java
int numpr = 9;
```

```text
i = 1:  9 % 1 = 0  →  count = 1
i = 2:  9 % 2 = 1
i = 3:  9 % 3 = 0  →  count = 2
i = 4..8: no
i = 9:  9 % 9 = 0  →  count = 3
count = 3  →  not prime  ✓
```

Correct. Perfect squares always have an odd number of divisors, because the square root pairs with itself.

## Large primes

```java
int numpr = 1000003;      // a prime
```

Correct, but the loop runs **1,000,003 times** — noticeably slow. The optimised version needs about 500 passes.

## `Integer.MAX_VALUE`

```java
int numpr = 2147483647;   // this is actually prime!
```

The loop would run 2.1 **billion** times — minutes of CPU time for one answer.

> [!warning] Correct but unusable
> The program gives the right answer eventually. "Eventually" is doing a lot of work in that sentence.
>
> **An algorithm that is correct but too slow to run is not a solution.** The √n version answers the same question in about 46,000 passes — instantly.
>
> There is no overflow risk here, though: `i` never exceeds `numpr`, and `%` never overflows. The only failure mode is time.

## Can it crash?

Not as written. The `i = 1` start prevents division by zero, there are no arrays, and there is no input parsing. The program always terminates and never throws.

Its weaknesses are **wasted work** and **hard-coded input**, not incorrectness.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Decide whether a number is prime.

        ↓

What is the exact definition?
"A whole number GREATER THAN 1 whose only
 positive divisors are 1 and itself."

        ↓

That definition has two clauses:
(a) greater than 1     ← a precondition
(b) only 1 and itself  ← the main rule
So the code needs two parts.

        ↓

Can I test (b) with one operation?
No. But restate it:
"only 1 and itself"  =  "exactly TWO divisors"

        ↓

"Exactly two" is a COUNT.
Counting is something I know how to do.
→ replace a hard predicate with an easy tally

        ↓

What do I count into?
An accumulator → int count = 0
Declared BEFORE the loop so it survives.

        ↓

Why seed at 0?
Before looking, I have found none.
(And 0 is the identity for addition.)

        ↓

How do I test whether i divides numpr?
"divides" = "leaves no remainder"
Java's remainder operator is %
→ numpr % i == 0

        ↓

Which candidates do I try?
Every whole number from 1 to numpr.

        ↓

Where does i start?
NOT 0 — numpr % 0 throws ArithmeticException.
And 1 is a genuine divisor.
→ i = 1

        ↓

Where does i stop?
numpr divides itself, and that must be counted.
→ i <= numpr
(With < , no number could ever reach a count of 2.)

        ↓

How do I read the tally?
count == 2  →  prime
anything else → not prime

        ↓

Wrap it all in the precondition:
if (numpr > 1) { ...count and check... }
else            { not prime }

        ↓

Trace 15:
divisors 1,3,5,15 → count 4 → not prime  ✓
Trace 7:
divisors 1,7 → count 2 → prime  ✓
Trace 1:
caught by the precondition  ✓

        ↓

NOW ASK: is this doing more work than necessary?
For 15, the answer was settled at i=3,
but the loop ran 12 more times.

        ↓

Two improvements:
(1) break as soon as a divisor is found
(2) divisors come in pairs straddling √n,
    so testing up to √n is enough
    → i * i <= numpr

        ↓

O(n) becomes O(√n).
For a billion: 1,000,000,000 → 31,623 passes.
```

> [!important] The step most beginners skip
> Look at the block beginning "NOW ASK". The program was **already correct** three steps earlier.
>
> Asking *"is this doing more work than necessary?"* after you have something working is what turns a correct program into a good one. It is also, in practice, most of what a technical interview is testing.
>
> **Get it right first. Then ask whether it is doing too much.**

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int numpr = 15;` | "Make a whole-number box called `numpr` and put 15 in it — this is the number we are testing." |
| `int count = 0;` | "Make a tally box starting at zero, because we have not found any divisors yet." |
| `if (numpr > 1)` | "Only bother checking if the number is bigger than 1, because the definition of prime says so." |
| `for (int i = 1; i <= numpr; i++)` | "Try every whole number from 1 up to and including the number itself as a possible divisor." |
| `if (numpr % i == 0)` | "Divide the number by this candidate and look at what is left over. If nothing is left over, the candidate divides it exactly." |
| `count++;` | "Add one to the tally." |
| `if (count == 2)` | "A prime has exactly two divisors — 1 and itself. So if the tally is exactly 2, it is prime." |
| `else` (inner) | "Any other tally means it has extra divisors, so it is not prime." |
| `else` (outer) | "And if the number was not bigger than 1 in the first place, it is not prime either." |

The loop in one sentence:

> **"Try dividing the number by every whole number from 1 up to itself, and count how many divide exactly."**

And the whole program:

> **"If the number is bigger than 1, count how many whole numbers divide it exactly. If exactly two do — 1 and itself — it is prime. Otherwise it is not, and neither is anything 1 or below."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| `%` (modulo) | `a % b == 0` means `b` divides `a` exactly |
| division by zero | `n % 0` and `n / 0` throw `ArithmeticException` |
| counting accumulator | `count++` — declared before the loop, read after |
| nested `if` | the inner condition is only evaluated when the outer one passed |
| precondition | a definition's "only if" clause becomes an outer `if` |
| `break` | exits the innermost loop immediately |
| guard clause | handle exceptional cases first and `return`, to flatten nesting |
| `==` for primitives | compares values — correct for `int` |
| braces | always write them, even for one statement |

### Important methods

| Member | Owner | Accepts | Returns |
|---|---|---|---|
| `println(String)` | `java.io.PrintStream` | text | `void` |
| `Math.sqrt(double)` | `java.lang.Math` | a `double` | `double` |
| `isProbablePrime(int)` | `java.math.BigInteger` | a certainty level | `boolean` — **probabilistic** |

There is deliberately **no** `Math.isPrime()` in Java.

### Important syntax

| Syntax | Meaning |
|---|---|
| `%` | remainder |
| `==` | equal to — correct for primitives |
| `count++` | add one |
| `i <= n` | includes the boundary value |
| `i * i <= n` | tests up to √n without floating point |
| `i += 2` | step by two, skipping evens |
| `break` | leave the innermost loop now |
| `if / else` | choose between two **actions** |
| `{ }` | a block — always write it |

### Main interview concept

> **A prime has exactly two divisors, so counting divisors and checking `count == 2` answers the question.** Start the loop at `1` (never `0` — `n % 0` throws) and end at `<= n` (so the number counts itself). Then improve it: `break` on the first divisor found, and test only up to √n, because **divisors come in pairs straddling the square root**. That takes it from `O(n)` to `O(√n)`.

### Main lesson for code reading

> **When a property is hard to test directly, look for a number the definition mentions and compute that instead.**
>
> ```text
> "only divisible by 1 and itself"
>          ↓ restate
> "has exactly two divisors"
>          ↓ compute
> count == 2
> ```
>
> And once the code is correct, ask the second question: **is it doing more work than necessary?** For `15` this loop finds its answer on pass 3 and then runs twelve more times. Spotting that gap — between when the answer is known and when the loop stops — is where almost every practical optimisation comes from.

---

### Related notes

- [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] — where `%` was introduced; `n % 2 == 0` is this test with `i` fixed at 2
- [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]] — `%` and `/` used together to peel digits
- [[Java_Factorials_Code_Reading_Explanation_Obsidian|Factorials]] — a counting loop where `i` is a value, not an index
- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — the accumulator pattern, summing rather than counting
- [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]] — `break` used to exit early from a nested loop
