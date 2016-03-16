package zooproject;

import java.util.ArrayList;


/**
 *This class will have a number of Enclosures containing Animals, a collection of Zookeepers assigned to those enclosures, and will organise the day to day running of the zoo
 * @version 1.0     11/03/2016
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 *  Zoo Project
 * Class: Zoo
 * Class contains all classes written as the structure for the Zoo
 */
public class Zoo {
    /** name of the SUPER zoo */
    private final String name;
    /** list of enclosures */
    private ArrayList<Enclosure> enclosures = new ArrayList();
    /** list of zookeepers */
    private ArrayList<Zookeeper> zookeepers = new ArrayList();
    /** Food Storage for all enclosures */
    private Foodstore zoofood = new Foodstore();
    /** naming index */
    private static int index = 1;
    /** Status of Zoo fullness/emptiness */
    private int status = 0;

    public Zoo() {
        this.name ="SuperZoo #" + Zoo.getNextId();
    }
    /**
     * @return  next generated name index for Zoo */
    protected static int getNextId() {
        return index++;
    }
    // GETTERS
    /** @return Food Storage of the Zoo */
    public Foodstore getFoodstore() {
        return this.zoofood;
    }
    /** @return Status of emptiness of the Zoo
     * @value -1 if empty
     */
    public int getStatus() {
        return this.status;
    }
    
    //-------------------------------------------
    // SETTERS
    /** set the main Food Storage of the Zoo
     * @param zoofoo  Food Storage object*/
    public void setZooFood(Foodstore zoofoo) {
        this.zoofood = zoofoo;
    }
    /** add another Enclosure to the Zoo
     * @param encl  Enclosure object to add to the Zoo*/
    public void addEnclosure(Enclosure encl) {
        this.enclosures.add(encl);
        encl.setZoo(this);
    }
    /** Add Zookeeper to the Enclosure ant in the Zookeeperslist
     * @param zk Zookeeper of any type
     * @param enk   Enclosure where he will work*/
    public void  addZookeeper(Zookeeper zk, Enclosure enk) {
        if (this.isZooEnclosure(enk)) {
            this.zookeepers.add(zk);
            zk.getEnclosures().add(enk);
            enk.setZookeeper(zk);
        }
    }
    /** Check Enclosure on belonging to the Zoo
     * @param enk Enclosure object
     * @return   true if it is in the Enclosures list*/
    public boolean isZooEnclosure( Enclosure enk) {
        return this.enclosures.contains(enk);
    }
    /** All event that happen in the Zoo during the month.
     * Each Enclosure will have aMonthPasses() called on it. This will in turn then call the method on all the Animals in the Enclosure.
     * Each Zookeeper (once these have been implemented) will have aMonthPasses() called on it
     * Each Animal in the zoo will be checked to see if its health is 0. If so, it will be removed from the zoo.
     * The zoo Foodstore can be restocked.
     * Dead animals removed.
     */
    public void aMonthPassed() {
        int empty = 0;
        System.out.println("\nAnother Month in paradise...\n\n    Let's see what happened in " + this.name);
        for (Enclosure enc : this.enclosures) {
            System.out.println("--------    " + enc.getName() + "    --------");
            if ( enc.getStatus() != -1) {
                enc.aboutAnimals();
                enc.getZookeeper().putFoodInStorage(enc);
                enc.aMonthPasses();
                enc.getZookeeper().aMonthPasses(enc);
                enc.removeDeadAnimals();
                enc.getFoodstore().removeEmpty();
            }
            else {
                System.out.println("ZOOKEEPER: No more animals in this Zoo. I'm feeling lonely :(");
                empty++;
            }
        }
        if (empty == this.enclosures.size()) {
            this.status = -1;
        }
    }
    /** this make 0.5 sec pause between months */
    public void go() {
        try {
                Thread.sleep(500);
                this.aMonthPassed();
        }
        catch (InterruptedException e) {
            
        }
    }

    @Override
    public String toString() {
        return "Zoo{" + "enclosures=" + enclosures + ", zookeepers=" + zookeepers + '}';
    }
    
}
