---
title: Java Code Reading — TernaryOperatorTime
tags:
  - java
  - interview-programs
  - code-reading
  - operators
  - conditionals
aliases:
  - TernaryOperatorTime Explained
  - Ternary Operator Explained
---

# Java Code Reading — TernaryOperatorTime Explained for a Fresh Java Programmer

> [!note]
> This is the shortest program in the whole collection — four real lines — and yet it contains one of the ideas beginners most often misunderstand: the difference between a **statement** (something Java *does*) and an **expression** (something Java *evaluates to a value*).
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Is this a decision or a calculation? → Which Java construct produces that? → What type comes out? → Where does it go?**
>
> By the end of this note you should be able to explain, precisely, *why* you can write `String result = time < 12 ? "A" : "B";` but you cannot write `String result = if (time < 12) ...`.

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class TernaryOperatorTime
{
    public static void main(String[] args)
    {
        int time = 13;

        String result = time<12 ? "Good Morning" : "Good Afternoon";

        System.out.println(result);
    }
}
```

The executable logic is three lines:

```java
int time = 13;
String result = time<12 ? "Good Morning" : "Good Afternoon";
System.out.println(result);
```

The middle line is the one worth studying carefully.

---

# 2. What problem is this program solving?

In plain language:

> Given the hour of the day, choose the right greeting. Before 12 o'clock it is morning; from 12 onwards it is afternoon. Print the greeting.

### What do we know?

- One number representing the hour, using a 24-hour clock.
- A cut-off point: `12`.
- Two possible greetings.

### What do we need?

- Exactly **one** greeting — a single piece of text — chosen based on the number.

### What transformations are required?

Two, in order:

1. A **comparison** — is the hour less than 12? This turns a number into a yes/no answer.
2. A **selection** — use that yes/no answer to pick one of two texts.

```text
int (13)
   ↓  compare with 12
boolean (false)
   ↓  select
String ("Good Afternoon")
```

> [!important] The key observation
> We are not *calculating* the greeting from the number — there is no formula that turns `13` into `"Good Afternoon"`. We are **choosing** between two values that were written down in advance.
>
> "Choosing between two ready-made values based on a condition" is precisely the job of the **ternary operator**. Recognising this shape in a requirement is what tells you to reach for `? :` rather than anything else.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares which group this class belongs to. It matches the folder the file lives in. Must be the first statement in the file.

## `public class TernaryOperatorTime`

- `public` — usable from anywhere.
- `class` — the keyword declaring a class.
- `TernaryOperatorTime` — the name, in `PascalCase`, matching the file `TernaryOperatorTime.java`.

The name is descriptive: it tells you the class *demonstrates the ternary operator using a time value*. That is good naming for a learning program.

## `public static void main(String[] args)`

The entry point the JVM looks for.

| Part | Meaning | Why it is required |
|---|---|---|
| `public` | reachable from outside | the JVM must be able to call it |
| `static` | tied to the class, not an object | the JVM has not built any object yet |
| `void` | returns nothing | the JVM has nowhere to put a returned value |
| `main` | the name the JVM searches for | fixed by the Java launcher |
| `String[] args` | command-line arguments | text typed after the program name lands here |

## Imports

There are none, and there is a reason: the program uses `int` (a primitive), `String` and `System`. `String` and `System` both live in the package `java.lang`, which every Java file imports automatically.

**Reading habit:** when you see a file with no imports, you immediately know it uses nothing beyond the core language.

## Variables

Two, both local to `main`:

| Variable | Type | Purpose |
|---|---|---|
| `time` | `int` | the input — the hour of the day |
| `result` | `String` | the output — the chosen greeting |

Notice that they have **different types**. The program's whole job is to convert from one to the other.

---

# 4. Line-by-line explanation

## 4.1 — `int time = 13;`

```java
int time = 13;
```

**What is this?**
A declaration with an initialisation: create a whole-number variable named `time`, containing `13`.

**Why do we need it?**
The decision must be based on *something*. This line supplies the input. In a real application this value would come from a clock or from user input; here it is hard-coded so the program is easy to run and predict.

**How would a beginner know to write it?**
Ask: "What information does my decision depend on?" The answer is "the hour." Anything a decision depends on must exist as data before the decision is made.

**What type of data is involved?**
An hour on a 24-hour clock: a whole number from `0` to `23`.

**Why was this data type selected?**

| Candidate | Verdict |
|---|---|
| `int` | **chosen** — hours are whole numbers, and `int` is Java's standard whole-number type |
| `double` | wrong — `13.0` implies fractional hours, which we never use |
| `byte` | would technically fit (range −128..127), but nobody writes this; `int` is the idiom |
| `String` | wrong — `"13"` is text. You cannot ask whether text is "less than 12" in a numeric sense |

The deciding question is: **will I do arithmetic or numeric comparison with this?** Here we compare it with `12`, so it must be a number.

**Why the name `time`?**
It describes the meaning of the data. `hour` would arguably be even better, since the value is specifically an hour rather than a full timestamp — but `time` is clear enough and reads naturally in `time < 12`.

**What does Java do when it reaches this line?**
Reserves 32 bits of memory, labels it `time`, writes the value `13` into it.

**What does it produce?**
No value is returned; the effect is that memory now holds `13` under the name `time`.

**Where does the result go?**
Into the `time` slot on `main`'s stack frame.

---

## 4.2 — `String result = time<12 ? "Good Morning" : "Good Afternoon";`

```java
String result = time<12 ? "Good Morning" : "Good Afternoon";
```

This is the line that matters. Do not try to swallow it whole. Break it apart exactly as you would break apart a long sentence:

```text
String                     ← the type of what we are storing
result                     ← the name of the storage
=                          ← "put the right-hand value here"
time<12                    ← the condition (produces true or false)
?                          ← "if that was true, use..."
"Good Morning"             ← the value when true
:                          ← "...otherwise use..."
"Good Afternoon"           ← the value when false
;                          ← end of statement
```

Now take each piece.

### `String` — why this type?

**What is this?**
The declared type of `result`.

**How would a beginner know to choose it?**
Look at what the expression can produce. Both possible outcomes — `"Good Morning"` and `"Good Afternoon"` — are text in double quotes, which means both are `String` values. The holder's type must match what it holds. Therefore `String`.

> [!important] The type-matching rule, again
> This is the same rule as everywhere else in Java:
>
> ```text
> the type of the variable   MUST MATCH   the type produced by the expression
> ```
>
> The ternary expression here produces a `String`, so the variable must be a `String`. If both branches produced `int` values, the variable would need to be an `int`.

### `result` — why this name?

It holds the outcome of the decision. `greeting` or `message` would be even more descriptive, since the value *is* a greeting. `result` is acceptable and very common. What matters is that it is not `x` or `s`.

### `=` — assignment

Evaluate everything on the right first, then copy the outcome into the variable on the left.

> [!warning]
> `=` stores. `==` compares. This line uses `=` (store), while the condition inside it uses `<` (compare). Two different jobs on one line — read them separately.

### `time<12` — the condition

**What is this?**
A comparison expression using the **less-than** relational operator.

**Why do we need it?**
The requirement said "before 12 it is morning." Translated to Java, "before 12" is `time < 12`.

**What does it produce?**
A `boolean` — either `true` or `false`. This is important and frequently missed: **a comparison is not a question you ask, it is an expression that evaluates to a value.**

With `time` being `13`:

```text
time < 12
 13  < 12
   false
