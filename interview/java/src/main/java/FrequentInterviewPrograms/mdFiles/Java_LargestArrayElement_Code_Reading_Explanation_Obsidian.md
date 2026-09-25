---
title: Java Code Reading — LargestArrayElement
tags:
  - java
  - interview-programs
  - code-reading
  - arrays
  - loops
aliases:
  - LargestArrayElement Explained
  - Find Largest Element in Array
---

# Java Code Reading — LargestArrayElement Explained for a Fresh Java Programmer

> [!note]
> This program teaches the **running maximum** — a variant of the accumulator you met in [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]], with one crucial difference that is worth understanding deeply.
>
> For summing, the seed was `0` — a neutral value that changes nothing. For finding a maximum, **there is no neutral value**. No number is "smaller than everything". So the seed has to come from somewhere else: from the data itself.
>
> That single observation explains `int max = arrayEle[0];`, and it also explains why this program crashes on an empty array while the sum program does not.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Is there a running answer? → Where does its starting value come from? → What decides whether it changes?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class LargestArrayElement {

    public static void main(String[] args) {

        int[] arrayEle = new int[]{324,34,324,53,265,2,234,234,52,231,325};

        int max = arrayEle[0];

        for( int i =0; i<arrayEle.length; i++){

            if(arrayEle[i]> max){

                max = arrayEle[i];
            }
        }

        System.out.println("The largest element in a Array is :" +max);
    }
}
```

The executable logic is four lines:

```java
int[] arrayEle = new int[]{324,34,324,53,265,2,234,234,52,231,325};
int max = arrayEle[0];
for(int i = 0; i < arrayEle.length; i++){ if(arrayEle[i] > max){ max = arrayEle[i]; } }
System.out.println("The largest element in a Array is :" + max);
```

> [!tip] Note the loop bound
> `i < arrayEle.length` — this is the **correct, idiomatic** form. Compare with [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] in this same folder, which writes `i < arreo.length - 1` and silently skips its last element. Same folder, same author, one correct and one not. **Always check the bound; never assume.**

---

# 2. What problem is this program solving?

In plain language:

> I have a list of numbers. Tell me the biggest one.

### What do we know?

- Eleven whole numbers, all known up front.
- They are unsorted — there is no pattern to exploit.

### What do we need?

- **One** number: the largest.

### What transformations are required?

Many values in, one value out — a **reduction**, the same shape as summing.

```text
324 34 324 53 265 2 234 234 52 231 325
  \  \  |  |  |  |  |  |  |  /  /
              325
        many → one
```

But the *combining rule* is different, and so is the seed.

### The key question: how do you find a maximum?

Think about how a human does it with a stack of cards.

> Look at the first card. That is the biggest so far, because it is the only one you have seen.
> Turn over the next card. Is it bigger than the one you are holding? If yes, keep the new one and discard the old. If no, keep what you have.
> Repeat to the end. Whatever you are holding is the biggest.

That description translates directly into code:

| Human action | Java |
|---|---|
| "the first card is the biggest so far" | `int max = arrayEle[0];` |
| "turn over the next card" | `for (int i = 0; ...)` |
| "is it bigger than what I hold?" | `if (arrayEle[i] > max)` |
| "keep the new one" | `max = arrayEle[i];` |
| "whatever I am holding is the answer" | `System.out.println(... + max)` |

> [!important] Why the seed cannot be `0`
> This is the difference that makes the max problem interesting.
>
> For **summing**, you start at `0` because adding zero changes nothing — it is the *identity* for addition.
>
> For **maximum**, ask the same question: *"what value can I compare against without affecting the result?"* You would need a number smaller than every possible input. **No such `int` exists** — whatever you pick, a smaller one is possible.
>
> Consider seeding with `0`:
> ```java
> int max = 0;
> int[] data = {-5, -3, -9};
> // nothing is greater than 0, so max stays 0
> // answer: 0 — but 0 is not even IN the array!
> ```
> The program would report a value that never existed in the data.
>
> Two correct alternatives:
>
> | Approach | Code | Why it works |
> |---|---|---|
> | seed from the data | `int max = arr[0];` | the answer is guaranteed to be a real element |
> | seed with the theoretical floor | `int max = Integer.MIN_VALUE;` | nothing can be smaller, so the first comparison always wins |
>
> This program uses the first, which is the better choice — it guarantees the answer is an actual member of the array.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder. First statement in the file.

## `public class LargestArrayElement`

- `public` — usable from anywhere.
- `class` — declares a class.
- `LargestArrayElement` — `PascalCase`, matching `LargestArrayElement.java`.

The name describes the task precisely. Good naming.

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

`int`, `int[]`, `String` and `System` only. Primitives, arrays and `java.lang` need no `import`.

## Variables

| Variable | Type | Role |
|---|---|---|
| `arrayEle` | `int[]` | the data |
| `max` | `int` | the accumulator — the running maximum |
| `i` | `int` | the counter — the current index |

The familiar trio: **data, accumulator, counter**. Classify a loop's variables this way before reading its body and the body almost explains itself.

## Comments

This file uses `//` line comments rather than Javadoc blocks, and they are unusually explanatory:

```java
// This line declares an integer variable named max and initializes it with the first element of the array.
int max = arrayEle[0];
```

That comment restates *what* the line does. The more valuable comment would explain *why*:

```java
int max = arrayEle[0];   // seed from the data: there is no "smaller than everything" value
```

**Code says what; comments should say why.**

---

# 4. Line-by-line explanation

## 4.1 — `int[] arrayEle = new int[]{324,34,324,53,265,2,234,234,52,231,325};`

```java
int[] arrayEle = new int[]{324,34,324,53,265,2,234,234,52,231,325};
```

**What is this?**
Creating an array of eleven integers and storing a reference to it.

**Why do we need it?**
The requirement involves several numbers. One `int` holds one value; an array holds many of the same type.

**How would a beginner know to write it?**
Ask: **"How many values must I store?"** Eleven known whole numbers, all the same type → an array of `int`.

**What type and why?**
`int[]` — "array of `int`". The values are whole numbers, we will compare them numerically, and `int` is Java's standard whole-number type.

**What does each part mean?**

