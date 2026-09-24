package ArraysInBuilt;

import java.util.Arrays;

public class SortStringArray {
    public static void main(String[] args) {
        String[] names = {"Mohamed", "Gabriel", "Ali", "Frank"};
        Arrays.sort(names);
        System.out.println(names);
        for(String name : names){
            System.out.println(name);
        }
    }

}
