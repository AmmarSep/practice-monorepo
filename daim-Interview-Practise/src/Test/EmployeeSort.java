package Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeSort
{
    int employeeId;
    String employeeName;
    int employeeAge;

    public EmployeeSort(int employeeId, String employeeName, int employeeAge)
    {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    @Override
    public String toString()
    {
        return "EmployeeSort{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeAge=" + employeeAge +
                '}';
    }

    public static void main(String[] args)
    {
        List<EmployeeSort> employeeSortList = new ArrayList<>();
        employeeSortList.add(new EmployeeSort(1,"Ammar", 29));
        employeeSortList.add(new EmployeeSort(2,"Richard", 29));
        employeeSortList.add(new EmployeeSort(3,"Manoj", 29));
        employeeSortList.add(new EmployeeSort(4,"Aishwarya", 29));
        employeeSortList.add(new EmployeeSort(5,"Ammar", 12));

        System.out.println("Employee List before sorting");
        for(EmployeeSort p : employeeSortList)
        {
            System.out.println(p);
        }
        System.out.println("\nEmployee List after sorting");

        //comparator
        employeeSortList.sort(Comparator.comparing(EmployeeSort::getEmployeeName).thenComparing(EmployeeSort::getEmployeeAge));
        for(EmployeeSort q : employeeSortList)
        {
            System.out.println(q);
        }
    }
}
