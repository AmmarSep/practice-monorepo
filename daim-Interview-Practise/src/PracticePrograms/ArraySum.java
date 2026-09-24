package PracticePrograms;

public class ArraySum {
        public static void main(String[] args){
            int[] arrEle = new int[]{3,4,8,10,14,5};
            int sumEven= 0;
            int sumOdd = 0;
            for(int i =0; i< arrEle.length; i++){
                if(arrEle[i] %2==0){
                    sumEven = sumEven+arrEle[i];

                }
                if(arrEle[i]%2 !=0){
                    sumOdd = sumOdd + arrEle[i];
                }
            }
            System.out.println("Print sum of even number : " +sumEven);
            System.out.println("Print sum of odd number : " +sumOdd);
        }
    }
