# TNOU coursework

Coursework is separate from [daily interview practice](../../interview/java/README.md).
The original collections remain intact internally so their relative paths continue
to work; similarly named labs are variants, not automatically interchangeable files.

| Collection | Contents |
| --- | --- |
| [`java-multimedia`](java-multimedia/README.md) | Main Java Q1–Q20 and multimedia Q1–Q10 lab collections |
| [`mixed-labs`](mixed-labs/README.md) | Alternate Java labs, hand-practice attempts, and Visual C++ labs |
| [`mixed-labs/Java_Claude`](mixed-labs/Java_Claude/README.md) | Additional documented Java implementations |
| [`mixed-labs/VisualCpp`](mixed-labs/VisualCpp/README.md) | Original Windows SDK/MFC exercises |
| [`mixed-labs/VisualCPP_Claude`](mixed-labs/VisualCPP_Claude/README.md) | Additional Windows SDK/MFC implementations and build documentation |

Start with `java-multimedia` for the main Java lab sequence and use the alternatives
only for comparison. Compile labs separately: several collections reuse class names
and must not all be added to one Java source root.

For example, from the repository root (JDK 17+):

```bash
java coursework/tnou/java-multimedia/java/Q1_TemperatureConverter.java
```

Follow each collection's own README for multi-file/package exercises. GUI programs
need a graphical desktop; image examples need the stated image files in their
working directory. The Visual C++ programs require **Windows** and the appropriate
Windows SDK/MFC toolchain; they are not part of the macOS/Linux Java build.

The coursework was relocated without changing its implementation. Its legacy CMake
and IDE projects are preserved, not certified by the interview workspace's smoke tests.