```

**What type is involved?**
Both sides are `int`, so this is a numeric comparison. The result is `boolean`.

> [!note] Spacing
> The original is written `time<12` with no spaces. Java ignores whitespace here, so `time<12`, `time < 12` and `time  <  12` all compile identically. Conventional Java style puts spaces around binary operators — `time < 12` — because it reads more easily. This is a style preference, not a correctness issue.

### `? :` — the conditional (ternary) operator

**What is this?**
Java's only operator that takes **three** operands. That is what "ternary" means — the same way "binary" means two (as in `a + b`) and "unary" means one (as in `-a` or `i++`).

Its shape is always:

```text
condition ? valueIfTrue : valueIfFalse
```

**Why do we need it here?**
Because we want the *decision itself* to produce a value that we can assign in a single statement.

**How would a beginner know to reach for it?**
Ask this diagnostic question:

> "Am I choosing **which value to store**, or am I choosing **which action to perform**?"

- Choosing a **value** → ternary operator fits beautifully.
- Choosing an **action** (print this, save that, call this method) → use `if / else`.

Here we are choosing which of two `String`s to store. That is a value choice. Ternary.

**What does Java do when it reaches it?**

1. Evaluate the condition `time < 12` → `false`.
2. Because it is `false`, evaluate **only** the part after the `:` → `"Good Afternoon"`.
3. Produce that as the value of the whole expression.

> [!important] Only one branch is evaluated
> Java does **not** compute both `"Good Morning"` and `"Good Afternoon"` and then throw one away. It evaluates the condition, then evaluates only the branch it needs. This is called **short-circuit** or **lazy** evaluation.
>
> With `String` literals this makes no observable difference. But it matters enormously when the branches call methods:
>
> ```java
> String s = (list == null) ? "empty" : list.get(0).toString();
> ```
>
> If `list` is `null`, the right branch is never touched, so no `NullPointerException` occurs. If both branches were evaluated, this code would crash.

### `"Good Morning"` and `"Good Afternoon"`

Two `String` **literals**. Double quotes mean `String`; single quotes would mean `char` and could hold only one character.

Both branches must produce **compatible types**. Here both are `String`, so the whole expression is a `String`. If you wrote:

```java
String result = time < 12 ? "Good Morning" : 42;   // suspicious
```

the types no longer match cleanly and the compiler will complain when you try to store the result in a `String`.

### `;`

Ends the statement.

**What does the whole line produce?**
The `String` `"Good Afternoon"`.

**Where does that result go?**
Into the variable `result`.

---

## 4.3 — `System.out.println(result);`

```java
System.out.println(result);
```

**What is this?**
A method call that writes text to the console followed by a newline.

**Why do we need it?**
Without it the program computes the right greeting and then throws it away silently. Printing is how the program communicates its answer.

**How would a beginner know to write it?**
Whenever a program produces an answer, it must *do* something with it. The simplest something is to display it.

**What does each part mean?**

| Part | What it is |
|---|---|
| `System` | a class in `java.lang` |
| `out` | a `static` field inside `System`, of type `PrintStream` |
| `println` | an instance method on that `PrintStream` |
| `result` | the argument — the `String` we want printed |

**What does Java do when it reaches this line?**
Reads the value stored in `result` (`"Good Afternoon"`), passes it to `println`, which writes those characters to the console and then a line separator.

**What does it return?**
`void` — nothing. That is why you can never write `String x = System.out.println(result);`.

**Where does the result go?**
To the console. It leaves your program entirely.

> [!tip] Notice what is *not* here
> There is no `+` in this print statement, because there is nothing to join — we are printing one `String` and nothing else. Compare with `SwapNumbers`, where `+` was needed to glue a label onto numbers. Use `+` only when you actually have separate pieces to combine.

---

# 5. How to think like the programmer

```text
Requirement
"Print a greeting that depends on the hour"
        ↓