```text
int[]   arrayEle   =   new   int[]   {324,34,...,325}   ;
  │        │       │    │      │            │
  │        │       │    │      │            └── initial contents
  │        │       │    │      └─────────────── the type being created
  │        │       │    └────────────────────── allocate on the heap
  │        │       └─────────────────────────── store the reference
  │        └─────────────────────────────────── the reference variable
  └──────────────────────────────────────────── the declared type
```

**What does Java do when it reaches this line?**

1. Allocates heap memory for eleven `int`s.
2. Writes the values into consecutive slots.
3. Records the length (`11`) permanently.
4. Copies the array's address into `arrayEle`.

```text
  arrayEle
  ┌─────┐
  │ ref │
  └──┬──┘
     ▼
┌─────┬────┬─────┬────┬─────┬───┬─────┬─────┬────┬─────┬─────┐
│ 324 │ 34 │ 324 │ 53 │ 265 │ 2 │ 234 │ 234 │ 52 │ 231 │ 325 │
└─────┴────┴─────┴────┴─────┴───┴─────┴─────┴────┴─────┴─────┘
   0     1    2     3    4    5    6     7    8     9    10
                                                          ↑
                                              length = 11, last index = 10
```

**Worth noticing about this data**

- `324` appears **twice** (indexes 0 and 2).
- `234` appears **twice** (indexes 6 and 7).
- The largest value, `325`, is at the **very last index**.

That last fact is deliberate and useful: it means the loop bound must be exactly right for the program to work. Had the author written `i < arrayEle.length - 1` here (as in `EvenOdd`), the answer would be `324`, not `325` — a silently wrong result.

**Why the name `arrayEle`?**
"array elements". It describes the container rather than the content. `numbers` or `values` would be better. Acceptable in a demo.

---

## 4.2 — `int max = arrayEle[0];`

```java
int max = arrayEle[0];
```

**What is this?**
The accumulator — the running maximum — seeded with the array's **first element**.

**Why do we need it?**
You cannot decide "the biggest" by looking at one number at a time unless you remember the best you have seen so far. `max` is that memory.

**How would a beginner know to write it?**
Ask: **"Do I need to remember something between one item and the next?"** Yes — the largest value seen so far. Anything that must survive across iterations is declared **before** the loop.

**Why seed with `arrayEle[0]` rather than `0`?**

This is the most important line in the program to understand.

```text
Why not int max = 0 ?

    data = {-5, -3, -9}
    Is -5 > 0 ?  no
    Is -3 > 0 ?  no
    Is -9 > 0 ?  no
    max stays 0

    Answer: 0
    But 0 is not in the array at all.   ✗
```

Seeding from the data avoids this entirely:

```text
    data = {-5, -3, -9}
    max = data[0] = -5
    Is -5 > -5 ?  no
    Is -3 > -5 ?  YES → max = -3
    Is -9 > -3 ?  no

    Answer: -3   ✓ correct, and a real element
```

> [!important] The general principle
> **When an accumulator has no identity value, seed it from the data.**
>
> | Problem | Identity exists? | Seed |
> |---|---|---|
> | sum | yes — `0` | `0` |
> | product | yes — `1` | `1` |
> | string build | yes — `""` | `""` |
> | count | yes — `0` | `0` |
> | **maximum** | **no** | `arr[0]` |
> | **minimum** | **no** | `arr[0]` |
>
> This is not an arbitrary rule to memorise. Ask *"what value leaves the result unchanged?"* If the question has an answer, use it. If it does not, take the first element and start the comparison from there.
>
> And notice the consequence: because max must read `arr[0]`, **it needs the array to be non-empty**. Sum does not. That is why the two programs differ in their edge-case behaviour — see section 15.

**What type and why?**
`arrayEle[0]` is an `int` (an element of an `int[]`), so `max` must be an `int`. **The holder's type must match what it holds.**

**Why the name `max`?**
Short for "maximum", universally understood. Excellent name — it states the meaning of the data.

**What does Java do when it reaches this line?**
Reads the value at index `0` (`324`) and copies it into a new `int` slot named `max`.

> [!note] A copy, not a link
> `max` now holds `324`. It is a **copy**. Later changing `arrayEle[0]` would not change `max`, and changing `max` does not change the array.
>
> This is primitive value semantics — the same behaviour discussed in [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]].

---

## 4.3 — `for( int i =0; i<arrayEle.length; i++){`

```java
for( int i =0; i<arrayEle.length; i++){
```

**What is this?**
A `for` loop walking every index of the array.

**The three sections**

```text
for ( int i = 0 ; i < arrayEle.length ; i++ )
         │              │                │
         │              │                └── after each pass
         │              └───────────────────  before each pass
         └──────────────────────────────────  once, at the start
```

**Why does `i` start at `0`?**
`i` is an **index** — a position — and Java arrays are zero-indexed.

**Why `i < arrayEle.length`?**

`arrayEle.length` is `11`, so the condition is `i < 11`, true for `i = 0..10`. Those are exactly the valid indexes.

This is the **correct, idiomatic** form. The alternatives:

| Condition | Indexes (length 11) | Verdict |
|---|---|---|
| `i < arr.length` | 0..10 | ✓ **used here** |
| `i <= arr.length - 1` | 0..10 | ✓ correct but wordy |
| `i < arr.length - 1` | 0..9 | ✗ would give `324`, missing `325` |
| `i <= arr.length` | 0..11 | ✗ crashes at `arr[11]` |

Because `325` sits at the last index, the third option would produce a wrong answer here — and no error. Worth remembering when you read [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]].

**What is `arrayEle.length`?**
The element count, `11`. A **field**, not a method — no parentheses. (`String` uses `length()`; `List` uses `size()`.)

**What is `i++`?**
The increment operator, `i = i + 1`. Without it the loop never ends.

**A small redundancy worth noticing**

The loop starts at `i = 0`, but `max` was already seeded from index `0`. So the very first comparison is:

```text
arrayEle[0] > max
      324   > 324    →  false
```

An element compared against itself. Harmless — the condition is `false`, nothing changes — but genuinely unnecessary.

> [!tip] Should you start at `i = 1` instead?
> ```java
> int max = arrayEle[0];
> for (int i = 1; i < arrayEle.length; i++) {    // start at 1
> ```
> This skips the pointless self-comparison and is very slightly more efficient.
>
> Both are correct. Starting at `1` shows you understand *why* — that element `0` is already accounted for. Starting at `0` is simpler and impossible to get wrong. Either is defensible; being able to explain the difference is what matters.

---

## 4.4 — `if(arrayEle[i]> max){`

