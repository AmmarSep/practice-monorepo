---
title: Java Code Reading — ReverseString
tags:
  - java
  - interview-programs
  - code-reading
  - strings
  - loops
aliases:
  - ReverseString Explained
  - Reverse a String in Java
---

# Java Code Reading — ReverseString Explained for a Fresh Java Programmer

> [!note]
> This program introduces two ideas that will follow you everywhere in Java: **a `String` is a sequence of characters you can index into**, and **`String` is immutable**.
>
> It also runs a loop **backwards**, which is the first time you have seen a counter that counts down. And it contains a small but real bug: the result carries a stray leading space.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → In what order do I need to visit the pieces? → What accumulates the answer? → What is its starting value?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class ReverseString
{
    public static void main(String[] args)
    {
        String str1 = "Google vs Amazon";

        String revStr = " ";

        for(int i=str1.length()-1; i>=0; i--)
        {
            revStr = revStr + str1.charAt(i);
        }

        System.out.println(revStr);
    }
}
```

The executable logic is four lines:

```java
String str1 = "Google vs Amazon";
String revStr = " ";
for(int i = str1.length()-1; i >= 0; i--) { revStr = revStr + str1.charAt(i); }
System.out.println(revStr);
```

> [!warning] Notice the seed
> `String revStr = " ";` starts with a **single space**, not an empty string.
>
> That space stays at the front of the result forever, so the program prints ` nozamA sv elgooG` with a leading space rather than `nozamA sv elgooG`.
>
> We explain the code as written, prove this in section 9, and fix it in section 11.

---

# 2. What problem is this program solving?

In plain language:

> I have some text. Print it backwards.

```text
"Google vs Amazon"   →   "nozamA sv elgooG"
```

### What do we know?

- One piece of text.
- Text is made of characters in a specific order.

### What do we need?

- The same characters, in the opposite order, as a new piece of text.

### What transformations are required?

Notice the shape: **many pieces in, one value out** — a reduction, like [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]]. But instead of numbers combined with `+` as addition, we have characters combined with `+` as **concatenation**.

```text
SUM                            REVERSE
──────────────────             ──────────────────
int sum = 0;                   String rev = "";
sum = sum + arr[i];            rev = rev + str.charAt(i);
        ↑ addition                     ↑ concatenation
walk forwards                  walk BACKWARDS
```

Two changes from the familiar pattern: the accumulator is a `String`, and the loop runs in reverse.

### Why walk backwards?

This is the key insight, and it is worth deriving rather than accepting.

We want the **last** character to come out **first**. So we should read the last character first. That means starting at the end and moving toward the beginning.

```text
"Google vs Amazon"
 ↑              ↑
 index 0        index 15

read from index 15 down to index 0
  →  n, o, z, a, m, A, ' ', s, v, ' ', e, l, g, o, o, G
append each one as we go
  →  "nozamA sv elgooG"
```

> [!important] Two ways to reverse — pick one, not both
> There are exactly two correct strategies, and mixing them gives you the original string back:
>
> | Strategy | Loop direction | Append side |
> |---|---|---|
> | **A** | backwards, from `length-1` to `0` | append to the **end**: `rev = rev + ch` |
> | **B** | forwards, from `0` to `length-1` | prepend to the **front**: `rev = ch + rev` |
>
> ```text
> Strategy A:  read "olleh" backwards, append  → h, he, hel, hell, hello
> Strategy B:  read "olleh" forwards, prepend  → o, lo, llo, ello, hello
> ```
>
> Both work. This program uses **A**.
>
> Doing both — forwards *and* appending — simply copies the string unchanged. That is the classic beginner mistake, and it produces output that looks so normal you might not notice.

### What starting value?

Following the accumulator reasoning: *what value can I concatenate with that changes nothing?*

```text
"" + "abc"  →  "abc"        ← the empty string is the identity
" " + "abc" →  " abc"       ← a space is NOT neutral
```

The identity for string concatenation is `""` — the **empty** string. The program uses `" "` instead, which is why the stray space appears.

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder. First statement in the file.

## `public class ReverseString`

- `public` — usable from anywhere.
- `class` — declares a class.
- `ReverseString` — `PascalCase`, matching `ReverseString.java`.

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

`String`, `System` and `int` only — `java.lang` and primitives, so no `import` is needed.

## Variables

| Variable | Type | Role |
|---|---|---|
| `str1` | `String` | the input text |
| `revStr` | `String` | the accumulator — the reversed text being built |
| `i` | `int` | the counter — the current character position, counting **down** |

Two `String`s and one `int`. Note that `i` is an `int` even though we are working with text — **positions are always whole numbers**, whatever they index into.

---

# 4. Line-by-line explanation

## 4.1 — `String str1 = "Google vs Amazon";`

```java
String str1 = "Google vs Amazon";
```

**What is this?**
A declaration with initialisation, holding the text to reverse.

**Why do we need it?**
The program needs input. Naming it lets us refer to it repeatedly — we call `.length()` on it and `.charAt()` on it sixteen times.

**How would a beginner know to write it?**
Ask: "What data does my task operate on?" Text. Anything referred to more than once needs a name.

**What type of data is involved?**
Text — a sequence of characters.

**Why `String` and not `char`?**

| Type | Holds | Suitable? |
|---|---|---|
| `String` | any number of characters | **yes** — 16 characters here |
| `char` | exactly **one** character | no — cannot hold a phrase |
| `char[]` | many characters, mutable | possible, but `String` is more convenient |

The deciding question: **how many characters?** More than one → `String`.

**What does each part mean?**

```text
String   str1   =   "Google vs Amazon"   ;
   │      │     │            │
   │      │     │            └── the String literal
   │      │     └─────────────── store the reference
   │      └───────────────────── the reference variable
   └──────────────────────────── the declared type
