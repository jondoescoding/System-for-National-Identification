package snid;

/**
 * @author Aaliyah Johnston 620130550
 *@author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 * @version 1.0
 * Abstract class which implements Biometric interface
 */
public abstract class Person implements Biometric {
    protected char gender, lifeStatus;
    protected int yob;
    protected Biometric biodata;
    protected static int id;
    private Person mother,father;

    /**
     * constructor for the abstract person class
     * @param gender gender of user , with 'M' or 'F'
     * @param yob year of birth of user
     */
    public Person(char gender, int yob){
       this.gender = gender;
       this.yob= yob;
   }

    /**
     * Abstract method to get the unique Identification number for each person
     * @return person's ID as string
     */
   public abstract String getId();

    /**
     * Getter method for user's gender
     * @return user's gender
     */
   public char getGender(){
        return this.gender;
   }

    /**
     * Getter method for the user's year of birth
     * @return user's YOB
     */
   public int getYOB(){
        return this.yob;
   }

    /**
     * Getter method for user's life status
     * @return person's life status
     */
   public char getLifeStatus(){
       return this.lifeStatus;
   }

    /**
     * Setter method for person's life status
     * @param lifeStatus whether the person is Alive or Deceased
     */
   public void setLifeStatus(String lifeStatus){
       if (lifeStatus.equals("Alive")){
           this.lifeStatus = 'A';
       } else if (lifeStatus.equals("Deceased")){
           this.lifeStatus = 'D';
       }
   }

    /**
     * Setter method for person's parent information
     * @param type 'M' for person's mother 'F' for person's father
     * @param parent stores parent information
     */
   public void setParent(char type, Person parent){
       if (type == 'F')
           this.father = parent;
       if (type == 'M')
           this.mother = parent;
   }

    /**
     * Getter method for the person's parent information
     * @param type 'M' for person's mother 'F' for person's father
     * @return the persons respective parent based on the type chosen
     */

   public Person getParent (char type) {
       if (type == 'M') {
           return mother;
       } else if (type == 'F') {
           return father;
       }
       return null;
   }

    /**
     * method to be completed
     * @param data description n/a
     */
   public void addBiometric(Biometric data){
       //method stub
   }

    /**
     * method to be completed
     * @param tag description n/a
     * @return Biometric information
     */
   public Biometric getBiometric(String tag){
       //method stub
       return null;
   }

    /**
     *  method description n/a (to be completed)
     * @return n/a
     */
   public String toString(){
       //method stub
       return null;
   }

}
