"""Layout, runner, and Java smoke checks. Requires a real JDK on PATH."""

import contextlib
import io
from pathlib import Path
import re
import subprocess
import sys
import tempfile
import unittest
from urllib.parse import unquote, urlsplit
import xml.etree.ElementTree as ET

import practice


class LayoutTests(unittest.TestCase):
    def test_sources_match_their_packages(self):
        sources = practice.source_files()
        self.assertTrue(sources)
        for source in sources:
            with self.subTest(source=source):
                text = source.read_text(encoding="utf-8")
                package = re.search(r"^package\s+([\w.]+);", text, re.MULTILINE)
                if package is None:
                    self.assertFalse(practice.MAIN_METHOD.search(text))
                    continue
                self.assertEqual(
                    package.group(1).split("."),
                    list(source.relative_to(practice.SOURCE_ROOT).parent.parts),
                )

    def test_only_active_sources_are_compiled(self):
        for source in practice.source_files():
            self.assertNotIn("archive", source.parts)
            self.assertNotIn("coursework", source.parts)
            self.assertNotIn("out", source.parts)

    def test_list_contains_programs_but_not_helpers(self):
        classes = practice.runnable_classes()
        self.assertEqual(classes, sorted(set(classes)))
        self.assertIn("MBRDI.FirstNonRepeatingChar", classes)
        self.assertIn("VariousStreamPrograms.EmployeeTesting", classes)
        self.assertNotIn("PracticePrograms.Employee", classes)
        self.assertNotIn("VariousStreamPrograms.EmployeeByCity", classes)
        self.assertIn("VariousStreamPrograms.EmployeeSort", classes)

    def test_unknown_class_is_rejected_before_compilation(self):
        errors = io.StringIO()
        with contextlib.redirect_stderr(errors), self.assertRaises(SystemExit) as error:
            practice.main(["run", "../archive/OldAttempt"])
        self.assertEqual(2, error.exception.code)
        self.assertIn("Unknown runnable class", errors.getvalue())

    def test_listing_works_outside_repository_root(self):
        result = subprocess.run(
            [sys.executable, str(Path(practice.__file__)), "list"],
            cwd=practice.ROOT / "docs",
            capture_output=True, text=True, check=True, timeout=10,
        )
        self.assertEqual(practice.runnable_classes(), result.stdout.splitlines())

    def test_navigation_links_resolve(self):
        documents = (
            "README.md", "interview/java/README.md", "archive/README.md",
            "coursework/tnou/README.md", "docs/organization.md", "docs/study-plan.md",
        )
        for document in documents:
            path = practice.ROOT / document
            for link in re.findall(r"\[[^\]]+\]\(([^)]+)\)", path.read_text(encoding="utf-8")):
                target = urlsplit(link)
                if not target.scheme and target.path:
                    with self.subTest(document=document, link=link):
                        self.assertTrue((path.parent / unquote(target.path)).exists())

    def test_intellij_modules_resolve(self):
        configuration = ET.parse(practice.ROOT / ".idea/modules.xml")
        for module in configuration.findall(".//module"):
            path = Path(module.attrib["filepath"].replace("$PROJECT_DIR$", str(practice.ROOT)))
            with self.subTest(module=path):
                self.assertTrue(path.is_file())
                ET.parse(path)
        active_module = ET.parse(practice.ROOT / "interview/java/interview-java.iml")
        roots = active_module.findall(".//sourceFolder")
        self.assertEqual(["file://$MODULE_DIR$/src/main/java"], [root.attrib["url"] for root in roots])


class JavaSmokeTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        practice.BUILD_ROOT.mkdir(exist_ok=True)
        cls.output = tempfile.TemporaryDirectory(prefix="java-test-", dir=practice.BUILD_ROOT)
        cls.addClassCleanup(cls.output.cleanup)
        cls.source_count = practice.compile_sources(Path(cls.output.name))

    def run_java(self, class_name, input_text=""):
        result = subprocess.run(
            ["java", "-cp", self.output.name, class_name],
            cwd=practice.ROOT, input=input_text, capture_output=True,
            text=True, check=True, timeout=10,
        )
        return result.stdout

    def test_all_active_sources_compile(self):
        self.assertEqual(len(practice.source_files()), self.source_count)
        for class_name in practice.runnable_classes():
            with self.subTest(class_name=class_name):
                self.assertTrue(
                    (Path(self.output.name) / (class_name.replace(".", "/") + ".class")).is_file()
                )

    def test_array_search(self):
        self.assertEqual("2\n", self.run_java("ArraysInBuilt.BinarySearch"))

    def test_first_non_repeating_character(self):
        self.assertEqual("w\n", self.run_java("MBRDI.FirstNonRepeatingChar"))

    def test_stream_filter(self):
        self.assertEqual("13\n16\n113\n145\n", self.run_java("VariousStreamPrograms.AllNumbersStartsWith1"))

    def test_shared_employee_dependency(self):
        output = self.run_java("VariousStreamPrograms.EmployeeTesting")
        self.assertIn("employeeName='Anwar', Salary=35000", output)
        self.assertIn("employeeName='Ansar', Salary=12000", output)

    def test_employee_sort_orders_names_and_breaks_age_ties(self):
        output = self.run_java("VariousStreamPrograms.EmployeeSort")
        self.assertIn("Employee List after sorting\n", output)
        sorted_employees = output.split("Employee List after sorting\n", 1)[1].splitlines()
        self.assertEqual([
            "EmployeeSort{employeeId=4, employeeName='Aishwarya', employeeAge=29}",
            "EmployeeSort{employeeId=5, employeeName='Ammar', employeeAge=12}",
            "EmployeeSort{employeeId=1, employeeName='Ammar', employeeAge=29}",
            "EmployeeSort{employeeId=3, employeeName='Manoj', employeeAge=29}",
            "EmployeeSort{employeeId=2, employeeName='Richard', employeeAge=29}",
        ], sorted_employees)

    def test_palindrome_input(self):
        self.assertIn("The given number is Palindrome number", self.run_java(
            "FrequentInterviewPrograms.PalindromeOrNot", "121\n"
        ))
        self.assertIn("123 is not a Palindrome number", self.run_java(
            "FrequentInterviewPrograms.PalindromeOrNot", "123\n"
        ))

    def test_runner_compiles_and_forwards_stdin(self):
        result = subprocess.run(
            [sys.executable, str(Path(practice.__file__)), "run", "FrequentInterviewPrograms.PalindromeOrNot"],
            cwd=practice.ROOT / "docs", input="121\n", capture_output=True,
            text=True, check=True, timeout=30,
        )
        self.assertIn("The given number is Palindrome number", result.stdout)


if __name__ == "__main__":
    unittest.main()