---
title: Java Code Reading — SwapNumbers
tags:
  - java
  - interview-programs
  - code-reading
  - variables
  - operators
aliases:
  - SwapNumbers Explained
---

# Java Code Reading — SwapNumbers Explained for a Fresh Java Programmer

> [!note]
> The goal here is **not** to memorise "the swap program". The goal is to be able to *derive* it.
>
> Read every program with this loop running in your head:
>
> **What do I have? → What do I want? → What operation gets me there? → Which Java feature performs that operation? → Where does the result go?**
>
> This program is tiny, but it teaches three things that will follow you through your entire Java career: how a variable actually stores a value, why you need a temporary holder, and why `+` does not always mean "add".

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class SwapNumbers
{
    public static void main(String[] args)
    {
        int a = 5;

        int b = 8;

        System.out.println(" Result before swapping :" + a + b);

        int temp = a;

        a = b;

        b = temp;

        System.out.println(" Result after swapping :" + a + b);
    }
}
```

Strip away the comments and the class wrapper, and the *executable logic* is only these six lines:

```java
int a = 5;
int b = 8;
int temp = a;
a = b;
b = temp;
```

Everything else is printing.

---

# 2. What problem is this program solving?

In plain language:

> I have two boxes. Box `a` holds `5`. Box `b` holds `8`. I want box `a` to hold `8` and box `b` to hold `5`.

Now break the requirement into the three questions you should ask about **every** program:

### What do we know?

- We have two whole numbers.
- Their starting values are `5` and `8`.
- Each value lives in its own named storage location.

### What do we need?

- The same two storage locations, but with the values exchanged.

### What transformation is required?

- Not arithmetic. Nothing is being calculated.
- This is a pure **movement of values** between named locations.

That last observation is the whole insight. Once you realise this is a *movement* problem and not a *calculation* problem, the temporary variable becomes obvious rather than magical.

> [!tip]
> Before writing any code, always classify the problem: is it a **calculation**, a **search**, a **transformation**, or a **movement**? The category tells you which tools you need.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

A **package** is a folder-like grouping for related classes. This line says "this class belongs to the group called `FrequentInterviewPrograms`". It must be the first real line of the file, and it must match the folder the file lives in.

You would know to write it because the file physically sits inside a folder named `FrequentInterviewPrograms`. If the folder and the package disagree, the compiler complains.

## `public class SwapNumbers`

Every piece of Java code lives inside a **class**.

- `public` — visible to any other class anywhere.
- `class` — the keyword that declares a class.
- `SwapNumbers` — the name. Java convention: `PascalCase`, and it **must** match the file name `SwapNumbers.java`.

You would know to write it because Java has no such thing as a free-floating statement. Code needs a home, and the home is a class.

## `public static void main(String[] args)`

This exact signature is the **entry point**. When you press Run, the Java Virtual Machine (JVM) looks for a method with precisely this shape and starts there.

Take it apart:

| Part | Meaning | Why it must be there |
|---|---|---|
| `public` | callable from outside the class | the JVM is "outside", so it must be able to reach in |
| `static` | belongs to the class, not to an object | the JVM has not created any object yet, so the method must be callable without one |
| `void` | returns nothing | the JVM has nowhere to put a return value |
| `main` | the name the JVM searches for | hard-coded into the JVM's launch process |
| `String[] args` | command-line arguments | text typed after the program name arrives here |

> [!warning]
> `main` is not a magic word you can rename. If you write `Main` or `mian`, the code still compiles, but running it fails with `Main method not found`.

## No imports

This program uses only `int`, `String` and `System`. `String` and `System` live in `java.lang`, which Java imports automatically. `int` is a built-in primitive, not a class at all. Therefore **no `import` line is needed**.

That is a useful reading habit: *the absence of imports tells you the program uses nothing beyond the language core.*

## Variables

Three variables appear: `a`, `b`, and `temp`. All are `int`. All are **local variables** — they exist only while `main` is running, and they vanish when it ends.

---

# 4. Line-by-line explanation

## 4.1 — `int a = 5;`

```java
int a = 5;
```

**What is this?**
A variable *declaration* combined with an *initialisation*.

**Why do we need it?**
The problem statement gives us a first number. A number that we intend to read and change later must be stored somewhere with a name. That is exactly what a variable is.

**How would a beginner know to write it?**
Ask: "Do I need to refer to this value more than once?" Yes — we print it, then overwrite it, then print it again. Anything referred to more than once must have a name.

**What type of data is involved?**
A whole number with no fractional part: `5`.

**Why was this data type selected?**
Walk through Java's numeric choices:

| Type | Holds | Suitable here? |
|---|---|---|
| `int` | whole numbers, roughly ±2.1 billion | **yes** — simple, standard, plenty of range |
| `long` | much larger whole numbers | overkill for `5` |
| `double` | numbers with decimals | wrong — `5.0` is not what we mean |
| `byte` / `short` | tiny whole numbers | technically works, but nobody writes this |
| `String` | text | wrong — `"5"` is a character, not a quantity |

`int` is the default choice for whole numbers in Java. Reach for anything else only when you have a reason.

**What does each part of the syntax mean?**

```text
int      a       =       5       ;
 │       │       │       │       │
 │       │       │       │       └── statement terminator
 │       │       │       └────────── the literal value
 │       │       └────────────────── assignment operator: "put into"
 │       └────────────────────────── the variable name
 └────────────────────────────────── the data type
