package snid;

/**
 * @author Aaliyah Johnston 620130550
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 * @version 1.0
 * class that creates name objects
 */
public class Name {
    /**
     * variable fn: String - Person's first name
     * variable mn: String - Person's middle name
     * variable ln: String - Person's last name
     */
    protected String fn, mn, ln;

    /**
     * Constructor for the Name class
     * @param fn stores Person's's first name
     * @param mn stores Person's middle name
     * @param ln stores Person's last name
     */
    public Name (String fn, String mn, String ln){
        this.fn = fn;
        this.mn = mn;
        this.ln = ln;
    }

    /**
     * Getter method for Person's First Name
     * @return Person's First Name
     */
    public String getFirstName(){
        return this.fn;
    }

    /**
     * Getter method for Person's Middle Name
     * @return Person's Middle Name
     */
    public String getMiddleName(){
        return this.mn;
    }

    /**
     * Getter method for Person's Last Name
     * @return Person's Last Name
     */
    public String getLastName(){
        return this.ln;
    }

    /**
     * Setter method for last name,
     * accepts new last name and changes previous last name
     * @param lastName new last name
     */
    public void setLastName(String lastName){
        this.ln = lastName;
    }

    /**
     * Formatted String that returns Person's full name
     * @return Person's full name
     */
    public String toString(){
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

    /**
     * Checks to see if the value of the name object and the parameters are the same
     * @param name the full name of the person
     * @return True if the value of the name object is the same as the given parameter
     */
    public boolean equals(Name name){
        if (name.getFirstName() == getFirstName()){
            if (name.getMiddleName() == getMiddleName()){
                if (name.getLastName() == getLastName()){
                    return true;
                }
            }
        }
        return false;
    }
}