Identify the input
An hour → a whole number → int time
        ↓
Identify the output
One piece of text → String
        ↓
Notice the types differ
Input is int, output is String.
Something must bridge them.
        ↓
Is the bridge a calculation or a choice?
There is no formula turning 13 into "Good Afternoon".
The two greetings are fixed, written in advance.
Therefore: a CHOICE.
        ↓
What decides the choice?
A rule: "before 12 → morning, otherwise → afternoon"
        ↓
Express the rule in Java
time < 12   →   produces a boolean
        ↓
Am I choosing a VALUE or an ACTION?
A value — which String to store.
        ↓
Which construct chooses a value?
The ternary operator:  condition ? a : b
        ↓
Assemble it
time < 12 ? "Good Morning" : "Good Afternoon"
        ↓
What type does that expression produce?
Both branches are String → the expression is a String
        ↓
Declare a matching holder
String result = ...
        ↓
Do something with the answer
System.out.println(result);
```

> [!tip] The reusable question
> **"Value or action?"** is the question that decides between `? :` and `if / else` every single time.
>
> ```java
> // choosing a VALUE → ternary reads beautifully
> String label = isAdmin ? "Administrator" : "User";
>
> // choosing an ACTION → if/else is correct, ternary is not even legal
> if (isAdmin) {
>     grantAccess();
>     writeAuditLog();
> } else {
>     denyAccess();
> }
> ```

---

# 6. Deep explanation of important Java concepts used

## `int`

A **primitive** type holding a 32-bit whole number, range roughly −2.1 billion to +2.1 billion. It stores the value directly rather than a reference to an object elsewhere. Default choice for any whole number in Java.

## `String`

A `String` is an **object** representing a sequence of characters. Unlike `int`, it is a class (`java.lang.String`), not a primitive.

Two facts worth knowing now:

1. **`String` is immutable.** Once created, its characters can never change. Every operation that appears to modify a `String` actually produces a brand-new one.
2. **A `String` variable holds a reference**, not the characters themselves. `String result` is a signpost pointing at the text stored elsewhere in memory.

```text
result ──────▶ "Good Afternoon"
(a reference)   (the actual object)
```

## `boolean`

Java's yes/no type. It has exactly two possible values: `true` and `false`. That is all — there is no "0 means false" rule as in some other languages.

```java
boolean isMorning = time < 12;   // perfectly legal — the comparison produces a boolean
```

You could rewrite the program using a named boolean, which is often clearer:

```java
boolean isMorning = time < 12;
String result = isMorning ? "Good Morning" : "Good Afternoon";
```

> [!tip]
> Giving a condition a name is one of the easiest ways to make code readable. `isMorning` explains *why* the comparison matters; `time < 12` only explains *what* it computes.

## Expression vs statement — the central idea of this program

This distinction is the reason the ternary operator exists.

| | Expression | Statement |
|---|---|---|
| **Definition** | a piece of code that **evaluates to a value** | a complete instruction that **performs an action** |
| **Examples** | `5 + 3`, `time < 12`, `a ? b : c`, `name.length()` | `int x = 5;`, `if (...) { ... }`, `System.out.println(x);` |
| **Can be assigned?** | yes — it produces something to store | no — there is nothing to store |

So:

```java
String result = time < 12 ? "Good Morning" : "Good Afternoon";   // ✓ ternary is an EXPRESSION
```

```java
String result = if (time < 12) "Good Morning" else "Good Afternoon";   // ✗ if is a STATEMENT
```

> [!warning] This is the single most common confusion about `? :`
> `if` and `? :` are **not** two spellings of the same thing.
>
> - `if` is a **statement**: it *does* something. It has no value, so nothing can be assigned from it.
> - `? :` is an **expression**: it *produces* a value, so it can appear anywhere a value can — on the right of `=`, inside a method call, inside a `return`, inside string concatenation.
>
> That is exactly why this program can fit its whole decision on one line.

Because the ternary is an expression, all of these are legal:

```java
System.out.println(time < 12 ? "Good Morning" : "Good Afternoon");   // inside a call
return time < 12 ? "AM" : "PM";                                       // inside a return
String msg = "It is " + (time < 12 ? "morning" : "afternoon") + ".";  // inside concatenation
```

None of those can be done with `if`.

## Relational operators

| Operator | Meaning | Example with `time = 13` | Result |
|---|---|---|---|
| `<` | less than | `time < 12` | `false` |
| `>` | greater than | `time > 12` | `true` |
| `<=` | less than or equal to | `time <= 13` | `true` |
| `>=` | greater than or equal to | `time >= 13` | `true` |
| `==` | equal to | `time == 13` | `true` |
| `!=` | not equal to | `time != 12` | `true` |

All six take two numbers and produce a `boolean`. Choosing between `<` and `<=` is where **off-by-one bugs** are born — see the edge cases section.

## Ternary operator, formally

```text
condition ? expressionIfTrue : expressionIfFalse
    │              │                    │
 boolean        value A              value B
