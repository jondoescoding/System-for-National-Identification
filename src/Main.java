import app.*;
import ui.*;
import java.io.IOException;
/**
 * Group Members
 * @author Aaliyah Johnston 620130550
 * @author <a href="mailto:jonathan.logos34@gmal.com">Jonathan White - 60129431</a>
 *	@version 1.0
 */
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * Driver for the System for National Ids (SNID).
 */
public class Main {
    public static void main(String[] args) throws IOException {
        SNIDApp app = new SNIDApp("SNID0.txt", ",");
        Scanner scan = new Scanner(System.in);
        System.out.println("*******************************");
        System.out.println("1. TextUI");
        System.out.println("2. GUI");
        System.out.println("3. Exit");
        System.out.println("*******************************");
        System.out.println("\n");
        int menuOption = scan.nextInt();
        if(menuOption == 1){
            TextUI ui = new TextUI();
            ui.go(app);
        }else if(menuOption==2){
            JFrame snid = new JFrame("SNID GUI");
            snid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GUI sgui = new GUI(app);
            snid.getContentPane().add(sgui);
            snid.setResizable(false);
            snid.pack();
            snid.setVisible(true);
        }else if(menuOption==3){
            System.exit(0);
        }else{
            System.out.println("ERROR WRONG OPTION");
        }
        scan.close();
    }
}