```java
if(arrayEle[i]> max){
```

**What is this?**
The comparison that decides whether the running maximum should be replaced.

**Why do we need it?**
We update `max` only when we find something bigger. Without the `if`, `max` would simply become the last element.

**How would a beginner know to write it?**
Translate the human procedure: *"Is this card bigger than the one I am holding?"* In Java, "bigger than" is `>`.

**What does each part mean?**

```text
if ( arrayEle[i]   >   max )
        │          │    │
        │          │    └── the best value found so far
        │          └─────── "greater than" → produces true/false
        └────────────────── the element we are currently examining
```

**What is `>`?**
A relational operator comparing two numbers and producing a `boolean`.

**Why `>` rather than `>=`?**

Both produce the same final answer, but they behave differently on ties:

```text
data = {324, 34, 324, ...}

with  >   :  at i=2, is 324 > 324 ? no  → max unchanged (still holds index 0's value)
with  >=  :  at i=2, is 324 >= 324 ? yes → max reassigned to the same number
```

The stored **value** is identical either way. The difference only matters if you also track *where* the maximum was found:

```java
int maxIndex = 0;
if (arrayEle[i] > arrayEle[maxIndex])  { maxIndex = i; }   // keeps the FIRST occurrence
if (arrayEle[i] >= arrayEle[maxIndex]) { maxIndex = i; }   // keeps the LAST occurrence
```

> [!tip]
> This array contains duplicates (`324` twice, `234` twice) precisely so this question can arise. `>` is the conventional choice: it does less work, and "keep the first one found" is usually the more natural behaviour.

**What does the condition produce?**
A `boolean`. If `true`, the block runs. If `false`, the loop moves on with `max` unchanged.

---

## 4.5 — `max = arrayEle[i];`

```java
max = arrayEle[i];
```

**What is this?**
Replacing the running maximum with a newly found larger value.

**Why is it inside the `if`?**
Because it must happen only when the test passes. Placement inside the braces is what makes it conditional.

**How is this different from the sum accumulator?**

Compare the two update rules:

```java
sum = sum + arr[i];      // ALWAYS runs — combines old with new
max = arr[i];            // runs CONDITIONALLY — replaces old with new
```

> [!important] Two shapes of accumulator
> | | Combining accumulator | Selecting accumulator |
> |---|---|---|
> | examples | sum, product, string build | maximum, minimum |
> | update | `acc = acc OP item` | `acc = item` |
> | runs | every iteration | only when a condition holds |
> | uses old value? | yes, in the expression | no, only in the *test* |
>
> For max, the old value appears in the **condition** (`arr[i] > max`) but not in the **assignment** (`max = arr[i]`). That is the structural signature of a selecting accumulator, and recognising it tells you instantly what a loop is doing.

**What does Java do?**
Copies the value at `arrayEle[i]` into `max`, discarding the previous contents.

---

## 4.6 — `System.out.println("The largest element in a Array is :" +max);`

```java
System.out.println("The largest element in a Array is :" +max);
```

**What is this?**
Printing the label and the result.

**What does each part mean?**

| Part | What it is |
|---|---|
| `System` | a class in `java.lang` |
| `out` | a `static` field of type `PrintStream` |
| `println` | prints its argument, then a newline |
| `"The largest element in a Array is :"` | a `String` literal |
| `+` | string concatenation — the left side is a `String` |
| `max` | the `int` to append |

**What does Java do?**

```text
"The largest element in a Array is :"  +  325
              String                       int
                        ↓
              int converted to "325"
                        ↓
"The largest element in a Array is :325"
                        ↓
                  passed to println
```

**Output**

```text
The largest element in a Array is :325
```

> [!note] Two small cosmetic issues
> - `"in a Array"` should read `"in an Array"`.
> - There is no space before the number, so it prints `is :325` rather than `is : 325`.
>
> Neither affects correctness. They are mentioned because **reading output critically is part of reading code critically** — and because a grammatically odd message in a real product is the kind of thing a reviewer will flag.

---

# 5. How to think like the programmer

```text
Requirement
"Find the biggest number in a list"
        ↓
What data do I have?
Eleven whole numbers → int[]
        ↓
What do I need to produce?
One number → many in, one out → a reduction
        ↓
Reductions need:
  (a) a place for the running answer
  (b) a starting value
  (c) a rule per item
        ↓
(a) int max
        ↓
(b) What starting value leaves the answer unchanged?
For addition it was 0.
For maximum... is there a number
smaller than every possible input?
NO — no such int exists.
        ↓
So where does the seed come from?
From the data itself → int max = arrayEle[0];
        ↓
(c) What is the rule per item?
"If this one is bigger, keep it instead."
        ↓
How do I express "bigger"?
The > operator, producing a boolean
        ↓
How do I act only when it is true?
An if statement
        ↓
What is the action?
Replace: max = arrayEle[i];
(not combine — this is a SELECTING accumulator)
        ↓
How do I reach every element?
for (int i = 0; i < arrayEle.length; i++)
        ↓
Does starting at 0 cause a problem?
It compares element 0 with itself.
Harmless. (Starting at 1 would avoid it.)
        ↓
Trace to verify:
324 → 324 → 324 → ... → 325   ✓
        ↓
What happens if the array is empty?
arrayEle[0] would throw.
Note the limitation.
        ↓
Print the answer with a label.
```

> [!important] The selecting-accumulator skeleton
> ```java
> TYPE best = data[0];                       // seed from the data
> for (int i = 0; i < data.length; i++) {
>     if (BETTER(data[i], best)) {
>         best = data[i];
>     }
> }
> use(best);
> ```
>
> | Goal | BETTER | Seed |
> |---|---|---|
> | maximum | `data[i] > best` | `data[0]` |
> | minimum | `data[i] < best` | `data[0]` |
> | longest word | `word.length() > best.length()` | `words[0]` |
> | oldest person | `p.getAge() > best.getAge()` | `people[0]` |
> | closest to zero | `Math.abs(x) < Math.abs(best)` | `data[0]` |
>
> One skeleton, five programs. Change the comparison; keep everything else.

---

# 6. Deep explanation of important Java concepts used

## Array

A **fixed-size, ordered, same-type** collection stored contiguously.

- Length fixed at creation.
- All elements the same type.
- Indexed `0` to `length - 1`.
- Arrays are objects: created with `new`, living on the heap, reached through a reference.