```

**What does Java do when it reaches this line?**
It reserves a slot of memory big enough for an `int` (32 bits), labels that slot `a`, and writes the bit pattern for `5` into it.

**What does it produce or return?**
Nothing is returned. The effect is the *side effect* of memory now holding a value.

**Where does that result go?**
Into the memory slot named `a`, on the method's stack frame.

---

## 4.2 — `int b = 8;`

```java
int b = 8;
```

Identical reasoning to `a`. A **separate** memory slot is created.

> [!important]
> `a` and `b` are two independent boxes. Writing to one never affects the other. That independence is exactly why we can lose a value if we are careless — which is the whole point of this program.

**Why the names `a` and `b`?**
Honestly, in production code these are poor names. In a *swap demonstration* they are acceptable because the values have no real-world meaning — they are deliberately abstract. If these were a person's scores you would write `firstScore` and `secondScore`.

The rule: **a name should describe the meaning of the data.** When the data has no meaning beyond "the first one" and "the second one", short names are honest.

---

## 4.3 — `System.out.println(" Result before swapping :" + a + b);`

```java
System.out.println(" Result before swapping :" + a + b);
```

**What is this?**
A method call that writes a line of text to the console.

**Why do we need it?**
To observe the "before" state. Without printing, the swap would happen invisibly and you would have no evidence it worked.

**How would a beginner know to write it?**
Whenever you want to see what your program is doing, printing is the first and simplest tool. Reach for it constantly while learning.

**What does each part mean?**

| Part | What it is |
|---|---|
| `System` | a class in `java.lang` giving access to system facilities |
| `.out` | a field inside `System`, of type `PrintStream`, representing the console |
| `.println(...)` | a method on `PrintStream` that prints, then moves to a new line |
| `" Result before swapping :"` | a `String` literal (note the leading space) |
| `+` | here, **string concatenation** — not addition |
| `a`, `b` | the values to append |

**What does Java do when it reaches this line?**

This is the single most misunderstood line in the program, so let's be precise. Java evaluates `+` **left to right**:

```text
Step 1:  " Result before swapping :" + a
         String            +   int
         → because the left side is a String, Java converts a to "5"
         → result: " Result before swapping :5"     (a String)

Step 2:  " Result before swapping :5" + b
         String            +   int
         → converts b to "8"
         → result: " Result before swapping :58"    (a String)

Step 3:  println(" Result before swapping :58")
```

**What does it produce?**
The console output:

```text
 Result before swapping :58
