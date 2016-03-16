package animals;

import zooproject.Enclosure;

/**
 * already have abstract Animal class, we now need to create some specific instances of Animals. 
 * We are going to use some further abstract classes however as some Animals share common eating habits.
 * encapsulation of different animals in their own classes will allow the simulation to be extended more easily at a later date
 * @author Veronika Romashkina
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Gorilla extention of Ape
 */
public class Gorilla extends Ape {
    protected static int index = 1;

    public Gorilla() {
        this.type = "Gorilla" + getNextId();
        
    }
    /** for auto generation from file
     * @param gender the Gender of the Animal ('m' or 'f' only)
     * @param age age (in Month)
     * @param health health level
     * @param encl  Enclosure where animal will live in
     */
    public Gorilla(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "Gorilla" + getNextId();
    }
    /**
     * @return  index for next Animal type number*/
    protected static int getNextId() {
        return index++;
    }
    /** treatment method for particular Animal type */
    public void painting() {
        this.encreaseHealth(4);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, PlayZookeeper! I <3 Art ~**~");
    }

    @Override
    public void treat() {
        this.painting();
    }
    
    

}