```

**What does Java do when it reaches this line?**
Creates (or reuses, from the string pool) a `String` object containing those 16 characters, and stores a **reference** to it in `str1`.

> [!important] `str1` does not contain the text
> Like an array, a `String` variable holds an **address**, not the characters themselves.
>
> ```text
>   str1                the String object
>  ┌─────┐        ┌───┬───┬───┬───┬───┬───┬───┬───┬ ...
>  │ ref ├───────▶│ G │ o │ o │ g │ l │ e │   │ v │ ...
>  └─────┘        └───┴───┴───┴───┴───┴───┴───┴───┴ ...
>                   0   1   2   3   4   5   6   7
> ```
>
> The characters are indexed **from 0**, exactly like an array. `"Google vs Amazon"` has 16 characters at indexes `0` through `15`.

**Why the name `str1`?**
Mediocre. The `1` suggests there might be a `str2` somewhere, and `str` only says "this is a string" — which the type already told us. `input`, `text`, or `original` would say more.

---

## 4.2 — `String revStr = " ";`

```java
String revStr = " ";
```

**What is this?**
The accumulator — the `String` that will collect the reversed characters.

**Why do we need it?**
Concatenation joins two things at a time. To assemble 16 characters you build up a partial result, one character per step. `revStr` holds that partial result.

**How would a beginner know to write it?**
Ask: **"Do I need to remember something between one loop pass and the next?"** Yes — the characters collected so far. Anything that must survive across iterations is declared **before** the loop.

**Why must it be declared outside the loop?**

```java
for (int i = str1.length()-1; i >= 0; i--) {
    String revStr = " ";                       // ✗ reset every pass
    revStr = revStr + str1.charAt(i);
}
System.out.println(revStr);                    // ✗ error: cannot find symbol
```

Declared inside, it would be recreated each pass — holding only one character — and would not exist afterwards.

**What type and why?**
We are building text, and concatenation of `String` and `char` produces a `String`. So the accumulator is a `String`.

**Now the seed — and the bug**

> [!warning] `" "` is not the identity for concatenation
> The accumulator's seed must be the value that **changes nothing** when combined. For concatenation that is the **empty string**:
>
> ```text
> "" + "abc"   →  "abc"      ✓ unchanged — this is the identity
> " " + "abc"  →  " abc"     ✗ an extra space appears
> ```
>
> Because `revStr` starts as `" "`, every character is appended *after* that space, and the space never goes away:
>
> ```text
> revStr = " "
> revStr = " " + 'n'  →  " n"
> revStr = " n" + 'o' →  " no"
> ...
> final:  " nozamA sv elgooG"
>          ↑
>       stray space
> ```
>
> The fix is one character:
> ```java
> String revStr = "";      // empty string — two quotes, nothing between
> ```
>
> **Why this bug is easy to miss:** a leading space is nearly invisible in console output. The program *looks* correct. Only comparing lengths or using `.equals()` would reveal it — see section 15.

**`""` vs `" "` vs `null`**

| Literal | Length | Meaning |
|---|---|---|
| `""` | `0` | an empty `String` — a real object with no characters |
| `" "` | `1` | a `String` containing one space character |
| `null` | — | **no object at all**; calling a method on it throws `NullPointerException` |

These are three genuinely different things. `"" ` is safe to concatenate; `null` is not:

```java
String s = null;
s = s + "abc";      // gives "nullabc" — Java converts null to the text "null"!
s.length();         // throws NullPointerException
```

**Why the name `revStr`?**
Reasonable — "reversed string". `reversed` or `result` would read slightly better.

---

## 4.3 — `for(int i=str1.length()-1; i>=0; i--)`

```java
for(int i=str1.length()-1; i>=0; i--)
```

**What is this?**
A `for` loop that counts **downwards** from the last character index to the first.

**Why do we need it?**
To visit every character in reverse order.

**The three sections**

```text
for ( int i = str1.length()-1 ; i >= 0 ; i-- )
          │                       │        │
          │                       │        └── DECREMENT after each pass
          │                       └─────────── keep going while still ≥ 0
          └─────────────────────────────────── start at the LAST index
```

Compare with every forward loop you have seen:

| | Forward loop | This backward loop |
|---|---|---|
| start | `i = 0` | `i = length - 1` |
| condition | `i < length` | `i >= 0` |
| update | `i++` | `i--` |

**All three parts are reversed.** That is the pattern — changing only one of them produces a broken loop.

**Why does `i` start at `str1.length()-1`?**

`str1.length()` is `16`. The valid indexes are `0` to `15`. So the **last** index is `16 - 1 = 15`.

```text
"Google vs Amazon"
 G  o  o  g  l  e     v  s     A  m  a  z  o  n
 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 ↑                                            ↑
first index                            last index = length-1
```

> [!important] Why `length - 1` is correct here, and was wrong in `EvenOdd`
> In [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]], writing `i < length - 1` was a bug. Here `i = length - 1` is correct. What is the difference?
>
> ```text
> EvenOdd:  i < length - 1     ← a CONDITION: stop before index 15  ✗ skips it
> Reverse:  i = length - 1     ← a STARTING VALUE: begin AT index 15  ✓ includes it
> ```
>
> `length - 1` is the **last valid index** — a true and useful fact. Using it as a *starting point* for a backward loop is exactly right. Using it as an *upper bound* with `<` in a forward loop excludes it.
>
> Same expression, two completely different roles. **Read what the expression is being used for, not just what it computes.**

**Why `i >= 0` and not `i > 0`?**

Because index `0` — the first character, `'G'` — must be included.

```text
i >= 0   →  visits 15,14,...,2,1,0   ✓ all 16 characters
i >  0   →  visits 15,14,...,2,1     ✗ misses 'G'
```

Using `i > 0` would print `nozamA sv elgoo` — missing the final `G`. Another silent wrong answer.

> [!tip] The mirror rule
> ```text
> forward:   start at 0            stop when i == length     (i < length)
> backward:  start at length-1     stop when i == -1         (i >= 0)
> ```
> In both cases the loop ends when the counter steps *just past* the valid range. Ask "what is the first invalid value?" and the condition writes itself.

**What is `i--`?**

The **decrement** operator: `i = i - 1`. It is the mirror of `i++`.

Without it — or with `i++` by mistake — `i` would climb past `15`, and `charAt(16)` would throw `StringIndexOutOfBoundsException`.

**Why `str1.length()` with parentheses?**

> [!warning] `length()` vs `length`
> | Type | Correct form | Why |
> |---|---|---|
> | `String` | `str1.length()` | a **method** — `String` is a class |
> | array | `arr.length` | a **field** — arrays are a language construct |
> | `List` | `list.size()` | a different method name |
>
> Writing `str1.length` gives `cannot find symbol: variable length`.
>
> There is no elegant reason for the inconsistency; it is a historical artefact of Java's design. Memorise the three.

**Is `length()` called every iteration?**

Yes — the condition and update run each pass, but the *initialisation* section runs only once. So `str1.length()` is evaluated exactly **once**, at the start.

That is worth knowing: had the length been in the *condition* (as in forward loops, `i < arr.length`), it would be evaluated on every pass. For a `String` this is cheap — `length()` just returns a stored field — so it does not matter in practice.

---

## 4.4 — `revStr = revStr + str1.charAt(i);`

```java
revStr = revStr + str1.charAt(i);
```

**What is this?**
The accumulation step — appending one character to the growing result.

**Why do we need it?**
This is the rule applied at each step: "take the character at the current position and add it to the end of what I have built so far."

**What does each part mean?**

```text
revStr   =   revStr   +   str1.charAt(i)   ;
   │     │      │     │          │
   │     │      │     │          └── the character at position i
   │     │      │     └───────────── string concatenation
   │     │      └─────────────────── the result built so far
   │     └────────────────────────── store the new result back
   └──────────────────────────────── the destination
