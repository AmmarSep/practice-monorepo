package PracticePrograms;

import java.util.ArrayList;
import java.util.List;

class DemoApp {

    public static void main(String[] args) {
        String student1 = "Sabqiue";
        Integer age = 23;
        String student2 = "Mohamed";
        String student3 = "dsfg";
        String student4 = "ddsfgssfg";

        List<String> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        List<StudentDetails> studentDetailsList = new ArrayList<>();


        StudentDetails studentDetails1 = new StudentDetails();
        studentDetails1.studentName = "Sabique";
        studentDetails1.age = 27;

        StudentDetails studentDetails2 = new StudentDetails();
        studentDetails2.studentName = "moahmed";
        studentDetails2.age = 17;
        studentDetails2.isEighteen = false;
//        String stduentName = studentList.get(0);  // result mohamed
//        String stduentName = studentList.get(1);  // result mohamed
//        String stduentName = studentList.get(2);  // result mohamed
        studentDetailsList.add(studentDetails1);
        studentDetailsList.add(studentDetails2);

        for(StudentDetails sd : studentDetailsList){
            System.out.println(sd.studentName);
            System.out.println(sd.age);
            System.out.println(sd.isEighteen);

        }


//        for (String studentName : studentList) {
//            System.out.println(studentName);
//        }
    }

    public static class StudentDetails {
        public String studentName;
        public Integer age;
        public Boolean isEighteen = true;
    }
}