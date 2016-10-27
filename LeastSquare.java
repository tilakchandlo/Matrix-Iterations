public class LeastSquare {

    // The data to be fitted.
    public static final double[][] A = new double[][] {{0.0, 1.0}, {1.0, 1.0}, {2.0, 1.0}, {3.0, 1.0}, {4.0, 1.0}};
    public static final double[][] B = new double[][] {{1.5}, {2.5}, {3.5}, {5.0}, {7.5}};
    public static final double[][] A1 = new double[][] {{0.0, 0.0, 1.0}, {1.0, 1.0, 1.0}, {4.0, 2.0, 1.0}, {9.0, 3.0, 1.0}, {16.0, 4.0, 1.0}};
    // Method for multiplying matrices together
    public static double[][] multiply(double[][] a, double[][] b) {
    int arow = a.length;
    int acol = a[0].length;
    int brow = b.length;
    int bcol = b[0].length;
    if (acol != brow)
      throw new IllegalArgumentException("Matrices don't match: " + acol + " != " + brow);

    double [][] result = new double[arow][bcol];
    // Multiply the two matrices
    for (int i=0; i<arow; i++)
      for (int j=0; j<bcol; j++)
        for (int k=0; k<acol; k++)
        result[i][j] += a[i][k] *b[k][j];
    return result;
    }
    // Method for transposing matrices together
    public static double[][] transpose(double [][] a) {
        double[][] result = new double[a[0].length][a.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
            result[j][i] = a[i][j];
        return result;
    }
    // Method for finding the inverse of a 2 x 2 matrix
    public static double[][] inverse2x2(double[][] m) {
        double[][] result = new double[m.length][m.length];
        double det = ((m[0][0] * m[1][1]) - (m[0][1] * m[1][0]));
        result[0][0] = m[1][1]; result[0][1] = -m[0][1]; result[1][0] = -m[1][0]; result[1][1] = m[0][0];
        result[0][0] /= det; result[0][1] /= det; result[1][0] /= det; result[1][1] /= det;
        return result;
    }
    // Method for finding the inverse of a 3 x 3 matrix
    public static double[][] inverse3x3(double[][] m) {
        double det = m[0][0] * (m[1][1] * m[2][2] - m[2][1] * m[1][2]) -
                m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0]) +
                m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);

        double invdet = 1 / det;
        double[][] result = new double[m.length][m.length];
        result[0][0] = (m[1][1] * m[2][2] - m[2][1] * m[1][2]) * invdet;
        result[0][1] = (m[0][2] * m[2][1] - m[0][1] * m[2][2]) * invdet;
        result[0][2] = (m[0][1] * m[1][2] - m[0][2] * m[1][1]) * invdet;
        result[1][0] = (m[1][2] * m[2][0] - m[1][0] * m[2][2]) * invdet;
        result[1][1] = (m[0][0] * m[2][2] - m[0][2] * m[2][0]) * invdet;
        result[1][2] = (m[1][0] * m[0][2] - m[0][0] * m[1][2]) * invdet;
        result[2][0] = (m[1][0] * m[2][1] - m[2][0] * m[1][1]) * invdet;
        result[2][1] = (m[2][0] * m[0][1] - m[0][0] * m[2][1]) * invdet;
        result[2][2] = (m[0][0] * m[1][1] - m[1][0] * m[0][1]) * invdet;
        return result;
    }
    // The `main' method actually implements
    // the least squares fit.
    public static void main(String[] args) {
        // For part 1a - linear model
        double [][] aT = transpose (A);
        double [][] aTA = multiply(aT, A);
        double [][] aTB = multiply(aT, B);
        double [][] invaTA = inverse2x2(aTA);
        double [][] ans = multiply(invaTA, aTB);
        //System.out.println(ans[0][0] + "\n" +ans[1][0]);
        // Finally output some data about the fit.
        System.out.println("Line of best fit (LINEAR): y = "
                             + ans[0][0] + " * x + " + ans[1][0]);

        // For part 1c - quadratic model
        double [][] aT1 = transpose (A1);
        double [][] aTA1 = multiply(aT1, A1);
        double [][] aTB1 = multiply(aT1, B);
        double [][] invaTA1 = inverse3x3(aTA1);
        double [][] ans1 = multiply(invaTA1, aTB1);
        //System.out.println(ans[0][0] + "\n" +ans[1][0]);
        // Finally output some data about the fit.
        System.out.println("Line of best fit (QUADRATIC): y = "
                             + ans1[0][0] + " * x^2 + " + ans1[1][0] + " * x + " + ans1[2][0]);
    }
}
