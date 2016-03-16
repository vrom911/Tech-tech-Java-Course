package zooproject;

/**
 * Extention of the Zookeeper Class
 * PhysioZookeepers are able to call neckMassage() or bath() as addition to simple treatment
 * @version 1.0 Mar 11, 2016
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * ZooProject
 * Class: PhysioZookeeper
 */
public class PhysioZookeeper extends Zookeeper{

    public PhysioZookeeper() {
        /** Treatment indicator 2 shows that it can do special physio treatment 
         * @value 2
         */
        this.treatmentWay = 2;
    }
            
}
