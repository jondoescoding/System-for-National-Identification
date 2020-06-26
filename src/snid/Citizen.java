package snid;
import java.util.*;

/**
 * @author Aaliyah Johnston 620130550
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 * @version 1.0
 * Class shows inheritance with person class and implements civic doc interface and comparable interface
 */
public class Citizen extends Person implements Comparable<Citizen>{

    protected Name name;
    private Address address;
    private ArrayList<CivicDoc> papers = new ArrayList<>();
    private String citId, motherId, fatherId,id;
    String fName, mName, lName;

    /**
     *Constructor for creating a unique citizen
     * @param gender citizen's gender
     * @param yob citizen's year of birth
     * @param fName citizen's first name
     * @param mName citizen's middle name
     * @param lName citizen's last name
     */
    public Citizen(char gender, int yob, String fName, String mName, String lName){
        super(gender,yob);
        name = new Name(fName, mName, lName);
    }

    /**
     * Second citizen constructor
     * @param id citizen's id
     * @param fName citizen's first name
     * @param mName citizen's middle name
     * @param lName citizen's last name
     * @param gender citizen's gender
     * @param yob citizen's year of birth
     * @param lifeStatus citizen's life Status
     * @param address1 citizen's first address line
     * @param address2 citizen's second address line
     * @param address3 citizen's third address line
     * @param address4 citizen's fourth address line
     * @param address5 citizen's fifth address line
     * @param motherId citizen's mother's id
     * @param fatherId citizen's father's id
     */
    public Citizen(String id, String fName, String mName, String lName, char gender, int yob, String lifeStatus, String address1,String address2,String address3,String address4,String address5,String motherId, String fatherId){
        super(gender,yob);
        name = new Name(fName, mName,lName);
        this.citId = id;
        this.motherId = motherId;
        this.fatherId = fatherId;
        setLifeStatus(lifeStatus);
        String citAddress = address1 + "|" + address2 + "|" + address3 + "|" + address4 + "|" + address5;
        setAddress(new Address(citAddress));
    }

    /**
     * sets the id of newly registered persons
     * @param lastIdNum id number of the last person in the list
     */
    public void setId(int lastIdNum){
        this.id = Integer.toString(++lastIdNum);
    }

    /**
     * Returns the ID of a citizen
     * @return citizen's ID
     */
    public String getFirstId(){
        return id;
    }

    public String getId(){
        return citId;
    }

    /**
     * setter method for citizen's address
     * @param address the address of the citizen
     */
    public void setAddress(Address address){
        this.address = address;
    }

    /**
     * getter method for citizen's address
     * @return the citizen's address
     */
    public Address getAddress(){
        return this.address;
    }

    /**
     * change's citizen's last name
     * @param newLast the new last name of citizen
     */
    public void changeLastName(String newLast){
        name.setLastName(newLast);
    }

    /**
     * compares two citizen objects by their ID
     * @param cit2 second citizen object to be compared
     * @return 1 -1 or 0 when comparing objects
     */
    public int compareTo(Citizen cit2) {
        return this.getId().compareTo(cit2.getId());
    }

    /**
     * gets the name of the citizen and returns it in a formatted method
     * @return formatted String of citizen's full name
     */
    public String getName(){
        return name.getLastName().toUpperCase() + ", " + name.getFirstName() + " " + name.getMiddleName().charAt(0) + ".";
    }

    /**
     * returns the name object of the citizen
     * @return name object (first name, middle name, last name)
     */
    public Name getFullName(){
        return name;
    }


    /**
     * Adds the marriage and/or death certificate(s) to a citizen's records
     * @param paper death/marriage certificate
     */
    public void addPapers(CivicDoc paper){
        this.papers.add(paper);
    }

    /**
     * getter method for accessing the documents the citizen has
     * @return formatted records for citizen
     */
    public String getPapers(){
        String formattedPapers = "";
        if (papers.size() > 1){
            for (int i=0; i<papers.size()-1;i++) {
                formattedPapers += papers.get(i).savePaper() + "@";
            }
            formattedPapers += papers.get(papers.size()-1).savePaper();
        } else if (papers.size() == 1) {
            for (CivicDoc paper : papers) {
                formattedPapers += paper.savePaper();
            }
        }
        return formattedPapers;
    }

    /**
     * method to be completed
     * @return String: tag
     */
    public String getTag(){

        return null;
    }

    /**
     * method to be completed
     * @return String: value
     */
    public String getValue(){
        //method stub
        return null;
    }

    /**
     * method to be completed
     * @param other description n/a
     * @return int: description n/a
     */
    public int match(Biometric other){
        //method stub
        return 1;
    }
}
