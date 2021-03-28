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
        t.assertTrue(a.abs() == 543339.9983849891);
    }

    public static void test6() {
        Test t = new Test();
        t.testNumber = 6;
        double[][] a = {{2, 0}, {0, 3}};
        Matrix x = new Matrix(a);
        t.assertTrue(x.det() == 6);
    }

    public static void test7() {
        Test t = new Test();
        t.testNumber = 7;
        double[][] a = {{1, 0}, {0, 1}};
        Matrix x = new Matrix(a);
        Matrix y = x.inverse();
        t.assertTrue(x.det() == y.det());
    }

    public static void test8() {
        Test t = new Test();
        t.testNumber = 8;
        NameRecognizer r = new NameRecognizer("Дыряев Даниил Александрович", "03.04.2001");
        t.assertTrue(r.GetAge() == 19); // Не будет проходить после 3 апреля, потому что мне будет уже 20 лет
    }

    public static void test9() {
        Test t = new Test();
        t.testNumber = 9;
        NameRecognizer r = new NameRecognizer("Дыряев Даниил Александрович", "03.04.2001");
        t.assertTrue(r.getSex().equals("Male"));
    }

    public static void test10() {
        Test t = new Test();
        t.testNumber = 10;
        NameRecognizer r = new NameRecognizer("Дыряев Даниил Александрович", "03.04.2001");
        t.assertTrue(r.getInitials().equals("Д. Д. А. "));
    }
}