## Reading `arrayEle[0]` at declaration time

```java
int max = arrayEle[0];
```

Two things happen in one line: an array element is **read**, and its value is **copied** into a new variable.

This works only because `arrayEle` already exists and has at least one element. Order matters:

```java
int max = arrayEle[0];                    // ✗ error: arrayEle not yet declared
int[] arrayEle = {324, 34};
```

**Java executes statements in order.** You can only use what already exists.

## Relational operators

| Operator | Meaning | Produces |
|---|---|---|
| `>` | greater than | `boolean` |
| `<` | less than | `boolean` |
| `>=` | greater than or equal | `boolean` |
| `<=` | less than or equal | `boolean` |
| `==` | equal to | `boolean` |
| `!=` | not equal to | `boolean` |

> [!warning] These work on numbers, not on objects
> ```java
> String a = "apple", b = "banana";
> if (a > b)  { }        // ✗ does not compile
> if (a.compareTo(b) < 0) { }   // ✓ the correct way for Strings
> ```
> For objects you need `compareTo` or a `Comparator` — which is exactly what [[Java_EmplSort_Code_Reading_Explanation_Obsidian|EmplSort]] uses to order employees.

## The `if` statement

```java
if (condition) {
    // runs only when condition is true
}
```

The condition **must** be a `boolean`. `if` is a **statement**: it performs an action and produces no value.

Note there is no `else` here, and none is needed. "Do nothing when the condition is false" is the default — writing an empty `else` would be noise.

## Two shapes of accumulator

| | Combining | Selecting |
|---|---|---|
| examples | sum, product, concatenation | max, min |
| seed | the identity value | the first element |
| update | `acc = acc OP item` — always | `acc = item` — conditionally |
| old value used in | the assignment | the condition |
| safe on empty input? | **yes** | **no** — reading `arr[0]` throws |

That last row is the practical consequence of the seed difference, and it is the crispest way to remember why the two programs behave differently on an empty array.

## Scope

```java
int max = arrayEle[0];                       // scope: rest of main
for (int i = 0; i < arrayEle.length; i++) {  // scope of i: the loop only
    if (arrayEle[i] > max) {
        max = arrayEle[i];                   // both visible here
    }
}
System.out.println(max);                     // max ✓ visible
// System.out.println(i);                    // ✗ i is gone
```

`max` is declared outside the loop because it must survive every pass and be readable afterwards. `i` is declared in the header because it is needed only during the loop.

---

# 7. Why this syntax?

## `>` vs `>=`

| Operator | On a tie | Effect on the stored value | Effect on a tracked index |
|---|---|---|---|
| `>` | does not update | none | keeps the **first** occurrence |
| `>=` | updates | none | keeps the **last** occurrence |

## `=` vs `==` vs `>`

Three symbols, three jobs, all present in this program:

```java
int max = arrayEle[0];       // =   stores a value
if (arrayEle[i] > max)       // >   asks a question
max = arrayEle[i];           // =   stores a value
```

There is no `==` here. Note that a comparison **produces** a value (`true`/`false`) while an assignment **stores** one.

## `[ ]` — two jobs

| Where | Meaning |
|---|---|
| after a type | "array of" — `int[] arrayEle` |
| after an array variable | "the element at" — `arrayEle[i]`, `arrayEle[0]` |

## `{ }` — two jobs

| Where | Meaning |
|---|---|
| after `new int[]` | array initialiser — the data |
| after `for`/`if`/method/class | block — a group of statements |

## `length` vs `length()` vs `size()`

| Type | Form |
|---|---|
| array | `arrayEle.length` |
| `String` | `str.length()` |
| `List` | `list.size()` |

## `+` — addition or concatenation?

```java
"The largest element in a Array is :" + max
```

The left operand is a `String`, so `+` concatenates and `max` is converted to text. Had both operands been numbers, `+` would add.

## Whitespace

The original writes `for( int i =0; i<arrayEle.length; i++)` with irregular spacing. Java ignores it entirely — `i<arrayEle.length` and `i < arrayEle.length` compile identically. Conventional style puts spaces around binary operators because it reads more easily, but this is style, not correctness.

---

# 8. Method discovery

## `println`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.io.PrintStream` |
| **Why can we call it?** | `System.out` is a `PrintStream` object |
| **Which overload runs?** | `println(String)` — concatenation happened first |
| **Returns?** | `void` |

## Is there a built-in "max of an array"?

An excellent question to ask, and the answer is instructive.

Type `arrayEle.` in IntelliJ. The list is tiny:

```text
length      clone()     equals()     hashCode()     toString()     getClass()
```

**No `max()`.** Arrays are a primitive language construct, not a rich class — so array algorithms are written manually or found in utility classes.

Now type `Math.`:

```text
abs()   max()   min()   pow()   sqrt()   floor()   ceil()   round()   ...
```

`Math.max()` exists — but read its signature carefully:

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.lang.Math` |
| **Why can we call it without an object?** | it is `static` — call it on the class |
| **What does it accept?** | **two** numbers, not an array |
| **What does it return?** | the larger of the two, same type as the arguments |

```java
Math.max(5, 9)          // → 9      ✓ two numbers
Math.max(arrayEle)      // ✗ does not compile — wrong argument type
```

> [!important] Read the parameter list, not just the name
> `Math.max` sounds like it should solve this problem. It does not, because it takes **two** values, not a collection.
>
> This is one of the most common beginner errors: choosing a method by its name and never checking what it accepts. **The signature is the contract.** Always read it.
>
> That said, `Math.max` *can* be used here — as the comparison step:
> ```java
> int max = arrayEle[0];
> for (int i = 1; i < arrayEle.length; i++) {
>     max = Math.max(max, arrayEle[i]);      // no if needed
> }
> ```
> This is a neat alternative: `Math.max` replaces the `if` entirely, because it *always* returns the larger of the two. Note how this turns the selecting accumulator back into a combining one — the update now runs unconditionally.

## The genuine one-liner

```text
"I want the max of an int[]."
        ↓
arrayEle.  →  no max()
        ↓
Math.max   →  takes two ints, not an array
        ↓
Search: "java max of int array"
        ↓
Find: Arrays.stream(int[]) returns an IntStream
      IntStream.max() returns an OptionalInt
        ↓
Why OptionalInt and not int?
Because an EMPTY array has no maximum —
there is no sensible int to return.
        ↓
