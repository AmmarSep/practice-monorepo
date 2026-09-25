---
title: Java Code Reading — PalindromeOrNot
tags:
  - java
  - interview-programs
  - code-reading
  - loops
  - math
  - input
aliases:
  - PalindromeOrNot Explained
  - Palindrome Number in Java
---

# Java Code Reading — PalindromeOrNot Explained for a Fresh Java Programmer

> [!note]
> This is the first program in the folder that **reads input from the user**, and the first that uses a **`while` loop** rather than a `for` loop. Both choices are deliberate, and understanding *why* they were made is more valuable than the palindrome logic itself.
>
> It also teaches a genuinely beautiful technique: **reversing a number using only arithmetic** — no strings, no arrays, just `%` and `/`. Once you see how `% 10` and `/ 10` work together, a whole family of digit problems opens up.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Do I know how many steps in advance? → What must I preserve before I destroy it? → What am I comparing at the end?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

import java.util.Scanner;

public class PalindromeOrNot
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number:");

        int num = sc.nextInt();

        int org_num = num;

        int rev = 0;

        while(num != 0)
        {
            rev= rev*10 + num%10;
            num = num/10;

        }
        if(rev==org_num)
        {
            System.out.println("The given number is Palindrome number");
        }
        else
        {
            System.out.println(org_num+" is not a Palindrome number");
        }
    }
}
```

The executable logic:

```java
int num = sc.nextInt();
int org_num = num;
int rev = 0;

while (num != 0) {
    rev = rev * 10 + num % 10;
    num = num / 10;
}

if (rev == org_num) { ... } else { ... }
```

Three variables, one loop, one comparison. The whole program hinges on the two lines inside the `while`.

---

# 2. What problem is this program solving?

In plain language:

> Ask the user for a number. Tell them whether it reads the same forwards and backwards.

```text
121   →  reversed is 121  →  palindrome      ✓
123   →  reversed is 321  →  not a palindrome ✗
```

### What do we know?

- One whole number, typed by the user.
- The definition: a palindrome reads identically in both directions.

### What do we need?

- A yes/no answer, printed as a message.

### What transformations are required?

The definition gives us the algorithm almost directly:

```text
"reads the same backwards"
        ↓
means: the number equals its own reverse
        ↓
so: reverse it, then compare
```

Two sub-problems, in order:

1. **Reverse the number.**
2. **Compare the reverse with the original.**

> [!important] The problem that makes step 2 interesting
> To compare the reverse with the original, you need the original — but **reversing destroys it**.
>
> The reversal works by repeatedly chopping digits off `num` until nothing is left. By the time `rev` holds the answer, `num` is `0`.
>
> ```text
> start:  num = 121,  rev = 0
> end:    num = 0,    rev = 121     ← the original is gone
> ```
>
> So before starting, we must **save a copy**: `int org_num = num;`
>
> This is the same principle as the `temp` in [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]] and the `number3` in [[Java_FabionacciSeries_Code_Reading_Explanation_Obsidian|FabionacciSeries]]: **save a value before the operation that destroys it.**

### How do you reverse a number without converting it to text?

This is the clever part, and it is worth deriving rather than memorising.

To reverse `121` you need to peel digits off one end and stack them onto the other. Two operators do exactly that:

```text
num % 10   →  the LAST digit         121 % 10 = 1
num / 10   →  everything else        121 / 10 = 12
```

`%` gives what is left over after dividing by ten — which is precisely the units digit. `/` on two `int`s **truncates**, discarding the remainder — which removes that digit.

```text
121  ──%10──▶  1     (the digit we grabbed)
     ──/10──▶  12    (what remains)

 12  ──%10──▶  2
     ──/10──▶  1

  1  ──%10──▶  1
     ──/10──▶  0     (nothing left — stop)
```

And to build the reversed number, each new digit goes on the end:

```text
rev = rev * 10 + newDigit
        ↑
   shift everything left one place, making room for the new digit
```

```text
rev = 0
rev = 0*10 + 1 = 1
rev = 1*10 + 2 = 12
rev = 12*10 + 1 = 121
```

> [!tip] The `%10` / `/10` pair
> Learn these two together — they are the standard tool for any digit problem:
>
> | Expression | Meaning |
> |---|---|
> | `n % 10` | the **last** digit |
> | `n / 10` | `n` with the last digit **removed** |
> | `n % 100` | the last **two** digits |
> | `n / 100` | `n` with the last two removed |
> | `rev * 10 + d` | **append** digit `d` to the right of `rev` |
>
> With these you can reverse a number, sum its digits, count its digits, check for an Armstrong number, or extract any digit you like. It is the numeric equivalent of `charAt` in [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]].

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder. First statement in the file.

## `import java.util.Scanner;`

**The first `import` in this folder.** Everything up to now used only `int`, `String`, `System` and arrays — all available automatically.

`Scanner` lives in the package `java.util`, which is **not** imported by default. Without this line, `Scanner` would be an unknown name and the code would not compile.

> [!important] When do you need an `import`?
> | Package | Imported automatically? | Examples |
> |---|---|---|
> | `java.lang` | **yes**, always | `String`, `System`, `Math`, `Integer`, `Object` |
> | anything else | **no** | `Scanner`, `ArrayList`, `Arrays`, `BigInteger` |
>
> **How you would discover this:** type `Scanner` in IntelliJ and it will be underlined in red. Press `Alt+Enter` and choose "Import class" — the IDE adds the line for you and tells you which package it came from.
>
> That is genuinely how professionals work. You do not memorise package names; you let the IDE resolve them and then read what it wrote.

## `public class PalindromeOrNot`

- `public` — usable from anywhere.
- `class` — declares a class.
- `PalindromeOrNot` — `PascalCase`, matching `PalindromeOrNot.java`.

Note the brace style: the opening `{` sits on its own line rather than at the end of the declaration. Java accepts both; the more common convention puts it on the same line. Purely cosmetic.

## `public static void main(String[] args)`

The JVM entry point.

| Part | Meaning | Why required |
|---|---|---|
| `public` | reachable from outside | the JVM is outside |
| `static` | belongs to the class | no object exists at startup |
| `void` | returns nothing | the JVM has nowhere to put a result |
| `main` | the name the JVM looks for | fixed by the launcher |
| `String[] args` | command-line arguments | text typed after the program name |

## Variables

| Variable | Type | Role |
|---|---|---|
| `sc` | `Scanner` | the input reader — an **object**, not a primitive |
| `num` | `int` | the working copy, **destroyed** by the loop |
| `org_num` | `int` | the saved original, used for the final comparison |
| `rev` | `int` | the accumulator — the reversed number being built |

> [!note] `sc` is the first object variable in this folder
> `int`, `char` and `boolean` are **primitives** — they hold a value directly. `Scanner` is a **class**, so `sc` holds a *reference* to an object created on the heap.
>
> That is why it needs `new`, and why you can call methods on it (`sc.nextInt()`). You cannot call a method on an `int`.

---

# 4. Line-by-line explanation

## 4.1 — `Scanner sc = new Scanner(System.in);`

```java
Scanner sc = new Scanner(System.in);
```

**What is this?**
Creating a `Scanner` object connected to the keyboard.

**Why do we need it?**
Every previous program in this folder hard-coded its input. This one asks the user, which means it needs a way to *read* what they type. `Scanner` is Java's beginner-friendly tool for that.

**How would a beginner know to write it?**
Ask: "Where does my data come from?" If the answer is "the user types it", search "java read input from keyboard". Every result points to `Scanner`.

**What does each part mean?**

```text
Scanner   sc   =   new   Scanner( System.in )   ;
   │       │   │    │        │         │
   │       │   │    │        │         └── WHERE to read from: standard input
   │       │   │    │        └──────────── the constructor
   │       │   │    └───────────────────── allocate a new object on the heap
   │       │   └────────────────────────── store the reference
   │       └────────────────────────────── the reference variable
   └────────────────────────────────────── the declared type
```

**What is `System.in`?**

The mirror image of `System.out`:

| | `System.out` | `System.in` |
|---|---|---|
| direction | output — program → screen | input — keyboard → program |
| type | `PrintStream` | `InputStream` |
| used for | `println` | feeding a `Scanner` |

`System.in` delivers raw **bytes**. On its own that is awkward to work with — you would have to assemble characters and parse numbers yourself. `Scanner` wraps it and provides convenient methods like `nextInt()`.

> [!tip] Why `Scanner` takes `System.in` as an argument
> Because a `Scanner` can read from many sources, not just the keyboard:
>
> ```java
> new Scanner(System.in)              // the keyboard
> new Scanner(new File("data.txt"))   // a file
> new Scanner("42 17 8")              // a String
> ```
>
> The constructor argument says **where** to read from. This is a very common design in Java: a general-purpose tool that you point at a specific source. Recognising the pattern helps you read unfamiliar constructors.

**What is `new`?**
It allocates a new object on the heap and returns a reference to it. Objects need `new`; primitives do not.

**Why the name `sc`?**
Conventional shorthand for "scanner". Short, but so universally used that it reads clearly. `scanner` or `input` would also be fine.

---

## 4.2 — `System.out.println("Enter the number:");`

```java
System.out.println("Enter the number:");
```

**What is this?**
A prompt telling the user what to type.

**Why do we need it?**
Without it, the program would sit silently waiting for input and the user would have no idea what was expected — or even that anything was expected.

**A small improvement worth noting**

```java
System.out.print("Enter the number: ");     // print, not println
```

Using `print` keeps the cursor on the same line, so the user types next to the prompt rather than underneath it:

```text
with println:            with print:
Enter the number:        Enter the number: 121
121
```

Cosmetic, but it is the kind of detail that separates a program that *works* from one that feels finished.

---

## 4.3 — `int num = sc.nextInt();`

```java
int num = sc.nextInt();
```

**What is this?**
Reading one whole number from the keyboard and storing it.

**Why do we need it?**
This is the program's input. Everything else operates on this value.

**What is `nextInt()`?**

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.util.Scanner` |
| **Why can we call it?** | `sc` is a `Scanner` object |
| **What does it accept?** | nothing |
| **What does it return?** | an **`int`** |
| **What does it do?** | waits for the user to type, reads the next token, converts it to an `int` |
| **What if the input is not a number?** | throws `InputMismatchException` |

**Why does the return type matter?**

```java
int num = sc.nextInt();       // ✓ nextInt() returns int, num is int
String num = sc.nextInt();    // ✗ incompatible types
```

**The variable's type must match the method's return type.** This is the same habit that made `String[] words = sentence.split(" ")` correct in [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]]: check what comes out before deciding what holds it.

