public class Test {
    private int testNumber;

    public void assertTrue(boolean statement) {
        if (statement) {
            System.out.println("Test " + testNumber + " has passed");
        } else {
            System.out.println("Test " + testNumber + " has failed");
        }
    }

    public static void test1() throws AssertionError {
        Test t = new Test();
        t.testNumber = 1;
        Complex a = new Complex(1, 1);
        Complex b = new Complex(2, -3);
        a.add(b);
        t.assertTrue(a.getReal() == 3);
    }

    public static void test2() {
        Test t = new Test();
        t.testNumber = 2;
        Complex a = new Complex(3, 2);
        Complex b = new Complex(4, 7);
        a.sub(b);
        t.assertTrue(a.getImg() == -5);
    }

    public static void test3() {
        Test t = new Test();
        t.testNumber = 3;
        Complex a = new Complex(234, 92);
        Complex b = new Complex(839, 6219);
        a.mul(b);
        t.assertTrue(a.getReal() == -375822);
    }

    public static void test4() {
        Test t = new Test();
        t.testNumber = 4;
        Complex a = new Complex(-1, 44);
        Complex b = new Complex(83, -602);
        a.div(b);
        t.assertTrue(Math.abs(a.getImg() - 0.008259024676882583) < 1e-9);
    }

    public static void test5() {
        Test t = new Test();
        t.testNumber = 5;
        Complex a = new Complex(61, -7);
        a.pow(3);
        t.assertTrue(a.getReal() == -543266);
    }
}
