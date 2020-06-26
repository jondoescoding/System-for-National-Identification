package app;
import java.io.IOException;
import java.util.*;
import snid.*;
import data.*;

/**
 * Group Members
 * @author Aaliyah Johnston 620130550
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 *Creates the an array List of citizens from a file and rewrites the file
 */

public class SNIDApp{
    private SNIDDb sniddB;
    private ArrayList<Citizen> citizens = new ArrayList<>();
    private Citizen citizenData;

    /**
     * Accepts a file name and a delimiter, and creates an arrayList of citizen objects
     * @param fileName name of file
     * @param delimiter delimiter
     */
    public SNIDApp(String fileName, String delimiter){

        sniddB = new SNIDDb(fileName,delimiter);
        while (sniddB.hasNext()) {
            String[] citizenLine = sniddB.getNext();
            if (!citizenLine[0].startsWith("#")) {
                try {
                    if (citizenLine.length <8) {
                        citizenData = new Citizen(citizenLine[0], citizenLine[1], citizenLine[2], citizenLine[3], citizenLine[4].charAt(0), Integer.parseInt(citizenLine[5]), citizenLine[6], "", "", "", "", "", "", "");
                        citizens.add(citizenData);
                    }else if (citizenLine.length < 12) {
                        citizenData = new Citizen(citizenLine[0], citizenLine[1], citizenLine[2], citizenLine[3], citizenLine[4].charAt(0), Integer.parseInt(citizenLine[5]), citizenLine[6], citizenLine[7], citizenLine[8], citizenLine[9], citizenLine[10], "", "", "");
                        citizens.add(citizenData);
                    }else if (citizenLine.length < 16){
                        citizenData = new Citizen(citizenLine[0], citizenLine[1], citizenLine[2], citizenLine[3], citizenLine[4].charAt(0), Integer.parseInt(citizenLine[5]), citizenLine[6], citizenLine[7], citizenLine[8], citizenLine[9], citizenLine[10], citizenLine[11], citizenLine[12], citizenLine[13]);
                        citizens.add(citizenData);
                    }
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("Array Index out of Bounds");
                    a.getStackTrace();
                } catch (NumberFormatException nf) {
                    System.out.println("Integer not entered!");
                    nf.getStackTrace();
                }
            }

            try {
                if (!citizenLine[12].isEmpty() || !citizenLine[13].isEmpty()) {
                   addParentData(citizenLine[0], citizenLine[12], citizenLine[13]);
                }
            } catch (ArrayIndexOutOfBoundsException | NullPointerException an) {
                an.getStackTrace();
            }

            try {
                if (!citizenLine[14].isEmpty()) { //adds citizen's civicDocs
                    CivicDoc deathCertificate, marriageCertificate;
                    String[] civicDoc;
                    String[] civicDocs = citizenLine[14].split("@");

                    for (int i = 0; i < civicDocs.length; i++) {
                        civicDoc = civicDocs[i].split(":");
                        if (civicDoc[0].startsWith("M")) {
                            marriageCertificate = new MarriageDoc(civicDoc[0], civicDoc[1], civicDoc[2]);
                            assert citizenData != null;
                            citizenData.addPapers(marriageCertificate);
                        } else if (civicDoc[0].startsWith("D")) {
                            deathCertificate = new DeathDoc(civicDoc[0], civicDoc[1], civicDoc[2]);
                            assert citizenData != null;
                            citizenData.addPapers(deathCertificate);
                        }
                    }
                }
            } catch(ArrayIndexOutOfBoundsException a){
                a.getStackTrace();
            }
        }
    }

    /**
     * Register's a citizen's birth
     * @param gender 'M' or 'F'
     * @param yob int year of birth
     * @param fName String first Name
     * @param mName String Middle Name
     * @param lName String last Name
     */
    public void registerBirth(char gender, int yob, String fName, String mName, String lName) {
        citizenData = new Citizen(gender,yob,fName,mName,lName);
        citizenData.setLifeStatus("Alive");
        citizenData.setId(citizens.size());
        citizens.add(citizenData);
        System.out.println("Registered birth");
    }

    /**
     * adds a citizen's parent data
     * @param idNum String citizen's ID Number
     * @param fatherID father's ID Number
     * @param motherID Mother's ID Number
     */
    public void addParentData(String idNum, String motherID, String fatherID){
        //gets citizen and their parents info
        try {
            Citizen citizen= getCitizen(idNum);
            Citizen mother= getCitizen(motherID);
            Citizen father= getCitizen(fatherID);

            if (citizen != null && father != null) { //if citizen and their father exists, add father details
                citizen.setParent('F', father);
            }

            if (citizen != null && mother != null) { //if citizen and their mother exists, add father details
                citizen.setParent('M', mother);
            }
        }catch (NullPointerException n){
            System.out.println("Citizen(s) don't exist");
            n.getStackTrace();
        }

    }

