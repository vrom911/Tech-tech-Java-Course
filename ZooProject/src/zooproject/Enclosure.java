package zooproject;

import animals.Animal;
import java.util.ArrayList;

/**
 * Enclosure class is where Animals will live inside. The Enclosure also has a Foodstore containing
 * varying amounts of food in it, potentially of different types. The Zookeepers
 * will fill the Foodstore from the Zoo Foodstore. Each Enclosure will also
 * accrue a certain amount of Animal waste.
 * @version 1.0    Mar 11, 2016
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * ZooProject
 * Class: Enclosure
 * Class where our Animals will live inside
 */
public class Enclosure {
    /** name of the enclosure (unique numeration ) */
    private final String name;
    /**integer for generating names */
    private static int index = 1;
    /** each Enclosure belongs to the one Zoo */
    private Zoo zoo;
    /** the list of animals in enclosure    NOTE: no more than 20!!!  */
    private ArrayList<Animal> animals;
    /** Foodstore belongs to the enclosure. zookeeper brings food in it from Zoo FoodStorage each month */
    private Foodstore foodstore;
    /** yeap, animals do make waste */
    private int waste;
    /** each Enclosure has its personal Zookeeper */
    private Zookeeper zookeeper;
    /** flag for checking status
     * @value -1 -no need to continue
     */
    private int status;

    //-----------------------------------------------------------
    public Enclosure() {
        this.name ="Enclosure #" + Enclosure.getNextId();
        this.status = 0;
        this.animals = new ArrayList<>();
        this.foodstore = new Foodstore();
        this.waste = 0;
    }
    /** Generates next index for enclosure naming
     * @return  naming unique desc index*/
    protected static int getNextId() {
        return index++;
    }
    //------------------------------------------------------
// GETTERS
    /** @return name of the Enclosure */
    public String getName() {
        return name;
    }
    /** @return  list of the animals in the Enclosure */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    /** @return numberof Waste in the Enclosure */
    public int getWaste() {
        return waste;
    }
    /** @return Foodstore where all Enclosure's food is stored */
    public Foodstore getFoodstore() {
        return this.foodstore;
    }
    /** @return Zoo  Enclosure belongs to */
    public Zoo getZoo() {
        return this.zoo;
    }
    /** @return  0 if Enclosure is not empty, -1 otherwise */
    public int getStatus() {
        //    check is have animals to operate further
        if (this.animals.isEmpty()){
            this.status = -1;
        }
        return this.status;
    }
    /** @return Zookeeper who looks after the Enclosure*/
    public Zookeeper getZookeeper() {
        return zookeeper;
    }
   
    //----------------------------------------------------------------------
// SETTERS
    /** Set the Food Storage to the Enclosure
     * @param fs  - Food Storage for the Enclosure*/
    public void setFoodstore(Foodstore fs) {
        this.foodstore = fs;
    }
    /** Set current waste of the Enclosure
     * @param waste -int of waste */
    protected void setWaste(int waste) {
        this.waste = waste;
    }
    /** Set which Zoo it belongs to (when build)
     * @param zoo  - Zoo*/
    protected void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }
    /** Set Zookeeper responsible for Enclosure
     * @param zookeeper  - Zookeeper*/
    public void setZookeeper(Zookeeper zookeeper) {
        this.zookeeper = zookeeper;
    }
    /** @return the number of Animals in the Enclosure  */
    public int size() {
        return this.animals.size();
    }
    
    //-------------------------------------------------------------------------------
// ANIMALS
    /** If there is space in the Enclosure we add the Animal in the list.
     * @param animal   Animal we want to add*/
    public void addAnimal(Animal animal) {
        if (this.size()<20){
            //  no more than 20 per enclosure
            this.animals.add(animal);
            animal.setEnclosure(this);
            System.out.println(animal.says() + "! " + animal.getType()+ " added to the Enclosure.");
        } else {
            System.err.println("Can't add more animals! The Enclosure is full!");
        }
    }
    /** If this animal was in the list - remove it
     * @param animal  animal to delete from the list*/
    public void removeAnimal(Animal animal) {
        int indx = this.animals.indexOf(animal);
        if (indx != -1) {
            this.animals.remove(indx);
        }
    }
    /** If Enclosure is not empty Prints  basic information about each animal in the Enclosure
     * @see package.animals#aboutMe() See method for single animal 
     */
    public void aboutAnimals() {
        //  print info about every animal in the enclosure
        if( this.getStatus() != -1) {
            for (Animal a : this.animals) {
                a.aboutMe();
            } 
        }
    }
    
    /** Here we generate the list of food Zookeeper should bring to the Enclosure
     *  the reason: not to take unnessesary food in storage which Animals won't eat
     *  used by Zookeeper class
     * @return   The list of food item names*/
    public ArrayList<String> getPreferedFoodList() {
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
    /** Removes the specified amount of waste from the Enclosure
     * used by Zookeeper during cleaning each month
     * @param wasteNum  number of Waste*/
    public void removeWaste(int wasteNum) {
        this.waste -= wasteNum;
        System.out.println(wasteNum + " of Waste bined. Still have " + this.waste);
    }
    /** adds an amount of waste (possible if animal has eaten)
     * @param wasteNum */
    public void addWaste(int wasteNum) {
        this.waste += wasteNum;
    }
    //----------------------------------------------------------------------
    /** this method is called to trigger a new month for the Enclosure. 
     * When it is called, it will in turn call aMonthPasses() on all the Animals in the Enclosure(if not empty). 
     */
    public void aMonthPasses() {
        if (this.status != -1) {
             for (Animal a : this.animals) {
                a.aMonthPasses();
            } 
        }
               
    }
    /**  In the end of each month remove animal bodies 
     * @see package.animals#isDead() - check
     */
    public void removeDeadAnimals() {
        /** @param zombies - using new array because impossible to remove object during for looping of it!tricky one */
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
/**
     * @return string for printing comfortable */
    @Override
    public String toString() {
        return "Enclosure{\n" + "    animals=" + animals + ",\n     foodstore=" + foodstore + ",\n     waste=" + waste + ",\n     zookeeper=" + zookeeper + "\n}";
    }
    
}