int max = Arrays.stream(arrayEle).max().getAsInt();
```

> [!tip] `OptionalInt` is the library agreeing with us
> The standard library returns `OptionalInt` rather than `int` for exactly the reason discussed in section 2: **an empty collection has no maximum**. Java's designers hit the same wall and chose to make the emptiness explicit in the return type rather than crash or invent a value.
>
> When a method returns `Optional`, it is telling you *"there might be no answer"*. That is a design signal worth learning to read — and here it independently confirms that the seeding problem is real, not a beginner's worry.

---

# 9. Trace the program with real values

```text
Array:  [324, 34, 324, 53, 265, 2, 234, 234, 52, 231, 325]
Index:    0    1    2    3    4   5    6    7   8    9   10
Length: 11    → condition is  i < 11
Seed:   max = arrayEle[0] = 324
```

## Iteration table

```text
────────────────────────────────────────────────────────────────────────
Pass   i   arrayEle[i]   max (before)   arrayEle[i] > max ?   max (after)
────────────────────────────────────────────────────────────────────────
 —     —        —             324              —                324  ← seed
 1     0       324            324        324 > 324  false        324
 2     1        34            324         34 > 324  false        324
 3     2       324            324        324 > 324  false        324
 4     3        53            324         53 > 324  false        324
 5     4       265            324        265 > 324  false        324
 6     5         2            324          2 > 324  false        324
 7     6       234            324        234 > 324  false        324
 8     7       234            324        234 > 324  false        324
 9     8        52            324         52 > 324  false        324
10     9       231            324        231 > 324  false        324
11    10       325            324        325 > 324  TRUE         325  ← updated
 —    11        —             325              —                 325  ← exit
────────────────────────────────────────────────────────────────────────
```

## Reading the trace carefully

Three things are worth noticing:

**1. The maximum updates exactly once, on the very last pass.**

That is a consequence of this specific data: `325` sits at index `10`. Had the loop bound been `i < arrayEle.length - 1`, the loop would have exited after pass 10 and reported `324` — wrong, with no error.

**2. Pass 1 compares element 0 with itself.**

`324 > 324` is `false`. Harmless but redundant, since `max` was seeded from that element. Starting the loop at `i = 1` would skip it.

**3. The duplicate `324` at index 2 does not update `max`.**

Because `>` is strict. With `>=` it would have reassigned `max` to the same value — no visible difference, but one extra write.

## The exit condition

```text
After pass 11:  i++ makes i = 11
Check:          11 < 11  →  false
Loop exits.  arrayEle[11] is never evaluated.
```

Had the condition been `i <= arrayEle.length`, this check would pass and `arrayEle[11]` would throw `ArrayIndexOutOfBoundsException`.

## Console output

```text
The largest element in a Array is :325
```

---

# 10. Visualize data where useful

## The array

```text
index:   0     1     2     3     4    5     6     7    8     9    10
       ┌────┬────┬────┬────┬─────┬───┬─────┬─────┬────┬─────┬─────┐
       │324 │ 34 │324 │ 53 │ 265 │ 2 │ 234 │ 234 │ 52 │ 231 │ 325 │
       └────┴────┴────┴────┴─────┴───┴─────┴─────┴────┴─────┴─────┘
         ▲                                                     ▲
         │                                                     │
       seed                                              the answer
    max starts here                                  found on the last pass
```

## The running maximum over time

```text
max:  324 ─── 324 ─── 324 ─── 324 ─── 324 ─── 324 ─── 324 ─── 324 ─── 324 ─── 324 ─── 325
        │       │       │       │       │       │       │       │       │       │       │
i:      0       1       2       3       4       5       6       7       8       9      10
val:  324      34     324      53     265       2     234     234      52     231     325
                                                                                        ▲
                                                                              only update
```

A flat line for ten passes, then one jump at the very end. **The maximum is "sticky" — it changes rarely, and only upward.**

## Combining vs selecting accumulator

```text
     SUM (combining)                MAX (selecting)
─────────────────────────      ─────────────────────────
int sum = 0;                   int max = arr[0];
      ↑ identity                     ↑ seeded from data
                                       (no identity exists)

for each item:                 for each item:
  sum = sum + item;              if (item > max)
        ↑ ALWAYS runs                max = item;
        ↑ uses old value             ↑ runs CONDITIONALLY
          in the expression          ↑ old value used only
                                       in the TEST
```

## Loop control flow

```text
        max = arr[0]
        int i = 0
            │
            ▼
      ┌─▶ i < 11 ? ─── false ──▶ print max ──▶ done
      │     │ true
      │     ▼
      │  arr[i] > max ? ─── false ──┐
      │     │ true                  │
      │     ▼                       │
      │  max = arr[i]               │
      │     │                       │
      │     ◀───────────────────────┘
      │     ▼
      │    i++
      └─────┘
```

---

# 11. Alternative ways to write the same logic

### Beginner-friendly version — start at index 1

```java
int[] numbers = {324, 34, 324, 53, 265, 2, 234, 234, 52, 231, 325};

int max = numbers[0];

for (int i = 1; i < numbers.length; i++) {     // start at 1, not 0
    if (numbers[i] > max) {
        max = numbers[i];
    }
}

System.out.println("The largest element in the array is: " + max);
```

Starting at `1` skips the redundant self-comparison. It also *documents* the reasoning: index `0` is already accounted for by the seed.

### Cleaner version — the enhanced `for` loop

```java
int[] numbers = {324, 34, 324, 53, 265, 2, 234, 234, 52, 231, 325};

int max = numbers[0];

for (int number : numbers) {
    if (number > max) {
        max = number;
    }
}

System.out.println("The largest element in the array is: " + max);
```

No index at all, so off-by-one errors become structurally impossible. It does re-examine element `0`, but that costs one harmless comparison.

**Rule: if you do not need the index, do not create one.** This program only ever uses `i` to write `arrayEle[i]`, so it never truly needs the index.

### Using `Math.max` — no `if` required

```java
int max = numbers[0];

