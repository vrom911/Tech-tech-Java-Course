package animals;

import zooproject.Enclosure;

/**
 *
 * @author Veronika Romashkina
 * @version 1.0 Mar 11, 2016
 * ZooProject
 * Class: Animal --> BigCat
 */
abstract class BigCat extends Animal{

    public BigCat() {
        this.lifeExpectancy = 24;
        this.eats.add("steak");
        this.eats.add("celery");
    }

    public BigCat(char gender, int age, int health, Enclosure encl) {
        super(gender, age, health, encl);
        this.lifeExpectancy = 24;
        this.eats.add("steak");
        this.eats.add("celery");
    }
    
}
