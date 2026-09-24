---
title: Java Code Reading — FindDuplicateChar
tags:
  - java
  - interview-programs
  - code-reading
  - strings
  - arrays
  - loops
aliases:
  - FindDuplicateChar Explained
  - Find Duplicate Characters in a String
---

# Java Code Reading — FindDuplicateChar Explained for a Fresh Java Programmer

> [!note]
> This is the first program in the folder with a **nested loop** — a loop inside another loop — and that single structural change is the most important thing here. It takes the program from `O(n)` to `O(n²)`, and understanding *why* is one of the highest-value concepts a beginner can learn.
>
> It also introduces `char[]`, the `break` statement, and a genuinely correct use of `==` on characters (compare with [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]], where `==` on `String`s would have been a bug).
>
> There are two quirks worth noting up front: `count` is incremented but **never printed**, and `new String(...)` creates an object that a plain literal would have provided for free. Neither breaks the program; both are worth understanding.
>
> Keep the reading loop running:
>
> **What do I have? → What do I want? → Must I compare every item with every other? → What stops the comparison early? → What does the result actually mean?**

---

# 1. Complete program first

```java
package FrequentInterviewPrograms;

public class FindDuplicateChar {

    public static void main(String[] args) {

        String str = new String(" ammar.S.S");

        int count = 0;

        char[] chars = str.toCharArray();

        System.out.println("Duplicate characters in the string are : ");

        for(int i=0; i<str.length();i++)
        {
            for(int j= i+1; j<str.length();j++)
            {
                if(chars[i] == chars[j])
                {
                    System.out.println(chars[j]);

                    count++;

                    break;
                }
            }
        }
    }
}
```

The executable logic:

```java
String str = new String(" ammar.S.S");
int count = 0;
char[] chars = str.toCharArray();

for (int i = 0; i < str.length(); i++) {
    for (int j = i+1; j < str.length(); j++) {
        if (chars[i] == chars[j]) {
            System.out.println(chars[j]);
            count++;
            break;
        }
    }
}
```

With this input the program prints `a`, `m`, `.`, `S`.

---

# 2. What problem is this program solving?

In plain language:

> I have some text. Print the characters that appear more than once.

```text
" ammar.S.S"
  ↑↑  ↑ ↑↑↑
'a' appears twice
'm' appears twice
'.' appears twice
'S' appears twice
```

### What do we know?

- One piece of text.
- A character is a duplicate if it appears somewhere else in the same text.

### What do we need?

- The duplicated characters, printed.

### What transformations are required?

This is a **filter** — many characters in, some characters out. But it is a filter with a twist that changes everything.

> [!important] The test depends on the *other* items
> In [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]], the test was `arr[i] % 2 == 0` — you could decide about each element **by looking at it alone**.
>
> Here you cannot. "Is this character a duplicate?" cannot be answered by looking at that character. You must ask: *does it appear anywhere else?* — which means comparing it against **every other character**.
>
> ```text
> SELF-CONTAINED TEST            RELATIONAL TEST
> ───────────────────            ───────────────────
> is arr[i] even?                is chars[i] duplicated?
>   look at arr[i]                 look at chars[i]
>   decide                         AND every other character
>                                  then decide
>         ↓                              ↓
>   one loop → O(n)              two loops → O(n²)
> ```
>
> **When a test relates one item to other items, you need a second loop.** That is the structural signal, and recognising it is what tells you the cost before you have written a line.

### How would you do this by hand?

The algorithm falls straight out of the manual procedure:

> Take the first character. Compare it with every character after it. If you find a match, it is a duplicate — report it and move on to the next starting character.

Two loops, exactly:

| Human step | Java |
|---|---|
| "take each character in turn" | outer loop, `i` |
| "compare with every character **after** it" | inner loop, `j = i+1` |
| "if they match" | `if (chars[i] == chars[j])` |
| "report it" | `println(chars[j])` |
| "move on to the next starting character" | `break` |

---

# 3. Understand the program from the top

## `package FrequentInterviewPrograms;`

Declares the grouping, matching the folder. First statement in the file.

## `public class FindDuplicateChar`

- `public` — usable from anywhere.
- `class` — declares a class.
- `FindDuplicateChar` — `PascalCase`, matching `FindDuplicateChar.java`.

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

`String`, `char[]`, `int`, `System` — primitives, arrays and `java.lang`, so nothing to import.

## Variables

| Variable | Type | Role |
|---|---|---|
| `str` | `String` | the input text |
| `count` | `int` | a tally of duplicates found — **never printed** |
| `chars` | `char[]` | the text as an array, for indexed access |
| `i` | `int` | outer counter — the character being checked |
| `j` | `int` | inner counter — the character being compared against |

> [!note] `count` is written but never read
> `count++` runs four times, and then the program ends without ever printing it.
>
> This is **dead code** — it has no effect on the output. It is harmless, but it is the kind of thing a reviewer would flag, because it suggests either a missing line or a leftover from an earlier version.
>
> The fix is one line at the end:
> ```java
> System.out.println("Total duplicates found: " + count);
> ```
>
> IntelliJ actually detects this and greys out the variable with the hint *"Variable 'count' is never used"*. **Learning to notice your IDE's greyed-out code is a genuinely useful habit** — it usually means something is missing.

## Structure — two levels of loop

```text
for i ...                      ← outer: each character
    for j = i+1 ...            ← inner: each LATER character
        if chars[i]==chars[j]  ← compare
            print, count, break
```

The `j = i+1` is the detail that makes this work correctly. We come back to it in section 4.5.

---

# 4. Line-by-line explanation

## 4.1 — `String str = new String(" ammar.S.S");`

```java
String str = new String(" ammar.S.S");
```

**What is this?**
Creating a `String` containing the text `" ammar.S.S"`.

**Why do we need it?**
It is the program's input.

**Note the leading space**

```text
" ammar.S.S"
 ↑
 a space, before the 'a'
```

That space is character `0`. It is easy to miss, and it matters for the trace in section 9 — it shifts every index by one.

**Why `new String(...)` rather than just `" ammar.S.S"`?**

Honestly: there is no good reason. Both produce a usable `String`, but they differ in an important way.

> [!warning] `new String(...)` is redundant — and slightly harmful
> ```java
> String a = "hello";                  // uses the string pool — no new object
> String b = new String("hello");      // forces a SEPARATE object on the heap
> ```
>
> Java maintains a **string pool**: identical literals share one object. Writing `"hello"` reuses the pooled instance; writing `new String("hello")` deliberately creates a second copy of the same text.
>
> The consequence:
> ```java
> String a = "hello";
> String b = "hello";
> String c = new String("hello");
>
> a == b          // true  — the SAME pooled object
> a == c          // FALSE — different objects
> a.equals(c)     // true  — same contents
> ```
>
> So `new String(...)` wastes memory *and* creates the classic `==` trap. IntelliJ flags it with *"Unnecessary 'new String()' call"*.
>
> **The correct form here is simply:**
> ```java
> String str = " ammar.S.S";
> ```
>
> It makes no difference to this program's output, because the program never compares `String`s with `==` — it compares `char`s. But the habit is worth correcting.

**Why the name `str`?**
Weak — it says "this is a string", which the type already told us. `text` or `input` would say more.

