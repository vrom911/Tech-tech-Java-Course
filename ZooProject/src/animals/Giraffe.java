package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 13, 2016
 * ZooProject
 * Class: Giraffe
 */
public class Giraffe extends Animal{
    protected static int index = 1;

    public Giraffe() {
        this.type = "Giraffe" + getNextId();
        this.sound = "Bli";
        this.lifeExpectancy = 28;
        this.eats.add("hay");
        this.eats.add("fruit");
        this.treatLevel = 2;
        
    }

    public Giraffe(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "Giraffe" + getNextId();
        this.sound = "Bli";
        this.lifeExpectancy = 28;
        this.eats.add("hay");
        this.eats.add("fruit");
        this.treatLevel = 2;
    }
    
    protected static int getNextId() {
        return index++;
    }
    public void neckMassage() {
        this.encreaseHealth(4);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, PhysioZookeeper! I <3 Massage");
    }
    @Override
    public void treat() {
        this.neckMassage();
    }
    

    
}
