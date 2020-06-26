package snid;

/**
 * Class implements the CivicDov to create a Marriage Document
 * @author Aaliyah Johnston 620130550
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 */
public class MarriageDoc implements CivicDoc {
    private static int refNo = 0;
    private String groomId, brideId, dateOfMarriage;

    /**
     * constructor for creating marriage doc
     * @param groomId groom's identification number
     * @param brideId bride's identification number
     * @param dateOfMarriage date of marriage
     */
    public MarriageDoc(String groomId, String brideId, String dateOfMarriage){
        refNo+=1; //document reference number is created
        this.groomId = groomId;
        this.brideId= brideId;
        this.dateOfMarriage= dateOfMarriage;
    }

    /**
     * getter for groom's identification number
     * @return groom's identification number
     */
    public String getGroomId(){
        return this.groomId;
    }

    /**
     * getter for bride's identification number
     * @return bride's identification number
     */
    public String getBrideId(){
        return this.brideId;
    }

    /**
     * getter for date of marriage
     * @return marriage date
     */
    public String getDateOfMarriage(){
        return this.dateOfMarriage;
    }

    /**
     * getter for documents reference number
     * @return returns reference number with 'M' as document identifier
     */
    public String getRefNo(){
        return "M" + Integer.toString(refNo);
    }

    /**
     * saves the paper to the file formatted
     * @return saves paper formatted
     */
    public String savePaper(){
        return getRefNo() + ":" +getGroomId()+ ":" +  getBrideId() + ":" + getDateOfMarriage();
    }

    /**
     * Displays marriage certificate
     * @return marriage certificate
     */
    public String toString(){
        return "Reference Number: "+getRefNo() + "\nGroom Identification Number: " + getGroomId() + "\nBride Identification Number: " + getBrideId() + "\nDate of Marriage: "+ getDateOfMarriage();
    }
}
