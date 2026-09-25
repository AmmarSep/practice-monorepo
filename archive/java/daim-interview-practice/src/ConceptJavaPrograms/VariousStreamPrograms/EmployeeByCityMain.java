package ConceptJavaPrograms.VariousStreamPrograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeByCityMain {
    public static void main(String[] args) {
        List<EmployeeByCity> empList = new ArrayList<>();
        createEmployeeList(empList);
        Map<String, List<EmployeeByCity>> empMap = empList.stream().
                collect(Collectors.groupingBy(EmployeeByCity::getCity));
        empMap.forEach((key, value) ->
                System.out.println("City : " + key + "Employees : " + value));
    }

    public static void createEmployeeList(List<EmployeeByCity> empList) {
        EmployeeByCity e1 = new EmployeeByCity();
        e1.setName("Arun");
        e1.setCity("Hyderabad");
        e1.setId("1");

        EmployeeByCity e2 = new EmployeeByCity();
        e2.setName("Amar");
        e2.setCity("Bangalore");
        e2.setId("1");

        EmployeeByCity e3 = new EmployeeByCity();
        e3.setName("Arjun");
        e3.setCity("Hyderabad");
        e3.setId("1");

        EmployeeByCity e4 = new EmployeeByCity();
        e4.setName("Lokesh");
        e4.setCity("Bangalore");
        e4.setId("1");

        EmployeeByCity e5 = new EmployeeByCity();
        e5.setName("Abdul");
        e5.setCity("Hyderabad");
        e5.setId("1");
    }
}
