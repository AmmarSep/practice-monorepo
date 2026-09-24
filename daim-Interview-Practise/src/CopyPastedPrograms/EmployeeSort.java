package CopyPastedPrograms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeSort {
    public String name;
    public int age;

    public EmployeeSort(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", age=" + age;
    }

    public static void main(String args[]) {

        List<EmployeeSort> employees = new ArrayList();
        EmployeeSort emp1 = new EmployeeSort("John", 26);
        EmployeeSort emp2 = new EmployeeSort("Martin", 23);
        EmployeeSort emp3 = new EmployeeSort("John", 20);
        EmployeeSort emp4 = new EmployeeSort("Martin", 19);
        EmployeeSort emp5 = new EmployeeSort("Arpit", 27);

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        System.out.println("List before sorting : ");
        for (EmployeeSort e : employees) {
            System.out.println(e.name + " - " + e.age);
//            employees.sort(Comparator.comparing(Employee::getName) .thenComparing(Employee::getAge)); System.out.println("sorted list of Person using Comparator.comparing : " + employees);


        }
        employees.sort(Comparator.comparing(EmployeeSort::getName) .thenComparing(EmployeeSort::getAge));
        System.out.println("sorted list of Person using Comparator.comparing : " + employees);
        for(EmployeeSort s : employees)
            System.out.println(s);
    }
}

