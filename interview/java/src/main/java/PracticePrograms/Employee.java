package PracticePrograms;

public class Employee {
 String employeeName;
 int Salary;

    public Employee(String employeeName, int salary) {
        this.employeeName = employeeName;
        Salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public Employee(){

    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", Salary=" + Salary +
                '}';
    }
}