**The `Scanner` method family**

| Method | Reads | Returns |
|---|---|---|
| `nextInt()` | a whole number | `int` |
| `nextDouble()` | a decimal number | `double` |
| `nextLong()` | a large whole number | `long` |
| `next()` | one word (up to whitespace) | `String` |
| `nextLine()` | the rest of the line | `String` |
| `nextBoolean()` | `true` or `false` | `boolean` |

You would discover these by typing `sc.` in IntelliJ and reading the list — the naming is systematic enough that you can guess correctly once you have seen two of them.

> [!warning] The classic `nextInt()` / `nextLine()` trap
> ```java
> int n = sc.nextInt();          // reads the number, leaves the NEWLINE behind
> String name = sc.nextLine();   // immediately reads that leftover newline → ""
> ```
>
> `nextInt()` consumes the digits but **not** the Enter key that followed them. The next `nextLine()` finds that leftover newline and returns an empty string without waiting.
>
> The fix is an extra `sc.nextLine()` to swallow it:
> ```java
> int n = sc.nextInt();
> sc.nextLine();                 // discard the rest of the line
> String name = sc.nextLine();   // now works as expected
> ```
>
> Not a problem in this program — it reads only one value — but it is one of the most common beginner frustrations in Java, and worth knowing before you meet it.

**Why the name `num`?**
Acceptable. `number` or `input` would read slightly better; `num` is a very common abbreviation.

---

## 4.4 — `int org_num = num;`

```java
int org_num = num;
```

**What is this?**
A saved copy of the original number.

**Why do we need it?**

Because the loop **destroys** `num`. Watch:

```text
num = 121
  → 12
  → 1
  → 0      ← the original value is gone
```

By the time `rev` holds the reversed number, `num` is `0`. Comparing `rev == num` would compare `121 == 0` — always false, so **every** number would be reported as not a palindrome.

> [!important] Save before you destroy — for the third time
> | Program | What is saved | Why |
> |---|---|---|
> | [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian\|SwapNumbers]] | `temp = a` | `a` is about to be overwritten |
> | [[Java_FabionacciSeries_Code_Reading_Explanation_Obsidian\|FabionacciSeries]] | `number3 = n1 + n2` | both are about to shift |
> | [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian\|SecondLargestArray]] | `secondLargest = largest` | the leader is about to change |
> | **PalindromeOrNot** | `org_num = num` | `num` is about to be consumed |
>
> Four programs, one principle. When you see a variable copied for no immediately obvious reason, **look for the operation that will destroy the original**.

**Is this a copy or a link?**

A **copy**. `int` is a primitive, so assignment copies the value. Later changes to `num` do not affect `org_num`.

```text
num     ┌─────┐        org_num ┌─────┐
        │ 121 │───copy────────▶│ 121 │
        └─────┘                └─────┘
           │                      │
     destroyed by              untouched
       the loop
```

Had these been objects rather than primitives, `org_num = num` would copy the *reference* and both names would point at the same object — a completely different behaviour. See [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] for the array version of this distinction.

**Why the name `org_num`?**

It means "original number", which is exactly right in meaning. The **style** is off, though: Java convention is `camelCase`, not `snake_case`.

```java
int org_num = num;        // snake_case — a C or Python habit
int originalNum = num;    // camelCase — Java convention
int original = num;       // clearer still
```

> [!note] Java naming conventions
> | Kind | Style | Example |
> |---|---|---|
> | variable, method | `camelCase` | `originalNum`, `nextInt` |
> | class, interface | `PascalCase` | `PalindromeOrNot`, `Scanner` |
> | constant | `UPPER_SNAKE_CASE` | `MAX_VALUE`, `PI` |
> | package | `lowercase` | `java.util` |
>
> The compiler does not care. Every Java reader does.

---

## 4.5 — `int rev = 0;`

```java
int rev = 0;
```

**What is this?**
The accumulator — the reversed number being built up.

**Why do we need it?**
The reversal is assembled one digit at a time. `rev` holds the partial result between steps.

**Why the starting value `0`?**

Apply the usual accumulator question: *what value can I start with that will not distort the result?*

```text
rev = rev*10 + digit
    = 0*10 + 1
    = 1              ✓ the first digit lands cleanly
```

Starting at `0` means the first step produces exactly the first digit. Starting at anything else would prepend a spurious value:

```text
rev = 5:   5*10 + 1 = 51     ✗ a 5 appears from nowhere
```

`0` is the identity for addition, and it also happens to be the identity for the "shift left and append" operation — which is why it works here.

Compare with [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]], where the identity was `""` and the program mistakenly used `" "`. Same reasoning, different type.

