package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Gorilla
 */
public class Gorilla extends Ape {
    protected static int index = 1;

    public Gorilla() {
        this.type = "Gorilla" + getNextId();
        
    }

    public Gorilla(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "Gorilla" + getNextId();
    }
    
    protected static int getNextId() {
        return index++;
    }
    public void painting() {
        this.encreaseHealth(4);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, PlayZookeeper! I <3 Art ~**~");
    }

    @Override
    public void treat() {
        this.painting();
    }
    
    

}