```

Rules to remember:

1. The condition **must** be a `boolean`. In Java you cannot write `5 ? a : b` the way you can in C.
2. Both branches must produce compatible types; the result type is derived from them.
3. Only the taken branch is evaluated.
4. It can be nested, but readability collapses quickly — see section 11.

## `System.out.println()`

- `System` — a class in `java.lang` providing access to the environment.
- `out` — a `static` field of type `PrintStream`, wired to the console.
- `println` — an overloaded instance method that prints its argument and then a newline.

`System.out.println(result)` reads as: *"From the class `System`, take the object in the field `out`, and ask it to `println` this value."*

---

# 7. Why this syntax?

## `?` and `:` — why these symbols?

They are simply the two-part punctuation Java inherited from C for the conditional operator. Read them aloud as English:

```text
time < 12  ?  "Good Morning"  :  "Good Afternoon"
   │       │        │         │        │
"if"    "then"    this     "else"    that
```

Reading `?` as **"then"** and `:` as **"otherwise"** makes the line instantly parseable:

> "Is time less than 12? **Then** `"Good Morning"`, **otherwise** `"Good Afternoon"`."

## `<` vs `<=`

`<` is strictly less than. `time < 12` is `false` when `time` is exactly `12`.

That is the correct behaviour here: 12:00 is noon, which is afternoon, not morning. If the code had used `time <= 12`, then 12 o'clock would be greeted with "Good Morning", which is wrong.

> [!tip]
> Whenever you write a comparison, always test the **boundary value** explicitly in your head. Here the boundary is `12`. Ask: "what should happen at exactly 12?" Answer that first, and the choice between `<` and `<=` becomes obvious rather than a guess.

## `"text"` vs `'c'`

| Syntax | Type | Holds |
|---|---|---|
| `"Good Morning"` | `String` | any number of characters, including zero |
| `'G'` | `char` | exactly one character |

Since a greeting is many characters, double quotes are required.

## `=` vs `==`

`=` assigns. `==` compares.

This line uses `=` once (to store into `result`) and `<` once (to compare). If you accidentally wrote `time = 12` inside the condition, you would be *setting* time to 12 rather than *asking* whether it is less than 12 — and the code would not compile here, because `time = 12` produces an `int`, not the `boolean` the ternary requires.

> [!note]
> Java's strict `boolean` requirement is a safety feature. In C, `if (x = 5)` compiles and silently assigns. In Java, it is a compile error unless `x` is a `boolean`. The compiler catches the mistake for you.

## `;` — statement terminator

Marks the end of the statement, not the end of the line. The declaration and its ternary could legally be spread over several lines:

```java
String result = time < 12
        ? "Good Morning"
        : "Good Afternoon";
```

This multi-line layout is very common and often the most readable way to write a ternary.

## `public`, `static`, `void`, `class`

| Keyword | Meaning |
|---|---|
| `public` | accessible from anywhere |
| `static` | belongs to the class itself, no object needed |
| `void` | this method returns nothing |
| `class` | declares a class |

---

# 8. Method discovery

This program calls exactly one method, but there are still useful discovery lessons.

## `println`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.io.PrintStream` |
| **Why can we call it here?** | `System.out` *is* a `PrintStream` object; you may call any public method on an object you hold |
| **What does it do?** | writes the text form of its argument, then a line separator |
| **What arguments does it accept?** | overloaded for `String`, `int`, `double`, `char`, `boolean`, `Object`, and more |
| **Which overload runs here?** | the `String` one, because `result` is a `String` |
| **What does it return?** | `void` |
| **How does that affect the next line?** | you can never assign from it — there is no value |

## Discovering the ternary operator itself

Here is an honest point: **the ternary operator is not a method, so autocomplete will not offer it to you.** Operators are part of the language grammar, not the library.

So how would a beginner find it? Through the requirement's shape:

```text
"I want to store one of two values depending on a condition."
        ↓
Search: "java assign value based on condition one line"
        ↓
Find: the conditional / ternary operator  ? :
```