for (int i = 1; i < numbers.length; i++) {
    max = Math.max(max, numbers[i]);
}
```

`Math.max(a, b)` always returns the larger, so the update becomes unconditional. This converts the selecting accumulator into a combining one and removes the branch entirely.

### Robust version — a reusable method with validation

```java
public static int findMax(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("Cannot find the maximum of an empty array");
    }
    int max = numbers[0];
    for (int i = 1; i < numbers.length; i++) {
        if (numbers[i] > max) {
            max = numbers[i];
        }
    }
    return max;
}
```

Why this is better:

- **Reusable** on any array.
- **Testable** — you can assert on its return value.
- **Honest about failure** — an explicit message beats a raw `ArrayIndexOutOfBoundsException`.

### Concise version — streams

```java
import java.util.Arrays;

int max = Arrays.stream(numbers).max().getAsInt();
```

Note `getAsInt()`. `max()` returns an `OptionalInt` because an empty stream has no maximum, and `getAsInt()` throws `NoSuchElementException` if it is empty. Safer:

```java
int max = Arrays.stream(numbers).max().orElseThrow(
        () -> new IllegalArgumentException("empty array"));
```

### Sorting — correct but wasteful

```java
import java.util.Arrays;

Arrays.sort(numbers);
int max = numbers[numbers.length - 1];      // largest is now last
```

It works, and interviewers ask about it deliberately.

> [!warning] Why sorting is the wrong tool here
> Two reasons:
>
> 1. **It is slower.** Sorting is `O(n log n)`; a single scan is `O(n)`. To find one value you would be ordering all of them.
> 2. **It modifies the input.** `Arrays.sort` sorts **in place** — the caller's array is permanently rearranged. That is a side effect the caller did not ask for and may not expect.
>
> If asked "can you find the max by sorting?", say yes, then explain both problems. Recognising unnecessary work — and unwanted mutation — is exactly what the question is testing.

### Finding the index as well as the value

```java
int maxIndex = 0;

for (int i = 1; i < numbers.length; i++) {
    if (numbers[i] > numbers[maxIndex]) {
        maxIndex = i;
    }
}

System.out.println("Largest value " + numbers[maxIndex] + " at index " + maxIndex);
```

Here the `>` vs `>=` choice becomes visible: `>` reports the **first** occurrence of the maximum, `>=` reports the **last**.

---

# 12. Common beginner mistakes

## Mistake 1 — seeding `max` with `0`

**Incorrect code**

```java
int max = 0;
for (int i = 0; i < arr.length; i++) {
    if (arr[i] > max) { max = arr[i]; }
}
```

**Why it is wrong**
If every element is negative, nothing beats `0`, so the program reports `0` — a value that is not in the array.

```java
int[] arr = {-5, -3, -9};    // reports 0, should report -3
```

**What Java expects**
Nothing — this compiles fine. It is a logic error.

**Correct code**

```java
int max = arr[0];                    // seed from the data (preferred)
int max = Integer.MIN_VALUE;         // or the theoretical floor
```

**How to recognise it in future**
Ask: *"could the correct answer ever be smaller than my seed?"* If yes, the seed is wrong. Test with all-negative data.

---

## Mistake 2 — using `<` instead of `>`

**Incorrect code**

```java
if (arr[i] < max) { max = arr[i]; }
```

**Why it is wrong**
This finds the **minimum**, not the maximum. It would print `2`.

**Correct code**

```java
if (arr[i] > max) { max = arr[i]; }
```

**How to recognise it**
The compiler cannot help — both are valid. Sanity-check the answer: is the reported value plausibly the largest? Here `2` obviously is not.

---

## Mistake 3 — writing `max = arr[i] ` outside the `if`

**Incorrect code**

```java
for (int i = 0; i < arr.length; i++) {
    if (arr[i] > max) {
    }
    max = arr[i];         // ← outside the if
}
```

**Why it is wrong**
`max` is reassigned on every pass, so it ends up holding the **last** element (`325`) rather than the largest. Coincidentally correct for this data — which makes it worse, because the bug is hidden.

**Correct code**
Put the assignment inside the `if` block.

**How to recognise it**
Test with data where the largest value is **not** last: `{325, 34, 2}`. A correct program prints `325`; this one prints `2`.

> [!tip]
> This is why the choice of test data matters. This program's array has its maximum at the end — a poor test case, because two different bugs both produce the right answer on it. **Good test data puts the answer somewhere unremarkable.**

---

## Mistake 4 — declaring `max` inside the loop

**Incorrect code**

```java
for (int i = 0; i < arr.length; i++) {
    int max = arr[0];
    if (arr[i] > max) { max = arr[i]; }
}
System.out.println(max);   // error: cannot find symbol
```

**Why it is wrong**
`max` is recreated and reset each pass, and does not exist after the loop.

**Correct code**
Declare it before the loop.

**How to recognise it**
`cannot find symbol` on a variable you know you declared → you declared it in a narrower scope.

---

## Mistake 5 — off-by-one going too far

**Incorrect code**

```java
for (int i = 0; i <= arr.length; i++) {
```

**What Java does**

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException:
    Index 11 out of bounds for length 11
```

**Correct code**

```java
for (int i = 0; i < arr.length; i++) {
```

---

## Mistake 6 — off-by-one stopping too early

**Incorrect code**

```java
for (int i = 0; i < arr.length - 1; i++) {
```

**Why it is wrong**
Skips the last element. **For this exact array that means missing `325` and reporting `324`** — silently.

**Correct code**

```java
for (int i = 0; i < arr.length; i++) {
```

**How to recognise it**
`<` goes with `length`; `<=` goes with `length - 1`. Seeing `<` together with `- 1` should raise an immediate flag. This is the bug present in [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]].

---

## Mistake 7 — using `length()` on an array

**Incorrect code**

```java
for (int i = 0; i < arr.length(); i++) {   // error: cannot find symbol
```

**Correct code**

```java
for (int i = 0; i < arr.length; i++) {
```

---

## Mistake 8 — assuming the array is non-empty

**Incorrect code**

```java
int max = arr[0];      // throws if arr is empty
```

**Why it is wrong**
An empty array has no index `0`.

```text
ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
```

**Correct code**

```java
if (arr.length == 0) {
    throw new IllegalArgumentException("Cannot find max of an empty array");
}
int max = arr[0];
```

**How to recognise it**
Whenever you write `arr[0]`, ask *"can this array be empty?"* If it comes from outside your method, the answer is yes.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| Seed reasoning | **do you know why `max = arr[0]` and not `max = 0`?** |
| Loop bounds | correct handling of `i < length` |
| Accumulator variants | do you see that this replaces rather than combines? |
| Edge-case awareness | what happens on an empty array? |
| Efficiency judgement | do you know sorting is the wrong tool? |
| Comparison semantics | do you understand `>` vs `>=` with duplicates? |