```

> [!warning] The classic beginner shock
> You expected `5` and `8` to appear as two separate numbers. Instead you got `58`, which looks like fifty-eight.
>
> **Rule to memorise:** the `+` operator means *addition* only when **both** sides are numbers. If **either** side is a `String`, `+` means *concatenation*, and the number is converted to its text form.
>
> Fix it by adding separators:
> ```java
> System.out.println(" Result before swapping : a = " + a + ", b = " + b);
> ```
> which prints `Result before swapping : a = 5, b = 8`.

**Where does that result go?**
Nowhere in your program. `println` returns `void`. The text goes to the standard output stream, which the terminal displays.

---

## 4.4 — `int temp = a;`  ← the heart of the program

```java
int temp = a;
```

**What is this?**
A third variable that takes a **copy** of whatever `a` currently holds.

**Why do we need it?**
Here is the reasoning you must be able to reproduce in an interview.

We want:

```text
a should become b's value
b should become a's value
```

Try the naive approach:

```java
a = b;   // a is now 8. But a's old value, 5, has been destroyed.
b = a;   // b = 8 as well. Both are 8. The 5 is gone forever.
```

Walk the memory:

```text
start:      a = 5        b = 8
a = b;      a = 8        b = 8      ← the 5 has been overwritten
b = a;      a = 8        b = 8      ← nothing left to copy back
```

The problem is now stated precisely:

> **Assignment overwrites. Overwriting destroys. So before I destroy `a`, I must save `a` somewhere else.**

That sentence *is* the temporary variable. You do not memorise `temp` — you deduce it from "I am about to destroy a value I still need."

**What type of data is involved and why?**
`temp` must hold whatever `a` holds. `a` is an `int`, so `temp` is an `int`. **The holder's type must match the type of what it holds.** This is a rule you will apply for the rest of your Java life.

**How would a beginner know the name `temp`?**
`temp` is short for *temporary*. The name announces its purpose: "I exist only briefly, to park a value." Equally good names would be `hold`, `saved`, or `original`. A bad name would be `x`, because it says nothing.

**What does Java do when it reaches this line?**
It creates a new memory slot named `temp` and **copies the bit pattern** from `a` into it.

> [!important] Copy, not link
> After `int temp = a;` the two variables are completely independent. Later changing `a` does **not** change `temp`.
>
> For primitive types like `int`, assignment always copies the *value*. This is called **pass/assign by value**, and it is why the swap works at all.

**Where does the result go?**
Into the slot named `temp`. State is now:

```text
a = 5      b = 8      temp = 5
```

---

## 4.5 — `a = b;`

```java
a = b;
```

**What is this?**
An assignment to an **existing** variable. Notice there is no `int` in front — `a` already exists, so we are not declaring, only overwriting.

**Why do we need it?**
Half of the requirement is "`a` should hold `b`'s value". This line performs exactly that half.

**How would a beginner know to write it?**
Read the requirement literally: "a becomes b." In Java, "becomes" is `=`.

**What does each part mean?**

```text
a   =   b   ;
│   │   │
│   │   └── the source: read b's current value
│   └────── copy the value on the right into the variable on the left
└────────── the destination
```

> [!warning] `=` is not "equals"
> In mathematics, `a = b` is a *statement of fact*. In Java, `a = b` is a *command*: "take b's value and put it into a."
>
> The comparison operator — the one that asks "are these the same?" — is `==`, with two signs. Confusing them is the most common beginner bug in all of programming.

**What does Java do when it reaches this line?**
Reads the value currently in `b` (`8`), copies it into the slot named `a`. The old contents of `a` (`5`) are destroyed — but we saved a copy in `temp`, so nothing is lost.

**Where does the result go?**

```text
a = 8      b = 8      temp = 5
```

Notice that right now `a` and `b` are *both* `8`. The array of values is momentarily "wrong". That is fine — we are mid-operation. The next line completes it.

---

## 4.6 — `b = temp;`

```java
b = temp;
```

**What is this?**
The second half of the exchange.

**Why do we need it?**
The requirement's other half is "`b` should hold `a`'s original value". `a`'s original value no longer lives in `a` — it lives in `temp`. So we copy from `temp`.

**How would a beginner know to write it?**
Ask: "Where is the value I still owe to `b`?" Answer: "In `temp`." Therefore: `b = temp;`.

**What does Java do?**
Copies `5` from `temp` into `b`.

**Where does the result go?**

```text
a = 8      b = 5      temp = 5
```

The swap is complete. `temp` still holds `5`, but nobody cares — it has done its job and will disappear when `main` ends.

---

## 4.7 — `System.out.println(" Result after swapping :" + a + b);`

```java
System.out.println(" Result after swapping :" + a + b);
```

Same mechanics as line 4.3. Left-to-right concatenation produces:

```text
 Result after swapping :85
```

Compare the two output lines:

```text
 Result before swapping :58
 Result after swapping :85
```

The digits `5` and `8` have traded places. That is your visual proof the swap worked.

---

# 5. How to think like the programmer

Here is the reasoning chain, reconstructed from requirement to code:

```text
Requirement
"Exchange the values held by two variables"
        ↓
Identify the input
Two whole numbers → two int variables
        ↓
Identify the required output
The same two variables, contents exchanged
        ↓
Classify the operation
Not arithmetic. This is value movement.
        ↓
Attempt the direct approach
a = b;  then  b = a;
        ↓
Simulate it in your head
a = 8, b = 8 → the original 5 is destroyed
        ↓
Diagnose the failure
"Assignment overwrites. I destroyed a value I still needed."
        ↓
Derive the fix
"Save the value before destroying it."
        ↓
Choose the tool
A third variable — a temporary holder
        ↓
Choose its type
Same type as what it holds → int
        ↓
Choose its name
temp — it announces "short-lived storage"
        ↓
Order the three statements
save → overwrite → restore
        ↓
Final code
int temp = a;
a = b;
b = temp;
```

> [!tip] The transferable lesson
> "Save it before you destroy it" is not a swap-specific trick. It is the general pattern behind undo stacks, database transactions, backups, and rotating three or more variables. Learn the *reason*, and the code writes itself.

---

# 6. Deep explanation of important Java concepts used

## `int`

`int` is one of Java's eight **primitive** types. Primitives are not objects; they hold a raw value directly rather than a reference to something elsewhere.

- Size: 32 bits.
- Range: `-2,147,483,648` to `2,147,483,647`.
- Default value (as a field): `0`.
- Literal form: just digits — `5`, `-17`, `1000000`.

Why `int` and not `Integer`? `Integer` is the object wrapper around `int`. You need it only when a value must be stored in a collection like `ArrayList` or be allowed to be `null`. For plain arithmetic in local variables, `int` is faster and simpler.

## Variable

A variable is three things bundled together:

1. A **type** — what kind of value is allowed.
2. A **name** — how you refer to the storage.
3. A **value** — what is currently stored.

```text
   ┌──────── type: int
   │   ┌──── name: a
   │   │  ┌─ value: 5
 ┌─┴─┬─┴─┬─┴─┐
 │int│ a │ 5 │
 └───┴───┴───┘