> [!important] Two different kinds of learning
> Java knowledge splits into two piles, and you find things in each pile differently:
>
> | Pile | Examples | How to discover |
> |---|---|---|
> | **Language constructs** | `if`, `for`, `while`, `? :`, `+`, `class` | learn once from a tutorial or language reference; there are only a few dozen |
> | **Library methods** | `println`, `split`, `trim`, `sort`, `length` | discover on demand via autocomplete and Javadoc; there are tens of thousands |
>
> The first pile is small and finite — **learn it properly**. The second pile is effectively unlimited — **learn how to search it**, not how to memorise it.

## How you would confirm the return type in IntelliJ

Place the caret on `result` and press `Ctrl+Shift+P` (Expression Type). IntelliJ tells you the static type of the expression. Do the same on the whole ternary expression and it will report `String` — confirming that your variable declaration is correct.

This is genuinely useful when the branches are complicated and you are unsure what type comes out.

---

# 9. Trace the program with real values

```text
─────────────────────────────────────────────────────────────────────
Statement                         time    result            Output
─────────────────────────────────────────────────────────────────────
int time = 13;                     13     —
String result = time<12 ? ... ;    13     "Good Afternoon"
System.out.println(result);        13     "Good Afternoon"  Good Afternoon
─────────────────────────────────────────────────────────────────────
```

## Evaluating the ternary, step by step

```text
Step 1: Look at the condition
        time < 12

Step 2: Substitute the current value
        13 < 12

Step 3: Evaluate the comparison
        false

Step 4: Because it is false, take the branch after the colon
        time < 12 ? "Good Morning" : "Good Afternoon"
                     ─────┬──────    ────────┬───────
                       skipped              taken

Step 5: The whole expression's value is
        "Good Afternoon"

Step 6: Assign it
        result = "Good Afternoon"
```

## Console output

```text
Good Afternoon
```

## What would happen with different inputs

| `time` | `time < 12` | Branch taken | `result` | Printed |
|---|---|---|---|---|
| `0` | `true` | left | `"Good Morning"` | `Good Morning` |
| `9` | `true` | left | `"Good Morning"` | `Good Morning` |
| `11` | `true` | left | `"Good Morning"` | `Good Morning` |
| `12` | `false` | right | `"Good Afternoon"` | `Good Afternoon` |
| `13` | `false` | right | `"Good Afternoon"` | `Good Afternoon` ← **this program** |
| `23` | `false` | right | `"Good Afternoon"` | `Good Afternoon` |
| `25` | `false` | right | `"Good Afternoon"` | `Good Afternoon` (see edge cases) |
| `-3` | `true` | left | `"Good Morning"` | `Good Morning` (see edge cases) |

Notice the boundary: `11` and `12` fall on opposite sides. That is the single most important row pair in the table.

---

# 10. Visualize data where useful

## The type pipeline

```text
   13
    │
   int
    │
    ├──────── compared with 12 using <
    ↓
  false
    │
 boolean
    │
    ├──────── used to select between two Strings
    ↓
"Good Afternoon"
    │
  String
    │
    ├──────── stored
    ↓
 result
    │
    ├──────── passed to println
    ↓
 console
```

Read that top to bottom and you can see the program's entire job: **turn an `int` into a `String` by way of a `boolean`.**

## The decision as a fork in the road

```text
                 time < 12 ?
                      │
          ┌───────────┴───────────┐
        true                    false
          │                       │
   "Good Morning"        "Good Afternoon"
          │                       │
          └───────────┬───────────┘
                      │
                   result
```

Both paths converge on the same variable. That convergence is exactly why a single expression can produce the value — and exactly why the ternary is the right tool.

## The 24-hour number line

```text
 0    3    6    9   11 │ 12   15   18   21   23
 ├────┼────┼────┼────┤ │ ├────┼────┼────┼────┤
 └──── "Good Morning" ─┘ └─── "Good Afternoon" ───┘
                        ↑
                    the boundary
                    time < 12 is false here
                                     ↑
                                   time = 13
```

---

# 11. Alternative ways to write the same logic

### Beginner-friendly version — `if / else`

If the ternary feels dense, this is exactly equivalent and completely acceptable:

```java
int time = 13;
String result;

if (time < 12) {
    result = "Good Morning";
} else {
    result = "Good Afternoon";
}

System.out.println(result);
```

Note the shape: `String result;` **declares without initialising**, and each branch assigns to it. Java allows this as long as it can prove that every path assigns a value before the variable is read. It can prove it here, because `if` has an `else`.

> [!warning]
> Remove the `else` and this breaks:
> ```java
> String result;
> if (time < 12) {
>     result = "Good Morning";
> }
> System.out.println(result);   // error: variable result might not have been initialized
> ```
> Java refuses because if the condition is false, `result` would never receive a value. The compiler is protecting you from reading uninitialised memory.
>
> The ternary version cannot have this problem, because `? :` *always* produces exactly one of its two values. That is a genuine safety advantage of the ternary.

### Even more concise — skip the variable entirely

```java
int time = 13;
System.out.println(time < 12 ? "Good Morning" : "Good Afternoon");
```

Legal, because the ternary is an expression and `println` accepts a `String`. Whether this is *better* depends on context: if the value is used once, inlining is fine. If it is used more than once, or the expression is long, keep the named variable.

