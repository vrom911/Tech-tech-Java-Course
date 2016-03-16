package zooproject;

/**
 * Extention of the Zookeeper Class
 * PlayZookeeper is able to call the watchAFilm(), playChase() or painting() methods on Animals which have that method.
 * @version 1.0 Mar 11, 2016
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * ZooProject
 * Class: PlayZookeeper
 */
public class PlayZookeeper extends Zookeeper{

    public PlayZookeeper() {
        /** Treatment indicator 1 shows that it can do special play treatment 
         * @value 1
         */
        this.treatmentWay = 1;
    }

}
