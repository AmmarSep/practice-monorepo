package PracticePrograms;

public class LargestArrayElement {
    public static void main(String[] args) {
        int[] arrayEle = new int[]{324,34,324,53,265,2,234,234,52,231,325};
        int max = arrayEle[0];
        for( int i =0; i<arrayEle.length; i++){
            if(arrayEle[i]> max){
                max = arrayEle[i];
            }
        }
        System.out.println("The largest element in a Array is :" +max);
    }
}