### More realistic version — three periods of the day

The real requirement usually has more than two cases. Chained ternaries handle it:

```java
int time = 13;

String result = time < 12  ? "Good Morning"
              : time < 17  ? "Good Afternoon"
              :              "Good Evening";

System.out.println(result);
```

This reads as: "before 12 → morning; otherwise before 17 → afternoon; otherwise → evening." The alignment matters enormously for readability — written on one line it becomes unreadable.

The `if / else if / else` equivalent:

```java
String result;
if (time < 12) {
    result = "Good Morning";
} else if (time < 17) {
    result = "Good Afternoon";
} else {
    result = "Good Evening";
}
```

> [!tip]
> Two branches → ternary is usually cleaner.
> Three branches → either works; align the ternary carefully or use `if / else if`.
> Four or more → prefer `if / else if` or a `switch`. Deeply nested ternaries become write-only code.

### More robust version — validate the input

The original silently accepts nonsense hours. A defensive version:

```java
int time = 13;

if (time < 0 || time > 23) {
    System.out.println("Invalid hour: " + time);
} else {
    String result = time < 12 ? "Good Morning" : "Good Afternoon";
    System.out.println(result);
}
```

Here `if` is the correct tool, because we are choosing between two **actions** (report an error vs. compute and print), not between two values.

### The version to avoid

```java
String result = time < 12 ? "Good Morning" : time < 17 ? "Good Afternoon" : time < 21 ? "Good Evening" : "Good Night";
```

Everything here is correct. It is still bad code, because a human reading it has to count colons to work out the structure. **Correct and readable are two separate goals; you need both.**

---

# 12. Common beginner mistakes

## Mistake 1 — trying to assign from an `if`

**Incorrect code**

```java
String result = if (time < 12) "Good Morning" else "Good Afternoon";
```

**Why it is wrong**
`if` is a **statement**. Statements do not produce values, so there is nothing for `=` to store.

**What Java expects**
Either use the ternary (an expression), or use `if` as a statement that assigns inside its branches.

**Correct code**

```java
String result = time < 12 ? "Good Morning" : "Good Afternoon";
```

**How to recognise it in future**
If you find yourself wanting to put `if` on the right of an `=`, you want a ternary.

---

## Mistake 2 — using `=` instead of `==` (or the right relational operator)

**Incorrect code**

```java
String result = time = 12 ? "Good Morning" : "Good Afternoon";
```

**Why it is wrong**
`time = 12` **assigns** 12 to `time` and produces an `int`. The ternary requires a `boolean` condition, so this fails to compile.

**What Java expects**
A `boolean` before the `?`.

**Correct code**

```java
String result = time < 12 ? "Good Morning" : "Good Afternoon";
```

**How to recognise it**
Compiler message: `incompatible types: int cannot be converted to boolean`. Whenever you see that message near a condition, look for a single `=` that should be `==`, `<`, or `>`.

---

## Mistake 3 — wrong boundary operator (off-by-one)

**Incorrect code**

```java
String result = time <= 12 ? "Good Morning" : "Good Afternoon";
```

**Why it is wrong**
At exactly `12` — noon — this says "Good Morning". Noon is afternoon.

**Correct code**

```java
String result = time < 12 ? "Good Morning" : "Good Afternoon";
```

**How to recognise it**
Always test the boundary explicitly. Ask "what happens at exactly 12?" *before* choosing the operator, not after.

---

## Mistake 4 — mismatched branch types

**Incorrect code**

```java
String result = time < 12 ? "Good Morning" : 42;
```

**Why it is wrong**
One branch produces a `String`, the other an `int`. Java computes a common type for the expression (here `Object`, after boxing), which cannot be stored in a `String` variable.

**Correct code**

```java
String result = time < 12 ? "Good Morning" : "Good Afternoon";
```

**How to recognise it**
Compiler message about incompatible types. **Rule: both branches of a ternary should produce the same type.**

---

## Mistake 5 — forgetting the colon or a branch

**Incorrect code**

```java
String result = time < 12 ? "Good Morning";
```

**Why it is wrong**
The ternary operator is *ternary* — it requires all three parts. Without an `else` value, what would `result` hold when the condition is false?

**Correct code**
Supply both branches. If you genuinely only want to act in one case, use `if` instead — that is what `if` without `else` is for.

---

## Mistake 6 — using single quotes for text

**Incorrect code**

```java
String result = time < 12 ? 'Good Morning' : 'Good Afternoon';
```

**Why it is wrong**
Single quotes denote a `char`, which holds exactly one character. `'Good Morning'` has thirteen.

**Correct code**

```java
String result = time < 12 ? "Good Morning" : "Good Afternoon";
```

**How to recognise it**
Compiler message: `unclosed character literal`. Text always uses double quotes.

---

## Mistake 7 — comparing a `String` with `<`

**Incorrect code**

```java
String time = "13";
String result = time < 12 ? "Good Morning" : "Good Afternoon";   // error
```

**Why it is wrong**
Relational operators like `<` work on numbers, not on `String`s. Text has no numeric ordering under `<`.