---

## 4.2 — `int count = 0;`

```java
int count = 0;
```

**What is this?**
A counting accumulator, seeded at `0`.

**Why do we need it?**
Nominally, to tally how many duplicates were found. As noted, the tally is never used — but the *reasoning* behind it is standard: declare before the loop so it survives every pass, seed at `0` because none have been found yet.

See [[Java_PrimeOrNot_Code_Reading_Explanation_Obsidian|PrimeOrNot]] for the same counting pattern actually put to use.

---

## 4.3 — `char[] chars = str.toCharArray();`

```java
char[] chars = str.toCharArray();
```

**What is this?**
Converting the `String` into an array of individual characters.

**Why do we need it?**
To compare characters by position, we need indexed access to them.

**What is `toCharArray()`?**

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.lang.String` |
| **Why can we call it?** | `str` is a `String` object |
| **What does it accept?** | nothing |
| **What does it return?** | **`char[]`** — a new array |
| **Why does `char[] chars` match?** | because the return type is `char[]` |

**How would a beginner know the return type?**

The same habit that made `String[] words = sentence.split(" ")` correct in [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]]:

```text
"I want the characters individually."
        ↓
Type  str.  in IntelliJ and read the list
        ↓
toCharArray() — hover shows:  public char[] toCharArray()
        ↓
The return type is char[]
        ↓
So the variable must be char[]:
    char[] chars = str.toCharArray();
```

**Check the return type before declaring the variable.** That ordering is the skill.

**What does Java do when it reaches this line?**

Creates a **new** array on the heap and copies every character into it.

```text
   str                       chars
  ┌─────┐                   ┌─────┐
  │ ref │                   │ ref │
  └──┬──┘                   └──┬──┘
     ▼                          ▼
"  ammar.S.S"        ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
  (immutable)        │ ' '│'a'│'m'│'m'│'a'│'r'│'.'│'S'│'.'│'S'│
                     └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
                       0   1   2   3   4   5   6   7   8   9
```

> [!important] This is a copy, not a view
> `toCharArray()` allocates a new array and copies the characters into it. Modifying `chars` does **not** modify `str` — which is guaranteed anyway, since `String` is immutable.
>
> That copy costs `O(n)` time and `O(n)` memory. For this program it is a fine trade: it buys convenient `chars[i]` indexing.
>
> **Is it necessary?** No. `str.charAt(i)` gives the same character without any array:
> ```java
> if (str.charAt(i) == str.charAt(j)) { ... }
> ```
> Both are correct. The array version reads slightly more cleanly with two indexes; the `charAt` version avoids the copy. Neither is wrong.

**Why `char[]` and not `String[]`?**

We want individual **characters**, not substrings. A `char` is a primitive holding exactly one character; a `String` is an object holding any number.

```java
char c = chars[0];         // ✓ a primitive character
String s = chars[0];       // ✗ incompatible types
```

---

## 4.4 — `System.out.println("Duplicate characters in the string are : ");`

```java
System.out.println("Duplicate characters in the string are : ");
```

A header, printed **once**, outside both loops. Inside, it would repeat on every comparison.

**Position relative to `{ }` decides how often something runs.**

---

## 4.5 — the outer loop

```java
for(int i=0; i<str.length();i++)
```

**What is this?**
A loop over every character position, choosing the character to check.

**The three sections**

```text
for ( int i = 0 ; i < str.length() ; i++ )
          │            │               │
          │            │               └── after each pass
          │            └────────────────── before each pass
          └─────────────────────────────── once, at the start
