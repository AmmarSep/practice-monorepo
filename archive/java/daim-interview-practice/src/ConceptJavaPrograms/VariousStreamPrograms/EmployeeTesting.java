package ConceptJavaPrograms.VariousStreamPrograms;

import PracticePrograms.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeTesting {
    public static void main(String[] args) {
        Employee objEm = new Employee();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Anwar",35000));
        employeeList.add(new Employee("Ansar", 12000));
        employeeList.add(new Employee("Dewan", 5600));
        employeeList.add(new Employee("Lanka",23499));

        List<Employee> sal =  employeeList.stream().filter(sali->sali.getSalary()<30000).collect(Collectors.toList());
        System.out.println(sal);
        employeeList.stream().filter(mali->mali.getSalary()>30000).forEach(System.out::println);
        Set<Object> naam = new HashSet<>();
        naam.add("Amr");
        naam.add("43");
        naam.add(4);
//        Set<> sampleSet = new HashSet<>();
//        sampleSet.add(3);
//        sampleSet.add(3d);
        Map<String,Integer> mapObject =  new HashMap<>();
        mapObject.put("A",1);
        mapObject.put("B",2);
        System.out.println("Printing of map values"+ " "+ mapObject.get("A"));

    }
}
