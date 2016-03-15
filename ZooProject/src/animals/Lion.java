package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Lion
 */
public class Lion extends BigCat{
    protected static int index = 1;

    public Lion() {
        this.type = "Lion" + getNextId();
        this.sound = "roar";
    }

    public Lion(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "Lion" + getNextId();
        this.sound = "roar";
    }
    
    protected static int getNextId() {
        return index++;
    }
    public void stroked() {
        this.encreaseHealth(2);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, Zookeeper! I <3 strokes");
    }

    @Override
    public void treat() {
        this.stroked();
    }
    
    

}
