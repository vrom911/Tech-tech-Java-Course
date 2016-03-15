package zooproject;

import static java.lang.Math.min;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Zookeeper
 */
public class Zookeeper {
    private ArrayList<Enclosure> enclosures = new ArrayList();  //  each zookeper can wark at one or more enclosures of one Zoo
    protected int treatmentWay; //  treatment depends om zookeeper type

    public Zookeeper() {
        this.treatmentWay = 0;
    }

    public ArrayList<Enclosure> getEnclosures() {
        return enclosures;
    }
    
    
    public boolean zokeepersEnclosure(Enclosure enc) {
        return this.enclosures.contains(enc);
    }
    public void putFoodInStorage(Enclosure enc) {
        if(this.zokeepersEnclosure(enc)) {
            if (enc.getStatus() != -1) {
                ArrayList<String> pfl = enc.getPreferedFoodList();
                Foodstore fs = enc.getZoo().getFoodstore();

                if (!fs.hasnoFood()) {
                    int val = 0;
                    for (String food : pfl) {
                        if (enc.getZoo().getFoodstore().getFoods().containsKey(food) && enc.getZoo().getFoodstore().canTakeFood(food)) { 
                            val += 1;
                        }  
                    }

                    if (val != 0) {
                        val = 20 / val;
                        if (val ==0 ) {
                            val = 1;
                        }
                        for (String food : pfl) {
                            if (fs.getFoods().containsKey(food) && enc.getZoo().getFoodstore().canTakeFood(food)) {
                                int num = min(val, enc.getZoo().getFoodstore().getFoods().get(food));
                                for( int i = 0; i < num; i++) {
                                    enc.getZoo().getFoodstore().takeFood(food);
                                }
                                enc.getFoodstore().addFood(food, min(val,num));
                            }
                        }
                    } else {
                        System.out.println("ZOOKEEPER: There is no proper food for this animals in the zoo stock..");
                    }

                } else {
                    System.out.println("ZOOKEEPER: Oops! Zoo food Storage is empty ;( pure animals.. They all are going to die");
                }
            } 
        }
    }
    public void treatAnimal(Enclosure enc) {
        // time for treatment
        // only possible type of treatments
        int k = 2;
        int size = enc.getAnimals().size();
        Set<Integer> generated = new HashSet<Integer>();
        Random r = new Random();
        while (generated.size() < size) {
            generated.add(r.nextInt(size));
        }
        for (int i = 0; i < size && k != 0; i++) {
            
            if (enc.getAnimals().get((Integer) generated.toArray()[i]).getTreatLevel() == 0 ||  enc.getAnimals().get((Integer) generated.toArray()[i]).getTreatLevel() == this.treatmentWay) {
                enc.getAnimals().get((Integer) generated.toArray()[i]).treat();
                k--;
            }
        }
    }
    public void aMonthPasses(Enclosure enc) {
        // removes waste each month
        int removed = min(enc.getWaste(), 20); 
        enc.addWaste((-1) * removed);
        System.out.println("ZOOKEEPER: " +removed +" waste cleaned up. " + enc.getWaste() +" waste remain");
        this.treatAnimal(enc);
        
    }
    
}
