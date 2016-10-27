
import java.util.Arrays;

import Jama.Matrix;

public class Jacobi {
        private double[][] arrayOne = new double[][];

        public static double roundToDecimals(double d, int c) {
                int temp = (int)((d * Math.pow(10,c)));
                return (((double)temp) / Math.pow(10,c));
        }

        public void findLargestOffDiagonal() {
                int maxI = -1, maxJ = -1;
                double maxOff = -1;
                for (int i = 0; i < 5; i++) {
                        for (int j = i; j < 5; j++) {
                                if (i != j && Math.abs(array[i][j]) > maxOff) {
                                        maxI = i;
                                        maxJ = j;
                                        maxOff = Math.abs(array[i][j]);
                                }
                        }
                }
                System.out.println("Max Offset at: (" + (maxI + 1) + ", " + (maxJ + 1) + ") with value: " + maxOff);
                diagonalizeTwoByTwo(maxI, maxJ);
        }

        public void diagonalizeTwoByTwo(int i, int j) {
                double array2[][] = {{array[i][i], array[i][j]}, {array[j][i], array[j][j]}};
                for (int x = 0; x < 2; x++) {
                        System.out.println(Arrays.toString(array2[x]));
                }
                Matrix A = new Matrix(array2);
                Matrix U = A.eig().getV();
                System.out.println("U:");
                for (int x = 0; x < 2; x++) {
                        System.out.println(Arrays.toString(U.getArray()[x]));
                }
                System.out.println("D:");
                Matrix D = A.eig().getD();
                for (int x = 0; x < 2; x++) {
                        System.out.println(Arrays.toString(D.getArray()[x]));
                }
                Matrix G = new Matrix(new double[][]{{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}});
                G.set(i, i, U.get(0, 0));
                G.set(i, j, U.get(0, 1));
                G.set(j, i, U.get(1, 0));
                G.set(j, j, U.get(1, 1));
                System.out.println("G: ");
                for (int x = 0; x < 5; x++) {
                        System.out.println(Arrays.toString(G.getArray()[x]));
                }

                Matrix B = G.transpose().times(new Matrix(array)).times(G);
                System.out.println("New B: ");
                for (int x = 0; x < 5; x++) {
                        System.out.println(Arrays.toString(B.getArray()[x]));
                }
                array = B.getArray();
                for(int i2 = 0; i2 < array.length; i2++) {
                        for(int j2 = 0; j2 < array[0].length; j2++) {
                                array[i2][j2] = roundToDecimals(array[i2][j2], 2);
                        }
                }
        }

        public double calculateOffset() {
                double ans = 0;
                for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                                if (i != j) {
                                        ans += array[i][j] * array[i][j];
                                }
                        }
                }
                return ans;
        }

        public double[][] getArray() {
                return array;
        }

    public static void main(String[] args) {
        Jacobi jacobi = new Jacobi();
        jacobi.randomize();
        for (int x = 0; x < 5; x++) {
                System.out.println(Arrays.toString(jacobi.array[x]));
        }
        jacobi.findLargestOffDiagonal();
        System.out.println(jacobi.calculateOffset());
    }
}