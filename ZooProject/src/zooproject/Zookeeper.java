package zooproject;

import animals.Animal;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *The Zookeeper comes in three different types. There is a general Zookeeper,
 * and two specialist Zookeepers called the PlayZookeeper and the PhysioZookeeper.
 * 
 * @version 1.0 Mar 11, 2016
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * ZooProject
 * Class: Zookeeper
 */
public class Zookeeper {
    /** Each zookeper can work at one or more enclosures of one Zoo */
    private ArrayList<Enclosure> enclosures = new ArrayList();
    /** Treatment indicator shows if can do special play treatment (depends om zookeeper type) */
    protected int treatmentWay;

    public Zookeeper() {
        this.treatmentWay = 0;
    }
    /** @return list of Enclosures of this Zookeeper */
    public ArrayList<Enclosure> getEnclosures() {
        return enclosures;
    }
    /** Check if this Enclosure is under our Zookeeper's scope
     * @param enc - Enclosure to check
     * @return  true if it Zookeeper's Enclosure  */
    public boolean zokeepersEnclosure(Enclosure enc) {
        return this.enclosures.contains(enc);
    }
    /** Each month first thing to happen - Zookeper brings food in Encl storage
     * @param enc  which of Zookeeper's Enclosures we're talking about
     */
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
    /** Treatment of Animals. (depend of treatment level and animal type
     * @param enc current Enclosure  */
    public void treatAnimal(Enclosure enc) {
        int k = 2;
        int size = enc.getAnimals().size();
        Set<Integer> generated = new HashSet<>();
        Random r = new Random();
        while (generated.size() < size) {
            generated.add(r.nextInt(size));
        }
        for (int i = 0; i < size && k != 0; i++) {
            Animal a = enc.getAnimals().get((Integer) generated.toArray()[i]);
            if (a.getTreatLevel() == 0 ||  a.getTreatLevel() == this.treatmentWay) {
                a.treat();
                k--;
            }
        }
    }
    /** Each month should remove Waste animals do
     * @param enc  current Enclosure*/
    public void aMonthPasses(Enclosure enc) {
        int removed = min(enc.getWaste(), 20); 
        enc.addWaste((-1) * removed);
        System.out.println("ZOOKEEPER: " +removed +" waste cleaned up. " + enc.getWaste() +" waste remain");
        this.treatAnimal(enc);
    }
    
}
