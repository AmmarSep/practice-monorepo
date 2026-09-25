# Organization and migration map

## Decisions

- The active interview workspace has one primary collection per topic. Files were
  moved without rewriting Java packages, class names, or algorithms.
- Earlier attempts and longer explanations remain available as references. Similar
  names do not prove equivalent behavior, so no source was automatically deleted
  or merged based on its filename.
- TNOU coursework stays in intact collections to preserve relative README links,
  module paths, and CMake source paths. It is not included in the interview build.
- Original ZIP imports and generated files are preserved in their relocated
  collections. Build outputs are now ignored to prevent new generated clutter.

## Old → new locations

| Original path | New path |
| --- | --- |
| `daim-Interview-Practise/src/FrequentInterviewPrograms` | [`interview/java/src/main/java/FrequentInterviewPrograms`](../interview/java/src/main/java/FrequentInterviewPrograms) (including explanation notes) |
| `daim-Interview-Practise/src/ArraysInBuilt` | [`interview/java/src/main/java/ArraysInBuilt`](../interview/java/src/main/java/ArraysInBuilt) |
| `daim-Interview-Practise/src/MBRDI` | [`interview/java/src/main/java/MBRDI`](../interview/java/src/main/java/MBRDI) |
| `daim-Interview-Practise/src/VariousStreamPrograms` | [`interview/java/src/main/java/VariousStreamPrograms`](../interview/java/src/main/java/VariousStreamPrograms) |
| `daim-Interview-Practise/src/JavaSimplePrograms` | [`interview/java/src/main/java/JavaSimplePrograms`](../interview/java/src/main/java/JavaSimplePrograms) |
| `daim-Interview-Practise/src/PracticePrograms/Employee.java` | [`interview/java/src/main/java/PracticePrograms/Employee.java`](../interview/java/src/main/java/PracticePrograms/Employee.java) (required by the stream examples) |
| Remaining `daim-Interview-Practise` contents | [`archive/java/daim-interview-practice`](../archive/java/daim-interview-practice) |
| `few-Interview_Practise` | [`archive/java/few-interview-practice`](../archive/java/few-interview-practice) |
| `Interview-Practise` | [`archive/imports/Interview-Practise`](../archive/imports/Interview-Practise) |
| `PP` | [`archive/imports/PP`](../archive/imports/PP) |
| `TNOU-Practise-Java` | [`coursework/tnou/java-multimedia`](../coursework/tnou/java-multimedia) |
| `TNOU-Practice-cpp` | [`coursework/tnou/mixed-labs`](../coursework/tnou/mixed-labs) |

## Which version should I study?

| Repeated material | Primary collection | Earlier attempts / alternatives |
| --- | --- | --- |
| Small array/string/number questions | `FrequentInterviewPrograms`, `ArraysInBuilt` | `PracticePrograms`, `Again2`, `mb/com/company`, `few-interview-practice` |
| First/second non-repeating values | `MBRDI` | `MBRDI_Practise`, `practise/secondNonRepeatingChar` |
| Stream exercises | `VariousStreamPrograms` | `ConceptJavaPrograms/VariousStreamPrograms`, `Again2/Practising`, `PracticePrograms` |
| Core Java concepts | `JavaSimplePrograms` | `FrequentInterviewQuestions`, `Fresher`, `ConceptJavaPrograms` |
| University Java labs | `coursework/tnou/java-multimedia` | `coursework/tnou/mixed-labs/java`, `Java_Claude`, and hand-practice folders |

Archive names in the last column are relative to
`archive/java/daim-interview-practice/src` unless a different collection is named.
The archive is deliberately not a second active source root. Where an archived
snippet imports a moved class, include the active source root when compiling it
separately; do not copy the shared class back into the archive.