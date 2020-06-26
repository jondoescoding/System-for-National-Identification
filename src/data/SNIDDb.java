/**
 * <h1>COMP1161 Project 2019/20, Semester 2
 * </h1>
 *  
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 * @author Aaliyah Jonhston - 620130550
 * @version 1.0
 */
package data;
import java.io.*;

public class SNIDDb {
    private String nextLine = null;
    private FileWriter fileWriter;
    private BufferedWriter buffWriter;
    private String delimiter;
    private File dataOfCitizens;
    private BufferedReader buffRead;
    private String fileName;
    
    /**
     * <h3>SNIDDb Class Constructor</h3>
     * @param fileName accepts the name the file
     * @param delimiter accepts the delimiter for the file
     */
    public SNIDDb(String fileName, String delimiter) {
        this.fileName=fileName;
        this.dataOfCitizens = new File(fileName);
        try{
            //this.fileReader_scan = new Scanner(dataOfCitizens);
            buffRead = new BufferedReader(new FileReader(dataOfCitizens));
        }catch (FileNotFoundException | NullPointerException fnf){
            System.out.println("file not found.");
            fnf.getStackTrace();
        }
        this.delimiter = delimiter;
    }

    /**
     * <b>hasNext</b>
     * Checks on whether or not there is a next line in the file 
     * @return true/false on whether or not there is a next line 
     */
    public Boolean hasNext() {
        try{
            nextLine = buffRead.readLine();
        }catch (IOException io){
            io.getStackTrace();
        }
        return (nextLine!=null);
    }

    /**
     * <b>getNext</b>
     * Get the next line of code in the file 
     * @return the next lines of the files 
     */
    public String[] getNext() {
        String[] lines = null;
        try{
            lines = (nextLine.split(delimiter));
        }catch (NullPointerException io){
            io.getStackTrace();
        }
        return lines;
    }

    /**
    * <b>rewrite</b>
    * Overwrites data in the file 
    * @throws IOException checks on whether or not the file was found 
    */
    public void rewrite() throws IOException {
        this.buffRead.close();
        try {
            fileWriter = new FileWriter(dataOfCitizens);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>putNext</b>
     * This method takes an array of strings and places them in order in a file
     * @param entries takes an array of string to be put into a file 
     */
    public void putNext(String[] entries) throws IOException{
        try {
            String lines= "";
            if(dataOfCitizens.length()!=0){
                this.buffWriter= new BufferedWriter(new FileWriter(dataOfCitizens.getPath(), true));
                for (String part : entries) {
                    lines += part + delimiter;
                }
                lines+="\n";
                buffWriter.append(lines);
                buffWriter.close();
            }else{
                this.fileWriter = new FileWriter(fileName);
                for (String part : entries) {
                    lines += part + delimiter;
                }
                lines+="\n";
                fileWriter.write(lines);
                fileWriter.close();
            }
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
            f.getStackTrace();
        }
    }

    /**
     * <b>close</b>
     * This closes the file 
     */
    public void close() throws IOException {
        fileWriter.close();
    }
}
