package PracticePrograms;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailsList {
    public static void main(String[] args) {
        StudentDetails studentDetailsList1 = new StudentDetails();
        studentDetailsList1.studentName = "Mohamed";
        studentDetailsList1.studentAge = 43;
        studentDetailsList1.isTeen = false;

        StudentDetails studentDetailsList2 = new StudentDetails();
        studentDetailsList2.studentName = "Areef";
        studentDetailsList2.studentAge = 15;
//        System.out.println(studentDetailsList1);
//        System.out.println(studentDetailsList1.studentName);
//        System.out.println(studentDetailsList2.isTeen);

        List<StudentDetails> sDList = new ArrayList();

        sDList.add(studentDetailsList1);
        sDList.add(studentDetailsList2);

        for (StudentDetails details : sDList){
           /* System.out.println(details.studentName);
            System.out.println(details.isTeen);*/

            System.out.println(details.toString());

        }
    }

}
