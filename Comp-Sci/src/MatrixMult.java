/*
 * Duy Nguyen
 * MatrixMult.java
 * Multiplies two matrices together and returns Matrix C
 */

import java.util.Scanner;

public class MatrixMult {

    public static double[][] matrixInput(String matrixName, Scanner input) {

        // Asks for input and receives matrix data

        System.out.println(matrixName);

        System.out.print("Enter number of rows: ");
        int matrixRows = input.nextInt();

        System.out.print("Enter number of columns: ");
        int matrixColumns = input.nextInt();

        double[][] matrix = new double[matrixRows][matrixColumns];

        System.out.print("Enter contents by row: ");
        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < matrixColumns; j++) {

                matrix[i][j] = input.nextDouble();
            }
        }

        System.out.println();
        return matrix;
    }

    public static double[][] multiplyMatrix(double[][] matrixA,
                                            double[][] matrixB) {
        // Initializes matrix based off matrix A and B dimensions
        double[][] matrixC =
                new double[matrixA.length][matrixB[0].length];

        // Multiplies matrix elements together into output matrix

        for (int Bj = 0; Bj < matrixB[0].length; Bj++)
            for (int Ai = 0; Ai < matrixA.length; Ai++) {
                for (int Aj = 0; Aj < matrixA[0].length; Aj++) {

                    matrixC[Ai][Bj] += matrixA[Ai][Aj] * matrixB[Aj][Bj];
                }
            }

        return(matrixC);
    }

    public static void matrixOutput(double[][] matrix) {

        // Outputs matrix in readable form

        System.out.println("Matrix C is: ");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Takes input using scanner object, input
        Scanner input = new Scanner(System.in);

        double[][] matrixA = matrixInput("Matrix A", input);
        double[][] matrixB = matrixInput("Matrix B", input);

        // Checks if matrices are valid
        if (matrixA[0].length == matrixB.length) {

            double[][] matrixC = multiplyMatrix(matrixA, matrixB);

            matrixOutput(matrixC);
        }
        else {
            System.out.println("The matrices are not compatible, " +
                    "the matrices should be (m x n * n x k) ");
        }
    }
}