```

**What is `charAt(i)`?**

A `String` method that returns the single character at the given position.

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.lang.String` |
| **Why can we call it?** | `str1` is a `String` object |
| **What does it accept?** | one `int` — the index, counting from `0` |
| **What does it return?** | a **`char`** — not a `String` |
| **What if the index is invalid?** | throws `StringIndexOutOfBoundsException` |

```java
str1.charAt(0)    →  'G'
str1.charAt(15)   →  'n'
str1.charAt(6)    →  ' '     ← a space is a perfectly ordinary character
str1.charAt(16)   →  throws — index 16 does not exist
```

> [!important] `charAt` returns a `char`, not a `String`
> This distinction matters constantly:
>
> ```java
> char c = str1.charAt(0);        // ✓ types match
> String s = str1.charAt(0);      // ✗ incompatible types: char cannot be converted to String
> String s = "" + str1.charAt(0); // ✓ concatenation converts it
> ```
>
> A `char` is a **primitive** holding one character. A `String` is an **object** holding any number. They are not interchangeable — but concatenation bridges them automatically, which is exactly what this program relies on.

**What does `+` do here?**

`revStr` is a `String` and `charAt(i)` returns a `char`. When a `String` is on the left, `+` **concatenates** and converts the right operand to text.

```text
" nozam"  +  'A'
 String      char
      ↓
"nozamA" appended → " nozamA"    (a String)
```

> [!warning] The `char` arithmetic trap
> `+` on two `char`s does something surprising:
>
> ```java
> char a = 'A', b = 'B';
> System.out.println(a + b);        // prints 131, not "AB"!
> System.out.println("" + a + b);   // prints "AB"
> ```
>
> A `char` is secretly a **number** — its Unicode code point. `'A'` is `65` and `'B'` is `66`, so `a + b` is `131`.
>
> Concatenation only kicks in when one operand is a `String`. In this program `revStr` is always a `String`, so we are safe — but the trap is worth knowing, and it is a favourite interview question.

**What does Java do when it reaches this line?**

1. Evaluate the right side: read `revStr`, read `str1.charAt(i)`, join them into a **new** `String`.
2. Store a reference to that new `String` back into `revStr`.

> [!important] `String` is immutable — every `+` creates a NEW object
> This is the most important concept in this program.
>
> A `String`'s characters can **never** change after creation. `revStr = revStr + ch` does not extend the existing string — it builds a brand-new one and points `revStr` at it. The old one is discarded.
>
> ```text
> pass 1:  create " n"                (old " " discarded)
> pass 2:  create " no"               (old " n" discarded)
> pass 3:  create " noz"              (old " no" discarded)
> ...
> pass 16: create " nozamA sv elgooG" (old discarded)
> ```
>
> **Sixteen separate `String` objects are created**, fifteen of which are immediately garbage. Each one copies all the characters accumulated so far.
>
> That is why this approach is `O(n²)` rather than `O(n)` — see section 14 — and why `StringBuilder` exists. For 16 characters it is irrelevant; for 100,000 it is the difference between instant and unusably slow.

---

## 4.5 — `System.out.println(revStr);`

```java
System.out.println(revStr);
```

**What is this?**
Printing the final result.

**Which overload runs?**
`println(String)`, because `revStr` is a `String`. No concatenation here — there is nothing to join.

**Output**

```text
 nozamA sv elgooG
```

Note the leading space, from the `" "` seed.

> [!note] The output has no label
> Someone running this sees a line of scrambled text with no explanation. A small improvement:
>
> ```java
> System.out.println("Original: " + str1);
> System.out.println("Reversed: " + revStr);
> ```
>
> which makes the transformation visible. **Programs communicate with humans** — labelling output costs one line.

---

# 5. How to think like the programmer

```text
Requirement
"Print this text backwards"
        ↓
What data do I have?
Text → String
        ↓
What do I need to produce?
Text → String
Same characters, opposite order
        ↓
Can I flip a String in one operation?
Check String's methods... there is no reverse().
So I must build it myself.
        ↓
How is text built up in Java?
Concatenation, two pieces at a time
        ↓
So this is an ACCUMULATOR problem:
build the answer one character per step
        ↓
What type is the accumulator?
I am building text → String
        ↓
What is its starting value?
What can I concatenate with that changes nothing?
"" + "abc" = "abc"  → the EMPTY string
(the program uses " " — that is the bug)
        ↓
In what ORDER do I visit the characters?
I want the last one to come out first.
So read from the END toward the START.
        ↓
Where is the end?
Indexes run 0 to length-1
→ start at length-1
        ↓
Where do I stop?
Index 0 must be included → i >= 0
        ↓
How do I move?
Toward smaller indexes → i--
        ↓
How do I read one character?
str1.charAt(i) → returns a char
        ↓
How do I add it to the accumulator?
revStr = revStr + char
(the String on the left makes + concatenate)
        ↓
Trace to verify:
" " → " n" → " no" → ... → " nozamA sv elgooG"
        ↓
Compare with the expected answer:
"nozamA sv elgooG"
There is an extra leading space. Fix the seed.
```

> [!important] The string-accumulator skeleton
> ```java
> String result = "";                        // identity for concatenation
> for (RANGE) {
>     result = result + ONE_PIECE;
> }
> use(result);
> ```
>
> | Goal | Range | Piece |
> |---|---|---|
> | reverse | `length-1` down to `0` | `str.charAt(i)` |
> | copy | `0` up to `length-1` | `str.charAt(i)` |
> | uppercase each char | `0` up to `length-1` | `Character.toUpperCase(str.charAt(i))` |
> | keep only letters | `0` up to `length-1` | `charAt(i)` guarded by an `if` |
> | join array elements | `0` up to `length-1` | `arr[i] + ","` |
>
> Same skeleton as `SumOfArrayEle` and `Factorials`, with `String` in place of `int` and concatenation in place of `+`/`*`.

---

# 6. Deep explanation of important Java concepts used

## `String`

A **class** (not a primitive) representing a sequence of characters.

Four facts that matter:

1. **Immutable.** Characters can never change after creation. Every "modifying" method returns a *new* `String`.
2. **Reference type.** A `String` variable holds an address, not the characters.
3. **Zero-indexed.** Positions run `0` to `length()-1`, exactly like an array.
4. **Interned.** Identical literals may share one object in the "string pool", which is why `==` on `String`s behaves confusingly (see below).

## Immutability — the consequences

```java
String s = "hello";
s.toUpperCase();                 // computes "HELLO" and THROWS IT AWAY
System.out.println(s);           // still "hello"

s = s.toUpperCase();             // ✓ must assign the result back
System.out.println(s);           // "HELLO"
```

