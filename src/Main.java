import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        Test.test1();
//        Test.test2();
//        Test.test3();
//        Test.test4();
//        Test.test5();
//        Complex a = new Complex(4.38, 2.61);
//        a.visualizeAtComplexPlane();
//        Complex b = new Complex(3.59, 6.21);
//        b.visualizeAtComplexPlane();
//        Test.test6();
//        Test.test7();
//        ;

        FileHandler f = new FileHandler("HardAsARock.txt");
        f.getDataFromFile();
        System.out.print(f);

    }
}
