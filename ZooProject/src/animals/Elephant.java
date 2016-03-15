package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 13, 2016
 * ZooProject
 * Class: Elephant
 */
public class Elephant extends Animal{
    protected static int index = 1;

    public Elephant() {
        this.type = "Elephant" + getNextId();
        this.sound = "truumpet";
        this.lifeExpectancy = 36;
        this.eats.add("hay");
        this.eats.add("fruit");
        this.treatLevel = 2;
    }

    public Elephant(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "Elephant" + getNextId();
        this.sound = "truumpet";
        this.lifeExpectancy = 36;
        this.eats.add("hay");
        this.eats.add("fruit");
        this.treatLevel = 2;
    }
    
    protected static int getNextId() {
        return index++;
    }
    public void bath() {
        this.encreaseHealth(5);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, PhysioZookeeper! I <3 Bathing");
    }
    @Override
    public void treat() {
        this.bath();
    }
    

}