```

Java is **statically typed**: the type is fixed at declaration and can never change. `a` will be an `int` for its entire life.

## Declaration vs assignment

These are two different acts that often appear together:

```java
int temp;        // declaration only  — the box exists, but is empty
temp = a;        // assignment only   — a value goes into the existing box
int temp = a;    // both at once      — the usual shorthand
```

Reading tip: **if a line starts with a type name, it is creating something new. If it starts with a variable name, it is modifying something that already exists.**

That single rule makes lines 4.4, 4.5 and 4.6 instantly readable:

```java
int temp = a;   // starts with a type → creating temp
a = b;          // starts with a name → modifying existing a
b = temp;       // starts with a name → modifying existing b
```

## Assignment operator `=`

`=` is a **binary operator** with right-to-left evaluation:

1. Evaluate the entire right-hand side first.
2. Copy the resulting value into the left-hand side.

The left-hand side must be something that *can be stored into* — a variable, an array slot, or a field. You cannot write `5 = a;` because `5` is not a storage location.

## Value semantics for primitives

This deserves its own section because it explains *why* the swap works.

```java
int x = 10;
int y = x;    // y gets a COPY of 10
x = 99;       // changing x does not touch y
// y is still 10
```

Every primitive assignment copies the bits. There is no shared link. Compare that with objects:

```java
int[] p = {1, 2, 3};
int[] q = p;      // q and p now refer to the SAME array
q[0] = 99;
// p[0] is now 99 too!
```

> [!important]
> Primitives copy the **value**. Object variables copy the **reference** (the address). Understanding this one distinction resolves an enormous fraction of confusing Java behaviour.

## `System.out.println()`

Read it right to left:

- `System` — a class.
- `out` — a `static` field inside `System`, holding a `PrintStream` object connected to the console.
- `println` — an *instance* method on that `PrintStream` object.

So `System.out.println(x)` means: "reach into the class `System`, grab the object stored in its field `out`, and ask that object to run its `println` method with `x`."

It is **overloaded** — there are many versions of `println` accepting `int`, `double`, `char`, `String`, `Object`, and so on. Java picks the right one based on the argument's type. Here the argument is already a `String` (the concatenation happened first), so the `String` version runs.

## String concatenation with `+`

The `+` operator is **overloaded** in Java — one symbol, two meanings:

| Left operand | Right operand | `+` means | Example | Result |
|---|---|---|---|---|
| number | number | addition | `5 + 8` | `13` |
| String | anything | concatenation | `"x" + 8` | `"x8"` |
| anything | String | concatenation | `8 + "x"` | `"8x"` |

Evaluation is **strictly left to right**, which is why the two examples below differ:

```java
System.out.println("Sum: " + 5 + 8);      // "Sum: 58"   ← concatenate, concatenate
System.out.println("Sum: " + (5 + 8));    // "Sum: 13"   ← parentheses force addition first
```

---

# 7. Why this syntax?

## `;` — the statement terminator

Java uses the semicolon to mark the end of a statement, not the newline. This is why you *may* write:

```java
int a = 5; int b = 8;
```

on one line. It compiles fine — but do not, because it is harder to read.

## `{ }` — a block

Curly braces group statements into a unit. `main`'s braces enclose everything the method does; the class's braces enclose everything the class contains. Every opening brace must be matched by a closing one.

## `( )` — three different jobs

| Context | Job | Example here |
|---|---|---|
| after a method name | wraps the arguments | `println(...)` |
| in a declaration | wraps the parameters | `main(String[] args)` |
| around an expression | forces evaluation order | `(5 + 8)` |

## `.` — the member access operator

Read `.` as the English word **"'s"**:

```text
System.out          →  System's out
System.out.println  →  System's out's println
```

It walks you from a container to a thing inside it.

## `=` vs `==`

| Operator | Name | Question or command? | Result |
|---|---|---|---|
| `=` | assignment | command: "put this there" | stores a value |
| `==` | equality comparison | question: "are these the same?" | `true` or `false` |

This program uses only `=`. There is no comparison anywhere — a good reminder that swapping requires no `if` at all.

## `" "` vs `' '`

- `" Result before swapping :"` — **double** quotes → a `String` (zero or more characters).
- `'x'` — **single** quotes → a `char` (exactly one character).

This program uses only double quotes, because the labels are multi-character text.

## `public`, `static`, `void`

- `public` — an **access modifier**: who may use this.
- `static` — belongs to the class itself, so no object is needed to call it.
- `void` — the **return type**, here meaning "returns nothing".

---

# 8. Method discovery

This program calls exactly one method, but the discovery process is worth practising even on an easy case.

## `println`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.io.PrintStream` |
| **Why can we call it here?** | because `System.out` *is* a `PrintStream` object, and you may call any public method on an object you hold |
| **What does it do?** | writes its argument's text form to the stream, then writes a line separator |
| **What arguments does it accept?** | it is overloaded: `String`, `int`, `long`, `char`, `boolean`, `double`, `float`, `char[]`, `Object`, or nothing at all |
| **What does it return?** | `void` — nothing |
| **How does the return type affect the next line?** | it does not, and it *cannot*. Because it returns `void`, you can never write `String s = System.out.println("hi");` — there is no value to store |

