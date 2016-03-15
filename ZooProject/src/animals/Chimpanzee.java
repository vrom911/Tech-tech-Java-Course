package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Chimpanzee
 */
public class Chimpanzee extends Ape{
    protected static int index = 1;

    public Chimpanzee() {
        this.type = "Chimpanzee" + getNextId();
    }

    public Chimpanzee(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.type = "Chimpanzee" + getNextId();
    }
    
    protected static int getNextId() {
        return index++;
    }
    public void playChase() {
        this.encreaseHealth(4);
        System.out.println(this.type.toUpperCase() + ": "+ this.sound + " ^^) Thank you, PlayZookeeper! I <3 Chase");
    }
    @Override
    public void treat() {
        this.playChase();
    }
    

}
