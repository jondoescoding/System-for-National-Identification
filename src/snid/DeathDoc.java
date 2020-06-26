package snid;

/**
 * Inherits CivicDoc class and is used to create a death Certificate
 * @author Aaliyah Johnston 620130550
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 */
public class DeathDoc implements CivicDoc {
    private static int refNo = 0;
    private String id,causeOfDeath,dateOfDeath,placeOfDeath;

    /**
     * Constructor for creating Death Certificate
     * @param causeOfDeath citizen's cause of death
     * @param dateOfDeath citizen's date of death
     * @param placeOfDeath citizen's place of death
     */
    public DeathDoc(String causeOfDeath, String dateOfDeath, String placeOfDeath){
        refNo += 1;
        this.causeOfDeath= causeOfDeath;
        this.dateOfDeath= dateOfDeath;
        this.placeOfDeath = placeOfDeath;
    }

    /**
     * Gets document reference number formatted with identifier, 'D'
     * @return reference number
     */
    public String getRefNo(){
        return "D" + Integer.toString(refNo);
    }

    /**
     * Saves Death Certificate to the file
     * @return formatted death certificate information
     */
    public String savePaper(){
        return getRefNo() + ":" +this.causeOfDeath+ ":" +  this.dateOfDeath + ":" + this.placeOfDeath;
    }

    /**
     * Prints Death Certificate
     * @return string of death certificate
     */
    public String toString(){
        return "Reference Number: "+getRefNo() + "\nCause of Death: " + causeOfDeath + "\nDate of Death: " + dateOfDeath + "\nPlace of Death: "+ placeOfDeath;
    }
}