**Why the name `rev`?**
Short for "reversed". Clear enough. `reversed` would be marginally better.

---

## 4.6 — `while(num != 0)`

```java
while(num != 0)
```

**What is this?**
A loop that keeps going until `num` has been reduced to zero.

**Why a `while` loop and not a `for` loop?**

This is a genuine design decision worth understanding.

> [!important] `for` vs `while` — how to choose
> | | `for` | `while` |
> |---|---|---|
> | use when | you know **how many** times in advance | you repeat **until a condition changes** |
> | shape | counter, bound, step | just a condition |
> | examples | "for each array element", "ten times" | "until the number runs out", "until the user quits" |
>
> Here, **we do not know how many digits the number has** before we start. `121` needs three passes; `7` needs one; `1234567` needs seven. There is no natural counter.
>
> What we *do* know is when to **stop**: when nothing is left. That is a condition, not a count — so `while` is the natural fit.
>
> You *could* force a `for` loop:
> ```java
> for (; num != 0; ) { ... }        // legal, but why?
> ```
> It compiles, but the empty sections announce that a `for` was the wrong choice. **Use the construct whose shape matches the problem.**

**Why `num != 0` and not `num > 0`?**

For positive numbers they behave identically. For negatives they do not — see the edge cases in section 15. `!= 0` is the more direct expression of "keep going while there is something left".

**What does the condition produce?**
A `boolean`. The loop body runs while it is `true`, and the check happens **before** each pass.

> [!warning] The condition is checked before the first pass
> If `num` is already `0` when the loop is reached, the body **never runs at all**. That is why entering `0` produces `rev = 0` — see section 15.
>
> This is the defining property of `while`: it may execute zero times. (`do-while` is the variant that always runs at least once.)

**What guarantees the loop terminates?**

`num = num / 10` shrinks `num` on every pass. Integer division always reduces a positive number toward zero, and it reaches `0` in a finite number of steps — specifically, once per digit.

> [!warning] Every `while` loop needs a reason to end
> ```java
> while (num != 0) {
>     rev = rev * 10 + num % 10;
>     // ✗ forgot  num = num / 10;
> }
> ```
> Without the shrinking line, `num` never changes, the condition stays `true` forever, and the program hangs.
>
> **Whenever you write a `while`, immediately ask: "what inside the body makes the condition eventually become false?"** If you cannot point to a specific line, you have an infinite loop.
>
> A `for` loop makes this harder to get wrong, because the update section is part of the header. A `while` loop puts the responsibility on you.

---

## 4.7 — `rev = rev*10 + num%10;`

```java
rev = rev*10 + num%10;
```

**What is this?**
The accumulation step: shift `rev` one place left and append the last digit of `num`.

**Why do we need it?**
This is how the reversed number is built, one digit per pass.

**Breaking it down**

```text
rev   =   rev * 10   +   num % 10   ;
 │         │              │
 │         │              └── the LAST digit of num
 │         └───────────────── shift rev left one decimal place
 └─────────────────────────── store the combined result
```

**Why `rev * 10`?**

Multiplying by ten in decimal shifts every digit one place to the left, leaving a `0` in the units position — exactly the space needed for the incoming digit.

```text
rev = 12
12 * 10 = 120        ← a gap appears in the units place
120 + 1 = 121        ← the new digit fills it
```

**Why `num % 10`?**

The remainder after dividing by ten is the units digit.

```text
121 % 10 = 1     (121 = 12×10 + 1)
 12 % 10 = 2     (12 = 1×10 + 2)
  1 % 10 = 1     (1 = 0×10 + 1)
```

**Operator precedence — why no parentheses are needed**

```text
rev * 10 + num % 10
```

`*` and `%` both bind tighter than `+`, so Java reads this as:

```text
(rev * 10) + (num % 10)        ✓ exactly what we want
```

The precedence order for the operators in this program:

```text
highest   ( )
          *  /  %
          +  -
          <  >  <=  >=
          ==  !=
lowest    =
```

> [!tip]
> The rule worth remembering: **multiplication-family binds tighter than addition-family, which binds tighter than comparison, which binds tighter than assignment.**
>
> When in doubt, add parentheses. They cost nothing and remove all ambiguity for the reader.

**What does Java do when it reaches this line?**
Evaluates the entire right side using the *current* values, then stores the result into `rev`. The old `rev` is read before the new one is written — no circularity.

---

## 4.8 — `num = num/10;`

```java
num = num/10;
```

**What is this?**
Removing the digit we just consumed.

**Why do we need it?**
Two reasons, both essential:

1. **Progress** — without it the same digit would be read forever.
2. **Termination** — this is the line that eventually makes `num` reach `0`.

**What is integer division?**

When both operands are `int`, `/` performs **integer division**: it truncates toward zero and discards any fractional part.

```text
121 / 10  →  12      not 12.1
 12 / 10  →   1      not 1.2
  1 / 10  →   0      not 0.1
```

> [!warning] Truncation, not rounding
> ```java
> 7 / 2    →  3       not 3.5, and NOT 4
> 9 / 10   →  0       not 0.9, and not 1
> ```
>
> Integer division **always rounds toward zero**, regardless of the fraction. `9/10` is `0`, not `1`.
>
> That truncation is exactly what makes this algorithm work: dropping the fraction *is* dropping the digit. But it is also the source of a classic bug — computing an average as `sum / count` on two `int`s silently truncates. To get a decimal result, at least one operand must be floating-point:
> ```java
> double avg = (double) sum / count;
> ```

**Why does this guarantee termination?**

Each division reduces the number of digits by one. A number with `d` digits reaches `0` after exactly `d` divisions. The loop cannot run forever.

---

## 4.9 — `if(rev==org_num)`

```java
if(rev==org_num)
```

**What is this?**
The comparison that decides the answer.

**Why compare with `org_num` and not `num`?**
Because `num` is `0` by now. Only `org_num` still holds the original value.

**Why `==` and not `.equals()`?**

> [!important] `==` is correct here, and it is worth knowing why
> `rev` and `org_num` are **`int` primitives**, not objects. For primitives, `==` compares the actual values — which is exactly what we want.
>
> ```java
> int a = 121, b = 121;
> a == b                  // true — compares values  ✓
>
> String s1 = new String("121"), s2 = new String("121");
> s1 == s2                // false — compares references  ✗
> s1.equals(s2)           // true — compares contents  ✓
> ```
>
> **The rule:** primitives use `==`; objects use `.equals()`.
>
> This is one of the most common sources of confusion in Java, and one of the most common interview questions. Here, `==` is not a shortcut — it is the correct operator, because there are no objects involved.
>
> The same reasoning applies to `char` comparisons in [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]].

**Why `==` and not `=`?**

`=` assigns; `==` asks. Writing `if (rev = org_num)` would try to assign and produce an `int`, which Java rejects because `if` requires a `boolean`. **The compiler catches this mistake for you** — unlike in C, where it silently compiles.

---

## 4.10 — the `if` / `else` blocks

```java
if(rev==org_num)
{
    System.out.println("The given number is Palindrome number");
}
else
{
    System.out.println(org_num+" is not a Palindrome number");
}
```

**Why `if` / `else` and not a ternary?**

We are choosing between two **actions** (which message to print), not between two **values** to store. `if` is the right tool.

Compare with [[Java_TernaryOperatorTime_Code_Reading_Explanation_Obsidian|TernaryOperatorTime]], where the choice was between two `String` *values* to assign — the ternary's natural home.

You *could* write it as a ternary here:

