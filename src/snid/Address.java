package snid;

/**
 * @author Aaliyah Johnston 620130550
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 * @version 1.0
 * Class that creates address object
 */
public class Address {
    /*The lines of the Address: String */
    protected String lines;
    private String[] address;


    /**
     * Constructor for the address class that accepts the address from the user
     * @param lines the lines of the address
     */
    public Address(String lines){
        try{
            String delimiter = "\\|";
            String[] tempAddress = lines.split(delimiter);

            try {
                address = new String[tempAddress.length];
                for (int i = 0; i < tempAddress.length; i++) {
                    if (!tempAddress[i].equals("")) {
                        address[i] = tempAddress[i];
                    }
                }
            }catch (NullPointerException n){
                n.getStackTrace();
            }
        }catch (NullPointerException n){
            n.getStackTrace();
        }

    }

    /**
     * Getter method for user's the country of residence
     * @return the country
     */
    public String getCountry(){
        return address[address.length-1];
    }

    /**
     * Formatted String showing the address of the user
     * @return formatted address
     */
    public String toString(){
        String addressString="";
        try {
            for (int i = 0; i < address.length - 1; i++) {
                addressString += address[i] + "\n";
            }
            addressString += getCountry();
        }catch (NullPointerException | ArrayIndexOutOfBoundsException n){
            n.getStackTrace();
        }
        return addressString;
        //return streetName + "\n" + zone + "\n" + getCountry();
    }
}