> [!warning] The single most common `String` mistake
> Every `String` method — `trim()`, `toUpperCase()`, `replace()`, `substring()`, `concat()` — **returns a new `String` and leaves the original untouched**.
>
> If you do not assign the result, the work is discarded silently. No error, no warning.
>
> **Rule: if a `String` method's return type is `String`, you must use the return value.** Check the return type, and the rule enforces itself.

## `char`

A **primitive** holding exactly one character, written with single quotes.

```java
char letter = 'A';
char space = ' ';
char digit = '7';
```

A `char` is really a 16-bit unsigned number — a Unicode code point:

```java
char c = 'A';
int code = c;                    // 65 — automatic widening
System.out.println((char) 66);   // 'B'
System.out.println('A' + 1);     // 66, not 'B' — arithmetic wins
System.out.println((char)('A' + 1));  // 'B'
```

| | `char` | `String` |
|---|---|---|
| quotes | `'a'` — single | `"a"` — double |
| kind | primitive | object |
| holds | exactly one character | zero or more |
| `+` with another of its kind | **numeric addition** | concatenation |

## `charAt(int)`

```java
public char charAt(int index)
```

Returns the character at `index`. Throws `StringIndexOutOfBoundsException` for an index below `0` or at/above `length()`.

## `length()`

```java
public int length()
```

Returns the number of characters. **Note the parentheses** — it is a method, unlike an array's `length` field.

## The `for` loop, counting down

```java
for (int i = start; i >= end; i--) { ... }
```

```text
1. initialisation           (once)
   ↓
2. check condition ─── false ──▶ exit
   ↓ true
3. run body
   ↓
4. run update (i--)
   ↓
   back to step 2
```

The mechanics are identical to a forward loop. Only the three sections' contents change.

## Decrement `--`

```java
i--;      // post-decrement: yield the current value, then subtract 1
--i;      // pre-decrement:  subtract 1, then yield the new value
```

As a standalone statement — which is how a `for` update uses it — they are identical.

## String concatenation with `+`

| Left | Right | `+` means | Example |
|---|---|---|---|
| `String` | anything | concatenation | `"a" + 'b'` → `"ab"` |
| anything | `String` | concatenation | `1 + "a"` → `"1a"` |
| `char` | `char` | **numeric addition** | `'a' + 'b'` → `195` |
| `int` | `int` | numeric addition | `1 + 2` → `3` |

Evaluation is strictly **left to right**, which is why `"" + a + b` concatenates while `a + b + ""` might add first.

## `==` vs `.equals()` for Strings

Not used in this program, but essential to know:

```java
String a = "hello";
String b = "hello";
String c = new String("hello");

a == b          // true  — both point at the SAME pooled object
a == c          // false — c is a different object
a.equals(c)     // true  — same CONTENTS
```

> [!warning]
> **`==` compares references; `.equals()` compares contents.** For `String`s you almost always want `.equals()`.
>
> `==` sometimes appears to work because of string pooling — which makes it a particularly nasty bug, since it passes your tests and fails in production. See [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]], where `==` **is** correct because it compares `char` primitives, not `String` objects.

---

# 7. Why this syntax?

## `""` vs `" "` vs `null`

| Literal | Length | Safe to concatenate? |
|---|---|---|
| `""` | 0 | yes — and it is the identity |
| `" "` | 1 | yes, but adds a space |
| `null` | n/a | yes (gives `"null"`), but any **method call** throws |

## `'c'` vs `"c"`

```java
char c = 'a';        // ✓ primitive, one character
String s = "a";      // ✓ object, one character
char c = "a";        // ✗ incompatible types
String s = 'a';      // ✗ incompatible types
```

Single quotes are `char`; double quotes are `String`. `charAt` returns the first kind; string literals are the second.

## `length()` vs `length` vs `size()`

| Type | Form |
|---|---|
| `String` | `str.length()` |
| array | `arr.length` |
| `List` | `list.size()` |

## `i--` vs `i++`

```java
i++      // i = i + 1   — move toward larger indexes
i--      // i = i - 1   — move toward smaller indexes
```

Using the wrong one in a backward loop causes an immediate crash: starting at `15` and incrementing reaches `16`, and `charAt(16)` throws.

## `>= 0` vs `> 0`

```text
i >= 0   →  15,14,...,1,0    ✓ includes the first character
i >  0   →  15,14,...,1      ✗ drops index 0
```

## `length()-1` as a start vs as a bound

```java
for (int i = str.length()-1; i >= 0; i--)     // ✓ START at the last index
for (int i = 0; i < arr.length - 1; i++)      // ✗ BOUND that excludes the last
```

The same expression, opposite effects. Read the role, not just the value.

## `+` — concatenation or addition?

Decided entirely by operand types. With a `String` on the left, it always concatenates.

---

# 8. Method discovery

## `charAt`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.lang.String` |
| **Why can we call it?** | `str1` is a `String` object |
| **What does it accept?** | one `int` index, from `0` |
| **What does it return?** | `char` |
| **How does that affect the next line?** | a `char` cannot be assigned to a `String`, but concatenating with a `String` converts it |
| **Exceptions?** | `StringIndexOutOfBoundsException` for an invalid index |

## `length`

| Question | Answer |
|---|---|
| **Owner** | `java.lang.String` |
| **Accepts** | nothing |
| **Returns** | `int` |
| **Why parentheses?** | it is a method; only arrays have a `length` field |

## How you would discover these in IntelliJ

Type:

```java
str1.
```

The list is long, because `String` is a rich class:

```text
length()        charAt()        substring()     indexOf()
split()         trim()          strip()         replace()
toUpperCase()   toLowerCase()   contains()      equals()
equalsIgnoreCase()   startsWith()   endsWith()  isEmpty()
isBlank()       toCharArray()   chars()         repeat()
concat()        matches()       compareTo()     ...
```

Hover over any of them and IntelliJ shows the signature and Javadoc.

> [!important] Look for `reverse()` — and notice it is missing
> Scroll the list. There is **no `String.reverse()`**.
>
> That absence is informative. It means either you write the loop yourself, or another class provides it. Searching "java reverse string" finds:
>
> ```java
> new StringBuilder(str1).reverse().toString()
> ```
>
> `reverse()` lives on `StringBuilder`, not on `String` — and the reason is exactly immutability. A `String` cannot reverse *itself*, because it cannot change. `StringBuilder` is the **mutable** companion class, so it can.
>
> **When a method you expect is missing, ask why.** The answer usually teaches you something about the type's design.

## Discovering `StringBuilder`

