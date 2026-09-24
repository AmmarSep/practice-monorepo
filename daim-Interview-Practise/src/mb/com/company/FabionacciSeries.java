package mb.com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FabionacciSeries {
//    1 1 2 3 5 8 13 21 34
public static void main(String[] args) {
//    List<Student> studentList = new ArrayList<>();
//    studentList.add(new Student("Amr", "Comp", 28));
//    studentList.add(new Student("Amy", "Chem", 29));
//    studentList.add(new Student("Kmr", "Eng", 35));
//    studentList.add(new Student("Lsn", "Eng", 36));
//    studentList.add(new Student("Don", "Chem", 29));
//  List<?> filteredList = studentList.stream().filter(student -> studentList.contains("Chem")).collect(Collectors.toList());
//    Map<String, List<Student>> mappedList  = studentList.stream().collect(Collectors.toMap(Student::getDepartment,studentList::));
//    System.out.println(mappedList);
    int number = 0;
    int number2 = 1;
    int count = 10;
    System.out.println("Expected Fabionacci series: " );
    for(int i =1; i<=count-1; i++ ){
        int num3 = number + number2;
        number = number2;
        number2 = num3 ;
        System.out.println(number+ "");

    }

}
//static class Student{
//    private String name;
//    private String department;
//    private int age;
//
//    public Student(String name, String department, int age) {
//        this.name = name;
//        this.department = department;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//}
}
