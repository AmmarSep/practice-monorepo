# TNOU MCA-P4 Java Programming Lab Solutions

This repository contains complete solutions for all 20 practical questions for Internet Programming (Java) - MCA-P4 Lab 4.

## Program List

### Basic Programs (1-5)

1. **P1_TemperatureConverter.java** - Converting Temperature in Fahrenheit into Temperature in Celsius
2. **P2_StudentMarkList.java** - Student Mark-List preparation with grade calculation
3. **P3_ReverseAndSum.java** - Reverse and finding sum of individual digits of a given number
4. **P4_Fibonacci.java** - Generate Fibonacci series
5. **P5_Factorial.java** - Finding Factorial of a given number

### Intermediate Programs (6-10)

6. **P6_PrimeNumber.java** - Check whether a given number is prime or not
7. **P7_SortingNumbers.java** - Sorting numbers in Ascending and Descending order
8. **P8_MatrixMultiplication.java** - Matrix Multiplication
9. **P9_QuadraticEquation.java** - Finding roots of quadratic equation
10. **P10_SphereVolume.java** - Finding volume of a sphere (Concept: Class and Object)

### OOP Concepts (11-15)

11. **P11_EmployeeSalary.java** - Employee salary Report (Concept: Array of Objects)
12. **P12_StackOperations.java** - Stack Operations (Concept: Constructor)
13. **P13_PalindromeNumber.java** - Palindrome number check (Concept: Abstract Class)
14. **P14_ElectricityBill.java** - Electricity charge calculation (Concept: Multiple Inheritance using Interfaces)
15. **P15_AreaCalculation/** - Area of Triangle and Rectangle (Concept: Package, Interface)
    - Shape.java (Interface)
    - Triangle.java
    - Rectangle.java
    - P15_AreaMain.java (Main class)

### Advanced Concepts (16-20)

16. **P16_QueueException.java** - Queue implementation (Concept: Exception Handling, User-defined Exception)
17. **P17_MultiThreading.java** - Multi-Threading (Concept: Extending Thread class)
18. **P18_RailwayReservation.java** - Railway Reservation System (Concept: IO Streams - DataInputStream & DataOutputStream)
19. **P19_GraphicsDemo.java** - Display graphical components (Concept: Graphics class)
20. **P20_ImageDisplay.java** - Display an image (Concept: PixelGrabber Class)

## How to Compile and Run

### For Regular Java Files (Programs 1-14, 16-20)

```bash
# Compile
javac ProgramName.java

# Run
java ProgramName
```

Example:
```bash
javac P1_TemperatureConverter.java
java P1_TemperatureConverter
```

### For Package-based Program (Program 15)

```bash
# Navigate to Java_Claude directory
cd Java_Claude

# Compile all files in the package
javac P15_AreaCalculation/*.java

# Run
java P15_AreaCalculation.P15_AreaMain
```

### For GUI Programs (Programs 19-20)

These programs create graphical windows and can be run the same way:

```bash
javac P19_GraphicsDemo.java
java P19_GraphicsDemo

javac P20_ImageDisplay.java
java P20_ImageDisplay
```

## Program Details

### Basic I/O Programs
- Programs 1-9 use Scanner for console input
- All demonstrate fundamental programming concepts

### OOP Concepts Covered
- **Classes and Objects**: P10
- **Array of Objects**: P11
- **Constructors**: P12
- **Abstract Classes**: P13
- **Multiple Inheritance (Interfaces)**: P14, P15
- **Packages**: P15

### Advanced Features
- **Exception Handling**: P16 (Custom exceptions: QueueFullException, QueueEmptyException)
- **Multi-threading**: P17 (extends Thread class)
- **File I/O**: P18 (DataInputStream/DataOutputStream)
- **GUI Programming**: P19, P20 (Swing, Graphics, PixelGrabber)

## Key Concepts Demonstrated

1. **Object-Oriented Programming**
   - Encapsulation
   - Inheritance
   - Polymorphism
   - Abstraction

2. **Data Structures**
   - Stack (P12)
   - Queue (P16)
   - Arrays and Matrices (P7, P8, P11)

3. **File Handling**
   - Binary file operations using DataInputStream/DataOutputStream (P18)

4. **Concurrent Programming**
   - Multi-threading with Thread class (P17)

5. **GUI Development**
   - Swing components (P19, P20)
   - Graphics rendering (P19)
   - Image processing (P20)

## Notes

- All programs include proper input validation where applicable
- GUI programs (19-20) create windows and should be closed properly
- Program 18 creates a file "railway_reservations.dat" in the current directory
- Program 20 creates a sample image if the specified image file is not found
- All programs are well-commented for easy understanding

## Requirements

- Java Development Kit (JDK) 8 or higher
- Command line or IDE (Eclipse, IntelliJ IDEA, NetBeans, VS Code)

## Author

Generated for TNOU MCA-P4 Lab Practice

## License

Educational purposes only