## How you would discover it in IntelliJ

Type this and pause:

```java
System.out.
```

IntelliJ pops up a list. You would see `print`, `println`, `printf`, `flush`, `write`, `append`, and more. Each entry shows its parameters and return type. Press `Ctrl+Q` (or `F1` on macOS) on a highlighted entry and the Javadoc appears inline.

Even simpler: hover over `println` in existing code and the signature appears.

## How to check the documentation

Search the web for `java PrintStream println` and open the official Oracle Javadoc. Every method page tells you the same three facts you always need:

```text
1. What arguments does it accept?
2. What does it return?
3. What are the special cases / exceptions?
```

> [!tip] The mindset to build
> You are never expected to memorise the Java API. You are expected to be able to say:
>
> *"I am holding a `PrintStream`. I want to display text. Let me look at what a `PrintStream` can do."*
>
> Identify the object → identify the operation → find the method. That loop scales to every library you will ever use.

---

# 9. Trace the program with real values

Follow the memory state after every single statement.

```text
─────────────────────────────────────────────────────────────
Statement                     a       b       temp    Output
─────────────────────────────────────────────────────────────
int a = 5;                    5       —       —
int b = 8;                    5       8       —
println(...before... + a + b) 5       8       —       " Result before swapping :58"
int temp = a;                 5       8       5
a = b;                        8       8       5
b = temp;                     8       5       5
println(...after... + a + b)  8       5       5       " Result after swapping :85"
─────────────────────────────────────────────────────────────
```

## The dangerous middle step, magnified

Look carefully at the row `a = b;`:

```text
a = 8      b = 8      temp = 5
           ↑↑↑
     both hold 8 at this instant
```

If `temp` did not exist, the `5` would be gone from the universe at this moment and the program could not finish. `temp` is the only reason the value survives.

## The exact console output

```text
 Result before swapping :58
 Result after swapping :85
```

> [!note]
> Each line begins with a space, because the string literals themselves start with a space: `" Result before swapping :"`. Nothing mysterious — the space is inside the quotes.

---

# 10. Visualize data where useful

## The three-step dance

```text
BEFORE
  a ┌───┐      b ┌───┐      temp ┌───┐
    │ 5 │        │ 8 │           │ ? │
    └───┘        └───┘           └───┘

STEP 1:  int temp = a;         (copy a into temp)
  a ┌───┐      b ┌───┐      temp ┌───┐
    │ 5 │───────────────────────▶│ 5 │
    └───┘        │ 8 │           └───┘
                 └───┘

STEP 2:  a = b;                (copy b into a)
  a ┌───┐      b ┌───┐      temp ┌───┐
    │ 8 │◀───────│ 8 │           │ 5 │
    └───┘        └───┘           └───┘
      ↑ the old 5 is destroyed here — but temp has it

STEP 3:  b = temp;             (copy temp into b)
  a ┌───┐      b ┌───┐      temp ┌───┐
    │ 8 │        │ 5 │◀──────────│ 5 │
    └───┘        └───┘           └───┘

AFTER
  a = 8    b = 5    ✓ swapped
```

## Why the order cannot be shuffled

```text
CORRECT ORDER              WRONG ORDER
─────────────────          ─────────────────
temp = a   (save)          a = b      (destroy!)
a = b      (overwrite)     temp = a   (saves 8, too late)
b = temp   (restore)       b = temp   (b = 8, unchanged)

result: a=8, b=5  ✓        result: a=8, b=8  ✗
```

The rule: **save must come before overwrite.**

## How `+` builds the output string

```text
" Result before swapping :"      ← String
            +
            a  (int 5)           ← converted to "5"
            ↓
" Result before swapping :5"     ← String
            +
            b  (int 8)           ← converted to "8"
            ↓
" Result before swapping :58"    ← String, finally passed to println
```

---

# 11. Alternative ways to write the same logic

### Beginner-friendly version — clearer output

The original prints `58`, which reads as one number. Make the output honest:

```java
int a = 5;
int b = 8;

System.out.println("Before swapping: a = " + a + ", b = " + b);

int temp = a;
a = b;
b = temp;

System.out.println("After swapping:  a = " + a + ", b = " + b);
```

