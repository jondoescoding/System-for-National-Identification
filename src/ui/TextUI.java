/**
 * @author Aaliyah Johnston 620130550
 *@author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 * @version 1.0
 * Class which controls the test menu interface
 */
package ui;
import java.util.Scanner;
import java.io.*;
import app.*;

public class TextUI {

    private Scanner scan = new Scanner(System.in);
    /**
     * The first name of the individual
     */
    private String fName; 
    /**
     * The Middle name of the inidiviual
     */
    private String mName;
    /**
     * The last name of the individual
     */
    private String lName;
    /**
     * The citizen's ID
     */
    private String citId;
    /**
     * The street name of the address
     */
    private String street;
    /**
     * The name of the town in the address
     */
    private String town;
    /**
     * The parish of the address
     */
    private String parish;
    /**
     * The country of the address
     */
    private String country;
    /**
    * Cause of death
    */
    private String cOfDeath;
    /**
     * The date of death
     */
    private String dOfDeath;
    /**
     * The groom's ID
     */
    private String groomId;
    /**
     * the bride's ID
     */
    private String brideId;
    /**
     * The date of the marriage
     */
    private String dOfMarriage;
    /**
     * The options given on the menu
     */
    private char menuOption;
    /**
     * The various search options
     */
    private char searchOption;
    /**
     * The gender of the citizens
     */
    private char gender;

    /**
     * The place of death
     */
    private String pOfDeath;

    /**
     * <b>MENU</b>
     * @return The menu for the textUI
     */
    public char menu(){
        System.out.println("\n");
        System.out.println("*******************************");
        System.out.println("a. Register a Birth");
        System.out.println("b. Update Parent Data");
        System.out.println("c. Update a Citizen's Address");
        System.out.println("d. Register a Death");
        System.out.println("e. Register a Marriage");
        System.out.println("f. Generate a Mailing Label");
        System.out.println("g. Search");
        System.out.println("h. Exit Application");
        System.out.println("*******************************");

        return scan.next().charAt(0);
    }

    /**
     * <b>searchMenu</b>
     * @return the search menu 
     */
    public char searchMenu(){
        System.out.println("*******************");
        System.out.println("a. Search by Id");
        System.out.println("b. Search by Name");
        System.out.println("*******************");
        System.out.println("\n");

        return scan.next().charAt(0);
    }

    /**
     * <b>GO</b>
     * @param app accepts a SNIDApp object
     * @throws IOException some sort of exception has occured within the file 
     */
    public void go(SNIDApp app) throws IOException {
        menuOption = menu();
        while (menuOption != 0){
            switch (menuOption){
                case 'a':
                    System.out.println("Register a Birth");
                    System.out.println("------------------");
                    System.out.println("Gender:");
                    gender = scan.next().charAt(0);
                    System.out.println("Year of Birth:");
                    int yob = scan.nextInt();
                    scan.nextLine();
                    System.out.println("First Name:");
                    fName = scan.nextLine();
                    System.out.println("Middle Name:");
                    mName = scan.nextLine();
                    System.out.println("Last Name:");
                    lName = scan.nextLine();
                    app.registerBirth(gender,yob,fName,mName,lName);
                    break;
                case 'b':
                    System.out.println("Update a Parent Citizen");
                    System.out.println("------------------------");
                    System.out.println("Enter Citizen's  Identification Number:");
                    citId = scan.next();
                    System.out.println("Enter Father's Identification Number :");
                    String fatherId = scan.next();
                    System.out.println("Enter Mother's Identification Number:");
                    String motherId = scan.next();
                    app.addParentData(citId, fatherId,motherId);
                    break;
                case 'c':
                    System.out.println("Update a Citizen's Address");
                    System.out.println("----------------------------");
                    System.out.println("Citizen's Identification Number:");
                    citId = scan.next();
                    System.out.println("Street Address:");
                    scan.nextLine();
                    street = scan.nextLine();
                    //scan.nextLine();
                    System.out.println("Town/City:");
                    town = scan.nextLine();
                    System.out.println("Parish/County:");
                    parish = scan.nextLine();
                    System.out.println("Country:");
                    country = scan.nextLine();
                    app.updateAddress(citId,street,town,parish,country);
                    break;
                case 'd':
                    System.out.println("Register a death");
                    System.out.println("------------------");
                    System.out.println("Citizen's Identification Number: ");
                    citId= scan.next();
                    scan.nextLine();
                    System.out.println("Cause of Death:");
                    cOfDeath = scan.nextLine();
                    System.out.println("Date of Death:");
                    dOfDeath = scan.nextLine();
                    System.out.println("Place of Death:");
                    pOfDeath = scan.nextLine();
                    app.registerDeath(citId,cOfDeath,dOfDeath,pOfDeath);
                    break;
                case 'e':
                    System.out.println("Register a Marriage");
                    System.out.println("--------------------");
                    System.out.println("Groom's Identification Number:");
                    groomId= scan.next();
                    System.out.println("Bride's Identification Number:");
                    brideId = scan.next();
                    scan.nextLine();
                    System.out.println("Date of Marriage:");
                    dOfMarriage = scan.nextLine();
                    app.registerMarriage(groomId,brideId,dOfMarriage);
                    break;
                case 'f':
                    System.out.println("Generate a Mailing Label");
                    System.out.println("-------------------------");
                    System.out.println("Enter Citizen's Identification Number:");
                    citId = scan.next();
                    System.out.println(app.mailingLabel(citId));
                    break;
                case 'g':
                    System.out.println("Search");
                    System.out.println("------------------");
                    searchOption = searchMenu();
                    if (searchOption == 'a'){
                        System.out.println("Search by Id");
                        System.out.println("-------------");
                        System.out.println("Enter Citizen's Identification Number:");
                        citId = scan.next();
                        System.out.println(app.searchById(citId));
                    } else if (searchOption == 'b'){
                        System.out.println("Search by Name");
                        System.out.println("----------------");
                        scan.nextLine();
                        System.out.println("First Name: ");
                        fName = scan.nextLine();
                        System.out.println("Last Name:");
                        lName = scan.nextLine();
                        String[] names = (app.searchByName(fName,lName));
                        app.printNames(names);
                    } else {
                        System.out.println("Invalid option");
                        System.out.println("Select\n 1. Re-enter option \n2. Exit search");
                        int errorOption = scan.nextInt();
                        if (errorOption == 1){
                            searchOption = searchMenu();
                        } else {
                            break;
                        }
                    }
                    break;
                case 'h':
                    app.shutdown();
                    System.exit(0);
            }
            menuOption = menu();
        }
    }


}
