package listdemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @version 1.0 Apr 6, 2016
 * @author Veronika Romashkina
 * @email vrom911@gmail.com
 */
public class ListDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Integer> listAr = new ArrayList<>();
        List<Integer> listLink = new LinkedList<>();
        Random rand = new Random();
        for (int i = 1; i <= 100000; i++) {

            int value = rand.nextInt(100000);
            listAr.add(value);
            value = rand.nextInt(100000);
            listLink.add(value);
        }
        
        System.out.format("%12s | arrayList | linkedList%n","");
        for (int i = 0; i< 45; i++) { System.out.print("-");}

        long start, finish;
        int val;
        
        
        System.out.format("%n%13s"," get | ");
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            val = listAr.get(i);
        }
        finish = System.currentTimeMillis();
        System.out.format("%11d |",(finish-start));
        
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            val = listLink.get(i);
        }
        finish = System.currentTimeMillis();
        System.out.format("%9d",(finish-start));
        
        
        
        System.out.format("%n%13s","set | ");
        
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            val = listAr.set(i, i);
        }
        finish = System.currentTimeMillis();
        System.out.format("%11d | ",(finish-start));
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            val = listLink.set(i, i);
        }
        finish = System.currentTimeMillis();
        System.out.format("%9d",(finish-start));
        
        
        
        System.out.format("%n%12s","add | ");
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            listAr.add(i);
        }
        finish = System.currentTimeMillis();
        System.out.format("%11d | ",(finish-start));
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            listLink.add(i);
        }
        finish = System.currentTimeMillis();
        System.out.format("%9d",(finish-start));
        
        
        
        
        System.out.format("%n%10s","remove | ");
        
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            listAr.remove(i);
        }
        finish = System.currentTimeMillis();
        System.out.format("%9d | ",(finish-start));
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            listLink.remove(i);
        }
        finish = System.currentTimeMillis();
        System.out.format("%9d%n",(finish-start));
        
        for (int i = 0; i< 45; i++) { System.out.print("-");}
        System.out.println("");
        
        
//        list1.removeAll(list2);
//        System.out.println(list1);
//        list1.addAll(list2);
//         System.out.println(list1);
//         Iterator<Integer> it1 = list1.iterator();
//         while(it1.hasNext()) {
//             int value = it1.next();
//             System.out.println("value: " + value);
//         }
//        
//        System.out.println("----------------------------------");
//         ListIterator<Integer> it2 = list1.listIterator(list1.size());
//         
//         while( it2.hasPrevious()) {
//             int value =  it2.previous();
//             System.out.println("value pr: " + value);
//         }
//         
//         
//        
//        int x = 0;
//        Scanner in = new Scanner(System.in);
//        
//        while (x != -1) {
//            System.out.println("Enter value (-1 for exit)");
//        
//            x = in.nextInt();
//
//            System.out.println("x = " + x);
//            int counter = 0;
//            for (int  i = 0; i<20; i++) {
//                if (x == list1.get(i)) {
//                    counter++;
//                }
//            }
//            if (counter > 0) {
//                System.out.println("Found " + x + " " + counter + " times");
//            } else {
//                System.out.println(x + " not found");
//            }
//        }
//        
        
         
    }

}
