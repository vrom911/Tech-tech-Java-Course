package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 13, 2016
 * ZooProject
 * Class: Penguin
 */
public class Penguin extends Animal{
    protected static int index = 1;

    public Penguin() {
        this.type = "penguin" + getNextId();
        this.sound = "Honk";
        this.lifeExpectancy = 15;
        this.eats.add("fish");
        this.eats.add("icecream");
        this.treatLevel = 1;
    }

    public Penguin(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "penguin" + getNextId();
        this.sound = "Honk";
        this.lifeExpectancy = 15;
        this.eats.add("fish");
        this.eats.add("icecream");
        this.treatLevel = 1;
    }
    
    protected static int getNextId() {
        return index++;
    }
    public void watchFilm() {
        this.encreaseHealth(2);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, PlayZookeeper! I <3 Cinema");
    }
    @Override
    public void treat() {
        this.watchFilm();
    }

}
