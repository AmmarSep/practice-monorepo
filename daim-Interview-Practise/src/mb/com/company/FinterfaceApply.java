package mb.com.company;

import org.w3c.dom.ls.LSOutput;

public class FinterfaceApply {


    public static void main(String[] args) {
        Finterface val = (a,b) -> a*b;
        System.out.println(val.multiply(5,32));
    }
}
