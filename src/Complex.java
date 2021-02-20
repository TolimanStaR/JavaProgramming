import java.math.BigInteger;

public class Complex {
    private double real;
    private double img;

    public Complex(double real, double img) {
        this.real = real;
        this.img = img;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImg(double img) {
        this.img = img;
    }

    public double getReal() {
        return real;
    }

    public double getImg() {
        return img;
    }

    public String toString() {
        String str = "";
        str += real;
        if (img > 0) {
            str += "+" + img + "j\n";
        } else if (img < 0) {
            str += img + "j\n";
        }
        return str;
    }

    public void add(Complex other) {
        real += other.real;
        img += other.img;
    }

    public static Complex add(Complex first, Complex second) {
        return new Complex(first.real + second.real, first.img + second.img);
    }

    public void sub(Complex other) {
        real -= other.real;
        img -= other.img;
    }

    public static Complex sub(Complex first, Complex second) {
        return new Complex(first.real - second.real, first.img - second.img);
    }

    public void mul(Complex other) {
        double newReal = real * other.real - img * other.img;
        double newImg = real * other.img + img * other.real;
        real = newReal;
        img = newImg;
    }

    public static Complex mul(Complex first, Complex second) {
        return new Complex(first.real * second.real - first.img * second.img,
                first.real * second.img + first.img * second.real);
    }

    public void div(Complex other) {
        double newReal = (real * other.real + img * other.img) / (other.real * other.real + other.img * other.img);
        double newImg = (img * other.real - real * other.img) / (other.real * other.real + other.img * other.img);
        real = newReal;
        img = newImg;
    }

    public static Complex div(Complex first, Complex second) {
        return new Complex(
                (first.real * second.real + first.img * second.img) / (second.real * second.real + second.img * second.img),
                (first.img * second.real - first.real * second.img) / (second.real * second.real + second.img * second.img)
        );
    }

    public static BigInteger factorial(long n) {
        BigInteger result = new BigInteger("1");
        for (BigInteger i = new BigInteger("2"); 0 != i.compareTo(new BigInteger(String.valueOf(n))); i = i.add(new BigInteger("1"))) {
            result = result.multiply(i);
        }
        return result;
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; ++i) {
            result *= i;
        }
        return result;
    }

    public static int combinations(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    public static int evenImgOnePower(int x) {
        if (x % 4 == 0) {
            return 1;
        }
        return -1;
    }

    public void pow(int power) {
        double newReal = 0;
        double newImg = 0;
        for (int i = 0; i < power; i += 2) {
            newReal += evenImgOnePower(i) * Math.pow(real, power - 1) * Math.pow(img, i) * combinations(power, i);
        }
        for (int i = 1; i < power; ++i) {
            newImg += evenImgOnePower(i - 1) * Math.pow(real, i) * Math.pow(img, power - i) * combinations(power, i);
        }
        real = newReal;
        img = newImg;
    }

    public static Complex pow(Complex number, int power) {
        double real = 0;
        double img = 0;
        for (int i = 0; i < power; i += 2) {
            real += evenImgOnePower(i) * Math.pow(number.real, power - 1) * Math.pow(number.img, i) * combinations(power, i);
        }
        for (int i = 1; i < power; ++i) {
            img += evenImgOnePower(i - 1) * Math.pow(number.real, i) * Math.pow(number.img, power - i) * combinations(power, i);
        }
        return new Complex(real, img);
    }

    public void conjugate() {
        img *= -1;
    }

    public static Complex conjugate(Complex number) {
        return new Complex(number.real, -number.img);
    }

    public double abs() {
        return Math.sqrt(real * real + img * img);
    }

    public static double getK(double real, double img) {
        return img / real;
    }

    public static double[] getValuesAtCurrentX(double real, double img, double pos) {
        double[] result = new double[2];
        result[0] = pos;
        result[1] = pos * getK(real, img);
        return result;
    }

    public void visualizeAtComplexPlane() {
        StringBuilder result = new StringBuilder();
        result.append("      IM\n");
        result.append("      ^\n");
        for (int i = 19; i > -1; --i) {
            result.append(String.format("%.2f", (double) i / 10 * real)).append("  |");
            for (int j = 0; j < 40; ++j) {
                double curI = (double) i / 20 * real;
                double curJ = (double) j / 40 * real;
                double[] value = getValuesAtCurrentX(real, img / 2, curJ);
                if (Math.abs(value[0] - curJ) < 0.1 && Math.abs(value[1] - curI) < 0.1) {
                    result.append("#");
                } else {
                    result.append(" ");
                }
            }
            result.append("\n");
        }
        result.append("      |" + "________________________________________-> RE\n           ");
        result.append(String.format("%.2f", (double) real * 0.2)).append("    ");
        result.append(String.format("%.2f", (double) real * 0.4)).append("    ");
        result.append(String.format("%.2f", (double) real * 0.6)).append("    ");
        result.append(String.format("%.2f", (double) real * 0.8)).append("    ");
        result.append(String.format("%.2f", (double) real * 1)).append("    \n");
        System.out.print(result);
    }
}