```text
"I need to build a String piece by piece."
        ↓
String is immutable — every + makes a new object
        ↓
Is there a mutable version?
Search: "java mutable string"
        ↓
Find: StringBuilder
        ↓
Check its methods:
  append(char)      → returns StringBuilder (for chaining)
  reverse()         → returns StringBuilder
  toString()        → returns String
        ↓
Note: append RETURNS the builder, but also
      MODIFIES it in place. Unlike String methods,
      you do not have to assign the result.
        ↓
StringBuilder sb = new StringBuilder();
sb.append('n');                    // modifies sb directly
String result = sb.toString();     // convert back at the end
```

> [!tip] The general rule this teaches
> **Immutable types return new values; mutable types modify in place.**
>
> ```java
> str = str.trim();          // String — MUST assign
> sb.append('x');            // StringBuilder — no assignment needed
> Arrays.sort(arr);          // array — modified in place, returns void
> ```
>
> Reading a method's return type tells you which kind you are dealing with. A `void` return almost always means "I modified something". A return type matching the receiver usually means "here is a new value — use it."

---

# 9. Trace the program with real values

```text
Input:  str1 = "Google vs Amazon"
        str1.length() = 16
        valid indexes: 0..15
        start:  i = 16 - 1 = 15
        stop:   when i < 0
Seed:   revStr = " "        ← one space
```

## The input, indexed

```text
char:   G  o  o  g  l  e     v  s     A  m  a  z  o  n
index:  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
                          ↑        ↑
                       spaces at indexes 6 and 9
```

## Iteration table

```text
─────────────────────────────────────────────────────────────
Pass   i   charAt(i)   revStr after appending
─────────────────────────────────────────────────────────────
 —     —       —       " "                      ← seed
 1    15      'n'      " n"
 2    14      'o'      " no"
 3    13      'z'      " noz"
 4    12      'a'      " noza"
 5    11      'm'      " nozam"
 6    10      'A'      " nozamA"
 7     9      ' '      " nozamA "
 8     8      's'      " nozamA s"
 9     7      'v'      " nozamA sv"
10     6      ' '      " nozamA sv "
11     5      'e'      " nozamA sv e"
12     4      'l'      " nozamA sv el"
13     3      'g'      " nozamA sv elg"
14     2      'o'      " nozamA sv elgo"
15     1      'o'      " nozamA sv elgoo"
16     0      'G'      " nozamA sv elgooG"
 —    -1       —       exit: -1 >= 0 is false
─────────────────────────────────────────────────────────────
```

## Reading the exit condition

```text
After pass 16:  i-- makes i = -1
Check:          -1 >= 0  →  false
Loop exits. charAt(-1) is never called.
```

Had the condition been `i > 0`, the loop would have exited after pass 15, and `'G'` would be missing.

## Console output

```text
 nozamA sv elgooG
```

versus the correct answer:

```text
nozamA sv elgooG
```

> [!warning] Proving the bug
> The difference is one leading space — visually almost undetectable in a terminal.
>
> Two ways to prove it exists:
>
> ```java
> System.out.println(revStr.length());              // 17, not 16
> System.out.println(revStr.equals("nozamA sv elgooG"));   // false
> System.out.println("[" + revStr + "]");           // [ nozamA sv elgooG]
> ```
>
> **Wrapping output in brackets is a genuinely useful debugging trick** whenever whitespace might be involved. What the eye cannot see, the brackets reveal.

## Object creation count

Because `String` is immutable, each pass creates a new object:

```text
" " → " n" → " no" → " noz" → ... → " nozamA sv elgooG"
 1     2      3       4              17 objects total
```

Sixteen of them are immediately garbage.

---

# 10. Visualize data where useful

## The input and the reading direction

```text
        "Google vs Amazon"
         G o o g l e _ v s _ A m a z o n
         0 1 2 3 4 5 6 7 8 9 ...      15
         ▲                             ▲
         │                             │
       stop here  ◀──────────────  start here
              reading direction
```

## The accumulator growing

```text
seed:    " "
   i=15  " " + 'n'  ──▶  " n"
   i=14  " n" + 'o' ──▶  " no"
   i=13  " no" + 'z'──▶  " noz"
                    ...
   i=0   " nozamA sv elgoo" + 'G' ──▶ " nozamA sv elgooG"
          ↑
      the stray space, carried all the way through
```

## Two strategies for reversing

```text
STRATEGY A (used here)          STRATEGY B
read BACKWARDS, APPEND          read FORWARDS, PREPEND

i=15 'n'  rev = "" + 'n'  = "n"      i=0  'G'  rev = 'G' + ""  = "G"
i=14 'o'  rev = "n" + 'o' = "no"     i=1  'o'  rev = 'o' + "G" = "oG"
i=13 'z'  rev = "no"+ 'z' = "noz"    i=2  'o'  rev = 'o' + "oG"= "ooG"
                ...                              ...

Both reach "nozamA sv elgooG"

MIXING THEM (forwards + append) just copies the input:
i=0 'G' → "G" ;  i=1 'o' → "Go" ;  i=2 'o' → "Goo"  ✗
```

## Immutability — objects created and discarded

```text
 " "  ──▶ garbage
  " n" ──▶ garbage
   " no" ──▶ garbage
    " noz" ──▶ garbage
             ...
              " nozamA sv elgooG"  ◀── revStr points here at the end

17 objects created, 16 discarded.
Each one copies every character built so far.
```

Compare with `StringBuilder`:

```text
StringBuilder:  ONE object, resized occasionally, characters written in place
```

## Loop control flow

```text
        i = length-1 = 15
            │
            ▼
      ┌─▶ i >= 0 ? ─── false ──▶ print revStr ──▶ done
      │     │ true
      │     ▼
      │  revStr = revStr + str1.charAt(i)
      │     │
      │     ▼
      │    i--
      └─────┘
```

---

# 11. Alternative ways to write the same logic

### The fix — an empty seed and a label

```java
String input = "Google vs Amazon";
String reversed = "";                      // ← empty string, not a space

for (int i = input.length() - 1; i >= 0; i--) {
    reversed = reversed + input.charAt(i);
}

System.out.println("Original: " + input);
System.out.println("Reversed: " + reversed);
```

Output:

```text
Original: Google vs Amazon
Reversed: nozamA sv elgooG
```

One character changed (`" "` → `""`), plus labels so the transformation is visible.

### Beginner-friendly variant — forwards and prepend

```java
String reversed = "";

for (int i = 0; i < input.length(); i++) {
    reversed = input.charAt(i) + reversed;      // note the ORDER
}
```

The loop is now the familiar forward shape, and the reversal comes from putting the new character **before** the accumulated result. Some people find this easier to reason about; it produces the identical answer.

Note that `input.charAt(i) + reversed` works because the **right** operand is a `String`, which still makes `+` concatenate.

### More efficient version — `StringBuilder`