> [!tip]
> The seed question is the one that separates candidates. Almost everyone can write the loop. Far fewer can explain *why* `int max = 0;` is a bug — and that explanation demonstrates real understanding rather than memorisation.

## Likely follow-up questions

> **"Why do you initialise `max` to `arr[0]` instead of `0`?"**

Because `0` is not smaller than every possible input. With all-negative data the program would report `0`, a value not in the array. Seeding from the data guarantees the answer is a real element. `Integer.MIN_VALUE` is the other valid choice.

> **"What is the time complexity?"**

`O(n)` — one pass, constant work per element. And it cannot be beaten: to find the largest you must look at every value at least once, since any unexamined element could be the answer.

> **"What is the space complexity?"**

`O(1)` — just `max` and `i`, regardless of array size.

> **"Could you sort the array and take the last element?"**

Yes, but it is `O(n log n)` instead of `O(n)`, and `Arrays.sort` mutates the caller's array. Both are reasons not to.

> **"What happens on an empty array?"**

`arr[0]` throws `ArrayIndexOutOfBoundsException`. Note the contrast with summing, which handles empty input gracefully because `0` is a valid answer — a maximum of nothing simply does not exist. That is also why `IntStream.max()` returns `OptionalInt`.

> **"How would you find the second largest?"**

Track two variables. Warn about the seeding trap — see [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]] in this folder, which has exactly that bug.

> **"How would you find the minimum?"**

Identical structure with `<` instead of `>`. Same seed reasoning.

> **"What if there are duplicates of the maximum?"**

The value is the same either way. If you also track the index, `>` keeps the first occurrence and `>=` keeps the last.

> **"What if you also needed the index of the maximum?"**

Track `maxIndex` instead of `max` and compare `arr[i] > arr[maxIndex]`.

---

# 14. Complexity

## Time complexity: `O(n)`

Let `n` be the number of elements.

The loop runs once per element, doing constant work each pass: one array read, one comparison, sometimes one assignment.

In beginner language:

> "The loop looks at each number exactly once. With 11 numbers it does 11 comparisons; with 11,000 it does 11,000. Double the array and you double the work. Because the work grows in direct proportion to the number of elements, we call it linear time and write it `O(n)`."

> [!note] This is optimal, and here is the proof
> You cannot find the maximum faster than `O(n)`. Suppose an algorithm skipped even one element — that element could have been the largest, so the answer would be wrong.
>
> **Every element must be examined at least once.** Therefore `O(n)` is the theoretical floor, and this program achieves it.
>
> Being able to argue *why* an algorithm is optimal — not just state its complexity — is a strong interview signal.

**Best, worst and average case** are all `O(n)`. Unlike a search, there is no early exit: even if the first element turns out to be the largest, you cannot *know* that without checking the rest.

## Space complexity: `O(1)`

Two extra variables — `max` and `i` — regardless of array size.

> "We use the same two variables whether the array holds 11 elements or 11 million, so the extra memory is constant: `O(1)`."

## Compared with sorting

| Approach | Time | Space | Mutates input? |
|---|---|---|---|
| single scan (this program) | `O(n)` | `O(1)` | no |
| `Arrays.sort` then take last | `O(n log n)` | `O(log n)` for the sort | **yes** |

For `n = 1,000,000`: roughly one million operations versus about twenty million. **Choosing the right algorithm matters far more than micro-optimising the wrong one.**

---

# 15. Edge cases

## Empty array — the program crashes

```java
int[] arrayEle = {};        // length 0
```

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException:
    Index 0 out of bounds for length 0
        at LargestArrayElement.main(LargestArrayElement.java:16)
```

The crash happens at `int max = arrayEle[0];` — **before the loop even starts**.

> [!warning] Why max crashes where sum does not
> ```java
> // SUM — safe on empty input
> int sum = 0;                        // no array access
> for (...) { }                       // body never runs
> // result: 0  ✓ mathematically correct
>
> // MAX — crashes on empty input
> int max = arrayEle[0];              // ✗ reads the array immediately
> ```
>
> The difference traces straight back to the seed. Sum has an identity value (`0`) that requires no data. Max has none, so it must reach into the array — and an empty array has nothing to reach for.
>
> This is not a flaw in the code so much as a property of the problem: **the maximum of nothing does not exist.** Java's own `IntStream.max()` acknowledges this by returning `OptionalInt` rather than `int`.
>
> A defensive version:
> ```java
> if (arrayEle.length == 0) {
>     System.out.println("Array is empty — no maximum exists");
>     return;
> }
> ```

## Single element

```java
int[] arrayEle = {42};
```

- `max = 42`.
- One pass: `42 > 42` is `false`.
- Output: `42`. **Correct.**

## All elements equal

```java
int[] arrayEle = {7, 7, 7};
```

- `max = 7`. No comparison ever succeeds (`>` is strict).
- Output: `7`. **Correct.**

## All negative

```java
int[] arrayEle = {-5, -3, -9};
```

- `max = -5`.
- `-3 > -5` → `max = -3`.
- `-9 > -3` → no.
- Output: `-3`. **Correct** — and this is exactly the case that a `max = 0` seed would get wrong.

## Maximum at the first position

```java
int[] arrayEle = {999, 1, 2};
```

`max = 999`, nothing beats it. Output: `999`. Correct — and worth testing, because it catches the "assignment outside the `if`" bug from section 12.

## Maximum at the last position

That is this program's actual data. It correctly finds `325` **only because the loop bound is right**. A `length - 1` bound would silently report `324`.

## Duplicates of the maximum

```java
int[] arrayEle = {325, 100, 325};
```

Output: `325`. Correct. With `>`, `max` is set from index `0` and index `2` does not trigger a reassignment — same value either way.

## Very large values

```java
int[] arrayEle = {2147483647, 5};      // Integer.MAX_VALUE
```

Output: `2147483647`. Correct. **Comparison never overflows** — unlike addition, `>` performs no arithmetic. This program is completely free of overflow risk, which is a genuine advantage over [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]].

## `null` array

```java
int[] arrayEle = null;
```

`arrayEle[0]` throws `NullPointerException`. Not possible here, since the array is initialised where it is declared — but relevant for any method that receives an array from elsewhere.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Find the largest number in a list.

        ↓

What data do I have?
Eleven whole numbers → int[]

        ↓

What do I need to produce?
One number → many in, one out → a reduction

        ↓

How would a HUMAN do this with a stack of cards?
"Hold the first card. For each new card,
 if it is bigger, swap it for the one I hold.
 At the end, I am holding the biggest."

        ↓

Translate that directly:
"the card I am holding" → a variable → int max

        ↓

What is its starting value?
For summing it was 0, because adding 0
changes nothing.
For maximum, is there a number smaller
than every possible input?
NO. No such int exists.

        ↓

So the seed must come from the data itself:
int max = arrayEle[0];
(the first card is the biggest so far,
 because it is the only one I have seen)

        ↓

Consequence to note: this requires at least
one element. An empty array will crash.
That is inherent to the problem —
the maximum of nothing does not exist.

        ↓

What is the rule per element?
"If this one is bigger, keep it instead."

        ↓

How do I express "bigger"?
The > operator → produces a boolean

        ↓

How do I act only when it is true?
An if statement

        ↓

What is the action?
Replace, not combine:  max = arrayEle[i];
The old value is used in the TEST,
not in the assignment.

        ↓

How do I reach every element?
for (int i = 0; i < arrayEle.length; i++)

        ↓

Check the bound carefully:
last valid index is 10, and 325 lives there.
i < length  →  0..10  ✓
i < length-1 → 0..9   ✗ would report 324

        ↓

Trace to verify:
324 stays for ten passes, then 325 on the last  ✓

        ↓

Sanity-check with awkward data:
all negative → seeded from data, works  ✓
max at the front → works  ✓
empty → crashes  ⚠ note the limitation

        ↓

Print with a label.
```

