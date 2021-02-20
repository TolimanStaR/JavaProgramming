import org.jetbrains.annotations.NotNull;

public class Matrix {
    private final int rows;
    private final int columns;
    private double[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
    }

    public Matrix(Matrix example) {
        this.rows = example.rows;
        this.columns = example.columns;
        matrix = new double[rows][columns];
        for (int i = 0; i < rows; ++i) {
            if (columns >= 0) System.arraycopy(example.matrix[i], 0, matrix[i], 0, columns);
        }
    }

    public Matrix(double array[][]) {
        this.rows = array.length;
        this.columns = array[0].length;
        this.matrix = new double[rows][columns];
        for (int i = 0; i < rows; ++i) {
            System.arraycopy(array[i], 0, matrix[i], 0, columns);
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                result.append(matrix[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void add(Matrix other) {
        if (rows != other.rows && columns != other.columns) {
            System.out.println("It is not possible");
            return;
        }
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                matrix[i][j] += other.matrix[i][j];
            }
        }
    }

    public void sub(Matrix other) {
        if (rows != other.rows && columns != other.columns) {
            System.out.println("It is not possible");
            return;
        }
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                matrix[i][j] -= other.matrix[i][j];
            }
        }
    }

    public void scalarMul(double scalar) {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                matrix[i][j] *= scalar;
            }
        }
    }

    public void scalarDiv(double scalar) {
        scalarMul(1 / scalar);
    }

    public void dotMul(@NotNull Matrix other) {
        if (columns != other.rows) {
            System.out.println("It is not possible");
            return;
        }
        matrix = new double[rows][other.columns];
        for (int i = 0; i < rows; ++i) {
            for (int k = 0; k < other.columns; ++k) {
                double newElement = 0;
                for (int j = 0; j < columns; ++j) {
                    newElement += matrix[i][j] * other.matrix[j][k];
                }
            }
        }
    }

    public double det() {
        if (rows != columns) {
            System.out.println("It is not possible to count determinator");
            return 0;
        }
        double result = 0;
        int move = 0;
        for (int i = 0; i < columns; ++i) {
            double diag = 1;
            for (int j = 0; j < rows; ++j) {
                diag *= matrix[j][(j + move) % rows];
            }
            result += diag;
            diag = 1;
            for (int j = rows - 1; j > -1; --j) {
                diag *= matrix[j][move];
            }
            result -= diag;
            move++;
        }
        return result;
    }

    public double minor(int exRow, int exCol) {
        double result = 0;
        int move = 0;
        for (int i = 0; i < columns; ++i) {
            double diag = 1;
            for (int j = 0; j < rows; ++j) {
                if (j != exRow || exCol != (j + move) % rows) {
                    diag *= matrix[j][(j + move) % rows];
                }
            }
            result += diag;
            diag = 1;
            for (int j = rows - 1; j > -1; --j) {
                if (j != exRow || exCol != move) {
                    diag *= matrix[j][move];
                }
            }
            result -= diag;
            move++;
        }
        return result;
    }

    public Matrix transpond() {
        Matrix x = new Matrix(columns, rows);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                x.matrix[j][i] = matrix[i][j];
            }
        }
        return x;
    }

    public Matrix inverse() {
        if (rows != columns || this.det() == 0) {
            System.out.println("It is not possible to make inverse matrix");
            return new Matrix(0, 0);
        }
        Matrix x = new Matrix(this.matrix);
        x = x.transpond();
        double d = x.det();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < rows; ++j) {
               x.matrix[i][j] = x.minor(i, j);
            }
        }
        x.scalarDiv(d);
        return x;
    }
}
