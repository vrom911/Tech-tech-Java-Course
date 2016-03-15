package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Tiger
 */
public class Tiger extends BigCat{
    protected static int index = 1; 

    public Tiger() {
        this.type = "tiger" + Tiger.getNextId();
        this.sound = "Grrr";
        
    }

    public Tiger(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "tiger" + Tiger.getNextId();
        this.sound = "Grrr";
    }
    
    protected static int getNextId() {
        return index++;
    }
    public void stroked() {
        this.encreaseHealth(3);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, Zookeeper! I <3 strokes");
    }
    @Override
    public void treat() {
        this.stroked();
    }

}