Output:

```text
Before swapping: a = 5, b = 8
After swapping:  a = 8, b = 5
```

Same logic, but the reader can now actually see two distinct numbers. **This is the version to write in an interview.**

### Concise version — swap without a temporary variable (arithmetic)

```java
int a = 5;
int b = 8;

a = a + b;   // a = 13   (both values now encoded in a)
b = a - b;   // b = 13 - 8 = 5   (recovers the original a)
a = a - b;   // a = 13 - 5 = 8   (recovers the original b)
```

Trace it:

```text
start:      a = 5    b = 8
a = a + b;  a = 13   b = 8
b = a - b;  a = 13   b = 5     ← 13 - 8 = 5, the original a
a = a - b;  a = 8    b = 5     ← 13 - 5 = 8, the original b
```

It works, and it is a classic interview follow-up. But:

> [!warning] Why the "clever" version is worse in real code
> If `a + b` exceeds `Integer.MAX_VALUE` (about 2.1 billion), the sum **silently overflows** and wraps around to a negative number. The subtractions then produce garbage.
>
> ```java
> int a = 2_000_000_000;
> int b = 2_000_000_000;
> a = a + b;   // overflow! a becomes -294967296
> ```
>
> The `temp` version cannot overflow, because it never performs arithmetic. **Never trade correctness for cleverness.**

### Another concise version — XOR swap

```java
a = a ^ b;
b = a ^ b;
a = a ^ b;
```

This uses the bitwise exclusive-or operator and does not overflow. It is a party trick. It is also **broken if `a` and `b` are the same variable** (`swap(x, x)` zeroes it out), it is harder to read, and on modern hardware it is not faster. Mention it in an interview if asked; do not ship it.

### More robust version — a reusable method (and the trap it reveals)

You might reasonably try to extract the logic:

```java
public static void swap(int x, int y) {
    int temp = x;
    x = y;
    y = temp;
}

// caller:
int a = 5, b = 8;
swap(a, b);
System.out.println(a + " " + b);   // prints "5 8" — NOT swapped!
```

> [!warning] This does not work, and understanding why is genuinely important
> Java passes primitives **by value**. `swap` receives *copies* of `a` and `b` in its own parameters `x` and `y`. It swaps the copies perfectly — and then the method ends, the copies are discarded, and the caller's `a` and `b` were never touched.
>
> To swap two values via a method in Java you must return them, or wrap them in an object or array:
>
> ```java
> public static void swap(int[] pair) {
>     int temp = pair[0];
>     pair[0] = pair[1];
>     pair[1] = temp;
> }
> // here it DOES work, because the array reference points at one shared array
> ```

This is a very common interview follow-up. Being able to explain it cleanly separates you from candidates who only memorised the three lines.

---

# 12. Common beginner mistakes

## Mistake 1 — swapping without a temporary variable

**Incorrect code**

```java
a = b;
b = a;
```

**Why it is wrong**
The first line destroys `a`'s original value. The second line then copies the *new* `a` (which equals `b`) back into `b`, achieving nothing.

**What Java expects**
Java executes statements strictly in order and assignment always overwrites. It will not "remember" the old value for you.

**Correct code**

```java
int temp = a;
a = b;
b = temp;
```

**How to recognise it in future**
Whenever you are about to overwrite a variable, ask: *"Will I need the old value later?"* If yes, save it first.

---

## Mistake 2 — declaring `temp` a second time

**Incorrect code**

```java
int temp = a;
a = b;
int b = temp;    // error: variable b is already defined
```

**Why it is wrong**
`b` already exists. Writing `int b` again tries to *declare a new variable with the same name* in the same scope, which Java forbids.

**What Java expects**
Declare once, assign many times.

**Correct code**

```java
b = temp;
```

**How to recognise it**
Compiler message: `variable b is already defined in method main(String[])`. Remember the reading rule from section 6: a line starting with a type name creates something new.

---

## Mistake 3 — expecting `+ a + b` to print two numbers

**Incorrect assumption**

```java
System.out.println("Values: " + a + b);   // hoping for "Values: 5 8"
```

**Why it is wrong**
Because the left operand is a `String`, every subsequent `+` concatenates. You get `Values: 58`.

**Correct code**

```java
System.out.println("Values: " + a + " " + b);
```

**How to recognise it**
Whenever numbers appear glued together in your output, look for a missing separator in a concatenation.

---

## Mistake 4 — expecting `+` to add when a String is present

**Incorrect code**

```java
System.out.println("Sum: " + a + b);   // hoping for "Sum: 13"
```

**Correct code**

```java
System.out.println("Sum: " + (a + b));   // "Sum: 13"
```

**How to recognise it**
If you want arithmetic inside a print statement, wrap it in parentheses. Always.

---

## Mistake 5 — using `==` instead of `=`

