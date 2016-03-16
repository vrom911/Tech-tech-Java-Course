package animals;

import zooproject.Enclosure;

/**
 * already have abstract Animal class, we now need to create some specific instances of Animals. 
 * We are going to use some further abstract classes however as some Animals share common eating habits.
 * encapsulation of different animals in their own classes will allow the simulation to be extended more easily at a later date
 * @author Veronika Romashkina
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Ape extention of Animals
 */
abstract public class Ape extends Animal {
    
    public Ape() {
        this.sound = "Gibber";
        this.eats.add("fruit");
        this.eats.add("icecream");
        this.treatLevel = 1;
    }
            /** for auto generation from file
     * @param gender the Gender of the Animal ('m' or 'f' only)
     * @param age age (in Month)
     * @param health health level
     * @param encl  Enclosure where animal will live in*/
    public Ape(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.sound = "Gibber";
        this.eats.add("fruit");
        this.eats.add("icecream");
        this.treatLevel = 1;
    }
}