```java
System.out.println(rev == org_num
        ? "The given number is Palindrome number"
        : org_num + " is not a Palindrome number");
```

That works, because the two messages are values being passed to one call. Both forms are defensible; the `if`/`else` is clearer when the branches might grow.

**An inconsistency worth noticing**

```java
"The given number is Palindrome number"        // does not show the number
org_num+" is not a Palindrome number"          // does show it
```

The two messages have different shapes. Including the number in both would be more consistent:

```java
System.out.println(org_num + " is a Palindrome number");
System.out.println(org_num + " is not a Palindrome number");
```

Small, but **consistent output is easier to read and easier to test**.

---

# 5. How to think like the programmer

```text
Requirement
"Check whether a number reads the same backwards"
        ↓
What does that mean precisely?
The number equals its own reverse
        ↓
So I need two things:
(a) the reverse
(b) the original, to compare against
        ↓
Where does the number come from?
The user types it → I need to read input
        ↓
How do I read input in Java?
Scanner, wrapping System.in
Scanner is in java.util → needs an import
        ↓
Which method reads a whole number?
sc.nextInt() → returns int
So the variable holding it must be int
        ↓
How do I reverse a number arithmetically?
I need the last digit → % 10
I need to remove it   → / 10
I need to append it   → rev*10 + digit
        ↓
How many times do I repeat?
Once per digit — but I do not know how many
digits there are in advance
        ↓
So: not a for loop. A while loop.
Condition: keep going while something is left
→ while (num != 0)
        ↓
What makes the loop end?
num = num/10 shrinks it toward 0 every pass
        ↓
PROBLEM: that destroys num,
and I need it for the comparison
        ↓
Solution: save a copy BEFORE the loop
int org_num = num;
        ↓
What is the accumulator's seed?
rev*10 + digit, starting from 0,
gives exactly the first digit → rev = 0
        ↓
After the loop, compare
rev == org_num
Both are int primitives → == is correct
        ↓
Choose between two ACTIONS → if/else
        ↓
Trace: 121 → rev 1, 12, 121 → equal → palindrome  ✓
```

> [!important] The three decisions that mattered
> 1. **`while` rather than `for`** — because the number of digits is unknown until you finish.
> 2. **Saving `org_num` before the loop** — because the loop consumes its input.
> 3. **`%10` / `/10`** — the arithmetic pair that peels digits.
>
> Everything else follows mechanically. Learn to spot which parts of a program are the real decisions.

---

# 6. Deep explanation of important Java concepts used

## `Scanner`

A class in `java.util` that reads and parses input from a source.

```java
Scanner sc = new Scanner(System.in);
```

- It is an **object**, so it needs `new`.
- The constructor argument says where to read from.
- Its `nextXxx()` methods each read one token and convert it to a specific type.

> [!note] Should you close a `Scanner`?
> Good practice says yes — `sc.close()` releases the resource. This program does not close it, which is harmless for a short program that ends immediately (the JVM releases everything on exit).
>
> But be careful: **closing a `Scanner` wrapped around `System.in` also closes `System.in`**, so no further input can be read anywhere in the program. For a single-read program that is fine; in longer programs, create one `Scanner` and keep it open.

## `System.in`

An `InputStream` delivering raw bytes from the keyboard. The counterpart to `System.out`. Almost always wrapped in something more convenient — `Scanner` for beginners, `BufferedReader` for performance.

## The `while` loop

```java
while (condition) {
    body
}
```

```text
1. check condition ─── false ──▶ exit (possibly without ever running the body)
   ↓ true
2. run body
   ↓
   back to step 1
```

Three requirements for a correct `while` loop:

1. Something **before** the loop sets up the condition's variables.
2. The condition can eventually become `false`.
3. Something **inside** the body moves toward that.

Miss the third and you have an infinite loop.

| | `for` | `while` |
|---|---|---|
| best for | known repetition count | unknown, condition-driven |
| counter | built into the header | you manage it yourself |
| risk | off-by-one | infinite loop |

## Integer division `/` and remainder `%`

Two halves of one operation:

```text
       121 ÷ 10
      ┌────┴────┐
   quotient  remainder
      12          1
   (121/10)   (121%10)
```

| Expression | Value | Meaning |
|---|---|---|
| `121 / 10` | `12` | drop the last digit |
| `121 % 10` | `1` | keep only the last digit |
| `121 / 100` | `1` | drop the last two |
| `121 % 100` | `21` | keep the last two |

## Building a number digit by digit

```java
rev = rev * 10 + digit;
```

The decimal equivalent of appending a character to a string:

| | Numbers | Strings |
|---|---|---|
| append | `rev = rev * 10 + d` | `s = s + c` |
| identity | `0` | `""` |
| extract last | `n % 10` | `s.charAt(s.length()-1)` |
| remove last | `n / 10` | `s.substring(0, s.length()-1)` |

Recognising that these are the same operation in different types is exactly the kind of pattern-spotting that makes unfamiliar code readable.

## `==` for primitives vs `.equals()` for objects

```java
int a = 5, b = 5;
a == b                    // true — compares values

Integer x = 1000, y = 1000;
x == y                    // FALSE — compares references!
x.equals(y)               // true — compares values
```

> [!warning] The `Integer` caching trap
> ```java
> Integer a = 100, b = 100;
> a == b       // true  — Java caches small Integers (-128 to 127)
>
> Integer c = 1000, d = 1000;
> c == d       // false — outside the cache range
> ```
>
> Same code, different answers depending on the *value*. This is why `==` on wrapper objects is so dangerous — it appears to work in testing and fails in production.
>
> **`int` primitives are immune to this.** Because `rev` and `org_num` are plain `int`s, `==` is unambiguously correct here.

## Saving before destroying

The recurring principle of this folder:

```java
int org_num = num;        // save
while (num != 0) { ... }  // destroy
if (rev == org_num)       // use the saved copy
```

## Scope

```java
int num = sc.nextInt();      // scope: rest of main
int org_num = num;           // scope: rest of main
int rev = 0;                 // scope: rest of main

while (num != 0) {
    // no new declarations here — all three are already in scope
}

if (rev == org_num) { }      // both still visible  ✓
```

Unlike the `for` loops in other programs, this `while` declares nothing of its own. All the state lives in `main`'s scope because all of it is needed after the loop.

---

# 7. Why this syntax?

## `%` vs `/`

```text
n % 10   →  the last digit        (what is left over)
n / 10   →  everything else       (how many tens fit)
```

Swap them and the algorithm collapses:

```java
rev = rev * 10 + num / 10;    // ✗ appends the wrong part
num = num % 10;               // ✗ never shrinks past one digit → infinite loop
```

## `!=` vs `>`

```java
while (num != 0)      // "while anything is left"
while (num > 0)       // "while positive"
```

Identical for positive input. For negatives, `> 0` exits immediately while `!= 0` keeps peeling — see section 15.

## `==` vs `=`

`==` asks; `=` stores. Java requires a `boolean` in `if`, so `if (rev = org_num)` fails to compile. The compiler protects you.

## `==` vs `.equals()`

`==` for primitives (correct here); `.equals()` for objects.

## `while` vs `for`

`for` when the count is known; `while` when the stopping condition is discovered as you go.

## `{ }` on its own line

```java
public class PalindromeOrNot
{
```
versus
```java
public class PalindromeOrNot {
```

Both compile identically. The second is the more common Java convention. Purely a style preference — but **be consistent within a file**.

## `org_num` vs `orgNum`

Java uses `camelCase` for variables. `org_num` is a `snake_case` habit from other languages. The compiler accepts it; readers notice it.

## `+` in the else message

```java
org_num + " is not a Palindrome number"
```