```

**Why `i = 0`?**
`i` is an **index** into a sequence, and both `String` and array positions start at `0`.

**Why `str.length()` with parentheses?**

> [!warning] A subtle inconsistency in this line
> The loop bounds use `str.length()` — the **`String`'s** length — but the body indexes `chars[i]` — the **array**.
>
> That works here, because `toCharArray()` produces an array of exactly the same length. But it mixes two different objects in one loop, which is slightly confusing to read.
>
> More consistent:
> ```java
> for (int i = 0; i < chars.length; i++) {      // array field, no parentheses
> ```
>
> And note the two spellings again:
>
> | Type | Form |
> |---|---|
> | `String` | `str.length()` — a **method** |
> | array | `chars.length` — a **field** |
> | `List` | `list.size()` — a different method |
>
> Also worth knowing: `str.length()` is called on **every** iteration of both loops — about 55 times here. `String.length()` is cheap (it returns a stored field), so this costs nothing measurable. But for an expensive bound, hoisting it into a variable before the loop is the standard fix.

**Is the bound correct?**

Yes. `str.length()` is `10`, so `i < 10` visits indexes `0..9` — all ten characters. This is the idiomatic form, unlike the `length - 1` bug in [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]].

---

## 4.6 — the inner loop

```java
for(int j= i+1; j<str.length();j++)
```

**What is this?**
A loop over every character **after** the current one.

**Why does `j` start at `i+1` rather than `0`?**

This is the most important detail in the program, and it does two jobs at once.

> [!important] Why `j = i + 1`
> **Reason 1 — avoid comparing a character with itself.**
>
> If `j` started at `0`, then when `j == i` the test `chars[i] == chars[j]` would compare a character with itself and always be `true`. **Every** character would be reported as a duplicate.
>
> **Reason 2 — avoid checking the same pair twice.**
>
> Comparing `chars[1]` with `chars[4]` is the same question as comparing `chars[4]` with `chars[1]`. Starting at `i+1` means each unordered pair is examined exactly once.
>
> ```text
> j = 0            j = i+1
> ───────────      ───────────
> (0,0) ✗ self     (0,1) (0,2) (0,3) ...
> (0,1)            (1,2) (1,3) ...
> (1,0) ← dup      (2,3) ...
> (1,1) ✗ self
> (1,2)
> ...
> n² comparisons   n(n-1)/2 comparisons
> ```
>
> The `i+1` start halves the work **and** fixes the self-comparison bug. One small expression, two problems solved.
>
> This "compare each pair once" pattern appears constantly: finding duplicates, checking for collisions, computing distances between points, detecting overlapping intervals. **Recognise `j = i + 1` and you immediately know the loop is examining pairs.**

**Why `j < str.length()`?**
The inner loop must be able to reach the last character. Same bound as the outer loop.

**What happens on the last outer pass?**

When `i` is `9` (the last index), `j` starts at `10`, and `10 < 10` is `false` — the inner loop body **never runs**.

That is correct: there are no characters after the last one, so there is nothing to compare against. **The inner loop naturally does nothing when there is nothing to do**, with no special case needed.

---

## 4.7 — `if(chars[i] == chars[j])`

```java
if(chars[i] == chars[j])
```

**What is this?**
The comparison that detects a match.

**Why `==` and not `.equals()`?**

> [!important] `==` is correct here — and knowing why matters
> `chars[i]` and `chars[j]` are **`char` primitives**, not objects. For primitives, `==` compares the actual values, which is exactly what we want.
>
> ```java
> char a = 'x', b = 'x';
> a == b                  // true — compares values  ✓
> a.equals(b)             // ✗ won't compile: char cannot be dereferenced
>
> String s1 = new String("x"), s2 = new String("x");
> s1 == s2                // false — compares REFERENCES  ✗
> s1.equals(s2)           // true — compares contents  ✓
> ```
>
> **The rule: primitives use `==`; objects use `.equals()`.**
>
> This is one of the most common interview questions in Java, and this program is a good illustration of the *correct* use. Contrast it with the trap described in section 4.1: had the program compared `String`s with `==`, the `new String(...)` on line 1 would have made it fail.
>
> A `char` is really a small number (its Unicode code point), so `==` on two `char`s is ordinary numeric comparison — `'a' == 'a'` is `97 == 97`.

**Case sensitivity**

```text
'S' is 83
's' is 115
'S' == 's'  →  false
```

So `'S'` and `'s'` are **different** characters. This input contains two uppercase `'S'`, which do match each other. Section 11 shows how to make the comparison case-insensitive if you want that.

**What does the condition produce?**
A `boolean`. If `true`, the block runs.

---

## 4.8 — `System.out.println(chars[j]);`

```java
System.out.println(chars[j]);
```

**What is this?**
Printing the duplicated character.

**Which overload runs?**
`println(char)` — there is a specific overload for `char`, which prints the character itself.

> [!warning] `println(char)` vs `println(int)`
> ```java
> char c = 'a';
> System.out.println(c);           // prints  a       ← println(char)
> System.out.println(c + 0);       // prints  97      ← the + makes it an int!
> System.out.println((int) c);     // prints  97
> ```
>
> Because `char` is numerically compatible with `int`, any arithmetic on it produces an `int` — and then the `int` overload prints the code point instead of the character.
>
> Here `chars[j]` is passed directly with no arithmetic, so the `char` overload runs and the character prints correctly.
>
> The same trap in concatenation:
> ```java
> System.out.println('a' + 'b');        // 195  — numeric addition!
> System.out.println("" + 'a' + 'b');   // ab   — concatenation
> ```

**Why print `chars[j]` and not `chars[i]`?**

They are equal at this point — the `if` just confirmed it — so the printed character is the same either way.

Conceptually `chars[i]` is arguably clearer: *"the character I was checking turned out to be a duplicate."* But the output is identical.

---

## 4.9 — `count++;`

```java
count++;
```

Increments the tally. As established, the tally is never printed, so this line has no observable effect.

Shorthand for `count = count + 1`.

---

## 4.10 — `break;`

```java
break;
```

**What is this?**
An instruction to leave the **innermost** loop immediately.

**Why do we need it?**

Once a match is found, the question *"is `chars[i]` duplicated?"* has been answered — **yes**. Continuing to search would find further copies and print the same character again.

**What happens without it?**

```text
Input: "aaa"
       indexes 0,1,2

WITHOUT break:
  i=0: j=1 match → print 'a'
       j=2 match → print 'a'      ← printed twice for i=0
  i=1: j=2 match → print 'a'
  Output: a a a

WITH break:
  i=0: j=1 match → print 'a', break
  i=1: j=2 match → print 'a', break
  i=2: (inner loop does not run)
  Output: a a
```

> [!important] `break` exits only the innermost loop
> This is the detail beginners most often get wrong.
>
> ```text
> for (i ...) {           ← break does NOT exit this
>     for (j ...) {       ← break exits THIS one
>         break;
>     }
>     // execution continues HERE
> }
> ```
>
> After `break`, control resumes at the outer loop's update (`i++`) and the outer loop carries on normally.
>
> That is exactly what we want here: stop searching for *more* copies of this character, but keep checking the *remaining* characters.
>
> To exit both loops you would need a labelled break:
> ```java
> outer:
> for (int i = ...) {
>     for (int j = ...) {
>         if (...) break outer;      // exits BOTH
>     }
> }
> ```
> Labelled breaks are legal but rare — usually a sign the logic belongs in its own method with a `return`.

**Does `break` fully fix the duplicate-reporting problem?**

No — and this is worth being precise about. `break` stops *one* starting position from reporting the same character twice, but it does not stop *different* starting positions from reporting it.

```text
Input: "aaa"
Output with break: a a       ← still printed twice
```

For a character appearing `k` times, it is printed `k-1` times. Section 15 covers this properly, and section 11 shows the fix.

---

# 5. How to think like the programmer

```text
Requirement
"Print the characters that appear more than once"
        ↓
What data do I have?
Text → String
        ↓
What do I need to produce?
Some of its characters → a FILTER
        ↓
What is the test for one character?
"Does it appear anywhere else?"
        ↓
Can I answer that by looking at one character alone?
NO — I must compare it against the others.
        ↓
A test that relates items to OTHER items
needs a second loop.
→ nested loops → O(n²)
        ↓
How do I access characters by position?
str.charAt(i), or convert once:
char[] chars = str.toCharArray();
(check the return type → char[])
        ↓
Outer loop: which character am I checking?
for (int i = 0; i < length; i++)
        ↓
Inner loop: what do I compare it against?
Every LATER character.
Why later, not all?
  - j = i would compare with itself → always true
  - j < i would re-check pairs already done
→ for (int j = i+1; j < length; j++)
        ↓
How do I compare two characters?
They are primitives → use ==
(.equals() would not even compile)
        ↓
What do I do on a match?
Print it — and stop searching this character,
because the question is already answered.
→ break
        ↓
Does break exit both loops?
No — only the inner one. That is what I want.
        ↓
Trace " ammar.S.S":
prints a, m, ., S   ✓
        ↓
But wait — what about "aaa"?
prints a twice.
The program reports a character once per
EARLIER occurrence, not once overall.
```

> [!important] The pair-comparison skeleton
> ```java
> for (int i = 0; i < n; i++) {
>     for (int j = i + 1; j < n; j++) {
>         if (RELATION(data[i], data[j])) {
>             ACT();
>         }
>     }
> }
> ```
>
> | Goal | RELATION |
> |---|---|
> | find duplicates | `data[i] == data[j]` |
> | find pairs summing to a target | `data[i] + data[j] == target` |
> | detect any collision | `overlaps(data[i], data[j])` |
> | find the closest pair | `distance(i,j) < best` |
>
> Every one of these is `O(n²)`, and every one starts its inner loop at `i+1`. **Seeing `j = i+1` should immediately tell you: this loop examines each pair once, and it costs `O(n²)`.**

---

# 6. Deep explanation of important Java concepts used

## `char`

A **primitive** holding exactly one character, written with single quotes.

```java
char c = 'a';
char space = ' ';
char dot = '.';
```

Internally a 16-bit unsigned number — a Unicode code point:

```text
' ' = 32
'.' = 46
'S' = 83
'a' = 97
'm' = 109
```

That is why `==` works on `char`s: it is numeric comparison.

| | `char` | `String` |
|---|---|---|
| quotes | `'a'` | `"a"` |
| kind | primitive | object |
| holds | exactly one character | zero or more |
| compare with | `==` | `.equals()` |
| has methods? | no | yes |

## `char[]`

An array of characters — fixed length, zero-indexed, holding primitives.

```text
        chars.length = 10
   ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
   │' '│'a'│'m'│'m'│'a'│'r'│'.'│'S'│'.'│'S'│
   └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
     0   1   2   3   4   5   6   7   8   9
```

Unlike a `String`, a `char[]` is **mutable** — you can assign to `chars[0]`. That is why the in-place string reversal in [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]] converts to `char[]` first.

## Nested loops

```java
for (int i = ...) {          // outer
    for (int j = ...) {      // inner
        // body
    }
}
```

The inner loop runs **completely** for each single pass of the outer loop.

```text
i=0:  j runs 1,2,3,...,9
i=1:  j runs 2,3,...,9
i=2:  j runs 3,...,9
...
```

> [!important] Nested loops multiply
> ```text
> one loop over n items      →  n passes        →  O(n)
> nested loops over n items  →  ~n × n passes   →  O(n²)
> ```
>
> That multiplication is why nesting is the single biggest performance decision a beginner makes:
>
> | `n` | one loop | nested loops |
> |---|---|---|
> | 10 | 10 | ~50 |
> | 1,000 | 1,000 | ~500,000 |
> | 1,000,000 | 1,000,000 | ~500,000,000,000 |
>
> The last row is the difference between instant and effectively never. **Whenever you write a loop inside a loop, pause and ask whether you can avoid it** — section 11 shows how, using a `Set`.

## The `break` statement

```java
break;
```

Exits the **innermost** enclosing loop immediately. Execution resumes after that loop's closing brace.

| Statement | Effect |
|---|---|
| `break` | leave the innermost loop entirely |
| `continue` | skip the rest of this pass, go to the next iteration |
| `return` | leave the whole **method** |
| `break label` | leave the labelled loop (rare) |

## `==` on primitives vs `.equals()` on objects

```java
char a = 'x', b = 'x';
a == b                    // true — values

String s1 = new String("x"), s2 = new String("x");
s1 == s2                  // false — references
s1.equals(s2)             // true — contents
```

**Primitives use `==`; objects use `.equals()`.** This program uses `==` on `char`s, which is correct.

## The string pool

```java
String a = "hello";               // pooled — reused
String b = "hello";               // the SAME object as a
String c = new String("hello");   // a deliberate new object
```

Java interns string literals so identical ones share memory. `new String(...)` opts out of that, wasting memory and breaking `==`. Avoid it.

## `toCharArray()`

```java
public char[] toCharArray()
```

Returns a **new** array containing the `String`'s characters. `O(n)` time and space.

## Counting accumulator

```java
int count = 0;      // before the loops
count++;            // inside, when a condition holds
// (and should be read afterwards — this program forgets to)
```

---

# 7. Why this syntax?

## `j = i+1` vs `j = 0` vs `j = i`

| Start | Effect |
|---|---|
| `j = 0` | re-checks every pair twice, and compares each character with itself → everything reported as duplicate |
| `j = i` | compares each character with itself → same bug |
| **`j = i+1`** | **each pair once, no self-comparison** ✓ |

## `==` vs `.equals()`

`==` for `char` primitives (correct here); `.equals()` for objects.

Writing `chars[i].equals(chars[j])` gives `char cannot be dereferenced`.

## `break` vs `continue` vs `return`

| Keyword | Leaves |
|---|---|
| `break` | the innermost loop |
| `continue` | just this iteration |
| `return` | the whole method |

Here `break` is right: stop searching for more copies of *this* character, but keep processing the rest.

## `str.length()` vs `chars.length`

| Type | Form |
|---|---|
| `String` | `str.length()` — method |
| array | `chars.length` — field |

Both are `10` here. Using the array's field would be more consistent with `chars[i]`.

## `'c'` vs `"c"`

Single quotes make a `char`; double quotes make a `String`. `chars[i]` is a `char`.

## `new String("x")` vs `"x"`

The literal is better in every way. `new String` forces a redundant object.

## `println(char)` vs `println(int)`

Passing a `char` directly prints the character. Any arithmetic on it produces an `int` and prints the code point.

---

# 8. Method discovery

## `toCharArray`

| Question | Answer |
|---|---|
| **Which class owns it?** | `java.lang.String` |
| **Why can we call it?** | `str` is a `String` object |
| **Accepts** | nothing |
| **Returns** | `char[]` |
| **Why does `char[] chars` match?** | the return type is `char[]` |
| **Cost** | `O(n)` — it copies |

## `length`

| Question | Answer |
|---|---|
| **Owner** | `java.lang.String` |
| **Returns** | `int` |
| **Why parentheses?** | it is a method; only arrays have a `length` field |

## `println(char)`

One of many overloads on `PrintStream`. Java picks it because the argument is a `char`.

## How you would discover a better approach

The most valuable discovery here is not a method — it is a **data structure**.

```text
"I am comparing every character with every other character.
 That is O(n²). Is there a faster way?"
        ↓
What am I really asking each time?
"Have I seen this character before?"
        ↓
Is there a structure that answers "have I seen this?" quickly?
        ↓
Search: "java check if already seen collection"
        ↓
Find: java.util.Set
        ↓
Read the Javadoc:
  Set.add(E) → returns boolean
  "returns false if the element was already present"
        ↓
That single return value IS the duplicate test!
        ↓
Set<Character> seen = new HashSet<>();
if (!seen.add(c)) {
    // c was already there → duplicate
}
        ↓
HashSet.add is O(1) on average
→ the whole algorithm becomes O(n)
```

| Question | Answer |
|---|---|
| **Which interface?** | `java.util.Set` |
| **Common implementation** | `java.util.HashSet` |
| **`add(E)` accepts** | one element |
| **`add(E)` returns** | `boolean` — **`false` if it was already present** |
| **Cost of `add`** | `O(1)` average |

> [!important] Read return types for hidden gifts
> `Set.add()` returning a `boolean` is easy to overlook — most people call `add` for its side effect and ignore the result.
>
> But that `boolean` answers exactly the question this program is asking. Using it turns an `O(n²)` algorithm into an `O(n)` one, in fewer lines.
>
> **When a method returns something you did not expect it to, read the Javadoc to find out why.** The return value is often the whole point.

---

# 9. Trace the program with real values

```text
Input:  " ammar.S.S"      ← note the leading space
Length: 10
```

## The characters, indexed

```text
index:   0    1    2    3    4    5    6    7    8    9
char:   ' '  'a'  'm'  'm'  'a'  'r'  '.'  'S'  '.'  'S'
```

Which characters repeat?

```text
' ' → index 0 only          (unique)
'a' → indexes 1, 4          (duplicate)
'm' → indexes 2, 3          (duplicate)
'r' → index 5 only          (unique)
'.' → indexes 6, 8          (duplicate)
'S' → indexes 7, 9          (duplicate)
```

## Outer-loop walkthrough

```text
─────────────────────────────────────────────────────────────────────────
i   chars[i]   inner loop j = i+1 .. 9                    Result
─────────────────────────────────────────────────────────────────────────
0    ' '       j=1'a' j=2'm' j=3'm' j=4'a' j=5'r'
               j=6'.' j=7'S' j=8'.' j=9'S'                no match
─────────────────────────────────────────────────────────────────────────
1    'a'       j=2 'm' no
               j=3 'm' no
               j=4 'a' MATCH → print 'a', count=1, BREAK   printed a
─────────────────────────────────────────────────────────────────────────
2    'm'       j=3 'm' MATCH → print 'm', count=2, BREAK   printed m
─────────────────────────────────────────────────────────────────────────
3    'm'       j=4'a' j=5'r' j=6'.' j=7'S' j=8'.' j=9'S'  no match
               (the other 'm' is at index 2, which is BEFORE 3)
─────────────────────────────────────────────────────────────────────────
4    'a'       j=5'r' j=6'.' j=7'S' j=8'.' j=9'S'          no match
               (the other 'a' is at index 1, BEFORE 4)
─────────────────────────────────────────────────────────────────────────
5    'r'       j=6'.' j=7'S' j=8'.' j=9'S'                 no match
─────────────────────────────────────────────────────────────────────────
6    '.'       j=7 'S' no
               j=8 '.' MATCH → print '.', count=3, BREAK   printed .
─────────────────────────────────────────────────────────────────────────
7    'S'       j=8 '.' no
               j=9 'S' MATCH → print 'S', count=4, BREAK   printed S
─────────────────────────────────────────────────────────────────────────
8    '.'       j=9 'S' no                                  no match
─────────────────────────────────────────────────────────────────────────
9    'S'       j starts at 10 → 10 < 10 is false           loop never runs
─────────────────────────────────────────────────────────────────────────
```

## Console output

```text
Duplicate characters in the string are : 
a
m
.
S
```

`count` ends at `4` and is never printed.

## Why each duplicate appears exactly once here

Look at rows `i=3` and `i=4`. The characters `'m'` and `'a'` are duplicates, but they produce **no match** — because their partners lie at *earlier* indexes, and the inner loop only looks **forward**.

```text
'a' at index 1  →  finds 'a' at index 4  →  REPORTED
'a' at index 4  →  looks at 5..9 only    →  not reported again
```

> [!important] The forward-only search is what prevents double reporting
> Each duplicated character is reported by its **first** occurrence and by no other — *provided it appears exactly twice*.
>
> For a character appearing **three** times, the arithmetic changes:
>
> ```text
> "aaa":  'a' at 0 → finds 'a' at 1 → REPORTED
>         'a' at 1 → finds 'a' at 2 → REPORTED AGAIN
>         'a' at 2 → nothing after it
>         Output: a a
> ```
>
> **A character appearing `k` times is printed `k-1` times.** This input happens to contain only pairs, so every duplicate prints exactly once and the bug is invisible.
>
> That is a recurring theme in this folder: **the chosen test data hides the weakness.** Always test with something more awkward than the example.

## Counting the comparisons

```text
i=0: 9 comparisons
i=1: 3 (breaks early)
i=2: 1 (breaks immediately)
i=3: 6
i=4: 5
i=5: 4
i=6: 2 (breaks)
i=7: 2 (breaks)
i=8: 1
i=9: 0
─────────
total: 33
```

Without any `break`, the full count would be `9+8+7+...+1 = 45`. The `break` saved 12 comparisons — helpful, but it does not change the `O(n²)` shape.

---

# 10. Visualize data where useful

## The nested-loop comparison grid

```text
        j→  0    1    2    3    4    5    6    7    8    9
      i     ' '  'a'  'm'  'm'  'a'  'r'  '.'  'S'  '.'  'S'
      ↓   ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
  0  ' '  │ ·  │    │    │    │    │    │    │    │    │    │
      1  'a'  │ ·  │ ·  │    │    │ ✓  │    │    │    │    │
      2  'm'  │ ·  │ ·  │ ·  │ ✓  │    │    │    │    │    │
      3  'm'  │ ·  │ ·  │ ·  │ ·  │    │    │    │    │    │
      4  'a'  │ ·  │ ·  │ ·  │ ·  │ ·  │    │    │    │    │
      5  'r'  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │    │    │    │
      6  '.'  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │    │ ✓  │
      7  'S'  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │    │ ✓
      8  '.'  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │
      9  'S'  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·  │ ·
           └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
             ·  = never examined (j ≤ i)
             ✓  = match found → print and break
```

Only the **upper triangle** is examined. That is what `j = i+1` buys: half the grid, and no diagonal (no self-comparison).

## Why `j = i+1` matters

```text
FULL GRID (j = 0)              UPPER TRIANGLE (j = i+1)
n² comparisons                 n(n-1)/2 comparisons
includes the diagonal          excludes the diagonal
(every char matches itself)    (no self-comparison)

  ███████████                    ░░░░░░░░░░
  ███████████                    ░░░░░░░░░
  ███████████         →          ░░░░░░░░
  ███████████                    ░░░░░░░
  ███████████                    ░░░░░░
```

## How `break` cuts the inner loop short

```text
i = 1, looking for another 'a'

j:   2    3    4    5    6    7    8    9
     'm'  'm'  'a'  'r'  '.'  'S'  '.'  'S'
     ✗    ✗    ✓
                │
                └── MATCH → print, count++, break
                    ─────────────────────────────
                    j = 5..9 never examined
```

## Growth of nested loops

```text
n = 10       ~45 comparisons        ▌
n = 100      ~4,950                 ████
n = 1,000    ~499,500               ████████████████
n = 10,000   ~49,995,000            ████████████████████████████████...

Each 10× increase in n makes the work 100× larger.
```

## Program flow

```text
        chars = toCharArray()
              │
              ▼
        ┌─▶ i < length ? ── false ──▶ done
        │     │ true
        │     ▼
        │   j = i+1
        │     │
        │     ▼
        │  ┌─▶ j < length ? ── false ──┐
        │  │     │ true                │
        │  │     ▼                     │
        │  │  chars[i]==chars[j] ? ─no─┤
        │  │     │ yes                 │
        │  │     ▼                     │
        │  │  print, count++, break ───┤
        │  │     │                     │
        │  │    j++                    │
        │  └─────┘                     │
        │     ◀────────────────────────┘
        │     ▼
        │    i++
        └─────┘
```

---

# 11. Alternative ways to write the same logic

### Minimal fixes — literal, array length, and print the count

```java
String str = " ammar.S.S";                 // no  new String(...)
int count = 0;
char[] chars = str.toCharArray();

System.out.println("Duplicate characters in the string are : ");

for (int i = 0; i < chars.length; i++) {   // array field, consistent with chars[i]
    for (int j = i + 1; j < chars.length; j++) {
        if (chars[i] == chars[j]) {
            System.out.println(chars[i]);
            count++;
            break;
        }
    }
}

System.out.println("Total duplicates found: " + count);   // count finally used
```

Three small corrections, no change to the algorithm.

### Without the array at all

```java
for (int i = 0; i < str.length(); i++) {
    for (int j = i + 1; j < str.length(); j++) {
        if (str.charAt(i) == str.charAt(j)) {
            System.out.println(str.charAt(i));
            break;
        }
    }
}
```

`charAt` gives the same character without allocating a `char[]`. Saves `O(n)` memory. Equally correct.

### The `O(n)` version — using a `Set`

```java
import java.util.HashSet;
import java.util.Set;

String str = " ammar.S.S";

Set<Character> seen = new HashSet<>();
Set<Character> duplicates = new HashSet<>();

for (char c : str.toCharArray()) {
    if (!seen.add(c)) {          // add returns false if already present
        duplicates.add(c);
    }
}

System.out.println("Duplicate characters: " + duplicates);
```

Output:

```text
Duplicate characters: [a, ., S, m]
```

> [!important] Why this is the version to know
> | | Nested loops | `HashSet` |
> |---|---|---|
> | time | `O(n²)` | **`O(n)`** |
> | space | `O(1)` extra | `O(n)` |
> | reports each duplicate | `k-1` times | **exactly once** |
> | loops | two | one |
>
> It fixes the repeated-reporting problem *and* the performance problem at the same time.
>
> The trade is memory: the `Set` grows with the input. **That is the classic time-versus-space trade** — you buy speed by remembering what you have already seen, instead of re-deriving it by searching.
>
> Note `Set<Character>` with a capital `C`. Collections cannot hold primitives, so `char` is auto-boxed to the wrapper class `Character`. Java does the conversion for you.

### Counting occurrences with a `Map`

If you want to know *how many times* each character appears:

```java
import java.util.LinkedHashMap;
import java.util.Map;

Map<Character, Integer> counts = new LinkedHashMap<>();

for (char c : str.toCharArray()) {
    counts.put(c, counts.getOrDefault(c, 0) + 1);
}

for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
    if (entry.getValue() > 1) {
        System.out.println(entry.getKey() + " appears " + entry.getValue() + " times");
    }
}
```

Output:

```text
a appears 2 times
m appears 2 times
. appears 2 times
S appears 2 times
```

`getOrDefault(key, 0)` is the idiomatic way to increment a counter that may not exist yet. `LinkedHashMap` preserves insertion order, so the output follows the order of first appearance.

### Case-insensitive version

```java
String str = " ammar.S.S".toLowerCase();
```

Now `'S'` and `'s'` count as the same character. Whether that is correct depends entirely on the requirement — **ask before assuming**.

### Ignoring whitespace and punctuation

```java
for (char c : str.toCharArray()) {
    if (!Character.isLetter(c)) {
        continue;                  // skip non-letters
    }
    ...
}
```

`Character.isLetter(char)` returns a `boolean`. This would exclude the space and the two dots, leaving only `a`, `m`, `S`.

Note `continue` rather than `break` — skip *this* character, keep going.

### A reusable method

```java
public static Set<Character> findDuplicates(String text) {
    Set<Character> seen = new HashSet<>();
    Set<Character> duplicates = new HashSet<>();
    for (char c : text.toCharArray()) {
        if (!seen.add(c)) {
            duplicates.add(c);
        }
    }
    return duplicates;
}
```

Reusable, testable, and it **returns** rather than prints — so the caller decides what to do with the answer.

---

# 12. Common beginner mistakes

## Mistake 1 — starting the inner loop at `0`

**Incorrect code**

```java
for (int j = 0; j < str.length(); j++) {
    if (chars[i] == chars[j]) { ... }
}
```

**Why it is wrong**
When `j == i`, a character is compared with itself and always matches. **Every** character is reported as a duplicate.

**Correct code**

```java
for (int j = i + 1; j < str.length(); j++) {
```

**How to recognise it in future**
If every element is reported as a duplicate, the inner loop is comparing items with themselves. **`j = i+1` is the standard start for pair comparison.**

---

## Mistake 2 — omitting `break`

**Incorrect code**

```java
if (chars[i] == chars[j]) {
    System.out.println(chars[j]);
    count++;
    // no break
}
```

**Why it is wrong**
A character appearing three or more times is printed once per later occurrence, from the same starting position.

**Correct code**
Add `break;` after the print.

**How to recognise it**
Test with `"aaaa"`. Correct-ish output is `a a a`; without `break` it is `a a a a a a`.

---

## Mistake 3 — expecting `break` to exit both loops

**Incorrect assumption**

```java
for (i ...) {
    for (j ...) {
        if (...) break;      // exits only the inner loop
    }
}
```

**Why it matters**
`break` leaves the **innermost** loop only. Execution continues with the next `i`.

Here that is exactly what we want. But if you genuinely needed to stop everything, you would need a labelled break or a `return`.

**How to recognise it**
If a loop keeps going after you expected it to stop, check which loop the `break` actually belongs to.

---

## Mistake 4 — using `.equals()` on `char`

**Incorrect code**

```java
if (chars[i].equals(chars[j])) {
```

**What Java does**
`char cannot be dereferenced`.

**Correct code**

```java
if (chars[i] == chars[j]) {
```

**How to recognise it**
`X cannot be dereferenced` always means "you called a method on a primitive". **Primitives use operators; objects use methods.**

---

## Mistake 5 — using `==` on `String`s

**Incorrect code**

```java
String a = new String("x");
String b = new String("x");
if (a == b) { }              // false — compares references!
```

**Why it is wrong**
`==` on objects compares identity, not contents.

**Correct code**

```java
if (a.equals(b)) { }
```

**How to recognise it**
This is the mirror image of Mistake 4. **`char` → `==`. `String` → `.equals()`.** And note that `new String(...)` on line 1 of this program is precisely what would make such a bug appear.

---

## Mistake 6 — using `length` instead of `length()` on a `String`

**Incorrect code**

```java
for (int i = 0; i < str.length; i++) {     // error: cannot find symbol
```

**Correct code**

```java
for (int i = 0; i < str.length(); i++) {   // String → method
for (int i = 0; i < chars.length; i++) {   // array → field
```

---

## Mistake 7 — declaring `count` inside a loop

**Incorrect code**

```java
for (int i = ...) {
    int count = 0;
    ...
}
System.out.println(count);     // error: cannot find symbol
```

**Correct code**
Declare it before the loops.

---

## Mistake 8 — computing something and never using it

**The code as written**

```java
count++;
// ...program ends without printing count
```

**Why it matters**
Dead code misleads readers into thinking the value matters. Either use it or remove it.

**Correct code**

```java
System.out.println("Total duplicates found: " + count);
```

**How to recognise it**
**IntelliJ greys out unused variables.** Learn to notice greyed-out code — it almost always signals a missing line or a leftover.

---

## Mistake 9 — assuming each duplicate is reported once

**Incorrect assumption**

The program looks like it reports each duplicated character exactly once. It does for this input — but only because every duplicate appears exactly twice.

```text
"aaa"  →  prints  a a
"aaaa" →  prints  a a a
```

**Correct approach**
Use a `Set` to track what has already been reported, or use the `HashSet` version in section 11.

**How to recognise it**
**Test with a character appearing three or more times.** The example data was not sufficient to expose this.

---

# 13. Interview perspective

## What the interviewer is testing

| Skill | How this program tests it |
|---|---|
| Nested loops | can you write and trace them? |
| Complexity | **do you know this is `O(n²)`?** |
| `j = i+1` | do you know why the inner loop starts there? |
| `==` vs `.equals()` | do you know which applies to `char`? |
| `break` semantics | do you know it exits only the inner loop? |
| Data-structure choice | **can you get to `O(n)` with a `Set`?** |
| Edge cases | triples, empty strings, case sensitivity |

> [!tip]
> "Find duplicate characters" is a classic screening question, and the expected arc is almost always the same:
>
> 1. Write the nested-loop version. ✓ entry ticket
> 2. State its complexity: `O(n²)`. ✓ shows awareness
> 3. Improve it to `O(n)` with a `HashSet`. ✓ **this is what they are waiting for**
>
> Getting to step 3 unprompted is a strong signal.

## Likely follow-up questions

> **"What is the time complexity?"**

`O(n²)` — for each of `n` characters, the inner loop scans up to `n` more. About `n²/2` comparisons, and constants are dropped.

> **"Can you do it in `O(n)`?"**

Yes — one pass with a `HashSet`. `set.add(c)` returns `false` if the character was already present, which is exactly the duplicate test. Trades `O(n)` memory for the speed.

> **"Why does the inner loop start at `i+1`?"**

Two reasons: it avoids comparing a character with itself (which would always match), and it avoids re-checking pairs already examined, halving the work.

> **"Why `==` and not `.equals()`?"**

`chars[i]` is a `char` **primitive**. Primitives have no methods and are compared by value with `==`. `.equals()` would not compile.

> **"What does `break` do here? Does it exit both loops?"**

It exits only the inner loop, moving on to the next starting character. That is the desired behaviour. A labelled break would exit both.

> **"What does this print for `'aaa'`?"**

`a` twice — because each of the first two occurrences finds a later match. A character appearing `k` times prints `k-1` times.

> **"How would you count occurrences instead?"**

A `Map<Character, Integer>` with `getOrDefault(c, 0) + 1`.

> **"Should 'S' and 's' be treated as the same?"**

They currently are not — `char` comparison is case-sensitive. `toLowerCase()` first if you want case-insensitivity. **Worth asking the interviewer rather than assuming.**

> **"What about spaces and punctuation?"**

Currently included — the space and the dots are ordinary characters. `Character.isLetter(c)` would filter them out.

---

# 14. Complexity

## Time complexity: `O(n²)`

Let `n` be the string length.

The outer loop runs `n` times. For each pass, the inner loop runs up to `n - i - 1` times:

```text
i=0:  n-1 comparisons
i=1:  n-2
i=2:  n-3
...
i=n-1: 0
─────────────────────
total = (n-1) + (n-2) + ... + 1
      = n(n-1)/2
      ≈ n²/2
```

In beginner language:

> "For every character, the program scans through all the characters that come after it. The first character is compared against nine others, the second against eight, and so on. Adding those up gives roughly `n²/2` comparisons — so **doubling the input quadruples the work**. That is quadratic time, written `O(n²)`."

> [!important] Why `n²/2` is still `O(n²)`
> Big-O describes the **shape** of the growth, not the exact count. Halving the comparisons (which `j = i+1` does) is a genuine 2× improvement, but the curve is still a parabola.
>
> Only changing the *algorithm* — not the constant — changes the classification. That is what the `HashSet` version does.

**Does `break` help?**

It reduces the constant, sometimes substantially, but not the worst case. For a string with **no** duplicates, no `break` ever fires and the full `n²/2` comparisons run.

```text
best case  (early duplicates):  ~n comparisons
worst case (no duplicates):     ~n²/2 comparisons
```

**Practical impact:**

| `n` | comparisons |
|---|---|
| 10 | 45 |
| 100 | 4,950 |
| 1,000 | 499,500 |
| 10,000 | ~50,000,000 |
| 100,000 | ~5,000,000,000 |

The last row would take minutes. The `HashSet` version handles it in milliseconds.

## Space complexity: `O(n)`

`toCharArray()` allocates a new array of `n` characters. Everything else — `i`, `j`, `count` — is constant.

> "The extra memory grows with the input, because we make a copy of all the characters. That is linear space, `O(n)`."

Using `str.charAt(i)` instead would make it `O(1)`.

## Comparison of approaches

| Approach | Time | Space | Reports duplicates |
|---|---|---|---|
| nested loops (this program) | `O(n²)` | `O(n)` for the array | `k-1` times each |
| nested loops with `charAt` | `O(n²)` | `O(1)` | `k-1` times each |
| `HashSet` | **`O(n)`** | `O(n)` | **once each** |
| sort then scan neighbours | `O(n log n)` | `O(n)` | once each |

> [!tip]
> The `HashSet` version is better on **both** correctness and speed, at the cost of memory proportional to the input.
>
> **That is the classic time-versus-space trade**: you avoid re-searching by remembering. Recognising when to spend memory to buy time is one of the most practically useful judgements in programming.

---

# 15. Edge cases

## Empty string

```java
String str = "";
```

- `str.length()` is `0`, so `0 < 0` is `false`.
- Neither loop runs.
- Output: just the header.

No crash. Correct — an empty string has no duplicates.

## Single character

```java
String str = "a";
```

- Outer loop runs once with `i = 0`.
- Inner loop: `j = 1`, and `1 < 1` is `false` — never runs.
- Output: just the header.

Correct.

## No duplicates

```java
String str = "abcdef";
```

Every comparison fails, no `break` ever fires, and the full `n²/2` comparisons run. Output: just the header.

**This is the worst case for performance** — and note that the header still prints, leaving a heading with nothing under it. Not an error, but a version that said "no duplicates found" would be friendlier.

## All identical characters

```java
String str = "aaaa";
```

```text
i=0: j=1 match → print 'a', break
i=1: j=2 match → print 'a', break
i=2: j=3 match → print 'a', break
i=3: inner loop does not run

Output: a a a
```

> [!warning] The same character is reported three times
> A character appearing `k` times is printed `k-1` times, because each occurrence except the last finds a later match.
>
> The original input `" ammar.S.S"` hides this completely, because every duplicate there appears exactly **twice** — so `k-1` equals `1` and each prints once.
>
> **The example data was not sufficient to expose the weakness.** Always test with a triple.
>
> The `HashSet` version in section 11 reports each duplicate exactly once.

## Character appearing three times

```java
String str = "abcabca";
```

`'a'` is at indexes 0, 3, 6.

```text
i=0 'a': finds 'a' at 3 → print, break
i=3 'a': finds 'a' at 6 → print, break
i=6 'a': nothing after → no match

'a' printed twice
```

Same pattern.

## Case differences

```java
String str = "aA";
```

```text
'a' is 97, 'A' is 65
97 == 65  →  false
Output: just the header
```

**No duplicates found.** `char` comparison is case-sensitive. Whether that is correct depends on the requirement.

## Spaces and punctuation

The program's own input contains a leading space and two dots. Both are treated as ordinary characters, and the dots are correctly reported as duplicates.

If the space appeared twice, it would be printed — as an apparently blank line, which would look odd:

```text
Duplicate characters in the string are : 

a
```

That blank-looking line is a duplicated space. **Wrapping output in brackets** — `System.out.println("[" + chars[i] + "]")` — makes invisible characters visible, a genuinely useful debugging trick.

## Very long strings

```java
String str = "x".repeat(100000);
```

The very first comparison matches and breaks, so this particular case is fast. But a 100,000-character string with **no** duplicates would require about 5 billion comparisons — several minutes.

## `null` input

```java
String str = null;
```

`str.toCharArray()` throws `NullPointerException` immediately. There is no `null` check. Not possible here, since `str` is initialised where it is declared.

## Unicode beyond the basic range

`char` is 16 bits. Characters outside the Basic Multilingual Plane (many emoji) are stored as **two** `char` values — a surrogate pair — so comparing them character by character can produce misleading results. Rare in interview settings, but worth knowing; the same limitation applies to [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]].

---

# 16. Programmer's thought process — from requirement to code

```text
Requirement:
Print the characters that appear more than once.

        ↓

What data do I have?
Text → String

        ↓

What do I need to produce?
Some of its characters → a FILTER

        ↓

What is the test for one character?
"Does it appear anywhere else in the text?"

        ↓

Can I answer that by looking at that character alone?
NO. Unlike "is it even?", this test relates
one item to OTHER items.

        ↓

A relational test needs a second loop.
→ nested loops
→ and therefore O(n²). Note that now,
  before writing anything.

        ↓

How do I access characters by position?
str.charAt(i), or convert once:
    char[] chars = str.toCharArray();
Check the return type → char[]
So the variable must be char[].

        ↓

Outer loop: which character am I checking?
for (int i = 0; i < length; i++)
i is an INDEX → starts at 0

        ↓

Inner loop: what do I compare against?
Options:
  j = 0   → compares with itself → always matches ✗
  j = i   → same problem ✗
  j = i+1 → only LATER characters ✓
            no self-comparison
            each pair examined once

        ↓

How do I compare two characters?
They are char PRIMITIVES → use ==
(.equals() would not compile)

        ↓

What do I do on a match?
Print it. The question "is this duplicated?"
is now answered — searching further would
print the same character again.
→ break

        ↓

Does break exit both loops?
No — only the inner one, so the outer
loop continues with the next character.
That is exactly what I want.

        ↓

Trace " ammar.S.S":
prints a, m, ., S   ✓

        ↓

NOW TEST SOMETHING HARDER:
"aaa" → prints a twice
Because each occurrence except the last
finds a later match.

        ↓

And ask about cost:
O(n²) means 100,000 characters would take
about 5 billion comparisons.

        ↓

Is there a better way?
What am I really asking each time?
"Have I seen this character before?"
        ↓
Is there a structure that answers that fast?
→ HashSet.  set.add(c) returns false
  if c was already present.
        ↓
One pass, O(n), and each duplicate
reported exactly once.
Both problems solved at once.
```

> [!important] Two habits demonstrated
> **1. Recognise the cost from the structure.** The moment you realise the test is *relational*, you know a nested loop is coming and therefore `O(n²)` — before writing a line.
>
> **2. Test beyond the given example.** The provided input contained only pairs, which hid the repeated-reporting behaviour entirely. `"aaa"` exposed it in seconds.

---

# 17. Translate the code into plain English

| Java | Plain English |
|---|---|
| `String str = new String(" ammar.S.S");` | "Make a text box holding this phrase — though the `new String` part is unnecessary; the quoted text alone would do." |
| `int count = 0;` | "Make a tally box starting at zero." |
| `char[] chars = str.toCharArray();` | "Make a row of boxes, one per character, and copy the text into them so I can reach any character by its position." |
| `for(int i=0; i<str.length();i++)` | "Take each character in turn — call its position `i`." |
| `for(int j= i+1; j<str.length();j++)` | "Compare it against every character that comes **after** it — call that position `j`. Starting at `i+1` means I never compare a character with itself, and never check the same pair twice." |
| `if(chars[i] == chars[j])` | "Are these two characters the same? They are single characters, so a plain `==` is the right way to ask." |
| `System.out.println(chars[j]);` | "If so, print it — it appears more than once." |
| `count++;` | "Add one to the tally (which this program then forgets to print)." |
| `break;` | "Stop looking for more copies of *this* character and move on to the next starting position." |

The nested loops in one sentence:

> **"For each character, look through everything that comes after it; if you find the same character again, print it and stop looking."**

And the whole program:

> **"Turn the text into a row of characters. Walk through them one at a time, and for each one scan forward through the rest. If you find a match, print the character and move on to the next starting position."**

---

# 18. Quick reference

### Key Java concepts

| Concept | One-line summary |
|---|---|
| `char` | a primitive holding exactly one character; compared with `==` |
| `char[]` | a mutable, zero-indexed array of characters |
| `toCharArray()` | copies a `String`'s characters into a new array — `O(n)` |
| nested loops | an inner loop runs fully for each outer pass → `O(n²)` |
| `j = i + 1` | examines each pair once, and never compares an item with itself |
| `break` | exits the **innermost** loop only |
| `==` for primitives | compares values — correct for `char` |
| `.equals()` for objects | compares contents — required for `String` |
| string pool | identical literals share one object; `new String(...)` opts out |
| dead code | a value computed but never used — IntelliJ greys it out |

### Important methods

| Method | Owner | Accepts | Returns |
|---|---|---|---|
| `toCharArray()` | `String` | nothing | `char[]` |
| `charAt(int)` | `String` | an index | `char` |
| `length()` | `String` | nothing | `int` — **method** |
| `length` | array | — | `int` — **field** |
| `add(E)` | `java.util.Set` | an element | **`boolean` — `false` if already present** |
| `getOrDefault(k, d)` | `java.util.Map` | a key and a fallback | the value, or the fallback |
| `Character.isLetter(char)` | `java.lang.Character` | a `char` | `boolean` |
| `println(char)` | `PrintStream` | a `char` | `void` |

### Important syntax

| Syntax | Meaning |
|---|---|
| `char[] chars` | a reference to an array of characters |
| `chars[i]` | the character at position `i` |
| `'c'` | a `char` literal — single quotes |
| `"c"` | a `String` literal — double quotes |
| `==` | equal to — correct for primitives |
| `j = i + 1` | the pair-comparison idiom |
| `break` | leave the innermost loop now |
| `continue` | skip to the next iteration |
| `str.length()` vs `arr.length` | method versus field |

### Main interview concept

> **A test that relates one item to other items needs a nested loop, and that means `O(n²)`.** Start the inner loop at `j = i + 1` so each pair is examined once and nothing is compared with itself, and use `break` to stop as soon as the answer is known. Then improve it: a `HashSet` answers "have I seen this before?" in `O(1)`, turning the whole algorithm into one `O(n)` pass — and fixing the repeated-reporting behaviour at the same time.

### Main lesson for code reading

> **When you see a loop inside a loop, two things follow immediately:**
>
> ```text
> 1. The cost is O(n²).  Ask whether it can be avoided.
> 2. Look at where the inner loop STARTS:
>       j = 0     → every pair twice, plus self-comparison
>       j = i     → self-comparison
>       j = i + 1 → each pair once  ← the pair-comparison idiom
> ```
>
> And whenever a program filters or reports, **test it with data more awkward than the example**. This input contained only duplicate *pairs*, which completely hid the fact that a character appearing three times gets printed twice.

---

### Related notes

- [[Java_ReverseString_Code_Reading_Explanation_Obsidian|ReverseString]] — `char`, `String` immutability, and `toCharArray()`
- [[Java_EvenOdd_Code_Reading_Explanation_Obsidian|EvenOdd]] — a self-contained filter test, needing only one loop
- [[Java_PrimeOrNot_Code_Reading_Explanation_Obsidian|PrimeOrNot]] — `break` and early exit in a single loop
- [[Java_CountWords_Code_Reading_Explanation_Obsidian|CountWords]] — checking a method's return type before declaring the variable
- [[Java_SecondLargestArray_Code_Reading_Explanation_Obsidian|SecondLargestArray]] — another program whose test data hid its weakness
