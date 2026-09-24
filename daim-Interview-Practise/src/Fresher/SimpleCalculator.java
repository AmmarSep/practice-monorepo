package Fresher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * A simple calculator application with a GUI interface
 */
public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField displayField;
    private JPanel buttonPanel;
    private String currentInput = "";
    private double result = 0;
    private String lastOperator = "=";
    private boolean startNewInput = true;
    private Stack<Double> memoryStack = new Stack<>();

    // Main method to launch the calculator
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }

    // Constructor
    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 420);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        // Set up the layout
        setLayout(new BorderLayout());

        // Create display field
        displayField = new JTextField("0");
        displayField.setPreferredSize(new Dimension(300, 60));
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayField.setBackground(new Color(230, 230, 230));

        // Create button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));
        buttonPanel.setBackground(new Color(210, 210, 210));

        // Add buttons to the panel
        addButtons();

        // Add components to the frame
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        displayPanel.add(displayField, BorderLayout.CENTER);

        JPanel mainButtonPanel = new JPanel(new BorderLayout());
        mainButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        mainButtonPanel.add(buttonPanel, BorderLayout.CENTER);

        add(displayPanel, BorderLayout.NORTH);
        add(mainButtonPanel, BorderLayout.CENTER);
    }

    // Method to add all buttons to the panel
    private void addButtons() {
        // Memory buttons
        addButton("MC", new Color(180, 180, 180));
        addButton("MR", new Color(180, 180, 180));
        addButton("MS", new Color(180, 180, 180));
        addButton("M+", new Color(180, 180, 180));

        // Special function buttons
        addButton("C", new Color(255, 120, 120));
        addButton("±", new Color(170, 170, 170));
        addButton("%", new Color(170, 170, 170));
        addButton("√", new Color(170, 170, 170));

        // Number buttons and operations
        addButton("7", new Color(240, 240, 240));
        addButton("8", new Color(240, 240, 240));
        addButton("9", new Color(240, 240, 240));
        addButton("/", new Color(255, 160, 90));

        addButton("4", new Color(240, 240, 240));
        addButton("5", new Color(240, 240, 240));
        addButton("6", new Color(240, 240, 240));
        addButton("*", new Color(255, 160, 90));

        addButton("1", new Color(240, 240, 240));
        addButton("2", new Color(240, 240, 240));
        addButton("3", new Color(240, 240, 240));
        addButton("-", new Color(255, 160, 90));

        addButton("0", new Color(240, 240, 240));
        addButton(".", new Color(240, 240, 240));
        addButton("=", new Color(100, 180, 255));
        addButton("+", new Color(255, 160, 90));
    }

    // Helper method to create and add a button
    private void addButton(String label, Color bgColor) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(bgColor);

        // Make equals button taller
        if (label.equals("=")) {
            button.setForeground(Color.WHITE);
        }

        button.addActionListener(this);
        buttonPanel.add(button);
    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Check the command and take appropriate action
        if ("0123456789.".contains(command)) {
            handleNumberInput(command);
        } else if ("+-*/".contains(command)) {
            handleOperator(command);
        } else if (command.equals("=")) {
            calculateResult();
        } else if (command.equals("C")) {
            clearCalculator();
        } else if (command.equals("±")) {
            negateValue();
        } else if (command.equals("%")) {
            calculatePercentage();
        } else if (command.equals("√")) {
            calculateSquareRoot();
        } else if (command.equals("MC")) {
            memoryStack.clear();
        } else if (command.equals("MR")) {
            memoryRecall();
        } else if (command.equals("MS")) {
            memoryStore();
        } else if (command.equals("M+")) {
            memoryAdd();
        }

        // Update the display
        updateDisplay();
    }

    // Handle numeric input and decimal point
    private void handleNumberInput(String digit) {
        if (startNewInput) {
            currentInput = digit;
            startNewInput = false;
        } else {
            // Don't allow multiple decimal points
            if (digit.equals(".") && currentInput.contains(".")) {
                return;
            }
            currentInput += digit;
        }
    }

    // Handle operator buttons
    private void handleOperator(String operator) {
        if (!startNewInput) {
            calculateResult();
        }
        lastOperator = operator;
        startNewInput = true;
    }

    // Calculate the result based on the current input and last operation
    private void calculateResult() {
        if (startNewInput) {
            return; // Nothing to calculate
        }

        double inputValue = Double.parseDouble(currentInput);

        switch (lastOperator) {
            case "+":
                result += inputValue;
                break;
            case "-":
                result -= inputValue;
                break;
            case "*":
                result *= inputValue;
                break;
            case "/":
                if (inputValue != 0) {
                    result /= inputValue;
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                    clearCalculator();
                    return;
                }
                break;
            case "=":
                result = inputValue;
                break;
        }

        currentInput = formatResult(result);
        startNewInput = true;
    }

    // Clear the calculator
    private void clearCalculator() {
        currentInput = "0";
        result = 0;
        lastOperator = "=";
        startNewInput = true;
    }

    // Negate the current value
    private void negateValue() {
        if (currentInput.equals("0")) {
            return;
        }

        if (currentInput.startsWith("-")) {
            currentInput = currentInput.substring(1);
        } else {
            currentInput = "-" + currentInput;
        }
    }

    // Calculate percentage
    private void calculatePercentage() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            value = value / 100.0;
            currentInput = formatResult(value);
        }
    }

    // Calculate square root
    private void calculateSquareRoot() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            if (value >= 0) {
                value = Math.sqrt(value);
                currentInput = formatResult(value);
            } else {
                JOptionPane.showMessageDialog(this, "Cannot calculate square root of a negative number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Memory store
    private void memoryStore() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            memoryStack.push(value);
        }
    }

    // Memory recall
    private void memoryRecall() {
        if (!memoryStack.isEmpty()) {
            currentInput = formatResult(memoryStack.peek());
            startNewInput = true;
        }
    }

    // Memory add
    private void memoryAdd() {
        if (!currentInput.isEmpty() && !memoryStack.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            double memoryValue = memoryStack.pop();
            memoryStack.push(memoryValue + value);
        } else if (!currentInput.isEmpty()) {
            memoryStore();
        }
    }

    // Update the display field
    private void updateDisplay() {
        displayField.setText(currentInput);
    }

    // Format the result to remove unnecessary decimal places
    private String formatResult(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        } else {
            return String.format("%s", value);
        }
    }
}