The right operand is a `String`, so `+` concatenates and `org_num` is converted to text.

---

# 8. Method discovery

## `nextInt`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.util.Scanner` |
| **Why can we call it?** | `sc` is a `Scanner` object |
| **Accepts** | nothing |
| **Returns** | `int` |
| **Why does `int num` match?** | because the return type is `int` |
| **Exceptions** | `InputMismatchException` if the input is not a valid `int`; `NoSuchElementException` if there is no input at all |

## `println`

| Question | Answer |
|---|---|
| **Owner** | `java.io.PrintStream` |
| **Why callable?** | `System.out` is a `PrintStream` |
| **Which overload?** | `println(String)` in both branches |
| **Returns** | `void` |

## How you would discover `Scanner` from scratch

```text
"The user needs to type a number."
        ↓
Search: "java read user input"
        ↓
Find: Scanner
        ↓
IntelliJ underlines Scanner in red — it is not in java.lang
        ↓
Alt+Enter → "Import class" → adds  import java.util.Scanner;
        ↓
Type  new Scanner(  and IntelliJ shows the constructor options:
    Scanner(InputStream)
    Scanner(File)
    Scanner(String)
        ↓
System.in is an InputStream → use the first
        ↓
Type  sc.  and read the method list:
    nextInt()  nextDouble()  nextLine()  next()  hasNextInt()  ...
        ↓
I want a whole number → nextInt()
        ↓
Check its return type: int
        ↓
So the variable must be int:  int num = sc.nextInt();
```

> [!important] Notice the last two steps
> **Check the return type, then declare a matching variable.** That order is the habit — not "write the variable and hope".
>
> Every method-discovery walkthrough in this folder ends the same way, because it is the single most transferable skill in reading Java: *what do I have → what operation do I need → what does that method return → what holds it?*

## A genuinely useful discovery: `hasNextInt()`

While reading `sc.` you would notice a family of `hasNextXxx()` methods:

```java
if (sc.hasNextInt()) {
    int num = sc.nextInt();
} else {
    System.out.println("That is not a valid number.");
}
```

| Question | Answer |
|---|---|
| **Accepts** | nothing |
| **Returns** | `boolean` |
| **What it does** | reports whether the next token *could* be read as an `int` — **without consuming it** |

This is how you validate input instead of crashing. The current program has no such check — typing `abc` throws `InputMismatchException` and the program dies. See section 15.

> [!tip]
> Browsing the neighbours of the method you came for is one of the cheapest ways to learn an API. You came looking for `nextInt`; you leave knowing `hasNextInt` exists — and that pairing (`nextX` / `hasNextX`) recurs throughout Java's I/O classes.

## Is there a built-in palindrome check?

No. There is no `isPalindrome()` anywhere in the standard library, for the same reason there is no `factorial()` or `fibonacci()`: it is a few lines of logic, and the definition varies (case sensitivity? spaces? negatives?).

For the **String** version you could compose one from existing methods:

```java
String s = String.valueOf(num);
boolean isPal = s.equals(new StringBuilder(s).reverse().toString());
```

Compare that with the arithmetic version — see section 11 for why the arithmetic one is usually preferred in interviews.

---

# 9. Trace the program with real values

## Case A — `121` (a palindrome)

```text
Input:  num = 121
Save:   org_num = 121
Seed:   rev = 0
```

```text
────────────────────────────────────────────────────────────────────────
Pass  num(in)  num!=0?  num%10  rev*10  rev = rev*10 + digit  num = num/10
────────────────────────────────────────────────────────────────────────
 —      121      —        —       —      0                      —
 1      121     true      1       0      0 + 1   = 1            12
 2       12     true      2      10     10 + 2   = 12            1
 3        1     true      1     120    120 + 1   = 121           0
 —        0    FALSE      —       —      121                     —   ← exit
────────────────────────────────────────────────────────────────────────
```

Final comparison:

```text
rev == org_num
121 == 121   →  true
```

Output:

```text
Enter the number:
121
The given number is Palindrome number
```

## Case B — `123` (not a palindrome)

```text
Input:  num = 123,  org_num = 123,  rev = 0
```

```text
────────────────────────────────────────────────────────────────────
Pass  num(in)  num%10   rev = rev*10 + digit   num = num/10
────────────────────────────────────────────────────────────────────
 1      123      3       0 + 3   = 3             12
 2       12      2      30 + 2   = 32             1
 3        1      1     320 + 1   = 321            0
 —        0      —       321                      —      ← exit
────────────────────────────────────────────────────────────────────
```

```text
rev == org_num
321 == 123   →  false
```

Output:

```text
Enter the number:
123
123 is not a Palindrome number
```

## Watching both variables move in opposite directions

```text
      num shrinks              rev grows
      ──────────              ──────────
        121                       0
         12                       1
          1                      12
          0                     121
          ↑                       ↑
       consumed              constructed
```

**The digits flow out of `num` and into `rev`, one per pass, in reverse order.** That single picture is the whole algorithm.

## Why `org_num` is essential

```text
after the loop:
    num     = 0        ← destroyed
    rev     = 121      ← the answer
    org_num = 121      ← the only surviving copy of the input

if (rev == num)      →  121 == 0    →  false, ALWAYS   ✗
if (rev == org_num)  →  121 == 121  →  true             ✓
```

Without the saved copy, **every** number — including genuine palindromes — would be reported as not a palindrome.

---

# 10. Visualize data where useful

## Digits moving from one number to the other

```text
   num                              rev
  ┌─────┐                         ┌─────┐
  │ 121 │                         │   0 │
  └─────┘                         └─────┘
     │ %10 → 1 ───────────────────────▶│ rev = 0*10 + 1 = 1
     │ /10                             │
     ▼                                 ▼
  ┌─────┐                         ┌─────┐
  │  12 │                         │   1 │
  └─────┘                         └─────┘
     │ %10 → 2 ───────────────────────▶│ rev = 1*10 + 2 = 12
     │ /10                             │
     ▼                                 ▼
  ┌─────┐                         ┌─────┐
  │   1 │                         │  12 │
  └─────┘                         └─────┘
     │ %10 → 1 ───────────────────────▶│ rev = 12*10 + 1 = 121
     │ /10                             │
     ▼                                 ▼
  ┌─────┐                         ┌─────┐
  │   0 │  ← loop stops           │ 121 │  ← the answer
  └─────┘                         └─────┘
```

## How `rev * 10` makes room

```text
rev = 12                    digit to append = 1

    1  2                    the existing digits
    ↓  ↓
  1 2  _    ← ×10 shifts left, opening the units place
       ↑
  1 2  1    ← +1 fills it

  rev = 121
```

## The `%` and `/` split

```text
              121
               │
      ┌────────┴────────┐
    /10                %10
      │                 │
     12                 1
  "the rest"      "the last digit"
      │                 │
   keep going      append to rev
```

## Program flow

```text
        read num
            │
            ▼
      save org_num
            │
            ▼
        rev = 0
            │
            ▼
      ┌─▶ num != 0 ? ─── false ──┐
      │     │ true               │
      │     ▼                    │
      │  rev = rev*10 + num%10   │
      │     │                    │
      │     ▼                    │
      │  num = num/10            │
      └─────┘                    │
                                 ▼
                        rev == org_num ?
                         ┌──────┴──────┐
                       true          false
                         │              │
                  "is Palindrome"  "is not Palindrome"
```

---

# 11. Alternative ways to write the same logic

### Cleaner version — better names and validated input