```java
StringBuilder builder = new StringBuilder();

for (int i = input.length() - 1; i >= 0; i--) {
    builder.append(input.charAt(i));
}

String reversed = builder.toString();
```

> [!important] Why this is genuinely better
> `StringBuilder` is **mutable** — `append` modifies the existing object rather than creating a new one.
>
> | | `String +` | `StringBuilder` |
> |---|---|---|
> | objects created | one per character | one total |
> | characters copied | `1+2+3+...+n` ≈ `n²/2` | `n` |
> | time complexity | **`O(n²)`** | **`O(n)`** |
>
> For 16 characters this is irrelevant. For 100,000 characters, the `String +` version copies about **5 billion** characters and takes minutes; `StringBuilder` finishes instantly.
>
> **This is the most practically important lesson in the whole program.** Never build a long string with `+` in a loop.

### The one-liner

```java
String reversed = new StringBuilder(input).reverse().toString();
```

Read it left to right:

```text
new StringBuilder(input)   → a mutable copy of the text
        .reverse()         → reverses in place, returns the same builder
        .toString()        → converts back to an immutable String
```

This is what you would write in production. In an interview, write the loop first — the question is testing whether you can, not whether you know the library.

### Using `toCharArray` and swapping ends

```java
char[] chars = input.toCharArray();
int left = 0;
int right = chars.length - 1;

while (left < right) {
    char temp = chars[left];         // the swap from SwapNumbers!
    chars[left] = chars[right];
    chars[right] = temp;
    left++;
    right--;
}

String reversed = new String(chars);
```

This reverses **in place** using two pointers moving toward each other, with `O(1)` extra space. Notice the swap in the middle — exactly the three-line pattern from [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]], now applied to array slots instead of plain variables.

Interviewers who say "reverse it without extra space" are asking for this.

### Recursive version

```java
public static String reverse(String s) {
    if (s.isEmpty()) {
        return s;                                   // base case
    }
    return reverse(s.substring(1)) + s.charAt(0);   // recursive case
}
```

Elegant, but it creates a new `String` per call and uses `O(n)` stack space. Worth knowing; not worth shipping.

---

# 12. Common beginner mistakes

## Mistake 1 — seeding with `" "` instead of `""`

**Incorrect code**

```java
String revStr = " ";
```

**Why it is wrong**
A space is not the identity for concatenation. It stays at the front of the result permanently.

**What Java does**
Nothing — it compiles and runs. The output looks almost right.

**Correct code**

```java
String revStr = "";
```

**How to recognise it in future**
Check `revStr.length()` against `str1.length()` — they should match. Or print with brackets: `System.out.println("[" + revStr + "]")`. **Whitespace bugs are invisible until you make them visible.**

---

## Mistake 2 — looping forwards and appending

**Incorrect code**

```java
for (int i = 0; i < str1.length(); i++) {
    revStr = revStr + str1.charAt(i);
}
```

**Why it is wrong**
Reading forwards *and* appending to the end copies the string unchanged. Output: `Google vs Amazon`.

**Correct code**

```java
for (int i = str1.length()-1; i >= 0; i--) {      // backwards + append
    revStr = revStr + str1.charAt(i);
}
```

or

```java
for (int i = 0; i < str1.length(); i++) {          // forwards + prepend
    revStr = str1.charAt(i) + revStr;
}
```

**How to recognise it**
If the "reversed" output equals the input, you have applied the reversal twice or not at all. **Reverse the reading direction or the append side — exactly one of them.**

---

## Mistake 3 — starting at `length()` instead of `length()-1`

**Incorrect code**

```java
for (int i = str1.length(); i >= 0; i--) {
```

**Why it is wrong**
`charAt(16)` does not exist — valid indexes stop at `15`.

**What Java does**

```text
Exception in thread "main" java.lang.StringIndexOutOfBoundsException:
    index 16, length 16
```

**Correct code**

```java
for (int i = str1.length()-1; i >= 0; i--) {
```

**How to recognise it**
The message gives both the bad index and the real length. If they are equal, you forgot the `-1`.

---

## Mistake 4 — using `i > 0` instead of `i >= 0`

**Incorrect code**

```java
for (int i = str1.length()-1; i > 0; i--) {
```

**Why it is wrong**
Stops at index `1`, so the first character is never appended. Output: `nozamA sv elgoo` — missing the `G`.

**Correct code**

```java
for (int i = str1.length()-1; i >= 0; i--) {
```

**How to recognise it**
Compare lengths. If the result is one character short, check the loop's stopping condition.

---

## Mistake 5 — using `i++` in a backward loop

**Incorrect code**

```java
for (int i = str1.length()-1; i >= 0; i++) {      // ++ instead of --
```

**Why it is wrong**
`i` climbs from `15` upward, the condition `i >= 0` stays true forever, and `charAt(16)` throws on the second pass.

**Correct code**

```java
for (int i = str1.length()-1; i >= 0; i--) {
```

**How to recognise it**
A backward loop needs **all three** parts reversed: start high, condition `>=`, update `--`.

---

## Mistake 6 — using `length` instead of `length()`

**Incorrect code**

```java
for (int i = str1.length - 1; i >= 0; i--) {     // error: cannot find symbol
```

**Correct code**

```java
for (int i = str1.length() - 1; i >= 0; i--) {
```

**How to recognise it**
`String` → method with parentheses. Array → field without. `List` → `size()`.

---

## Mistake 7 — assigning a `char` to a `String`

**Incorrect code**

```java
String c = str1.charAt(i);      // error: incompatible types
```

**Why it is wrong**
`charAt` returns a `char` primitive, not a `String` object.

**Correct code**

```java
char c = str1.charAt(i);              // ✓ matching types
String c = "" + str1.charAt(i);       // ✓ concatenation converts
String c = String.valueOf(str1.charAt(i));   // ✓ explicit conversion
```

---

## Mistake 8 — forgetting that `String` methods return new values

**Incorrect code**

```java
str1.toUpperCase();               // result thrown away
System.out.println(str1);         // unchanged
```

**Correct code**

```java
str1 = str1.toUpperCase();
```

**How to recognise it**
**If a `String` method returns a `String`, you must use the return value.** `String` objects can never be modified in place.

---

## Mistake 9 — declaring the accumulator inside the loop

**Incorrect code**

```java
for (int i = str1.length()-1; i >= 0; i--) {
    String revStr = "";
    revStr = revStr + str1.charAt(i);
}
System.out.println(revStr);       // error: cannot find symbol
```

**Correct code**
Declare it before the loop.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| `String` indexing | do you know `charAt` and zero-based positions? |
| Backward loops | can you write all three parts correctly? |
| Immutability | **do you know `String +` in a loop is `O(n²)`?** |
| `char` vs `String` | do you know `charAt` returns a primitive? |
| Accumulator seeding | do you know the identity is `""`? |
| API knowledge | do you know `reverse()` is on `StringBuilder`, not `String`? |

