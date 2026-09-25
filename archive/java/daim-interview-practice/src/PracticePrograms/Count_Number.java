package PracticePrograms;

class Count_Number {
    public static void main(String[] args) {
        int num = 324343;
        int count = 0;
        while (num > 0) {
            num = num/10;
            count++;

        }
        System.out.println("Total digits in a number:" +count);
    }
}