**Correct code**
Store the hour as an `int` in the first place, or convert:

```java
int hour = Integer.parseInt(time);
String result = hour < 12 ? "Good Morning" : "Good Afternoon";
```

**How to recognise it**
`bad operand types for binary operator '<'`. It means you tried to do arithmetic-style comparison on something that is not a number.

---

## Mistake 8 — assuming both branches always run

**Incorrect assumption**

```java
String s = (name == null) ? "unknown" : name.toUpperCase();
```

A beginner might fear this crashes when `name` is `null`. It does not — the right branch is never evaluated when the condition is `true`. Understanding this makes the ternary a genuinely useful null-safety tool.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| Expression vs statement | do you know *why* `? :` can be assigned but `if` cannot? |
| Type reasoning | can you explain why `result` must be a `String`? |
| Boundary awareness | do you notice that `12` produces "Good Afternoon"? |
| Readability judgement | do you know when a ternary stops being appropriate? |
| Evaluation semantics | do you know only one branch is evaluated? |

## Likely follow-up questions

> **"What is the difference between the ternary operator and `if / else`?"**

The core answer: `? :` is an **expression** producing a value; `if` is a **statement** performing an action. Therefore the ternary can appear on the right of an assignment, inside a method argument, or inside a `return`, while `if` cannot. Add that `if` can hold many statements per branch, while the ternary holds exactly one value per branch.

> **"What is printed when `time` is 12?"**

`Good Afternoon`, because `12 < 12` is `false`. This checks whether you actually read the operator or just skimmed.

> **"Can you nest ternary operators?"**

Yes, and show the three-period example. Then add the judgement: readable up to about three branches with careful alignment; beyond that, use `if / else if` or `switch`.

> **"Are both branches evaluated?"**

No — only the selected one. Give the null-safety example to prove you understand *why* that matters.

> **"Why must the condition be a `boolean` in Java, when C allows any number?"**

Because Java deliberately removed the `if (x = 5)` class of bug. Requiring `boolean` means an accidental `=` becomes a compile error rather than a silent logic error.

> **"Rewrite this to handle morning, afternoon, evening and night."**

Show the aligned chained ternary *and* the `if / else if` version, and say which you would ship.

> **"What is the time complexity?"**

`O(1)`. One comparison, one selection, one print. No loops.

---

# 14. Complexity

## Time complexity: `O(1)`

The program does a fixed amount of work: one comparison, one branch selection, one assignment, one print. Nothing repeats.

In beginner language:

> "There is no loop here. Whatever value `time` holds — `0`, `13`, or `23` — the computer performs exactly the same three or four operations. The work does not grow with the input at all, so we call this constant time and write it `O(1)`."

## Space complexity: `O(1)`

Two variables exist: one `int` (32 bits) and one `String` reference. The two greeting literals live in the string pool and are created once when the class loads, not per run of the code.

> "We use a fixed number of storage slots no matter what the input is, so the memory usage is constant: `O(1)`."

> [!note] Why complexity analysis is almost trivial here
> Complexity describes **how work grows as input grows**. This program has no collection to iterate, no recursion, and no loop — so there is nothing that can grow. Every program without loops or recursion is `O(1)`.
>
> Being able to say that confidently is worth more in an interview than reciting a memorised answer, because it shows you know *why* it is `O(1)`.

---

# 15. Edge cases

## Exactly 12 — the boundary

```java
int time = 12;
```

`12 < 12` is `false` → `"Good Afternoon"`. **Correct**, because noon is afternoon. This is the case worth checking every single time you write a comparison.

## Exactly 11

`11 < 12` is `true` → `"Good Morning"`. Correct.

## Zero — midnight

```java
int time = 0;
```

`0 < 12` is `true` → `"Good Morning"`. Technically midnight is not usually greeted with "Good Morning", but under the two-branch rule the program is behaving exactly as specified. This is a **requirements** gap, not a bug — the specification only defined two periods.

## Negative numbers

```java
int time = -5;
```

`-5 < 12` is `true` → `"Good Morning"`. The program accepts an impossible hour without complaint, because it never validates its input.

> [!warning] What the current implementation does
> The program performs **no validation whatsoever**. Any `int` at all is accepted, and anything below 12 — including `-1000` — produces `"Good Morning"`.
>
> This is not a crash; it is a *silent wrong answer*, which is worse. If validation matters, add it explicitly:
>
> ```java
> if (time < 0 || time > 23) {
>     System.out.println("Invalid hour: " + time);
> } else {
>     System.out.println(time < 12 ? "Good Morning" : "Good Afternoon");
> }
> ```

## Values above 23

```java
int time = 30;
```

`30 < 12` is `false` → `"Good Afternoon"`. Again accepted silently. Same analysis as negatives.

## Very large values

```java
int time = 2147483647;   // Integer.MAX_VALUE
```

Works fine — `< 12` is `false`, giving `"Good Afternoon"`. There is no arithmetic anywhere in this program, so **overflow is impossible**. That is a genuine strength of comparison-only code.

## Can `result` ever be `null`?