> [!tip]
> The immutability question is the discriminator. Almost everyone can write the loop. Far fewer can explain that it creates `n` objects and copies `n²/2` characters — and that explanation is what separates a candidate who understands Java from one who has memorised a program.

## Likely follow-up questions

> **"Why is `String` concatenation in a loop inefficient?"**

Because `String` is immutable: every `+` allocates a new object and copies all existing characters. Building an `n`-character string copies about `n²/2` characters, making it `O(n²)`. `StringBuilder` is mutable and does it in `O(n)`.

> **"Do it without `StringBuilder` or `reverse()`."**

That is exactly this program. Show the backward loop.

> **"Do it in place, without extra memory."**

`toCharArray()` plus two pointers swapping from the ends inward — `O(1)` extra space. The swap is the `temp` pattern from `SwapNumbers`.

> **"What is the time complexity?"**

The loop is `O(n)` iterations, but each concatenation copies the accumulated result, so overall it is `O(n²)`. With `StringBuilder`, `O(n)`.

> **"What is the difference between `String`, `StringBuilder` and `StringBuffer`?"**

`String` is immutable. `StringBuilder` is mutable and not thread-safe (fast). `StringBuffer` is mutable and synchronised (slower, rarely needed).

> **"What happens with an empty string?"**

`length()` is `0`, so `i` starts at `-1` and the condition `-1 >= 0` fails immediately. The loop never runs and the seed is returned. **With the correct `""` seed, that is exactly right.** With the `" "` seed, it wrongly returns a space.

> **"How would you check if a string is a palindrome?"**

Reverse it and compare with `.equals()` — never `==`. Or use two pointers from the ends inward, which avoids building anything. Compare with [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]], which does the numeric equivalent.

> **"Why does `'a' + 'b'` print `195`?"**

Because `char` is a numeric type and neither operand is a `String`, so `+` performs addition on the Unicode values. `"" + 'a' + 'b'` gives `"ab"`.

> **"Reverse the words but not the letters — `'Google vs Amazon'` → `'Amazon vs Google'`."**

`split(" ")` into an array, walk it backwards, join with spaces. This combines this program with [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]].

---

# 14. Complexity

## Time complexity: `O(n²)` — not `O(n)`

This surprises people, so let us be precise.

The loop runs `n` times. But **each concatenation is not a constant-time operation.**

Because `String` is immutable, `revStr = revStr + ch` must:

1. Allocate a new `String` one character longer.
2. **Copy every character already accumulated** into it.
3. Append the new character.

So the copying cost grows each pass:

```text
pass 1:  copy 1 char
pass 2:  copy 2 chars
pass 3:  copy 3 chars
...
pass n:  copy n chars

total = 1 + 2 + 3 + ... + n  =  n(n+1)/2  ≈  n²/2
```

In beginner language:

> "The loop itself runs once per character, which sounds like `O(n)`. But each step rebuilds the entire result string from scratch, copying everything collected so far. Early steps copy a little, later steps copy a lot. Adding those up gives roughly `n²/2` character copies — so doubling the input **quadruples** the work. That is quadratic time, written `O(n²)`."

**What it means in practice:**

| Length | `String +` copies | `StringBuilder` copies |
|---|---|---|
| 16 | ~128 | 16 |
| 1,000 | ~500,000 | 1,000 |
| 100,000 | ~5,000,000,000 | 100,000 |

The last row is the difference between minutes and milliseconds.

> [!important] The lesson
> **Never build a long string with `+` inside a loop.** Use `StringBuilder`.
>
> Note that `+` outside a loop is completely fine — the compiler optimises `"a" + b + "c"` into a single `StringBuilder` automatically. It is the *loop* that defeats that optimisation, because each iteration is a separate statement.

## Space complexity: `O(n)`

The final string holds `n` characters. Intermediate strings become garbage but may briefly coexist, so peak usage can be higher.

The two-pointer `char[]` version uses `O(n)` for the array and `O(1)` extra — the minimum possible, since the output itself must be stored somewhere.

---

# 15. Edge cases

## Empty string

```java
String str1 = "";
```

- `length()` is `0`, so `i` starts at `-1`.
- `-1 >= 0` is `false` — the loop never runs.
- Result: the seed, unchanged.

```text
with seed "" :  prints ""            ✓ correct — reverse of nothing is nothing
with seed " ":  prints " "           ✗ a space appears from nowhere
```

> [!warning]
> This is the cleanest demonstration of the seeding bug. With an empty input, **the entire output is the bug** — a space that has no source in the data.
>
> **Testing with empty input is one of the fastest ways to expose a bad seed.**

## Single character

```java
String str1 = "A";
```

`i` starts at `0`, one pass appends `'A'`, then `i` becomes `-1` and the loop exits. Result: `"A"` (plus the stray space). Correct — a one-character string is its own reverse.

## A palindrome

```java
String str1 = "racecar";
```

Result: `"racecar"` (plus the stray space). The reversal works; the string simply happens to equal its own reverse. That is the basis of palindrome checking.

## String with spaces

That is this program's input. Spaces are ordinary characters — `charAt(6)` returns `' '` and it is appended like any other. **The reversal does not treat whitespace specially**, which is why `"Google vs Amazon"` becomes `"nozamA sv elgooG"` and not `"Amazon vs Google"`.

If you wanted to reverse word order instead, you would split on spaces first.

## Leading or trailing spaces in the input

```java
String str1 = "  hi  ";
```

Reversed: `"  ih  "`. The spaces move to the other end. Correct behaviour — reversal is symmetric and does not trim.

## Very long string

```java
String str1 = "x".repeat(100000);
```

Works, but the `O(n²)` copying makes it extremely slow — roughly 5 billion character copies. `StringBuilder` handles the same input instantly. **This is where the complexity discussion stops being theoretical.**

## `null` input

```java
String str1 = null;
```

`str1.length()` throws `NullPointerException` before the loop starts. There is no `null` check. Not possible here, since `str1` is initialised where it is declared, but relevant for any method receiving a `String` from elsewhere.

## Unicode characters beyond the basic range

```java
String str1 = "café";
```

Works — four characters, reversed to `"éfac"`.

> [!note] A genuine limitation worth knowing
> `charAt` returns a `char`, which is 16 bits. Characters beyond the Basic Multilingual Plane — many emoji, some scripts — are stored as **two** `char` values called a surrogate pair.
>
> Reversing character by character splits those pairs and corrupts them:
>
> ```java
> "a🙂b"  →  reversed by charAt  →  "b??a"   (the emoji is broken)
> ```
>
> The correct approach uses `codePoints()`, or simply `new StringBuilder(s).reverse()`, which handles surrogate pairs properly.
>
> You will not be asked this in most interviews, but knowing it exists shows genuine depth — and it is another reason to prefer the library method in real code.

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Print a piece of text backwards.

        ↓

