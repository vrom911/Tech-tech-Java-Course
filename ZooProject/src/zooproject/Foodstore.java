package zooproject;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Food is held in a Foodstore class.
 * Each Enclosure will have a Foodstore where the animals get their food from, and the Zoo will have a Foodstore where food is delivered to the Zoo.
 * @version    1.0 Mar 11, 2016
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * ZooProject
 * Class: Foodstore
 */
public class Foodstore {
    /** Food we have in the storage and quantity*/
    private Hashtable<String, Integer> foods = new Hashtable<>();
    /** this is the info for eating process:possible food, health,waste */
    public final Hashtable<String, int[]> foodsInfo = makeFoodInfo(); 

    /**
     * @return  food hashtable in current Storage */
    public Hashtable<String, Integer> getFoods() {
        return foods;
    }
    /** adds a number of items of food of the specified type to the Foodstore
     * Only called by Zookeeper or initialization of zoo storage
     * @param food  food name to add
     * @param  number quantity of food to add
     */
    public void addFood(String food, int number) {
        if (this.foodsInfo.containsKey(food) && number > 0) {
            this.foods.put(food, number);
            System.out.println("ZOOKEEPER: Added "+ number + " " + food);
        } else {
            System.err.println("ZOOKEEPER: Impossible to add unknown food. It could be dangerous for pure little animals ");
        }
        
    }
    /**
     * @return  true if Foodstore is empty*/
    public boolean hasnoFood() {
        return  this.foods.isEmpty();
    }
    /**check whether food can be taken from storage
     *  no need to check on hasnoFood()
     * @param food food name
     * @return  true if there is food of particular type in the storage*/
    public boolean canTakeFood(String food) {
        return this.foods.get(food) > 0;
    }
    /** If animal ate something from the Storage
     * @param food food name */
    public void takeFood(String food) {
        this.foods.put(food, this.foods.get(food) - 1) ;
    }
    /** if bulk of particular food is empty - delete it from the list */
    public void removeEmpty( ) {
        ArrayList<String> toremove = new ArrayList();
        for (String food : this.foods.keySet()) {
            if (this.foods.get(food) == 0) {
                toremove.add(food);
            }
        }
        for (String food : toremove) {
            this.foods.remove(food);
        }
    }
    /**
     * @return   number of types of food in the Storage*/
    public int sizeOfFoodstore() {
        return this.foods.size();
    }
    /**
     * @param food  food name
     * @return  true if the food is possible to use*/
    public boolean validFood(String food) {
        return this.foodsInfo.keySet().contains(food);
    }
    /** here we have <code><Foodname : {health, waste}> </code>*/
    private Hashtable makeFoodInfo() {
        Hashtable<String, int[]> foodInfo = new Hashtable<>();
        int[] helw = {1,4};
        foodInfo.put("hay",helw.clone());
        helw[0] =3;
        foodInfo.put("steak", helw.clone());
        helw[0] =2; helw[1] =3;
        foodInfo.put("fruit", helw.clone());
        helw[0] = 0; helw[1] = 1;
        foodInfo.put("celery", helw.clone());
        helw[0] = 3; helw[1] = 2;
        foodInfo.put("fish", helw.clone());
        helw[0] = 1; helw[1] = 3;
        foodInfo.put("ice cream", helw.clone());
        return foodInfo;
    }

    @Override
    public String toString() {
        return "Foodstore{" + "foods=" + foods + '}';
    }
}
