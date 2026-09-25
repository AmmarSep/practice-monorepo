package notion;

public class CalculatorTest {

    public static void main(String[] args) {
        CalculatorTest test = new CalculatorTest();
        test.runAllTests();
    }

    public void runAllTests() {
        System.out.println("Running Calculator tests...");
        try {
            testAddition();
            testDivision();
            System.out.println("All tests passed!");
        } catch (AssertionError e) {
            System.out.println("Test failed: " + e.getMessage());
        }
    }

    public void testAddition() {
        Calculator calc = new Calculator();
        // 3 + 4 = 7
        int expected = 7;
        int actual = calc.add(3, 4);
        assertEquals("adding 3 and 4", expected, actual);
        System.out.println("Addition test passed");
    }

    public void testDivision() {
        Calculator calc = new Calculator();
        // Divide by zero shouldn't work
        try {
            calc.divide(2, 0);
            fail("Should have thrown an exception!");
        }
        catch (ArithmeticException e) {
            // Good, that's what we expect
            System.out.println("Division by zero test passed");
        }
    }

    // Custom assertion methods to replace JUnit's methods
    private void assertEquals(String message, int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError(message + ": expected " + expected + " but was " + actual);
        }
    }

    private void fail(String message) {
        throw new AssertionError(message);
    }
}
