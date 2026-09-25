# Java interview practice

This is the primary study workspace: 52 existing Java source files selected from
the original collections. Package names are retained to keep imports and explanation
notes intact. `practice.py list` shows runnable entry points; helper classes are not
listed.

Run a class from the repository root:

```bash
python3 scripts/practice.py run FrequentInterviewPrograms.CountWords
```

## Topic index

Within each row, start with the linked source and then work through the companions.
The package name plus the file's class name is the runner's fully qualified name.

| Topic | Start here | Companion exercises in the same package |
| --- | --- | --- |
| Strings | [CountWords](src/main/java/FrequentInterviewPrograms/CountWords.java) | `ReverseString`, `FindDuplicateChar` |
| Numeric warm-ups | [PrimeOrNot](src/main/java/FrequentInterviewPrograms/PrimeOrNot.java) | `Factorials`, `FabionacciSeries`, `PalindromeOrNot`, `SwapNumbers` |
| Array traversal | [LargestArrayElement](src/main/java/FrequentInterviewPrograms/LargestArrayElement.java) | `SecondLargestArray`, `SumOfArrayEle`, `EvenOdd` |
| Java array APIs | [BinarySearch](src/main/java/ArraysInBuilt/BinarySearch.java) | `SortArray`, `SortStringArray`, `ReverseArray`, `CopyOf`, `CompareArrays`, `WritingArrays` |
| Frequency maps | [CountFrequencyChar](src/main/java/MBRDI/CountFrequencyChar.java) | `DuplicateElementWithCount` |
| First/second unique value | [FirstNonRepeatingChar](src/main/java/MBRDI/FirstNonRepeatingChar.java) | `SecondNonRepeatingChar`, `FirstNonRepeatingNumber`, `SecondNonRepeatingNumber` |
| Stream filtering | [AllNumbersStartsWith1](src/main/java/VariousStreamPrograms/AllNumbersStartsWith1.java) | `CountOccurenceOfWordStream`, `_streams` |
| Stream duplicates | [DuplicateNumberUsingHashSet](src/main/java/VariousStreamPrograms/DuplicateNumberUsingHashSet.java) | `DuplicateElementInArray`, `FindDuplicatesUsingFilterAndCollectionFrequency`, `PrintDuplicateElements` |
| Employee filtering/grouping | [EmployeeByCityMain](src/main/java/VariousStreamPrograms/EmployeeByCityMain.java) | `EmployeeTesting` |
| Sorting objects | [ComparableVsComparatorDemo](src/main/java/JavaSimplePrograms/ComparableVsComparatorDemo.java) | Also compare [`FrequentInterviewPrograms.EmplSort`](src/main/java/FrequentInterviewPrograms/EmplSort.java) and [`VariousStreamPrograms.EmployeeSort`](src/main/java/VariousStreamPrograms/EmployeeSort.java) |
| OOP | [OOPSConcepts](src/main/java/JavaSimplePrograms/OOPSConcepts.java) | `EncapsulationDemo`, `InheritanceDemo`, `PolymorphismDemo`, `AbstractionDemo`, `AbstractClassVsInterfaceDemo` |
| Functional Java | [FunctionalInterfaceDemo](src/main/java/JavaSimplePrograms/FunctionalInterfaceDemo.java) | `LambdaExpressionDemo`, `PredicateConsumerSupplierDemo`, `Java8Features` |
| JVM and interfaces | [JavaMemoryDemo](src/main/java/JavaSimplePrograms/JavaMemoryDemo.java) | `MarkerInterfaceDemo` |

The different duplicate-finding stream exercises remain together intentionally:
compare the algorithm, output semantics, stateful predicates, and time complexity
rather than memorizing all versions. `VariousStreamPrograms.EmployeeSort` demonstrates
sorting by name with age as a tie-breaker.
[`PracticePrograms.Employee`](src/main/java/PracticePrograms/Employee.java)
is a shared model used by `EmployeeTesting`, not another exercise.

## Explanations and references

- Existing line-by-line explanations remain beside the basic exercises in
  [`FrequentInterviewPrograms/mdFiles`](src/main/java/FrequentInterviewPrograms/mdFiles).
- Longer core-Java walkthroughs are in
  [`archive/.../FrequentInterviewQuestions`](../../archive/java/daim-interview-practice/src/FrequentInterviewQuestions).
- Beginner collections, exceptions, I/O, and threading examples are in
  [`archive/.../Fresher`](../../archive/java/daim-interview-practice/src/Fresher).
- Spring/JDBC snippets are [reference material](../../archive/java/daim-interview-practice/src/Intermediate),
  not a configured backend application. They are not part of the supported build.
- For all previous locations, see [the migration map](../../docs/organization.md).

## How to practise each question

1. Read the problem name, not the solution; state input/output and assumptions.
2. Spend 15–25 minutes implementing it yourself.
3. Explain brute force, a better approach, and time/space complexity aloud.
4. Compare with the primary example, then test boundary cases.
5. Record mistakes in the [study tracker](../../docs/study-plan.md) and revisit after 1, 3, and 7 days.

These are preserved learning samples, **not fully validated reference algorithms**.
In particular, `ReverseString` starts its result with a space; `SecondLargestArray`
initializes both candidates from the first element and does not reliably find the
second *distinct* maximum for arbitrary input. Treat these as debugging exercises.
Check empty/single-element input, repeated maxima, negative numbers, whitespace,
case sensitivity, absent unique values, and integer overflow. The existing notes
describe the original examples and are not independently correctness-audited.