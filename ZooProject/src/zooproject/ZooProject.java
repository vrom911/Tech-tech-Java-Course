package zooproject;


import animals.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * @version 1.0    Mar 11, 2016
 *  Zoo Project - for understanding classes in Java
 * Imitation of simplified life circle of the ZOO
 * Each Zoo has Foodstore, some Enclosures.
 * Each Enclosure has Foodstore, some Animals, Zookeeper
 */
public class ZooProject {

    public static void main(String[] args) {
        // arrays for building ZOO from the data of txt file;
        ArrayList<Zoo> zoos = new ArrayList<>();
        ArrayList<Zookeeper> zookeepers = new ArrayList<>();
        ArrayList<Enclosure> enclosures = new ArrayList<>();
        ArrayList<Foodstore> foodstores = new ArrayList<>();
        
        
        
        try {
            Scanner in = new Scanner(new File("myZoo.txt"));
            while (in.hasNextLine()) {
                String str = in.nextLine();
                System.out.println(str);     
                if(str.split(":").length == 2) {
                    if (str.split(":[ ]*")[0].toLowerCase().equals("enclosure")) {
                        enclosures.add(new Enclosure());
                        zoos.get(zoos.size() - 1).addEnclosure(enclosures.get(enclosures.size() - 1));
                        
                        foodstores.add(new Foodstore());
                        enclosures.get(enclosures.size() - 1).setFoodstore(foodstores.get(foodstores.size() - 1));
                        enclosures.get(enclosures.size() - 1).setWaste(Integer.parseInt(str.split(":[ ]*")[1]));
                    }
                    else {
                        if (foodstores.get(foodstores.size() - 1).validFood(str.split(":[ ]*")[0].toLowerCase())) {
                            foodstores.get(foodstores.size() - 1).addFood(str.split(":[ ]*")[0].toLowerCase(), Integer.parseInt(str.split(":[ ]*")[1]));
                        }
                        else {
                            String[] ar = str.split(":[ ]*")[1].toLowerCase().split(",[ ]*");
                            switch (str.split(":[ ]*")[0].toLowerCase()) {
                                case "bear":
                                    enclosures.get(enclosures.size() - 1).addAnimal(new Bear(ar[0].charAt(0), Integer.parseInt(ar[1]), Integer.parseInt(ar[2]),enclosures.get(enclosures.size() - 1)));
                                    break;
                                case "chimpanzee":
                                    enclosures.get(enclosures.size() - 1).addAnimal(new Chimpanzee(ar[0].charAt(0), Integer.parseInt(ar[1]), Integer.parseInt(ar[2]),enclosures.get(enclosures.size() - 1)));
                                    break;
                                case "elephant":
                                    enclosures.get(enclosures.size() - 1).addAnimal(new Elephant(ar[0].charAt(0), Integer.parseInt(ar[1]), Integer.parseInt(ar[2]),enclosures.get(enclosures.size() - 1)));
                                    break;
                                case "giraffe":
                                    enclosures.get(enclosures.size() - 1).addAnimal(new Giraffe(ar[0].charAt(0), Integer.parseInt(ar[1]), Integer.parseInt(ar[2]),enclosures.get(enclosures.size() - 1)));
                                    break;
                                case "gorilla":
                                    enclosures.get(enclosures.size() - 1).addAnimal(new Gorilla(ar[0].charAt(0), Integer.parseInt(ar[1]), Integer.parseInt(ar[2]),enclosures.get(enclosures.size() - 1)));
                                    break;
                                case "lion":
                                    enclosures.get(enclosures.size() - 1).addAnimal(new Lion(ar[0].charAt(0), Integer.parseInt(ar[1]), Integer.parseInt(ar[2]),enclosures.get(enclosures.size() - 1)));
                                    break;
                                case "penguin":
                                    enclosures.get(enclosures.size() - 1).addAnimal(new Penguin(ar[0].charAt(0), Integer.parseInt(ar[1]), Integer.parseInt(ar[2]),enclosures.get(enclosures.size() - 1)));
                                    break;
                                case "tiger":
                                    enclosures.get(enclosures.size() - 1).addAnimal(new Tiger(ar[0].charAt(0), Integer.parseInt(ar[1]), Integer.parseInt(ar[2]),enclosures.get(enclosures.size() - 1)));
                                    break;
                                default:
                                    System.err.println("Wrong input! Is it a new type of animal you discovered?");
                                    break;
                            }
                        }
                              
                    }
                }
                else if(str.split(":").length == 1) {
                    switch (str.split(":[ ]*")[0].toLowerCase()) {
                        case "zoo":
                            zoos.add(new Zoo());
                            foodstores.add(new Foodstore()); 
                            zoos.get(zoos.size() - 1).setZooFood(foodstores.get(foodstores.size() - 1));
                            break;
                        case "zookeeper":
                            zookeepers.add(new Zookeeper());
                            zoos.get(zoos.size() - 1).addZookeeper(zookeepers.get(zookeepers.size() - 1), enclosures.get(enclosures.size() - 1));
                            break;
                        case "playzookeeper":
                            zookeepers.add(new PlayZookeeper());
                            zoos.get(zoos.size() - 1).addZookeeper(zookeepers.get(zookeepers.size() - 1), enclosures.get(enclosures.size() - 1));
                            break;
                        case "physiozookeeper":
                            zookeepers.add(new PhysioZookeeper());
                            zoos.get(zoos.size() - 1).addZookeeper(zookeepers.get(zookeepers.size() - 1), enclosures.get(enclosures.size() - 1));
                            break;
                    }
                }
            };

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ZooProject.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
//        Foodstore zoostore = new Foodstore();
//        zoostore.addFood("steak", 50);
//        zoostore.addFood("hay", 30);
//        System.out.println(zoostore);
//        Zookeeper zk = new Zookeeper();
//        Enclosure encl = new Enclosure();
//        Lion kitty = new Lion();
//        kitty.setAge(12);
//        kitty.setHealth(5);
//        Penguin kitty2 = new Penguin();
//        kitty2.setAge(10);
//        kitty2.setHealth(4);
//        encl.addAnimal(kitty);
//        encl.addAnimal(kitty2);
//        Zoo zoo = new Zoo();
//        zoo.addEnclosure(encl);
//        zoo.addZookeeper(zk, encl);
//        zoo.setZooFood(zoostore);
//        Foodstore fs = new Foodstore();
//        fs.addFood("hay", 20);
//        fs.addFood("steak", 5);
//        fs.addFood("celery", 10);
//        encl.setFoodstore(fs);
        
        // status -1 means all enclosures are empty
        for( Zoo zoo : zoos) {
            while(zoo.getStatus() != -1) {
                zoo.go();
             }
        }
    }


}