**Incorrect code**

```java
int temp == a;      // syntax error
a == b;             // compiles as a useless expression, changes nothing
```

**Why it is wrong**
`==` asks a question and produces `true`/`false`. It never stores anything.

**Correct code**

```java
int temp = a;
a = b;
```

**How to recognise it**
Say the line out loud. If you mean "**put** this there", use one `=`. If you mean "**is** this the same as that", use two.

---

## Mistake 6 — giving `temp` the wrong type

**Incorrect code**

```java
String temp = a;    // error: incompatible types: int cannot be converted to String
```

**Why it is wrong**
`a` is an `int`. A `String` variable cannot hold an `int`.

**Correct code**

```java
int temp = a;
```

**How to recognise it**
The compiler message `incompatible types: X cannot be converted to Y` always means the same thing: the holder's type does not match what you are putting in it.

---

## Mistake 7 — declaring `temp` inside a block and using it outside

**Incorrect code**

```java
if (true) {
    int temp = a;
}
a = b;
b = temp;        // error: cannot find symbol
```

**Why it is wrong**
A variable declared inside `{ }` exists only inside those braces. This is called **scope**.

**Correct code**
Declare `temp` in the same block where you use it.

---

# 13. Interview perspective

## What the interviewer is actually testing

This question is almost never about the answer — it is about the *explanation*. They want to see:

| Skill | How this program tests it |
|---|---|
| Understanding of variables as storage | can you explain that assignment overwrites? |
| Understanding of value semantics | do you know `int` assignment copies? |
| Sequential reasoning | can you trace three statements and predict the state? |
| Awareness of operator overloading | do you know why `+ a + b` prints `58`? |
| Judgement | do you prefer the readable version over the clever one? |

## Likely follow-up questions

> **"Can you swap without using a third variable?"**

Yes — show the arithmetic version (`a = a + b; b = a - b; a = a - b;`), then immediately volunteer the overflow risk. Volunteering the weakness is what makes the answer strong.

> **"What is the time and space complexity?"**

`O(1)` time and `O(1)` space. Three assignments, one extra variable, regardless of the values.

> **"Why does `System.out.println(" ... " + a + b)` print 58?"**

Because `+` is left-associative and the leftmost operand is a `String`, so both `+` operations concatenate rather than add.

> **"Write a `swap(int a, int b)` method that swaps the caller's variables."**

The honest answer: **you cannot** in Java, because primitives are passed by value. Then show the array or wrapper-object workaround. This question exists specifically to see whether you understand pass-by-value.

> **"Does the XOR trick work? Any problems with it?"**

It works for distinct storage locations, but it fails when both references point to the same variable, and it is less readable. Not worth it.

> **"What if `a` and `b` are `String`s instead of `int`s?"**

The exact same three lines work, with `String temp`. The difference is that you would be copying *references*, not values — but the swap still succeeds, because you are rearranging which reference lives in which variable.

---

# 14. Complexity

## Time complexity: `O(1)`

The program performs the same fixed amount of work no matter what values `a` and `b` hold. Three assignments happen; that is it. There is no loop, no recursion, nothing that grows.

In beginner language:

> "Whether the numbers are `5` and `8` or `5,000,000` and `8,000,000`, the computer does exactly three copy operations. The work does not grow with the input, so we call it constant time, written `O(1)`."

## Space complexity: `O(1)`

We create exactly one extra variable, `temp`, holding 32 bits. That is a fixed cost that never grows.

> "We needed one extra box regardless of the input, so the extra memory is constant, written `O(1)`."

> [!note]
> Do not be impressed by the arithmetic swap "saving" the `temp` variable. It saves 4 bytes — an amount so small it is meaningless — while introducing an overflow bug. Both are `O(1)` space. The trade is a bad one.

---

# 15. Edge cases

## Both values are equal

```java
int a = 7, b = 7;
```

Trace: `temp = 7`, `a = 7`, `b = 7`. The swap runs and produces the correct (identical) result. **No problem.**

Contrast with XOR swap: if `a` and `b` were the *same variable*, XOR would zero it out. The `temp` version is safe in every case.

## Negative numbers

```java
int a = -5, b = 8;
```

Works perfectly. The `temp` version does no arithmetic, so sign is irrelevant. Output would be `-58` before and `8-5` after — which is another good reason to add separators to the print statements!

## Very large numbers

```java
int a = 2147483647, b = 1;   // Integer.MAX_VALUE
```

The `temp` version works flawlessly. The arithmetic version (`a = a + b`) **overflows immediately** and produces wrong answers. This edge case alone justifies preferring `temp`.

## Zero

```java
int a = 0, b = 8;
```

Works. Zero is an ordinary `int` value with no special behaviour under assignment.

## What this program cannot do

There is no input, so there are no `null`s, no empty values, and no exceptions possible. The program is *total* — it always terminates and always succeeds. That is worth noticing: **programs with no input and no loops cannot fail at runtime.**

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Exchange the values stored in two variables.

        ↓

