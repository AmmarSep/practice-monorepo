package notion;

public class JUnitProgram {

    public static void main(String[] args) {
        JUnitProgram program = new JUnitProgram();
        program.test_JUnit();
        System.out.println("All tests passed!");
    }

    public void test_JUnit() {
        System.out.println("This is the testcase in this class");
        String str1 = "This is the testcase in this class";
        assertEquals("This is the testcase in this class", str1);
    }

    // Simple assertion method to replace JUnit's assertEquals
    private void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Assertion failed: expected '" + expected + "' but was '" + actual + "'");
        }
        System.out.println("Assertion passed: values are equal");
    }
}
