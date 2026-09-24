package PracticePrograms;

import java.util.LinkedList;
import java.util.List;

public class StudentList {
    public static void main(String[] args) {
        String student1 = "Dhanish";
        int age = 25;
        boolean isTeen = false;

        String student2 = "Ameer";
        String student3 = "Nidha";
        String student4 = "Rilwan";


        List<String> studentList = new LinkedList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        System.out.println(studentList);

        String studentName1= studentList.get(0);
        String studentName2 = studentList.get(1);

        System.out.println(studentName2);
    }


}