    /**
     * updates a citizen's address
     * @param id citizen's id
     * @param street citizen's street address
     * @param town citizen's town address
     * @param parish citizen's parish of residence
     * @param country citizen's country of residence
     */
    public void updateAddress(String id, String street, String town, String parish, String country){
        try{
            String newAddress = street+"|"+town+"|"+parish+ "|"+ country;
            Address updatedAddress= new Address(newAddress);
            Citizen citizen= getCitizen(id);
            citizen.setAddress(updatedAddress);
        } catch (NullPointerException n){
            System.out.println("Citizen does not exist.");
            n.getStackTrace();
        }
    }


    /**
     * registers the death of a citizen
     * @param id citizen's if
     * @param causeOfDeath cause of death
     * @param dateOfDeath date of death
     * @param placeOfDeath place of death
     */
    public void registerDeath(String id, String causeOfDeath, String dateOfDeath, String placeOfDeath){
        try {
            Citizen citizen= getCitizen(id);
            DeathDoc deathDoc = new DeathDoc(causeOfDeath, dateOfDeath, placeOfDeath);
            citizen.addPapers(deathDoc);
            citizen.setLifeStatus("Deceased");
            System.out.println("\n" + deathDoc.toString());
        } catch (NullPointerException n){
            System.out.println("Citizen does not exist");
            n.getStackTrace();
        }
    }

    /**
     * registers a marriage
     * @param groomId groom's Identification Number
     * @param brideID bride's Identification Number
     * @param marriageDate Date of marriage
     */
    public void registerMarriage(String groomId, String brideID, String marriageDate){
        Citizen groom = null;
        Citizen bride = null;
        Name groomName = null;
        MarriageDoc marriageDoc = new MarriageDoc(groomId,brideID,marriageDate); //creates a marriage certificate

        try{ //tests if groom citizen object exists
            groom= getCitizen(groomId);
            groomName= groom.getFullName();
        } catch (NullPointerException n){
            System.out.println("Citizen does not exist");
            n.getStackTrace();
        }

         try{ //tests if bride citizen object exists
             bride = getCitizen(brideID);
         } catch (NullPointerException n){
             System.out.println("Citizen does not exist");
             n.getStackTrace();
        }
         if (groom!= null && bride!=null){ //adds papers if both exist
             groom.addPapers(marriageDoc);
             bride.addPapers(marriageDoc);
             assert groomName != null;
             bride.changeLastName(groomName.getLastName()); //changes the bride's last name to that of the groom's
             System.out.println(marriageDoc.toString());
         }
    }

    /**
     * Generates the mailing label for a citizen with their name and address formatted
     * @param id citizen's identification number
     * @return formatted mailing label
     */
    public String mailingLabel(String id){
        String label;
        try {
            Citizen citizen = getCitizen(id);
            Name citName = citizen.getFullName();
            label = citName.getLastName().toUpperCase() + ", " + citName.getFirstName() + " " + citName.getMiddleName() + "\n" + citizen.getAddress().toString();
        }catch(NullPointerException n) {
            label = "Citizen does not exist";
            n.getStackTrace();
        }
        return label;
    }

    /**
     * Gets citizen's mother's information, if mother does  not exist, empty string is returned
     * @param citizenID citizen's id
     * @return citizen's mother's information or empty string
     */
    public String getMother(String citizenID){
        String momInfo = "";
        try{
            Citizen citizen= getCitizen(citizenID);

            Citizen mom = (Citizen) citizen.getParent('M');
            Name momName= mom.getFullName();
            momInfo = mom.getId() + "," + momName.getFirstName() + "," + momName.getMiddleName() + "," + momName.getLastName();
        } catch (NullPointerException n) {
            System.out.println("Citizen does not exist");
            n.getStackTrace();
        }
        return momInfo;
    }

    /**
     * Gets citizen's father's information, if mother does  not exist, empty string is returned
     * @param dadID citizen's id
     * @return citizen's father's information or empty string
     */
    public String getFather(String dadID){
        String dadInfo = "";
        try{
            Citizen citizen= getCitizen(dadID);
            Citizen dad = (Citizen)citizen.getParent('F');
            Name dadName= dad.getFullName();
            dadInfo = dad.getId() + "," + dadName.getFirstName() + "," + dadName.getMiddleName() + "," + dadName.getLastName();
        }catch (NullPointerException n){
            System.out.println("Citizen does not exist");
            n.getStackTrace();
        }
        return dadInfo;
    }

