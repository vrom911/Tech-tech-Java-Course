package animals;

import java.util.ArrayList;
import zooproject.Enclosure;
import zooproject.Foodstore;

/**
 *
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 * @version 1.0    Mar 11, 2016
 *  Zoo Project
 * Class: Animals
 * Class defines the basic properties and methods that all the different animal classes will use.
 */
abstract public class Animal {
    
        protected  String type;     //for to string print
        protected String sound;     //  know you could know what does the fox say
        protected int age;      // says how old the Animal is in months
        protected char gender;      // Male (‘m’) or Female (‘f’)
        protected ArrayList<String> eats;       // list of food names for particular animal
        private int Health;     // value between 0 and 10, with 10 being veryhealthy and 0 being dead
        protected int lifeExpectancy;       // average age that the Animal lives to in months
        private Enclosure enclosure;    // where Animal is
        protected int treatLevel;
        
// CONSTRUCTORS
        public Animal() {
            this.eats = new ArrayList<>();
            this.age = 0;
            this.Health = 10;
        }
        public Animal(char gender, int age, int health, Enclosure encl) {
            //animal:gender,age,health,enclosure
            this.eats = new ArrayList<>();
            this.age = age;
            this.Health = health;
            this.setGender(gender);
            this.enclosure = encl;
            
        }
        
        //---------------------------------------
// GETTERS
        public int getAge() {
            return this.age;
        }
        public char getGender() {
            return this.gender;
        }
        public int getHealth() {
            return this.Health;
        }
        public int getLifeExpectancy() {
            return this.lifeExpectancy;
        }
        public String getType() {
            return this.type;
        } 
        public int getTreatLevel() {
            return this.treatLevel;
        }
        public String says(){
            return this.sound + " " + this.sound;
        }
        
//------------------------------------------------------------------
// SETTERS
        public void setEnclosure(Enclosure enc) {
            this.enclosure = enc;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setHealth(int Health) {
            this.Health = Health;
        }
        
        public void setGender(char letter) {
            //  check on gender
            if (letter != 'm' || letter != 'f') {
                System.err.println("Impossible operation!\nIt can only be girl or boy!");
            } else {
                this.gender = letter;
            }
        }
        
        
        //----------------------------------------------------------------
        private boolean canEat(String food) {
        //      true if the Animal can eat that food
            return  eats.contains(food);
        }
        
        public ArrayList<String> eats() {
            return this.eats;
        }
        
        public void eat(Foodstore fs) {
            //process of eating food:
            // if there is proper food for this animal in the foodstorage -> 
                //animal eats : +health(no more than 10     -food in storage
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
        private void decreaseHealth() {
            //  happens each month
            this.Health -= 2;
        }
        protected void encreaseHealth(int h) {
            //  for treatment method
            this.Health += h;
        }
        
        public void treat() {
            System.out.println("Treatment in process...");
        }
        
        public boolean isDead() {
            // check if animal died of age or health issues..
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
        
        public void aboutMe() {
            //proper print of animal info
            System.out.println(this.type.toUpperCase() + ": I'm " + this.age + " out of " +this.lifeExpectancy +  ". Health level " + this.Health + "/10");
        }
        
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