> [!important] The one decision that mattered
> Everything here was mechanical except **choosing the seed**. That choice came from asking:
>
> *"What value can I start with that will not distort the answer?"*
>
> For sum, the question has an answer (`0`). For maximum, it does not — and recognising that *the question has no answer* is what tells you to seed from the data instead.
>
> Learning to notice when a familiar technique does not apply is more valuable than learning one more technique.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int[] arrayEle = new int[]{324,34,...,325};` | "Create a row of eleven boxes holding whole numbers, fill them with these values, and let `arrayEle` point at that row." |
| `int max = arrayEle[0];` | "Make a whole-number box called `max` and copy into it the number from the very first box — because I have to start somewhere, and the first number is the biggest one I have seen so far." |
| `for(int i = 0; i < arrayEle.length; i++)` | "Start a counter at 0. Keep going while the counter is below the number of boxes. After each pass, add 1." |
| `if(arrayEle[i] > max)` | "Ask whether the number in box `i` is bigger than the best I have found so far." |
| `max = arrayEle[i];` | "If it is, forget the old best and remember this number instead." |
| `System.out.println("The largest element in a Array is :" + max);` | "Take the label text, stick the final value of `max` on the end, and print the whole line." |

The loop in one sentence:

> **"Walk through every box, and whenever you find a number bigger than the best so far, make that the new best."**

And the whole program:

> **"Start by assuming the first number is the biggest. Look at every other number, and each time you find a bigger one, remember it instead. When you reach the end, whatever you are remembering is the answer."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| array | fixed-size, ordered, same-type collection, indexed `0` to `length-1` |
| accumulator | a variable declared before a loop that builds up an answer |
| **combining accumulator** | `acc = acc OP item` — always runs (sum, product) |
| **selecting accumulator** | `acc = item` — runs conditionally (max, min) |
| identity value | the seed that changes nothing — **does not exist for max** |
| seeding from data | `arr[0]` — required when no identity exists |
| relational operator | `>`, `<`, `>=`, `<=` — compare numbers, produce `boolean` |
| `if` statement | conditional action; requires a `boolean` |
| scope | a variable lives only inside the `{ }` where it was declared |

### Important methods

| Member | Owner | Accepts | Returns |
|---|---|---|---|
| `length` | array | — (a **field**) | `int` |
| `Math.max(int,int)` | `java.lang.Math` | **two** numbers, not an array | the larger |
| `Arrays.stream(int[])` | `java.util.Arrays` | an `int[]` | `IntStream` |
| `IntStream.max()` | `java.util.stream.IntStream` | — | **`OptionalInt`** — empty has no max |
| `Arrays.sort(int[])` | `java.util.Arrays` | an `int[]` | `void` — sorts **in place** |
| `println(String)` | `java.io.PrintStream` | text | `void` |

### Important syntax

| Syntax | Meaning |
|---|---|
| `int[] arr` | a reference to an array of `int` |
| `arr[0]` | the first element |
| `arr[i]` | the element at position `i` |
| `arr.length` | how many elements — no parentheses |
| `>` | greater than |
| `>=` | greater than or equal — differs from `>` only on ties |
| `if (cond) { }` | run the block only when `cond` is `true` |
| `for (int x : arr)` | enhanced for — no index, no off-by-one |
| `+` | concatenation when a `String` is on the left |

### Main interview concept

> **When an accumulator has no identity value, seed it from the data.** `int max = 0;` is a bug, because `0` is not smaller than every possible input — with all-negative data it reports a value that is not in the array. Use `arr[0]` (guarantees a real element) or `Integer.MIN_VALUE`. The direct consequence is that finding a maximum requires a non-empty array, which is why `IntStream.max()` returns `OptionalInt`.

### Main lesson for code reading

> **Ask what the accumulator's seed reveals about the problem.**
>
> ```text
> int sum = 0;          → an identity exists → empty input is fine → answer is 0
> int factorial = 1;    → an identity exists → empty input is fine → answer is 1
> int max = arr[0];     → NO identity exists → empty input CRASHES → no answer exists
> ```
>
> The seed is not a detail. It tells you where the answer comes from, whether the loop can handle empty input, and whether the result is guaranteed to be a real member of the data. Read the line before the loop before you read the loop.

---

### Related notes

- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — the combining accumulator, seeded with an identity value
- [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]] — two running maxima, and a seeding bug worth studying
- [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] — the `length - 1` bug that would break this program
- [[Java_Factorials_Code_Reading_Explanation_Obsidian|Factorials]] — the identity value seen from the multiplication side
- [[Java_EmplSort_Code_Reading_Explanation_Obsidian|EmplSort]] — comparing objects, where `>` no longer works
