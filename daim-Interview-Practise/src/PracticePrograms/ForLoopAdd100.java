package PracticePrograms;

import java.util.ArrayList;
import java.util.List;

public class ForLoopAdd100 {
    public static void main(String[] args) {
        List<Integer> objL = new ArrayList<>();
        for(int i = 0; i<=100; i++){
           objL.add(i);
        }
        System.out.println(objL);
        objL.forEach(System.out::println);
        for(int i : objL)
        {
            System.out.println(i);
        }
    }
}