What data do I have?
Text → String

        ↓

What do I need to produce?
Text → String, same characters, opposite order

        ↓

Is there a single method that does this?
Check String's method list... no reverse().
(Because String is immutable, it cannot
 reverse itself — that is why reverse()
 lives on StringBuilder instead.)
So I build it myself.

        ↓

How is text assembled in Java?
Concatenation, two pieces at a time.

        ↓

So this is an ACCUMULATOR problem —
build the answer one character per step.

        ↓

What type is the accumulator?
I am building text → String

        ↓

What is its seed?
What can I concatenate with that changes nothing?
"" + "abc" = "abc"  →  the EMPTY string
(this program uses " " — that is the bug)

        ↓

In what order do I visit the characters?
I want the LAST character to come out FIRST,
so I read from the end toward the start.

        ↓

Where is the end?
Indexes run 0 to length()-1
→ start at str1.length()-1 = 15

        ↓

Where do I stop?
Index 0 must be INCLUDED → i >= 0
(i > 0 would drop the first character)

        ↓

How do I move?
Toward smaller indexes → i--
(all three loop parts reverse together)

        ↓

How do I read one character?
str1.charAt(i)  →  returns a char, not a String

        ↓

How do I add it on?
revStr = revStr + charAt(i)
The String on the left makes + concatenate,
and converts the char automatically.

        ↓

Trace it:
" " → " n" → " no" → ... → " nozamA sv elgooG"

        ↓

Compare with the expected answer:
"nozamA sv elgooG"
There is an extra leading space.
→ the seed is wrong. Change " " to "".

        ↓

Sanity-check the edges:
empty input → loop never runs → returns the seed
             (which is exactly why the seed matters)
one char    → returns itself  ✓

        ↓

Is it efficient?
Each + copies everything so far → O(n²).
Fine for 16 characters.
For long text, use StringBuilder.
```

> [!important] Two habits demonstrated here
> **1. Compare your output against the expected answer character by character.** The bug was one invisible space — spotting it required checking, not glancing.
>
> **2. Ask what the empty input does.** For accumulator programs, empty input returns the seed *unchanged* — which makes it the perfect test for whether the seed is right.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `String str1 = "Google vs Amazon";` | "Make a text box called `str1` and put this phrase in it." |
| `String revStr = " ";` | "Make a text box called `revStr` to collect the answer, and start it holding a single space — which is a mistake, because that space will end up at the front of the result." |
| `for(int i=str1.length()-1; ...)` | "Start a counter at the position of the *last* character — one less than the number of characters, because positions start at zero." |
| `... i>=0 ...` | "Keep going as long as the counter is still zero or more, so the very first character is included." |
| `... i--)` | "After each pass, move one position toward the front." |
| `revStr = revStr + str1.charAt(i);` | "Take whatever text I have collected so far, stick the character at position `i` on the end of it, and store the whole new piece of text back into `revStr`." |
| `System.out.println(revStr);` | "Print the collected text, then move to a new line." |

The loop in one sentence:

> **"Walk from the last character to the first, and stick each one onto the end of the result as you go."**

And the whole program:

> **"Start with an empty result, read the text from back to front adding each character to the result, then print it."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| `String` | an immutable object holding a sequence of characters, indexed from `0` |
| **immutability** | a `String` never changes; every "modifying" method returns a **new** one |
| `char` | a primitive holding exactly one character; secretly a number |
| accumulator | a variable declared before a loop that builds up an answer |
| identity for concatenation | `""` — the empty string, **not** `" "` |
| backward loop | start at `length-1`, condition `>= 0`, update `--` — all three reverse |
| `StringBuilder` | the **mutable** companion to `String`; `O(n)` instead of `O(n²)` |
| `==` vs `.equals()` | `==` compares references; `.equals()` compares contents |

### Important methods

| Method | Owner | Accepts | Returns |
|---|---|---|---|
| `length()` | `String` | — | `int` — **with** parentheses |
| `charAt(int)` | `String` | an index from `0` | **`char`**, not `String` |
| `toCharArray()` | `String` | — | `char[]` |
| `substring(int)` | `String` | a start index | a **new** `String` |
| `append(char)` | `StringBuilder` | a `char` | the builder — **modified in place** |
| `reverse()` | `StringBuilder` | — | the builder, reversed |
| `toString()` | `StringBuilder` | — | a `String` |
| `println(String)` | `PrintStream` | text | `void` |

There is deliberately **no** `String.reverse()` — a `String` cannot change itself.

### Important syntax

| Syntax | Meaning |
|---|---|
| `""` | the empty string — length `0`, the concatenation identity |
| `" "` | a string containing one space — length `1` |
| `'c'` | a `char` literal — exactly one character |
| `"c"` | a `String` literal — any number of characters |
| `str.length()` | character count — a **method** |
| `arr.length` | element count — a **field** |
| `str.charAt(i)` | the character at position `i` |
| `i--` | subtract one — the mirror of `i++` |
| `i >= 0` | include index `0`; `i > 0` would skip it |
| `+` | concatenation when a `String` is involved; **addition** between two `char`s |

### Main interview concept

> **`String` is immutable, so `result = result + ch` inside a loop creates a new object every pass and copies everything accumulated so far — making it `O(n²)`, not `O(n)`.** Use `StringBuilder` for anything longer than a few characters. This single fact explains why `reverse()` lives on `StringBuilder` and not on `String`.

### Main lesson for code reading

> **Read the accumulator's seed before you read the loop — it tells you what the program returns for empty input.**
>
> ```text
> String rev = "";     → empty input returns ""     ✓ correct
> String rev = " ";    → empty input returns " "    ✗ a space from nowhere
> ```
>
> And when reversing, check that **exactly one** thing is reversed — either the reading direction or the append side, never both and never neither. Reversing twice is the same as not reversing at all, and the output looks so ordinary you may not notice.

---

### Related notes

- [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]] — `String` methods, chaining, and `split()`
- [[Java_PalindromeOrNot_Code_Reading_Explanation_Obsidian|PalindromeOrNot]] — reversing a *number* with `%` and `/` instead of `charAt`
- [[Java_FindDuplicateChar_Code_Reading_Explanation_Obsidian|FindDuplicateChar]] — `toCharArray()` and comparing `char`s with `==`
- [[Java_SumOfArrayEle_Code_Reading_Explanation_Obsidian|SumOfArrayEle]] — the same accumulator skeleton with `int` and `0`
- [[Java_SwapNumbers_Code_Reading_Explanation_Obsidian|SwapNumbers]] — the `temp` swap used by the two-pointer reversal
