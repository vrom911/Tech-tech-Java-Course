package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Ape
 */
abstract public class Ape extends Animal {
    
    public Ape() {
        this.sound = "Gibber";
        this.eats.add("fruit");
        this.eats.add("icecream");
        this.treatLevel = 1;
    }
    public Ape(char gender, int age, int health, Enclosure encl) {
            //animal:gender,age,health,enclosure
        super(gender, age, health, encl);
        this.sound = "Gibber";
        this.eats.add("fruit");
        this.eats.add("icecream");
        this.treatLevel = 1;
    }
}