    /**
     * Searches for a citizen by ID Number
     * @param idNum id number to be searched
     * @return String with formatted citizen name
     */
    public String searchById(String idNum){
        String result = "";
        for (Citizen citizen:citizens){
            if (citizen.getId().equals(idNum)){
                result = searchFormattedName(citizen);
            }
        }
        return result;
    }
        

    /**
     * Searches by first and last name
     * @param fName citizen's first name
     * @param lName citizen's last name
     * @return return arrayList of persons with that name
     */
    public String[] searchByName(String fName, String lName){
        String[] result = new String[20];
        int i= 0;
        for (Citizen citizen:citizens){
            Name citizenName= citizen.getFullName();
            if (citizen.getFullName().getFirstName().equals(fName) || citizenName.getLastName().equals(lName)){
                result[i]=(searchFormattedName(citizen));
                i++;
            }
        }
        return result;
    }

    /**
     * Searches by the last name and returns an array of person(s) with similar records
     * @param name last name of citizen to be found
     * @return array of persons with the last name
     */
    public String[] searchByLastName(String name){
        String[] result = new String[20];
        int i= 0;
        for (Citizen citizen:citizens){
            if (citizen.getFullName().getLastName().equals(name)){
                result[i]=(searchFormattedName(citizen));
                i++;
            }
        }
        return result;
    }

    /**
     * Prints the names searched in search by full name and last name
     * @param names array of names
     */
    public void printNames(String[] names){
        try{
            for (String name:names){
                if (name!=null){
                    System.out.println(name);
                }
            }
        } catch (NullPointerException n){
            System.out.println("User(s) with this name not found");
            n.getStackTrace();
        }
    }

    /**
     * rewrites everything to the file and closes it
     * @throws IOException intended io exception
     */
    public void shutdown() throws IOException {
        sniddB.rewrite();
        for (Citizen citizen : citizens) {
            String[] citizenData = new String[15];

            Name name = citizen.getFullName();
            String citId = citizen.getId();
            if (citId== null){
                citizenData[0] = citizen.getFirstId() + "";
            } else {
                citizenData[0] = citizen.getId() + "";
            }
            citizenData[1] = name.getFirstName();
            citizenData[2] = name.getMiddleName();
            citizenData[3] = name.getLastName();
            citizenData[4] = citizen.getGender() + "";
            citizenData[5] = citizen.getYOB() + "";
            if (citizen.getLifeStatus() == 'A') {
                citizenData[6] = "Alive";
            } else {
                citizenData[6] = "Deceased";
            }

            Address address = citizen.getAddress();

            if (address != null) {
                String[] addressLines = address.toString().split("\n");
                for (int i=0; i <5; i++)
                {
                    if (addressLines.length != 5){ //accounts for addresses that do not have all the fields
                        for (int x=0; x<addressLines.length;x++){
                            citizenData[7+x] = addressLines[x];
                            i++;
                        }
                        citizenData [7+i] = "";
                    } else {
                        citizenData[7+i] = addressLines[i];
                    }
                }
            } else {
                citizenData[7] = "";
                citizenData[8] = "";
                citizenData[9] = "";
                citizenData[10] = "";
                citizenData[11] = "";
            }
            if (citizen.getParent('M') != null) {
                citizenData[12] = citizen.getParent('M').getId();
            } else {
                citizenData[12] = "";
            }
            if (citizen.getParent('F') != null) {
                citizenData[13] = citizen.getParent('F').getId();
            } else {
                citizenData[13] = "";
            }

            if(!citizen.getPapers().isEmpty()){
                citizenData[14] = citizen.getPapers();
            }else {
                citizenData[14] = "";
            }
            sniddB.putNext(citizenData);
        }
        sniddB.close();

    }

    /**
     * Gets the citizen information for the
     * @param idNum citizen's id Number
     * @return citizen object that matches the ID
     */
    private Citizen getCitizen(String idNum){
        Citizen citizenInfo = null;
        for (Citizen citizen : citizens) {
            String citId= citizen.getId();
            if(citId==null){
                citId = citizen.getFirstId();
            }
            if (citId.equals(idNum)) {
                citizenInfo = citizen;
            }
        }
        return citizenInfo;
    }

    /**
     * Formatted Citizen Name string for search
     * @param citizen citizen object with name to be formatted
     * @return formatted name string
     */
    private String searchFormattedName(Citizen citizen){
        String searchResults;
        String gender= citizen.getGender() == 'M' ? "Male" : "Female";
        Name name = citizen.getFullName();
        String citizenId = citizen.getId();
        if (citizenId == null){
            citizenId = citizen.getFirstId();
        }
        searchResults = citizenId + "," + gender + "," + name.getFirstName()+"," + name.getMiddleName() + "," + name.getLastName();
        return searchResults;
    }

}