```java
import java.util.Scanner;

public class PalindromeOrNot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number: ");

        if (!sc.hasNextInt()) {
            System.out.println("That is not a valid whole number.");
            return;
        }

        int number = sc.nextInt();
        int original = number;
        int reversed = 0;

        while (number != 0) {
            reversed = reversed * 10 + number % 10;
            number = number / 10;
        }

        if (reversed == original) {
            System.out.println(original + " is a Palindrome number");
        } else {
            System.out.println(original + " is not a Palindrome number");
        }
    }
}
```

Four improvements, none of which change the algorithm:

- `hasNextInt()` guards against non-numeric input instead of crashing.
- `camelCase` names following Java convention.
- `print` rather than `println` for the prompt, so the user types on the same line.
- Both messages include the number, so the output is consistent.

### Reusable version — extract a method

```java
public static boolean isPalindrome(int number) {
    if (number < 0) {
        return false;                  // negatives are never palindromes
    }
    int original = number;
    int reversed = 0;
    while (number != 0) {
        reversed = reversed * 10 + number % 10;
        number = number / 10;
    }
    return reversed == original;
}
```

Why this is better:

- **Reusable** — call it on any number.
- **Testable** — `assert isPalindrome(121)` can be automated.
- **Named** — the method name documents the intent.
- **Note the parameter trick:** `number` is a *copy* of the caller's argument (primitives are passed by value), so the method can destroy it freely without affecting the caller. The `original` variable is still needed inside, but the caller's variable is safe automatically.

Returning a `boolean` rather than printing separates computation from presentation — a habit worth building early.

### String-based version

```java
String s = String.valueOf(number);
String reversed = new StringBuilder(s).reverse().toString();
boolean isPalindrome = s.equals(reversed);      // .equals, NOT ==
```

Shorter and easier to read. Note `.equals()` — these are `String` **objects**, so `==` would compare references and give wrong answers.

> [!tip] Why interviewers usually want the arithmetic version
> The `String` version is perfectly good production code. But the arithmetic version demonstrates that you understand `%` and `/`, loop termination, and value preservation — which is what the question is actually testing.
>
> **Show the arithmetic version first, then mention the `String` one** as evidence you know the library too.

### Two-pointer version — no reversal at all

```java
String s = String.valueOf(number);
int left = 0;
int right = s.length() - 1;

while (left < right) {
    if (s.charAt(left) != s.charAt(right)) {
        return false;                  // mismatch — stop immediately
    }
    left++;
    right--;
}
return true;
```

Two pointers move toward each other from the ends. It **exits early** on the first mismatch, so `987654321` fails after one comparison instead of nine.

The same technique reverses a string in place in [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]].

### Half-reversal — avoiding overflow entirely

```java
public static boolean isPalindrome(int number) {
    if (number < 0 || (number % 10 == 0 && number != 0)) {
        return false;      // negatives, and numbers ending in 0 (except 0 itself)
    }

    int reversedHalf = 0;
    while (number > reversedHalf) {
        reversedHalf = reversedHalf * 10 + number % 10;
        number = number / 10;
    }

    // even length: number == reversedHalf
    // odd length:  drop the middle digit with reversedHalf/10
    return number == reversedHalf || number == reversedHalf / 10;
}
```

This reverses only **half** the digits, so `reversedHalf` can never overflow. It is the standard optimal solution and a strong answer if an interviewer raises the overflow issue.

### Handling text palindromes

```java
public static boolean isPalindrome(String text) {
    String cleaned = text.toLowerCase().replaceAll("[^a-z0-9]", "");
    return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
}
```

Handles `"A man, a plan, a canal: Panama"` by stripping punctuation and case first. A very common follow-up question.

---

# 12. Common beginner mistakes

## Mistake 1 — forgetting to save the original

**Incorrect code**

```java
int num = sc.nextInt();
int rev = 0;
while (num != 0) {
    rev = rev * 10 + num % 10;
    num = num / 10;
}
if (rev == num) { ... }        // ✗ num is 0 by now
```

**Why it is wrong**
The loop reduces `num` to `0`, so the comparison is always `rev == 0` — false for every input except `0` itself. **Every number is reported as not a palindrome.**

**Correct code**

```java
int org_num = num;             // before the loop
...
if (rev == org_num) { ... }
```

**How to recognise it in future**
If a program reports "no" for every input, check whether the value being compared was destroyed. **Whenever a loop consumes a variable, save a copy first.**

---

## Mistake 2 — forgetting to shrink `num`

**Incorrect code**

```java
while (num != 0) {
    rev = rev * 10 + num % 10;
    // ✗ missing: num = num / 10;
}
```

**Why it is wrong**
`num` never changes, so `num != 0` is always `true`. **The program hangs forever.**

**Correct code**

```java
num = num / 10;
```

**How to recognise it**
The program produces no output and never finishes — press Ctrl+C. **Every `while` loop needs a line inside it that moves toward the exit condition.** If you cannot point to that line, you have an infinite loop.

---

## Mistake 3 — swapping `%` and `/`

**Incorrect code**

```java
rev = rev * 10 + num / 10;
num = num % 10;
```

**Why it is wrong**
`/` gives the leading digits, not the last one; `%` leaves `num` stuck at a single digit forever. Wrong answer *and* likely infinite loop.

**Correct code**

```java
rev = rev * 10 + num % 10;
num = num / 10;
```

**How to recognise it**
Remember the pair: **`% 10` takes the last digit; `/ 10` removes it.**

---

## Mistake 4 — seeding `rev` with something other than `0`

**Incorrect code**

```java
int rev = 1;
```

**Why it is wrong**
The first step becomes `1*10 + digit`, prepending a spurious `1`. Reversing `121` would give `1121`.

**Correct code**

```java
int rev = 0;
```

**How to recognise it**
If the reversed number has one digit too many, check the seed.

---

## Mistake 5 — using `=` instead of `==`

**Incorrect code**

```java
if (rev = org_num) { ... }
```

**Why it is wrong**
`=` assigns and produces an `int`; `if` requires a `boolean`.

**What Java does**
Compile error: `incompatible types: int cannot be converted to boolean`. **The compiler catches it** — in C it would silently compile.

**Correct code**

```java
if (rev == org_num) { ... }
```

---

## Mistake 6 — using `.equals()` on primitives

**Incorrect code**

```java
if (rev.equals(org_num)) { ... }
```

**Why it is wrong**
`int` is a primitive, not an object. Primitives have no methods.

**What Java does**
`int cannot be dereferenced`.

**Correct code**

```java
if (rev == org_num) { ... }
```

**How to recognise it**
`X cannot be dereferenced` always means "you called a method on a primitive". **Primitives use operators; objects use methods.**

---

## Mistake 7 — forgetting the `import`

**Incorrect code**

```java
public class PalindromeOrNot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);      // ✗ no import
```

**What Java does**
`cannot find symbol: class Scanner`.

**Correct code**

```java
import java.util.Scanner;
```

**How to recognise it**
`cannot find symbol: class X` usually means a missing import. In IntelliJ, `Alt+Enter` on the red name adds it automatically.

---

## Mistake 8 — assuming the input is valid

**Incorrect assumption**

The user types `abc`.

**What Java does**

```text
Exception in thread "main" java.util.InputMismatchException
    at java.base/java.util.Scanner.throwFor(Scanner.java:...)
```

The program crashes with a stack trace.

**Correct code**

```java
if (sc.hasNextInt()) {
    int num = sc.nextInt();
    ...
} else {
    System.out.println("Please enter a whole number.");
}
```

**How to recognise it**
**Any program that reads input from a human must assume the human will type something unexpected.** Validate.

---

## Mistake 9 — using `for` where `while` fits

**Awkward code**

