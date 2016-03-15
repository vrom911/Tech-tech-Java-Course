package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 13, 2016
 * ZooProject
 * Class: Bear
 */
public class Bear extends Animal{
    protected static int index = 1;

    public Bear() {
        this.type = "Bear" + getNextId();
        this.sound = "Groooar";
        this.lifeExpectancy = 18;
        this.eats.add("fish");
        this.eats.add("steak");
    }

    public Bear(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "Bear" + getNextId();
        this.sound = "Groooar";
        this.lifeExpectancy = 18;
        this.eats.add("fish");
        this.eats.add("steak");
    }
    
    
    protected static int getNextId() {
        return index++;
    }
    public void hug() {
        this.encreaseHealth(3);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, Zookeeper! I <3 hugs *-*");
    }
    @Override
    public void treat() {
        this.hug();
    }
    

}
