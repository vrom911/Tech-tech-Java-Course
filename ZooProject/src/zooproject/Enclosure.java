package zooproject;

import animals.Animal;
import java.util.ArrayList;

/**
 *
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * @version 1.0    Mar 11, 2016
 * ZooProject
 * Class: Enclosure
 * Class where our Animals will live inside
 */
public class Enclosure {

    private final String name;   //name of the enclosure (unique numeration )
    private static int index = 1;       // for naming
    private Zoo zoo;        // each Enclosure belongs to the one Zoo
    private ArrayList<Animal> animals;      //  which animals has in it.    NOTE: no more than 20!!!
    private Foodstore foodstore;    // this Foodstore beongs to the enclosure. zookeeper brings food in it from Zoo FoodStorage each month
    private int waste;      // yeap, animals do make waste
    private Zookeeper zookeeper;    // each Enclosure has its personal Zookeeper
    private int status;     // -1 is no more reasons to continue

    //-----------------------------------------------------------
    public Enclosure() {
        this.name ="Enclosure #" + Enclosure.getNextId();
        this.status = 0;
        this.animals = new ArrayList<>();
        this.foodstore = new Foodstore();
        this.waste = 0;
    }
    
    protected static int getNextId() {
        return index++;
    }
    //------------------------------------------------------
// GETTERS
    public String getName() {
        return name;
    }
    
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    
    public int getWaste() {
        return waste;
    }
    
    public int  getWasteSize() {
        //returns current amount of Waste

        return this.waste;
    }
    public Foodstore getFoodstore() {
        return this.foodstore;
    }
    
    public Zoo getZoo() {
        return this.zoo;
    }
    
    public int getStatus() {
        //    check is have animals to operate further
        if (this.animals.isEmpty()){
            this.status = -1;
        }
        return this.status;
    }

    public Zookeeper getZookeeper() {
        return zookeeper;
    }
   
    //----------------------------------------------------------------------
// SETTERS
    public void setFoodstore(Foodstore fs) {
        this.foodstore = fs;
    }

    public void setWaste(int waste) {
        this.waste = waste;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public void setZookeeper(Zookeeper zookeeper) {
        this.zookeeper = zookeeper;
    }

    public int size() {
        //  returns the number of Animals in the Enclosure
        return this.animals.size();
    }
    
    //-------------------------------------------------------------------------------
// ANIMALS
    public void addAnimal(Animal animal) {
        //adds Animal to Enclosure
        if (this.size()<20){
            //  no more than 20 per enclosure
            this.animals.add(animal);
            animal.setEnclosure(this);
            System.out.println(animal.says() + "! " + animal.getType()+ " added to the Enclosure.");
        } else {
            System.err.println("Can't add more animals! The Enclosure is full!");
        }
    }
    public void removeAnimal(Animal animal) {
        //removes animal from Enclosure
        
        int index = this.animals.indexOf(animal);
        if (index != -1) {
            this.animals.remove(index);
        }
    }
    public void aboutAnimals() {
        //  print info about every animal in the enclosure
        if( this.getStatus() != -1) {
            for (Animal a : this.animals) {
                a.aboutMe();
            } 
        }
    }
    
    
    public ArrayList<String> getPreferedFoodList() {
        // here I complete the list of the food I need for the animals in the particular enclosure
            // the reason: not to take unnessesary food in storage
        
        ArrayList<String> foodList = new ArrayList<>();
        for (Animal a : this.animals) {
            for (String f : a.eats()) {
                if (!foodList.contains(f)) {
                    foodList.add(f);
                }
            }
        }
        return foodList;
    }

    //--------------------------------------------------------------------------------
// WASTE
    public void removeWaste(int wasteNum) {
        // removes the specified amount of waste from the Enclosure
        
        this.waste -= wasteNum;
        System.out.println(wasteNum + " of Waste bined. Still have " + this.waste);
    }
    public void addWaste(int wasteNum) {
        // adds an amount of waste (if animal has eaten)
        
        this.waste += wasteNum;
    }
    
    //----------------------------------------------------------------------
    public void aMonthPasses() {
        // trigger a new month for the Enclosure
        if (this.status != -1){
             for (Animal a : this.animals) {
                a.aMonthPasses();
            } 
        }
               
    }
    
    public void removeDeadAnimals() {
        
        //using new array because imposible to remove object during for looping of it!tricky one
        ArrayList<Animal> zombies = new ArrayList();
         for (Animal a : this.animals) {            
            if (a.isDead()) {
                zombies.add(a);
            }
        } 
         for (Animal an : zombies) {
             this.animals.remove(an);
             an = null;
         }
         zombies.removeAll(zombies);
    }

    @Override
    public String toString() {
        return "Enclosure{\n" + "    animals=" + animals + ",\n     foodstore=" + foodstore + ",\n     waste=" + waste + ",\n     zookeeper=" + zookeeper + "\n}";
    }
    
}