```java
for (; num != 0; ) {
    rev = rev * 10 + num % 10;
    num = num / 10;
}
```

**Why it is poor**
It compiles and works, but the empty initialisation and update sections announce that a `for` was the wrong shape.

**Correct code**

```java
while (num != 0) { ... }
```

**How to recognise it**
**If two of a `for` loop's three sections are empty, you wanted a `while`.**

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| Digit manipulation | do you know `%10` and `/10`? |
| Loop choice | can you explain why `while` and not `for`? |
| Value preservation | **do you see that the original must be saved?** |
| Loop termination | can you name the line that ends the loop? |
| `==` vs `.equals()` | do you know `==` is correct for primitives? |
| Input handling | do you validate, or do you crash? |
| Overflow awareness | do you know reversing can overflow? |

## Likely follow-up questions

> **"Why do you need `org_num`?"**

Because the loop reduces `num` to `0`. Without a saved copy, the final comparison is always against `0` and every number is reported as not a palindrome.

> **"Why a `while` loop rather than a `for` loop?"**

Because the number of digits is unknown before you start. `for` suits a known repetition count; `while` suits "repeat until a condition changes".

> **"What guarantees the loop terminates?"**

`num = num / 10` reduces the digit count by one each pass, and integer division truncates toward zero, so `num` reaches `0` in exactly as many steps as there are digits.

> **"Do it without converting to a String."**

That is this program. The `%10` / `/10` technique.

> **"Now do it with a String."**

`String.valueOf(n)`, then `StringBuilder.reverse()`, then `.equals()` — emphasising `.equals()` rather than `==` for objects.

> **"What is the time complexity?"**

`O(d)` where `d` is the number of digits — which is `O(log₁₀ n)` in terms of the value itself. Very fast: even a billion has only ten digits.

> **"Can the reversal overflow?"**

Yes. `2,147,483,647` reversed is `7,463,847,412`, which exceeds `int`. Use `long`, or reverse only half the digits.

> **"What about negative numbers?"**

`-121` reversed with this algorithm gives `-121` — so it is wrongly reported as a palindrome. By convention negatives are never palindromes, because the minus sign is not symmetric. Add an explicit `if (num < 0) return false;`.

> **"What about a text palindrome like 'A man, a plan, a canal: Panama'?"**

Lowercase it, strip non-alphanumeric characters, then compare with its reverse — or use two pointers.

> **"Can you exit early?"**

The two-pointer approach stops at the first mismatch. The reversal approach must process every digit. For long numbers, early exit is a genuine win.

---

# 14. Complexity

## Time complexity: `O(d)` — where `d` is the number of digits

The loop runs once per digit, doing constant work each pass: one `%`, one `*`, one `+`, one `/`.

In beginner language:

> "The loop removes one digit each time round. A three-digit number takes three passes; a nine-digit number takes nine. The work grows with the number of *digits*, not the size of the number — so we call it `O(d)`."

Expressed in terms of the value `n`, the digit count is about `log₁₀(n)`, so this is `O(log n)`.

> [!important] Why `O(log n)` is extraordinarily fast
> | Number | Digits | Passes |
> |---|---|---|
> | 121 | 3 | 3 |
> | 1,000,000 | 7 | 7 |
> | 2,147,483,647 | 10 | 10 |
>
> Multiplying the input by ten adds only **one** pass. Even the largest possible `int` needs just ten iterations.
>
> Any algorithm whose work grows with the *digit count* rather than the *value* is effectively instant. That is what `O(log n)` means in practice — and it is why binary search and balanced trees are so valuable.

## Space complexity: `O(1)`

Three `int`s — `num`, `org_num`, `rev` — plus the `Scanner`. Constant, regardless of input size.

> "We use the same three variables whether the number has 3 digits or 10, so the extra memory is constant: `O(1)`."

The `String`-based version is `O(d)` space because it builds a text representation. **The arithmetic version is more memory-efficient**, which is another reason interviewers prefer it.

---

# 15. Edge cases

## Single-digit numbers

```java
Input: 7
```

```text
org_num = 7,  rev = 0
pass 1:  rev = 0*10 + 7 = 7,  num = 7/10 = 0
exit
7 == 7  →  palindrome  ✓
```

**Correct.** Every single-digit number reads the same in both directions.

## Zero

```java
Input: 0
```

```text
org_num = 0,  rev = 0
Condition:  0 != 0  →  FALSE
The loop body NEVER RUNS.
rev stays 0.
0 == 0  →  palindrome  ✓
```

**Correct** — and correct *by accident*. The loop never executes, but because both `rev` and `org_num` are `0`, the comparison still gives the right answer.

> [!note]
> This is a nice illustration of `while`'s defining property: **the body may run zero times**, because the condition is checked before the first pass. A `do-while` would run once regardless.
>
> It is also a reminder that "the code produced the right answer" and "the code handled the case deliberately" are different things. Here they coincide; often they do not.

## Numbers ending in zero

```java
Input: 120
```

```text
pass 1:  rev = 0*10 + 0 = 0,    num = 12
pass 2:  rev = 0*10 + 2 = 2,    num = 1
pass 3:  rev = 2*10 + 1 = 21,   num = 0

21 == 120  →  not a palindrome  ✓
```

**Correct.** The leading zero simply vanishes, because `0*10 + 0` is `0`. Any number ending in `0` (except `0` itself) can never be a palindrome, since its reverse would need a leading zero.

## Negative numbers

```java
Input: -121
```

```text
org_num = -121,  rev = 0

-121 % 10  =  -1     ← Java's % keeps the sign of the LEFT operand
-121 / 10  =  -12    ← truncates toward zero

pass 1:  rev = 0*10 + (-1)   = -1,    num = -12
pass 2:  rev = -1*10 + (-2)  = -12,   num = -1
pass 3:  rev = -12*10 + (-1) = -121,  num = 0

-121 == -121  →  reported as a PALINDROME
```

> [!warning] A wrong answer that looks right
> The algorithm reverses `-121` to `-121` and reports it as a palindrome.
>
> By mathematical convention, **negative numbers are never palindromes**, because the minus sign appears only at the front — `-121` written backwards would be `121-`, which is not a number.
>
> The current program has no negative check. The fix:
> ```java
> if (num < 0) {
>     System.out.println(num + " is not a Palindrome number");
>     return;
> }
> ```
>
> Note that using `while (num > 0)` instead of `num != 0` would *also* change the behaviour — the loop would exit immediately, leaving `rev = 0`, and `-121 != 0` would correctly report "not a palindrome". But that would be right by accident rather than by intent. **Prefer an explicit check that states the rule.**

## Large numbers — overflow

```java
Input: 2147483647       // Integer.MAX_VALUE
```

The true reverse is `7,463,847,412`, which exceeds `int`'s maximum of `2,147,483,647`.

```text
rev overflows silently and wraps to a negative or wrong value
→ the comparison fails
→ reported as "not a palindrome"
```

Here the *answer* happens to be correct (`2147483647` genuinely is not a palindrome), but for the wrong reason. A number like `1999999991`, which **is** a palindrome, would be misreported if its reverse overflowed.

> [!warning] Silent integer overflow
> No exception, no warning — the value wraps around.
>
> **Fixes:**
> ```java
> long rev = 0;                        // 64 bits, cannot overflow from an int input
> ```
> or reverse only half the digits (section 11), which is the standard optimal solution precisely because it makes overflow impossible.

## Non-numeric input

```java
Input: abc
```

```text
Exception in thread "main" java.util.InputMismatchException
```

The program crashes. There is no validation. Use `hasNextInt()` to guard.

## No input at all (end of stream)

