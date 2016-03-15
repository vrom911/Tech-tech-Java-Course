package zooproject;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * @version    1.0 Mar 11, 2016
 * ZooProject
 * Class: Foodstore
 */
public class Foodstore {

    private Hashtable<String, Integer> foods = new Hashtable<>();   // what do we have in the storage
    public final Hashtable<String, int[]> foodsInfo = makeFoodInfo(); //     this is the info for eating process:possible food, health,waste

    public Hashtable<String, Integer> getFoods() {
        return foods;
    }
    
    public void addFood(String food, int number) {
        // adds a number of items of food of the specified type to the Foodstore
        // Only called by Zookeeper or initialization of zoo storage
        
        if (this.foodsInfo.containsKey(food) && number > 0) {
            this.foods.put(food, number);
            System.out.println("ZOOKEEPER: Added "+ number + " " + food);
        } else {
            System.err.println("ZOOKEEPER: Impossible to add unknown food. It could be dangerous for pure little animals ");
        }
        
    }
    public boolean hasnoFood() {
        // check foodstore on emptyness
        return  this.foods.isEmpty();
    }
    public boolean canTakeFood(String food) {
        // check whether food can be taken from storage
        //no need to check on hasnoFood()
        return this.foods.get(food) > 0;
    }
    public void takeFood(String food) {
        //   if animal ate something
        this.foods.put(food, this.foods.get(food) - 1) ;
        
    }
    public void removeEmpty( ) {
        // if bulk of particular food is empty - delete it from the list
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
    
    public int sizeOfFoodstore() {
        //  how many types of food do we have
        return this.foods.size();
    }
    
    public boolean validFood(String food) {
        //  true if the food type is possible to use
        return this.foodsInfo.keySet().contains(food);
    }
    
    private Hashtable makeFoodInfo() {
        // here we have <Foodname : {health, waste}>
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
