import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Throwable {
        Test.test8();
        Test.test9();
        Test.test10();
        NameRecognizer r = new NameRecognizer();
        r.getInput();
        System.out.println("Initials is " + r.getInitials());
        System.out.println("Sex is " + r.getSex());
        System.out.println("Age is " + r.GetAge());
    }
}