If input is piped from an empty file, `nextInt()` throws `NoSuchElementException`. Same fix — check `hasNextInt()` first.

## Repdigits

```java
Input: 7777
```

```text
rev = 7 → 77 → 777 → 7777
7777 == 7777  →  palindrome  ✓
```

Correct. Any number whose digits are all identical is trivially a palindrome.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Check whether a number reads the same backwards.

        ↓

What does "reads the same backwards" mean precisely?
The number equals its own reverse.

        ↓

So I need TWO things:
(a) the reversed number
(b) the original, to compare against

        ↓

Where does the number come from?
The user types it.
→ I need to read keyboard input.

        ↓

How does Java read input?
Scanner, wrapping System.in.
Scanner lives in java.util, NOT java.lang,
so it needs an import.

        ↓

Which Scanner method reads a whole number?
Browse sc. in the IDE → nextInt()
Check its return type → int
So the variable holding it must be int.

        ↓

How do I reverse a number WITHOUT text?
I need three operations:
  get the last digit   → % 10
  remove the last digit → / 10
  append a digit        → rev*10 + digit

        ↓

How many times do I repeat?
Once per digit — but I do not know how many
digits there are until I have consumed them all.

        ↓

Known count → for loop.
Unknown count, known stopping condition → while loop.
→ while (num != 0)

        ↓

What makes the loop stop?
num = num/10 shrinks num toward 0 every pass.
Integer division truncates, so it always reaches 0.
(Without this line: infinite loop.)

        ↓

PROBLEM SPOTTED:
that same line destroys num —
and I need num for the final comparison.

        ↓

Solution: save a copy BEFORE the loop.
int org_num = num;
(Same principle as temp in SwapNumbers.)

        ↓

What is the accumulator's seed?
rev*10 + digit starting from 0
gives exactly the first digit.
→ int rev = 0;

        ↓

After the loop, compare rev with org_num.
Both are int PRIMITIVES → == is correct.
(.equals() would not even compile.)

        ↓

Am I choosing a VALUE or an ACTION?
Two different messages to print → an ACTION.
→ if / else, not a ternary.

        ↓

Trace 121:
rev: 0 → 1 → 12 → 121
num: 121 → 12 → 1 → 0
121 == 121  ✓

        ↓

Trace 123:
rev: 0 → 3 → 32 → 321
321 == 123  →  false  ✓

        ↓

Check hostile inputs:
  0        → loop never runs, 0 == 0  ✓
  7        → single pass  ✓
  -121     → reported as palindrome  ⚠ should not be
  "abc"    → InputMismatchException  ⚠ crashes
  huge     → rev can overflow  ⚠
```

> [!important] What the last block demonstrates
> The algorithm was finished four steps earlier. The last block is **testing** — and it found three real weaknesses that no amount of re-reading the code would have revealed.
>
> **For any program that takes input, always ask:** what if it is zero? negative? the largest possible value? not a number at all? Those four questions catch most input-handling bugs.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `import java.util.Scanner;` | "I want to use the `Scanner` tool, which lives in a part of Java that is not loaded automatically." |
| `Scanner sc = new Scanner(System.in);` | "Make a new reader and point it at the keyboard." |
| `System.out.println("Enter the number:");` | "Tell the user what to type." |
| `int num = sc.nextInt();` | "Wait for the user to type a whole number, and store it in a whole-number box called `num`." |
| `int org_num = num;` | "Make a second copy of that number, because the steps below are going to destroy the first one and I will still need the original." |
| `int rev = 0;` | "Make an empty box to build the reversed number in, starting at zero so the first digit lands cleanly." |
| `while(num != 0)` | "Keep repeating as long as there is anything left of `num`." |
| `rev = rev*10 + num%10;` | "Shift everything in `rev` one place to the left to make room, then put the last digit of `num` into the space." |
| `num = num/10;` | "Chop the last digit off `num`. This is also what eventually makes the loop stop." |
| `if(rev==org_num)` | "Ask whether the number I built backwards is the same as the copy I saved at the start." |

The loop in one sentence:

> **"Keep taking the last digit off `num` and sticking it onto the end of `rev`, until `num` runs out."**

And the whole program:

> **"Ask the user for a number and keep a copy of it. Peel its digits off one at a time from the right, building them up left-to-right into a new number. When nothing is left, compare the new number with the copy — if they match, the original reads the same in both directions."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| `Scanner` | reads and parses input; lives in `java.util`, so it needs an `import` |
| `System.in` | the keyboard input stream — the counterpart to `System.out` |
| `while` loop | repeat until a condition changes; **may run zero times** |
| loop termination | something inside the body must move toward the exit condition |
| `%` (remainder) | `n % 10` gives the **last digit** |
| `/` (integer division) | `n / 10` **removes** the last digit; truncates, never rounds |
| digit append | `rev = rev * 10 + digit` |
| save before destroy | copy a value before the operation that consumes it |
| `==` for primitives | compares values — correct for `int`, wrong for objects |
| `.equals()` for objects | compares contents — required for `String` |
| integer overflow | reversing a large `int` can silently wrap |

### Important methods

| Method | Owner | Accepts | Returns |
|---|---|---|---|
| `nextInt()` | `java.util.Scanner` | nothing | `int` — throws on bad input |
| `hasNextInt()` | `java.util.Scanner` | nothing | `boolean` — checks **without consuming** |
| `nextLine()` | `java.util.Scanner` | nothing | `String` |
| `close()` | `java.util.Scanner` | nothing | `void` — also closes `System.in` |
| `println(String)` | `java.io.PrintStream` | text | `void` |
| `String.valueOf(int)` | `java.lang.String` | an `int` | `String` |

There is deliberately **no** `isPalindrome()` in the standard library.

### Important syntax

| Syntax | Meaning |
|---|---|
| `import java.util.Scanner;` | make a class outside `java.lang` available |
| `new Scanner(System.in)` | create a reader pointed at the keyboard |
| `while (cond) { }` | repeat while `cond` is true; checked **before** each pass |
| `n % 10` | the last digit |
| `n / 10` | the number with its last digit removed |
| `rev * 10 + d` | append digit `d` to `rev` |
| `!=` | not equal to |
| `==` | equal to — correct for primitives |
| `if / else` | choose between two **actions** |

### Main interview concept

> **Reverse a number with `%10` and `/10`, and save the original before you start — because the loop destroys it.** `rev = rev * 10 + num % 10` appends a digit; `num = num / 10` removes one and is also what makes the loop terminate. Use `while` rather than `for` because the digit count is unknown in advance, and use `==` rather than `.equals()` because these are `int` primitives.

### Main lesson for code reading

> **When you meet a `while` loop, ask two questions before anything else:**
>
> ```text
> 1. What makes this condition eventually false?
>       → num = num / 10   (without it: infinite loop)
>
> 2. What does the loop destroy that I might still need?
>       → num  (which is why org_num exists)
> ```
>
> A `for` loop answers the first question in its header. A `while` loop hides the answer somewhere in its body — so finding it is the first thing you do. And whenever you see a variable copied for no obvious reason, look ahead for the operation that will consume the original.

---

### Related notes

- [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]] — the same reversal, done with `charAt` on text instead of arithmetic
- [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] — where `%` was introduced as a divisibility test
- [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]] — "save before you destroy", the principle behind `org_num`
- [[Java_PrimeOrNot_Code_Reading_Explanation_Obsidian|PrimeOrNot]] — `%` used again, this time to count divisors
- [[Java_TernaryOperatorTime_Code_Reading_Explanation_Obsidian|TernaryOperatorTime]] — choosing a value with `? :` versus choosing an action with `if`
