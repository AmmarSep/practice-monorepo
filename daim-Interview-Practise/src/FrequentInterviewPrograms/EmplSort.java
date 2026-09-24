package FrequentInterviewPrograms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * The EmplSort class represents an employee with a name and age.
 */
public class EmplSort {
    public String name; // The name of the employee
    public int age; // The age of the employee

    /**
     * Constructs an EmplSort object with the specified name and age.
     *
     * @param name The name of the employee
     * @param age The age of the employee
     */
    public EmplSort(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the name of the employee.
     *
     * @return The name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name The name of the employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the age of the employee.
     *
     * @return The age of the employee
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the employee.
     *
     * @param age The age of the employee
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns a string representation of the EmplSort object.
     *
     * @return A string representation of the EmplSort object
     */
    @Override
    public String toString() {
        return "EmplSort{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * The main method creates a list of EmplSort objects, adds some employees to the list,
     * prints the list before sorting, sorts the list based on name and age,
     * and then prints the list after sorting.
     *
     * @param args The command line arguments
     */
    /**
     * The main method is the entry point of the program.
     * It demonstrates sorting a list of EmplSort objects based on name and age.
     */
    public static void main(String[] args) { // This is the main method where the execution of the program starts
        List<EmplSort> employeeList = new ArrayList<>(); // This line declares a List of EmplSort objects named 'employeeList'

        // The following lines add new EmplSort objects to 'employeeList' with the given names and ages
        employeeList.add(new EmplSort("Niyaz", 21));
        employeeList.add(new EmplSort("Raez", 32));
        employeeList.add(new EmplSort("Siha", 53));
        employeeList.add(new EmplSort("Lafir", 23));
        employeeList.add(new EmplSort("Sabiq", 32));
        employeeList.add(new EmplSort("Lafir", 33));

        System.out.println("Before sorting"); // This line prints the message "Before sorting" to the console

        for(EmplSort e : employeeList){ // This line starts a for-each loop that iterates over the elements of 'employeeList'
            System.out.println(e); // This line prints the current EmplSort object to the console
        }

        // This line sorts 'employeeList' by comparing the names of the EmplSort objects. If the names are the same, it then compares the ages
        employeeList.sort(Comparator.comparing(EmplSort::getName).thenComparing(EmplSort::getAge));

        System.out.println("After sorting"); // This line prints the message "After sorting" to the console

        for(EmplSort s : employeeList){ // This line starts a for-each loop that iterates over the elements of 'employeeList'
            System.out.println(s); // This line prints the current EmplSort object to the console
        }
    }
}
