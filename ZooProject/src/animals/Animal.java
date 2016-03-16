package animals;

import java.util.ArrayList;
import zooproject.Enclosure;
import zooproject.Foodstore;

/**
 * Animal class will be the basis for all different animal classes.
 * Class defines the basic properties and methods that all the different animal classes will use.
 * it will allow them to eat, excrete and grow old
 * @version 1.0    Mar 11, 2016
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 *  Zoo Project
 * Class: Animals
 */
abstract public class Animal {
        /**  Type of the Animal */
        protected  String type;
        /** Sound the animal make. Now you know what does the Fox say ;)*/
        protected String sound;
        /** How old is Animal (in Month) */
        protected int age;
        /** Gender of the Animal Male (‘m’) or Female (‘f’) */
        protected char gender;
        /** list of food names for particular animal*/
        protected ArrayList<String> eats;
        /** how healthy the Animal is: value between 0 and 10, with 10 being very healthy and 0 being dead*/
        private int Health;
        /** average age that the Animal can live in months*/
        protected int lifeExpectancy;
        /** the Enclosure the Animal is in */
        private Enclosure enclosure;
        /** indicator used by Zookeeper to find out the right way of treatment for the Animal*/
        protected int treatLevel;
        
// CONSTRUCTORS
        public Animal() {
            this.eats = new ArrayList<>();
            this.age = 0;
            this.Health = 10;
        }
        /** for auto generation from file
     * @param gender the Gender of the Animal ('m' or 'f' only)
     * @param age age (in Month)
     * @param health health level
     * @param encl  Enclosure where animal will live in*/
        public Animal(char gender, int age, int health, Enclosure encl) {
            this.eats = new ArrayList<>();
            this.age = age;
            this.Health = health;
            this.setGender(gender);
            this.enclosure = encl;
            
        }
        
        //---------------------------------------
// GETTERS
        /**
     * @return  how old is the Animal*/
        public int getAge() {
            return this.age;
        }
        /**
     * @return  is it boy or girl*/
        public char getGender() {
            return this.gender;
        }
        /**
     * @return  How healthy the Animal is*/
        public int getHealth() {
            return this.Health;
        }
        /**
     * @return  how long Animal will live*/
        public int getLifeExpectancy() {
            return this.lifeExpectancy;
        }
        /**
     * @return  what kind of animal is it*/
        public String getType() {
            return this.type;
        } 
        /**
     * @return  how Zookeeper should treat Animal*/
        public int getTreatLevel() {
            return this.treatLevel;
        }
        /**
     * @return  Animal words to you*/
        public String says(){
            return this.sound + " " + this.sound;
        }
        
//------------------------------------------------------------------
// SETTERS
        /**
     * @param enc   Enclosure the Animal will live in */
        public void setEnclosure(Enclosure enc) {
            this.enclosure = enc;
        }
        /**
     * @param age  how old the Animal will be*/
        public void setAge(int age) {
            this.age = age;
        }
        /**
     * @param Health health mark (0 to 10) */
        public void setHealth(int Health) {
            this.Health = Health;
        }
        /** set the gender by input value
     * @param letter  'm' or 'f'*/
        public void setGender(char letter) {
            if (letter != 'm' || letter != 'f') {
                System.err.println("Impossible operation!\nIt can only be girl or boy!");
            } else {
                this.gender = letter;
            }
        }

        //----------------------------------------------------------------
        /** @return true if the Animal can eat that food type*/
        private boolean canEat(String food) {
            return  eats.contains(food);
        }
        /**
     * @return  the list of Food name this type of Animals can eat*/
        public ArrayList<String> eats() {
            return this.eats;
        }
        /** process of eating food:
         * if there is proper food for this animal in the foodstorage -> 
         *      animal eats : +health(no more than 10     -food in storage
     * @param fs Food Storage from which Animal will take food
         */
        public void eat(Foodstore fs) {
            boolean success = false;
            if (!fs.hasnoFood()){
                for (String key : fs.getFoods().keySet())
                {
                    if (this.canEat(key)) {
                        if (fs.canTakeFood(key)) {
                            System.out.println(this.type.toUpperCase() + ": " + this.sound + "! "  + key +"  was delicious! Om-nom-nom");
                            int temp = fs.foodsInfo.get(key)[0];
                            this.Health += temp;
                            if (this.Health > 10) {
                                this.Health = 10;
                            }
                            System.out.print("    +" + temp + " to Health Yeah!");
                            temp = fs.foodsInfo.get(key)[1];
                            this.enclosure.addWaste(temp);
                            System.out.println("    +" + temp + " to Waste, sorry, man");
                            fs.takeFood(key);
                            success = true;
                            break;
                        }
                    }
                }
            } 
            if (!success) {
                    System.out.println(this.type.toUpperCase() + ": " + this.sound + "! I'm so hungry I could eat an elephant");
                    if (this.type.contains("elephant")) {
                        System.err.println(this.type.toUpperCase() + ": Wait a minute. I AM an ELEPHANT o_O");
                    }
                }
        }
        /** Each month as the Animal gets older it loose 2 point of Health */
        private void decreaseHealth() {
            this.Health -= 2;
        }
        /** when Animal treated gets additional health points
         * @see subclasses
         * @param h  amount of health points
         */
        protected void encreaseHealth(int h) {
            this.Health += h;
        }
        /** This will be overriden in each subclass*/
        public void treat() {
            System.out.println("Treatment in process...");
        }
        /** check breath
     * @return true if Animal alive*/
        public boolean isDead() {
            if (this.Health <= 0) {
                System.out.println(this.type.toUpperCase() + ":Goodbye Cruel World! // just died because of health issues...");
                return true;
            }
            else if (this.age >= this.lifeExpectancy) {
                System.out.println(this.type.toUpperCase() + ":Goodbye Cruel World! //  just died because too old for this...");
                return true;
            }
            return false;
        }
        /** proper print of animal info */
        public void aboutMe() {
            System.out.println(this.type.toUpperCase() + ": I'm " + this.age + " out of " +this.lifeExpectancy +  ". Health level " + this.Health + "/10");
        }
        /** Each month action for animal ( get older, eat food, make waste, get treatment, lose health*/
        public void aMonthPasses() {
            // trigger each month
            this.age += 1;
            this.eat(this.enclosure.getFoodstore());
            this.decreaseHealth();
        }

    @Override
    public String toString() {
        return "Animal{" + "\n    type=" + type + ",\n     age=" + age +  ",\n     Health=" + Health + ",\n     lifeExpectancy=" + lifeExpectancy  + "\n}";
    }
        
}