No. The ternary always produces exactly one of the two literals, and neither is `null`. Therefore `System.out.println(result)` can never print `null` and can never throw a `NullPointerException`. This program is **total** — it always terminates and always succeeds.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
"Greet the user based on the time of day."

        ↓

What data do I have?
An hour of the day.
Hours are whole numbers → int

        ↓

What do I need to produce?
A greeting — a piece of text → String

        ↓

The input type and output type are different.
Something has to bridge int → String.
What is the bridge?

        ↓

Is it a calculation or a choice?
Is there a formula turning 13 into "Good Afternoon"?
No. The greetings are fixed text I write in advance.
Therefore: a CHOICE between two known values.

        ↓

What decides the choice?
The rule from the requirement:
"before 12 → morning, otherwise → afternoon"

        ↓

How do I express "before 12" in Java?
The less-than operator → time < 12

        ↓

What does that produce?
A boolean: true or false

        ↓

What is the boundary behaviour?
At exactly 12 it must be afternoon.
12 < 12 is false → afternoon.  ✓  Use < , not <= .

        ↓

Now: am I choosing a VALUE or an ACTION?
A value — which String to store.
(If I were choosing an action, I would use if/else.)

        ↓

Which Java construct chooses between two values?
The conditional operator:  condition ? valueIfTrue : valueIfFalse

        ↓

Assemble it:
time < 12 ? "Good Morning" : "Good Afternoon"

        ↓

What type does that expression produce?
Both branches are String literals → the expression is a String

        ↓

Declare a holder whose type matches:
String result = <that expression>;

        ↓

What do I do with the answer?
Display it → System.out.println(result);

        ↓

Verify by tracing:
time = 13
13 < 12 → false
take the branch after the colon → "Good Afternoon"
result = "Good Afternoon"
prints: Good Afternoon   ✓
```

> [!important] The two decisions that mattered
> Everything else in this program is mechanical. Two choices required actual thought:
>
> 1. **`<` rather than `<=`** — determined by asking what should happen at exactly 12.
> 2. **`? :` rather than `if`** — determined by asking whether we are choosing a value or an action.
>
> Learn to spot which parts of a program are the real decisions. The rest is typing.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int time = 13;` | "Make a whole-number box called `time` and put `13` in it." |
| `time < 12` | "Ask whether the number in `time` is smaller than 12. The answer is `true` or `false`." |
| `? "Good Morning"` | "If that answer was `true`, the value of this whole expression is the text `Good Morning`." |
| `: "Good Afternoon"` | "Otherwise, the value of this whole expression is the text `Good Afternoon`." |
| `String result = ...;` | "Make a box that can hold text, call it `result`, and put whichever of those two texts was chosen into it." |
| `System.out.println(result);` | "Ask the console to display whatever text is in `result`, then move to a new line." |

The entire second line in one sentence:

> **"Create a text variable called `result`, and fill it with `'Good Morning'` if the hour is under 12, or `'Good Afternoon'` if it is not."**

And the whole program:

> **"Set the hour to 13, pick the greeting that matches that hour, and print it."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| expression | code that evaluates to a value — can be assigned |
| statement | code that performs an action — cannot be assigned |
| `boolean` | a type with exactly two values: `true` and `false` |
| relational operator | compares two numbers, produces a `boolean` |
| ternary operator | the only three-operand operator; chooses between two **values** |
| lazy evaluation | only the selected branch of `? :` is evaluated |
| type matching | the variable's type must match the expression's type |
| boundary condition | the exact value where a comparison flips — always test it |

### Important methods

| Method | Owner | Accepts | Returns |
|---|---|---|---|
| `println(String)` | `java.io.PrintStream` | text (overloaded for many types) | `void` |

### Important syntax

| Syntax | Meaning |
|---|---|
| `? :` | conditional operator: `condition ? valueIfTrue : valueIfFalse` |
| `<` | less than — strictly, so `12 < 12` is `false` |
| `<=` | less than or equal to |
| `=` | assignment |
| `==` | equality comparison |
| `"text"` | a `String` literal |
| `'c'` | a `char` literal — exactly one character |
| `;` | ends a statement |
| `( )` | arguments, or forced evaluation order |
| `{ }` | a block, which also defines scope |

### Main interview concept

> **The ternary operator is an *expression*; `if` is a *statement*.** That single distinction explains why `? :` can sit on the right of an `=`, inside a method call, or inside a `return`, while `if` cannot. Use `? :` to choose a **value**; use `if` to choose an **action**.

### Main lesson for code reading

> When you meet a dense one-liner, **split it at its operators and name each piece before judging the whole**:
>
> ```text
> String   result   =   time<12   ?   "Good Morning"   :   "Good Afternoon" ;
>   │        │      │      │      │         │          │          │
>  type    name   store  condition then   value-if-true else  value-if-false
> ```
>
> Once each fragment is labelled, the line stops being intimidating. This decomposition habit works on *every* complicated Java expression you will ever meet.

---

### Related notes

- [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]] — decomposing a dense one-liner into pieces
- [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] — `if` used as a statement, plus the `%` operator
- [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]] — `if / else` choosing between two actions
- [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]] — assignment and value semantics
