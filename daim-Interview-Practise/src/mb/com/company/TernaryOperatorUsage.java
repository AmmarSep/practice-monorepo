package mb.com.company;

public class TernaryOperatorUsage {
    public static void main(String[] args) {
        int time = 20;
        int a = 34;
        int x = 75;
        String result = (time<18)? "GoodMorning" : "GoodEvening";
        System.out.println(result);
        String varOne = (a == 1) ? "aa" : (a == 24) ? "gper" : "rooust";
        System.out.println(varOne);
        String grade
                = (x >= 90) ? "A"
                : (x >= 80) ? "B"
                : (x >= 70) ? "C"
                : (x >=60) ? "D"
                : "E";
        System.out.println(grade);
    }
}