What data do I have?
Two whole numbers → int a, int b

        ↓

What do I need at the end?
a holds b's original value
b holds a's original value

        ↓

What kind of operation is this?
Not a calculation. A movement of values.

        ↓

What Java construct moves a value?
The assignment operator, =

        ↓

First attempt:
a = b;
b = a;

        ↓

Simulate it before trusting it:
a = 5, b = 8
after a = b →  a = 8, b = 8
after b = a →  a = 8, b = 8   ✗

        ↓

What went wrong?
Assignment overwrites. I destroyed the 5
before I had finished using it.

        ↓

What is the general fix?
"If I am about to destroy a value I still need,
 I must copy it somewhere safe first."

        ↓

What do I copy it into?
A new variable.

        ↓

What type must that variable be?
The same type as the value it holds → int

        ↓

What should I call it?
It exists only briefly, for safekeeping → temp

        ↓

In what order must the three statements run?
1. save     int temp = a;
2. overwrite a = b;
3. restore   b = temp;

        ↓

Verify by simulation:
a=5 b=8 temp=?
temp = a  →  a=5 b=8 temp=5
a = b     →  a=8 b=8 temp=5
b = temp  →  a=8 b=5 temp=5   ✓

        ↓

How do I prove it to a human?
Print before and print after.

        ↓

Final code:
int a = 5;
int b = 8;
System.out.println("before: a = " + a + ", b = " + b);
int temp = a;
a = b;
b = temp;
System.out.println("after:  a = " + a + ", b = " + b);
```

> [!important]
> Notice how much of this reasoning is *simulation*. Writing the naive version and then mentally executing it is what exposed the bug. Get into the habit of tracing your own code on paper before running it — it is the single fastest way to improve.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `int a = 5;` | "Make a box that can hold a whole number, call it `a`, and put `5` in it." |
| `int b = 8;` | "Make a second whole-number box called `b`, and put `8` in it." |
| `System.out.println(" Result before swapping :" + a + b);` | "Take the text `' Result before swapping :'`, stick the text form of `a` on the end, then stick the text form of `b` on the end of that, and print the whole thing on its own line." |
| `int temp = a;` | "Make a third whole-number box called `temp`, and copy into it whatever `a` currently holds — because I am about to overwrite `a` and I do not want to lose that value." |
| `a = b;` | "Copy whatever `b` holds into `a`, discarding what `a` held before." |
| `b = temp;` | "Copy whatever `temp` holds — which is `a`'s original value — into `b`." |
| `System.out.println(" Result after swapping :" + a + b);` | "Print the label, then `a`'s new value, then `b`'s new value, all on one line." |

And the whole program in one sentence:

> **"Put 5 in a and 8 in b, show them, park a's value in temp, move b's value into a, move temp's value into b, then show them again — they have traded places."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| primitive type | holds a raw value directly, not a reference |
| `int` | 32-bit whole number, the default numeric type |
| declaration | `int x;` — creates a new named storage slot |
| assignment | `x = y;` — copies a value into an existing slot |
| value semantics | primitive assignment copies the value; there is no shared link |
| scope | a variable lives only inside the `{ }` where it was declared |
| operator overloading | one symbol (`+`) with different meanings by operand type |
| pass by value | a method receives copies, so it cannot swap the caller's primitives |

### Important methods

| Method | Owner | Accepts | Returns |
|---|---|---|---|
| `println(String)` | `java.io.PrintStream` | text (or any overloaded type) | `void` |

### Important syntax

| Syntax | Meaning |
|---|---|
| `;` | ends a statement |
| `{ }` | groups statements into a block, and defines scope |
| `( )` | arguments, parameters, or forced evaluation order |
| `.` | "'s" — reach inside a class or object |
| `=` | assignment — put the right value into the left slot |
| `==` | comparison — asks "are these the same?" |
| `+` | addition between numbers; concatenation when a `String` is involved |
| `"text"` | a `String` literal |
| `'c'` | a `char` literal (single character) |
| `public` | accessible from anywhere |
| `static` | belongs to the class, callable without an object |
| `void` | returns nothing |

### Main interview concept

> **Assignment overwrites, so a value you still need must be saved before it is destroyed.** The temporary variable is not a trick to memorise — it is the direct consequence of that fact.

### Main lesson for code reading

> When a line starts with a **type name**, something new is being created.
> When a line starts with a **variable name**, something existing is being changed.
>
> Apply that rule to any unfamiliar Java code and half the confusion disappears immediately.

---

### Related notes

- [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]] — `String` methods and method chaining
- [[Java_TernaryOperatorTime_Code_Reading_Explanation_Obsidian|TernaryOperatorTime]] — assignment from a conditional expression
- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — the accumulator pattern, another use of "read then overwrite"
- [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]] — building a value up across a loop
