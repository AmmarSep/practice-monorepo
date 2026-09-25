# Interview practice monorepo

Start with **[the Java topic index](interview/java/README.md)** and follow the
**[14-day study plan](docs/study-plan.md)**. Use one primary solution per problem;
compare earlier attempts only after solving it yourself.

## Layout

| Folder | Purpose |
| --- | --- |
| [`interview/java`](interview/java/README.md) | Active, buildable Java interview exercises and their existing explanations |
| [`coursework/tnou`](coursework/tnou/README.md) | University Java, multimedia, and Windows C++ labs; separate from daily interview practice |
| [`archive`](archive/README.md) | Earlier attempts, longer reference demos, personal snippets, and original ZIP imports |
| [`docs`](docs/study-plan.md) | Study plan and [old-to-new location map](docs/organization.md) |
| `scripts` | Dependency-free Java runner and regression checks |

No original exercise or ZIP was deleted. Selected source files were **moved, not
copied**; class names, packages, and exercise logic are unchanged. Repetition is
kept out of the active study path rather than destructively merging different attempts.

## Quick start

Requires **JDK 17+** (`java` and `javac` on `PATH`) and **Python 3.9+**.
No Maven, Gradle, external Java libraries, or network downloads are required.
Run these from the repository root:

```bash
python3 scripts/practice.py list
python3 scripts/practice.py build
python3 scripts/practice.py run MBRDI.FirstNonRepeatingChar
python3 scripts/practice.py run FrequentInterviewPrograms.PalindromeOrNot
python3 -m unittest discover -s scripts -p 'test_*.py' -v
```

The palindrome exercise reads a number from standard input. Most other examples
use hard-coded sample input: edit that input to try your own cases. Each build/run
uses a fresh temporary directory under `.build/`, removed afterward, so stale
class files and archived sources cannot affect the result. Extra arguments after
the class name are forwarded to its `main` method. Programs run with the repository
root as their working directory.

`JavaMemoryDemo` uses a deprecated API in its original teaching example, so a
compiler warning is expected. Compilation and smoke tests are not proof that every
sample handles every edge case; see the cautions in the topic index.

## IntelliJ IDEA

Open the repository root and select an installed JDK 17+ as the Project SDK.
The shared configuration registers `interview/java` as the Java module and excludes
archives, coursework, and generated output from the active build. Run any listed
class using the gutter icon beside `main`. No ZIP backup needs to be added as a library.

Coursework can be opened separately; follow its own README and platform requirements.

## Adding practice without creating more duplicates

1. Check the topic index and existing package before adding a solution.
2. Improve the existing exercise, or add an explicitly named alternative only when
   it demonstrates a different algorithm or trade-off.
3. Match its package to its path under `interview/java/src/main/java`.
4. Update the topic index and add regression checks for changed behavior.
5. Run the build and tests. Do not commit `.class` files, build output, or new ZIP backups.