package zooproject;

import java.util.ArrayList;


/**
 *
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * @version 1.0     11/03/2016
 *  Zoo Project
 * Class: Zoo
 *  Class contains all classes written as the structure for the Zoo
 */
public class Zoo {
    private final String name;      //name of the SUPER zoo
    private ArrayList<Enclosure> enclosures = new ArrayList();  // list of enclosures
    private ArrayList<Zookeeper> zookeepers = new ArrayList();  // list of zookeepers
    private Foodstore zoofood = new Foodstore();    //food for all enclosure storages'
    private static int index = 1;   // for naming
    private int status = 0;     //  for checking on emptyness

    public Zoo() {
        this.name ="SuperZoo #" + Zoo.getNextId();
    }
    protected static int getNextId() {
        return index++;
    }
    // GETTERS
    public Foodstore getFoodstore() {
        return this.zoofood;
    }
    public int getStatus() {
        return this.status;
    }
    
    //-------------------------------------------
    // SETTERS
    public void setZooFood(Foodstore zoofoo) {
        this.zoofood = zoofoo;
    }
    public void addEnclosure(Enclosure encl) {
        this.enclosures.add(encl);
        encl.setZoo(this);
    }
    public void  addZookeeper(Zookeeper zk, Enclosure enk) {
        if (this.isZooEnclosure(enk)) {
            this.zookeepers.add(zk);
            zk.getEnclosures().add(enk);
            enk.setZookeeper(zk);
        }
    }
    
    public boolean isZooEnclosure( Enclosure enk) {
        return this.enclosures.contains(enk);
    }
    
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
