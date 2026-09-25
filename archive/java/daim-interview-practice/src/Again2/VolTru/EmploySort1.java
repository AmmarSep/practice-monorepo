package Again2.VolTru;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmploySort1
{
    int employeeId;
    String employeeName;
    int employeeAge;

    public EmploySort1(int employeeId, String employeeName, int employeeAge)
    {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }

    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public int getEmployeeAge()
    {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge)
    {
        this.employeeAge = employeeAge;
    }

    @Override
    public String toString()
    {
        return "EmploySort1{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeAge=" + employeeAge +
                '}';
    }

    public static void main(String[] args)
    {
        List<EmploySort1> employSort1List = new ArrayList<>();
        employSort1List.add(new EmploySort1(1,"Ammar", 42));
        employSort1List.add(new EmploySort1(2,"Sumaiya", 26));
        employSort1List.add(new EmploySort1(3,"Ayesha", 21));
        employSort1List.add(new EmploySort1(4,"Sahira", 44));
        employSort1List.add(new EmploySort1(5,"Sheik", 61));
        employSort1List.add(new EmploySort1(6,"Ammar", 37));
        employSort1List.add(new EmploySort1(7,"Sheik", 33));

        System.out.println("Employee List Before Sorting : ");
        for(EmploySort1 p : employSort1List)
        {
            System.out.println(p);
        }
//Comparable
        System.out.println("\nEmployee List After Sorting using Comparable : ");
        Collections.sort(employSort1List,(o1, o2) -> o1.getEmployeeName().compareTo(o2.getEmployeeName()));
        for(EmploySort1 q : employSort1List)
        {
            System.out.println(q);
        }
//Comparator
        System.out.println("\nEmployee List after sorting using Comparator : ");
        employSort1List.sort(Comparator.comparing(EmploySort1::getEmployeeName).thenComparing(EmploySort1::getEmployeeAge));
        for(EmploySort1 r : employSort1List)
        {
            System.out.println(r);
        }
    }
}
