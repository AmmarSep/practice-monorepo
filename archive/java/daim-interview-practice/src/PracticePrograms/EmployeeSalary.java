package PracticePrograms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeSalary {
    public static void main(String[] args){
        // int id;
        // String name;
        // int Salary;
        List<Integer> salary = Arrays.asList(32345,452345,64345);
        // EmployeeList.equals(int id1,int id2);

        List sortedNameListBySalary = salary.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedNameListBySalary);
    }
}
