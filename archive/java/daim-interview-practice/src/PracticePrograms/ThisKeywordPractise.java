package PracticePrograms;

public class ThisKeywordPractise {
    int x;
    public ThisKeywordPractise(int x){
        this.x = x;
    }

    public static void main(String[] args) {
        ThisKeyword ob = new ThisKeyword();
        ThisKeywordPractise obP = new ThisKeywordPractise(9);
        System.out.println("obje val is " +ob);
        System.out.println("obje real value is " +ob.x);
        System.out.println("Practise value is " +obP.x);
    }
}
