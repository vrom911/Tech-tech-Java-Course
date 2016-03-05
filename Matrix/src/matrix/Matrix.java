package matrix;

import java.util.Scanner;

/**
 *
 * @author Veronika
 * @version 1.0 - 05/03/2016
 * Multiplication of Matrix of any dimentions(possible for operations)
 * Input: 4 integers: dim of 1st matrix &dim of 2nd matrix
 * Output: Matrix - result of computation.
 */
public class Matrix {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int dim[] = new int[4];
        do {
            System.out.print("Please enter dimentions of matrixes:\n");
            for(int i = 0; i < 4; i++) {
                dim[i] = in.nextInt();
            }
        } while (dim[1] != dim[2]);
        
        double[][] a = makeArray(dim[0],dim[1]);
        double[][] b = makeArray(dim[2],dim[3]);
        double[][] c = multipl(a,b);
        System.out.print("Result\n");
        for (double[] c1 : c) {
            for (int j = 0; j < c[0].length; j++) {
                System.out.print(round(c1[j], 2) + " ");
            }
            System.out.print("\n");
        }
        System.out.print("---------\n");
        
    }
    public static double[][] makeArray(int num1, int num2) {
        double[][] arr = new double[num1][num2];
        for(double[] arr1 : arr){
            for ( int j = 0; j < num2; j++) {
//                arr[i][j] = j+2;
                arr1[j] = round((Math.random() * 100),2);
                System.out.print(arr1[j]+ " ");
            }
            System.out.print("\n");
        }
        System.out.print("---------\n");
        return arr;
    }
    public static double[][] multipl(double[][] a, double[][] b){
        // matrix: [x][n]  [n][m]
        int n = a[0].length, m = b[0].length,x = a.length;
        double[][] c = new double[x][m];
        for (int y = 0; y < x; y++){
            for ( int i = 0; i < m; i++) {
                for ( int j = 0; j < n; j++) {
                    c[y][i] += a[y][j] * b[j][i];
                }
            }
        }   
        return c;
    }
    
    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }  
}
