package arrayhomework;

import java.util.Scanner;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 -- 03/03/2016
 * The programm is homework for chapter Array 
 * In main function need to input number of operation you'd like to run.
 * #1 homeWork1() function. Finds max and min of random array. Input - array's length.
 * #2 homework2(false). Finds max and min in each row of n-dimention array.
 * #3 homeWork2(true). #2 and change max and min of each row between eah other
 */
public class ArrayHomework {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String hw;
        do {
            System.out.println(" #1 min&max of random array\n #2 min&max of 3x5 array\n #3 min&max of 3x5 array + change max and min allocation\n #4 EXIT");
            System.out.print("Enter the one you want: ");
            hw = in.nextLine();
            System.out.println("Execute hw #" + hw + " ...");
            if (hw.equals("1")) {
                homeWork1();
            } else if(hw.equals("2")) {
                homeWork2(false);
            } else if (hw.equals("3")) {
                homeWork2(true);
            } else if ( !hw.equals("4") ){
                System.out.println("Wrong input. Try again");
            } else {
                break;
            }
            
        } while ( true );
        System.out.println("Thank you! Bye-bye");  
    }
    public static void homeWork1() {
        Scanner in = new Scanner(System.in);
        System.out.println("You've chosen #1 find the min and max of an Array");
        int len = -1;
        while(len < 0){
            System.out.print("Please, enter the length of an Array:");
            len = in.nextInt();
        }
        
        double[] a = new double[len];
        System.out.print("Here's what you've got: ");
        for(int i=0; i < a.length; i++){
            a[i] = round((Math.random() * 100),2);
            System.out.print(a[i]+ " ");
        }
        int[] maxmin;
        maxmin = maxMinArray(a);
        System.out.print("\nMax: Array[" + maxmin[0] + "] = " + a[maxmin[0]]);
        System.out.println("Min: Array[" + maxmin[1] + "] = " + a[maxmin[1]]);
    }
    public static void homeWork2(boolean allocate) {
        double[][] a = new double[3][5];
        int[] a_max = new int[3];
        int[] a_min = new int[3];
        int[] receive;
        System.out.println("Here's what you've got: ");
        for(int i=0; i < 3; i++){
            for ( int j = 0; j < 5; j++) {
                a[i][j] = round((Math.random() * 100),2);
                System.out.print(a[i][j]+ " ");
            }
            System.out.print("\n");
            receive = maxMinArray(a[i]);
            a_max[i] = receive[0];
            a_min[i] = receive[1];
        }
        int a_main_max = 0,a_main_min = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("For " + i + " row Max: Array[" + a_max[i] + "] = " + a[i][a_max[i]]);
            System.out.println(" Min: Array[" + a_min[i] + "] = " + a[i][a_min[i]]);
            if (a[i][a_max[i]] > a[i][a_main_max])
                a_main_max = i;
            if (a[i][a_min[i]] < a[i][a_main_min])
                a_main_min = i;
        }
        System.out.println("Array max is: Array["+a_main_max+"]["+a_max[a_main_max]+"] = "+a[a_main_max][a_max[a_main_max]] );
        System.out.println("Array min is: Array["+a_main_min+"]["+a_min[a_main_min]+"] = "+a[a_main_min][a_min[a_main_min]] );        
        if(allocate) {
            for (int i=0; i < 3; i++) {
                double empty = a[i][a_max[i]];
                a[i][a_max[i]] = a[i][a_min[i]];
                a[i][a_min[i]] = empty;
            }
            System.out.println("Here's what you've got after operations: ");
            for(int i=0; i < 3; i++){
                for ( int j = 0; j < 5; j++) {
                    System.out.print(a[i][j]+ " ");
                }
                System.out.print("\n");
            }
        }
    
    }
    
    
    public static int[] maxMinArray(double[] a) {
        /*  This function receive an Array of doubles
            returns an int Array of indexs where
            1st element is Index of Maximum of the given Array
            2nd element is Index of Minimum of the given Array
        */
        int max_val = 0;
        int min_val = 0;
        int[] indx = new int[2];
        for( int i = 1; i < a.length; i++) {
            if (a[max_val] < a[i]) 
                max_val = i;
            if (a[min_val] > a[i]) 
                min_val = i;
        }
        indx[0] = max_val;
        indx[1] = min_val;
        return indx;
        
    }
    
    // rounding double - two numbers after'.' , ex.: 3.14
    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }
    
}
