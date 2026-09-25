package PracticePrograms;

public class StudentDetails {
    public String studentName;
    public int studentAge;
    public boolean isTeen = true;

    @Override
    public String toString() {
        return
                "studentName='" + studentName + '\'' +
                ", studentAge=" + studentAge +
                ", isTeen=" + isTeen ;
    }
}
