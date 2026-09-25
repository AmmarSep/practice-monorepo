#!/usr/bin/env python3
"""List, compile, and run the active Java interview exercises without dependencies."""

import argparse
from pathlib import Path
import re
import subprocess
import sys
import tempfile


ROOT = Path(__file__).resolve().parents[1]
SOURCE_ROOT = ROOT / "interview/java/src/main/java"
BUILD_ROOT = ROOT / ".build"
MAIN_METHOD = re.compile(r"^\s*public\s+static\s+void\s+main\s*\(", re.MULTILINE)


def source_files():
    return sorted(SOURCE_ROOT.rglob("*.java"))


def runnable_classes():
    return [
        ".".join(source.relative_to(SOURCE_ROOT).with_suffix("").parts)
        for source in source_files()
        if MAIN_METHOD.search(source.read_text(encoding="utf-8"))
    ]


def compile_sources(output):
    sources = source_files()
    if not sources:
        raise ValueError(f"No Java exercises found in {SOURCE_ROOT}")
    subprocess.run(
        [
            "javac", "--release", "17", "-encoding", "UTF-8",
            "-classpath", str(output), "-sourcepath", str(SOURCE_ROOT),
            "-d", str(output),
            *map(str, sources),
        ],
        cwd=ROOT,
        check=True,
    )
    return len(sources)


def main(argv=None):
    parser = argparse.ArgumentParser(description=__doc__)
    commands = parser.add_subparsers(dest="command", required=True)
    commands.add_parser("list", help="List runnable classes (not helper classes)")
    commands.add_parser("build", help="Compile all active Java sources with Java 17 compatibility")
    run = commands.add_parser("run", help="Compile and run one fully qualified class")
    run.add_argument("class_name", help="A class from the list command")
    run.add_argument("args", nargs=argparse.REMAINDER, help="Arguments passed to the Java program")
    args = parser.parse_args(argv)

    if args.command == "list":
        print("\n".join(runnable_classes()))
        return 0

    if args.command == "run" and args.class_name not in runnable_classes():
        parser.error(f"Unknown runnable class: {args.class_name}. Use the list command.")

    try:
        BUILD_ROOT.mkdir(exist_ok=True)
        with tempfile.TemporaryDirectory(prefix="java-", dir=BUILD_ROOT) as output:
            count = compile_sources(Path(output))
            if args.command == "build":
                print(f"Compiled {count} Java source files successfully.")
            else:
                subprocess.run(
                    ["java", "-cp", output, args.class_name, *args.args],
                    cwd=ROOT,
                    check=True,
                )
    except FileNotFoundError as error:
        print(f"Cannot find {error.filename}. Install JDK 17+ and add its bin directory to PATH.", file=sys.stderr)
        return 1
    except (OSError, ValueError) as error:
        print(str(error), file=sys.stderr)
        return 1
    except subprocess.CalledProcessError as error:
        return error.returncode
    return 0


if __name__ == "__main__":
    sys.exit(main())